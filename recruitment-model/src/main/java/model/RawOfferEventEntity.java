package model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by chetan on 28.12.2017.
 */
@Entity
@Table(name = "offer_event_store")
@Data
@SequenceGenerator(name = "seq_offer_event_store", sequenceName = "seq_offer_event_store")
public class RawOfferEventEntity {

    @Id
    @GeneratedValue(generator = "seq_offer_event_store")
    private Long id;

    private UUID offerUuid;

    private String name;

    private String payload;
}
