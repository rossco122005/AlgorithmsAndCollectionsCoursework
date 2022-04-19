//This code was taken from the library given from UWS's module Structures and Algorithms
//It defines functions to allow the program to take any kind of input from the user

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Input {
    private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public static Character getCharacter(String prompt) {
        Character value;
        System.out.println(prompt);
        try {
            value = Input.input.readLine().charAt(0);
        } catch (Exception error) {
            // error condition
            value = null;
        }
        return value;
    }

    public static Double getDouble(String prompt) {
        Double value;
        System.out.println(prompt);
        try {
            value = Double.parseDouble(Input.input.readLine());
        } catch (Exception error) {
            // error condition
            throw new NumberFormatException();
        }
        return value;
    }

    public static Integer getInteger(String prompt) {
        Integer value;
        System.out.println(prompt);
        try {
            value = Integer.parseInt(Input.input.readLine());
        } catch (Exception error) {
            // error condition
            throw new NumberFormatException();
        }
        return value;
    }

    public static String getString(String prompt) {
        String string;
        System.out.println(prompt);
        try {
            string = Input.input.readLine();
        } catch (Exception error) {
            // error condition
            string = null;
        }
        return string;
    }
}
