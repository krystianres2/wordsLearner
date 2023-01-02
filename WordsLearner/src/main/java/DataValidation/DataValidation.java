package DataValidation;

import Excel.Excel;
import Exceptions.IncorrectValue;
import Exceptions.InputDoesNotMatchToRegex;
import Others.Others;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public interface DataValidation extends Others {

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
                System.out.println("Wartość musi być liczbą w zakresie "+min+"-"+max);
                input.next();
                continue;
            }catch (IncorrectValue e){
                System.out.println("Wartość musi być w zakresie "+min+"-"+max);
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
                System.out.println("Wartość musi być liczbą");
                input.next();
                continue;
            }catch (IncorrectValue e){
                System.out.println("Wartość musi być w zakresie "+min+"-"+max);
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
                System.out.println("Podana wartosć nie może zawierać liczb, znaków interpunkcyjnych ani znaków specjalnych");
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
                System.out.println("Podana wartosć nie może zawierać liczb, znaków interpunkcyjnych ani znaków specjalnych");
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
                System.out.println("Podana wartosć może jedynie zawierać liczby oddzielone przecinkami");
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
        File file=new File(path);
      //  System.out.println(Others.getFileType(path));
       //if (/*!file.exists()||file.canWrite()||*/ Others.getFileType(path).equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
         //   throw new IOException();
        //}
       return path;
    }//xlsFilePath

}
