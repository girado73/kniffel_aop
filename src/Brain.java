package src;

import java.util.Arrays;

/**
 * Brain-Klasse zur Auswertung der Sheet-Checker Methoden
 *
 */
public class Brain extends Sheet {

  /**
   * Enthält die Feldanzahl fuer das Sheet
   *
   * @author Ricardo Guettner
   */
  private static final int fieldArrayLen = 13;

  private static final String[] options = {
      "Einsen", "Zweien", "Dreien", "Vieren", "Fuenfen", "Sechsen",
      "Dreierpasch", "Viererpasch", "Full House", "Kleine Straße",
      "Große Straße", "Kniffel", "Chance"
  };

  /**
   * Leerer Constructor da nicht nötig
   */
  public Brain(int[] wuerfel) {
  }

  /**
   * Get a proposal on what option to take based on the dice you have
   * 
   * @param wuerfel  int[] welcher ausgewertet werden soll
   * @param mysheet Sheet auf welches giveProp angewendet werden soll
   * @author Ricardo Guettner
   */
  public static void giveProp(int[] wuerfel, Sheet mysheet) {
    int[] sumvalues = getSumvalues(wuerfel, mysheet);
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
   * Wendet alle Checker fuer die Felder an und fasst es in einem int[] zusammen
   *
   * @param wuerfel  int[] welcher ausgewertet werden soll
   * @param mysheet Sheet auf welches getSumvalues angewendet werden soll
   * @author Ricardo Guettner
   */
  public static int[] getSumvalues(int[] wuerfel, Sheet mysheet) {
    int[] sumvalues = new int[13];

    for (int i = 0; i < 6; i++) {
      sumvalues[i] = mysheet.multipleKniffel(wuerfel, nummercounter(wuerfel, i + 1));
    }

    sumvalues[6] = mysheet.multipleKniffel(wuerfel, paschcounter(wuerfel));
    sumvalues[7] = mysheet.multipleKniffel(wuerfel, sumIf(wuerfel, pasch4checker(wuerfel)));
    sumvalues[8] = mysheet.multipleKniffel(wuerfel, setIf(25, full_house_check(wuerfel)));
    sumvalues[9] = mysheet.multipleKniffel(wuerfel, setIf(30, klstrcheck(wuerfel)));
    sumvalues[10] = mysheet.multipleKniffel(wuerfel, setIf(40, grstrcheck(wuerfel)));
    sumvalues[11] = setIf(50, kniffelcheck(wuerfel));
    sumvalues[12] = mysheet.multipleKniffel(wuerfel, sum(wuerfel));

    return sumvalues;
  }

  /**
   * Printfunktion fuer die bessere Anschauung der Felder
   * 
   * @param wuerfel  int[] welcher ausgewertet und geprintet werden soll
   * @param mysheet Sheet auf welches printSumValues angewendet werden soll
   * @author Ricardo Guettner
   */
  public static void printSumValues(int[] wuerfel, Sheet mysheet) {
    int[] sumvalues = getSumvalues(wuerfel, mysheet);

    System.out.println("Wuerfel: " + Arrays.toString(wuerfel));

    for (int i = 0; i < fieldArrayLen; i++) {
      System.out.println(options[i] + ": " + sumvalues[i]);
    }
  }

}
