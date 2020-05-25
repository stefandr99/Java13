package com;

import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;

public class DisplayLocales implements Command {
    private final String baseName = "Messages";
    private Locale locale;
    private ResourceBundle resourceBundle;

    /**
     * Executarea comenzii. Se apeleaza metoda message pentru a se extrage raspunsul
     * @param params - Parametrii comenzii (Nu e cazul aici
     */
    public void execute(String[] params) {
        Locale[] available = Locale.getAvailableLocales();

        System.out.println(this.message("locales", Locale.getDefault()));
        for(Locale locale : available) {
            System.out.println(locale.getDisplayCountry() + "\t" + locale.getDisplayLanguage(locale));
        }
    }

    /**
     * Metida care returneaza mesajul comenzii. Este preluat din Messages.properties
     * @param key - Cheia valorii care se extrage din fisierul .properties
     * @param locale - Localul despre care se vor extrage informatii
     * @param arguments - Argumentele comenzilor (Nu este cazul aici)
     * @return - Mesajul comenzii care se executa
     */
    private String message(String key, Locale locale, String ... arguments) {
        resourceBundle =  ResourceBundle.getBundle(baseName, locale);
        String pattern = resourceBundle.getString(key);
        String message = new MessageFormat(pattern).format(arguments);

        return message;
    }
}
