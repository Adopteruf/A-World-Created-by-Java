//Author: Clarence Guo
public class DManager {

    private String word;
    private String meaning;

    //a method for creating new DManager object. 
    public DManager(String word, String meaning) {
        super();
        this.word = word;
        this.meaning = meaning;
    }

    //a method prepared for reading data from the WordList text file and storing the data into the DManager object.
    public DManager(String lineFile) {
        String[] LF = lineFile.split(":");
        this.word = LF[0].trim();
        this.meaning = LF[1].trim();
    }

    //get a String value including word and meaning.
    public String tostring() {
        return word + ": " + meaning;
    }

    public String getWord() {
        return word;
    }

    public String getMeaning() {
        return meaning;
    }
}