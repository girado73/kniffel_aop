package src;

import java.util.Arrays;

/**
 * Brain
 */
public class Brain extends Sheet {

  /**
   * Enthält die Feldanzahl für das Sheet
   */
  private static final int fieldArrayLen = 13;

  private static final String[] options = {
      "Einsen", "Zweien", "Dreien", "Vieren", "Fünfen", "Sechsen",
      "Dreierpasch", "Viererpasch", "Full House", "Kleine Straße",
      "Große Straße", "Kniffel", "Chance"
  };

  /**
   * Autoexecute all Funktions to instantly give proposal
   */
  public Brain(int[] würfel) {
    // printSumValues(würfel);
    // giveProp(würfel);
  }

  /**
   * Get a proposal on what option to take based on the dice you have
   */
  // TODO hier vielleicht kein void
  // dann können wir weiter damit arbeiten
  public static void giveProp(int[] würfel) {
    int[] sumvalues = getSumvalues(würfel);
    int bestIndex = -1;
    int maxValue = 0;

    // Check for the best option, ignoring the last field (assumed to be "chance")
    for (int i = 0; i < fieldArrayLen - 1; i++) {
      // TODO hier muss aus dem Sheet entnommen werden, welche felder noch nicht
      // vergeben sind
      if (sumvalues[i] >= maxValue) {
        bestIndex = i;
        maxValue = sumvalues[i];
      }
    }

    if (bestIndex >= 0 && bestIndex < options.length) {
      System.out.println(options[bestIndex] + " mit dem Wert: " + maxValue);
    } else {
      System.out.println("Something went wrong");
    }
  }

  /**
   * Wendet alle Checker für die Felder an und fasst es in einem int[] zusammen
   */
  public static int[] getSumvalues(int[] würfel) {
    int[] sumvalues = new int[13];

    for (int i = 0; i < 6; i++) {
      sumvalues[i] = nummercounter(würfel, i + 1);
    }

    sumvalues[6] = paschcounter(würfel);
    sumvalues[7] = sumIf(würfel, pasch4checker(würfel));
    sumvalues[8] = setIf(25, full_house_check(würfel));
    sumvalues[9] = setIf(30, klstrcheck(würfel));
    sumvalues[10] = setIf(40, grstrcheck(würfel));
    sumvalues[11] = setIf(50, kniffelcheck(würfel));
    sumvalues[12] = sum(würfel);

    return sumvalues;
  }

  /**
   * Printfunktion für die bessere Anschauung der Felder
   */
  public static void printSumValues(int[] würfel) {
    int[] sumvalues = getSumvalues(würfel);

    System.out.println("Würfel: " + Arrays.toString(würfel));

    for (int i = 0; i < fieldArrayLen; i++) {
      System.out.println(options[i] + ": " + sumvalues[i]);
    }
  }

  public static void main(String[] args) {
    int[] würfel = { 1, 2, 2, 2, 4 };
    printSumValues(würfel);
    giveProp(würfel);
  }
}
