package src;

import java.util.Arrays;

/**
 * Sheet
 * This should represent the Sheet that you have
 * whilst playing Kniffel
 */
public class Sheet {

  // hier sind die Felder welche das Sheet hat.
  static int einser = 0;
  static int zweier = 0;
  static int dreier = 0;
  static int vierer = 0;
  static int fünfer = 0; // ü ist potenziell keine gute idee
  static int sechser = 0;

  static int dreierpasch = 0;
  static int viererpasch = 0;
  static int full_house = 0;
  static int kleine_str = 0;
  static int grosse_str = 0;
  static int kniffel = 0;
  static int chance = 0;

  /**
   * Diese Methode fasst alle Klassenfelder/Variablen unter einem String zusammen
   * 
   * @return gibt einen formatierten String mit allen Feldern wieder
   * @author Ricardo Güttner
   */
  public static String sheet_to_string() {
    String returnstring = "einser: " + String.valueOf(einser) + "\n" +
        "zweier: " + String.valueOf(zweier) + "\n" +
        "dreier: " + String.valueOf(dreier) + "\n" +
        "vierer: " + String.valueOf(vierer) + "\n" +
        "fünfer: " + String.valueOf(fünfer) + "\n" +
        "sechser: " + String.valueOf(sechser) + "\n" +
        "\n" +
        "dreierpasch: " + String.valueOf(dreierpasch) + "\n" +
        "viererpasch: " + String.valueOf(viererpasch) + "\n" +
        "full_house: " + String.valueOf(full_house) + "\n" +
        "kleine_str: " + String.valueOf(kleine_str) + "\n" +
        "grosse_str: " + String.valueOf(grosse_str) + "\n" +
        "kniffel: " + String.valueOf(kniffel) + "\n" +
        "chance: " + String.valueOf(chance) + "\n";

    return returnstring;
  }

  public static int nummercounter(int[] würfel, int nummer) {
    int resultnumber = 0;
    for (int number : würfel) {
      if (number == nummer) {
        resultnumber += number;
      }
    }
    return resultnumber;
  }

  /**
   * Methode welche guckt ob im Array ein Pasch mit vorkommen = anzahl ist
   * 
   * @param würfel ist der Array von integern welches die würfel representiert
   * @param anzahl ist die Anzahl der vorkommnisse der Paschzahl
   * @param target ist die Zahl nach welcher geguckt wird
   * @return gibt entweder true zurück wenn der Pasch da ist oder false wenn nicht
   * @author Ricardo Güttner
   */
  private static boolean paschchecker(int[] würfel, int anzahl, int target) {
    int counter = 0;
    for (int number : würfel) {
      if (number == target) {
        counter++;
      }
    }
    if (counter >= anzahl) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Zähle alle Würfel zusammen falls ein Pasch innerhalb der Würfel ist
   *
   * @param würfel  ist der Array von integern welches die würfel representiert
   * @param paschnr ist die angestrebte Paschzahl
   * @param target  ist die Zahl nach welcher geguckt wird
   * @return gibt entweder 0 zurück wenn kein Pasch enthalten ist oder die Summe
   *         aller würfel
   * @author Ricardo Güttner
   */
  public static int paschcounter(int[] würfel, int paschnr, int target) {
    int resultnumber = 0;
    if (paschchecker(würfel, paschnr, target)) {
      for (int number : würfel) {
        resultnumber += number;
      }
      return resultnumber; // returnt mit summe aller würfel
    } else {
      return resultnumber; // returnt mit 0
    }
  }

  public static boolean grstrcheck(int[] würfel) {
    Arrays.sort(würfel);
    int tmpnumber = würfel[0] - 1;
    for (int number : würfel) {
      if (number != tmpnumber + 1) {
        return false;
      } else {
        tmpnumber = number;
      }
    }
    return true;
  }

  public static boolean klstrcheck(int[] würfel) {
    Arrays.sort(würfel);
    int[] vers1 = würfel.; // TODO hier 2 versionen von würfel, einmal ohne den ersten wert
                          // und einmal ohne den letzten wert
    int[] vers2 = würfel;
  }
}
