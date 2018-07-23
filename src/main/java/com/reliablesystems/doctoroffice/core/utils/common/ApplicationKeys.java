package com.reliablesystems.doctoroffice.core.utils.common;

public final class ApplicationKeys {

    private ApplicationKeys() {}

    public static final String SEX_FEMALE = "FEMALE";
    public static final String SEX_MALE = "MALE";
    public static final String DEFAULT_PATERN = "dd/MM/yyyy";
    public static final String PATH_SERVER_FILES = System.getProperty("user.dir") + "/../standalone";
    public static final String[] ARRAY_AVAILABLE_IMAGES_PROFILE = {"jpg", "jpeg", "png"};
    public static final String FOLDER_FILES = "files";
    public static final String FOLDER_PATIENT = "patient-";
    public static final String FOLDER_PATIENT_PROFILE_PICTURE = "profilepicture";
    public static final Long ROLE_GENERIC_ID = 1L;
    public static final Long ROLE_SUPERUSER_ID = 2L;
    public static final Long ROLE_ADMINISTRATOR_ID = 3L;
    public static final Long ROLE_DOCTOR_ID = 4L;
}
