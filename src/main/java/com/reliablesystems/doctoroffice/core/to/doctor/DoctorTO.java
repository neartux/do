package com.reliablesystems.doctoroffice.core.to.doctor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorTO {
    private Long id;
    private Long personalDataId;
    private Long locationDataId;
    private String professionalCard;
    private String firstName;
    private String lastName;
    private String profileImage;
    private Date birthDate;
    private String birthDateS;
    private String sex;
    private String address;
    private String zipCode;
    private String phone;
    private String cellPhone;
    private String email;
    private Long companyId;
    private Long userId;
    private String userName;
    private String password;
    private String pattern;

    @Override
    public String toString() {
        return "DoctorTO{" +
                "id=" + id +
                ", personalDataId=" + personalDataId +
                ", locationDataId=" + locationDataId +
                ", professionalCard='" + professionalCard + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", birthDate=" + birthDate +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phone='" + phone + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", email='" + email + '\'' +
                ", pattern='" + pattern + '\'' +
                '}';
    }
}
