package pl.edu.wszib.library;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.edu.wszib.library.configuration.AppConfiguration;
import pl.edu.wszib.library.core.Core;

public class Library {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        AppConfiguration.class);
        Core core = context.getBean(Core.class);
        core.run();
    }
}
