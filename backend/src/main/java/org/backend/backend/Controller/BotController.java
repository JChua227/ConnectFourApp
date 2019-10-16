package org.backend.backend.Controller;

import org.backend.backend.Object.Bot;
import org.backend.backend.Object.Evaluator;
import org.backend.backend.Object.Move;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bot")
public class BotController {

    //the grid of the game must always have an odd number of columns
    //takes in game state and depth level
    @PostMapping("/move")
    public Move getMove(@RequestBody Bot bot){
        return new Bot(bot.getMove(),bot.getDepth()).getDecision();
    }
}
