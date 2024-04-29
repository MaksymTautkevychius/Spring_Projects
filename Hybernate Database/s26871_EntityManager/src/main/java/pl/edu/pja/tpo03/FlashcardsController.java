package pl.edu.pja.tpo03;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class FlashcardsController  {

    public FlashcardsController( EntryRepository entryRepository, FileService fileService) {
         this.fileService = fileService;
        this.entryRepository=entryRepository;
        // this.wordsProfiles=wordsProfiles;
    }
    private EntryRepository entryRepository;
    private FileService fileService;

    public void callTheMenu() throws IOException {
        fileService.setAnswer(entryRepository.SendQuery());
        fileService.CheckIfDataBaseIsEmpty(entryRepository);
        while (true)
        {
            System.out.println("1. Manage Words");
            System.out.println("2. Display words");
            System.out.println("3. Create quiz");
            System.out.println("4. Quit");
            Scanner input = new Scanner(System.in);
            String answer = input.nextLine();
            switch (answer)
            {
                case "1":
                    this.ManageWords();
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


    public void addWord(String English, String German, String Polish) throws IOException {
       entryRepository.addEntry(English,German,Polish);
    }
    public void displayAllWords() {
        entryRepository.getAllEntries();
    }
    public void deleteWord(String word)
    {
        entryRepository.deleteEntry(word);
    }
    public void ModifyWord(String word)
    {
        entryRepository.modifyEntry(word);
    }
    public void findWord(String word)
    {
        entryRepository.findEntry(word);
    }
    @Transactional
    public void ManageWords()
    {
        System.out.println("delete/modify/find/add/sort");
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();
        switch (choice)
        {
            case "delete":
                System.out.println("write interpretation of one of three languages to delete the word");
                choice = input.nextLine();
                entryRepository.deleteEntry(choice);
                break;
            case "modify":
                System.out.println("write interpretation of one of three languages to modify the word");
                choice = input.nextLine();
                entryRepository.modifyEntry(choice);
                break;
            case "find":
                System.out.println("write interpretation of one of three languages to find the word");
                choice = input.nextLine();
                System.out.println(entryRepository.findEntry(choice).getEnglish()+"/"+entryRepository.findEntry(choice).getGerman()+"/"+entryRepository.findEntry(choice).getPolish());
                break;
            case "add":
                System.out.println("English");
                choice = input.nextLine();
                String English = choice;
                System.out.println("German");
                choice = input.nextLine();
                String german = choice;
                System.out.println("Polish");
                choice = input.nextLine();
                String Polish = choice;
                entryRepository.addEntry(English,german,Polish);
                break;
            case "sort":
                System.out.println("1 => descending, 2=> ascending");
                int choices = input.nextInt();
                entryRepository.Sort(choices);
                break;
            default:break;
        }
    }
    public void takeTest(int k) {
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
        List<Entry> words = entryRepository.getAllEntriesForSorting();


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


