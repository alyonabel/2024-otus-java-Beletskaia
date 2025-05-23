package ru.otus.java.hw12;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static String path = "Homeworks/src/test/files";

    public static void readFile(String fileName) {
        try (FileInputStream fis = new FileInputStream(path + "/" + fileName + ".txt");
             BufferedInputStream bis = new BufferedInputStream(fis);
             InputStreamReader in = new InputStreamReader(bis)) {
            int n = in.read();
            System.out.println(" File Contents:");
            while (n != -1) {
                System.out.print((char) n);
                n = in.read();
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file with such name doesn't exist!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(String data, String fileName) {
        try (FileOutputStream fos = new FileOutputStream(path + "/" + fileName + ".txt", true);
             BufferedOutputStream out = new BufferedOutputStream(fos)) {
            byte[] buffer = data.getBytes(StandardCharsets.UTF_8);
            out.write(10);
            out.write(buffer);
            System.out.println("Your text has been saved to the file successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! Let's  start to write down your thoughts!");

        File file = new File(path);
        System.out.println("List of current files: " + Arrays.toString(file.listFiles()));

        System.out.println("Please type one of the file name from the list above");
        String fileName = in.nextLine();
        System.out.println("Your choice: " + fileName + ".txt");
        readFile(fileName);

        System.out.println("");
        System.out.println("Please type your text for writing to file and press ENTER");
        String text = in.nextLine();
        writeFile(text, fileName);

        System.out.println("Thanks for using my application!");
        in.close();
    }
}
