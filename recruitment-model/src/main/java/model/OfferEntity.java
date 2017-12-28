package model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by chetan on 27.12.2017.
 */
@Entity
@Table(name = "offer")
@Data
@EqualsAndHashCode
@SequenceGenerator(name = "seq_offer", sequenceName = "seq_offer")
public class OfferEntity {

    @Id
    @GeneratedValue(generator = "seq_offer")
    private Long id;

    private UUID offerUuid;

    private String jobTitle;

    private Date startDate;

    private Integer noOfApplications;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "offer")
    private List<ApplicationEntity> applicationEntities;
}
