package src;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * View
 * Diese Klasse repräsentiert die grafische Benutzeroberfläche für das
 * Kniffel-Spiel.
 *
 * @author Max Hollerbaum, Ricardo Güttner
 */
public class View extends JFrame {
  private JButton alertButton;
  private JButton rollDiceButton;
  private JButton updateAndResetButton;
  private JButton rerollButton;
  private JTextField numberField;
  private JLabel counterLabel;
  private int counter;
  private final int maxRolls = 3; // Maximale Anzahl an Würfen
  private Dice dice; // Instanz der Dice-Klasse
  private Sheet[] sheetlist;
  private JLabel sheetdisplay; // String mit den informationen über das derzeitige Sheet
  private JLabel dicedisplay; // Anzeige des derzeitigen Wurfs
  private JList<String> feldliste;
  private int feldindex; // Index des feldes in sheet welches verändert wird
  private final String[] options = { "Einsen", "Zweien", "Dreien", "Vieren", "Fünfen", "Sechsen", "Dreierpasch",
      "Viererpasch", "Full House", "Kleine Straße", "Große Straße", "Kniffel", "Chance" };;
  private int[] würfelstand;
  private int activeSpielerNr = 0;
  private JLabel spieleridendify;
  private int spieleranzahl;

  /**
   * Konstruktor für die View Klasse.
   * Initialisiert das Fenster und die Komponenten.
   */
  public View(Sheet[] mainSheetlist, int mainSpieleranzahl) {
    // Fenster-Einstellungen
    setTitle("Kniffel Spiel");
    setSize(800, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5); // Abstand zwischen den Komponenten
    gbc.fill = GridBagConstraints.HORIZONTAL;

    // Würfel-Instanz initialisieren
    dice = new Dice();
    sheetlist = mainSheetlist;

    // GUI-Komponenten initialisieren
    alertButton = new JButton("Spielanleitung");
    rollDiceButton = new JButton("Roll Dice");
    updateAndResetButton = new JButton("Update Sheet and Reset Counter");
    rerollButton = new JButton("Reroll Selected Die");
    numberField = new JTextField(10);
    counterLabel = new JLabel("Rolls: 0");
    sheetdisplay = new JLabel(
        "<html><body>" + sheetlist[activeSpielerNr].sheet_to_string().replace("\n", "<br>") + "</body></html>");
    dicedisplay = new JLabel("");
    feldliste = new JList<>(options);
    würfelstand = new int[5];
    spieleridendify = new JLabel("Spieler " + activeSpielerNr);
    spieleranzahl = mainSpieleranzahl;

    // Feldindex mit 0 initialisieren
    feldindex = 0;

    // Komponente für Spielanleitung
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    add(alertButton, gbc);

    // Nummer eingeben
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    add(numberField, gbc);

    // Roll Dice Button
    gbc.gridx = 1;
    gbc.gridy = 1;
    add(rollDiceButton, gbc);

    // Counter Label neben Roll Dice Button
    gbc.gridx = 2;
    gbc.gridy = 1;
    add(counterLabel, gbc);

    // Komponente für Reroll Selected Die
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 2;
    add(rerollButton, gbc);

    // Anzeige der geworfenen Würfel und die Auswahl der Felder
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 2;
    add(dicedisplay, gbc);

    gbc.gridx = 2;
    gbc.gridy = 3;
    gbc.gridwidth = 1;
    add(feldliste, gbc);

    // Komponente für Update Sheet and Reset Counter
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 3;
    add(updateAndResetButton, gbc);

    // Das aktuelle Sheet
    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.gridwidth = 3;
    gbc.fill = GridBagConstraints.BOTH;
    add(sheetdisplay, gbc);

    gbc.gridx = 2;
    gbc.gridy = 5;
    add(spieleridendify, gbc);

    // ActionListener für Alert Button
    alertButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showAlert();
      }
    });

    // ActionListener für Roll Dice Button
    rollDiceButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        rollDice();
      }
    });

    // ActionListener für Update and Reset Button
    updateAndResetButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        updateAndReset();

        // update das sheetdisplay
        if (activeSpielerNr == spieleranzahl - 1) {
          activeSpielerNr = 0;
        } else {
          activeSpielerNr++;
        }
        spieleridendify.setText("Spieler" + activeSpielerNr);
        sheetdisplay.setText(
            "<html><body>" + sheetlist[activeSpielerNr].sheet_to_string().replace("\n", "<br>") + "</body></html>");

      }
    });

    // ActionListener für Reroll Button
    rerollButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        rerollSelectedDie();
      }
    });

    // ListSelectionListener für die Felderliste
    feldliste.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        feldindex = feldliste.getSelectedIndex();
      }
    });

    // Initialisierung des Counters und Deaktivierung des Update-Buttons
    counter = 0;
    updateAndResetButton.setEnabled(false);
    rerollButton.setEnabled(false); // Reroll Button wird erst aktiv, wenn mindestens einmal geworfen wurde
  }

  /**
   * Zeigt einen Alert-Dialog mit einer Nachricht an.
   */
  private void showAlert() {
    JOptionPane.showMessageDialog(this,
        "Spielanleitung\n1. Um deinen Spielzug zu beginnen, wirf die Würfen indem du auf 'Roll Dice' klickst." +
            "\n2. Schau dir deine Würfel an und überlege dir welche Würfel du nochmal würfeln möchtest.\n3. " +
            "Gib die Stelle der Würfel in das Feld ein, die du neu würfeln möchtest.\n4. " +
            "Würfle neu indem du auf 'Reroll Selected Dice' klickst.\n5. " +
            "Wenn du noch nicht zufrieden bist, nutze deinen dritten Versuch wie in Schritten 2 bis 4 beschrieben.\n6. "
            +
            "Wähle aus in welche Kategorie dein Spielzug zählen soll.\n7. " +
            "Um deinen Zug abzuschließen, klicke auf 'Update Sheet and Reset Counter'.\n8. " +
            "Der nächste Spielzug kann mit 'Roll Dice' gestartet werden.",
        "Spielanleitung", JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Würfelt eine Zahl, zeigt sie an und aktualisiert den Counter.
   * Aktiviert den Update-Button, wenn die maximale Anzahl an Würfen erreicht ist.
   */
  private void rollDice() {
    if (counter < maxRolls) {
      try {
        // Überprüfung der Eingabe, ob sie eine gültige Zahl ist
        int inputNumber = 5;

        // Würfeln mehrerer Würfel basierend auf der eingegebenen Zahl
        int[] rollResults = dice.rollMultiple(inputNumber);
        würfelstand = rollResults;

        // Anzeigen der geworfenen Zahlen
        JOptionPane.showMessageDialog(this, "Geworfene Zahlen: " + Arrays.toString(rollResults), "Würfeln",
            JOptionPane.INFORMATION_MESSAGE);

        // Counter erhöhen und aktualisieren
        dicedisplay.setText(Arrays.toString(rollResults));

        // setListButton wird nach dem dicedisplay ein value hat aktiviert
        counter++;
        counterLabel.setText("Rolls: " + counter);

        // Aktivieren des Update-Buttons, wenn maximale Anzahl erreicht ist
        if (counter > 0) {
          updateAndResetButton.setEnabled(true);
          rerollButton.setEnabled(true); // Reroll Button aktivieren
        }
      } catch (NumberFormatException ex) {
        // Fehlermeldung anzeigen, wenn die Eingabe keine gültige Zahl ist
        JOptionPane.showMessageDialog(this, "Bitte eine gültige Zahl eingeben.", "Fehler", JOptionPane.ERROR_MESSAGE);
      } catch (IllegalArgumentException ex) {
        // Fehlermeldung anzeigen, wenn die Eingabe außerhalb des Bereichs liegt
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
      }
    } else {
      // Fehlermeldung anzeigen, wenn die maximale Anzahl an Würfen erreicht ist
      JOptionPane.showMessageDialog(this,
          "Maximale Anzahl an Würfen erreicht. Bitte das Sheet aktualisieren und den Counter zurücksetzen.", "Fehler",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Aktualisiert das passende Feld im Sheet und setzt den Counter zurück.
   */
  private void updateAndReset() {
    // Beispiel: Update des 'einser' Felds im Sheet mit einer geworfenen Zahl
    setList();
    resetCounter();
    // Anzeige des aktualisierten Werts des 'einser' Felds
    JOptionPane.showMessageDialog(this,
        options[feldindex] + "-Feld aktualisiert. " + Brain.getSumvalues(würfelstand, sheetlist[activeSpielerNr])[feldindex] + " Punkte",
        "Update Sheet",
        JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Setzt den Counter zurück und deaktiviert den Update-Button.
   */
  private void resetCounter() {
    counter = 0;
    counterLabel.setText("Rolls: " + counter);
    updateAndResetButton.setEnabled(false);
    rerollButton.setEnabled(false);
  }

  private void setList() {
    // hier wird der in feldindex festgelegte index auf Sheet.indexSet eingesetzt um
    // im Sheet das passende feld zu ändern

    sheetlist[activeSpielerNr].indexSet(feldindex, Brain.getSumvalues(würfelstand, sheetlist[activeSpielerNr])[feldindex]);
  }

  /**
   * Würfelt die aktuell ausgewählten Würfel neu.
   */
  private void rerollSelectedDie() {
    if (counter < maxRolls) {
      try {
        // Eingabe aus dem Textfeld lesen und in einen Array von Strings aufteilen
        String[] indices = numberField.getText().split(",");
        if (indices[0].equals("d")) {
              // wenn die debug flag gesetzt wird, dann gehen wir in die neue funktion und
              // droppen die alte
              // außerdem müssen wir die debug flag vor debugRoll loswerden
              debugRoll(Arrays.copyOfRange(indices, 1, indices.length));
              return;
        }

        for (int i = 0; i < indices.length; i++) {
          int value = Integer.parseInt(indices[i].trim()); // Konvertiere das String-Element in eine Ganzzahl
          value -= 1; // Ziehe 1 von der Zahl ab
          indices[i] = Integer.toString(value); // Konvertiere die Zahl wieder in einen String
        }

        boolean counteradd = false;

        // Für jeden eingegebenen Index den entsprechenden Würfel neu würfeln
        for (String indexStr : indices) {
          int index = Integer.parseInt(indexStr.trim()); // Leerzeichen entfernen und in Integer konvertieren

          // Überprüfen, ob ein gültiger Würfel ausgewählt ist
          if (index >= 0 && index < würfelstand.length) {
            // Nur den ausgewählten Würfel neu würfeln
            würfelstand = dice.rollSpecific(würfelstand, index);
            counteradd = true;

          } else {
            JOptionPane.showMessageDialog(this, "Kein gültiger Würfel ausgewählt: " + index, "Fehler",
                JOptionPane.ERROR_MESSAGE);
          }
        }
        if (counteradd) {
          counter++;
          counterLabel.setText("Rolls: " + counter);
        }
        // Die neuen Würfelergebnisse anzeigen
        dicedisplay.setText(Arrays.toString(würfelstand));

      } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Bitte gültige Würfelindizes eingeben, getrennt durch Kommas.", "Fehler",
            JOptionPane.ERROR_MESSAGE);
      }
    } else {
      JOptionPane.showMessageDialog(this,
          "Maximale Anzahl an Würfen erreicht. Bitte das Sheet aktualisieren und den Counter zurücksetzen.", "Fehler",
          JOptionPane.ERROR_MESSAGE);

    }

  }

  private void debugRoll(String[] würfelString) {
    int würfelstandcounter = 0;
    if (würfelString.length != 5) {
      JOptionPane.showMessageDialog(this, "Nicht genau 5 Zahlen eingegeben", "Fehler", JOptionPane.ERROR_MESSAGE);
      return;
    }
    for (String indexStr : würfelString) {
      int index = Integer.parseInt(indexStr.trim()); // Leerzeichen entfernen und in Integer konvertieren

      // Überprüfen ob es würfelzahlen sind
      if (index >= 1 && index <= 6) {
        // Nur den ausgewählten Würfel neu würfeln
        würfelstand[würfelstandcounter] = index;
        würfelstandcounter++;
      } else {
        JOptionPane.showMessageDialog(this, "Würfel geht nur von 1 bis 6, ausgewählt wurde: " + index, "Fehler",
            JOptionPane.ERROR_MESSAGE);
      }
    }
    // würfelstand festsetzen
    dicedisplay.setText(Arrays.toString(würfelstand));
    // info über den roll
    Brain.printSumValues(würfelstand);
  }

  /**
   * Einstiegspunkt des Programms.
   * Erstellt und zeigt das GUI-Fenster.
   */
  public static void main(String[] args) {

    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        Sheet[] mySheetList = new Sheet[3];
        for (int i = 0; i < mySheetList.length; i++) {
          mySheetList[i] = new Sheet(); // oder eine passende Initialisierung für deine Anwendung
        }
        new View(mySheetList, mySheetList.length).setVisible(true);
      }
    });
  }
}
