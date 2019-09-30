package com.limao.test;

@Test
public void InsertionSort() {
	int[] arr = { 3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48 };
	for (int i = 1; i < arr.length; i++) {
		// 定义待插入的数
		int insertValue = arr[i];
		// 找到待插入数的前一个数的下标
		int insertIndex = i - 1;
		while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
			arr[insertIndex + 1] = arr[insertIndex];
			insertIndex--;
		}
		arr[insertIndex + 1] = insertValue;
	}
	System.out.println(Arrays.toString(arr));
}

