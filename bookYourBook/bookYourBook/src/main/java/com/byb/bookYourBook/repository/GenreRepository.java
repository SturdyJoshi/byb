package com.byb.bookYourBook.repository;

import com.byb.bookYourBook.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer>{
}
