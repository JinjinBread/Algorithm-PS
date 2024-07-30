import java.util.Arrays;

public class Solution {
    
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{1, 3, 5}, new int[]{2, 4, 6})));
    }

    // 이미 정렬이 완료되어 있는 두 배열 arr1, arr2를 병합 정렬하기
    // arr1과 arr2는 각각 오름차순으로 정렬되어 있다.
    private static int[] solution(int[] arr1, int[] arr2) {
        return mergeSort(arr1, arr2);
    }

    private static int[] mergeSort(int[] arr1, int[] arr2) {
        
        int resLen = arr1.length + arr2.length;
        int[] merge = new int[resLen];

        int resIdx = 0;
        int arr1Idx = 0;
        int arr2Idx = 0;

        // 방법 1
        // while (arr1Idx < arr1.length && arr2Idx < arr2.length) {
        //     merge[resIdx++] = arr1[arr1Idx] <= arr2[arr2Idx] ? arr1[arr1Idx++] : arr2[arr2Idx++];
        // }


        // while (arr1Idx < arr1.length) {
        //     merge[resIdx++] = arr1[arr1Idx++];
        // }

        // while (arr2Idx < arr2.length) {
        //     merge[resIdx++] = arr2[arr2Idx++];
        // }

        // 방법 2
        for (int i = 0; i < resLen; i++) {
            if (arr1Idx >= arr1.length) {
                merge[i] = arr2[arr2Idx++];
            }
            else if (arr2Idx >= arr2.length) {
                merge[i] = arr1[arr1Idx++];
            }
            else {
                merge[i] = arr1[arr1Idx] < arr2[arr2Idx] ? arr1[arr1Idx++] : arr2[arr2Idx++];
            }
        }

        return merge;
    }

}
