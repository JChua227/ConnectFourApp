package org.backend.backend.Controller;

import org.backend.backend.Object.Bot;
import org.backend.backend.Object.Move;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bot")
public class BotController {


    @PostMapping("/move")
    public Move getMove(@RequestBody Bot bot){
        return bot.getDecision();
    }
}
