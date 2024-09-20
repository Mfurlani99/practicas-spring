package com.example.demo.DAOS;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.utilidades.Persona;

@Repository
public interface PersonaDAO extends CrudRepository<Persona,Long> {

    
} 