package Lab1;

public class ACalc extends Calculations implements Operation {

    @Override
    String add(String s) {
        int a = Integer.parseInt(s.substring(0, s.indexOf("+")));
        int b = Integer.parseInt(s.substring(s.indexOf("+")));
        return String.valueOf(a + b);
    }

    @Override
    String sub(String s) {
        int a = Integer.parseInt(s.substring(0, s.indexOf("-")));
        int b = Integer.parseInt(s.substring(s.indexOf("-") + 1));
        int res = a - b;
        return String.valueOf(res);
    }

    @Override
    String mul(String s) {
        int a = Integer.parseInt(s.substring(0, s.indexOf("*")));
        int b = Integer.parseInt(s.substring(s.indexOf("*") + 1));
        return String.valueOf(a * b);
    }

    @Override
    String div(String s) {
        int a = Integer.parseInt(s.substring(0, s.indexOf("/")));
        int b = Integer.parseInt(s.substring(s.indexOf("/") + 1));
        return String.valueOf(a / b);
    }
}
