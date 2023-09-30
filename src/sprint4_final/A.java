package sprint4_final;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
-- ПРИНЦИП РАБОТЫ --
Решение состоит из двух шагов: индексация документов и поиск.
Индексация - это подсчет количества вхождений слова по каждому документу.
Поиск - тоже делится на два шага: подсчет суммы вхождений уникальных слов из поискового запроса по каждому
документу и выбор 5 лучших документов.
Подсчет суммы вхождений уникальных слов - это проход по всем документам, в котором есть i-е слово из запроса,
и сохранение суммы вхождения относительно документа.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Решение основывается на том, что слово из запроса должно быть ключом в HashMap с документом и количеством
вхождений (HashMap<String, HashMap<Integer, Integer>>)
После этого, мы сможем за О(1) получить все необходимые данные, чтобы произвести поиск 5 подходящих документов.
Чтобы произвести подсчет 5 лучших, нам приходится трансформировать данные так, чтобы у нас была сумма вхождений
уникальных слов относительно индекса документа (HashMap<Integer, Integer>).
После этих преобразований можно провести сортивку сначала относительно количества слов, а потом по индексу документа.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Метод findRelevantRequests имеет сложность O(N + M), где N - количество документов, M - количество запросов.
Внутри findRelevantRequests вызывается метод addDocument, который имеет сложность О(N), т.к. необходимо пройти по всем
словам в документе, чтобы их проиндексировать. Индексация осуществляется подсчетом слов по документам и сохранием
результата за О(1).
Также внутри findRelevantRequests вызывается метод search, который имеет сложность О(M * K), где К - количество вхождений
слова подокументно. В свою очередь внутри search вызывается getBestFive, который имеет сложность О(KlogK), т.к. в нем
содержится сортировка по убыванию.
Итого: O(N + M) * (O(N) + О(M * K) + О(KlogK)) => O(N + M) * O(M * K) => O(M * K)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Пространственная сложность О(N * M), где N - количество уникальных слов, M - количество документов

*/
//https://contest.yandex.ru/contest/24414/run-report/83667813/
public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            List<String[]> documents = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                documents.add(readList(reader));
            }

            int m = readInt(reader);
            List<String[]> requests = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                //Согласен, но считаю, что ввод-вывод данных и сам алгоритм лучше разделить, чтобы соблюдать SOLID
                requests.add(readList(reader));
            }

            List<List<Integer>> result = findRelevantRequests(documents, requests);
            for (List<Integer> ints : result) {
                writer.write(ints.stream().map(String::valueOf)
                        .collect(Collectors.joining(" ")));
                writer.newLine();
            }
        }
    }

    //O(N + M)
    private static List<List<Integer>> findRelevantRequests(List<String[]> documents, List<String[]> requests) {
        //индексация документов
        Index index = new Index();
        for (int i = 0; i < documents.size(); i++) {
            String[] document = documents.get(i);
            index.addDocument(document, i + 1);
        }

        List<List<Integer>> result = new ArrayList<>();
        for (String[] request : requests) {
            result.add(index.search(request));
        }

        return result;
    }

    private static String[] readList(BufferedReader reader) throws IOException {
        return reader.readLine().split(" ");
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    static class Index {
        private final HashMap<String, HashMap<Integer, Integer>> index;

        public Index() {
            this.index = new HashMap<>();
        }

        //O(N)
        public void addDocument(String[] document, int documentId) {
            for (String word : document) {
                if (index.containsKey(word)) {
                    HashMap<Integer, Integer> wordCounter = index.get(word);
                    if (wordCounter.containsKey(documentId)) {
                        wordCounter.put(documentId, wordCounter.get(documentId) + 1);
                    } else {
                        wordCounter.put(documentId, 1);
                    }
                } else {
                    HashMap<Integer, Integer> newWordCounter = new HashMap<>();
                    newWordCounter.put(documentId, 1);
                    index.put(word, newWordCounter);
                }
            }
        }

        //O(M * K) + O(KlogK)
        public List<Integer> search(String[] words) {
            Set<String> wordSet = new HashSet<>(Arrays.asList(words));

            //HashMap<Index, Word Sum>
            HashMap<Integer, Integer> map = new HashMap<>();

            for (String word : wordSet) {
                HashMap<Integer, Integer> documents = index.get(word);
                if (documents == null) {
                    continue;
                }
                for (Map.Entry<Integer, Integer> entry : documents.entrySet()) {
                    int key = entry.getKey();
                    int value = entry.getValue();

                    if (map.containsKey(key)) {
                        map.put(entry.getKey(), map.get(entry.getKey()) + value);
                    } else {
                        map.put(entry.getKey(), value);
                    }
                }
            }

            List<Integer> result = getBestFive(map);
            return result;
        }

        //O(KLogK)
        private List<Integer> getBestFive(HashMap<Integer, Integer> map) {
            Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
            List<Map.Entry<Integer, Integer>> entriesList = new ArrayList<>(entries);
            entriesList.sort((o1, o2) -> {
                int result = Integer.compare(o2.getValue(), o1.getValue());
                if (result != 0) {
                    return result;
                }
                return Integer.compare(o1.getKey(), o2.getKey());
            });
            return entriesList.stream()
                    .limit(5)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        }
    }
}
