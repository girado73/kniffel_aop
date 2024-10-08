package src;

import java.util.Arrays;

/**
 * Dice
 * This class should represent the Dices
 */
public class Dice {
  private static final int SEITEN = 6;

  /**
   * Erstelle eine Zufallsint zwischen 1 und 6
   */
  public int roll() {
    return (int) (Math.random() * SEITEN) + 1;
  }

  /**
   * Erstelle einen Array von Zufallsints mit einer Länge von numberOfDice
   */
  public int[] rollMultiple(int numberOfDice) {
    int[] results = new int[numberOfDice];
    for (int i = 0; i < numberOfDice; i++) {
      results[i] = roll();
    }
    Arrays.sort(results);
    return results;
  }

  /**
   * Erstelle einen Array von Zufallsints, wobei nur der Wuerfel an der gegebenen
   * Position neu geworfen wird.
   */
  public int[] rollSpecific(int[] diceStates, int indexToReroll) {
    int[] results = Arrays.copyOf(diceStates, diceStates.length);
    results[indexToReroll] = roll();
    return results;
  }

}
