package com.tesis.tiendavirtualbackend.impl;

import com.tesis.tiendavirtualbackend.bo.Pedidos;
import com.tesis.tiendavirtualbackend.bo.Productos;
import com.tesis.tiendavirtualbackend.dto.PedidosResponseDTO;
import com.tesis.tiendavirtualbackend.dto.ProductosRequestDTO;
import com.tesis.tiendavirtualbackend.dto.ProductosResponseDTO;
import com.tesis.tiendavirtualbackend.repository.PedidosRepository;
import com.tesis.tiendavirtualbackend.repository.ProductosRepository;
import com.tesis.tiendavirtualbackend.service.ProductosService;
import com.tesis.tiendavirtualbackend.utils.GlobalExceptionHandler;
import com.tesis.tiendavirtualbackend.utils.MetodosUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

@Service
public class ProductosServiceImpl implements ProductosService {

    private static final String FILE_DIR = "/var/www/html/images";

    @Autowired
    private ProductosRepository repository;

    @Override
    public Productos getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public Page<Productos> getByPage(ProductosRequestDTO request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("nombre").ascending());
        Page<Productos> response = repository.getByPage(request.getProducto().getId(), request.getProducto().getId() == null ? 0l : request.getProducto().getId(),
                request.getProducto().getNombre(), request.getProducto().getNombre() == null ? "" : request.getProducto().getNombre(),
                request.getProducto().getRangoPrecioInicio(), request.getProducto().getRangoPrecioInicio() == null ? 0d : request.getProducto().getRangoPrecioInicio(),
                request.getProducto().getRangoPrecioFin(), request.getProducto().getRangoPrecioFin() == null ? 0d : request.getProducto().getRangoPrecioFin(),
                request.getProducto().getTipoProducto() != null ? request.getProducto().getTipoProducto().getId() : null, request.getProducto().getTipoProducto() == null ? null : request.getProducto().getTipoProducto().getId(),
                request.getProducto().getEstado(), request.getProducto().getEstado() == null ? "" : request.getProducto().getEstado(),
                pageable);

        if (response != null && response.getContent() != null && response.getContent().size() > 0){
            for (Productos obj: response.getContent()){
                Path filePath = Paths.get(FILE_DIR).resolve("file"+obj.getId()).normalize();
                try {
                    obj.setImagen(Files.readAllBytes(filePath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return response;
    }

    @Override
    public Page<Productos> getByFilter(ProductosRequestDTO request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by("nombre").ascending());
        Page<Productos> response = repository.getByFilters(
                request.getProducto().getNombre(), request.getProducto().getNombre() == null ? "" : request.getProducto().getNombre(),
                request.getProducto().getNombre(), request.getProducto().getNombre() == null ? "" : request.getProducto().getNombre(),
                request.getProducto().getEstado(),
                request.getProducto().getTipoProducto() != null ? request.getProducto().getTipoProducto().getId() : null, request.getProducto().getTipoProducto() == null ? 0l : request.getProducto().getTipoProducto().getId(),
                pageable);

        if (response != null && response.getContent() != null && response.getContent().size() > 0){
            for (Productos obj: response.getContent()){

                Path filePath = Paths.get(FILE_DIR).resolve("file"+obj.getId()).normalize();
                try {
                    obj.setImagen(Files.readAllBytes(filePath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return response;
    }

    @Override
    public ProductosResponseDTO save(Productos obj, String type) {
        ProductosResponseDTO responseDTO = new ProductosResponseDTO();
        try {

            byte[] decodedBytes = MetodosUtils.decodeBase64String(obj.getImageSrc());

            File directory = new File(FILE_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File fileToSave = new File(FILE_DIR + "/file" + obj.getId());
            try (FileOutputStream fos = new FileOutputStream(fileToSave)) {
                fos.write(decodedBytes);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

//            obj.setImagen();
            repository.save(obj);
            responseDTO.setError(false);
            responseDTO.setMensaje("Registro "+type+" con éxito");
        } catch (DataIntegrityViolationException ex){
            HashMap<String, String> mapExcepciones = new HashMap<String, String>();
            mapExcepciones.put("productos.nombre_UNIQUE", "nombre");
            String exception = GlobalExceptionHandler.handleDataIntegrityViolationException(ex, mapExcepciones);
            responseDTO.setError(true);
            responseDTO.setMensaje(exception);
        }

        return responseDTO;
    }

    @Override
    public ProductosResponseDTO delete(Long id) {
        ProductosResponseDTO responseDTO = new ProductosResponseDTO();

        Productos producto = repository.findInProductosFavoritos(id);
        if (producto != null){
            responseDTO.setError(true);
            responseDTO.setMensaje("Error, no se puede inactivar el registro seleccionado ya que se encuentra asociado a los productos muestra.");
        } else {
            Productos obj = getById(id);
            if (obj != null){
                obj.setEstado("I");
                repository.save(obj);
                responseDTO.setError(false);
                responseDTO.setMensaje("El producto se ha inactivado");
            }
        }
//        Productos obj = getById(id);
//        if (obj != null) {
//            try {
//                repository.delete(obj);
//                responseDTO.setError(false);
//                responseDTO.setMensaje("Registro eliminado con éxito");
//            } catch (DataIntegrityViolationException ex){
//                HashMap<String, String> mapExcepciones = new HashMap<String, String>();
//                mapExcepciones.put("fk_usuarios_sucursal_id", "un usuario");
//                mapExcepciones.put("fk_productos_favoritos_producto_id", "a los productos iniciales de tienda");
//                String exception = GlobalExceptionHandler.handleDataIntegrityViolationException(ex, mapExcepciones);
//                responseDTO.setError(true);
//                responseDTO.setMensaje(exception);
//            }
//        }

        return responseDTO;
    }

    @Override
    public ProductosResponseDTO activar(Long id) {
        ProductosResponseDTO responseDTO = new ProductosResponseDTO();

        Productos obj = getById(id);
        if (obj != null){
            obj.setEstado("A");
            repository.save(obj);
            responseDTO.setError(false);
            responseDTO.setMensaje("El producto se ha activado");
        }

        return responseDTO;
    }



    @Override
    public List<Productos> getAll() {
        return repository.getByEstado("A" ,Sort.by("nombre").ascending());
    }

}
