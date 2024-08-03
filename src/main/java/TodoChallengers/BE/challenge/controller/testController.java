package TodoChallengers.BE.challenge.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class testController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }

    @PostMapping("/echo")
    public String echoMessage(@RequestBody String message) {
        return message;
    }

}
