package lesson1;

import kotlin.NotImplementedError;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

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
        FileReader fr = new FileReader(inputName);
        Scanner scan = new Scanner(fr);
        StringBuilder text = new StringBuilder();
        String str;
        int hours, minutes, seconds, AMorPM, secondsAll;
        int size = 0;
        ArrayList<Integer> array = new ArrayList<>();

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

        int[] arraySorted = new int[size];
        for (int i = 0; i < size; i++) {
            arraySorted[i] = array.get(i);
        }
        Sorts.mergeSort(arraySorted);

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
        fr.close();
        FileWriter f = new FileWriter(outputName);
        f.write(text.toString());
        f.close();
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
     * Время: O(N*logN), N = количество строк в inputName
     * Память: O(N)
     */
    static public void sortTemperatures(String inputName, String outputName) throws IOException {
        FileReader fr = new FileReader(inputName);
        Scanner scan = new Scanner(fr);
        StringBuilder text = new StringBuilder();
        int nowInteger;
        double nowDouble;
        ArrayList<Integer> arr = new ArrayList<>();
        int size = 0;
        while (scan.hasNextLine()) {
            nowDouble = Double.parseDouble(scan.nextLine()) * 10;
            nowInteger = (int) nowDouble;
            arr.add(nowInteger);
            size++;
        }

        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = arr.get(i);
        }
        Sorts.mergeSort(array);

        double[] res = new double[size];
        for (int i = 0; i < size; i++) {
            res[i] = array[i] / 10.0;
        }

        for (Object o : res) {
            text.append(o);
            text.append("\n");
        }

        fr.close();
        FileWriter f = new FileWriter(outputName);
        f.write(text.toString());
        f.close();
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
     * Время: O(N*logN), N = количество строк в inputName
     * Память: O(N)
     */
    static public void sortSequence(String inputName, String outputName) throws IOException {
        FileReader fr = new FileReader(inputName);
        Scanner scan = new Scanner(fr);
        StringBuilder text = new StringBuilder();
        ArrayList<Integer> arrayAtStart = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        int now;
        int startSize = 0;

        while (scan.hasNextLine()) {
            now = Integer.parseInt(scan.nextLine());
            arrayAtStart.add(now);
            startSize++;
        }

        int[] arraySorted = new int[startSize];
        for (int i = 0; i < startSize; i++) {
            arraySorted[i] = arrayAtStart.get(i);
        }
        Sorts.mergeSort(arraySorted);

        int maxRepeats = 0;
        int maxNumber = 0;
        int numberNow = 0;
        int repeatsNow = 0;
        for (int i = 0; i < startSize; i++) {
            if (arraySorted[i] == numberNow) {
                repeatsNow++;
                if (repeatsNow > maxRepeats) {
                    maxRepeats = repeatsNow;
                    maxNumber = numberNow;
                }
            } else {
                repeatsNow = 1;
            }
            numberNow = arraySorted[i];
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < startSize; i++) {
            if (arrayAtStart.get(i) != maxNumber) {
                result.add(arrayAtStart.get(i));
            }
        }
        for (int i = 0; i < maxRepeats; i++) {
            result.add(maxNumber);
        }
        for (Object o : result) {
            text.append(o);
            text.append("\n");
        }
        fr.close();
        FileWriter f = new FileWriter(outputName);
        f.write(text.toString());
        f.close();
    }
/*   Вроде бы время=O(N), но работает медленнее
        FileReader fr = new FileReader(inputName);
        Scanner scan = new Scanner(fr);
        StringBuilder text = new StringBuilder();
        ArrayList<Integer> arrStart = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        int now;
        int startSize=0;
        int max=0;

        while (scan.hasNextLine()) {
            now = Integer.parseInt(scan.nextLine());
            set.add(now);
            arrStart.add(now);
            startSize++;
        }

        int setSize = set.size();
        ArrayList<Integer> setToArray = new ArrayList<>();
        setToArray.addAll(set);
        int[] arrayOfRepeats = new int[setSize];
        for (int i = 0; i < startSize; i++) {
            arrayOfRepeats[setToArray.indexOf(arrStart.get(i))]+=1;
        }

        int maxNumber=0;
        int maxRepeats=0;
        for (int i = 0; i < setSize; i++) {
                if (arrayOfRepeats[i]==maxRepeats) {
                    if (maxNumber>setToArray.get(i)) {
                        maxRepeats=arrayOfRepeats[i];
                        maxNumber=setToArray.get(i);
                    }
                }
                if (arrayOfRepeats[i]>maxRepeats) {
                    maxRepeats = arrayOfRepeats[i];
                    maxNumber = setToArray.get(i);
                }
        }

        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < startSize; i++) {
            if (arrStart.get(i)!=maxNumber) {
                res.add(arrStart.get(i));
            }
        }
        for (int i=0; i<maxRepeats; i++ ) {
            res.add(maxNumber);
        }
        for (Object o : res) {
            text.append(o);
            text.append("\n");
        }
        fr.close();
        FileWriter f = new FileWriter(outputName);
        f.write(text.toString());
        f.close();
*/

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
