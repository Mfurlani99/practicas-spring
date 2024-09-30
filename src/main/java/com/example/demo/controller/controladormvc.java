package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.servicio.PersonaService;
import com.example.demo.utilidades.Persona;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;






@Controller
@Slf4j
public class controladormvc {

    @Autowired
    private PersonaService personaservicio;

    @GetMapping("/")
    public String comienzo(Model model) {
        String hola = "Hola, ¿cómo están?";
        List<Persona> Personas = personaservicio.listaPersonas();

        model.addAttribute("saludo", hola);
        model.addAttribute("personas", Personas);
        return "indice";
    } 

    @GetMapping("/anexar")
    public String anexar(Persona persona){
    return"anexar";
    }

    @GetMapping("/cambiar/{id_individuo}")
    public String cambiar(Persona persona, Model model) {
     persona = persona =  personaservicio.localizarPersona(persona);
     model.addAttribute("persona",persona);
        return "cambiar";
    }


    @GetMapping("/eliminar/{id_individuo}")
    public String eliminar(Persona persona){
        persona = persona =  personaservicio.localizarPersona(persona);
        
        if (persona != null) {
            personaservicio.borrar(persona);
        }
        
        return "redirect:/";
    }
    
    

    @PostMapping("/salvar")
    public String salvar(@Valid Persona persona, Errors error) {
        if(error.hasErrors()){
            return "cambiar";
        }
     personaservicio.salvar(persona);      
        
        return "redirect:/";
    }
    
   
}

