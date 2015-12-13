package us.juggl.twentyfifteen.december.jpa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dphillips on 12/12/15.
 */
@Entity
@Table(name="people", schema="public", uniqueConstraints = {@UniqueConstraint(columnNames = {"givenname", "familyname"})})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(fluent = true, chain = true)
public class Person implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "givenname")
    private String givenName;

    @Column(name = "familyname")
    private String familyName;

    private Integer age;
}
