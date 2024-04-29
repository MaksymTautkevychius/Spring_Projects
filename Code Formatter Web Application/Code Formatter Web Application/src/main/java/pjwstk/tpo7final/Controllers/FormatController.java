package pjwstk.tpo7final.Controllers;


import pjwstk.tpo7final.Objects.Format;
import pjwstk.tpo7final.Services.FormatService;
import pjwstk.tpo7final.Services.SerializationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.google.googlejavaformat.java.FormatterException;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Controller
public class FormatController {

    FormatService formatService = new FormatService();
    SerializationService serializationService = new SerializationService();

    @GetMapping("/format")
    public String showFormatForm(@RequestParam(required = false) String input, Model model) {
        String formattedCode = "";
        if (input != null) {
            try {
                formattedCode = formatService.FormatCode(input);
            } catch (FormatterException e) {
                // Handle formatting exception
                return "redirect:error";
            }
        }
        model.addAttribute("formattedCode", formattedCode);
        return "format";
    }

    @PostMapping("/format")
    public String saveFormat(@RequestParam int id, @RequestParam(required = false) int ttl,
                             @RequestParam(required = false, name = "formattedCode") String formattedCode,
                             Model model) throws IOException {
        LocalDate currentDate = LocalDate.now();
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDateTime currentDateTime = LocalDateTime.of(currentDate, midnight);
        int minutesToAdd = 30;
        LocalDateTime futureDateTime = currentDateTime.plus(minutesToAdd, ChronoUnit.MINUTES);
        Format format = new Format(id, formattedCode, futureDateTime);
        serializationService.SerializeFormat(Duration.ofMinutes(ttl), format);
        formattedCode = format.getData();
        model.addAttribute("formattedCode", formattedCode);

        return "format";
    }

    @PostMapping("/getById")
    public String getFormat(@RequestParam int id, Model model) throws IOException, ClassNotFoundException {
        Format format = serializationService.DeserializeWithId(Integer.toString(id));
        if (format != null) {
            model.addAttribute("formattedCode", format.getData());
        }
        return "format";
    }
}

