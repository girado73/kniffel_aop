package src;

public class Test {

  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";

  private static void errorPrint(String msg) {
    System.out.println(ANSI_RED + msg + ANSI_RESET);
  }

  private static void successPrint(String msg) {
    System.out.println(ANSI_GREEN + msg + ANSI_RESET);
  }

  private static boolean sum_test() {
    System.out.println("Sheet.sum Test: ");
    int[] würfel = { 1, 2, 3, 4, 5 };

    try {
      assert 15 == src.Sheet.sum(würfel) : "True Assertation Failed";
      successPrint("Sum Test Succeeded");
      return true;
    } catch (AssertionError e) {
      errorPrint("Sum Test failed");
      System.out.println(e.getMessage());
      return false;
    }
  };

  private static boolean sumIf_test() {
    System.out.println("Sheet.sumIf Test: ");
    int[] würfel = { 1, 2, 3, 4, 5 };

    try {
      assert 15 == src.Sheet.sumIf(würfel, true) : "True Assertation Failed";
      assert 0 == src.Sheet.sumIf(würfel, false) : "False Assertation Failed";
      successPrint("Sum Test Succeeded");
      return true;
    } catch (AssertionError e) {
      errorPrint("Sum Test failed");
      System.out.println(e.getMessage());
      return false;
    }
  };

  private static boolean nummercounter_test() {
    System.out.println("Nummercounter Test:");
    int[] würfel = { 1, 2, 4, 4, 4 };
    int[] würfel2 = { 1, 3, 3, 3, 3 };

    try {
      assert 12 == src.Sheet.nummercounter(würfel, 4) : "True Assertation Failed";
      assert 12 == src.Sheet.nummercounter(würfel2, 3) : "True Assertation2 Failed";
      successPrint("Nummercounter Test Succeeded");
      return true;
    } catch (AssertionError e) {
      errorPrint("Nummercounter Test failed");
      System.out.println(e.getMessage());
      return false;
    }
  }

  private static boolean pasch4checker_test() {
    System.out.println("Pasch4checker Test:");
    int[] würfelwrong = { 1, 2, 4, 4, 4 };
    int[] würfeltrue = { 1, 3, 3, 3, 3 };

    try {
      assert true == src.Sheet.pasch4checker(würfeltrue) : "True Assertation Failed";
      assert false == src.Sheet.pasch4checker(würfelwrong) : "False Assertation Failed";
      successPrint("Pasch4checker Test Succeeded");
      return true;
    } catch (AssertionError e) {
      errorPrint("pasch4checker Test failed");
      System.out.println(e.getMessage());
      return false;
    }
  }

  private static boolean full_house_check_test() {
    System.out.println("full_house_check Test:");
    int[] würfelwrong = { 1, 2, 4, 4, 4 };
    int[] würfeltrue = { 2, 2, 3, 3, 3 };

    try {
      assert true == src.Sheet.full_house_check(würfeltrue) : "True Assertation Failed";
      assert false == src.Sheet.full_house_check(würfelwrong) : "False Assertation Failed";
      successPrint("full_house_check Test Succeeded");
      return true;
    } catch (AssertionError e) {
      errorPrint("full_house_check Test failed");
      System.out.println(e.getMessage());
      return false;
    }
  }

  private static boolean klstrcheck_test() {
    System.out.println("klstrcheck Test:");
    int[] würfelwrong = { 1, 2, 4, 4, 4 };
    int[] würfeltrue = { 2, 1, 2, 3, 4 };

    try {
      assert true == src.Sheet.klstrcheck(würfeltrue) : "True Assertation Failed";
      assert false == src.Sheet.klstrcheck(würfelwrong) : "False Assertation Failed";
      successPrint("klstrcheck Test Succeeded");
      return true;
    } catch (AssertionError e) {
      errorPrint("klstrcheck Test failed");
      System.out.println(e.getMessage());
      return false;
    }
  }

  private static boolean grstrcheck_test() {
    System.out.println("grstrcheck Test:");
    int[] würfelwrong = { 1, 2, 4, 4, 4 };
    int[] würfeltrue = { 2, 3, 4, 5, 6 };

    try {
      assert true == src.Sheet.grstrcheck(würfeltrue) : "True Assertation Failed";
      assert false == src.Sheet.grstrcheck(würfelwrong) : "False Assertation Failed";
      successPrint("grstrcheck Test Succeeded");
      return true;
    } catch (AssertionError e) {
      errorPrint("grstrcheck Test failed");
      System.out.println(e.getMessage());
      return false;
    }
  }

  private static boolean kniffel_test() {
    System.out.println("Sheet.kniffelcheck Test:");
    int[] kniffel = { 6, 6, 6, 6, 6 };
    int[] nokniffel = { 1, 2, 3, 4, 5 };

    try {
      assert true == src.Sheet.kniffelcheck(kniffel) : "True Assertation failed";

      assert false == src.Sheet.kniffelcheck(nokniffel) : "False Assertation failed";

      System.out.println(ANSI_GREEN + "Kniffel Assertation Completed" + ANSI_RESET);
      return true;
    } catch (AssertionError e) {
      System.out.println(ANSI_RED + "Kniffel Assertation Failed" + ANSI_RESET);
      System.out.println(e.getMessage());
      return false;
    }

  }

  private static boolean pasch3_test() {
    System.out.println("Sheet.pasch3checker Test: ");
    int[] pasch = { 5, 5, 5, 2, 6 };
    int[] nopasch = { 1, 2, 3, 4, 5 };

    try {
      assert true == src.Sheet.pasch3checker(pasch) : "True Assertation Failed";

      assert false == src.Sheet.pasch3checker(nopasch) : "False Assertation Failed";

      System.out.println(ANSI_GREEN + "Pasch3 Assertation Completed" + ANSI_RESET);
      return true;
    } catch (AssertionError e) {
      System.out.println(ANSI_RED + "Pasch3 Assertation Failed" + ANSI_RESET);
      System.out.println(e.getMessage());
      return false;
    }
  }

  // ----------------------------------------------------------
  // Brain.java Tests

  /**
   * Teste die Brain.getSumvalues MethodExitEvent
   */
  private static boolean getSumvalues_test() {
    System.out.println("Brain.getSumvalues Test: ");
    int[] würfel = { 1, 2, 2, 2, 4 };
    int[] sumvalues = src.Brain.getSumvalues(würfel, new Sheet());
    try {
      assert 6 == sumvalues[1] : "Count Values 2";
      assert 1 == sumvalues[0] : "Count Values 1";
      assert 0 == sumvalues[5] : "Count Values 6";

      assert 11 == sumvalues[6] : "Paschcheck + sum";
      assert 11 == sumvalues[7] : "Paschcheck2 + sum";

      assert 0 == sumvalues[11] : "Kniffelcheck";
      assert 11 == sumvalues[12] : "Chance(sum)";

      System.out.println(ANSI_GREEN + "getSumvalues Assertation Succeeded" + ANSI_RESET);
      return true;
    } catch (AssertionError e) {
      System.out.println(ANSI_RED + "getSumvalues Assertation Failed" + ANSI_RESET);
      System.out.println(e.getMessage());
      return false;
    }
  }

  // ----------------------------------------------------------
  // executing Tests
  /**
   * Ausführen mit java -ea Test.java
   */
  public static void main(String[] args) {
    ClassLoader loader = ClassLoader.getSystemClassLoader();
    loader.setDefaultAssertionStatus(true);

    kniffel_test();
    System.out.println("- - - - - - - - - ");
    pasch3_test();
    System.out.println("- - - - - - - - - ");
    pasch4checker_test();
    System.out.println("- - - - - - - - - ");
    full_house_check_test();
    System.out.println("- - - - - - - - - ");
    klstrcheck_test();
    System.out.println("- - - - - - - - - ");
    grstrcheck_test();
    System.out.println("- - - - - - - - - ");
    nummercounter_test();
    System.out.println("- - - - - - - - - ");
    sum_test();
    System.out.println("- - - - - - - - - ");
    sumIf_test();
    System.out.println("- - - - - - - - - ");
    getSumvalues_test();
  }
}
