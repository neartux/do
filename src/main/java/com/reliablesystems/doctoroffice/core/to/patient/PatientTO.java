package com.reliablesystems.doctoroffice.core.to.patient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientTO {
    private Long id;
    private String expedient;
    private String profileImage;
    private Long statusId;
    private Long personalDataId;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String birthDateS;
    private String identifier;
    private String sex;
    private String civilStatus;
    private Long bloodTypeId;
    private String bloodType;
    private Long locationDataId;
    private String address;
    private String zipCode;
    private String cellPhone;
    private String phone;
    private String email;
    private Long cityId;
    private String city;
    private Long companyId;

    @Override
    public String toString() {
        return "PatientTO{" +
                "id=" + id +
                ", expedient='" + expedient + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", statusId=" + statusId +
                ", personalDataId=" + personalDataId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", identifier='" + identifier + '\'' +
                ", sex='" + sex + '\'' +
                ", civilStatus='" + civilStatus + '\'' +
                ", bloodTypeId=" + bloodTypeId +
                ", bloodType='" + bloodType + '\'' +
                ", locationDataId=" + locationDataId +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", cityId=" + cityId +
                ", city='" + city + '\'' +
                '}';
    }
}
