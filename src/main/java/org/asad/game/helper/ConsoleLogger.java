package org.asad.game.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * this util class takes the responsibility to print anything.
 */
public class ConsoleLogger {

    /**
     * print a string
     * @param string
     */
    public static void print(String string) {
        System.out.print(string);
    }

    /**
     * print a string with next line
     * @param string
     */
    public static void println(String string) {
        System.out.println(string);
    }

    /**
     * print a poster by reading the fileName.
     * @param fileName
     */
    public static void printPoster(String fileName) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName), StandardCharsets.UTF_8))) {
            String st;
            while ((st = br.readLine()) != null) {
                println(st);
            }
        } catch (IOException e) {
            println(e.getMessage());
        }
    }

}
