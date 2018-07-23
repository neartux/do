package com.reliablesystems.doctoroffice.core.utils.company;

import com.reliablesystems.doctoroffice.core.domain.Company;
import com.reliablesystems.doctoroffice.core.domain.LocationData;
import com.reliablesystems.doctoroffice.core.domain.PersonalData;
import com.reliablesystems.doctoroffice.core.to.company.CompanyTO;

public final class CompanyUtil {
    private CompanyUtil() {}

    /**
     * Method to convert a {@link CompanyTO} to {@link Company} domain
     *
     * @param companyTO Data company
     * @return A Domain of {@link Company}
     */
    public static Company getCompanyToCreate(CompanyTO companyTO) {
        PersonalData personalData = new PersonalData();

        LocationData locationData = new LocationData();
        locationData.setAddress(companyTO.getAddress());
        locationData.setPhone(companyTO.getPhone());
        locationData.setCellPhone(companyTO.getCellPhone());
        locationData.setEmail(companyTO.getEmail());

        Company company = new Company();
        company.setPersonalData(personalData);
        company.setLocationData(locationData);
        company.setDescription(companyTO.getDescription());

        return company;
    }

    /**
     * Method to get {@link Company} updated
     *
     * @param companyTO Company data
     * @param company Companny to update
     * @return Company updated
     */
    public static Company getCompanyToUpdate(CompanyTO companyTO, Company company) {
        company.getLocationData().setAddress(companyTO.getAddress());
        company.getLocationData().setPhone(companyTO.getPhone());
        company.getLocationData().setCellPhone(companyTO.getCellPhone());
        company.getLocationData().setEmail(companyTO.getEmail());

        company.setDescription(companyTO.getDescription());

        return company;
    }
}
