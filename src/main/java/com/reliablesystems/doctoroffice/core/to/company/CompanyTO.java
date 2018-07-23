package com.reliablesystems.doctoroffice.core.to.company;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyTO {
    private Long id;
    private Long statusId;
    private Long personalDataId;
    private Long locationDataId;
    private Long billingDataId;
    private String firstName;
    private String lastName;
    private String description;
    private String address;
    private String cellPhone;
    private String phone;
    private String email;

    @Override
    public String toString() {
        return "CompanyTO{" +
                "id=" + id +
                ", statusId=" + statusId +
                ", personalDataId=" + personalDataId +
                ", locationDataId=" + locationDataId +
                ", billingDataId=" + billingDataId +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
