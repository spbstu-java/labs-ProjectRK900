import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        try {
            System.out.print("Введите имя файла словаря: ");
            String dictFile = scanner.nextLine();
            // dictFile = "Lab3\\dictionary.txt";
            var translator = new Translator(dictFile);

            System.out.println("Введите текст для перевода:");
            String input = scanner.nextLine();
            // input = "The big dog look after the mouse, but the dog look to the window and the cat look forward to the big dog";

            System.out.println("Перевод:\n" + translator.translate(input));

        } catch (InvalidFileFormatException e) {
            System.err.println("Ошибка формата словаря:\n\t" + e);
        } catch (FileReadException e) {
            System.err.println(e);
        }

    }
}