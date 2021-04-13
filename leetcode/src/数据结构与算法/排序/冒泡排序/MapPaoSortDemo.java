package 数据结构与算法.排序.冒泡排序;

import java.util.Arrays;

/**
 * @author lx
 * @date 2021/2/3 17:45
 */
public class MapPaoSortDemo {

    public static void main(String[] args) {
        int[] arr = new int[]{3,4,2,5,8,7};

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0;j< arr.length-i-1;j++){
                if (arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(arr));
    }

}
