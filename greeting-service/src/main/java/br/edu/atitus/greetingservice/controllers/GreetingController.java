package br.edu.atitus.greetingservice.controllers;

import br.edu.atitus.greetingservice.configs.GreetingConfig;
import br.edu.atitus.greetingservice.dtos.GreetingRecordDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private final GreetingConfig config;

    public GreetingController(GreetingConfig config) {
        this.config = config;
    }

    @GetMapping({"", "/","/{name}"})
    public String getGreeting(@RequestParam(required = false) String name,
                              @PathVariable(name = "name",required = false) String namePath) {

        String finalName = name != null ? name : namePath;

        String greetingReturn = String.format("%s %s!!!", config.getGreeting(),finalName = finalName != null ? finalName : config.getDefaultName());
        return greetingReturn;

    }

    @PostMapping
    public String postGreeting(@RequestBody GreetingRecordDto greetingRecordDto) {

        String name = greetingRecordDto.name();
        if (name.isBlank())
            name = config.getDefaultName();

        String greetingReturn = String.format("%s %s!!!", config.getGreeting(),name);
        return greetingReturn;
    }
}
