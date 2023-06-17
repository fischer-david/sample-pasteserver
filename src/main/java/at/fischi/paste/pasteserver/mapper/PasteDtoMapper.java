package at.fischi.paste.pasteserver.mapper;

import at.fischi.paste.pasteserver.dtos.PasteDto;
import at.fischi.paste.pasteserver.entities.PasteEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PasteDtoMapper implements Function<PasteEntity, PasteDto> {

    @Override
    public PasteDto apply(PasteEntity pasteEntity) {
        return new PasteDto(
                pasteEntity.isArchived() ?
                        "Paste is archived :(" :
                        pasteEntity.getValue(),
                pasteEntity.getCreation()
        );
    }
}