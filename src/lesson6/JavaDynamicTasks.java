package lesson6;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     *
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     *
     * Трудоемкость = O(first*second)
     * Ресурсоемкость = O(first*second)
     */
    public static String longestCommonSubSequence(String first, String second) {
        StringBuilder str = new StringBuilder();
        int sizeX = first.length();
        int sizeY = second.length();
        int value;
        int[][] arr = new int[sizeX + 1][sizeY + 1];

        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                arr[i + 1][j + 1] = Math.max(arr[i][j + 1], arr[i + 1][j]);
                if (first.charAt(i) == second.charAt(j)) {
                    arr[i + 1][j + 1] = arr[i][j] + 1;
                }
            }
        }

        int x = sizeX;
        int y = sizeY;
        value = arr[x][y];

        while (value != 0) {
            while (arr[x - 1][y] != value - 1) x--;
            while (arr[x][y - 1] != value - 1) y--;
            x--;
            y--;
            value--;
            str.append(first.charAt(x));
        }

        return str.reverse().toString();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     *
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     *
     * Трудоемкость = O(n^2)
     * Ресурсоемкость = O(n), n = количество чисел в list
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        int size = list.size();
        if (size == 0 || size == 1) return list;

        int[] length = new int[size];
        int[] prev = new int[size];

        for (int i = 0; i < size; i++) {
            length[i] = 1;
            prev[i] = -1;
            for (int j = 0; j < i; j++) {
                if (list.get(j) < list.get(i) && length[j] + 1 > length[i]) {
                    length[i] = length[j] + 1;
                    prev[i] = j;
                }
            }
        }

        int pos = 0;
        int maxLength = length[0];

        for (int i = 0; i < size; i++) {
            if (length[i] > maxLength) {
                pos = i;
                maxLength = length[i];
            }
        }

        List<Integer> array = new ArrayList<>();

        while (pos != -1) {
            array.add(list.get(pos));
            pos = prev[pos];
        }

        Collections.reverse(array);
        return array;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     *
     * В файле с именем inputName задано прямоугольное поле:
     *
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     *
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     *
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     *
     * Трудоемкость = O(n), n = количество символов в файле
     * Ресурсоемкость = O(m), m = количество чисел в строке
     */
    public static int shortestPathOnField(String inputName) throws FileNotFoundException {
        int nowX = 0;
        int nowY = 0;
        int lastSpace = 0;
        List<Integer> thisLine = new ArrayList<>();
        List<Integer> previousLine = new ArrayList<>();
        String str;

        try (Scanner scan = new Scanner(new FileReader(inputName))) {
            while (scan.hasNextLine()) {
                str = scan.nextLine();
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) == ' ') {
                        int number = Integer.parseInt(str.substring(lastSpace, i));

                        if (nowY == 0) {
                            if (nowX == 0) thisLine.add(number);
                            else thisLine.add(number + thisLine.get(nowX - 1));
                        }
                        else {
                            if (nowX == 0) thisLine.add(number + previousLine.get(0));
                            else thisLine.add(number + Math.min(Math.min(thisLine.get(nowX - 1),
                                    previousLine.get(nowX - 1)), previousLine.get(nowX)));
                        }

                        lastSpace = i + 1;
                        nowX++;
                    }
                }

                int number = Integer.parseInt(str.substring(lastSpace));

                if (nowY == 0) {
                    if (nowX == 0) thisLine.add(number);
                    else thisLine.add(thisLine.get(nowX - 1) + number);
                }
                else {
                    if (nowX == 0) thisLine.add(number + (previousLine.get(0)));
                    else thisLine.add(number + Math.min(Math.min(thisLine.get(nowX - 1),
                            previousLine.get(nowX - 1)), previousLine.get(nowX)));
                }

                previousLine.clear();
                previousLine.addAll(thisLine);
                thisLine.clear();

                lastSpace = 0;
                nowX = 0;
                nowY++;
            }
        }
        return previousLine.get(previousLine.size() - 1);
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
