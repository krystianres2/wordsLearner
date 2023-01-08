package DataValidation;

import Exceptions.IncorrectValue;
import Exceptions.InputDoesNotMatchToRegex;
import com.diogonunes.jcolor.Attribute;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;

public interface DataValidation  {

    static byte ByteValidation(byte min,byte max) {
        byte num = 0;
        Scanner input = new Scanner(System.in);
        boolean valid = false;
        while (!valid||num<min||num>max) {
            try {
                num = input.nextByte();
                valid=true;
                if (num<min||num>max) throw new IncorrectValue();
            } catch (InputMismatchException e) {
                System.out.println(colorize("Wartość musi być liczbą w zakresie "+min+"-"+max, Attribute.RED_TEXT()));
                input.next();
                continue;
            }catch (IncorrectValue e){
                System.out.println(colorize("Wartość musi być w zakresie "+min+"-"+max,Attribute.RED_TEXT()));
                //input.next();
                continue;
            }
        }//while
        return num;
    }//ByteValidation

    static int IntValidation(int min,int max) {
        int num = 0;
        Scanner input = new Scanner(System.in);
        boolean valid = false;
        while (!valid||num<min||num>max) {
            try {
                num = input.nextInt();
                valid=true;
                if (num<min||num>max) throw new IncorrectValue();
            } catch (InputMismatchException e) {
                System.out.println(colorize("Wartość musi być liczbą w zakresie " +min+"-"+max,Attribute.RED_TEXT()));
                input.next();
                continue;
            }catch (IncorrectValue e){
                System.out.println(colorize("Wartość musi być w zakresie "+min+"-"+max,Attribute.RED_TEXT()));
                //input.next();
                continue;
            }
        }//while
        return num;
    }//ByteValidation

    static String StringEngValidation() {
        String pom="";
        String regex = "^[a-zA-Z ]+$";
        Scanner input = new Scanner(System.in);
        boolean valid = false;
        while (!valid||pom.length()<1) {
            try {
                pom = input.nextLine();
                valid = true;
                if (!pom.matches(regex)){
                    valid=false;
                    throw new InputDoesNotMatchToRegex();
                }
            } catch (InputDoesNotMatchToRegex e) {
                System.out.println(colorize("Podana wartosć nie może zawierać liczb, znaków interpunkcyjnych ani znaków specjalnych",Attribute.RED_TEXT()));
                //input.next();
                continue;
            }
        }//while
        return pom;
    }//StringEngValidation
    static String StringPlValidation() {
        String pom="";
        String regex = "^(?=\\S)[\\p{L}\\s]*[ąćęłńóśźżĄĆĘŁŃÓŚŹŻ]?[\\p{L}\\s]*\\S$";
        Scanner input = new Scanner(System.in);
        boolean valid = false;
        while (!valid||pom.length()<1) {
            try {
                pom = input.nextLine();
                valid = true;
                if (!pom.matches(regex)) {
                    valid=false;
                    throw new InputDoesNotMatchToRegex();
                }
            } catch (InputDoesNotMatchToRegex e) {
                System.out.println(colorize("Podana wartosć nie może zawierać liczb, znaków interpunkcyjnych ani znaków specjalnych",Attribute.RED_TEXT()));
                //input.next();
                continue;
            }
        }//while
        return pom;
    }//StringEngValidation

    static String IntegersAndCommas(){
        String pom="";
        String regex = "^[0-9]+,[0-9]+([,][0-9]+)*$";
        Scanner input = new Scanner(System.in);
        boolean valid = false;
        while (!valid||pom.length()<1) {
            try {
                pom = input.nextLine();
                valid = true;
                if (!pom.matches(regex)){
                    valid=false;
                    throw new InputDoesNotMatchToRegex();
                }
            } catch (InputDoesNotMatchToRegex e) {
                System.out.println(colorize("Podana wartosć może jedynie zawierać liczby oddzielone przecinkami",Attribute.RED_TEXT()));
                //input.next();
                continue;
            }
        }//while
        return pom;
    }//IntgersAndCommas

    static String xlsFilePath() throws IOException {
       String path;
       Scanner input=new Scanner(System.in);
       path= input.nextLine();
       return path;
    }//xlsFilePath

}
