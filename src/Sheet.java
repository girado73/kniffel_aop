package src;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

  /**
   * Zähle das Vorkommen von target in würfel
   *
   * @param würfel ist der Array von integern welches die würfel representiert
   * @param nummer ist die nummer nach welcher gesucht wird
   * @return gibt das vorkommen von nummer zurück
   * @author Ricardo Güttner
   */
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
   * Zähle alle Würfel zusammen falls ein Pasch innerhalb der Würfel ist
   *
   * @param würfel ist der Array von integern welches die würfel representiert
   * @param target ist die Zahl nach welcher geguckt wird
   * @return gibt entweder 0 zurück wenn kein Pasch enthalten ist oder die Summe
   *         aller würfel
   * @author Ricardo Güttner
   */
  public static int paschcounter(int[] würfel, int target) {
    int resultnumber = 0;
    if (pasch4checker(würfel) || pasch3checker(würfel)) {
      for (int number : würfel) {
        if (number == target) {
          resultnumber += number;
        }
      }
      return resultnumber; // returnt mit summe aller würfel
    } else {
      return resultnumber; // returnt mit 0
    }
  }

  /**
   * Checke ob ein 4erpasch in würfel ist
   * 
   * @param würfel ist der Array von integern welches die würfel representiert
   * @return gibt einen Boolean zurück ob ein 4erpasch im Array ist
   * @author Ricardo Güttner
   */
  public static boolean pasch4checker(int[] würfel) {
    Arrays.sort(würfel);
    int[] vers1 = new int[4]; // 2 vers. von würfel, drop [0], drop[-1]
    int[] vers2 = new int[4];
    int counter = 0;

    // erstellen der Listen
    for (int x : würfel) {
      if (counter == 0) {
        vers2[counter] = x;
      } else {
        if (counter == würfel.length - 1) {
          vers1[counter - 1] = x;
        } else {
          vers1[counter - 1] = x;
          vers2[counter] = x;
        }
      }
      counter++;
    }

    return kniffelcheck(vers1) || kniffelcheck(vers2); // veroderung von kniffelcheck welches prüft ob alle zahlen
                                                       // gleich sind
  }

  /**
   * Checke ob ein Dreierpasch in einem Intarray ist
   *
   * @param würfel ist der Array von integern welches die würfel representiert
   * @return gibt einen Boolean zurück ob ein 3erpasch im Array ist
   * @author Ricardo Güttner
   */
  public static boolean pasch3checker(int[] würfel) {
    Arrays.sort(würfel);

    int last_number = 0;
    int counter = 1;
    for (int x : würfel) {
      if (x == last_number) {
        counter += 1;
        if (counter >= 3) {
          return true;
        }
      } else {
        counter = 1;
      }
      last_number = x;
    }
    return false;
  }

  /**
   * Checke ob ein Intarray ein "full_house" ist
   *
   * @param würfel ist der Array von integern welches die würfel representiert
   * @return gibt einen Boolean zurück ob ein Full House im Array ist
   * @author Ricardo Güttner
   */
  public static boolean full_house_check(int[] würfel) {

    // set lässt nur unterschiedliche elemente zu
    Set<Integer> countset = new HashSet<>();
    for (int x : würfel) {
      countset.add(x);
    }

    // wenn set.size() == 2 dann muss es ein full_house sein
    if (countset.size() == 2)
      return true;
    else
      return false;

  }

  /**
   * Checke ob das Intarray eine große Straße ist
   *
   * @param würfel ist der Array von integern welches die würfel representiert
   * @return gibt einen Boolean zurück ob eine große Straße im Array ist
   * @author Ricardo Güttner
   */
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

  /**
   * Checke ob das Intarray eine kleine Straße ist
   *
   * @param würfel ist der Array von integern welches die würfel representiert
   * @return gibt einen Boolean zurück ob eine kleine Straße im Array ist
   * @author Ricardo Güttner
   */
  public static boolean klstrcheck(int[] würfel) {
    Arrays.sort(würfel);

    int[] vers1 = new int[4]; // 2 vers. von würfel, drop [0], drop[-1]
    int[] vers2 = new int[4];
    int counter = 0;

    // erstellen der Listen
    for (int x : würfel) {
      if (counter == 0) {
        vers2[counter] = x;
      } else {
        if (counter == würfel.length - 1) {
          vers1[counter - 1] = x;
        } else {
          vers1[counter - 1] = x;
          vers2[counter] = x;
        }
      }
      counter++;
    }

    // checken der Listen:
    if (vers1.length != vers2.length) {
      throw new ArrayIndexOutOfBoundsException("Listen sind nicht gleich lang");
    }

    // variablen welchen den letzten wert von versx[i] halten
    int tmp_vers1 = 0;
    int tmp_vers2 = 0;

    // variablen welche festhalten ob der array noch im rennen ist
    boolean vers1_ex = true;
    boolean vers2_ex = true;
    for (int i = 0; i < vers1.length; i++) { // ver1.length da sie gleich lang sein müssen
      if (vers1[i] <= tmp_vers1) {
        vers1_ex = false;
      }
      if (vers2[i] <= tmp_vers2) {
        vers2_ex = false;
      }

      tmp_vers1 = vers1[i];
      tmp_vers2 = vers2[i];
    }

    return vers1_ex || vers2_ex; // logisches oder denn es reicht wenn eine funktioniert
  }

  /**
   * Checke ob ein Kniffel im Intarray vorliegt
   *
   * @param würfel ist der Array von integern welches die würfel representiert
   * @return gibt einen Boolean zurück ob ein Kniffel im Array ist
   * @author Ricardo Güttner
   */
  public static boolean kniffelcheck(int[] würfel) {
    int checkval = würfel[0];

    for (int x : würfel) {
      if (x != checkval) {
        return false;
      }
    }
    return true;
  }

}
