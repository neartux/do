package com.reliablesystems.doctoroffice.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "createdat")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @JoinColumn(name = "statusid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Status status;
    @JoinColumn(name = "personaldataid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PersonalData personalData;
    @JoinColumn(name = "locationdataid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private LocationData locationData;
    @JoinColumn(name = "billingdataid", referencedColumnName = "id")
    @ManyToOne
    private BillingData billingData;
    @JsonIgnore
    @OneToMany( mappedBy = "company")
    private Collection<User> userCollection;

    public Company(long id) { this.id = id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", status=" + status +
                ", personalData=" + personalData +
                ", locationData=" + locationData +
                ", billingData=" + billingData +
                '}';
    }
}
