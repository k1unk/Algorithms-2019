package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     *
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     *
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     *
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     *
     * Время: O(N^2), N = количество строк в inputName
     * Память: O(N)
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) throws IOException {
        FileReader fr = new FileReader(inputName);
        Scanner scan = new Scanner(fr);
        ArrayList<Double> arr = new ArrayList<>();
        double value;

        while (scan.hasNextLine()) {
            value = Double.parseDouble(scan.nextLine());
            arr.add(value);
        }

        double max = 0;
        int firstInPair = 0;
        int secondInPair = 0;
        for (int i = 0; i < arr.size(); i++) {
            double nowI = arr.get(i);
            for (int j = i; j < arr.size(); j++) {
                double nowJ = arr.get(j);
                double now = nowJ - nowI;
                if (now > max) {
                    max = now;
                    firstInPair = i + 1;
                    secondInPair = j + 1;
                }
            }
        }
        return new Pair(firstInPair, secondInPair);
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     *
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     *
     * 1 2 3
     * 8   4
     * 7 6 5
     *
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     *
     * 1 2 3
     * 8   4
     * 7 6 х
     *
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     *
     * 1 х 3
     * 8   4
     * 7 6 Х
     *
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     *
     * 1 Х 3
     * х   4
     * 7 6 Х
     *
     * 1 Х 3
     * Х   4
     * х 6 Х
     *
     * х Х 3
     * Х   4
     * Х 6 Х
     *
     * Х Х 3
     * Х   х
     * Х 6 Х
     *
     * Х Х 3
     * Х   Х
     * Х х Х
     *
     * Общий комментарий: решение из Википедии для этой задачи принимается,
     * но приветствуется попытка решить её самостоятельно.
     * Время: O(N), N = menNumber
     * Память: O(1)
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        int res = 0;
        for (int i = 1; i <= menNumber; i++) {
            res = (res + choiceInterval) % i;
        }
        return res + 1;
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     *
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     *
     * Время: O(N*M), N = количество строк в first, M = количество строк в second
     * Память: O(N*M)
     */
    static public String longestCommonSubstring(String first, String second) {
        int maxLength = 0;
        int endPosition = 0;
        int[][] arr = new int[first.length()][second.length()];
        for (int i = 0; i < first.length(); i++) {
            for (int j = 0; j < second.length(); j++) {
                if (first.charAt(i) == second.charAt(j)) {
                    if (i != 0 && j != 0 && arr[i - 1][j - 1] != 0) {
                        arr[i][j] = arr[i - 1][j - 1] + 1;
                        if (arr[i - 1][j - 1] + 1 > maxLength) {
                            maxLength = arr[i - 1][j - 1] + 1;
                            endPosition = i + 1;
                        }
                    } else arr[i][j] = 1;
                }
            }
        }
        return first.substring(endPosition - maxLength, endPosition);
    }

    /**
     * Число простых чисел в интервале
     * Простая
     *
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     *
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     *
     * Время: O(N*sqrt(N)), N = limit
     * Память: O(1)
     */
    static public int calcPrimesNumber(int limit) {
        int res = 1;
        if (limit <= 1) return 0;
        for (int i = 3; i < limit + 1; i += 2) {
            if (isPrime(i)) {
                res++;
            }
        }
        return res;
    }

    private static boolean isPrime(int n) {
        for (int m = 3; m < Math.sqrt(n) + 1; m += 2) {
            if (n % m == 0) return false;
        }
        return true;
    }

    /**
     * Балда
     * Сложная
     *
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     *
     * И Т Ы Н
     * К Р А Н
     * А К В А
     *
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     *
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     *
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     *
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */
    static public Set<String> baldaSearcher(String inputName, Set<String> words) {
        throw new NotImplementedError();
    }
}
