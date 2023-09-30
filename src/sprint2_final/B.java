package sprint2_final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
-- ПРИНЦИП РАБОТЫ --
Реализация была сделана по описанию в задаче https://contest.yandex.ru/contest/22781/problems/B/?success=82598054#2989/2020_04_23/jYTzKhCd4T
Где в стеке находятся операнды, а при получении операции они изымаются, совершается операция над ними, а результат
отправляется обратно в стек.

Основная сложность была в приведении результата деления к нижнему целому числу, но оказалось, что Math содержит
необходимую функцию.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Все операции внутри алгоритма совершаются за O(1), т.к. в них нет циклов.
Для хранения операндов используется Stack, у которого методы pop() и push() работают за О(1)
Алгоритма взят из задачи
https://contest.yandex.ru/contest/22781/problems/B/?success=82598054#2989/2020_04_23/jYTzKhCd4T

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Алгоритм один раз проходит по входному массиву слева на право, все операции внутри совершаются за O(1).
отсюда итоговая сложность O(n).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Стек внутри содержит операнды, количество которых меньше n, где n - длинна входящего выражения.
Но, т.к. в задании используются только бинарные операции, то в стеке будет находится максимум 2 значения.
Отсюда я делаю вывод, что пространственная сложность O(K), но k = [1,2]
*/

//https://contest.yandex.ru/contest/22781/run-report/82598054/
public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<String> inputList = readList(reader);
            int result = calculate(inputList);
            System.out.print(result + " ");
        }
    }

    private static int calculate(List<String> inputList) {
        return (new Calculator()).calculate(inputList);
    }

    private static List<String> readList(BufferedReader reader) throws IOException {
        return new ArrayList<>(Arrays.asList(reader.readLine().split(" ")));
    }

    /**
     * Для вычисления значения выражения, записанного в обратной польской нотации, нужно считывать
     * выражение слева направо и придерживаться следующих шагов:
     *
     * Обработка входного символа:
     * Если на вход подан операнд, он помещается на вершину стека.
     * Если на вход подан знак операции, то эта операция выполняется над требуемым количеством значений,
     * взятых из стека в порядке добавления. Результат выполненной операции помещается на вершину стека.
     * Если входной набор символов обработан не полностью, перейти к шагу 1.
     * После полной обработки входного набора символов результат вычисления выражения находится в вершине стека.
     * Если в стеке осталось несколько чисел, то надо вывести только верхний элемент.
     */
    public static class Calculator {

        private Stack<Integer> stack;

        public Calculator() {
            stack = new Stack<>();
        }
        /**
          O(n)
         */
        public int calculate(List<String> expression) {
            for (String item : expression) {
                switch (item) {
                    case "+" : {
                        int val1 = stack.pop();
                        int val2 = stack.pop();
                        int res = val2 + val1;
                        stack.push(res);
                        break;
                    }
                    case "-" : {
                        int val1 = stack.pop();
                        int val2 = stack.pop();
                        int res = val2 - val1;
                        stack.push(res);
                        break;
                    }
                    case "*" : {
                        int val1 = stack.pop();
                        int val2 = stack.pop();
                        int res = val2 * val1;
                        stack.push(res);
                        break;
                    }
                    case "/" : {
                        int val1 = stack.pop();
                        int val2 = stack.pop();
                        int res = Math.floorDiv(val2, val1);
                        stack.push(res);
                        break;
                    }
                    default: {
                        stack.push(Integer.parseInt(item));
                        break;
                    }
                }
            }
            return stack.pop();
        }

    }
}
