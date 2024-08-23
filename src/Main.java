package src;

import javax.swing.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Scanner für die Konsoleneingabe
        Scanner scanner = new Scanner(System.in);

        int numberOfPlayers = 0;
        boolean validInput = false;

        // Schleife zur Eingabe der Anzahl der Spieler
        while (!validInput) {
            System.out.print("Bitte geben Sie die Anzahl der Spieler ein: ");
            String input = scanner.nextLine();

            try {
                numberOfPlayers = Integer.parseInt(input);
                if (numberOfPlayers > 0) {
                    validInput = true;
                } else {
                    System.out.println("Die Anzahl der Spieler muss eine positive Zahl sein.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ungültige Eingabe. Bitte geben Sie eine gültige Zahl ein.");
            }
        }

        scanner.close();
        // Erstellen der Sheet-Instanz, die die Spielstände verwaltet
        Sheet sheet = new Sheet();

        // Erstellen der View-Instanz und übergeben der Sheet-Instanz
        View view = new View();
        
        // Starten des GUI
        SwingUtilities.invokeLater(() -> {
            view.setVisible(true);
        });

        // Spiel-Loop zur Überprüfung, ob das Sheet voll ist
        Timer gameTimer = new Timer(5000, e -> {
            if (sheet.isFull()) {
                ((Timer) e.getSource()).stop(); // Stoppen des Timers, wenn das Sheet voll ist
                JOptionPane.showMessageDialog(null, "Das Spiel ist vorbei! Das Sheet ist voll.", "Spiel beendet", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        gameTimer.start();
    }
}
