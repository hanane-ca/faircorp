package com.emse.spring.faircorp.hello;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DummyUserService implements UserService{

    private final GreetingService greetingService;

    @Autowired
    public DummyUserService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Override
    public void greetAll() {
        List<String> names = Arrays.asList("Elodie", "Charles");
        for (String name : names) {
            this.greetingService.greet(name);        }
    }
}
