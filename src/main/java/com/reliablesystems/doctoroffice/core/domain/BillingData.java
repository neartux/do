package com.reliablesystems.doctoroffice.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "billingdata")
public class BillingData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "rfc")
    private String rfc;
    @Column(name = "businessname")
    private String businessName;
    @Column(name = "expeditionplace")
    private String expeditionPlace;
    @Column(name = "ciec")
    private String ciec;
    @JoinColumn(name = "statusid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Status status;
    @JoinColumn(name = "usecfdiid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UseCfdi useCfdi;
    @JoinColumn(name = "fiscalregimeid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private FiscalRegime fiscalRegime;
    @JsonIgnore
    @OneToMany( mappedBy = "billingData")
    private Collection<Company> companyCollection;

    public BillingData(long id) { this.id = id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillingData that = (BillingData) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BillingData{" +
                "id=" + id +
                ", rfc='" + rfc + '\'' +
                ", businessName='" + businessName + '\'' +
                ", expeditionPlace='" + expeditionPlace + '\'' +
                ", ciec='" + ciec + '\'' +
                ", status=" + status +
                ", useCfdi=" + useCfdi +
                ", fiscalRegime=" + fiscalRegime +
                '}';
    }
}
