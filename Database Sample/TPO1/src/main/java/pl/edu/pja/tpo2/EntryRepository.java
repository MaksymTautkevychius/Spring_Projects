package pl.edu.pja.tpo2;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntryRepository  {
    private List<Entry> entries;

    public EntryRepository() {
        this.entries = new ArrayList<>();
    }

    public List<Entry> getAllEntries() {
        return entries;
    }

    public List<List<Entry>> Splited()
    {
        List<List<Entry>> SplittedList=null;
        for (int i=0;i<entries.size();i++)
        {
            for (int j=0;j<3;j++)
            {

            }
        }
        return SplittedList;
    }

    public Entry addEntry(String English, String German, String Polish) {
        Entry newEntry = new Entry(English, German, Polish);
        entries.add(newEntry);

        return newEntry;
    }
}
