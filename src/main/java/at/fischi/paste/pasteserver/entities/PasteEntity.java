package at.fischi.paste.pasteserver.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class PasteEntity {

    @Id
    @Column(unique = true, length = 36)
    private String uniqueId;

    @Setter
    @Column(columnDefinition = "longtext")
    private String value;

    @Setter
    @Column(columnDefinition = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;

    @Setter
    private boolean archived = false;

    public void setUniqueId(UUID uniqueId) {
        this.uniqueId = uniqueId.toString();
    }
}
