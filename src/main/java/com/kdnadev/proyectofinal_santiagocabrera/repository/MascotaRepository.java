package com.kdnadev.proyectofinal_santiagocabrera.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdnadev.proyectofinal_santiagocabrera.model.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByTipoMascotaId(Long tipoMascotaId);

    @Query("SELECT m FROM Mascota m WHERE m.id = :id AND m.disponibleParaAdopcion = TRUE")
    Optional<Mascota> findByIdAndDisponibleParaAdopcion(Long id);

    @Query("SELECT m FROM Mascota m WHERE m.disponibleParaAdopcion = TRUE")
    List<Mascota> findAllDisponibleParaAdopcion();

}
