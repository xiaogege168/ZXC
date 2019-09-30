package com.limao.test;

@Test
public void bubbleSort() {
	int[] arr = { 3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48 };
	// 统计比较次数
	int count = 0;
	for (int i = 0; i < arr.length - 1; i++) {
		boolean flag = true;
		for (int j = 0; j < arr.length - 1 - i; j++) {
			if (arr[j] > arr[j + 1]) {
				// 交换位置
				int temp = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = temp;
				flag = false;
			}
			count++;
		}
		if(flag) {
			break;
		}
	}
	System.out.println(Arrays.toString(arr));
	System.out.println("一共比较了:" + count + "次");
}
