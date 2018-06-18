package uk.co.datadisk.ddflix.entities.user;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "user", callSuper = false)
@ToString(exclude = "user")
@Entity
public class UserImages {

    @Id
    private Long id;

    @MapsId("id")
    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    @Lob
    private Byte[] profileImage;

    @Lob
    private Byte[] otherImage;
}
