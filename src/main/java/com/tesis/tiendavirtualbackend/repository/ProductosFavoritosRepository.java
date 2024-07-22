package com.tesis.tiendavirtualbackend.repository;

import com.tesis.tiendavirtualbackend.bo.ProductosFavoritos;
import com.tesis.tiendavirtualbackend.bo.ProductosFavoritos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductosFavoritosRepository extends JpaRepository<ProductosFavoritos, Long> {

    ProductosFavoritos getById(Long id);

}
