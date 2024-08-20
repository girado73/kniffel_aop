package src;

import java.util.Arrays;

/**
 * Brain
 */
public class Brain extends Sheet {

  private static final int fieldArrayLen = 13;

  /**
   * Autoexecute all Funktions to instantly give proposal
   */
  public Brain(int[] würfel) {
    printSumValues(würfel);
    giveProp(würfel);
  }

  /**
   * Get a proposal on what option to take based on the dice you have
   */
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

    // Output result based on the bestIndex
    String[] options = {
        "Einsen", "Zweien", "Dreien", "Vieren", "Fünfen", "Sechsen",
        "Dreierpasch", "Viererpasch", "Full House", "Kleine Straße",
        "Große Straße", "Kniffel"
    };

    if (bestIndex >= 0 && bestIndex < options.length) {
      System.out.println(options[bestIndex] + " mit dem Wert: " + maxValue);
    } else {
      System.out.println("Something went wrong");
    }
  }

  public static int[] getSumvalues(int[] würfel) {
    int[] sumvalues = new int[13];

    for (int i = 0; i < 6; i++) {
      sumvalues[i] = nummercounter(würfel, i + 1);
    }

    sumvalues[6] = paschcounter(würfel);
    sumvalues[7] = sumIf(würfel, pasch4checker(würfel));
    sumvalues[8] = sumIf(würfel, full_house_check(würfel));
    sumvalues[9] = sumIf(würfel, klstrcheck(würfel));
    sumvalues[10] = sumIf(würfel, grstrcheck(würfel));
    sumvalues[11] = sumIf(würfel, kniffelcheck(würfel));
    sumvalues[12] = sum(würfel);

    return sumvalues;
  }

  public static void printSumValues(int[] würfel) {
    int[] sumvalues = getSumvalues(würfel);

    System.out.println("Würfel: " + Arrays.toString(würfel));

    // Output result based on the bestIndex
    String[] options = {
        "Einsen", "Zweien", "Dreien", "Vieren", "Fünfen", "Sechsen",
        "Dreierpasch", "Viererpasch", "Full House", "Kleine Straße",
        "Große Straße", "Kniffel", "Chance"
    };

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
