package de.jankahle.capstone.db;

import de.jankahle.capstone.model.RecipeDao;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RecipeDB extends PagingAndSortingRepository<RecipeDao, String> {

    List<RecipeDao> findAll();
}
