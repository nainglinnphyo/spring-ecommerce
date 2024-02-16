package com.paint.painting.repository;

import com.paint.painting.entity.Artist;
import com.paint.painting.entity.Paint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArtistRepository extends CrudRepository<Artist, Long> {
    List<Artist> findByName(String name);
}