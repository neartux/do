package com.reliablesystems.doctoroffice.core.to.doctorsoffice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorsOfficeTO {
    private Long id;
    private Long personalDataId;
    private Long locationDataId;
    private String description;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String birthDateS;
    private String sex;
    private String address;
    private String zipCode;
    private String phone;
    private String cellPhone;
    private String email;
    private Long doctorId;
    private Long companyId;

    @Override
    public String toString() {
        return "DoctorsOfficeTO{" +
                "id=" + id +
                ", personalDataId=" + personalDataId +
                ", locationDataId=" + locationDataId +
                ", description='" + description + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", birthDateS='" + birthDateS + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phone='" + phone + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", email='" + email + '\'' +
                ", companyId=" + companyId +
                '}';
    }
}
