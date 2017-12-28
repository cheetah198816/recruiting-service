package model;

import enums.ApplicationStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by chetan on 27.12.2017.
 */
@Entity
@Table(name = "application")
@Data
@EqualsAndHashCode(exclude = "offer")
@SequenceGenerator(name = "seq_application", sequenceName = "seq_application")
public class ApplicationEntity {

    @Id
    @GeneratedValue(generator = "seq_application")
    private Long id;

    private UUID applicationUuid;

    private String emailId;

    private String resume;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus currentApplicationStatus;

    @ManyToOne
    private OfferEntity offer;
}
