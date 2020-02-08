package com.srans.nestserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Amenity;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long> {

}
