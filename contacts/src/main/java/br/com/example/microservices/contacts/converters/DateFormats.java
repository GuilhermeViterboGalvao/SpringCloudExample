package br.com.example.microservices.contacts.converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum DateFormats {
    /**
     * <pre>
     *     Example:
     *     dd/MM/yyyy = 01/01/2018
     * </pre>
     */
    DDMMYYYY("dd/MM/yyyy"),
    /**
     * <pre>
     *     Example:
     *     yyyy-MM-dd'T'HH:mm:ss'Z' = 2017-10-06T15:58:11Z
     * </pre>
     */
    YYYYMMDD_T_HHMMSS_Z_UTC("yyyy-MM-dd'T'HH:mm:ss'Z'"),
    /**
     * <pre>
     *     Example:
     *     yyyy-MM-dd'T'HH:mm:ss.SSSXXX = 2017-11-14T09:27:00.000-02:00
     * </pre>
     */
    YYYYMMDD_T_HHMMSS_SSSXXX_LOCALZONE("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"),
    /**
     * <pre>
     *     Example:
     *     yyyy-MM-dd = 2017-11-14
     * </pre>
     */
    YYYYMMDD("yyyy-MM-dd"),

    /**
     * <pre>
     *     Example:
     *     dd/MM/yyyy HH:mm = 14/11/2017 15:20
     * </pre>
     */
    DDMMYYYY_HHMM("dd/MM/yyyy HH:mm");

    private String format;

    DateFormats(String format){
        this.format = format;
    }

    public String getField() {
        return this.format;
    }

    public static List<String> getValues(){
        List<DateFormats> dateFormats = Arrays.asList(DateFormats.values());
        List<String> stringFormats = new ArrayList<>();
        dateFormats.forEach(f-> stringFormats.add(f.getField()));
        return stringFormats;
    }
}
