package src;

import java.util.Arrays;

/**
 * Brain-Klasse zur Auswertung der Sheet-Checker Methoden
 *
 */
public class Brain extends Sheet {

  /**
   * Enthält die Feldanzahl für das Sheet
   *
   * @author Ricardo Güttner
   */
  private static final int fieldArrayLen = 13;

  private static final String[] options = {
      "Einsen", "Zweien", "Dreien", "Vieren", "Fünfen", "Sechsen",
      "Dreierpasch", "Viererpasch", "Full House", "Kleine Straße",
      "Große Straße", "Kniffel", "Chance"
  };

  /**
   * Leerer Constructor da nicht nötig
   */
  public Brain(int[] würfel) {
  }

  /**
   * Get a proposal on what option to take based on the dice you have
   * 
   * @param würfel  int[] welcher ausgewertet werden soll
   * @param mysheet Sheet auf welches giveProp angewendet werden soll
   * @author Ricardo Güttner
   */
  public static void giveProp(int[] würfel, Sheet mysheet) {
    int[] sumvalues = getSumvalues(würfel, mysheet);
    int bestIndex = -1;
    int maxValue = 0;

    // Check for the best option, ignoring the last field (assumed to be "chance")
    for (int i = 0; i < fieldArrayLen - 1; i++) {
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
   *
   * @param würfel  int[] welcher ausgewertet werden soll
   * @param mysheet Sheet auf welches getSumvalues angewendet werden soll
   * @author Ricardo Güttner
   */
  public static int[] getSumvalues(int[] würfel, Sheet mysheet) {
    int[] sumvalues = new int[13];

    for (int i = 0; i < 6; i++) {
      sumvalues[i] = mysheet.multipleKniffel(würfel, nummercounter(würfel, i + 1));
    }

    sumvalues[6] = mysheet.multipleKniffel(würfel, paschcounter(würfel));
    sumvalues[7] = mysheet.multipleKniffel(würfel, sumIf(würfel, pasch4checker(würfel)));
    sumvalues[8] = mysheet.multipleKniffel(würfel, setIf(25, full_house_check(würfel)));
    sumvalues[9] = mysheet.multipleKniffel(würfel, setIf(30, klstrcheck(würfel)));
    sumvalues[10] = mysheet.multipleKniffel(würfel, setIf(40, grstrcheck(würfel)));
    sumvalues[11] = setIf(50, kniffelcheck(würfel));
    sumvalues[12] = mysheet.multipleKniffel(würfel, sum(würfel));

    return sumvalues;
  }

  /**
   * Printfunktion für die bessere Anschauung der Felder
   * 
   * @param würfel  int[] welcher ausgewertet und geprintet werden soll
   * @param mysheet Sheet auf welches printSumValues angewendet werden soll
   * @author Ricardo Güttner
   */
  public static void printSumValues(int[] würfel, Sheet mysheet) {
    int[] sumvalues = getSumvalues(würfel, mysheet);

    System.out.println("Würfel: " + Arrays.toString(würfel));

    for (int i = 0; i < fieldArrayLen; i++) {
      System.out.println(options[i] + ": " + sumvalues[i]);
    }
  }

  public static void main(String[] args) {
    int[] würfel = { 1, 2, 2, 2, 4 };
    printSumValues(würfel, new Sheet());
    giveProp(würfel, new Sheet());
  }
}
