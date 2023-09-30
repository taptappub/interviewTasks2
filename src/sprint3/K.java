package sprint3;

/*
Гоше дали задание написать красивую сортировку слиянием. Поэтому Гоше обязательно надо реализовать отдельно
функцию merge и функцию merge_sort.

Функция merge принимает два отсортированных массива, сливает их в один отсортированный массив и возвращает его.
Если требуемая сигнатура имеет вид merge(array, left, mid, right), то первый массив задаётся полуинтервалом
[left,mid) массива array, а второй – полуинтервалом [mid,right) массива array.

Функция merge_sort принимает некоторый подмассив, который нужно отсортировать. Подмассив задаётся
полуинтервалом — его началом и концом. Функция должна отсортировать передаваемый в неё подмассив, она ничего не возвращает.

Функция merge_sort разбивает полуинтервал на две половинки и рекурсивно вызывает сортировку отдельно для каждой.
Затем два отсортированных массива сливаются в один с помощью merge.

Заметьте, что в функции передаются именно полуинтервалы [begin,end), то есть правый конец не включается.
Например, если вызвать merge_sort(arr, 0, 4), где arr=[4,5,3,0,1,2], то будут отсортированы только первые
четыре элемента, изменённый массив будет выглядеть как arr=[0,3,4,5,1,2].
Реализуйте эти две функции.
 */

import java.util.Arrays;

public class K {
	public static int[] merge(int[] arr, int left, int mid, int right) {
		//[left, mid), [mid, right)
		int i = left;
		int j = mid;

		int[] result = new int[right - left];
		int resCounter = 0;
		while (i < mid && j < right) {
			int valLeft = arr[i];
			int valRight = arr[j];
			if (valLeft < valRight) {
				result[resCounter] = valLeft;
				i++;
			} else {
				result[resCounter] = valRight;
				j++;
			}
			resCounter++;
		}

		while (i < mid) {
			int valLeft = arr[i];
			result[resCounter] = valLeft;
			resCounter++;
			i++;
		}

		while (j < right) {
			int valRight = arr[j];
			result[resCounter] = valRight;
			resCounter++;
			j++;
		}
		return result;
	}

	public static void merge_sort(int[] arr, int left, int right) {
		if (right - left < 2) {
			return;
		}

		int middle = (right + left) / 2;
		merge_sort(arr, left, middle);
		merge_sort(arr, middle, right);

		int[] res = merge(arr, left, middle, right);
		for (int i = left; i < right; i++) {
			arr[i] = res[i - left];
		}
	}

	public static void main(String[] args) {
		int[] a = {1, 4, 9, 2, 10, 11};
		int[] b = merge(a, 0, 3, 6);
		int[] expected = {1, 2, 4, 9, 10, 11};
		assert Arrays.equals(b, expected);
		int[] c = {1, 4, 2, 10, 1, 2};
		merge_sort(c, 0, 6);
		int[] expected2 = {1, 1, 2, 2, 4, 10};
		assert Arrays.equals(c, expected2);
	}
}