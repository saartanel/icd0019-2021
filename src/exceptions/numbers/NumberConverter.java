package exceptions.numbers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Properties;

public class NumberConverter {

    private String lang;
    private Properties properties;

    public NumberConverter(String lang) {

        this.lang = lang;

        String filePath = "src/exceptions/numbers/numbers_" + lang + ".properties";

        this.properties = new Properties();
        FileInputStream is = null;

        try {
            is = new FileInputStream(filePath);

            InputStreamReader reader = new InputStreamReader(
                    is, StandardCharsets.UTF_8);

            properties.load(reader);
            if (properties.isEmpty()){
                throw new MissingTranslationException("File is empty");
            }
        } catch (IOException e) {
            throw new MissingLanguageFileException(lang, e);
        } catch (IllegalArgumentException e) {
            throw new BrokenLanguageFileException(lang, e);
        } finally {
            closeFile(is);
        }

    }

    public String numberInWords(Integer number) {


        if (number >= 0 && number <= 10){
            return properties.getProperty(String.valueOf(number));
        }

        int hundreds = number / 100;
        int tens = number - (hundreds * 100);
        int ones = tens % 10;

        System.out.println(number);
        String output = "";

        if (hundreds > 0){
            output += readNumber(hundreds);
            output += properties.getProperty("hundreds-before-delimiter") + properties.getProperty("hundred");
            if (tens > 0){
                output += properties.getProperty("hundreds-after-delimiter");
            }
        }

        if (tens > 0){
            if (properties.containsKey(String.valueOf(tens))){
                output += readNumber(tens);
            } else {
                if (tens < 20){
                    output += readNumber(ones) + properties.getProperty("teen");
                } else if (properties.containsKey(String.valueOf(tens - ones))){
                    output += readNumber(tens - ones);
                    if (ones > 0){
                        output += properties.getProperty("tens-after-delimiter") + readNumber(ones);
                    }
                } else {
                    output += readNumber((tens - ones) / 10) + properties.getProperty("tens-suffix");
                    if (ones > 0){
                        output += properties.getProperty("tens-after-delimiter") + readNumber(ones);
                    }
                }
            }
        }

        return output;

    }

    public String readNumber(Integer number){
        try {
            if (properties.containsKey(String.valueOf(number))) {
                return properties.getProperty(String.valueOf(number));
            } else {
                throw new MissingTranslationException(String.valueOf(number));
            }
        } catch (Exception e) {
            throw new BrokenLanguageFileException(lang, e);
        }
    }


    private static void closeFile(FileInputStream is) {
        if (is == null) {
            return;
        }

        try {
            is.close();
        } catch (IOException ignore) {}
    }

}
