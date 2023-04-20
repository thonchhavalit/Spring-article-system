package com.istad.springthymleafpart1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

@SpringBootApplication
public class SpringThymleafPart1Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringThymleafPart1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Locale currentLocal = LocaleContextHolder.getLocale();
        String countryName = currentLocal.getDisplayCountry();

        System.out.println("Country name is : "+countryName);

        String languageCode = currentLocal.getLanguage();
        System.out.println("Language Code is : "+languageCode);


        //write code to show all the language code
        String[] languages = Locale.getISOLanguages();
        for(String lang : languages){
            Locale locale = new Locale(lang);
            System.out.println("Language code are:" + locale.getLanguage()+"->"+locale.getDisplayLanguage());
        }
    }
}
