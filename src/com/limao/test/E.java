package com.limao.test;
@Test
public void SelectionSort() {
	int[] arr = { 3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48 };
	for (int i = 0; i < arr.length - 1; i++) {
		int index = i;
		for (int j = 1 + i; j < arr.length; j++) {
			if (arr[j] < arr[index]) {
				index = j;// 保存最小元素的下标
			}
		}
		// 此时已经找到最小元素的下标
		// 将最小元素与前面的元素交换
		int temp = arr[index];
		arr[index] = arr[i];
		arr[i] = temp;
	}
	System.out.println(Arrays.toString(arr));
}
