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
    private JTextField numberField;

    public View() {
        // Fenster-Einstellungen
        setTitle("Kniffel Spiel");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Komponenten initialisieren
        alertButton = new JButton("Alert");
        rollDiceButton = new JButton("Roll Dice");
        numberField = new JTextField(10);

        // Komponenten dem Fenster hinzufügen
        add(alertButton);
        add(numberField);
        add(rollDiceButton);

        // ActionListener für Alert Button
        alertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(View.this, "Ehm das ist nh Nachricht I guess XD!", "Alert", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // ActionListener für Roll Dice Button
        rollDiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int inputNumber = Integer.parseInt(numberField.getText());
                    int randomNumber = new Random().nextInt(6) + 1; // Würfeln einer Zahl zwischen 1 und 6
                    JOptionPane.showMessageDialog(View.this, "Gewürfelte Zahl: " + randomNumber, "Würfeln", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(View.this, "Bitte eine gültige Zahl eingeben.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        // GUI erstellen und anzeigen
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new View().setVisible(true);
            }
        });
    }
}
