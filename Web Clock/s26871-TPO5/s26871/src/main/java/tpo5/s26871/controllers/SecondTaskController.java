package tpo5.s26871.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecondTaskController {

    @PostMapping("/convert")
    @ResponseBody
    public String convertNumber(
            @RequestParam("number") String number,
            @RequestParam("InputType") int inputType,
            @RequestParam("OutputType") int outputType) {
        String val = Convert(number, inputType, outputType);
        String dec = Convert(number,inputType,10);
        String oct = Convert(number,inputType,8);
        String bin = Convert(number,inputType,2);
        String hex = Convert(number,inputType,16);
        String response = "<html><head><title>Result</title>";
        response += "<style>";
        response += "body {";
        response += "  text-align: center;";
        response += "  margin: 200px auto auto;";
        response += "  background-color: darkgray;";
        response += "  color: white;";
        response += "}";
        response += "</style>";
        response += "</head><body>";
        response += "<h1>Result:</h1>";
        response += "<p>" + val + "</p>";
        response += "<p> Bin: " + bin+" | " + "Oct: "+oct+" | " +"Dec: "+ dec+" | " +"Hex: " + hex + "</p>";
        response += "<form action=\"/convert-number.html\" method=\"get\">";
        response += "<button type=\"submit\">New value</button>";
        response += "</form>";
        response += "</body></html>";
        return response;
    }

    private String Convert(String number, int InputType, int Output) throws NumberFormatException {
        String answer;
        int newBase = Integer.parseInt(number, InputType);
        answer = Integer.toString(newBase, Output);
        return  answer;
    }
}
