package src;

public class Test {

  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";

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

  /**
   * Ausführen mit java -ea Test.java
   */
  public static void main(String[] args) {
    ClassLoader loader = ClassLoader.getSystemClassLoader();
    loader.setDefaultAssertionStatus(true);

    kniffel_test();
    System.out.println("- - - - - - - - - ");
    pasch3_test();
  }
}
