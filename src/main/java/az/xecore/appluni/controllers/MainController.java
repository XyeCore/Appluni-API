package az.xecore.appluni.controllers;


import az.xecore.appluni.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MainController {

    private final UsersService usersService;

    @GetMapping
    public String apiHome() {

        return "Hello namik";
    }

    @GetMapping("/users")
    public String getContacts() {

        return usersService.getAllUsers().toString();
    }


}
