//Author: Clarence Guo
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class DMControl {

    static ArrayList<DManager> wordlist = ReadFile();
    static Scanner kb = new Scanner(System.in);

    //read the data from "WordList.txt" and store it to a ArrayList.
    public static ArrayList ReadFile() {
        ArrayList<DManager> listWord = new ArrayList<DManager>();
        String file_path = "E:\\CodesPackage-notes\\Java\\first-project\\src\\WordList.txt";
        File file = new File(file_path);
        String line;
        try {
            BufferedReader FILE = new BufferedReader(new FileReader(file));
            while ((line = FILE.readLine()) != null) {
                if (!line.equals("")) {
                    DManager W = new DManager(line);
                    listWord.add(W);
                }
            }
            FILE.close();
        } catch (IOException e) {
            System.out.println("Error reading file");
            return null;
        }
        return listWord;
    }

    //write the data to the text named "WordList.txt".
    public static void SaveFile(ArrayList<DManager> wordlist) {
        Iterator<DManager> LW = wordlist.iterator();
        File files = new File("WordList.txt");
        if (files.exists()) {
            files.delete();
        }
        File file = new File("WordList.txt");
        try {
            BufferedWriter FILE = new BufferedWriter(new FileWriter(file, true));
            while (LW.hasNext()) {
                FILE.write(LW.next().tostring());
                FILE.newLine();
            }
            FILE.flush();
            FILE.close();
            System.out.println("The WordList has been changed and saved successfully");
        } catch (IOException e) {
            System.out.println("Error saving to file");
        }
    }

    //return false (true) when input "n" or "N"("Y"or"y").
    public static boolean StopOrNot(String question) {
        String result = stopORnot(question);
        if (result.equalsIgnoreCase("Y")) {
            return true;
        } else {
            return false;
        }
    }

    //confine the input value into "y", "Y", "n" or "N".
    public static String stopORnot(String question) {
        String answer = null;
        boolean result = false;
        while (!result) {
            System.out.println(question);
            answer = inputString();
            if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("N")) {
                result = true;
            } else {
                System.out.println("Please, text N or Y to show NO or YES");
                result = false;
            }
        }
        return answer;
    }

    //select the certain words starting with letters "n".
    public static int intended(String n, boolean result) {
        int i = 0, N = n.length();
        DManager WordL;
        String M;
        Iterator<DManager> LM1 = wordlist.iterator();
        while (LM1.hasNext()) {
            WordL = LM1.next();
            M = WordL.getWord();
            if ((M.substring(0, N)).equalsIgnoreCase(n)) {
                if (!result) {
                    System.out.println(M);
                    i++;
                } else {
                    System.out.println(WordL.tostring());
                    i++;
                }
            }
        }
        return i;
    }

    //print all of the words in WordList in alphabetical order.
    public static void PrintALL() {
        boolean result = StopOrNot("Do you want to print the whole words with meaning ? (Y/N)");
        char c;
        String w;
        for (int x = 0; x < 26; x++) {
            c = (char) ((byte) (x + 65));
            w = String.valueOf(c);
            intended(w, result);
        }
    }

//print the words from the WordList by starting with particular letters asked by users.
    public static void PrintIntended() {
        boolean result = true, want;
        int i = 0;
        System.out.println("If you want to print the particular words in the WordList. ");
        while (result) {
            System.out.println("First, please enter letters or a certain letter as the start of the word :");
            String n = inputString();
            want = StopOrNot("Do you want to print the words with its meaning ? (Y/N)");
            i = intended(n, want);
            if (i != 0) {
                result = StopOrNot("Whether you want to keep on printing the words as you want ? (Y/N)");
            } else {
                System.out.println("Sorry, the type of words that you want to find dose not exist.");
                result = StopOrNot("Whether you want to keep on using this function? (Y/N)");
            }
        }
    }

    //print a random word with its meaning.
    public static void PrintAtRandom() {
        boolean result = true;
        int y, len = wordlist.size();
        while (result) {
            y = (int) (Math.random() * len);
            System.out.println((wordlist.get(y)).tostring());
            result = StopOrNot("Whether you want to keep on getting the random word ? (Y/N)");
        }
    }

    //judge the existence of the word input by users.
    public static boolean wordExistence(String m, ArrayList<DManager> wordlist) {
        Iterator<DManager> word = wordlist.iterator();
        while (word.hasNext()) {
            if ((word.next().getWord()).equalsIgnoreCase(m)) {
                return true;
            }
        }
        return false;
    }

    //remove words and save the new WordList into a file. 
    public static void RemoveWords() {
        boolean result = true;
        while (result) {
            System.out.println("enter the word that you want to remove from the list:");
            String RWord = inputString();
            if (wordExistence(RWord, wordlist)) {
                for (int i = 0; i < wordlist.size(); i++) {
                    if (wordlist.get(i).getWord().equalsIgnoreCase(RWord)) {
                        wordlist.remove(wordlist.get(i));
                    }
                }
                result = StopOrNot("Boss, do you still have any words want to remove? (Y/N)");
                SaveFile(wordlist);
            } else {
                System.out.println("Sorry, the word you text dose not exist in the WordList");
                result = StopOrNot("Boss, do you still have any words want to remove? (Y/N)");
            }
        }
    }

    //add words and save the new WordList into a file.
    public static void AddWords() {
        boolean result = true;
        while (result) {
            System.out.println("Enter the word that you want to add to the WordList:");
            String Word = inputString();
            if (!wordExistence(Word, wordlist)) {
                System.out.println("Enter the meaning of the word above:");
                String Meaning = inputString();
                DManager newWORD = new DManager(Word, Meaning);
                wordlist.add(newWORD);
                result = StopOrNot("Boss,do you stil have any other words that you want to add to the WordList? (Y/N)");
            } else {
                System.out.print("Sorry, the word you text has already existed in the WordList.");
                result = StopOrNot("Boss,do you stil have any other words that you want to add to the WordList? (Y/N)");
            }
        }
        SaveFile(wordlist);
    }

    //display the meaning of a random word and check the word's spelling from users.
    public static void TestForVocabulary() {
        int len = wordlist.size();
        boolean result = true;
        String x;
        int n;
        ArrayList<DManager> wordlist = ReadFile();
        while (result) {
            n = (int) (Math.random() * len);
            System.out.print("Please think about which word in the WordList has the meaning below :\r\n" + wordlist.get(n).getMeaning()
                    + "\r\nPlease type it in the screen: ");
            x = inputString();
            if (x.equalsIgnoreCase(wordlist.get(n).getWord())) {
                System.out.println("Correct word !");
                result = StopOrNot("Boss, do you want to keep on having a test here ? (Y/N)");
            } else {
                System.out.println("Sorry, boss, your answer is not correct.\r\nThe correct answer is:\r\n" + wordlist.get(n).getWord());
                result = StopOrNot("Boss,do not be so sad for this mistake, I am sure you will do better.\r\nDo you want to do it agian ? (Y/N)");
            }
        }
    }

    //display the meaning of the word input by users.(the range of the word is WordList)
    public static void ToGetMeaning() {
        int len = wordlist.size();
        System.out.print("If you want to know a certain words' meaning. First, ");
        boolean result = true;
        while (result) {
            int M = spellingCheck();
            if (M != len) {
                System.out.println("The meaning of " + "[ " + wordlist.get(M).getWord() + " ] : " + wordlist.get(M).getMeaning());
                result = StopOrNot("Boss,do you want to check more words'meaning ? (Y/N)");
            } else {
                result = false;
            }
        }
    }

    //check the spelling of the word input by users and give an suggestion on the word in case spelling wrong.
    public static int spellingCheck() {
        int len = wordlist.size();
        boolean result = true;
        String WORD;
        int m = 0;
        while (result) {
            System.out.println("Enter the word to check your spelling:");
            WORD = inputString();
            for (int x = 0; x < len; x++) {
                if (WORD.equalsIgnoreCase(wordlist.get(x).getWord())) {
                    m = x;
                    System.out.println("Spelling Corect!");
                    result = false;
                }
            }
            if (result) {
                m = FindSimilarWord.SWN(WORD, wordlist, len);
                if (m < len) {
                    System.out.println("Sorry boss,your spelling is not exactly correct.");
                    System.out.print("But whether " + "[ " + wordlist.get(m).getWord() + " ]");
                    if (StopOrNot(" is the word you want to check ?(Y/N) :")) {
                        result = false;
                    } else {
                        m = len;
                        result = StopOrNot("Before you get the meaning that you want to check in the wordlist, you need to pass the SpellingCheck.\r\n"
                                + "Do you want to keep on going ?");
                    }
                } else {
                    result = StopOrNot("Sorry boss, perhaps the word you input is not within the WordList.\r\n"
                            + "Do you want to keep on going ?");
                }
            }
        }
        return m;
    }

    //count the total number of the words in the WordList.
    public static void CountWord() {
        int Sum = wordlist.size();
        System.out.println("The total number of the words in the WordList is " + Sum + ".");
    }

    //for users to input values.
    public static String inputString() {
        String n = null;
        boolean result = true;
        while (result) {
            n = kb.nextLine();
            if (VaildEmpty(n)) {
                result = false;
            } else {
                System.out.print("Sorry, you cannot text nothing. Please, input the vaild value: ");
            }
        }
        return n;
    }

    //this method is for avoiding the null input.
    public static boolean VaildEmpty(String option) {
        boolean result = true;
        if (option.equals("")) {
            return false;
        }
        return result;
    }
}