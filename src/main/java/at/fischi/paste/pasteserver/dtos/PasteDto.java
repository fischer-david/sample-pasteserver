package at.fischi.paste.pasteserver.dtos;

import java.util.Date;

public record PasteDto(
        String value,
        Date creation
) {
}
