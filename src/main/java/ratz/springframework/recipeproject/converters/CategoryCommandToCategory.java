package ratz.springframework.recipeproject.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ratz.springframework.recipeproject.commands.CategoryCommand;
import ratz.springframework.recipeproject.domain.Category;


@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand , Category> {

    // sync for thread safe
    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand source) {
        if (source == null) {
            return null;
        }

        final Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());
        return category;
    }
}

