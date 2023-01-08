package Others;

import DataValidation.DataValidation;
import com.diogonunes.jcolor.Attribute;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;

public interface Others extends DataValidation {
    static boolean MapContainsArray(String @NotNull [] tab, HashMap<Integer,Integer> map){
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
        return hasAllKeys && tab.length >= 1;
    }//MapContainsArray

    static String[] integerToArray(Integer @NotNull []intArray){
        String[] strArray = new String[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            strArray[i] = intArray[i].toString();
        }
        return strArray;
    }//integerToArray

    static String[] keysToValues(String @NotNull []tab, HashMap<Integer,Integer> map){
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

    static String strArrayToStringSepByCommas(String @NotNull []strArray){//funkcja rozdziela elementy tablicy przecinkiem i zapisuje je do String
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArray.length; i++) {
            sb.append(strArray[i]);
            if (i < strArray.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();//przykładowy output: 1,2,3,4
    }//strArrayToStringSepByCommas

    static int randomInt(int min, int max){
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }//randomInt

    static String getFileType(String fileName){
        String fileType = "Undetermined";
        final File file = new File(fileName);
        try{
            fileType = Files.probeContentType(file.toPath());
        }
        catch (IOException ioException){
            System.out.println(colorize("File type not detected for " + fileName, Attribute.RED_TEXT()));
        }
        return fileType;
    }
    static String chooseTable(){
        String tab="";
        byte num;
        System.out.println("Wybierz numer zestawu którego wybrana operacja ma dotyczyć");
        System.out.println("1. Zestaw 1");
        System.out.println("2. Zestaw 2");
        System.out.println("3. Zestaw 3");
        System.out.print("Twój wybór: ");
        num=DataValidation.ByteValidation((byte) 1, (byte) 3);
        if (num==1){
            tab="Tab1";
        }
        if (num==2){
            tab="Tab2";
        }
        if (num==3){
            tab="Tab3";
        }
        System.out.println();
        return tab;
    }//chooseTable
    static void displayMenu(){
        System.out.println("-----------------------------");
        System.out.println(colorize("MENU",Attribute.BLACK_TEXT(),Attribute.WHITE_BACK()));
        System.out.println("1. Wyświetl zawartość zestawu");
        System.out.println("2. Wstaw nowe słówka do zestawu");
        System.out.println("3. Usuń słówka z zestawu");
        System.out.println("4. Ucz się");
        System.out.println("5. Przetestuj znajomość słówek");
        System.out.println("6. Importuj plik xls do bazy danych");
        System.out.println("7. Eksportuj baze danych do pliku xls");
        System.out.println("8. Wyjdź");
        System.out.println();
        System.out.println("-----------------------------");
        System.out.println();
    }
    static void displayDeleteSubMenu(){
        System.out.println("1. Usun pojedynczy wiersz");
        System.out.println("2. Usun więcej wierszy");
        System.out.println();
    }
    static void next(){
        Scanner input=new Scanner(System.in);
        input.nextLine();
    }

}
