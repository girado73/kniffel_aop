import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * View
 * Here is the GUI constructed
 */
public class View extends JFrame {
    private JButton alertButton;
    private JButton rollDiceButton;
    private JButton updateAndResetButton;
    private JTextField numberField;
    private JLabel counterLabel;
    private int counter;
    private final int maxRolls = 3; // Maximale Anzahl an Würfen

    public View() {
        // Fenster-Einstellungen
        setTitle("Kniffel Spiel");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Komponenten initialisieren
        alertButton = new JButton("Alert");
        rollDiceButton = new JButton("Roll Dice");
        updateAndResetButton = new JButton("Update Einser and Reset Counter");
        numberField = new JTextField(10);
        counterLabel = new JLabel("Rolls: 0");

        // Komponenten dem Fenster hinzufügen
        add(alertButton);
        add(numberField);
        add(rollDiceButton);
        add(counterLabel);
        add(updateAndResetButton);

        // ActionListener für Alert Button
        alertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAlert();
            }
        });

        // ActionListener für Roll Dice Button
        rollDiceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });

        // ActionListener für Update and Reset Button
        updateAndResetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateAndReset();
            }
        });

        // Initialisierung des Counters und Button deaktivieren
        counter = 0;
        updateAndResetButton.setEnabled(false);
    }

    private void showAlert() {
        JOptionPane.showMessageDialog(this, "Das ist eine Nachricht!", "Alert", JOptionPane.INFORMATION_MESSAGE);
    }

    private void rollDice() {
        if (counter < maxRolls) {
            try {
                int inputNumber = Integer.parseInt(numberField.getText()); // Überprüfung der Eingabe
                int randomNumber = new Random().nextInt(6) + 1; // Würfeln einer Zahl zwischen 1 und 6
                JOptionPane.showMessageDialog(this, "Gewürfelte Zahl: " + randomNumber, "Würfeln", JOptionPane.INFORMATION_MESSAGE);
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

    private void updateAndReset() {
        // Beispiel: Update des 'einser' Felds im Sheet und Reset des Counters
        Sheet.einser += new Random().nextInt(6) + 1; // Zufälliger Wert zwischen 1 und 6 hinzufügen
        resetCounter();
        JOptionPane.showMessageDialog(this, "Einser-Feld aktualisiert: " + Sheet.einser, "Update Sheet", JOptionPane.INFORMATION_MESSAGE);
    }

    private void resetCounter() {
        counter = 0;
        counterLabel.setText("Rolls: " + counter);
        updateAndResetButton.setEnabled(false);
    }

    public static void main(String[] args) {
        // GUI erstellen und anzeigen
        SwingUtilities.invokeLater(new Runnable() {          
            public void run() {
                new View().setVisible(true);
            }
        });
    }
}
