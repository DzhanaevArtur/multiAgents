package Lab1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String in = scanner.nextLine();
        boolean ara = NumId.isArabic(in);
        boolean rom = NumId.isRoman(in);

        ACalc a = new ACalc();
        RCalc r = new RCalc();
        boolean mCont = in.contains("*");
        boolean aCont = in.contains("+");
        boolean sCont = in.contains("-");
        boolean dCont = in.contains("/");
        String out = null;

        if (ara) {
            if (rom) {
                out = "Invalid number format";
            } else {
                if (mCont) {
                    out = String.valueOf(a.mul(in));
                } else if (aCont) {
                    out = String.valueOf(a.add(in));
                } else if (sCont) {
                    out = String.valueOf(a.sub(in));
                } else if (dCont) {
                    out = String.valueOf(a.div(in));
                }
            }
        } else if (rom) {
            if (mCont) {
                out = String.valueOf(r.mul(in));
            } else if (aCont) {
                out = String.valueOf(r.add(in));
            } else if (sCont) {
                out = String.valueOf(r.sub(in));
            } else if (dCont) {
                out = String.valueOf(r.div(in));
            }
        }

        System.out.println(out);
        scanner.close();
    }
}
