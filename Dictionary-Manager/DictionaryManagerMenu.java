//Author: Clarence Guo
import java.util.Scanner;

public class DictionaryManagerMenu {

    static Scanner kb = new Scanner(System.in);
    static String question = new String("Do you want to continue enjoying the service from your DictionaryManager ? (Y/N)");

    //run the followed the function chosed by users.
    public static void DictionaryManagerRun() {
        boolean EXIT = true;
        String option;
        System.out.print("Hi boss, DctionaryManager is here for service.\r\n");
        while (EXIT) {
            option = OPTION();
            switch (option) {
                case "1":
                    DMControl.PrintALL();
                    EXIT = DMControl.StopOrNot(question);
                    break;
                case "2":
                    DMControl.PrintAtRandom();
                    EXIT = DMControl.StopOrNot(question);
                    break;
                case "3":
                    DMControl.PrintIntended();
                    EXIT = DMControl.StopOrNot(question);
                    break;
                case "4":
                    DMControl.TestForVocabulary();
                    EXIT = DMControl.StopOrNot(question);
                    break;
                case "5":
                    DMControl.AddWords();
                    EXIT = DMControl.StopOrNot(question);
                    break;
                case "6":
                    DMControl.RemoveWords();
                    EXIT = DMControl.StopOrNot(question);
                    break;
                case "7":
                    DMControl.CountWord();
                    EXIT = DMControl.StopOrNot(question);
                    break;
                case "8":
                    DMControl.ToGetMeaning();
                    EXIT = DMControl.StopOrNot(question);
                    break;
                case "0":
                    EXIT = false;
            }
        }
    }

    //supply the menu to user by printing it out.
    public static String OPTION() {
        boolean result = false;
        String option = null;
        while (!result) {
            System.out.println("There are several fuctions available for use:\r\n"
                    + "1==Print the whole words\r\n"
                    + "2==Print a word with meaning at random\r\n"
                    + "3==Print the words starting with particular letters\r\n"
                    + "4==Check your spelling when knowing the meaning of a random word\r\n"
                    + "5==add some new words into the WordList\r\n"
                    + "6==remove some words of the WordList\r\n"
                    + "7==Count the total number of words in the WordList\r\n"
                    + "8==Check the meaning of a word by typing a word in the wordlist(with word reminder)\r\n"
                    + "0==EXIT\r\n"
                    + "Your Option: ");
            option = DMControl.inputString();
            result = Vaild(option);
            if (!result) {
                System.out.println("Sorry, boss, but please input in the required format.");
            }
        }
        return option;
    }

    //check the validity of input value.
    public static boolean Vaild(String option) {
        char OPTION;
        char[] Option = option.toCharArray();
        OPTION = Option[0];
        if (option.length() != 1) {
            return false;
        }
        if ((OPTION > '9') || (OPTION < '0')) {
            return false;
        }
        return true;
    }
}