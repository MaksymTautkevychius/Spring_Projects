package pl.edu.pja.tpo03;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Scanner;

@Repository
public class EntryRepository {
    private final EntityManager entityManager;

    public EntryRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void getAllEntries() {
        Query query = entityManager.createQuery("SELECT a FROM Entry a",Entry.class);
        List<Entry> entries= query.getResultList();
        if (entries.isEmpty())
        {
            System.out.println("No words to display");
            return;
        }
        for (Entry i:
             entries) {
            System.out.println(i.getEnglish()+"/"+i.getGerman()+"/"+i.getPolish());
        }
    }

    public List<Entry> getAllEntriesForSorting() {
        Query query = entityManager.createQuery("SELECT a FROM Entry a",Entry.class);
        List<Entry> entries= query.getResultList();
        return entries;
    }

    @Transactional
    public void addEntry(String English, String German, String Polish) {
        Entry newEntry = new Entry(English, German, Polish);
        entityManager.persist(new Entry(English,German,Polish));
    }

    @Transactional
    public void deleteEntry(String word) {
        Entry toDelete = findEntry(word);
            Entry managedEntry = entityManager.find(Entry.class, toDelete.getId());
                entityManager.remove(managedEntry);
    }


    @Transactional
    public void modifyEntry(String word) {
        Scanner input = new Scanner(System.in);
        Entry entry = findEntry(word);
            System.out.println("English");
            String word1 = input.nextLine();
            entry.setEnglish(word1);
            System.out.println("German");
            word1 = input.nextLine();
            entry.setGerman(word1);
            System.out.println("Polish");
            word1 = input.nextLine();
            entry.setPolish(word1);
            entityManager.merge(entry);

    }

    @Transactional
    public Entry findEntry(String word) {
        TypedQuery<Entry> query = entityManager.createQuery(
                "SELECT e FROM Entry e WHERE e.English = :word OR e.German = :word OR e.Polish = :word", Entry.class);
        query.setParameter("word", word);
        return query.getSingleResult();
    }
    public void Sort(int typeOfSort)
    {
        List<Entry> toSort = getAllEntriesForSorting();

        for (Entry entry: toSort)
        {
        }
    }
    public int SendQuery()
    {
        Query query = entityManager.createQuery("SELECT a FROM Entry a",Entry.class);
        int answer = query.getFirstResult();
        return answer;
    }
}
