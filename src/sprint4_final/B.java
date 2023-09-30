package sprint4_final;

/*
-- ПРИНЦИП РАБОТЫ --
HashTable внутри себя имеет массив с заданной capacity, т.к. по условию задачи, не надо думать о масштабировании.
В каждой ячейке находится LinkedList, который содержит пару Ключ-Значение.
При обращении к элементу, значала на основе key высчитывается его индекс в массиве, а потом сравнивая key, ищется необходимый элемент.

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
Из-за того, что внутри имеется достаточно большой массив с количеством элементов равному простому числу, то количество
созданных бакетов должно быть максимальным, а количество элементов в бакете будет стремиться к 1.
Следовательно доступ к элементу в лучшем случае будет О(1), но при условии коллизий, т.е. в худшем случае будет О(N)

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Операция put при добавлении элемента выполняется за О(1), при обновлении - за О(N), где N - количество элементов в бакете.
Операции get и delete в лучшем случае выполняются за О(1), в худшем за О(N), где N - количество элментов в бакете.
Т.к. на входе мы имеем список операций, то итоговая временная сложность в лучшем случае О(N), в худшем О(N^2).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Capacity HashTable в 10 раз меньше чем N. В лучшем случае пространственная сложность О(Capacity),
в худшем О(Capacity + B * N), где B - количество бакетов, а N - количество элементов в бакете
*/
//https://contest.yandex.ru/contest/24414/run-report/83667813/
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = readInt(reader);
            int capacity = n / 10 + 10;
            HashTable myHashTable = new HashTable(capacity);

            for (int i = 0; i < n; i++) {
                List<String> rawAction = readList(reader);

                switch (rawAction.get(0)) {
                    case "put": {
                        int key = Integer.parseInt(rawAction.get(1));
                        int value = Integer.parseInt(rawAction.get(2));
                        myHashTable.put(key, value);
                        break;
                    }
                    case "delete": {
                        int key = Integer.parseInt(rawAction.get(1));
                        int value = myHashTable.delete(key);
                        if (value != -1) {
                            writer.write(value + "");
                        } else {
                            writer.write("None");
                        }
                        writer.newLine();
                        break;
                    }
                    case "get": {
                        int key = Integer.parseInt(rawAction.get(1));
                        int value = myHashTable.get(key);
                        if (value != -1) {
                            writer.write(value + "");
                        } else {
                            writer.write("None");
                        }
                        writer.newLine();
                        break;
                    }
                }
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<String> readList(BufferedReader reader) throws IOException {
        return new ArrayList<>(Arrays.asList(reader.readLine().split(" ")));
    }

    /*
    get 1
    put 1 10
    put 2 4
    get 1
    get 2
    delete 2
    get 2
    put 1 5
    get 1
    delete 2
     */
    static class HashTable {
        private final LinkedList<HashTableItem>[] buckets;
        private final int capacity;
        private int size;

        public HashTable(int capacity) {
            this.capacity = capacity;
            buckets = new LinkedList[capacity];
            size = 0;
        }

        public void put(int key, int value) {
            int bucketIndex = getBucketIndex(key);
            LinkedList<HashTableItem> bucket = buckets[bucketIndex];
            if (bucket == null) {
                LinkedList<HashTableItem> newBucket = new LinkedList<>();
                newBucket.add(new HashTableItem(key, value));
                buckets[bucketIndex] = newBucket;
                size++;
            } else {
                int keyIndex = getKeyIndex(bucket, key);
                if (keyIndex != -1) {
                    removeValue(bucket, keyIndex);
                } else {
                    size++;
                }
                bucket.add(new HashTableItem(key, value));
            }
        }

        public int get(int key) { //сделать из него лямбду
            int bucketIndex = getBucketIndex(key);
            LinkedList<HashTableItem> bucket = buckets[bucketIndex];
            if (bucket == null) {
                return -1;
            }

            for (HashTableItem hashTableItem : bucket) {//сделать из него лямбду
                if (hashTableItem.getKey() == key) {
                    return hashTableItem.value;
                }
            }
            return -1;
        }

        public int delete(int key) {
            if (size == 0) {
                return -1;
            }

            int bucketIndex = getBucketIndex(key);
            LinkedList<HashTableItem> bucket = buckets[bucketIndex];
            if (bucket == null) {
                return -1;
            }

            for (HashTableItem hashTableItem : bucket) {//сделать из него лямбду
                if (hashTableItem.getKey() == key) {
                    bucket.remove(hashTableItem);
                    return hashTableItem.value;
                }
            }
            return -1;
        }

        //Можно объединить Поиск и Удаление в одном методе, и тогда они оба будут работать за О(N), но
        //ради читаемости, я решил их разделить
        private void removeValue(LinkedList<HashTableItem> bucket, int index) {
            bucket.remove(index);
        }

        //Можно объединить Поиск и Удаление в одном методе, и тогда они оба будут работать за О(N), но
        //ради читаемости, я решил их разделить
        private int getKeyIndex(LinkedList<HashTableItem> bucket, int key) {
            for (int i = 0; i < bucket.size(); i++) {
                HashTableItem hashTableItem = bucket.get(i);
                if (hashTableItem.getKey() == key) {
                    return i;
                }
            }
            return -1;
        }

        private int getBucketIndex(int key) {
            return Math.abs(key) % capacity;
        }

        static class HashTableItem {
            private final int key;
            private final int value;

            public HashTableItem(int key, int value) {
                this.key = key;
                this.value = value;
            }

            public int getKey() {
                return key;
            }

            public int getValue() {
                return value;
            }
        }
    }
}
