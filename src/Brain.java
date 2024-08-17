package src;

/**
 * Brain
 */
public class Brain extends Sheet {

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
    // wird

  }

  public static int[] getSumvalues(int[] würfel) {
    // TODO vielleicht ist ein array aus (Valuename, Valuesum) besser
    int[] sumvalues = new int[13];

    for (int i = 0; i < 6; i++) {
      sumvalues[i] = nummercounter(würfel, i + 1);
    }

    sumvalues[6] = paschcounter(würfel);
    sumvalues[7] = paschcounter(würfel);
    sumvalues[8] = sumIf(würfel, full_house_check(würfel));
    sumvalues[9] = sumIf(würfel, klstrcheck(würfel));
    sumvalues[10] = sumIf(würfel, grstrcheck(würfel));
    sumvalues[11] = sumIf(würfel, kniffelcheck(würfel));
    sumvalues[12] = sum(würfel);

    return sumvalues;
  }
}
