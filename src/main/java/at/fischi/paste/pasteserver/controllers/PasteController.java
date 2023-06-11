package at.fischi.paste.pasteserver.controllers;

import at.fischi.paste.pasteserver.dtos.PasteDto;
import at.fischi.paste.pasteserver.services.PasteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/paste")
public class PasteController {
    private final PasteService pasteService;

    @Autowired
    public PasteController(PasteService pasteService) {
        this.pasteService = pasteService;
    }

    @PostMapping()
    public UUID createPaste(String value) {
        if(value == null) {
            return null;
        }

        return this.pasteService.createPaste(value);
    }

    @GetMapping()
    public PasteDto getPasteByUniqueId(String uniqueId) {
        return this.pasteService.getPaste(uniqueId).orElse(null);
    }
}