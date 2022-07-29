package com.videostreamer.repository;

import com.videostreamer.model.Video;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface VideoRepository extends CrudRepository<Video, UUID> {

    Optional<Video> findByName(String name);

    boolean existsByName(String name);

    @Query("SELECT v.name FROM Video v")
    Set<String> findAllNames();
}
