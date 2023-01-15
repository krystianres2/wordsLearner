package Others;

import DataBase.Select;
import DataValidation.DataValidation;
import Row.Row;
import com.diogonunes.jcolor.Attribute;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Learn extends Select implements DataValidation {

    public static void test(ArrayList<Row>list){
        int index=0;
        int Or=0;//jeśli 0 to wordPL jeśli 1 to wordENG
        double size= list.size();
        double overallGoodAnswers=0;
        String word="";
        double wynik=0;
        try {
            if (list.isEmpty()) throw new IllegalArgumentException();

            while (!list.isEmpty()) {
                index = Others.randomInt(0, list.size() - 1);
                Or = Others.randomInt(0, 1);
                if (Or == 0) {
                    System.out.println(list.get(index).getWordPl());
                    System.out.println("Zapisz tłumaczenie tego słowa na angielski: ");
                    word = DataValidation.StringEngValidation();
                    if (word.equals(list.get(index).getWordEng())) {
                        System.out.println("Poprawnie ");
                        list.remove(index);
                        overallGoodAnswers++;
                    } else {
                        System.out.println("Niepoprawnie ");
                        list.remove(index);
                    }
                }//Or==0
                if (Or == 1) {
                    System.out.println(list.get(index).getWordEng());
                    System.out.println("Zapisz tłumaczenie tego słowa na polski: ");
                    word = DataValidation.StringPlValidation();
                    if (word.equals(list.get(index).getWordPl())) {
                        System.out.println("Poprawnie");
                        list.remove(index);
                        overallGoodAnswers++;
                    } else {
                        System.out.println("Niepoprawnie ");
                        list.remove(index);
                    }
                }//Or==1
                System.out.println();
            }//while
            System.out.println("Ukoczyłeś test");
            wynik = (overallGoodAnswers / size) * 100;
            System.out.println("Twój wynik to: " + wynik + " %");
        }catch (IllegalArgumentException e){
            System.out.println(colorize("Brak słówek w zestawie", Attribute.RED_TEXT()));
        }
    }//test

    public static void learn(@NotNull ArrayList<Row> list){
        int index=0;
        int Or=0;//jeśli 0 to wordPL jeśli 1 to wordENG
        String word;
        try {
            if (list.isEmpty()) throw new IllegalArgumentException();
            while (!list.isEmpty()) {
                index = Others.randomInt(0, list.size() - 1);
                Or = Others.randomInt(0, 1);
                if (Or == 0) {
                    System.out.println(list.get(index).getWordPl());
                    System.out.println("Zapisz tłumaczenie tego słowa na angielski: ");
                    word = DataValidation.StringEngValidation();
                    if (word.equals(list.get(index).getWordEng())) {
                        System.out.println("Poprawnie ");
                        list.get(index).goodAnswers++;
                    } else {
                        System.out.println("Niepoprawnie ");
                    }
                }//Or==0
                if (Or == 1) {
                    System.out.println(list.get(index).getWordEng());
                    System.out.println("Zapisz tłumaczenie tego słowa na polski: ");
                    word = DataValidation.StringPlValidation();
                    if (word.equals(list.get(index).getWordPl())) {
                        System.out.println("Poprawnie");
                        list.get(index).goodAnswers++;
                    } else {
                        System.out.println("Niepoprawnie ");
                    }
                }//Or==1
                if (list.get(index).getGoodAnswers() == 4) {
                    list.remove(index);
                }//if
                System.out.println();
            }//while
            System.out.println("Brawo, nauczyłeś się wszystkich słówek");
        }catch (IllegalArgumentException e){
            System.out.println(colorize("Brak słówek w zestawie",Attribute.RED_TEXT()));
        }
    }//learn

}
