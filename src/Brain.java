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
  public Brain() {

  }

  /**
   * Get a proposal on what option to take based on the dice you have
   */

  public static void giveProp(int[] würfel) {
    // Nehmen wir an das wir nach nur nach den sum werten gehen
    // Hier werden alle funktionen hintereinander auf würfel angewandt
    // Danach werden die vorgeschlagen wo die größten werte bei rauskommen
    // Dabei heißt 0 das es mit 0 eingetragen werden würde wenn die option gewählt

    int[] sumvalues = getSumvalues(würfel);
    int[] indexValue = { -1, 0 };

    // wir machen hier fieldArrayLen - 1 da wir chance nicht mitnehmen
    // wollen da das immer maximal/sum ist
    for (int i = 0; i < fieldArrayLen - 1; i++) {
      // TODO hier muss noch aus dem sheet entnommen werden ob wert schon vergeben ist
      // je nachdem wird sumvalues[i] verwertet oder nicht
      if (sumvalues[i] >= indexValue[1]) { // TODO wir nehmen hier >= da der schwierigkeitsgrad nach unten hin steigt,
                                           // ist das ok???
        indexValue[0] = i; // Index setzen damit wir das Feld kennen
        indexValue[1] = sumvalues[i]; // Value setzen damit wir den Wert kennen
      }
    }
    // TODO ich seh hier bis jetzt keine andere möglichkeit als dieses Ungetüm
    switch (indexValue[0]) {
      case -1:
        System.out.println("Something went wrong");
        break;
      case 0:
        System.out.println("Einsen mit dem Wert: " + indexValue[1]);
        break;
      case 1:
        System.out.println("Zweien mit dem Wert: " + indexValue[1]);
        break;
      case 2:
        System.out.println("Dreien mit dem Wert: " + indexValue[1]);
        break;
      case 3:
        System.out.println("Vieren mit dem Wert: " + indexValue[1]);
        break;
      case 4:
        System.out.println("Fünfen mit dem Wert: " + indexValue[1]);
        break;
      case 5:
        System.out.println("Sechsen mit dem Wert: " + indexValue[1]);
        break;
      case 6:
        System.out.println("Dreierpasch mit dem Wert: " + indexValue[1]);
        break;
      case 7:
        System.out.println("Viererpasch mit dem Wert: " + indexValue[1]);
        break;
      case 8:
        System.out.println("Full House mit dem Wert: " + indexValue[1]);
        break;
      case 9:
        System.out.println("Kleine Straße mit dem Wert: " + indexValue[1]);
        break;
      case 10:
        System.out.println("Große Straße mit dem Wert: " + indexValue[1]);
        break;
      case 11:
        System.out.println("Kniffel mit dem Wert: " + indexValue[1]);
        break;
      default:
        System.out.println("We got somehow into default case");
        break;
    }
  }

  public static int[] getSumvalues(int[] würfel) {
    // TODO vielleicht ist ein array aus (Valuename, Valuesum) besser
    // Es gibt keine tupel o.O
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

    System.err.println("Würfel: " + Arrays.toString(würfel));

    System.out.println("Einser: " + sumvalues[0]);
    System.out.println("Zweier: " + sumvalues[1]);
    System.out.println("Dreier: " + sumvalues[2]);
    System.out.println("Vierer: " + sumvalues[3]);
    System.out.println("Fünfer: " + sumvalues[4]);
    System.out.println("Sechser: " + sumvalues[5]);
    System.out.println("Dreierpasch: " + sumvalues[6]);
    System.out.println("Viererpasch: " + sumvalues[7]);
    System.out.println("Full House: " + sumvalues[8]);
    System.out.println("Kleine Straße: " + sumvalues[9]);
    System.out.println("Große Straße: " + sumvalues[10]);
    System.out.println("Kniffel: " + sumvalues[11]);
    System.out.println("Chance: " + sumvalues[12]);

  }

  public static void main(String[] args) {
    int[] würfel = { 5, 2, 3, 4, 1 };
    printSumValues(würfel);
    giveProp(würfel);
  }
}
