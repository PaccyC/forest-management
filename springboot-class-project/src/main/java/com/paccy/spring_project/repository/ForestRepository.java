package com.paccy.spring_project.repository;

import com.paccy.spring_project.entities.Forest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ForestRepository extends JpaRepository<Forest, Long> {

    @Query(value = "SELECT * FROM forest WHERE name = :name AND location = :location", nativeQuery = true)
    Optional<Forest> findByNameAndLocation(@Param("name") String name, @Param("location") String location);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM forest WHERE name = :name", nativeQuery = true)
    void deleteByName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "UPDATE forest SET name = :newName, description = :description, location = :location, area = :area WHERE name = :name", nativeQuery = true)
    void updateByName(@Param("name") String name, @Param("newName") String newName, @Param("description") String description, @Param("location") String location, @Param("area") int area);
}
