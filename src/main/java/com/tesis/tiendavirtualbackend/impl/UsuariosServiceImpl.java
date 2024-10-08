package com.tesis.tiendavirtualbackend.impl;

import com.tesis.tiendavirtualbackend.bo.CodigoConfirmacion;
import com.tesis.tiendavirtualbackend.bo.Usuarios;
import com.tesis.tiendavirtualbackend.dto.UsuariosRequestDTO;
import com.tesis.tiendavirtualbackend.dto.UsuariosResponseDTO;
import com.tesis.tiendavirtualbackend.repository.CodigoConfirmacionRepository;
import com.tesis.tiendavirtualbackend.repository.UsuariosRepository;
import com.tesis.tiendavirtualbackend.service.UsuariosService;
import com.tesis.tiendavirtualbackend.utils.GlobalExceptionHandler;
import com.tesis.tiendavirtualbackend.utils.MailUtils;
import com.tesis.tiendavirtualbackend.utils.MetodosUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

    @Override
    public UsuariosResponseDTO save(Usuarios obj, String type) {

        UsuariosResponseDTO responseDTO = new UsuariosResponseDTO();

        try {

            if (type.equals("editado")){
                Usuarios objSave = repository.getById(obj.getId());
                objSave.setPrincipal(obj.getPrincipal());
                objSave.setSucursal(obj.getSucursal());
                objSave.setTelefono(obj.getTelefono());
                objSave.setCorreo(obj.getCorreo());
                objSave.setNombres(obj.getNombres());
                objSave.setApellidos(obj.getApellidos());
                objSave.setUsuario(obj.getUsuario());
                repository.save(objSave);
            } else {
                repository.save(obj);
            }
            responseDTO.setError(false);
            responseDTO.setRespuesta("Usuario "+type+" con éxito");
        } catch (DataIntegrityViolationException ex){
            HashMap<String, String> mapExcepciones = new HashMap<String, String>();
            mapExcepciones.put("usuarios.telefono_UNIQUE", "teléfono");
            mapExcepciones.put("usuarios.correo_UNIQUE", "correo");
            mapExcepciones.put("usuarios.usuario_UNIQUE", "usuario");
            String exception = GlobalExceptionHandler.handleDataIntegrityViolationException(ex, mapExcepciones);
            responseDTO.setError(true);
            responseDTO.setRespuesta(exception);
        }

        return responseDTO;
    }

    @Override
    public UsuariosResponseDTO delete(Long id) {
        UsuariosResponseDTO responseDTO = new UsuariosResponseDTO();

        Usuarios obj = getById(id);
        List<CodigoConfirmacion> codigo = codigoConfirmacionRepository.getByUsuarioId(id);
        if (codigo != null && codigo.size() > 0){
            for (CodigoConfirmacion c : codigo )
            codigoConfirmacionRepository.deleteById(c.getId());
        }
        if (obj != null) {
            try {
                repository.delete(obj);
                responseDTO.setError(false);
                responseDTO.setRespuesta("Usuario eliminado con éxito");
            } catch (DataIntegrityViolationException ex){
                HashMap<String, String> mapExcepciones = new HashMap<String, String>();
                String exception = GlobalExceptionHandler.handleDataIntegrityViolationException(ex, mapExcepciones);
                responseDTO.setError(true);
                responseDTO.setRespuesta(exception);
            }
        }

        return responseDTO;
    }

    @Override
    public List<Usuarios> getAll() {
        return repository.findAll(Sort.by("nombre").ascending());
    }

    @Override
    public Usuarios getByUsuario(String usuario) {
        return repository.getByUsuario(usuario);
    }

    @Override
    public UsuariosResponseDTO getByUsuarioOrCorreo(String usuario, String correo) {
        UsuariosResponseDTO responseDTO = new UsuariosResponseDTO();

        Usuarios usuarioObj = repository.getByUsuarioOrCorreo(usuario, correo);

        if (null == usuarioObj) {
            responseDTO.setRespuesta("Usuario no existe");
            responseDTO.setError(true);
        } else {
            responseDTO.setError(false);
            responseDTO.setUsuario(usuarioObj);
        }

        return responseDTO;
    }

    @Override
    public UsuariosResponseDTO getByInfoUsuarioOrCorreo(String usuario, String correo) {
        return repository.getInfoByUsuarioOrCorreo(usuario, correo);
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

        usuario = repository.getByUsuarioOrCorreo(requestDTO.getUsuario(), requestDTO.getCorreo());
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
        }

        Date fecha = new Date();
        codigoConfirmacion = new CodigoConfirmacion(null, MetodosUtils.generarCodigo(), "R", usuario.getId(), fecha);
        codigoConfirmacionRepository.save(codigoConfirmacion);
        MailUtils.sendEmail("R", codigoConfirmacion.getCodigo(), usuario.getUsuario(), "", usuario.getNombres()+", "+usuario.getApellidos(),
                "", "", fecha);


        return responseDTO;
    }

    @Override
    public UsuariosResponseDTO generarCodigoConfirmarUsuarioPrincipal(UsuariosRequestDTO requestDTO) {

        UsuariosResponseDTO responseDTO = new UsuariosResponseDTO();

        Usuarios usuario = null;

        usuario = repository.getByUsuarioOrCorreo(requestDTO.getUsuario(), requestDTO.getCorreo());
        Pageable pageable = PageRequest.of(0, 1);
        CodigoConfirmacion codigoConfirmacion = codigoConfirmacionRepository.getByUsuarioIdAndTipo(usuario.getId(),"P");
        responseDTO.setError(false);
        responseDTO.setUsuario(usuario);

        if (null == usuario) {
            responseDTO.setRespuesta("Usuario no existe");
            responseDTO.setError(true);
        }

        if (null !=  codigoConfirmacion) {
            codigoConfirmacionRepository.delete(codigoConfirmacion);
        }
        Date fecha = new Date();
        codigoConfirmacion = new CodigoConfirmacion(null, MetodosUtils.generarCodigo(), "P", usuario.getId(), fecha);
        codigoConfirmacionRepository.save(codigoConfirmacion);
        MailUtils.sendEmail("P", codigoConfirmacion.getCodigo(), usuario.getUsuario(), "", usuario.getNombres()+", "+usuario.getApellidos(),
                "", "", fecha);


        return responseDTO;
    }

    @Override
    public UsuariosResponseDTO actualizarContrasenia(UsuariosRequestDTO requestDTO) {

        UsuariosResponseDTO responseDTO = new UsuariosResponseDTO();

        Usuarios usuario = null;

        usuario = repository.getByUsuarioOrCorreo(requestDTO.getUsuario(), requestDTO.getCorreo());

        CodigoConfirmacion codigoConfirmacionInformativo = codigoConfirmacionRepository.getByUsuarioIdAndTipo(
                usuario.getId(), requestDTO.getTipoRecuperacion());

        CodigoConfirmacion codigoConfirmacion = codigoConfirmacionRepository.getByCodigoAndUsuarioIdAndTipo(requestDTO.getCodigo(),
                usuario.getId(), requestDTO.getTipoRecuperacion());

        if (null == codigoConfirmacion && null != codigoConfirmacionInformativo) {
            responseDTO.setError(true);
            String fecha = MetodosUtils.getFechaStringByFormat(codigoConfirmacionInformativo.getFecha(), "dd/MM/yyyy HH:mm:ss");
            responseDTO.setRespuesta("Código de confirmación inválido, favor ingresar el código enviado con fecha y hora " + fecha);
            return responseDTO;
        } else if (null != codigoConfirmacion) {
            codigoConfirmacionRepository.delete(codigoConfirmacion);
            responseDTO.setRespuesta("Código confirmado");
            responseDTO.setError(true);
            responseDTO.setConfirmado(true);
            return responseDTO;
        }

        if (null != usuario) {
            usuario.setContrasenia(requestDTO.getContrasenia());
            responseDTO.setRespuesta("Contraseña reestablecida");
            responseDTO.setError(true);
            responseDTO.setConfirmado(true);
            repository.save(usuario);
            return responseDTO;
        }

        return null;
    }

    @Override
    public Page<Usuarios> getByPage(UsuariosRequestDTO request) {
        List<Sort.Order> list = new ArrayList<Sort.Order>();
        list.add(Sort.Order.asc("nombres"));
        list.add(Sort.Order.asc("usuario"));
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by(list).ascending());

        Page<Usuarios> response = repository.getByPage(request.getUsuarios().getId(), request.getUsuarios().getId() == null ? 0l : request.getUsuarios().getId(),
                request.getUsuarios().getNombres(), request.getUsuarios().getNombres() == null ? "" : request.getUsuarios().getNombres(),
                request.getUsuarios().getApellidos(), request.getUsuarios().getApellidos() == null ? "" : request.getUsuarios().getApellidos(),
                request.getUsuarios().getUsuario(), request.getUsuarios().getUsuario() == null ? "" : request.getUsuarios().getUsuario(),
                request.getUsuarios().getCorreo(), request.getUsuarios().getCorreo() == null ? "" : request.getUsuarios().getCorreo(),
                request.getUsuarios().getSucursal() == null ? null : null, request.getUsuarios().getSucursal() == null ? 0l : request.getUsuarios().getSucursal().getId(),
                request.getUsuarios().getPrincipal(), request.getUsuarios().getPrincipal() == null ? "" : request.getUsuarios().getPrincipal(),
                request.getUsuarios().getTelefono(), request.getUsuarios().getTelefono() == null ? "" : request.getUsuarios().getTelefono(),
                pageable);

        return response;
    }
}
