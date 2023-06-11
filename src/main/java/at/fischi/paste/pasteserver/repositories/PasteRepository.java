package at.fischi.paste.pasteserver.repositories;

import at.fischi.paste.pasteserver.entities.PasteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasteRepository extends JpaRepository<PasteEntity, String> {

    PasteEntity findPasteEntityByUniqueId(String uniqueId);

}