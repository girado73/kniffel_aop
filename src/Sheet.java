package src;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Sheet
 * This should represent the Sheet that you have
 * whilst playing Kniffel
 */
public class Sheet {

  // hier sind die Felder welche das Sheet hat.
  int einser = 0;
  int zweier = 0;
  int dreier = 0;
  int vierer = 0;
  int fuenfer = 0; // ue ist potenziell keine gute idee
  int sechser = 0;

  int dreierpasch = 0;
  int viererpasch = 0;
  int full_house = 0;
  int kleine_str = 0;
  int grosse_str = 0;
  int kniffel = 0;
  int chance = 0;

  /**
   *
   */
  public Sheet() {}

  /**
   * Converte einen Feldint in einen String zum Printen
   *
   * @param feld ist das Feld welches in einen String umgewandelt wird
   * @return gibt einen String des Feldes zurueck
   * @author Ricardo Guettner
   */
  private String fieldToSting(int feld) {
    if (feld >= 0) {
      return String.valueOf(feld);
    } else {
      return "0 (belegt)";
    }
  }

  /**
   * Diese Methode fasst alle Klassenfelder/Variablen unter einem String
   * zusammen
   *
   * @return gibt einen formatierten String mit allen Feldern wieder
   * @author Ricardo Guettner
   */
  public String sheet_to_string() {
    String returnstring = "einser: " + String.valueOf(einser) + "\n"
                          + "zweier: " + fieldToSting(zweier) + "\n"
                          + "dreier: " + fieldToSting(dreier) + "\n"
                          + "vierer: " + fieldToSting(vierer) + "\n"
                          + "fuenfer: " + fieldToSting(fuenfer) + "\n"
                          + "sechser: " + fieldToSting(sechser) + "\n"
                          + "\n"
                          + "dreierpasch: " + fieldToSting(dreierpasch) + "\n"
                          + "viererpasch: " + fieldToSting(viererpasch) + "\n"
                          + "full_house: " + fieldToSting(full_house) + "\n"
                          + "kleine_str: " + fieldToSting(kleine_str) + "\n"
                          + "grosse_str: " + fieldToSting(grosse_str) + "\n"
                          + "kniffel: " + fieldToSting(kniffel) + "\n"
                          + "chance: " + fieldToSting(chance) + "\n";

    return returnstring;
  }

  /**
   * Setter fuer die Class Methods ueber index
   *
   * @param index Index fuer das Feld was angesprochen werden soll. Beginnt bei 0
   * @param value der Wert welcher gesetzt weden soll
   * @return returnt ein Boolean ob der Wert schon gesetzt wurde oder nicht
   * @author Ricardo Guettner
   */
  public boolean indexSet(int index, int value) {
    // Array der Felder
    int[] fields = {einser,     zweier,     dreier,      vierer,
                    fuenfer,     sechser,    dreierpasch, viererpasch,
                    full_house, kleine_str, grosse_str,  kniffel,
                    chance};

    // ueberpruefen, ob der Index im gueltigen Bereich liegt
    if (index < 0 || index >= fields.length) {
      System.out.println("Es ist ein unerwarteter Fehler beim Setzen der "
                         + "Variablen aufgetreten");
      return false;
    }

    // Wert nur setzen, wenn das Feld noch nicht belegt ist
    if (fields[index] == 0) {
      fields[index] = value;
      // Update the corresponding field
      switch (index) {
      case 0:
        einser = value;
        break;
      case 1:
        zweier = value;
        break;
      case 2:
        dreier = value;
        break;
      case 3:
        vierer = value;
        break;
      case 4:
        fuenfer = value;
        break;
      case 5:
        sechser = value;
        break;
      case 6:
        dreierpasch = value;
        break;
      case 7:
        viererpasch = value;
        break;
      case 8:
        full_house = value;
        break;
      case 9:
        kleine_str = value;
        break;
      case 10:
        grosse_str = value;
        break;
      case 11:
        kniffel = value;
        break;
      case 12:
        chance = value;
        break;
      }
      return true;
    }

    return false;
  }

  /**
   * Faltet einen int[] indem es die Summe aller Werte bildet
   *
   * @param wuerfel Array welcher zusammengefasst werden soll
   * @return gibt die Summe des int[] zurueck
   * @author Ricardo Guettner
   */
  public static int sum(int[] wuerfel) { return Arrays.stream(wuerfel).sum(); }

  /**
   * Sum wenn checker true ist ansonten wird -1 zurueck gegeben
   *
   * @param wuerfel  Array welcher zusammengefasst werden soll
   * @param checker Boolean welche entscheidet ob die sum gebildet wird
   * @return gibt die Summe des int[] zurueck
   * @author Ricardo Guettner
   */
  public static int sumIf(int[] wuerfel, boolean checker) {
    if (checker) {
      return sum(wuerfel);
    } else
      return -1;
  }

  /**
   * Auf value setzen wenn checker true ist ansonten wird -1 zurueck gegeben
   *
   * @param value   Integer welche eingefuegt werden soll
   * @param checker Boolean welche entscheidet ob die sum gebildet wird
   * @return gibt die Summe des Values
   * @author Ricardo Guettner
   */
  public static int setIf(int value, boolean checker) {
    if (checker) {
      return value;
    } else
      return -1;
  }

  /**
   * Wrapper fuer sumIf/setIf um bei Mehrfachem Kniffel 100 in alles einzutragen
   *
   * @param wuerfel   Array welcher zusammengefasst werden soll
   * @param prevalue Value ohne Wrapper
   * @return gibt 100 zurueck wenn weiterer Kniffel gewuerfelt wurde sonst
   *         prevalue
   * @author Ricardo Guettner
   */
  public int multipleKniffel(int[] wuerfel, int prevalue) {
    if (kniffel != 0 && kniffelcheck(wuerfel)) {
      return 100;
    } else
      return prevalue;
  }

  /**
   * Zähle das Vorkommen von target in wuerfel
   *
   * @param wuerfel ist der Array von integern welches die wuerfel representiert
   * @param nummer ist die nummer nach welcher gesucht wird
   * @return gibt das vorkommen von nummer zurueck
   * @author Ricardo Guettner
   */
  public static int nummercounter(int[] wuerfel, int nummer) {
    int resultnumber = 0;
    for (int number : wuerfel) {
      if (number == nummer) {
        resultnumber += number;
      }
    }
    if (resultnumber == 0) {
      return -1;
    } else {
      return resultnumber;
    }
  }

  /**
   * Zähle alle Wuerfel zusammen falls ein Pasch innerhalb der Wuerfel ist
   *
   * @param wuerfel ist der Array von integern welches die wuerfel representiert
   * @return gibt entweder -1 zurueck wenn kein Pasch enthalten ist oder die
   *         Summe
   *         aller wuerfel
   * @author Ricardo Guettner
   */
  public static int paschcounter(int[] wuerfel) {
    int resultnumber = 0;
    if (pasch4checker(wuerfel) || pasch3checker(wuerfel)) {
      for (int number : wuerfel) {
        resultnumber += number;
      }
      return resultnumber; // returnt mit summe aller wuerfel
    } else {
      return -1; // returnt mit -1
    }
  }

  /**
   * Checke ob ein 4erpasch in wuerfel ist
   *
   * @param wuerfel ist der Array von integern welches die wuerfel representiert
   * @return gibt einen Boolean zurueck ob ein 4erpasch im Array ist
   * @author Ricardo Guettner
   */
  public static boolean pasch4checker(int[] wuerfel) {
    Arrays.sort(wuerfel);
    int[] vers1 = new int[4]; // 2 vers. von wuerfel, drop [0], drop[-1]
    int[] vers2 = new int[4];
    int counter = 0;

    // erstellen der Listen
    for (int x : wuerfel) {
      if (counter == 0) {
        vers2[counter] = x;
      } else {
        if (counter == wuerfel.length - 1) {
          vers1[counter - 1] = x;
        } else {
          vers1[counter - 1] = x;
          vers2[counter] = x;
        }
      }
      counter++;
    }

    return kniffelcheck(vers1) ||
        kniffelcheck(vers2); // veroderung von kniffelcheck welches prueft ob
                             // alle zahlen gleich sind
  }

  /**
   * Checke ob ein Dreierpasch in einem Intarray ist
   *
   * @param wuerfel ist der Array von integern welches die wuerfel representiert
   * @return gibt einen Boolean zurueck ob ein 3erpasch im Array ist
   * @author Ricardo Guettner
   */
  public static boolean pasch3checker(int[] wuerfel) {
    Arrays.sort(wuerfel);

    int last_number = 0;
    int counter = 1;
    for (int x : wuerfel) {
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
   * @param wuerfel ist der Array von integern welches die wuerfel representiert
   * @return gibt einen Boolean zurueck ob ein Full House im Array ist
   * @author Ricardo Guettner
   */
  public static boolean full_house_check(int[] wuerfel) {
    Map<Integer, Integer> countMap = new HashMap<>();

    for (int num : wuerfel) {
      countMap.put(num, countMap.getOrDefault(num, 0) + 1);
    }
    boolean hasTwice = false;
    boolean hasThrice = false;

    // Check for exactly twice and thrice occurrences
    for (int count : countMap.values()) {
      if (count == 2) {
        if (hasTwice) {
          return false; // More than one number appears twice
        }
        hasTwice = true;
      } else if (count == 3) {
        if (hasThrice) {
          return false; // More than one number appears thrice
        }
        hasThrice = true;
      }
    }

    return hasTwice && hasThrice;
  }

  /**
   * Checke ob das Intarray eine große Straße ist
   *
   * @param wuerfel ist der Array von integern welches die wuerfel representiert
   * @return gibt einen Boolean zurueck ob eine große Straße im Array ist
   * @author Ricardo Guettner
   */
  public static boolean grstrcheck(int[] wuerfel) {
    Arrays.sort(wuerfel);
    int tmpnumber = wuerfel[0] - 1;
    for (int number : wuerfel) {
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
   * @param wuerfel ist der Array von integern welches die wuerfel representiert
   * @return gibt einen Boolean zurueck ob eine kleine Straße im Array ist
   * @author Ricardo Guettner
   */
  public static boolean klstrcheck(int[] wuerfel) {
    Arrays.sort(wuerfel);
    // Entfernen von Duplikaten und Sortieren des Arrays
    int[] uniqueDice = Arrays.stream(wuerfel).distinct().sorted().toArray();

    // Alle möglichen kleinen Straßen
    int[][] smallStraights = {{1, 2, 3, 4}, {2, 3, 4, 5}, {3, 4, 5, 6}};

    // ueberpruefung, ob eine der kleinen Straßen im Array enthalten ist
    for (int[] straight : smallStraights) {
      if (containsSubArray(uniqueDice, straight)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Hilfsmethode, die ueberprueft, ob das große Array das kleine Array enthält
   *
   * @param array    der "größere Array"
   * @param subArray der "kleinere Array" welcher im größeren enthalten sein
   *                 soll
   * @return true wenn subArray Teil von array sonst false
   * @author Ricardo Guettner
   */
  private static boolean containsSubArray(int[] array, int[] subArray) {
    for (int i = 0; i <= array.length - subArray.length; i++) {
      boolean found = true;
      for (int j = 0; j < subArray.length; j++) {
        if (array[i + j] != subArray[j]) {
          found = false;
          break;
        }
      }
      if (found) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checke ob ein Kniffel im Intarray vorliegt
   *
   * @param wuerfel ist der Array von integern welches die wuerfel representiert
   * @return gibt einen Boolean zurueck ob ein Kniffel im Array ist
   * @author Ricardo Guettner
   */
  public static boolean kniffelcheck(int[] wuerfel) {
    int checkval = wuerfel[0];

    for (int x : wuerfel) {
      if (x != checkval) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checke ob jedes Feld belegt ist
   *
   * @return gibt einen Boolean zurueck ob alle Werte belegt sind
   * @author Ricardo Guettner
   */
  public boolean isFull() {
    int[] fields = {einser,     zweier,     dreier,      vierer,
                    fuenfer,     sechser,    dreierpasch, viererpasch,
                    full_house, kleine_str, grosse_str,  kniffel,
                    chance};
    for (int field : fields) {
      if (field == 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * Bilde die Summe aus allen Feldern im Sheet
   *
   * @return gibt die Summe als Integer zurueck
   * @author Ricardo Guettner
   */
  public int sheetSum() {
    int returnvalue = 0;
    int[] fields = {einser,     zweier,     dreier,      vierer,
                    fuenfer,     sechser,    dreierpasch, viererpasch,
                    full_house, kleine_str, grosse_str,  kniffel,
                    chance};
    for (int field : fields) {
      returnvalue += field;
    }
    return returnvalue;
  }
}
