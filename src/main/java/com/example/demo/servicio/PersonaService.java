package com.example.demo.servicio;

import java.util.List;

import com.example.demo.utilidades.Persona;

public interface PersonaService {

  public List<Persona> listaPersonas();

  public void salvar(Persona persona);

  public void borrar( Persona persona );
  
  public Persona localizarPersona(Persona persona);

}
