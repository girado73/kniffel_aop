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
 * Diese Klasse repräsentiert die grafische Benutzeroberfläche für das Kniffel-Spiel.
 * 
 * @author Max Hollerbaum
 */

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
  private JTextField numberField;
  private JLabel counterLabel;
  private int counter;
  private final int maxRolls = 3; // Maximale Anzahl an Würfen
  private Dice dice; // Instanz der Dice-Klasse
  private Sheet[] sheetlist; // Spielerinformationen hier drinnen
  private JLabel sheetdisplay; // String mit den informationen über das derzeitige Sheet
  private JLabel dicedisplay; // Anzeige des derzeitigen Wurfs
  private JList<String> feldliste;
  private int feldindex; // Index des feldes in sheet welches verändert wird
  private final String[] options = { "Einsen", "Zweien", "Dreien", "Vieren", "Fünfen", "Sechsen", "Dreierpasch",
      "Viererpasch", "Full House", "Kleine Straße", "Große Straße", "Kniffel" };;
  private int[] würfelstand;

  /**
   * Konstruktor für die View Klasse.
   * Initialisiert das Fenster und die Komponenten.
   */
  public View() {
    // Fenster-Einstellungen
    setTitle("Kniffel Spiel");
    setSize(800, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new FlowLayout());

    // Würfel-Instanz initialisieren
    dice = new Dice();
    // TODO Playernumber aus Main übernehmen
    final int playernumber = 3;
    // TODO sheetlist aus Main übernehmen
    sheetlist = new Sheet[playernumber];

    // GUI-Komponenten initialisieren
    alertButton = new JButton("Alert");
    rollDiceButton = new JButton("Roll Dice");
    updateAndResetButton = new JButton("Update Sheet and Reset Counter");
    numberField = new JTextField(10);
    counterLabel = new JLabel("Rolls: 0");
    sheetdisplay = new JLabel("<html><body>" + sheetlist[0].sheet_to_string().replace("\n", "<br>") + "</body></html>");
    dicedisplay = new JLabel("");
    feldliste = new JList<String>(options);
    würfelstand = new int[5];

    // Feldindex mit 0 initialisieren
    feldindex = 0;

    // Komponenten dem Fenster hinzufügen
    add(alertButton);
    add(numberField);
    add(rollDiceButton);
    add(counterLabel);
    add(updateAndResetButton);
    add(sheetdisplay);
    add(dicedisplay);
    add(feldliste);

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
        // TODO hier statt [0] die "spielernummer eintragen"
        sheetdisplay.setText("<html><body>" + sheetlist[0].sheet_to_string().replace("\n", "<br>"));
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
  }

  /**
   * Zeigt einen Alert-Dialog mit einer Nachricht an.
   */
  private void showAlert() {
    JOptionPane.showMessageDialog(this, "Das ist eine Nachricht!", "Alert", JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Würfelt eine Zahl, zeigt sie an und aktualisiert den Counter.
   * Aktiviert den Update-Button, wenn die maximale Anzahl an Würfen erreicht ist.
   */
  private void rollDice() {
    if (counter < maxRolls) {
      try {
        // Überprüfung der Eingabe, ob sie eine gültige Zahl ist
        int inputNumber = Integer.parseInt(numberField.getText());

        // Überprüfung, ob die Zahl zwischen 1 und 5 liegt
        if (inputNumber < 1 || inputNumber > 5) {
          throw new IllegalArgumentException("Die Zahl muss zwischen 1 und 5 liegen.");
        }

        // Würfeln mehrerer Würfel basierend auf der eingegebenen Zahl
        int[] rollResults = dice.rollMultiple(inputNumber);
        würfelstand = rollResults;

        // Anzeigen der gewürfelten Zahl
        JOptionPane.showMessageDialog(this, "Gewürfelte Zahlen: " + Arrays.toString(rollResults), "Würfeln",
            JOptionPane.INFORMATION_MESSAGE);

        // Counter erhöhen und aktualisieren
        dicedisplay.setText(Arrays.toString(rollResults));

        // setListButton wird nach dem dicedisplay ein value hat aktiviert
        counter++;
        counterLabel.setText("Rolls: " + counter);

        // Aktivieren des Update-Buttons, wenn maximale Anzahl erreicht ist
        if (counter > 0) {
          updateAndResetButton.setEnabled(true);
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
    // Beispiel: Update des 'einser' Felds im Sheet mit einer gewürfelten Zahl
    setList();
    resetCounter();
    // Anzeige des aktualisierten Werts des 'einser' Felds
    JOptionPane.showMessageDialog(this, options[feldindex] + "-Feld aktualisiert: ", "Update Sheet",
        JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Setzt den Counter zurück und deaktiviert den Update-Button.
   */
  private void resetCounter() {
    counter = 0;
    counterLabel.setText("Rolls: " + counter);
    updateAndResetButton.setEnabled(false);
  }

  private void setList() {
    // hier wird der in feldindex festgelegte index auf Sheet.indexSet eingesetzt um
    // im Sheet das passende feld zu ändern

    // TODO hier statt [0] die spielernummer eintragen
    // TODO die quickBrain kann auch durch eine klassenvariable abgelößt werden
    Brain quickBrain = new Brain(würfelstand);
    sheetlist[0].indexSet(feldindex, quickBrain.getSumvalues(würfelstand)[feldindex]);
  }

  /**
   * Einstiegspunkt des Programms.
   * Erstellt und zeigt das GUI-Fenster.
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new View().setVisible(true);
      }
    });
  }
}
