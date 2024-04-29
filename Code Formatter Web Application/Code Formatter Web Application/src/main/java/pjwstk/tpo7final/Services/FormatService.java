package pjwstk.tpo7final.Services;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FormatService {
    public FormatService()
    {

    }
    public String FormatCode(String ToFormat) throws FormatterException
    {
        Formatter formatter = new Formatter();
        return formatter.formatSource(ToFormat);
    }
}
