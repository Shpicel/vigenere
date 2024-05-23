package com.example.demo1.controller;

import com.example.demo1.entity.VotingInput;
import com.example.demo1.service.Voting;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class VotingController {
    private final Voting voting;

    @RequestMapping("/voting")
    public String votingForm(Model model) {
        model.addAttribute("votingInput", new VotingInput());
        return "voting";
    }

    @PostMapping("/executeActionVoting")
    public String generatePrimeSubmit(@ModelAttribute VotingInput votingInput, Model model) {
        String result;
        result = String.valueOf(voting.runVoting(Integer.parseInt(votingInput.getCountVotes())));
        model.addAttribute("resultVoting", result);
        model.addAttribute("votingInput", votingInput);
        return "voting";
    }
}
