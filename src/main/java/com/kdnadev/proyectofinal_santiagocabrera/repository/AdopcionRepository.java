package com.kdnadev.proyectofinal_santiagocabrera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kdnadev.proyectofinal_santiagocabrera.model.Adopcion;

@Repository
public interface AdopcionRepository extends JpaRepository<Adopcion, Long>{
    
}
