package Row;

public class Row {
    private int ID=0;//ID rekordu w bazie
    private String wordPl="";
    private String wordEng="";
    public byte goodAnswers=0;// ilość poprawnych odpowiedzi

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getWordPl() {
        return wordPl;
    }

    public void setWordPl(String wordPl) {
        this.wordPl = wordPl;
    }

    public String getWordEng() {
        return wordEng;
    }

    public void setWordEng(String wordEng) {
        this.wordEng = wordEng;
    }

    public byte getGoodAnswers() {
        return goodAnswers;
    }

    public void setGoodAnswers(byte goodAnswers) {
        this.goodAnswers = goodAnswers;
    }
}
