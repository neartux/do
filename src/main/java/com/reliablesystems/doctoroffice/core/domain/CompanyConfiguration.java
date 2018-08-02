package com.reliablesystems.doctoroffice.core.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "companyconfiguration")
public class CompanyConfiguration implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "officesnumber")
    private Integer officessNumer;
    @JoinColumn(name = "companyid", nullable = false)
    @OneToOne(optional = false)
    private Company company;

    @Override
    public String toString() {
        return "CompanyConfiguration{" +
                "id=" + id +
                ", officessNumer=" + officessNumer +
                '}';
    }
}
