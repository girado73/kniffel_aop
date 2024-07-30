package src;

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
}
