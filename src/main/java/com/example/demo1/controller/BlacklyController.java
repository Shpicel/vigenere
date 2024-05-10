package com.example.demo1.controller;

import com.example.demo1.entity.BlacklyInput;
import com.example.demo1.service.MethodBlackly;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Controller
@AllArgsConstructor
public class BlacklyController {
    private final MethodBlackly methodBlackly;

    @RequestMapping("/blackly")
    public String blacklyForm(Model model) {
        model.addAttribute("blacklyInput", new BlacklyInput());
        return "blackly";
    }

    @PostMapping("/executeActionBlackly")
    public String generatePrimeSubmit(@ModelAttribute BlacklyInput blacklyInput, Model model) {
        HashMap<String, Object> resultMap = methodBlackly.runBlackly(blacklyInput.getFileName());
        String finalResult = ((StringBuilder) resultMap.get("finalResult")).toString();
        ArrayList<double[]> subResults = (ArrayList<double[]>) resultMap.get("subResults");
        StringBuilder subResultString = new StringBuilder();
        subResults.forEach(it -> subResultString.append(Arrays.toString(it)).append("\n"));
        model.addAttribute("resultBlackly", finalResult);
        model.addAttribute("subResults", subResultString.toString());
        model.addAttribute("blacklyInput", blacklyInput);
        return "blackly";
    }
}
