package org.backend.backend.Controller;

import org.backend.backend.Entity.Move;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:4000")
@RequestMapping("/bot")
public class BotController {

    @GetMapping
    public Move getMove(){
        return new Move();
    }
}
