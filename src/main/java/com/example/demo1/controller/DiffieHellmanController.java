package com.example.demo1.controller;

import com.example.demo1.entity.DiffieHellmanInput;
import com.example.demo1.service.DiffieHellman;
import com.example.demo1.service.PrimitiveRoots;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class DiffieHellmanController {
    private final DiffieHellman diffieHellman;
    private final PrimitiveRoots primitiveRoots;

    @RequestMapping("/diffiehellman")
    public String diffieHellmanForm(Model model) {
        model.addAttribute("diffieHellmanInput", new DiffieHellmanInput());
        return "diffieHellman";
    }

    @PostMapping("/executeAction1")
    public String diffieHellmanSubmit(@ModelAttribute DiffieHellmanInput diffieHellmanInput, Model model) {
        String result;
        result = String.valueOf(diffieHellman.runDiffieHellman());
        model.addAttribute("result", result);
        model.addAttribute("diffieHellmanInput", diffieHellmanInput);
        return "diffieHellman";
    }

    @PostMapping("/executeAction2")
    public String bigIntegerPrimeSieveSubmit(@ModelAttribute DiffieHellmanInput diffieHellmanInput, Model model) {
        String result;
        result = String.valueOf(diffieHellman.runBigIntegerPrimeSieve());
        model.addAttribute("result", result);
        model.addAttribute("diffieHellmanInput", diffieHellmanInput);
        return "diffieHellman";
    }
    @PostMapping("/executeAction3")
    public String primitiveRootsSubmit(@ModelAttribute DiffieHellmanInput diffieHellmanInput, Model model) {
        String result;
        result = String.valueOf(primitiveRoots.runPrimitiveRoots());
        model.addAttribute("result", result);
        model.addAttribute("diffieHellmanInput", diffieHellmanInput);
        return "diffieHellman";
    }
}
