package com.kdnadev.proyectofinal_santiagocabrera.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kdnadev.proyectofinal_santiagocabrera.model.TipoMascota;

@Repository
public interface TipoMascotaRepository extends JpaRepository<TipoMascota, Long> {
    Optional<TipoMascota> findByNombre(String nombre);
}
