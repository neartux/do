package com.reliablesystems.doctoroffice.core.utils.user;

import com.reliablesystems.doctoroffice.core.domain.LocationData;
import com.reliablesystems.doctoroffice.core.domain.PersonalData;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class UserUtil {
    private UserUtil() {}

    /**
     * Get new {@link PersonalData}
     *
     * @param personalData Data
     * @return New instance
     */
    public static PersonalData getNewPersonalData(PersonalData personalData) {
        PersonalData personalData1 = new PersonalData();
        personalData1.setFirstName(personalData.getFirstName());
        personalData1.setLastName(personalData.getLastName());
        return personalData1;
    }

    /**
     * Get new {@link LocationData}
     *
     * @param locationData Data
     * @return New instance
     */
    public static LocationData getNewLocationData(LocationData locationData) {
        LocationData locationData1 = new LocationData();
        locationData1.setAddress(locationData.getAddress());
        locationData1.setPhone(locationData.getPhone());
        locationData1.setCellPhone(locationData.getCellPhone());
        locationData1.setEmail(locationData.getEmail());
        return locationData1;
    }

    public static String digestMD5(String password) {
        String passwordEncriptado = null;
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            int digestLength = messageDigest.getDigestLength();
            byte[] encriptadoMD5 = messageDigest.digest(password.getBytes());
            passwordEncriptado = toHex(encriptadoMD5);
        } catch (NoSuchAlgorithmException var5) {
            var5.printStackTrace();
        }

        return passwordEncriptado;
    }

    private static String toHex(byte[] hash) {
        StringBuilder stringBuilder = new StringBuilder(hash.length * 2);
        byte[] arr$ = hash;
        int len$ = hash.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            byte aHash = arr$[i$];
            int intVal = aHash & 255;
            if (intVal < 16) {
                stringBuilder.append("0");
            }

            stringBuilder.append(Integer.toHexString(intVal));
        }

        return stringBuilder.toString();
    }
}
