package Lab1;

public class NumId {
    static boolean isArabic(String s) { return s.indexOf('I') == -1 && s.indexOf('V') == -1 && s.indexOf('X') == -1; }
    static boolean isRoman(String s) { return s.indexOf('I') != -1 || s.indexOf('V') != -1 || s.indexOf('X') != -1; }
}
