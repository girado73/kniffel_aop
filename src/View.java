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
public class View extends JFrame {
// Gemeinsame GUI-Komponenten und neue Komponenten aus beiden Branches
private JButton alertButton;
private JButton rollDiceButton;
private JButton updateAndResetButton;
private JTextField numberField;
private JLabel counterLabel;
private int counter;
private final int maxRolls = 3; // Maximale Anzahl an Würfen
private Dice dice; // Instanz der Dice-Klasse
private Sheet[] sheetlist; // Spielerinformationen hier drinnen
private JLabel sheetdisplay; // String mit den Informationen über das derzeitige Sheet
private JLabel dicedisplay; // Anzeige des derzeitigen Wurfs
private JList<String> feldliste;
private int feldindex; // Index des Feldes in Sheet welches verändert wird
private final String[] options = { "Einsen", "Zweien", "Dreien", "Vieren", "Fünfen", "Sechsen", "Dreierpasch",
    "Viererpasch", "Full House", "Kleine Straße", "Große Straße", "Kniffel" };

// Konstruktor für die View Klasse.
// Initialisiert das Fenster und die Komponenten.
public View() {
    // Fenster-Einstellungen
    setTitle("Kniffel Spiel");
    setSize(800, 800); // Übernehme die größere Größe aus dem ersten Abschnitt
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new FlowLayout());

    // Würfel-Instanz initialisieren
    dice = new Dice();
    final int playernumber = 3;
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
            sheetdisplay.setText("<html><body>" + sheetlist[0].sheet_to_string().replace("\n", "<br>") + "</body></html>");
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

// Zeigt einen Alert-Dialog mit einer Nachricht an.
private void showAlert() {
    JOptionPane.showMessageDialog(this, "Das ist eine Nachricht!", "Alert", JOptionPane.INFORMATION_MESSAGE);
}

// Würfelt eine Anzahl von Würfeln basierend auf der Eingabe im Textfeld,
// zeigt die Ergebnisse an und aktualisiert den Counter.
private void rollDice() {
    if (counter < maxRolls) {
        try {
            // Überprüfung der Eingabe, ob sie eine gültige Zahl ist
            int inputNumber = Integer.parseInt(numberField.getText());

            // Würfeln einer Zahl und Anzeigen des Ergebnisses
            int randomNumber = dice.roll();
            dicedisplay.setText("[" + randomNumber + "]");
            counter++;
            counterLabel.setText("Rolls: " + counter);

            // Aktivieren des Update-Buttons, wenn maximale Anzahl erreicht ist
            if (counter == maxRolls) {
                updateAndResetButton.setEnabled(true);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Bitte eine gültige Zahl eingeben.", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Maximale Anzahl an Würfen erreicht. Bitte das Sheet aktualisieren und den Counter zurücksetzen.", "Fehler", JOptionPane.ERROR_MESSAGE);
    }
}

// Aktualisiert das Sheet und setzt den Counter zurück.
private void updateAndReset() {
    // Beispielhafte Implementierung der Update-Logik
    resetCounter();
}

// Setzt den Counter zurück und deaktiviert den Update-Button.
private void resetCounter() {
    counter = 0;
    counterLabel.setText("Rolls: 0");
    updateAndResetButton.setEnabled(false);
}

        counterLabel.setText("Rolls: " + counter);

// Fortsetzung der Methode rollDice aus dem vorherigen Konflikt
if (counter < maxRolls) {
    try {
        // Überprüfung der Eingabe, ob sie eine gültige Zahl ist
        int inputNumber = Integer.parseInt(numberField.getText());

        // Würfeln einer Zahl und Anzeigen des Ergebnisses
        int randomNumber = dice.roll();
        dicedisplay.setText("[" + randomNumber + "]");
        counter++;
        counterLabel.setText("Rolls: " + counter);

        // Aktivieren des Update-Buttons, wenn mindestens ein Wurf erfolgt ist
        if (counter > 0) {
            updateAndResetButton.setEnabled(true);
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Bitte eine gültige Zahl eingeben.", "Fehler", JOptionPane.ERROR_MESSAGE);
    }
} else {
    JOptionPane.showMessageDialog(this, "Maximale Anzahl an Würfen erreicht. Bitte das Sheet aktualisieren und den Counter zurücksetzen.", "Fehler", JOptionPane.ERROR_MESSAGE);
}
}

// Ergänzung der main-Methode zum Starten des Programms
/**
 * Einstiegspunkt des Programms.
 * Erstellt und zeigt das GUI-Fenster.
 */
public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            new View().setVisible(true);
        }
    });
}

    }
  }

  /**
   * Aktualisiert das 'einser' Feld im Sheet und setzt den Counter zurück.
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
    sheetlist[0].indexSet(feldindex, Integer.parseInt(dicedisplay.getText().replace("[", "").replace("]", "")));
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
