package pl.edu.pja.tpo2;

public class Entry  {
    private String Polish,German,English;

    public Entry(String English, String German, String Polish)
    {
        this.English=English;
        this.German=German;
        this.Polish=Polish;
    }

    public String getPolish() {
        return Polish;
    }
    public String getGerman()
    {
        return German;
    }
    public String getEnglish()
    {
        return English;
    }

    public String get(int languageIndex) {
        switch (languageIndex)
        {
            case 0:
               return  this.getEnglish();
            case 1:
                return  this.getGerman();
            case 2:
               return this.getPolish();
            default:
                System.out.println("Wrong val, English chosen as a default");
                break;
        }
        return this.getEnglish();
    }
    public int getInt(String languageName) {
        switch (languageName)
        {
            case "English":
                return  0;
            case "German":
                return  1;
            case "Polish":
                return 2;
            default:
                System.out.println("Wrong val, English chosen as a default");
                break;
        }
        return 0;
    }
    public static String getLangName(int languageIndex) {
        switch (languageIndex) {
            case 0:
                return "English";
            case 1:
                return "German";
            case 2:
                return "Polish";
            default:
                System.out.println("Wrong val, English chosen as a default");
                break;
        }
        return "English";
    }
}
