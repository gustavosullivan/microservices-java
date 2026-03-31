package br.edu.atitus.greetingservice.controllers;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import br.edu.atitus.greetingservice.configs.GreetingConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.edu.atitus.greetingservice.dto.GreetingRequest;
@RestController
@RequestMapping("/greeting")
public class GreetingContoller {
    private final GreetingConfig config;

    public GreetingContoller(GreetingConfig config) {
        this.config = config;
    }

    @GetMapping({"", "/"})
    public String getGreeting(
        @RequestParam(required = false) String name){
        if (name == null || name.isEmpty()){
            name = config.getDefaultName();
        }

        String greetingReturn = String.format("%s %s!!!", config.getGreeting(), name);
        return greetingReturn;
    }
        @PostMapping
    public String postGreeting(@RequestBody GreetingRequest request) {

        String name = request.getName();

        if (name == null || name.isEmpty()) {
            name = config.getDefaultName();
        }

        return String.format("%s %s!!!", config.getGreeting(), name);
    }
    @GetMapping("/{name}")
    public String getGreetingByPath(@PathVariable String name) {

        if (name == null || name.isEmpty()) {
            name = config.getDefaultName();
        }

        return String.format("%s %s!!!", config.getGreeting(), name);
    }
}
