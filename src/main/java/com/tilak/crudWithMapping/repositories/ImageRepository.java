package com.tilak.crudWithMapping.repositories;


import com.tilak.crudWithMapping.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}