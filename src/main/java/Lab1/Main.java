package Lab1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String in = scanner.nextLine();
        scanner.close();
        String out = null;

        ACalc a = new ACalc();
        RCalc r = new RCalc();

        boolean ara = NumId.isArabic(in);
        boolean rom = NumId.isRoman(in);

        boolean mCont = in.contains("*");
        boolean aCont = in.contains("+");
        boolean sCont = in.contains("-");
        boolean dCont = in.contains("/");

        int m1 = 0, a1 = 0, s1 = 0, d1 = 0, m2 = 0, a2 = 0, s2 = 0, d2 = 0;

        if (ara) {
            if (mCont) {
                m1 = Integer.parseInt(in.substring(0, in.indexOf("*")));
                m2 = Integer.parseInt(in.substring(in.indexOf("*") + 1));
            }
            if (aCont) {
                a1 = Integer.parseInt(in.substring(0, in.indexOf("+")));
                a2 = Integer.parseInt(in.substring(in.indexOf("+")));
            }
            if (sCont) {
                s1 = Integer.parseInt(in.substring(0, in.indexOf("-")));
                s2 = Integer.parseInt(in.substring(in.indexOf("-")));
            }
            if (dCont) {
                d1 = Integer.parseInt(in.substring(0, in.indexOf("/")));
                d2 = Integer.parseInt(in.substring(in.indexOf("/") + 1));
            }
        } else if (rom) {
            if (mCont) {
                m1 = r.romanToInt(in.substring(0, in.indexOf("*")));
                m2 = r.romanToInt(in.substring(in.indexOf("*") + 1));
            }
            if (aCont) {
                a1 = r.romanToInt(in.substring(0, in.indexOf("+")));
                a2 = r.romanToInt(in.substring(in.indexOf("+") + 1));
            }
            if (sCont) {
                s1 = r.romanToInt(in.substring(0, in.indexOf("-")));
                s2 = r.romanToInt(in.substring(in.indexOf("-") + 1));
            }
            if (dCont) {
                d1 = r.romanToInt(in.substring(0, in.indexOf("/")));
                d2 = r.romanToInt(in.substring(in.indexOf("/") + 1));
            }
        }

        if (a1 <= 10 && a2 <= 10 && m1 <= 10 && m2 <= 10 && s1 <= 10 && s2 <= 10 && d1 <= 10 && d2 <= 10) {
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

        } else System.out.println("Big numbers");
    }
}
