package com.kdnadev.proyectofinal_santiagocabrera.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdnadev.proyectofinal_santiagocabrera.repository.AdopcionRepository;
import com.kdnadev.proyectofinal_santiagocabrera.repository.MascotaRepository;
import com.kdnadev.proyectofinal_santiagocabrera.repository.UsuarioRepository;
import com.kdnadev.proyectofinal_santiagocabrera.common.utils.Utils;
import com.kdnadev.proyectofinal_santiagocabrera.dto.adopcion.AdopcionCreateDTO;
import com.kdnadev.proyectofinal_santiagocabrera.dto.adopcion.AdopcionMapper;
import com.kdnadev.proyectofinal_santiagocabrera.exception.ResourceNotFoundException;
import com.kdnadev.proyectofinal_santiagocabrera.exception.ValidacionNegocioException;
import com.kdnadev.proyectofinal_santiagocabrera.model.Adopcion;
import com.kdnadev.proyectofinal_santiagocabrera.model.Mascota;
import com.kdnadev.proyectofinal_santiagocabrera.model.Usuario;

@Service
public class AdopcionService {
    private AdopcionRepository adopcionRepository;
    private MascotaRepository mascotaRepository;
    private UsuarioRepository usuarioRepository;
    private AdopcionMapper adopcionMapper;

    public AdopcionService(AdopcionRepository adopcionRepository, MascotaRepository mascotaRepository, UsuarioRepository usuarioRepository, AdopcionMapper adopcionMapper) {
        this.adopcionRepository = adopcionRepository;
        this.mascotaRepository = mascotaRepository;
        this.usuarioRepository = usuarioRepository;
        this.adopcionMapper = adopcionMapper;
    }

    @Transactional(readOnly = false)
    public Adopcion create(AdopcionCreateDTO adopcionNueva) {
        Adopcion adopcion = adopcionMapper.toEntity(adopcionNueva);

        Usuario usuario = usuarioRepository.findById(adopcion.getIdUsuario())
            .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Mascota mascota = mascotaRepository.findById(adopcion.getIdMascota())
            .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada"));

        if (!mascota.isDisponibleParaAdopcion()) {
            throw new ValidacionNegocioException("Mascota no disponible para adopcion");
        }

        // En el modelo se maneja el error de maximo numero de mascotas
        usuario.incrementaNumeroDeAdopciones();
        usuarioRepository.save(usuario);

        mascota.setDisponibleParaAdopcion(false);
        mascotaRepository.save(mascota);

        adopcion.setFechaAdopcion(Utils.getCurrentDate());
        return adopcionRepository.save(adopcion);
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {
        // Buscar la adopción primero para evitar múltiples consultas
        Adopcion adopcion = adopcionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No se encontró el vínculo de adopción con ID: " + id));

        Usuario usuario = usuarioRepository.findById(adopcion.getIdUsuario())
            .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Mascota mascota = mascotaRepository.findById(adopcion.getIdMascota())
            .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada"));

        adopcionRepository.delete(adopcion);

        mascota.setDisponibleParaAdopcion(true);
        mascotaRepository.save(mascota);
        usuario.decrementaNumeroDeAdopciones();
        usuarioRepository.save(usuario);
    }
}
