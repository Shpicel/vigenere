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

import java.util.Arrays;

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

    @PostMapping("/executeActionGeneratePrime")
    public String generatePrimeSubmit(@ModelAttribute DiffieHellmanInput diffieHellmanInput, Model model) {
        String result;
        result = String.valueOf(diffieHellman.generatePrime(Integer.parseInt(diffieHellmanInput.getBitLen()), Integer.parseInt(diffieHellmanInput.getIteration())));
        model.addAttribute("resultGeneratePrime", result);
        model.addAttribute("diffieHellmanInput", diffieHellmanInput);
        return "diffieHellman";
    }

    @PostMapping("/executeActionDiffieHellman")
    public String diffieHellmanSubmit(@ModelAttribute DiffieHellmanInput diffieHellmanInput, Model model) {
        String result;
        result = Arrays.toString(diffieHellman.runDiffieHellman(diffieHellmanInput.getPrivateKeyXa(), diffieHellmanInput.getPrivateKeyXb(), diffieHellmanInput.getPrimeNum(), diffieHellmanInput.getRootPrimeNum()));
        model.addAttribute("resultDiffieHellman", result);
        model.addAttribute("diffieHellmanInput", diffieHellmanInput);
        return "diffieHellman";
    }

    @PostMapping("/executeActionBigIntegerPrimeSieve")
    public String bigIntegerPrimeSieveSubmit(@ModelAttribute DiffieHellmanInput diffieHellmanInput, Model model) {
        String result;
        result = String.valueOf(diffieHellman.runBigIntegerPrimeSieve(diffieHellmanInput.getLowerBound(),diffieHellmanInput.getUpperBound()));
        model.addAttribute("resultBigIntegerPrimeSieve", result);
        model.addAttribute("diffieHellmanInput", diffieHellmanInput);
        return "diffieHellman";
    }

    @PostMapping("/executeActionPrimitiveRoots")
    public String primitiveRootsSubmit(@ModelAttribute DiffieHellmanInput diffieHellmanInput, Model model) {
        String result;
        result = String.valueOf(primitiveRoots.runPrimitiveRoots(diffieHellmanInput.getInputRoot()));
        model.addAttribute("result", result);
        model.addAttribute("diffieHellmanInput", diffieHellmanInput);
        return "diffieHellman";
    }
}
