package pl.edu.pja.tpo03;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class  FileService  {

    private int answer;
    List<Entry> entries= new ArrayList<>();
    String fileName="src/main/resources/dictionary.csv";

    public void addEntryFile(String English, String German, String Polish)
    {
        entries.add(new Entry(English,German,Polish));
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
    public void readEntriesFromFile() {
        try (BufferedReader read = new BufferedReader(new FileReader(fileName))) {
            String line;
            String none="";
            while ((line = read.readLine()) != null) {
                List<String> words = List.of(line.split(";"));
                    this.addEntryFile(words.get(0), words.get(1), words.get(2));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void CheckIfDataBaseIsEmpty(EntryRepository entryRepository)
    {
        if(answer==0)
        {
            readEntriesFromFile();
            for (Entry i: entries
                 ) {
                entryRepository.addEntry(i.getEnglish(),i.getGerman(),i.getPolish());
            }
        }
    }

}
