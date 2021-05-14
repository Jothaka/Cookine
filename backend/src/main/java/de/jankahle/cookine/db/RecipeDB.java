package de.jankahle.cookine.db;

import de.jankahle.cookine.model.RecipeDao;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RecipeDB extends PagingAndSortingRepository<RecipeDao, String> {

    List<RecipeDao> findAll();
}
