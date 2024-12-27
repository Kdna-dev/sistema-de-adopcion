package com.kdnadev.proyectofinal_santiagocabrera.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.kdnadev.proyectofinal_santiagocabrera.common.utils.Utils;
import com.kdnadev.proyectofinal_santiagocabrera.dto.mascota.MascotaCreateDTO;
import com.kdnadev.proyectofinal_santiagocabrera.dto.mascota.MascotaMapper;
import com.kdnadev.proyectofinal_santiagocabrera.dto.mascota.MascotaUpdateDTO;
import com.kdnadev.proyectofinal_santiagocabrera.dto.tipo_mascota.TipoMascotaCreateDTO;
import com.kdnadev.proyectofinal_santiagocabrera.dto.tipo_mascota.TipoMascotaMapper;
import com.kdnadev.proyectofinal_santiagocabrera.exception.ElementoDuplicadoException;
import com.kdnadev.proyectofinal_santiagocabrera.exception.ResourceNotFoundException;
import com.kdnadev.proyectofinal_santiagocabrera.model.Mascota;
import com.kdnadev.proyectofinal_santiagocabrera.model.TipoMascota;
import com.kdnadev.proyectofinal_santiagocabrera.repository.MascotaRepository;
import com.kdnadev.proyectofinal_santiagocabrera.repository.TipoMascotaRepository;

@Service
public class MascotaService {
    private MascotaRepository mascotaRepository;
    private MascotaMapper mascotaMapper;
    private TipoMascotaRepository tipoMascotaRepository;
    private TipoMascotaMapper tipoMascotaMapper;

    public MascotaService(MascotaRepository mascotaRepository, MascotaMapper mascotaMapper,
            TipoMascotaRepository tipoMascotaRepository,
            TipoMascotaMapper tipoMascotaMapper) {
        this.mascotaRepository = mascotaRepository;
        this.mascotaMapper = mascotaMapper;
        this.tipoMascotaRepository = tipoMascotaRepository;
        this.tipoMascotaMapper = tipoMascotaMapper;
    }

    public List<Mascota> getAll() {
        return mascotaRepository.findAll();
    }

    public Optional<Mascota> getById(Long id) {
        return mascotaRepository.findById(id);
    }

    public List<Mascota> getAllDisponiblesParaAdopcion() {
        return mascotaRepository.findAllDisponibleParaAdopcion();
    }

    public Optional<Mascota> getByIdDisponibleAdopcion(Long id) {
        return mascotaRepository.findByIdAndDisponibleParaAdopcion(id);
    }

    public void deleteById(Long id) {
        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("No se encontro la mascota con id: " + id);
                });

        mascotaRepository.delete(mascota);
    }

    /**
     * Registra una nueva mascota
     * 
     * @param mascotaNueva: Datos de la nueva mascota
     * @return type Mascota
     */
    public Mascota create(MascotaCreateDTO mascotaNueva) {
        // Primero buscamos el tipo de mascota existente
        TipoMascota tipoMascota = tipoMascotaRepository.findById(mascotaNueva.getTipoMascota().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existe el tipo de mascota con id: " + mascotaNueva.getTipoMascota().getId()));

        Mascota mascota = mascotaMapper.toEntity(mascotaNueva);
        mascota.setTipoMascota(tipoMascota); // Establecemos el tipo de mascota encontrado
        mascota.setFechaCreacion(Utils.getCurrentDate());

        return mascotaRepository.save(mascota);
    }

    /**
     * Actualiza la mascota correspondiente con los datos nuevos
     *
     * @param id:                   Identificador de la mascota a actualizar
     * @param actualizacionMascota: Datos nuevos de la mascota
     * @return type Mascota
     */
    public Mascota update(Long id, MascotaUpdateDTO actualizacionMascota) {
        if(actualizacionMascota.getTipoMascota() != null)
            tipoMascotaRepository.findById(actualizacionMascota.getTipoMascota().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo mascota no encontrado"));

        return mascotaRepository.findById(id)
                .map(m -> {
                    mascotaMapper.updateMascotaFromDTO(actualizacionMascota, m);

                    m.setFechaActualizacion(Utils.getCurrentDate());
                    return mascotaRepository.save(m);

                }).orElseThrow(() -> new ResourceNotFoundException("No se encontro la mascota con id: " + id));
    }

    public Mascota setDisponibleParaAdopcion(Long id, boolean disponible) {
        return mascotaRepository.findById(id)
                .map(m -> {
                    m.setDisponibleParaAdopcion(disponible);
                    return mascotaRepository.save(m);
                })
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro la mascota con id: " + id));
    }

    public TipoMascota createTipoMascota(TipoMascotaCreateDTO tipoNuevo) {
        TipoMascota tipoMascota = tipoMascotaMapper.toEntity(tipoNuevo);

        if (tipoMascotaRepository.findByNombre(tipoMascota.getNombre()).isPresent())
            throw new ElementoDuplicadoException("Ya existe el tipo mascota que intenta ingresar.");

        tipoMascota.setFechaCreacion(Utils.getCurrentDate());
        return tipoMascotaRepository.save(tipoMascota);
    }

    public List<TipoMascota> getAllTipoMascota() {
        return tipoMascotaRepository.findAll();
    }
}
