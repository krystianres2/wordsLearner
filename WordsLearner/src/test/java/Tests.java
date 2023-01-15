import Others.Learn;
import Others.Others;
import Row.Row;
import org.junit.Test;

import java.util.ArrayList;

import static Others.Others.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class Tests {
    @Test
    public void testTestWithEmptyList() {
        //given
        ArrayList<Row> list = new ArrayList<>();
        //then
        Learn.test(list);
        assertTrue(list.isEmpty());
    }
    @Test
    public void testLearnWithEmptyList(){
        //given
        ArrayList<Row> list=new ArrayList<>();
        //then
        Learn.learn(list);
        assertTrue(list.isEmpty());
    }
    @Test
    public void testRandomInt_withValidInput() {
        //given
        int min = 5, max = 10;
        //when
        int result = Others.randomInt(min, max);
        //then
        assertTrue(result >= min && result <= max);
    }

    @Test
    public void testRandomInt_withEqualMinMax() {
        //given
        int min = 5, max = 5;
        //when
        int result = Others.randomInt(min, max);
        //then
        assertEquals(result, max);
    }

    @Test
    public void testRandomInt_withManyCalls() {
        //given
        int min = 5, max = 10;
        //then
        for (int i = 0; i < 100; i++) {
            int result = Others.randomInt(min, max);
            assertTrue(result >= min && result <= max);
        }
    }
    @Test
    public void testIntegerToArray() {
        //given
        Integer[] intArray = {1, 2, 3, 4, 5};
        String[] expectedOutput = {"1", "2", "3", "4", "5"};
        //then
        assertArrayEquals(expectedOutput, integerArrayToStringArray(intArray));
    }
    @Test
    public void testStrArrayToStringSepByCommas() {
        //given
        String[] strArray = {"one", "two", "three", "four"};
        String expectedOutput = "one,two,three,four";
        //then
        assertEquals(expectedOutput, strArrayToStringSepByCommas(strArray));
    }
}
