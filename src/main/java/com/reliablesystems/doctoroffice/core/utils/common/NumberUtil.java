package com.reliablesystems.doctoroffice.core.utils.common;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class NumberUtil {

    private static final String HUNDREDTH = "0.001";
    private static final int DIVIDE_SCALE = 14;


    public static final int ZERO_INT = 0;
    public static final int ONE_INT = 1;
    public static final int TWO_INT = 2;
    public static final int THREE_INT = 3;
    public static final int FOUR_INT = 4;
    public static final int FIVE_INT = 5;
    public static final int SIX_INT = 6;
    public static final int SEVEN_INT = 7;
    public static final int EIGHT_INT = 8;
    public static final int NINE_INT = 9;
    public static final int TEN_INT = 10;
    public static final int ELEVEN_INT = 11;
    public static final int HUNDRED_INT = 100;
    public static final int TWENTY_THREE_INT = 23;
    public static final int FIFTY_NINE_INT = 59;
    public static final int TWELVE_INT = 12;
    public static final int THIRTY_ONE_INT = 31;
    public static final int ONE_INT_NEG = -1;
    public static final int NINE_THOUSAND_INT = 9000;

    public static final BigInteger ZERO_BIGINTEGER = BigInteger.ZERO;
    public static final BigInteger ONE_BIGINTEGER = BigInteger.ONE;
    public static final BigInteger TWO_BIGINTEGER = BigInteger.valueOf(2);
    public static final BigInteger ONE_BIGINTEGER_NEG = BigInteger.valueOf(-1);
    public static final BigDecimal ZERO_BIGDECIMAL = BigDecimal.ZERO;

    public static final long THREE_LONG_NEG = -3l;
    public static final long ZERO_LONG = 0;
    public static final long ONE_LONG = 1l;
    public static final long TWO_LONG = 2l;
    public static final long THREE_LONG = 3l;
    public static final long FOUR_LONG = 4l;
    public static final long TEN_LONG = 10l;

    public static final double ZERO_DOUBLE = 0.0;
    public static final double ONE_DOUBLE = 1d;
    public static final double FIVE_DOUBLE = 5d;


    private NumberUtil() {
    }

    public static int stringToInt(String number) {
        if (number != null && number.length() > ZERO_INT) {
            return Integer.parseInt(number);
        }
        return ZERO_INT;
    }

    public static int[] arrayStringToArrayInt(String[] numbers) {
        if (numbers != null && numbers.length > ZERO_INT) {
            int[] intNumbers = new int[numbers.length];
            for (int i = ZERO_INT; i < numbers.length; i++) {
                intNumbers[i] = stringToInt(numbers[i]);
            }
            return intNumbers;
        }
        return new int[ZERO_INT];
    }

    public static long[] arrayStringToArrayLong(String[] numbers) {
        if (numbers != null && numbers.length > 0) {
            long[] longNumbers = new long[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                longNumbers[i] = stringToLong(numbers[i]);
            }
            return longNumbers;
        }
        return new long[0];
    }

    public static BigDecimal add(BigDecimal num1, double num2) {
        return add(num1, toBigDecimal(num2));
    }

    public static BigDecimal add(double num1, BigDecimal num2) {
        return add(toBigDecimal(num1), num2);
    }

    public static BigDecimal add(double num1, double num2) {
        return add(toBigDecimal(num1), toBigDecimal(num2));
    }

    public static BigDecimal substractLessThanHundredth(double num1, double num2) {
        return substractLessThanHundredth(toBigDecimal(num1), toBigDecimal(num2));
    }

    public static BigDecimal substract(BigDecimal num1, double num2) {
        return substract(num1, toBigDecimal(num2));
    }

    public static BigDecimal substract(double num1, BigDecimal num2) {
        return substract(toBigDecimal(num1), num2);
    }

    public static BigInteger substract(double num1, BigInteger num2) {
        return substract(toBigInteger(num1), num2);
    }

    public static BigDecimal substract(double num1, double num2) {
        return substract(toBigDecimal(num1), toBigDecimal(num2));
    }

    public static BigDecimal divide(double num1, double num2) {
        return divide(toBigDecimal(num1), toBigDecimal(num2));
    }

    public static BigDecimal multiply(double num1, double num2) {
        return multiply(toBigDecimal(num1), toBigDecimal(num2));
    }

    /**
     * Created by Janet
     * Multiplica dos numeros tipo BigInteger
     *
     * @param num1 valor 1
     * @param num2 valor 2
     * @return multiplicacion
     */
    public static BigInteger multiply(BigInteger num1, BigInteger num2) {
        return num1.multiply(num2);
    }

    public static BigDecimal add(BigDecimal num1, BigDecimal num2) {
        return num1.add(num2);
    }

    public static BigInteger add(BigInteger num1, BigInteger num2) {
        return num1.add(num2);
    }

    public static BigDecimal substractLessThanHundredth(BigDecimal num1, BigDecimal num2) {
        BigDecimal result = num1.subtract(num2);
        return result.compareTo(toBigDecimal(HUNDREDTH)) > 0 ? result : getZeroBigDecimal();
    }

    public static BigDecimal substract(BigDecimal num1, BigDecimal num2) {
        return num1.subtract(num2);
    }

    public static BigDecimal divide(BigDecimal num1, BigDecimal num2) {
        return num1.divide(num2, DIVIDE_SCALE, RoundingMode.HALF_UP);
    }

    public static BigDecimal multiply(BigDecimal num1, BigDecimal num2) {
        return num1.multiply(num2);
    }

    public static BigDecimal getZeroBigDecimal() {
        return BigDecimal.ZERO;
    }

    public static BigDecimal toBigDecimal(double number) {
        return toBigDecimal(String.valueOf(number));
    }

    public static BigDecimal toBigDecimal(String number) {
        return new BigDecimal(number);
    }

    /**
     * Created by: Janet
     * Convierte un double a un entero
     *
     * @param number valor a convertir
     * @return valor BigInteger
     */
    public static int toInt(double number) {
        return (int) Math.floor(number);
    }

    public static Integer toIntNotNull(Long number) {

        if (number != null) {
            return (int) Math.floor(number);
        }
        return ZERO_INT;
    }

    /**
     * Created by: Janet
     * Convierte un long a un entero
     *
     * @param number valor a convertir
     * @return valor BigInteger
     */
    public static int toInt(long number) {
        return (int) Math.floor(number);
    }

    /**
     * Created by: Janet
     * Convierte un entero a un BigInteger
     *
     * @param number valor a convertir
     * @return valor BigInteger
     */
    public static BigInteger toBigInteger(int number) {
        return toBigInteger(String.valueOf(number));
    }

    /**
     * Created by: Janet
     * Convierte un BigDecimal a un BigInteger
     *
     * @param number valor a convertir
     * @return valor BigInteger
     */
    public static BigInteger toBigInteger(BigDecimal number) {
        return toBigInteger(String.valueOf(number));
    }

    /**
     * Created by: Janet
     * Convierte un double a un BigInteger
     *
     * @param number valor a convertir
     * @return valor BigInteger
     */
    public static BigInteger toBigInteger(double number) {
        return toBigInteger(String.valueOf(toInt(number)));
    }

    /**
     * Created by: Janet
     * Convierte un long a un BigInteger
     *
     * @param number valor a convertir
     * @return valor BigInteger
     */
    public static BigInteger toBigInteger(long number) {
        return toBigInteger(String.valueOf(toInt(number)));
    }

    public static Double toDouble(BigInteger number) {
        return stringToDouble(String.valueOf(number));
    }

    /**
     * Created by: Janet
     * Convierte un String a un BigInteger
     *
     * @param number valor a convertir
     * @return valor BigInteger
     */
    public static BigInteger toBigInteger(String number) {
        if (number != null && !number.equals("")) {
            return new BigInteger(number);
        }
        return new BigInteger(StringUtil.ZERO_STRING);
    }

    public static Double stringToDouble(String number) {
        if (number != null && !number.equals("")) {
            return Double.parseDouble(number);
        }
        return 0.0;
    }

    public static double[] arrayStringToArrayDouble(String[] numbers) {
        if (numbers != null && numbers.length > 0) {
            double[] doubleNumbers = new double[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                doubleNumbers[i] = stringToDouble(numbers[i]);
            }
            return doubleNumbers;
        }
        return new double[0];
    }

    public static Long stringToLong(String number) {

        if (number != null && number.length() > ZERO_INT) {
            return Long.parseLong(number);
        }
        return 0L;
    }

    /**
     * @param data
     * @return
     */
    public static Integer stringToInteger(String data) {
        if ((data != null) && !data.equals("")) {
            return Integer.parseInt(data);
        } else {
            return 0;
        }
    }

    /**
     * convierte un valor Long a String.
     *
     * @param data el valor long para convertir
     * @return el long convertido en String
     */
    public static String longToString(Long data) {
        if (data != null) {
            return data.toString();
        } else {
            return null;
        }
    }

    /**
     * Convierte un valor entero a string.
     *
     * @param data el valor entero.
     * @return el valor ya convertido en string.
     */
    public static String integerToString(Integer data) {
        if (data != null) {
            return data.toString();
        } else {
            return null;
        }
    }

    /**
     * convierte un arreglo de Strings a una Coleccion
     *
     * @param numbers
     * @return
     */
    public static ArrayList<Long> arrayStringToArrayListLong(String[] numbers) {
        if (numbers != null && numbers.length > 0) {
            ArrayList<Long> longCollection = new ArrayList<>();
            for (int i = 0; i < numbers.length; i++) {
                longCollection.add(stringToLong(numbers[i]));
            }
            return longCollection;
        }
        return new ArrayList<>();
    }

    /**
     * convierte un arreglo de Strings a una Coleccion
     *
     * @param strings
     * @return
     */
    public static ArrayList<String> arrayStringToArrayListString(String[] strings) {
        if (strings != null && strings.length > 0) {
            ArrayList<String> longCollection = new ArrayList<>();
            for (int i = 0; i < strings.length; i++) {
                longCollection.add(strings[i]);
            }
            return longCollection;
        }
        return new ArrayList<>();
    }

    /**
     * Obtiene el promedio una lista de cantidades
     *
     * @param listCantidades la lista de cantidades
     * @return el promedio
     */
    public static double getPromedioCantidades(List<Double> listCantidades) {
        double totalSuma = 0d;
        for (Double cantidad : listCantidades) {
            totalSuma += cantidad;
        }
        return totalSuma == 0d ? 0d : divide(totalSuma, listCantidades.size()).doubleValue();
    }

    /**
     * convierte un arreglo de Strings a una Coleccion de Integer
     *
     * @param numbers
     * @return
     */
    public static ArrayList<Integer> arrayStringToArrayListInteger(String[] numbers) {
        if (numbers != null && numbers.length > 0) {
            ArrayList<Integer> longCollection = new ArrayList<>();
            for (int i = 0; i < numbers.length; i++) {
                longCollection.add(stringToInteger(numbers[i]));
            }
            return longCollection;
        }
        return new ArrayList<>();
    }

    /**
     * convierte un arreglo de Strings a una Coleccion de biginteger
     *
     * @param numbers
     * @return
     */
    public static BigInteger[] arrayStringToArrayBigInteger(String[] numbers) {
        if (numbers != null && numbers.length > 0) {
            BigInteger[] bigIntegersNumbers = new BigInteger[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                bigIntegersNumbers[i] = stringToBiginteger(numbers[i]);
            }
            return bigIntegersNumbers;
        }
        return new BigInteger[0];
    }

    public static BigInteger stringToBiginteger(String number) {
        if (number != null && !number.equals("")) {
            return new BigInteger(number);
        }
        return new BigInteger(StringUtil.ZERO_STRING);
    }

    public static BigInteger substract(BigInteger num1, BigInteger num2) {
        return num1.subtract(num2);
    }

    public static HashMap<Long, Long> arrayStringToHashMap(String[] numbers) {
        if (numbers != null && numbers.length > 0) {
            HashMap hashMap = new HashMap();
            for (int i = 0; i < numbers.length; i++) {
                hashMap.put(stringToLong(numbers[i]), stringToLong(numbers[i]));
            }
            return hashMap;
        }
        return new HashMap();
    }

    /**
     * Verifica si el string se trata de un numero
     *
     * @param number variable a evaluar
     * @return true o false
     */
    public static boolean isNumeric(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * @param data
     * @return
     */
    public static String doubleToString(Double data) {
        if (data != null) {
            return data.toString();
        } else {
            return null;
        }
    }

    /**
     * @param data
     * @return
     */
    public static Integer stringToIntegerNullToZero(String data) {
        if (data != null && !data.equals("")) {
            return Integer.parseInt(data);
        } else {
            return 0;
        }
    }
}
