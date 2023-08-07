package com.budget.tp_budget.controller;

import com.budget.tp_budget.entity.User;
import com.budget.tp_budget.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //Endpoint pour ajouter un utilisateur
    @PostMapping("/add")
    public String add(@RequestBody User user){
        userService.add(user);
        return "add successful";
    }

    //Endpoint pour recuperer la liste des utilisateur
    @GetMapping("/list")
    public List<User> read(){
        return userService.read();
    }

    //Endpoint pour suprimer un utilisateur
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        userService.delete(id);
        return "delete successful";
    }

    //Endpoint pour modifier les information d'un utilisateur
    @PutMapping("/modifier")
    public User modifier(@RequestBody User user){
       return userService.update(user);
    }

    //Endpoint connexion pour verifier si l'utilisateur existe
    @GetMapping("/connexion")
    public ResponseEntity<User> user(@RequestParam("email") String email, @RequestParam("password") String password){
        return new ResponseEntity<>(userService.connexion(email, password), HttpStatus.OK);
    }

}
