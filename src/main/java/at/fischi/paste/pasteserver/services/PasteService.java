package at.fischi.paste.pasteserver.services;

import at.fischi.paste.pasteserver.dtos.PasteDto;
import at.fischi.paste.pasteserver.entities.PasteEntity;
import at.fischi.paste.pasteserver.mapper.PasteDtoMapper;
import at.fischi.paste.pasteserver.repositories.PasteRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasteService {
    private final PasteRepository pasteRepository;
    private final PasteDtoMapper pasteDtoMapper;

    @Autowired
    public PasteService(PasteRepository pasteRepository, PasteDtoMapper pasteDtoMapper) {
        this.pasteRepository = pasteRepository;
        this.pasteDtoMapper = pasteDtoMapper;
    }

    public UUID getUnusedUniqueId() {
        UUID uniqueId = UUID.randomUUID();
        if (this.isUniqueIdUsed(uniqueId.toString())) {
            this.getUnusedUniqueId();
        }

        return uniqueId;
    }

    private boolean isUniqueIdUsed(String uniqueId) {
        return this.getPaste(uniqueId).isPresent();
    }

    public UUID createPaste(String value) {
        PasteEntity pasteEntity = new PasteEntity();
        pasteEntity.setUniqueId(this.getUnusedUniqueId());
        pasteEntity.setValue(value);
        pasteEntity.setCreation(new Date());

        return UUID.fromString(this.pasteRepository.save(pasteEntity).getUniqueId());
    }

    @SneakyThrows
    public Optional<PasteDto> archivePaste(String uniqueId) {
        Optional<PasteEntity> pasteEntity = this.getPasteEntity(uniqueId);
        pasteEntity.ifPresentOrElse(x -> {
            x.setArchived(true);
            this.pasteRepository.delete(x);
            this.pasteRepository.save(x);
        }, () -> {
            throw new RuntimeException(
                    String.format("Paste with uniqueId %s does not exist", uniqueId)
            );
        });

        return this.getPaste(uniqueId);
    }

    private Optional<PasteEntity> getPasteEntity(String uniqueId) {
        try {
            return Optional.of(this.pasteRepository.findPasteEntityByUniqueId(uniqueId));
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    @SneakyThrows
    public Optional<PasteDto> getPaste(String uniqueId) {
        Optional<PasteEntity> pasteEntity = this.getPasteEntity(uniqueId);
        return pasteEntity.map(this.pasteDtoMapper);
    }
}