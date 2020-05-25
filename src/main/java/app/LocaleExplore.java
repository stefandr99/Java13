package app;

import com.Command;
import com.DisplayLocales;
import com.SetLocale;
import com.Info;



import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplore {
    private static final String baseName = "Messages";
    private Locale locale;
    private static ResourceBundle resourceBundle;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(message("prompt", Locale.getDefault()));
            String commandName = scanner.next();
            if (commandName.equalsIgnoreCase("exit")) {
                break;
            }
            String[] params = scanner.nextLine().split(" ");
            if(commandName.compareTo("DisplayLocales") == 0) {

                Command command = new DisplayLocales();
                command.execute(params);
            }
            else if(commandName.compareTo("SetLocale") == 0) {

                Command command = new SetLocale();
                command.execute(params);
            }
            else if(commandName.compareTo("Info") == 0) {
                Command command = new Info();
                command.execute(params);
            }
            else {
                System.out.println(message("invalid", Locale.getDefault()));
            }

        }
    }

    private static String message(String key, Locale locale, String... arguments) {
        resourceBundle =  ResourceBundle.getBundle(baseName, locale);
        String pattern = resourceBundle.getString(key);
        String message = new MessageFormat(pattern).format(arguments);
        return message;
    }

}
