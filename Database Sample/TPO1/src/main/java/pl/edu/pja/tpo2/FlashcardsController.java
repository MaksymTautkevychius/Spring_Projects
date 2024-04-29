package pl.edu.pja.tpo2;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class FlashcardsController  {
    private FileService fileService;
    private EntryRepository entryRepository;

    public void callTheMenu() throws IOException {
        while (true)
        {
            System.out.println("1. Add a new word");
            System.out.println("2. Display words");
            System.out.println("3. Create quiz");
            System.out.println("4. Quit");
            Scanner input = new Scanner(System.in);
            String answer = input.nextLine();
            switch (answer)
            {
                case "1":
                    System.out.println("Please, write required vals");
                    System.out.println("English:");
                    String English = input.nextLine();
                    System.out.println("German:");
                    String German = input.nextLine();
                    System.out.println("Polish:");
                    String Polish = input.nextLine();
                    this.addWord(English,German,Polish);
                    break;
                case "2":
                    this.displayAllWords();
                    break;
                case "3":
                    this.takeTest(Integer.parseInt("1"));
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Wrong value");
            }
        }
    }
    public FlashcardsController(FileService fileService, EntryRepository entryRepository) {
        this.fileService = fileService;
        this.entryRepository=entryRepository;
       // this.wordsProfiles=wordsProfiles;
    }

    public void addWord(String English, String German, String Polish) throws IOException {
       Entry newOne =  entryRepository.addEntry(English,German,Polish);
        fileService.writeNewEntryToFile(newOne,entryRepository);
    }

    public void displayAllWords() {
        fileService.readEntriesFromFile(entryRepository);
        List<Entry> entries = entryRepository.getAllEntries();
        for (Entry entry : entries) {
            String ProfEnglish = entry.getEnglish();
            String ProfGerman = entry.getGerman();
            String ProfPolish = entry.getPolish();
            System.out.println(ProfEnglish + "/" + ProfGerman + "/" + ProfPolish);
        }
    }
    public void takeTest(int k) {
        fileService.readEntriesFromFile(entryRepository);
        String mainChosenLanguage;
        Scanner input = new Scanner(System.in);
        List<Integer> langs = new ArrayList<>();
        langs.add(0);
        langs.add(1);
        langs.add(2);

        System.out.println("Choose main language: English/German/Polish");
        mainChosenLanguage = input.nextLine();
        int languageIndex;

        switch (mainChosenLanguage) {
            case "English":
                languageIndex = 0;
                langs.remove(Integer.valueOf(0));
                break;
            case "German":
                languageIndex = 1;
                langs.remove(Integer.valueOf(1));
                break;
            case "Polish":
                languageIndex = 2;
                langs.remove(Integer.valueOf(2));
                break;
            default:
                System.out.println("Invalid language choice");
                return;
        }
        List<Entry> words = entryRepository.getAllEntries();


            int randomIndex = (int) (Math.random() * words.size());
            int randomLangIndex = (int) (Math.random() * langs.size());

            String secondRandomLang = Entry.getLangName(langs.get(randomLangIndex));
            Entry randomWord = words.get(randomIndex);

            System.out.println("Translate the following word from " + mainChosenLanguage + " to " +
                    secondRandomLang + "=>" + randomWord.get(languageIndex));

            String userAnswer = input.nextLine();

            if (userAnswer.equalsIgnoreCase(randomWord.get(langs.get(randomLangIndex)))) {
                System.out.println("Correct!");
            } else {
                System.out.println("Wrong, the correct answer is: " + randomWord.get(langs.get(randomLangIndex)));
            }
            langs.remove(languageIndex);


         secondRandomLang = Entry.getLangName(langs.get(0));
         randomWord = words.get(randomIndex);

        System.out.println("Translate the following word from " + mainChosenLanguage + " to " +
                secondRandomLang + "=>" + randomWord.get(0));
            userAnswer = input.nextLine();
        if (userAnswer.equalsIgnoreCase(randomWord.get(langs.get(0)))) {
            System.out.println("Correct!");
        } else {
            System.out.println("Wrong. The correct answer is: " + randomWord.get(langs.get(0)));
        }

        }
    }


