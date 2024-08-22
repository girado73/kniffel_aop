package src;

import java.util.Arrays;

/**
 * Dice
 * This class should represent the Dices
 */
public class Dice {
  private static final int Seiten = 6;

  /**
   * Erstelle eine Zufallsint zwischen 1 und 6
   */
  public int roll() {
    return (int) (Math.random() * Seiten) + 1;
  }

  /**
   * Erstelle einen Array von Zufallsints mit einer länge von numberOfDice
   */
  public int[] rollMultiple(int numberOfDice) {
    int[] results = new int[numberOfDice];
    for (int i = 0; i < numberOfDice; i++) {
      results[i] = roll();
    }
    Arrays.sort(results);
    return results;
  }

  public static void main(String[] args) {
    Dice dice = new Dice();

    int[] results = dice.rollMultiple(5);

    System.out.println("Das Ergebnis der Würfel ist: " + Arrays.toString(results));
  }
}
