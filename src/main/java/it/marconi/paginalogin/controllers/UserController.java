package it.marconi.paginalogin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.marconi.paginalogin.services.UserService;

@Controller
public class UserController {
    
    public String getPaginaUtente(@RequestParam("type") String tipo, Model model) {
        // Controllo il valore del parametro tipo
        if("new".equals(tipo)) {
            // Se è "new", restituisco la pagina di registrazione
            return "registrazione";
        } else if("login".equals(tipo)) {
            // Se è "login", restituisco la pagina di login
            return "login";
        }
        // Se il tipo non è nè "new" né "login", mi da errore
        return "errore";
    }

    // Gestisco la richiesta POST a /utente/registrazione
    @PostMapping("/utente/registrazione")
    public String registraUtente(@RequestParam("nickname") String nome, @RequestParam("email") String email, @RequestParam("password") String password) {
        boolean registrato = UserService.registraUtente(nome, email, password);
        if(registrato) {
            return "redirect:/utente/" + nome;
        }
        return "errore";
    }

    // Gestisco la richiesta POST a /utente/login
    @PostMapping("/utente/login")
    public String loginUtente(@RequestParam("nickname") String nome, @RequestParam("password") String password) {
        boolean loggato = UserService.loginUtente(nome, password);
        if(loggato) {
            return "redirect:/utente/" + nome;
        }
        return "errore";
    }

    // Gestisco la richiesta GET a /utente/{nickname}
    @GetMapping("/utente/{nickname}")
    public String getProfiloUtente(@PathVariable("nickname") String nome, Model model) {
        model.addAttribute("nickname", nome);
        return "profilo";
    }
}
