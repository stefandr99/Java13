package com;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class SetLocale implements Command{
    private final String baseName = "Messages";
    private Locale locale;
    private ResourceBundle resourceBundle;

    /**
     * Executarea comenzii de schimbare a localului
     * @param params Limba si tara pe care dorim sa le setam ca default
     */
    public void execute(String[] params) {
        Locale.setDefault(new Locale(params[1], params[2]));
        this.locale = Locale.getDefault();
        System.out.println(this.message("locale.set", Locale.getDefault(), locale.getLanguage()));
    }

    /**
     * Metoda prin care se extrage raspunsul comenzii SetLocale
     * @param key - Cheia valorii pe care trebuie sa o obtinem din fisierul .properties
     * @param locale - Localul care urmeaza sa fie setat ca default
     * @param arguments - Limba si tara pe care dorim sa le setam ca default
     * @return - Raspunsul comenzii
     */
    private String message(String key, Locale locale, String ... arguments) {
        resourceBundle =  ResourceBundle.getBundle(baseName, locale);
        String pattern = resourceBundle.getString(key);
        String message = new MessageFormat(pattern).format(arguments);
        return message;
    }
}
