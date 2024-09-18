package com.tesis.tiendavirtualbackend.impl;

import com.tesis.tiendavirtualbackend.bo.Productos;
import com.tesis.tiendavirtualbackend.bo.ProductosFavoritos;
import com.tesis.tiendavirtualbackend.dto.ProductosFavoritosResponseDTO;
import com.tesis.tiendavirtualbackend.repository.ProductosFavoritosRepository;
import com.tesis.tiendavirtualbackend.service.ProductosFavoritosService;
import com.tesis.tiendavirtualbackend.utils.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

@Service
public class ProductosFavoritosServiceImpl implements ProductosFavoritosService {

    private static final String FILE_DIR = "/var/www/html/images";

    @Autowired
    private ProductosFavoritosRepository repository;

    @Override
    public ProductosFavoritosResponseDTO save(ProductosFavoritos obj, String type) {
        ProductosFavoritosResponseDTO responseDTO = new ProductosFavoritosResponseDTO();
        try {
            repository.save(obj);
            responseDTO.setError(false);
            responseDTO.setMensaje("Registro "+type+" con éxito");
        } catch (DataIntegrityViolationException ex){
            HashMap<String, String> mapExcepciones = new HashMap<String, String>();
            mapExcepciones.put("productos_favoritos.producto_id_UNIQUE", "producto");
            mapExcepciones.put("productos_favoritos.orden_UNIQUE", "orden");
            String exception = GlobalExceptionHandler.handleDataIntegrityViolationException(ex, mapExcepciones);
            responseDTO.setError(true);
            responseDTO.setMensaje(exception);
        }

        return responseDTO;
    }

    @Override
    public ProductosFavoritosResponseDTO delete(Long id) {
        ProductosFavoritosResponseDTO responseDTO = new ProductosFavoritosResponseDTO();

        ProductosFavoritos obj = repository.getById(id);
        if (obj != null) {
            try {
                repository.delete(obj);
                responseDTO.setError(false);
                responseDTO.setMensaje("Registro eliminado con éxito");
            } catch (DataIntegrityViolationException ex){
                HashMap<String, String> mapExcepciones = new HashMap<String, String>();
                String exception = GlobalExceptionHandler.handleDataIntegrityViolationException(ex, mapExcepciones);
                responseDTO.setError(true);
                responseDTO.setMensaje(exception);
            }
        }

        return responseDTO;
    }

    @Override
    public List<ProductosFavoritos> getAll() {
        List<ProductosFavoritos> lst = repository.findAll(Sort.by("orden").ascending());
        if (lst != null && lst.size() > 0){
            for (ProductosFavoritos obj : lst){
                Path filePath = Paths.get(FILE_DIR).resolve("file"+obj.getProducto().getId()).normalize();
                try {
                    obj.getProducto().setImagen(Files.readAllBytes(filePath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return lst;
    }

}
