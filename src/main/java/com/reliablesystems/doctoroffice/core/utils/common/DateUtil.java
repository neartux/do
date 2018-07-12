package com.reliablesystems.doctoroffice.core.utils.common;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public final class DateUtil {
    public static final int DAYS = 0;
    public static final int HOURS = 1;
    public static final int MINUTES = 2;
    public static final int SECONDS = 3;
    public static final int WEEK_DAYS_NUMBER = 7;
    public static int YEAR_MONTHS_NUMBER = 12;
    private static final String[] MONTHS = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    private static final String[] NAMEDAYS = {"Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};


    /**
     * nadie puede hacer instancias de este objeto,
     * todos los metodos debe ser estaticos
     */
    private DateUtil() {
    }


    public static Date now(){
        return new Date();
    }

    public static Timestamp nowTimestamp(){
        return new Timestamp(now().getTime());
    }

    public static java.sql.Date toSQL(Date date){
        return new java.sql.Date(date.getTime());
    }

    public static String dateStringSpanishHM(Date fecha) {
        SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es", "MX"));
        return formateador.format(fecha);
    }

    public static Date getFechaPrimerDiaDeSemana(Date fechaDada) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaDada);
        int diaDeSemana = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DAY_OF_MONTH, -(diaDeSemana - 1));
        return calendar.getTime();
    }

    public static Date getFechaUltimoDiaDeSemana(Date fechaDada) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaDada);
        int diaDeSemana = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DAY_OF_MONTH, 7 - diaDeSemana);
        return calendar.getTime();

    }

    public static Date getFechaPrimerDiaDeQuincena(Date fechaDada) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaDada);
        int diaDeMes = calendar.get(Calendar.DAY_OF_MONTH);
        if (diaDeMes >= 15)
            calendar.set(Calendar.DAY_OF_MONTH, 16);
        else
            calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date getFechaUltimoDiaDeQuincena(Date fechaDada) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaDada);
        int ultimodiaMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int diaDeMes = calendar.get(Calendar.DAY_OF_MONTH);
        if (diaDeMes <= 15)
            calendar.set(Calendar.DAY_OF_MONTH, 15);
        else
            calendar.set(Calendar.DAY_OF_MONTH, ultimodiaMes);

        return calendar.getTime();
    }

    public static java.util.Date getFechaInicioMes(Date fechaDada) {
        Calendar fechaInicial = Calendar.getInstance();
        fechaInicial.setTime(fechaDada);
        int dia = fechaInicial.get(Calendar.DAY_OF_MONTH);
        fechaInicial.add(Calendar.DAY_OF_MONTH, -(dia - 1));
        fechaInicial.set(Calendar.HOUR_OF_DAY, 0);
        fechaInicial.set(Calendar.MINUTE, 0);
        fechaInicial.set(Calendar.SECOND, 0);
        fechaInicial.set(Calendar.MILLISECOND, 0);
        return fechaInicial.getTime();

    }

    public static java.util.Date getFechaFinMes(Date fechaDada) {
        Calendar fechaInicial = Calendar.getInstance();
        fechaInicial.setTime(fechaDada);
        int diaActual = fechaInicial.get(Calendar.DAY_OF_MONTH);
        int ultimodiaMes = fechaInicial.getActualMaximum(Calendar.DAY_OF_MONTH);
        fechaInicial.add(Calendar.DAY_OF_MONTH, (ultimodiaMes - diaActual));
        fechaInicial.set(Calendar.HOUR_OF_DAY, 23);
        fechaInicial.set(Calendar.MINUTE, 59);
        fechaInicial.set(Calendar.SECOND, 59);
        fechaInicial.set(Calendar.MILLISECOND, 999);
        return fechaInicial.getTime();

    }

    public static java.util.Date getFechaInicioMes(String mes, String anno) {
        Calendar fechaInicial = Calendar.getInstance();
        fechaInicial.set(Integer.parseInt(anno), Integer.parseInt(mes) - 1, 1);
        return fechaInicial.getTime();

    }

    public static java.util.Date getFechaFinMes(String mes, String anno) {
        Calendar fechaInicial = Calendar.getInstance();
        fechaInicial.set(Integer.parseInt(anno), Integer.parseInt(mes) - 1, 1);
        int ultimodiaMes = fechaInicial.getActualMaximum(Calendar.DAY_OF_MONTH);
        fechaInicial.add(Calendar.DAY_OF_MONTH, (ultimodiaMes - 1));
        return fechaInicial.getTime();

    }

    public static java.util.Date getFechaInicioMes(int mes, int anno) {
        Calendar fechaInicial = Calendar.getInstance();
        fechaInicial.set(anno, mes - 1, 1);
        return fechaInicial.getTime();

    }

    public static java.util.Date getFechaFinMes(int mes, int anno) {
        Calendar fechaInicial = Calendar.getInstance();
        fechaInicial.set(anno, mes - 1, 1);
        int ultimodiaMes = fechaInicial.getActualMaximum(Calendar.DAY_OF_MONTH);
        fechaInicial.add(Calendar.DAY_OF_MONTH, (ultimodiaMes - 1));
        return fechaInicial.getTime();

    }

    public static Date getDate(String fecha, String datePattern) throws ParseException {
        SimpleDateFormat formateer = new SimpleDateFormat(datePattern);
        return formateer.parse(fecha);
    }

    public static Date getDateLastHourMinuteSecond(String fecha, String pattern) {
        if ((fecha == null) || fecha.trim().equals(StringUtil.EMPTY)) {
            return null;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        try {
            Date dateLastHourMinuteSecond = simpleDateFormat.parse(fecha);
            Calendar calendarNow = Calendar.getInstance();
            calendarNow.setTime(dateLastHourMinuteSecond);
            calendarNow.set(Calendar.HOUR_OF_DAY, 23);
            calendarNow.set(Calendar.MINUTE, 59);
            calendarNow.set(Calendar.SECOND, 59);

            return calendarNow.getTime();
        } catch (ParseException e) {
            throw new java.lang.IllegalArgumentException("Invalid date pattern!");
        }
    }


    public static Date getDateFirstHourMinuteSecond(String fecha, String pattern) {
        if ((fecha == null) || fecha.trim().equals(StringUtil.EMPTY)) {
            return null;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        try {
            Date dateLastHourMinuteSecond = simpleDateFormat.parse(fecha);
            Calendar calendarNow = Calendar.getInstance();
            calendarNow.setTime(dateLastHourMinuteSecond);
            calendarNow.set(Calendar.HOUR_OF_DAY, 0);
            calendarNow.set(Calendar.MINUTE, 0);
            calendarNow.set(Calendar.SECOND, 0);

            return calendarNow.getTime();
        } catch (ParseException e) {
            throw new java.lang.IllegalArgumentException("Invalid date pattern!");
        }
    }


    public static String getDate(Date fecha, String datePattern) throws ParseException {
        SimpleDateFormat formateer = new SimpleDateFormat(datePattern);
        return formateer.format(fecha);
    }

    public static String getDay(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        String diaString = String.valueOf(dia);
        return diaString.length() < NumberUtil.TWO_INT ? StringUtil.ZERO_STRING + dia : diaString;
    }

    public static String getMonth(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        int mes = calendar.get(Calendar.MONTH) + 1;
        String mesString = String.valueOf(mes);
        return mesString.length() < NumberUtil.TWO_INT ? StringUtil.ZERO_STRING + mes : mesString;
    }

    public static String getMonth011(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        int mes = calendar.get(Calendar.MONTH);
        return String.valueOf(mes);
    }

    public static String getYear(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        int anno = calendar.get(Calendar.YEAR);
        return String.valueOf(anno);
    }

    public static int getYearInt(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        return calendar.get(Calendar.YEAR);
    }

    public static String getDay(String fecha, String datePattern) throws ParseException {
        Date fechaDate = getDate(fecha, datePattern);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaDate);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        String diaString = String.valueOf(dia);
        return diaString.length() < NumberUtil.TWO_INT ? StringUtil.ZERO_STRING + dia : diaString;
    }

    public static String getMonth(String fecha, String datePattern) throws ParseException {
        Date fechaDate = getDate(fecha, datePattern);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaDate);
        int mes = calendar.get(Calendar.MONTH) + 1;
        String mesString = String.valueOf(mes);
        return mesString.length() < NumberUtil.TWO_INT ? StringUtil.ZERO_STRING + mes : mesString;
    }

    public static String getYear(String fecha, String datePattern) throws ParseException {
        Date fechaDate = getDate(fecha, datePattern);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaDate);
        int anno = calendar.get(Calendar.YEAR);
        return String.valueOf(anno);
    }

    public static Date summMonth(Date fechaIncioPeriodo, int cambioMes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaIncioPeriodo);
        calendar.add(Calendar.MONTH, cambioMes);
        return calendar.getTime();
    }

    public static Date summYear(Date fechaIncioPeriodo, int cambioAnno) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaIncioPeriodo);
        calendar.add(Calendar.YEAR, cambioAnno);
        return calendar.getTime();
    }

    public static Date summDay(Date fechaIncioPeriodo, int cambioDia) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaIncioPeriodo);
        calendar.add(Calendar.DAY_OF_MONTH, cambioDia);
        return calendar.getTime();
    }

    /**
     * Compara si una fecha tiene el mismo mes y a�o de la fecha que se compara
     */
    public static boolean isSameMonth(Date dateBase, Date dateCompare) {
        String monthBase = getMonth(dateBase);
        String yearBase = getYear(dateBase);
        String monthCompare = getMonth(dateCompare);
        String yearCompare = getYear(dateCompare);
        return (monthBase.equals(monthCompare) && yearBase.equals(yearCompare));
    }

    /**
     * Obtiene una lista de los primeros dias del mes contenidos en el rango de fechas
     *
     * @param inicioPeriodo El inicio del rango
     * @param finPeriodo    El fin del rango
     * @return La lista de los primeros dias del mes de cada rango de fechas
     * @throws java.lang.IllegalArgumentException
     *          Si las fecha inicial es mayor a la fecha final
     */
    public static List<Date> getListaPrimerosDiasMesRangoFechas(Date inicioPeriodo, Date finPeriodo) throws java.lang.IllegalArgumentException {
        ArrayList<Date> listaPrimerosDiasMesRangoFechas = new ArrayList<Date>();
        int comparacionFechas = finPeriodo.compareTo(inicioPeriodo);
        Calendar inicioPeriodoCalendar = Calendar.getInstance();
        inicioPeriodoCalendar.setTime(inicioPeriodo);
        Calendar finPeriodoCalendar = Calendar.getInstance();
        finPeriodoCalendar.setTime(finPeriodo);
        if (comparacionFechas >= NumberUtil.ZERO_INT) {//es que es correcto el periodo
            int annoFinPeriodo = finPeriodoCalendar.get(Calendar.YEAR);
            int annoInicioPeriodo = inicioPeriodoCalendar.get(Calendar.YEAR);
            int mesFinPeriodo = finPeriodoCalendar.get(Calendar.MONTH) + 1;//los MONTHS son del 0 al 11
            int mesInicioPeriodo = inicioPeriodoCalendar.get(Calendar.MONTH) + 1;//los MONTHS son del 0 al 11
            for (int i = annoInicioPeriodo; i <= annoFinPeriodo; i++) {
                for (int j = mesInicioPeriodo; i == annoFinPeriodo ? j <= mesFinPeriodo : j <= YEAR_MONTHS_NUMBER; j++) {
                    Date fechaInicioMes = getFechaInicioMes(j, i);
                    listaPrimerosDiasMesRangoFechas.add(fechaInicioMes);
                }
                mesInicioPeriodo = NumberUtil.ONE_INT;//para cada salto de a�o
            }
        } else {//cuando es incorrecto el
            throw new java.lang.IllegalArgumentException("Invalid Range, the firts argument must be less than the last");
        }
        return listaPrimerosDiasMesRangoFechas;
    }

    public static java.util.Date getFecha(String dia, String mes, String anno) {
        return getFecha(Integer.parseInt(anno), Integer.parseInt(mes), Integer.parseInt(dia));
    }

    public static java.util.Date getFecha(int dia, int mes, int anno) {
        Calendar fechaInicial = Calendar.getInstance();
        fechaInicial.set(anno, mes - 1, dia);
        return fechaInicial.getTime();

    }

    /**
     * Obtiene  una fecha completa configurada con todos los parametros
     *
     * @param year      El ano que deseamos
     * @param month     El mes de 1 a 12
     * @param date      El dia del mes
     * @param hourOfDay La hora del dia desde las 0 a 23
     * @param minute    El minuto desde 0 a 59
     * @param second    El segundo desde 0 a 59
     * @return La fecha completa configurada
     */
    public static Date getFullDate(int year, int month, int date, int hourOfDay, int minute, int second) {
        Calendar fullDate = Calendar.getInstance();
        fullDate.set(year, month - 1, date, hourOfDay, minute, second);
        return fullDate.getTime();
    }


    /**
     * Obtiene la siguiente fecha disponible con la hora, minuto y segundo, basandose en la fecha de referencia, esto es:
     * Si en la fecha de referencia es menor al tiempo definido en hora,minuto y segundo, retorna la fecha de referencia
     * con esos componentes.
     * si es mayor, regresa la fecha de referencia + Un dia del mes
     * Util para saber si el tiempo que deseamos configurar ya paso o no ha pasado con respecto a la fecha de referencia
     *
     * @param referenceDate La fecha de referencia
     * @param hourOfDay     La hora del dia que se desea configurar
     * @param minute        El minuto
     * @param second        El segundo
     * @return La fecha configurada
     */
    public static Date getNextDateUntilDate(Date referenceDate, int hourOfDay, int minute, int second) {
        Calendar fullDate = Calendar.getInstance();
        fullDate.setTime(referenceDate);
        fullDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
        fullDate.set(Calendar.MINUTE, minute);
        fullDate.set(Calendar.SECOND, second);
        Date probablyDate = fullDate.getTime();
        if (probablyDate.compareTo(referenceDate) > 0) {
            return probablyDate;
        } else {
            fullDate.add(Calendar.DAY_OF_MONTH, 1);
        }
        return fullDate.getTime();
    }

    /**
     * Obtiene el tiempo transcurrido entre una fechaInicial a una fechaFinal
     * @param fechaFinal La fecha final del rango de fechas
     * @param fechaInicial La fecha inicial del rango de fechas
     * @return Un arreglo de resultados [DIAS, HORAS, MINUTOS, SEGUNDOS]
     * @throws IllegalArgumentException Si fechaFinal es menor a fechaInicial
     */
    public static int[] substractDate(Date fechaInicial, Date fechaFinal){
        if(fechaFinal.compareTo(fechaInicial) < 0){
            throw new IllegalArgumentException("Fecha final debe ser mayor a fecha inicial");
        }
        int[] duracion = new int[4]; //dias, horas, minutos, segundos
        Calendar calendarFechaInicial = Calendar.getInstance();
        calendarFechaInicial.setTime(fechaInicial);
        Calendar calendarFechaFinal = Calendar.getInstance();
        calendarFechaFinal.setTime(fechaFinal);
        //annos
        int annoFinal = calendarFechaFinal.get(Calendar.YEAR);
        int annoInicial = calendarFechaInicial.get(Calendar.YEAR);
        //dias
        int diaFinal = calendarFechaFinal.get(Calendar.DAY_OF_YEAR);
        int diaInicial = calendarFechaInicial.get(Calendar.DAY_OF_YEAR);
        //horas
        int horaFinal = calendarFechaFinal.get(Calendar.HOUR_OF_DAY);
        int horaInicial = calendarFechaInicial.get(Calendar.HOUR_OF_DAY);
        //minutos
        int minutoFinal = calendarFechaFinal.get(Calendar.MINUTE);
        int minutoInicial = calendarFechaInicial.get(Calendar.MINUTE);
        //segundos
        int segundoFinal = calendarFechaFinal.get(Calendar.SECOND);
        int segundoInicial = calendarFechaInicial.get(Calendar.SECOND);

        //calculo de segundos
        if((segundoFinal - segundoInicial) < 0){
            minutoFinal--;
            duracion[3] = (segundoFinal + 60 ) - (segundoInicial);//prestamo de un minuto
        }else{
            duracion[3] = segundoFinal - segundoInicial;
        }

        //calculo de minutos
        if((minutoFinal - minutoInicial) < 0){
            horaFinal--;
            duracion[2] = (minutoFinal + 60) - minutoInicial;//prestamo de una hora
        }else{
            duracion[2] = minutoFinal - minutoInicial;
        }

        //calculo de horas
        if((horaFinal - horaInicial) < 0){
            diaFinal--;
            duracion[1] = (horaFinal + 24) - horaInicial;//prestamo de un dia
        }else{
            duracion[1] = horaFinal - horaInicial;
        }

        //dias
        int numeroDiasFinal = annoFinal * 365 + diaFinal;
        int numeroDiasInicial = annoInicial * 365 + diaInicial;

        //calculo de dias
        duracion[0] = numeroDiasFinal - numeroDiasInicial;
        return duracion;
    }

    /**
     * Convierte un numero int a su equivalente en binario
     * @param number        El numero a convertir
     * @return              El numero binario en su formato String
     */
    public static String integerToBinary(int number) {
        String binaryNumber = "";
        while (number > 0) {
            int q = number % 2;
            binaryNumber = String.valueOf(q) + binaryNumber;
            number /= 2;
        }
        return binaryNumber;
    }

    /**
     * Convierte un numero binario en formato string a int
     * @param binary            El numero binario en formato string
     * @return                  El numero int
     */
    public static int binaryToInteger(String binary){
        int number = 0;
        for(int i = binary.length() - 1; i >= 0; i--){
            char digit = binary.charAt(i);
            if(digit != '0' && digit != '1'){
                throw new ArithmeticException("Invalid Binary Digit");
            }
            number += Character.getNumericValue(digit) * Math.pow(2, (binary.length() - i - 1));
        }
        return number;
    }

    /**
     * Obtiene los rangos de fechas arreglados en semanas de un mes definido por mes y anno
     * Acompletando en el primer y ultimo con las fechas de los MONTHS subsecuentes (anterior y posterior)
     * @param mes El mes
     * @param anno El anno
     * @return Retorna el arreglo de fechas
     */
    public static Date[][] getRangoSemanasPrimerYUltimoDia(int mes, int anno){
        //se obtiene la fecha de inicio del mes para calculos
        Date fechaInicioMes = getFechaInicioMes(mes, anno);
        //se obtiene la fecha de fin de mes para comparaciones
        Date fechaFinMes = getFechaFinMes(mes, anno);
        //el primer dia de la primera semana, puede estar fuera del mes
        Date fechaPrimerDiaDeSemana = getFechaPrimerDiaDeSemana(fechaInicioMes);
        //el ultimo dia de la semana podria ser el primer dia del mes
        Date fechaUltimoDiaDeSemana = getFechaUltimoDiaDeSemana(fechaPrimerDiaDeSemana);
        //calculamos el numero de semanas que se requieren
        //el rango de dias usables a partir de la segunda semana
        int[] resultSubstract = substractDate(fechaUltimoDiaDeSemana, fechaFinMes);
        //obtenemos el numero de semanas que tiene el mes contando aun las semanas compartidas con los MONTHS anterior y posterior
        int numeroSemanas = (int)Math.ceil(resultSubstract[DAYS] / (double)WEEK_DAYS_NUMBER) + 1;
        //se inicializa el arreglo de fechas con la informacion de las semanas
        Date[][] rangoSemanas = new Date[numeroSemanas][NumberUtil.TWO_INT];
        //se coloca el primer rango de fechas correspondiente a la primer semana, para poder quitar el rango usable del mes
        rangoSemanas[NumberUtil.ZERO_INT][NumberUtil.ZERO_INT] = fechaPrimerDiaDeSemana;
        rangoSemanas[NumberUtil.ZERO_INT][NumberUtil.ONE_INT] = fechaUltimoDiaDeSemana;

        //sumanos una semana al primer dia de la semana y asi obtenemos el inicio de la segunda semana
        fechaPrimerDiaDeSemana = summDay(fechaPrimerDiaDeSemana, NumberUtil.SEVEN_INT);
        //se realiza la operacion apra todas las demas semanas
        for(int i = NumberUtil.ONE_INT; (fechaPrimerDiaDeSemana.before(fechaFinMes) || fechaPrimerDiaDeSemana.equals(fechaFinMes)); i++){
            rangoSemanas[i][NumberUtil.ZERO_INT] = fechaPrimerDiaDeSemana;
            rangoSemanas[i][NumberUtil.ONE_INT] = getFechaUltimoDiaDeSemana(fechaPrimerDiaDeSemana);
            fechaPrimerDiaDeSemana = summDay(fechaPrimerDiaDeSemana, NumberUtil.SEVEN_INT);
        }
        return rangoSemanas;
    }

    /**
     * Metodo que devuelve la hora  de una fecha
     *
     * @param fecha fecha que se desa saber
     * @return la hora de una fecha.
     */
    public static String getHour(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return String.valueOf(hour);
    }

    /**
     * Metodo que devuelve el minuto de una fecha
     *
     * @param fecha fecha que se desa saber
     * @return minuto de una fecha.
     */
    public static String getMinute(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        int minute = calendar.get(Calendar.MINUTE);
        return String.valueOf(minute);
    }

    public static Date getFullDate(String fecha, String pattern) {
        if ((fecha == null) || fecha.trim().equals(StringUtil.EMPTY)) {
            return null;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        try {
            Date dateLastHourMinuteSecond = simpleDateFormat.parse(fecha);
            Calendar calendarNow = Calendar.getInstance();
            calendarNow.setTime(dateLastHourMinuteSecond);
            calendarNow.set(Calendar.HOUR_OF_DAY, Integer.parseInt(getHour(new Date())));
            calendarNow.set(Calendar.MINUTE, Integer.parseInt(getMinute(new Date())));
            calendarNow.set(Calendar.SECOND, 0);

            return calendarNow.getTime();
        } catch (ParseException e) {
            throw new java.lang.IllegalArgumentException("Invalid date pattern!");
        }
    }

    public static Date getCreateDate(String fecha, String pattern, int horas, int minutos, int segundos) {
        if ((fecha == null) || fecha.trim().equals("")) {
            return null;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        try {
            Date dateLastHourMinuteSecond = simpleDateFormat.parse(fecha);
            Calendar calendarNow = Calendar.getInstance();
            calendarNow.setTime(dateLastHourMinuteSecond);
            calendarNow.set(Calendar.HOUR_OF_DAY, horas);
            calendarNow.set(Calendar.MINUTE, minutos);
            calendarNow.set(Calendar.SECOND, segundos);

            return calendarNow.getTime();
        } catch (ParseException e) {
            throw new java.lang.IllegalArgumentException("Invalid date pattern!");
        }
    }

    /**
     * Encuentra la diferencia de horas respecto a dos fechas dadas
     */
    public static double getDiferenciaHoras(Timestamp fechaInicial, Timestamp fechaFinal){
        long tiempoInicial=fechaInicial.getTime();
        long tiempoFinal=fechaFinal.getTime();
        double resta=tiempoFinal - tiempoInicial;
        //el metodo getTime te devuelve en mili segundos para saberlo en horas lo dividimos entre
        return resta/(1000*3600);
    }

    /** Dada una fecha se convierte a String con el formato de fecha proporcionado
     */
    public static String dateToString(Date fecha, String pattern) {
        String fechaFormateada = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        try {
            fechaFormateada = simpleDateFormat.format(fecha);
        } catch (Exception e) {
            return null;
        }

        return fechaFormateada;
    }

    /** Dada una fecha en String se convierte a un Date
     */
    public static Date stringToDate(String fecha, String pattern) {
        if ((fecha == null) || fecha.trim().equals(StringUtil.EMPTY)) {
            return null;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        try {
            return simpleDateFormat.parse(fecha);
        } catch (ParseException e) {
            throw new java.lang.IllegalArgumentException(
                    "Invalid date pattern!");
        }
    }

    /**
     * Obtiene una instancia de Date configurada con la fecha dada, con la hora minuto y segundos actuales.
     * @param fecha      La fecha en string.
     * @param pattern    El pattern para convertir la fecha.
     * @return La instancia de Date configurada con los datos dados.
     */
    public static Date stringToDateCurrentHourMinuteSecond(String fecha, String pattern) {
        return setCurrentTimeToDate(stringToDate(fecha, pattern));
    }

    /**
     * Formateo de fechas con anno de dos digitos
     */
    public static String dateToStringTwoDigitYear(Date fecha, int anno,
                                                  String pattern) {
        String fechaFormateada = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTime(new Date());
        calendarNow.set(Calendar.YEAR, anno);
        simpleDateFormat.set2DigitYearStart(calendarNow.getTime());

        try {
            fechaFormateada = simpleDateFormat.format(fecha);
        } catch (Exception e) {
            return null;
        }

        return fechaFormateada;
    }

    public static Date stringToDateLastHourMinuteSecond(String fecha,
                                                        String pattern) {
        if ((fecha == null) || fecha.trim().equals(StringUtil.EMPTY)) {
            return null;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        try {
            Date dateLastHourMinuteSecond = simpleDateFormat.parse(fecha);
            Calendar calendarNow = Calendar.getInstance();
            calendarNow.setTime(dateLastHourMinuteSecond);
            calendarNow.set(Calendar.HOUR_OF_DAY, 23);
            calendarNow.set(Calendar.MINUTE, 59);
            calendarNow.set(Calendar.SECOND, 59);

            return calendarNow.getTime();
        } catch (ParseException e) {
            throw new java.lang.IllegalArgumentException("Invalid date pattern!");
        }
    }

    public static Date stringToDateFirstHourMinuteSecond(String fecha,
                                                         String pattern) {
        if ((fecha == null) || fecha.trim().equals(StringUtil.EMPTY)) {
            return null;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        try {
            Date dateLastHourMinuteSecond = simpleDateFormat.parse(fecha);
            Calendar calendarNow = Calendar.getInstance();
            calendarNow.setTime(dateLastHourMinuteSecond);
            calendarNow.set(Calendar.HOUR_OF_DAY, 0);
            calendarNow.set(Calendar.MINUTE, 0);
            calendarNow.set(Calendar.SECOND, 0);
            calendarNow.set(Calendar.MILLISECOND, 0);
            return new Timestamp(calendarNow.getTime().getTime());
        } catch (ParseException e) {
            throw new java.lang.IllegalArgumentException("Invalid date pattern!");
        }
    }

    /**
     * Obtiene la fecha con formato de Timestamp
     * @param date la fecha Util.Date
     * @return  la fecha con formato Timestamp
     */
    public static Timestamp getDateToTimestamp(Date date){
        return new Timestamp(date.getTime());
    }

    public static Date stringToDateTwoDigitYear(String fecha, int anno, String pattern) {
        if ((fecha == null) || fecha.trim().equals(StringUtil.EMPTY)) {
            return null;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTime(new Date());
        calendarNow.set(Calendar.YEAR, anno);
        simpleDateFormat.set2DigitYearStart(calendarNow.getTime());

        try {
            return simpleDateFormat.parse(fecha);
        } catch (ParseException e) {
            throw new java.lang.IllegalArgumentException("Invalid date pattern!");
        }
    }

    /**
     * Obtiene el numero de dias que tiene un mes especifico
     * @return el numero de dias
     */
    public static int getNumeroDiasDelMes(Date fecha){
        int mes = getMonthInteger(fecha);
        int year = getYearInt(fecha);
        switch(mes){
            case 1:  // Enero
            case 3:  // Marzo
            case 5:  // Mayo
            case 7:  // Julio
            case 8:  // Agosto
            case 10:  // Octubre
            case 12: // Diciembre
                return 31;
            case 4:  // Abril
            case 6:  // Junio
            case 9:  // Septiembre
            case 11: // Noviembre
                return 30;
            case 2:  // Febrero
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.setTime(fecha);
                if ( gregorianCalendar.isLeapYear(gregorianCalendar.get(GregorianCalendar.YEAR)) ) {
                    return 29;  // Año Bisiesto
                }else {
                    return 28;
                }
            default:
                throw new java.lang.IllegalArgumentException("El mes debe estar entre 0 y 11");
        }
    }

    /**
     * Obtiene el mes el formato integer de una fecha dada
     * @param fecha la fecha dada
     * @return el mes de la fecha
     */
    public static int getMonthInteger(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        return calendar.get(Calendar.MONTH) + NumberUtil.ONE_INT;
    }

    /**
     * Obtiene la fechainicial del mes anterior a la fecha en cuestion
     * @param fecha la fecha de la que se desea saber su fecha inicial del mes anterior
     * @return la fecha inicial del mes anterior
     */
    public static Date getFechaInicialMesAnterior(Timestamp fecha){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.MONTH, -1);
        return getFechaInicioMes(calendar.getTime());
    }

    /**
     * le asigna las primeras horas a una fecha
     * @param fecha la fecha dada
     * @return la fecha con la hora de inicio del dia
     */
    public static Date getFechaConHoraInicio(Date fecha){
        Calendar date = Calendar.getInstance();
        date.setTime(fecha);
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        return date.getTime();
    }

    /**
     * Asigna la fecha con hora fin a una fecha dada
     * @param fecha la fecha dada
     * @return la fecha con hora fin
     */
    public static Date getFechaConHoraFin(Date fecha){
        Calendar date = Calendar.getInstance();
        date.setTime(fecha);
        date.set(Calendar.HOUR_OF_DAY, 23);
        date.set(Calendar.MINUTE, 59);
        date.set(Calendar.SECOND, 59);
        date.set(Calendar.MILLISECOND, 999);
        return date.getTime();
    }


    /**
     * Obtiene la diferencia en dias que hay entre la fecha1 y la fecha dos.
     *
     * @param fecha1 la fecha uno.
     * @param fecha2 la feha dos.
     * @return la diferencia en dias entre las fechas dadas.
     * La fecha2 debe ser mayor a la fecha1, de lo contrario el resultado sera negativo.
     */
    public static int getDiferenciaEnDias(Date fecha1, Date fecha2) {
        // se crea las fechas con gregrian calendar.
        GregorianCalendar date1 = new GregorianCalendar();
        date1.setTime(fecha1);
        GregorianCalendar date2 = new GregorianCalendar();
        date2.setTime(fecha2);
        int rango = 0;
        // se comprueba si las fechas corresponden al mismo anio.
        if (date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR)) { // si corresponden al mismo anio:
            // se hace la resta sencila para obtener la diferencia en dias..
            rango = (date2.get(Calendar.DAY_OF_YEAR) - date1.get(Calendar.DAY_OF_YEAR));
        } else {
            /**
             * Si las fechas con de distintos anios,se comprueba que el anio de la fecha1 no sea bisiesto.
             * Si es bisiesto, se toman los dias del anio como 366, de lo contrario 365.
             */
            int diasAnyo = date1.isLeapYear(date1.get(Calendar.YEAR)) ? 366 : 365;
            // se calcula el rando de anios.
            int rangoAnyos = date2.get(Calendar.YEAR) - date1.get(Calendar.YEAR);
            // se obtienen los dias de diferencia entre las fechas.
            rango = (rangoAnyos * diasAnyo) + (date2.get(Calendar.DAY_OF_YEAR) - date1.get(Calendar.DAY_OF_YEAR));
        }
        return rango;
    }

    /**
     * Obtiene el nuemro de semana de una fecha dada
     */
    public static int getSemanadelAnio(Date fecha){
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek( Calendar.MONDAY);
        calendar.setMinimalDaysInFirstWeek(NumberUtil.FOUR_INT);
        calendar.setTime(fecha);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * Determinamos si la fecha1 es anterior a la fecha2
     * @param fecha1 La primera fecha
     * @param fecha2 La segunda fecha
     * @return true | False Dependiendo del comportamiento de las fechas
     */
    public static boolean esFechainicialAnterioralaFinal(Date fecha1, Date fecha2){
        return fecha1.before(fecha2);
    }

    /**
     * Le agrega la hora actual a una fecha dada
     * @param fecha la fecha
     * @return  la fecha con la hora actual
     */
    public static Date setCurrentTimeToDate(Date fecha) {
        //Instancia a calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);

        Calendar calendarToday = Calendar.getInstance();
        calendarToday.setTime(nowTimestamp());

        calendarToday.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendarToday.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        calendarToday.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
        return calendarToday.getTime();
    }


    /**
     * Resta el numero de MONTHS deseado a la fecha dada.
     * @param fecha la fecha a la que se requere restar los MONTHS.
     * @param numeroMONTHSBack el numero de MONTHS a restar.
     * @return la fecha resultante de la operacion.
     */
    public static Date substractMonthsToDate(Date fecha, int numeroMONTHSBack){
        Calendar date = Calendar.getInstance();
        date.setTime(fecha);
        date.add(Calendar.MONTH, (numeroMONTHSBack * -1));
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        return date.getTime();
    }

    /**
     * Encuentra el mes al que pertenece la fecha dada
     * @param fecha la fecha de la cual requerimos el nombre del mes
     * @return el nombre del mes de la fecha dada
     */
    public static String getMesByDate(Date fecha){
        return MONTHS [Integer.parseInt(getMonth(fecha)) - 1];
    }

    /**
     * Obtiene el nombre del mes seleccionado
     * @param numberMonth el numero del mes
     * @return el nombre del mes
     */
    public static String getMonthName(int numberMonth){
        return MONTHS[numberMonth - 1];
    }

    /**
     * Ecuentra que dia es la fecha dada
     * @param fecha la fecha de la cual requerimos el nombre del dia
     * @return el nombre del dia de la fecha dada
     */
    public static String getDayOfTheWeek(Date fecha){
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(fecha);
        int dia = cal.get(Calendar.DAY_OF_WEEK);
        return NAMEDAYS[ dia-1 ];
    }

    public static int getNumberDayOfTheWeek(Date fecha){
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(fecha);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static Date getDateFirstMoment(Date referenceDate) {
        return resetDateWithParameters(referenceDate, 0, 0, 0, 0);
    }

    /**
     * Cambia los valores de los campos de fecha
     * @param referenceDate La fecha de referencia
     * @param hourOfDay     La hora del dia que se desea configurar
     * @param minute        El minuto
     * @param second        El segundo
     * @param milisecond    El milisegundo
     * @return La fecha configurada
     */
    public static Date resetDateWithParameters(Date referenceDate, int hourOfDay, int minute, int second, int milisecond) {
        Calendar fullDate = Calendar.getInstance();
        fullDate.setTime(referenceDate);
        fullDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
        fullDate.set(Calendar.MINUTE, minute);
        fullDate.set(Calendar.SECOND, second);
        fullDate.set(Calendar.MILLISECOND, milisecond);
        return fullDate.getTime();
    }

    public static Date getDateFirstMomentNotNull(Date referenceDate) {
        if(referenceDate != null) {
            return resetDateWithParameters(referenceDate, 0, 0, 0, 0);
        }else{
            return resetDateWithParameters(now(), 0, 0, 0, 0);
        }
    }

    public static Date getDateLastMoment(Date referenceDate) {
        return resetDateWithParameters(referenceDate, 23, 59, 59, 999);
    }

    public static Timestamp toTimestamp(Date date){
        return new Timestamp(date.getTime());
    }

    /**
     * Suma determinado numero de dias a la fecha dada.
     * Retornando la fecha resultante de la suma.
     * @param fecha la fecha a la cual se le sumaran los dias.
     * @param numeroDias    dias a sumar a la fecha.
     * @return  la fecha resultante de la suma.
     */
    public static Date addDaysToDate(Date fecha, int numeroDias) {
        //Instancia a calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DATE, numeroDias);
        return calendar.getTime();
    }
}
