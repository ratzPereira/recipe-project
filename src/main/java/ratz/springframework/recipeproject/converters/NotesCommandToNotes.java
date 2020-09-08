package ratz.springframework.recipeproject.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ratz.springframework.recipeproject.commands.NotesCommand;
import ratz.springframework.recipeproject.domain.Notes;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {


    // sync for thread safe
    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand source) {
        if(source == null) {
            return null;
        }

        final Notes notes = new Notes();
        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNotes());
        return notes;
    }
}
