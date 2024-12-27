package com.kdnadev.proyectofinal_santiagocabrera.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdnadev.proyectofinal_santiagocabrera.common.utils.Utils;
import com.kdnadev.proyectofinal_santiagocabrera.dto.usuario.UsuarioCreateDTO;
import com.kdnadev.proyectofinal_santiagocabrera.dto.usuario.UsuarioMapper;
import com.kdnadev.proyectofinal_santiagocabrera.dto.usuario.UsuarioUpdateDTO;
import com.kdnadev.proyectofinal_santiagocabrera.exception.ElementoDuplicadoException;
import com.kdnadev.proyectofinal_santiagocabrera.exception.ResourceNotFoundException;
import com.kdnadev.proyectofinal_santiagocabrera.model.Rol;
import com.kdnadev.proyectofinal_santiagocabrera.model.Usuario;
import com.kdnadev.proyectofinal_santiagocabrera.repository.UsuarioRepository;

@Service
@Transactional(readOnly = true)
public class UsuarioService {
    private PasswordEncoder passwordEncoder;
    private UsuarioRepository usuarioRepository;
    private UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper,
            PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> getAll() {
        return usuarioRepository.findAllWithRoles();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findByIdWithRoles(id);
    }

    @Transactional(readOnly = false)
    public void deleteById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("No se encontro el usuario con id: " + id);
                });

        usuarioRepository.delete(usuario);
    }

    @Transactional(readOnly = false)
    public Usuario create(UsuarioCreateDTO usuarioNuevo, int codigoRol) {
        Usuario usuario = usuarioMapper.toEntity(usuarioNuevo);

        usuarioRepository.findByDocumento(usuario.getDocumento())
                .ifPresent(usuarioExistente -> {
                    throw new ElementoDuplicadoException("Ya existe un usuario con el documento ingresado.");
                });

        usuarioRepository.findByUsername(usuario.getUsername())
                .ifPresent(usuarioExistente -> {
                    throw new ElementoDuplicadoException("El nombre de usuario que ingreso no esta disponible");
                });

        usuario.setPassword(passwordEncoder.encode(usuarioNuevo.getPassword()));
        usuario.setRoles(Set.of(Rol.fromCodigo(codigoRol)));

        usuario.setFechaCreacion(Utils.getCurrentDate());

        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = false)
    public Usuario update(Long id, UsuarioUpdateDTO actualizacionUsuario) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("No se encontro el usuario con id: " + id);
                });

        usuarioMapper.updateUsuarioFromDTO(actualizacionUsuario, usuario);

        usuario.setFechaActualizacion(Utils.getCurrentDate());

        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> findByUsername(String string) {
        return usuarioRepository.findByUsername(string);
    }
}
