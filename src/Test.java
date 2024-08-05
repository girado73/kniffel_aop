package src;

public class Test {

  private static boolean kniffel_test() {

    int[] kniffel = { 6, 6, 6, 5, 6 };
    int[] nokniffel = { 1, 2, 3, 4, 5 };

    try {
      assert true == src.Sheet.kniffelcheck(kniffel) : "True Assertation failed";

      assert false == src.Sheet.kniffelcheck(nokniffel) : "False Assertation failed";

      return true;
    } catch (AssertionError e) {
      System.out.println("Kniffel Assertation Failed");
      System.out.println(e.getMessage());
      return false;
    }

  }

  private static boolean pasch3_test() {

    int[] pasch = { 6, 6, 6, 2, 3 };
    int[] nopasch = { 1, 2, 3, 4, 5 };

    try {
      assert true == src.Sheet.pasch3checker(pasch) : "True Assertation Failed";

      assert false == src.Sheet.pasch3checker(nopasch) : "False Assertation Failed";

      return true;
    } catch (AssertionError e) {
      System.out.println("Pasch3 Assertation Failed");
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

    System.out.println("kniffel_test: " + Test.kniffel_test());
    System.out.println("----");
    System.out.println("pasch3_test: " + Test.pasch3_test());
  }
}
