package Lab1;

import java.util.HashMap;
import java.util.Map;

public class RCalc extends Calculations implements Operation {

    int convertRomanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        s = s.replace("IV","IIII");
        s = s.replace("IX","VIIII");
        s = s.replace("XL","XXXX");
        s = s.replace("XC","LXXXX");
        s = s.replace("CD","CCCC");
        s = s.replace("CM","DCCCC");
        int n = 0;
        for (int i = 0; i < s.length(); i++) { n = n + (map.get(s.charAt(i))); }
        return n;
    }

    String intToRoman(int num) {
        System.out.println("Integer: " + num);
        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romanLetters = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                roman.append(romanLetters[i]);
            }
        }
        return roman.toString();
    }

    @Override
    String add(String s) {
        int a = convertRomanToInt(s.substring(0, s.indexOf("+")));
        int b = convertRomanToInt(s.substring(s.indexOf("+")));
        return intToRoman(a + b);
    }

    @Override
    String sub(String s) {
        int a = convertRomanToInt(s.substring(0, s.indexOf("-")));
        int b = convertRomanToInt(s.substring(s.indexOf("-")));
        return (a - b) < 0 ? "Non-positive result" : intToRoman(a - b);
    }

    @Override
    String mul(String s) {
        int a = convertRomanToInt(s.substring(0, s.indexOf("*")));
        int b = convertRomanToInt(s.substring(s.indexOf("*") + 1));
        return intToRoman(a * b);
    }

    @Override
    String div(String s) {
        int a = convertRomanToInt(s.substring(0, s.indexOf("/")));
        int b = convertRomanToInt(s.substring(s.indexOf("/")));
        return intToRoman(a / b);
    }
}
