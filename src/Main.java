package src;

/**
 * Main File
 */
public class Main {

  public static void main(String[] args) {

    Sheet mysheet = new Sheet();
    mysheet.kniffel = 26;
    System.out.println(Sheet.sheet_to_string());
    Sheet.test("test");
    System.out.println("Hello World");
  }
}
