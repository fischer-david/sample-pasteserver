package at.fischi.paste.pasteserver.dtos;

import java.util.Date;
import java.util.UUID;

public record PasteDto(
        String value,
        Date creation
) {
}
