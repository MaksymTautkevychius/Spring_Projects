package pl.edu.pja.tpo2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class  FileService  {
    String fileName;
    public FileService(@Value("${pl.edu.pja.tpo02.filename}") String fileName)
    {
        this.fileName=fileName;
    }
    public void readEntriesFromFile(EntryRepository entryRepository) {
        try (BufferedReader read = new BufferedReader(new FileReader(fileName))) {
            String line;
            String none="";
            while ((line = read.readLine()) != null) {
                List<String> words = List.of(line.split(";"));
                    entryRepository.addEntry(words.get(0), words.get(1), words.get(2));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void writeNewEntryToFile(Entry entry, EntryRepository entryRepository) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String line = String.format("%n%s;%s;%s",
                    entry.getEnglish(), entry.getGerman(), entry.getPolish());
            writer.write(line);
            readEntriesFromFile(entryRepository);
        }
    }

}
