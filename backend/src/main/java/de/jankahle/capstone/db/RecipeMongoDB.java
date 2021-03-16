package de.jankahle.capstone.db;

import de.jankahle.capstone.model.DBRecipe;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RecipeMongoDB extends PagingAndSortingRepository<DBRecipe, String> {

    List<DBRecipe> findAll();
}
