package model;

import enums.ApplicationStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created by chetan on 27.12.2017.
 */

/**
 * This event store can be kept in a seperate database which will keep track of the application status.
 */
@Entity
@Table(name = "application_event_store")
@Data
@SequenceGenerator(name = "seq_application_event_store", sequenceName = "seq_application_event_store")
public class RawApplicationEventEntity {

    @Id
    @GeneratedValue(generator = "seq_application_event_store")
    private Long id;

    private UUID offerUuid;

    private UUID applicationUuid;

    private Date creationDate;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    private String name;

    private String payload;
}
