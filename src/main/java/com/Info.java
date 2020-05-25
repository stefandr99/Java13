package com;

import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;

public class Info implements Command {
    private final String baseName = "Messages";
    private Locale locale;
    private ResourceBundle resourceBundle;

    /**
     * Apelarea metodei message pentru a obtine informatiile cerute
     * @param params - Parametrii comenzii (Nu e cazul aici)
     */
    public void execute(String[] params) {
        System.out.println(message("info", Locale.getDefault(), Locale.getDefault().getLanguage()));
    }

    /**
     * Se formeaza String-ul message ce va constitui raspunsul total al comenzii Info
     * Se afiseaza informatiile relevate despre localul curent
     * @param key - Cheia valorii pe care trebuie sa o obtinem din fisierul .properties
     * @param locale - Localul curent
     * @param arguments - Argumentele comenzii (Nu e cazul aici)
     * @return - Un String ce contine tot raspunsul comenzii
     */
    private String message(String key, Locale locale, String ... arguments) {
        resourceBundle =  ResourceBundle.getBundle(baseName, locale);
        String pattern = resourceBundle.getString(key);
        String message = new MessageFormat(pattern).format(arguments);

        message += "\nCountry: " + locale.getCountry() + "\nLanguage: " + locale.getLanguage() + "\nCurrency: ";
        Currency currency = Currency.getInstance(locale);
        message += currency.getSymbol();
        WeekFields wf = WeekFields.of(locale);
        DayOfWeek day = wf.getFirstDayOfWeek();
        message += "\nWeek days: ";
        for (int i = 0; i < DayOfWeek.values().length; i++) {
            message += day.getDisplayName(TextStyle.FULL, locale) + " ";
            day = day.plus(1);
        }
        message += "\nMonths: ";
        DateFormatSymbols months = new DateFormatSymbols(locale);
        for (int i = 0; i < months.getMonths().length; i++) {
            message += months.getMonths()[i] + " ";
        }

        message += "\nDate: " + LocalDateTime.now();
        return message;
    }
}
