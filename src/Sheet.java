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
  int einser = 0;
  int zweier = 0;
  int dreier = 0;
  int vierer = 0;
  int fünfer = 0; // ü ist potenziell keine gute idee
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
  public Sheet() {
  }

  /**
   * Converte einen Feldint in einen String zum Printen
   *
   * @param feld ist das Feld welches in einen String umgewandelt wird
   * @return gibt einen String des Feldes zurück
   * @author Ricardo Güttner
   */
  private String fieldToSting(int feld) {
    if (feld >= 0) {
      return String.valueOf(feld);
    } else {
      return "0 (belegt)";
    }
  }

  /**
   * Diese Methode fasst alle Klassenfelder/Variablen unter einem String zusammen
   * 
   * @return gibt einen formatierten String mit allen Feldern wieder
   * @author Ricardo Güttner
   */
  public String sheet_to_string() {
    String returnstring = "einser: " + String.valueOf(einser) + "\n" +
        "zweier: " + fieldToSting(zweier) + "\n" +
        "dreier: " + fieldToSting(dreier) + "\n" +
        "vierer: " + fieldToSting(vierer) + "\n" +
        "fünfer: " + fieldToSting(fünfer) + "\n" +
        "sechser: " + fieldToSting(sechser) + "\n" +
        "\n" +
        "dreierpasch: " + fieldToSting(dreierpasch) + "\n" +
        "viererpasch: " + fieldToSting(viererpasch) + "\n" +
        "full_house: " + fieldToSting(full_house) + "\n" +
        "kleine_str: " + fieldToSting(kleine_str) + "\n" +
        "grosse_str: " + fieldToSting(grosse_str) + "\n" +
        "kniffel: " + fieldToSting(kniffel) + "\n" +
        "chance: " + fieldToSting(chance) + "\n";

    return returnstring;
  }

  /**
   * Setter für die Class Methods über index
   *
   * @param index Index für das Feld was angesprochen werden soll. Beginnt bei 0
   * @param value der Wert welcher gesetzt weden soll
   * @return returnt ein Boolean ob der Wert schon gesetzt wurde oder nicht
   * @author Ricardo Güttner
   */
  public boolean indexSet(int index, int value) {
    // Array der Felder
    int[] fields = {
        einser, zweier, dreier, vierer, fünfer, sechser,
        dreierpasch, viererpasch, full_house, kleine_str,
        grosse_str, kniffel, chance
    };

    // Überprüfen, ob der Index im gültigen Bereich liegt
    if (index < 0 || index >= fields.length) {
      System.out.println("Es ist ein unerwarteter Fehler beim Setzen der Variablen aufgetreten");
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
          fünfer = value;
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
   * @param würfel Array welcher zusammengefasst werden soll
   * @return gibt die Summe des int[] zurück
   * @author Ricardo Güttner
   */
  public static int sum(int[] würfel) {
    return Arrays.stream(würfel).sum();
  }

  /**
   * Sum wenn checker true ist ansonten wird -1 zurück gegeben
   *
   * @param würfel  Array welcher zusammengefasst werden soll
   * @param checker Boolean welche entscheidet ob die sum gebildet wird
   * @return gibt die Summe des int[] zurück
   * @author Ricardo Güttner
   */
  public static int sumIf(int[] würfel, boolean checker) {
    if (checker) {
      return sum(würfel);
    } else
      return -1;
  }

  /**
   * Auf value setzen wenn checker true ist ansonten wird -1 zurück gegeben
   *
   * @param value   Integer welche eingefügt werden soll
   * @param checker Boolean welche entscheidet ob die sum gebildet wird
   * @return gibt die Summe des Values
   * @author Ricardo Güttner
   */
  public static int setIf(int value, boolean checker) {
    if (checker) {
      return value;
    } else
      return -1;
  }

  /**
   * Wrapper für sumIf/setIf um bei Mehrfachem Kniffel 100 in alles einzutragen
   *
   * @param würfel   Array welcher zusammengefasst werden soll
   * @param prevalue Value ohne Wrapper
   * @return gibt 100 zurück wenn weiterer Kniffel gewürfelt wurde sonst prevalue
   * @author Ricardo Güttner
   */
  public int multipleKniffel(int[] würfel, int prevalue) {
    if (kniffel != 0 && kniffelcheck(würfel)) {
      return 100;
    } else
      return prevalue;
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
    if (resultnumber == 0) {
      return -1;
    } else {
      return resultnumber;
    }
  }

  /**
   * Zähle alle Würfel zusammen falls ein Pasch innerhalb der Würfel ist
   *
   * @param würfel ist der Array von integern welches die würfel representiert
   * @return gibt entweder -1 zurück wenn kein Pasch enthalten ist oder die Summe
   *         aller würfel
   * @author Ricardo Güttner
   */
  public static int paschcounter(int[] würfel) {
    int resultnumber = 0;
    if (pasch4checker(würfel) || pasch3checker(würfel)) {
      for (int number : würfel) {
        resultnumber += number;
      }
      return resultnumber; // returnt mit summe aller würfel
    } else {
      return -1; // returnt mit -1
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
    // Entfernen von Duplikaten und Sortieren des Arrays
    int[] uniqueDice = Arrays.stream(würfel).distinct().sorted().toArray();

    // Alle möglichen kleinen Straßen
    int[][] smallStraights = {
        { 1, 2, 3, 4 },
        { 2, 3, 4, 5 },
        { 3, 4, 5, 6 }
    };

    // Überprüfung, ob eine der kleinen Straßen im Array enthalten ist
    for (int[] straight : smallStraights) {
      if (containsSubArray(uniqueDice, straight)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Hilfsmethode, die überprüft, ob das große Array das kleine Array enthält
   *
   * @param array    der "größere Array"
   * @param subArray der "kleinere Array" welcher im größeren enthalten sein soll
   * @return true wenn subArray Teil von array sonst false
   * @author Ricardo Güttner
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

  /**
   * Checke ob jedes Feld belegt ist
   *
   * @return gibt einen Boolean zurück ob alle Werte belegt sind
   * @author Ricardo Güttner
   */
  public boolean isFull() {
    int[] fields = { einser, zweier, dreier, vierer, fünfer, sechser, dreierpasch, viererpasch, full_house, kleine_str,
        grosse_str, kniffel, chance };
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
   * @return gibt die Summe als Integer zurück
   * @author Ricardo Güttner
   */
  public int sheetSum() {
    int returnvalue = 0;
    int[] fields = { einser, zweier, dreier, vierer, fünfer, sechser, dreierpasch, viererpasch, full_house, kleine_str,
        grosse_str, kniffel, chance };
    for (int field : fields) {
      returnvalue += field;
    }
    return returnvalue;
  }
}
