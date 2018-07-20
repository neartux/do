package com.reliablesystems.doctoroffice.core.utils.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class StringUtil {
    public static final String EMPTY_SPACE = " ";
    public static final String EMPTY = "";
    public static final String COMMA = ",";
    public static final String DASH_ = "_";
    public static final String SLASH = "/";
    public static final String DOT = ".";
    public static final String STRING_ACCENTED = "áÁéÉíÍóÓúÚñÑ";
    public static final String STRING_UNACCENTED = "aAeEiIoOuUnN";
    public static final String STRING_ACCENTED_NO_N = "áÁéÉíÍóÓúÚ";
    public static final String STRING_UNACCENTED_NO_N = "aAeEiIoOuU";
    public static final String ZERO_STRING = "0";
    public static final String NO = "NO";
    public static final String YES = "YES";
    public static final String OPEN_BRACKET = "(";
    public static final String CLOSE_BRACKET = ")";

    private StringUtil() {
    }

    public static String convertListLongToString(List<Long> longList){
        String ids = EMPTY;
        for(Long id : longList){
            ids += (!ids.equals(EMPTY) ? "," : EMPTY) + id;
        }
        return ids;
    }

    public static String convertListIntegerToString(List<Integer> integerList){
        String ids = EMPTY;
        for(Integer id : integerList){
            ids += (!ids.equals(EMPTY) ? "," : EMPTY) + id;
        }
        return ids;
    }

    /**
     * Dado un String de valores se convierte este a un Collection de Longs
     * @param serie el String que contiene una serie de valores para ser convertido en Collection  de Longs
     * @return El Collection de Longs en los que se convirtio el String
     */
    public static List<Long> convertStringToArrayListLong(String serie){
        String[] valores = new String[NumberUtil.ZERO_INT];
        if(serie != null){
            valores = serie.split(COMMA);
        }
        return NumberUtil.arrayStringToArrayListLong(valores);
    }

    /**
     * convierte un string separado por coma a un arreglo de strings
     * @param value el string separado por comma
     * @return el array de strings
     */
    public static List<String> convertStringToArrayString(String value){
        List<String> listString = new ArrayList<>();
        Collections.addAll(listString, value.split(StringUtil.COMMA));
        return listString;
    }

    public static Boolean stringToBoolean(String data) {
        if (data != null) {
            return Boolean.valueOf(data);
        } else {
            return null;
        }
    }

    public static Boolean stringToBooleanNullToFalse(String data) {
        if (data != null) {
            return Boolean.valueOf(data);
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * Metodo que convierte un arreglo de String a una cadena
     * @param arrayString arreglo
     * @return cadena
     */
    public static String convertArrayStringToString(String [] arrayString){
        String arrayNew = EMPTY;
        for(String data: arrayString){
            arrayNew +=(arrayNew.isEmpty() ? EMPTY: COMMA) + data;
        }
        return arrayNew;
    }

    public static String nullToEmpty(String data) {
        if (data != null) {
            return data;
        } else {
            return EMPTY;
        }
    }

    /**
     * Normaliza la longitud del mes, que debe ser de dos digitos en todas las operaciones
     * @param mes El mes de entrada
     * @return El mes normalizado
     */
    public static String normalizaMes(String mes){
        return mes.length() < 2 ? "0" + mes : mes;
    }


    public static String refillString(String base, int finalLength, char symbol, boolean right) {
        while (base.length() < finalLength) {
            if (right) {
                base = base + String.valueOf(symbol);
            } else {
                base = String.valueOf(symbol) + base;
            }
        }
        return base;
    }
}
