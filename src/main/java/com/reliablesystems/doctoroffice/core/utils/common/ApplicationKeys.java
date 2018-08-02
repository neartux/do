package com.reliablesystems.doctoroffice.core.utils.common;

public final class ApplicationKeys {

    private ApplicationKeys() {}

    public static final String SEX_FEMALE = "FEMALE";
    public static final String SEX_MALE = "MALE";
    public static final String DEFAULT_PATERN = "dd/MM/yyyy";
    public static final String PATH_SERVER_FILES = System.getProperty("user.dir") + "/../standalone";
    public static final String PATH_AVATAR_SERVER_IMAGE = System.getProperty("user.dir") + "/../standalone/files/images/no-avatar.png";
    public static final String[] ARRAY_AVAILABLE_IMAGES_PROFILE = {"jpg", "jpeg", "png"};
    public static final String FOLDER_FILES = "files";
    public static final String FOLDER_PATIENT = "patient-";
    public static final String FOLDER_DOCTOR = "doctor-";
    public static final Long MAX_SIZE_FILE = 100000L;
    public static final String FOLDER_PATIENT_PROFILE_PICTURE = "profilepicture";
    public static final String FOLDER_DOCTOR_PROFILE_PICTURE = "profilepicture";
    public static final Long ROLE_GENERIC_ID = 1L;
    public static final Long ROLE_SUPERUSER_ID = 2L;
    public static final Long ROLE_ADMINISTRATOR_ID = 3L;
    public static final Long ROLE_DOCTOR_ID = 4L;
    public static final String DATA_CURRENT_USER = "_DATA_CURRENT_USER";
    public static final String DATA_CURRENT_COMPANY = "_DATA_CURRENT_COMPANY";
    public static final Long EVENT_TYPE_DATE=1L;
    public static final Long EVENT_TYPE_ABSENCE=2L;
    public static final Long EVENT_TYPE_BREAK=3L;
}
