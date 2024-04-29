package tpo5.s26871.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class FirstTaskController {
    @GetMapping("/current-time")
    @ResponseBody
    public String returnDate(){
        String answ = null;
        String val = null;
        try
        {
            String pattern = "hh:mm:ss.SSSS yyyy/MM/dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date());
            val=date;

        }
        catch (Exception e)
        {
            System.out.println("Error"+e.getMessage());
        }
         answ="<!DOCTYPE html>\n" +
                 "<html lang=\"en\">\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "<title>Curr</title>\n" +
                "<style>\n" +
                "  body {\n" +
                "    margin: 200px auto auto;\n" +
                "    text-align: center;\n" +
                "    background-color: darkgray;\n" +
                "    color: gray;\n" +
                "  }\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <p>Current time and date is:"+val+"</p>\n" +
                "</body>\n" +
                "</html>\n";
        return  answ;
    }

    @GetMapping("/current-year")
    @ResponseBody
    public String returnYear(){
        String date="";
        String answ;
        try
        {
            String pattern = "YYYY";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String dates = simpleDateFormat.format(new Date());
            date=dates;
        }
        catch (Exception e)
        {
            System.out.println("Error" + e.getMessage());
        }
        answ="<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "<title>Curr</title>\n" +
                "<style>\n" +
                "  body {\n" +
                "    margin: 200px auto auto;\n" +
                "    text-align: center;\n" +
                "    background-color: darkgray;\n" +
                "    color: gray;\n" +
                "  }\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <p>Current year is:"+date+"</p>\n" +
                "</body>\n" +
                "</html>\n";
        return answ;
    }
}
