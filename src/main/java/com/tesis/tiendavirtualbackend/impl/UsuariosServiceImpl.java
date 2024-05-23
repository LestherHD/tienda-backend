package com.tesis.tiendavirtualbackend.impl;

import com.tesis.tiendavirtualbackend.bo.CodigoConfirmacion;
import com.tesis.tiendavirtualbackend.bo.Usuarios;
import com.tesis.tiendavirtualbackend.dto.UsuariosRequestDTO;
import com.tesis.tiendavirtualbackend.dto.UsuariosResponseDTO;
import com.tesis.tiendavirtualbackend.repository.CodigoConfirmacionRepository;
import com.tesis.tiendavirtualbackend.repository.UsuariosRepository;
import com.tesis.tiendavirtualbackend.service.UsuariosService;
import com.tesis.tiendavirtualbackend.utils.MailUtils;
import com.tesis.tiendavirtualbackend.utils.MetodosUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UsuariosServiceImpl implements UsuariosService {

    @Autowired
    private UsuariosRepository repository;

    @Autowired
    private CodigoConfirmacionRepository codigoConfirmacionRepository;

    @Override
    public Usuarios getById(Long id) {
        return repository.getById(id);
    }

//    HABILITAR Y MODIFICAR SOLO SI HUBIERA UN MANTENIMIENTO DE USUARIOS
//    @Override
//    public Page<Usuarios> getByPage(UsuariosRequestDTO request) {
//        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("nombre").ascending());
//
//        Page<Usuarios> response = repository.getByPage(request.getTipoProducto().getId(), request.getTipoProducto().getId() == null ? 0l : request.getTipoProducto().getId(),
//                request.getTipoProducto().getCodigo(), request.getTipoProducto().getCodigo() == null ? "" : request.getTipoProducto().getCodigo(),
//                request.getTipoProducto().getNombre(), request.getTipoProducto().getNombre() == null ? "" : request.getTipoProducto().getNombre(),
//                pageable);
//        return response;
//    }

    @Override
    public Usuarios save(Usuarios obj) {
        return repository.save(obj);
    }

    @Override
    public void delete(Long id) {
        Usuarios obj = getById(id);
        if (obj != null) {
            repository.delete(obj);
        }
    }

    //Método para validar campos únicos, devuelve un mensaje por medio del consumo del servicio
    @Override
    public String getUniqueValidator(UsuariosRequestDTO requestDTO) {

        if (null != requestDTO && null != requestDTO.getUsuarios()) {

            Usuarios obj1 = repository.getByTelefono(requestDTO.getUsuarios().getTelefono());
            if (null != obj1){
                return "El teléfono ingresado ya se encuentra registrado en el sistema";
            }

            Usuarios obj2 = repository.getByCorreo(requestDTO.getUsuarios().getCorreo());
            if (null != obj2){
                return "El correo ingresado ya se encuentra registrado en el sistema";
            }

            Usuarios obj3 = repository.getByUsuario(requestDTO.getUsuarios().getUsuario());
            if (null != obj3){
                return "El usuario ingresado ya se encuentra registrado en el sistema";
            }




        }
        return null;
    }

    @Override
    public List<Usuarios> getAll() {
        return repository.findAll(Sort.by("nombre").ascending());
    }

    @Override
    public Usuarios getByUsuario(String usuario) {
        return repository.getByUsuario(usuario);
    }

    public Usuarios getByUsuarioOrCorreo(String usuario, String correo) {
        return repository.getByUsuarioOrCorreo(usuario, correo);
    }

    @Override
    public Usuarios getByUsuarioOrCorreoAndContrasenia(String usuarioOCorreo, String contrasenia) {
        return repository.getByUsuarioAndContraseniaOrCorreoAndContrasenia(usuarioOCorreo, contrasenia, usuarioOCorreo, contrasenia);
    }

    @Override
    public Usuarios getByPrincipal() {
        return repository.getByPrincipal("Y");
    }

    @Override
    public UsuariosResponseDTO generarCodigoCambiarContrasenia(UsuariosRequestDTO requestDTO) {

        UsuariosResponseDTO responseDTO = new UsuariosResponseDTO();

        Usuarios usuario = null;

        usuario = repository.getByUsuarioOrCorreo(requestDTO.getUsuarios().getUsuario(), requestDTO.getUsuarios().getCorreo());
        Pageable pageable = PageRequest.of(0, 1);
        CodigoConfirmacion codigoConfirmacion = codigoConfirmacionRepository.getByUsuarioIdAndTipo(usuario.getId(),"R");
        responseDTO.setError(false);
        responseDTO.setUsuario(usuario);

        if (null == usuario) {
            responseDTO.setRespuesta("Usuario no existe");
            responseDTO.setError(true);
        }

        if (null !=  codigoConfirmacion) {
            codigoConfirmacionRepository.delete(codigoConfirmacion);
            codigoConfirmacion = new CodigoConfirmacion(null, MetodosUtils.generarCodigo(), "Y", "R", usuario.getId(), new Date());
            codigoConfirmacionRepository.save(codigoConfirmacion);
            MailUtils.sendEmail("R", codigoConfirmacion.getCodigo(), usuario.getUsuario(), "", usuario.getNombres()+", "+usuario.getApellidos(),
                    "", "", codigoConfirmacion.getFecha());
        }

        return responseDTO;
    }
}
