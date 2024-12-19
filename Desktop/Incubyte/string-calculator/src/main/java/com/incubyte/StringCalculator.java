package com.incubyte;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter = ",|\n";

        if (numbers.startsWith("//")) {
            String delimiterPart = numbers.split("\n")[0].substring(2);
            delimiter = delimiterPart.contains("[") ? 
                extractDelimiters(delimiterPart) : delimiterPart;  
            numbers = numbers.split("\n", 2)[1]; 
        }

        String[] tokens = numbers.split(delimiter);

        int sum = 0;
        StringBuilder negativeNumbers = new StringBuilder();

        for (String token : tokens) {
            int number = Integer.parseInt(token);
            if (number < 0) {
                if (negativeNumbers.length() > 0) {
                    negativeNumbers.append(", ");
                }
                negativeNumbers.append(number);
            } else {
                sum += number;
            }
        }

        if (negativeNumbers.length() > 0) {
            throw new IllegalArgumentException("negative numbers not allowed [" + negativeNumbers + "]");
        }

        return sum;
    }

    private String extractDelimiters(String delimiterPart) {
        if (delimiterPart.contains("[") && delimiterPart.contains("]")) {
            Pattern pattern = Pattern.compile("\\[(.*?)\\]");
            Matcher matcher = pattern.matcher(delimiterPart);
            StringBuilder delimiters = new StringBuilder();
            while (matcher.find()) {
                delimiters.append(Pattern.quote(matcher.group(1))).append("|");
            }
            return delimiters.substring(0, delimiters.length() - 1);
        } else {
            return delimiterPart;
        }
    }
}
