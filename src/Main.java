package src;

import javax.swing.*;
import java.util.Scanner;

public class Main {

  private static int numberOfPlayers = 0;

  public static void main(String[] args) {
    // Scanner für die Konsoleneingabe
    Scanner scanner = new Scanner(System.in);

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

    // Erstellen eines Arrays von Sheet-Instanzen
    Sheet[] sheets = new Sheet[numberOfPlayers];
    for (int i = 0; i < sheets.length; i++) {
      sheets[i] = new Sheet(); // oder eine passende Initialisierung für deine Anwendung
    }

    // Erstellen der View-Instanz und übergeben des Sheet-Arrays
    View view = new View(sheets, numberOfPlayers);

    // Starten des GUI
    SwingUtilities.invokeLater(() -> {
      view.setVisible(true);
    });

    // Spiel-Loop zur Überprüfung, ob das Sheet voll ist
    Timer gameTimer = new Timer(5000, e -> {
      boolean oneSheetFull = false;

      for (Sheet sheet : sheets) {
        if (sheet.isFull()) {
          oneSheetFull = true;
          break;
        }
      }

      if (oneSheetFull) {
        ((Timer) e.getSource()).stop(); // Stoppen des Timers, wenn alle Sheets voll sind
        int[] sheetSums = new int[numberOfPlayers];
        int maxValIndex = 0;
        int maxVal = 0;
        for (int i = 0; i < sheets.length; i++) {
          int sumOfSheet = sheets[i].sheetSum();
          if (sumOfSheet > maxVal) {
            maxVal = sumOfSheet;
            maxValIndex = i;
          }
          sheetSums[i] = sumOfSheet;
        }
        JOptionPane.showMessageDialog(null, "Das Spiel ist vorbei! Alle Sheets sind voll.", "Spiel beendet",
            JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null,
            "Spieler " + maxValIndex + " hat das Spiel mit " + maxVal + " Punkten gewonnen", "Spiel beendet",
            JOptionPane.INFORMATION_MESSAGE);

        for (int i = 0; i <= numberOfPlayers; i++) {
          System.out.println("Spieler: " + i);
          System.out.println(sheets[i].sheet_to_string());
          System.out.println("Sum: " + sheets[i].sheetSum());
          System.out.println("------------------------------");
        }

      }
    });
    gameTimer.start();
  }
}
