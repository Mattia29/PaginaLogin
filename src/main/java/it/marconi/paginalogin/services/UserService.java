package it.marconi.paginalogin.services;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    public static boolean registraUtente(String nome, String email, String password) {
        return true;
    }

    public static boolean loginUtente(String nome, String password) {
        return true;
    }
}
