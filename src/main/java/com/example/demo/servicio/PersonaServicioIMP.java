package com.example.demo.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DAOS.PersonaDAO;
import com.example.demo.utilidades.Persona;
@Service
public class PersonaServicioIMP implements PersonaService {

    @Autowired
    private PersonaDAO personadao;
   
    @Override
    @Transactional(readOnly = true)
    public List<Persona> listaPersonas() {
        return (List<Persona>) personadao.findAll();
        
    }

    @Override
    @Transactional
    public void salvar(Persona persona) {
      personadao.save(persona);
    }

    @Override
    @Transactional
    public void borrar(Persona persona) {
        personadao.delete(persona);
    }

    

    @Override
    @Transactional(readOnly = true)
    public Persona localizarPersona(Persona persona) {
       return personadao.findById(persona.getId_individuo()).orElse(null);
    }

}
