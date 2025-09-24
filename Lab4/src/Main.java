import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 2, 3, 4, 4, 5);
        System.out.println("Среднее значение: " + average(numbers));

        List<String> strings = List.of("java", "python", "javaScript");
        System.out.println("Трансформированные строки: " + transformStrings(strings));

        System.out.println("Квадраты уникальных элементов: " + squaresOfUnique(numbers));

        List<String> collection = List.of("a", "b", "c", "d");
        System.out.println("Последний элемент: " + getLastElement(collection));

        int[] numbers2 = {1, 2, 3, 4, 5, 6};
        System.out.println("Сумма чётных чисел: " + sumEvenNumbers(numbers2));
        int[] numbers3 = {1, 3, 5, 7, 9, 11};
        System.out.println("Сумма чётных чисел (отсутствуют в массиве): " + sumEvenNumbers(numbers3));

        List<String> words = List.of("apple", "banana", "avocado", "cherry", "cherry", "");
        System.out.println("Map из строк: " + stringsToMap(words));
    }

    /**
     * Метод, возвращающий среднее значение списка целых чисел
     */
    public static double average(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElseThrow();
    }

    /**
     * Метод, приводящий все строки в списке в верхний регистр и добавляющий к ним префикс «_new_»
     */
    public static List<String> transformStrings(List<String> strings) {
        return strings.stream()
                .map(str -> "_new_" + str.toUpperCase())
                .collect(Collectors.toList());
    }

    /**
     * Метод, возвращающий список квадратов всех встречающихся только один раз элементов списка
     */
    public static List<Integer> squaresOfUnique(List<Integer> numbers) {
        return numbers.stream()
                .collect(Collectors.groupingBy(
                        num -> num,
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(entry -> entry.getKey() * entry.getKey())
                .collect(Collectors.toList());
    }

    /**
     * Метод, принимающий на вход коллекцию и возвращающий ее последний элемент или кидающий исключение, если коллекция пуста
     */
    public static <T> T getLastElement(Collection<T> collection) {
        return collection.stream()
                .reduce((first, second) -> second)
                .orElseThrow(() -> new NoSuchElementException("Коллекция пуста"));
    }

    /**
     * Метод, принимающий на вход массив целых чисел, возвращающий сумму чётных чисел или 0, если чётных чисел нет
     */
    public static int sumEvenNumbers(int[] numbers) {
        return Arrays.stream(numbers)
                .filter(num -> num % 2 == 0)
                .sum();
    }

    /**
     * Метод, преобразовывающий все строки в списке в Map,
     * где первый символ – ключ, оставшиеся – значение
     */
    public static Map<Character, String> stringsToMap(List<String> strings) {
        return strings.stream()
                .filter(str -> str.length() > 1)
                .collect(Collectors.toMap(
                        str -> str.charAt(0),                     // Ключ - первый символ
                        str -> str.substring(1),        // Значение - остальные символы
                        (existing, replacement) -> existing // Обработка дубликатов ключей
                ));
    }
}