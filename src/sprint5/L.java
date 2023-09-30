package sprint5;

//Просеивание вниз
public class L {

    public static int siftDown(int[] heap, int idx) {
        int left = 2 * idx;
        int right = 2 * idx + 1;

        if (heap.length <= left) {
            return idx;
        }

        int indexLargest;
        if (right < heap.length && heap[left] < heap[right]) {
            indexLargest = right;
        } else {
            indexLargest = left;
        }

        //if (heap[idx] < heap[indexLargest]) {
        if (heap[indexLargest] > heap[idx]) {
            int temp = heap[indexLargest];
            heap[indexLargest] = heap[idx];
            heap[idx] = temp;

            return siftDown(heap, indexLargest);
        }
        return idx;
    }

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        int[] sample = {-1, 12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        int res = siftDown(sample, 2);
        boolean sdfs = res == 5;
    }
}
