package Others;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public interface Others {
    public static boolean MapContainsArray(String @NotNull [] tab, HashMap<Integer,Integer> map){
        boolean hasAllKeys = true;
        int[] intArray = new int[tab.length];

        for (int i = 0; i < tab.length; i++) {
            intArray[i] = Integer.parseInt(tab[i]);
        }
        for (Integer a: intArray){
            if (!map.containsKey(a)) {
                hasAllKeys = false;
                break;
            }
        }
        if (!hasAllKeys||tab.length<1){
            return false;
        }return true;
    }//MapContainsArray

    public static String[] integerToArray(Integer @NotNull []intArray){
        String[] strArray = new String[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            strArray[i] = intArray[i].toString();
        }
        return strArray;
    }//integerToArray

    public static String[] keysToValues(String @NotNull []tab, HashMap<Integer,Integer> map){
        Integer[] values = new Integer[tab.length];//docelowa tablica z wartościami
        int[] keys = new int[tab.length];//przechowuje tab przekonwertowane na int

        for (int i = 0; i < keys.length; i++) {//konwersja tab na int
            keys[i] = Integer.parseInt(tab[i]);
        }
        for (int i = 0; i < keys.length; i++) {//zapisywanie wartości do values
            values[i] = map.get(keys[i]);
        }
        return integerToArray(values);
    }//keysToValues

    public static String strArrayToStringSepByCommas(String @NotNull []strArray){//funkcja rozdziela elementy tablicy przecinkiem i zapisuje je do String
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArray.length; i++) {
            sb.append(strArray[i]);
            if (i < strArray.length - 1) {
                sb.append(",");
            }
        }
        String str = sb.toString();
        return str;//przykładowy output: 1,2,3,4
    }//strArrayToStringSepByCommas

}
