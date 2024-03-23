package com.example.demo1.controller;

import com.example.demo1.entity.VigenereInput;
import com.example.demo1.service.VigenereCipher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class VigenereController {
    private final VigenereCipher vigenereCipher;

    @RequestMapping("/vigenere")
    public String vigenereForm(Model model) {
        model.addAttribute("vigenereInput", new VigenereInput());
        return "inputText";
    }



    @PostMapping("/executeAction")
    public String vigenereSubmit(@ModelAttribute VigenereInput vigenereInput, Model model) {
        String result;
        if (!vigenereInput.getMode().equals("encryption")) {
            result = vigenereCipher.decrypt(vigenereInput.getText(), vigenereInput.getKey());
        } else {
            result = vigenereCipher.encrypt(vigenereInput.getText(), vigenereInput.getKey());
        }
        model.addAttribute("result", result);
        model.addAttribute("vigenereInput", vigenereInput);
        return "inputText";
    }
}
