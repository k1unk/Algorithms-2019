package lesson1;

import kotlin.NotImplementedError;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     * <p>
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     * <p>
     * Пример:
     * <p>
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     * <p>
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     * <p>
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     *
     * Время: O(N*logN), N = количество строк в inputName
     * Память: O(N)
     */
    static public void sortTimes(String inputName, String outputName) throws IOException {
        String str;
        int hours, minutes, seconds, AMorPM, secondsAll;
        int size = 0;
        List<Integer> array = new ArrayList<>();
        try (Scanner scan = new Scanner(new FileReader(inputName))) {
            while (scan.hasNextLine()) {
                str = scan.nextLine();

                hours = Integer.parseInt(str.substring(0, 2));
                if (hours == 12) hours = 0;
                minutes = Integer.parseInt(str.substring(3, 5));
                seconds = Integer.parseInt(str.substring(6, 8));
                if (str.charAt(9) == 'A') AMorPM = 0;
                else AMorPM = 43200; //60*60*12

                secondsAll = hours * 3600 + minutes * 60 + seconds + AMorPM;

                array.add(secondsAll);
                size++;
            }
        }

        int[] arraySorted = new int[size];
        for (int i = 0; i < size; i++) {
            arraySorted[i] = array.get(i);
        }
        Sorts.mergeSort(arraySorted);

        StringBuilder text = new StringBuilder();
        for (int y = 0; y < size; y++) {
            String AMorPMString = "AM";
            if (arraySorted[y] >= 43200) {
                arraySorted[y] -= 43200;
                AMorPMString = "PM";
            }
            hours = arraySorted[y] / 3600;
            if (hours == 0) hours = 12;
            minutes = arraySorted[y] / 60 % 60;
            seconds = arraySorted[y] % 60;

            String zeroBeforeHours = "";
            String zeroBeforeMinutes = "";
            String zeroBeforeSeconds = "";
            if (hours < 10) zeroBeforeHours = "0";
            if (minutes < 10) zeroBeforeMinutes = "0";
            if (seconds < 10) zeroBeforeSeconds = "0";
            text.append(zeroBeforeHours + hours + ":" + zeroBeforeMinutes + minutes + ":" +
                    zeroBeforeSeconds + seconds + " " + AMorPMString);
            text.append("\n");
        }
        try (FileWriter fw = new FileWriter(outputName)) {
            fw.write(text.toString());
        }
    }

    /**
     * Сортировка адресов
     * <p>
     * Средняя
     * <p>
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     * <p>
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     * <p>
     * Людей в городе может быть до миллиона.
     * <p>
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     * <p>
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     * <p>
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     * <p>
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     * <p>
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     * <p>
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     *
     * Время: O(N), N = количество строк в inputName
     * Память: O(N)
     */
    static public void sortTemperatures(String inputName, String outputName) throws IOException {
        int[] array = new int[7732];
        int nowInteger;
        double nowDouble;
        try (Scanner scan = new Scanner(new FileReader(inputName))) {
            while (scan.hasNextLine()) {
                nowDouble = Double.parseDouble(scan.nextLine()) * 10;
                nowInteger = (int) nowDouble;
                array[nowInteger + 2730]++;
            }
        }

        StringBuilder text = new StringBuilder();
        int count;
        for (int i = 0; i < 7732; i++) {
            if (array[i] != 0) {
                count = array[i];
                for (int j = 0; j < count; j++) {
                    text.append((i - 2730) / 10.0);
                    text.append("\n");
                }
            }
        }
        try (FileWriter fw = new FileWriter(outputName)) {
            fw.write(text.toString());
        }
    }

    /**
     * Сортировка последовательности
     * <p>
     * Средняя
     * (Задача взята с сайта acmp.ru)
     * <p>
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     * <p>
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     * <p>
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     * <p>
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     *
     * Время: O(N), N = количество строк в inputName
     * Память: O(N)
     */
    static public void sortSequence(String inputName, String outputName) throws IOException {
        List<Integer> arrStart = new ArrayList<>();
        int now;
        int startSize = 0;
        try (Scanner scan = new Scanner(new FileReader(inputName))) {
            while (scan.hasNextLine()) {
                now = Integer.parseInt(scan.nextLine());
                arrStart.add(now);
                startSize++;
            }
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < startSize; i++) {
            map.put(arrStart.get(i), 0);
        }
        for (int i = 0; i < startSize; i++) {
            int key = arrStart.get(i);
            int value = map.get(key);
            map.put(key, value + 1);
        }

        int mapSize = map.size();
        int minNumber = 0;
        int maxRepeats = 0;
        for (int i = 0; i < mapSize; i++) {
            int key = arrStart.get(i);
            int value = map.get(key);
            if (value == maxRepeats) {
                if (minNumber > key) {
                    minNumber = key;
                }
            }
            if (value > maxRepeats) {
                maxRepeats = value;
                minNumber = key;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < startSize; i++) {
            if (arrStart.get(i) != minNumber) {
                result.add(arrStart.get(i));
            }
        }
        for (int i = 0; i < maxRepeats; i++) {
            result.add(minNumber);
        }

        StringBuilder text = new StringBuilder();
        for (Object o : result) {
            text.append(o);
            text.append("\n");
        }
        try (FileWriter fw = new FileWriter(outputName)) {
            fw.write(text.toString());
        }
    }

    /**
     * Соединить два отсортированных массива в один
     * <p>
     * Простая
     * <p>
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     * <p>
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     * <p>
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
