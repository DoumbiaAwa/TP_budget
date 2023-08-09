package com.budget.tp_budget.service;

import com.budget.tp_budget.entity.User;
import com.budget.tp_budget.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Methode pour ajouter un utilisateur
    public String add(User user){
        userRepository.save(user);
        return "add success";
    }

    //Methode pour recuperer la liste des utilisateur
    public List<User> read(){
        return userRepository.findAll();
    }

    //Methode pour supprimer un utilisateur
    public String delete(int id){
        userRepository.deleteById(id);
        return "suppression successful";
    }

    //Methode pour Modifier les informations d'un utilisateur
    public User update(User user){
        userRepository.save(user);
       return userRepository.findById(user.getId());

    }

    //Methode connexion pour verifier si l'utilisateur existe
    public User connexion(String email, String password){
        User user = userRepository.findByEmailAndPassword(email, password);
        if(user != null){
            return user;
        }else throw new EntityExistsException("user doesn't exist");
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId.intValue());
    }
}
