package pl.edu.wszib.library.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
@ComponentScan({
        "pl.edu.wszib.library.authentication",
        "pl.edu.wszib.library.core",
        "pl.edu.wszib.library.database",
        "pl.edu.wszib.library.gui"
})
public class AppConfiguration {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}
