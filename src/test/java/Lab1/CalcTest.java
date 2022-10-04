package Lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcTest {

    ACalc aCalc = new ACalc();

    RCalc rCalc = new RCalc();

    @Test
    void test1() { assertEquals(Integer.parseInt(aCalc.add("5+6")), 11); }

    @Test
    void test2() { assertEquals(Integer.parseInt(aCalc.sub("9-5")), 4); }

    @Test
    void test3() { assertEquals(Integer.parseInt(aCalc.mul("5*6")), 30); }

    @Test
    void test4() { assertEquals(Integer.parseInt(aCalc.div("8/3")), 3); }

    @Test
    void test5() { assertEquals(rCalc.add("X+VI"), rCalc.intToRoman(16)); }

    @Test
    void test6() { assertEquals(rCalc.sub("X-VI"), rCalc.intToRoman(4)); }

    @Test
    void test7() { assertEquals(rCalc.mul("X*VI"), rCalc.intToRoman(60)); }

    @Test
    void test8() { assertEquals(rCalc.div("X/VI"), rCalc.intToRoman(1)); }

    @Test
    void test9() { assertEquals(rCalc.sub("VI-X"), "Non-positive result"); }
}