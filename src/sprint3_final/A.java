package sprint3_final;

/*
-- ПРИНЦИП РАБОТЫ --
Задача сводится к 3 действиям:
1) если в массиве один элемент, то он просто проверяется на равенство k
2) иначе, я ищу элемент разлома так, чтобы элемент справа был больше элемента слева
3) делю исходный массив по элементу разлома, и делаю binary search до k

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Поиск элемента разлома (pivot) осуществляется через binary search.
Поиск K по 2 частям массива от 0 до pivot и от pivot до length, также осуществляется через binary search.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Поиск элемента разлома (pivot) осуществляется через binary search и имеет сложность O(logN).
Поиск K по 2 частям массива от 0 до pivot и от pivot до length, также осуществляется через binary search и имеет
сложность O(log(M + L)), где M + L = N

Результирующая сложность 2 * O(logN), а следовательно O(logN)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Алгоритм осуществляется на входном массиве и дополнительных структур данных не требует.
Следовательно памяти потребляется О(1), но из-за того что применяется рекурсия, будет затрачена дополнительная системная память
O(logN), где N - количество рекурсивных вызовов.

*/
//https://contest.yandex.ru/contest/23815/run-report/82963875/
public class A {
    //O(logn)
    public static int brokenSearch(int[] arr, int k) {
        if (arr.length == 1) {
            return arr[0] == k ? 0 : -1;
        }

        //нахожу индекс разлома
        int pivot = getPivot(arr, 0, arr.length - 1);
        //продолжюа барный поиск по частям до разлома и после

        if (k >= arr[pivot] && k <= arr[arr.length - 1]) {
            return search(arr, pivot, arr.length - 1, k);
        } else if (k >= arr[0]) {
            return search(arr, 0, pivot - 1, k);
        }
        // если k не входит в упорядоченные части разлома, значит его нет в массиве
        return -1;
    }

    //O(logn)
    private static int search(int[] arr, int left, int right, int k) {
        if (left >= right) {
            return arr[left] == k ? left : -1;
        }
        int mid = (left + right) / 2;

        if (arr[mid] == k) {
            return mid;
        } else if (arr[mid] > k) {
            return search(arr, left, mid, k);
        } else {
            return search(arr, mid + 1, right, k);
        }
    }

    //O(logn)
    private static int getPivot(int[] arr, int left, int right) {
        if (left >= right) {
            return left;
        }
        int mid = (left + right) / 2;
        if (arr[mid] > arr[right]) {
            return getPivot(arr, mid + 1, right);
        } else {
            return getPivot(arr, left, mid);
        }
    }

//    public static void main(String[] args) throws IOException {
//        int[] arr = {1};
//        brokenSearch(arr, 1);
//    }
}
