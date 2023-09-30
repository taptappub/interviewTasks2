package sprint5;

public class M {
// Просеивание кучи вверх

//    функция sift_up(heap, index):
//    если index == 1, то
//    завершить работу
//    parent_index = index / 2  (целочисленное деление)
//    если heap[parent_index] < heap[index], то
//    обменять местами heap[parent_index] и heap[index]
//    sift_up(heap, parent_index)
    public static int siftUp(int[] heap, int idx) {
        if (idx == 1) {
            return 1;
        }

        int parentIndex = idx / 2;
        if (heap[parentIndex] < heap[idx]) {
            int temp = heap[parentIndex];
            heap[parentIndex] = heap[idx];
            heap[idx] = temp;

            return siftUp(heap, parentIndex);
        }
        return idx;
    }

    private static void test() {
        int[] sample = {-1, 12, 6, 8, 3, 15, 7};
        boolean dsf = siftUp(sample, 5) == 1;
    }
}