package com.reliablesystems.doctoroffice.core.bridge;

import com.reliablesystems.doctoroffice.core.service.company.CompanyService;
import com.reliablesystems.doctoroffice.core.service.user.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BusinessSpringContextBridge implements BusinessSpringContextBridgeService, ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BusinessSpringContextBridge.applicationContext = applicationContext;
    }

    /**
     * Get current object {@link BusinessSpringContextBridgeService} configures to services access
     *
     * @return The object {@link BusinessSpringContextBridgeService} for bridge
     */
    public static BusinessSpringContextBridgeService services() {
        return applicationContext.getBean(BusinessSpringContextBridgeService.class);
    }

    /**
     * Get instans of {@link UserService} in spring
     *
     * @return El {@link UserService}
     */
    @Override
    public UserService getUserService() {
        return userService;
    }

    /**
     * Get instans of {@link CompanyService} in spring
     *
     * @return El {@link CompanyService}
     */
    @Override
    public CompanyService getCompanyService() {
        return companyService;
    }
}
