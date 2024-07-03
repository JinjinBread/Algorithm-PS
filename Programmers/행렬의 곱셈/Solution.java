class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        //행렬의 결과는 앞 행렬의 행의 수 * 뒤 행렬의 열의 수
        int[][] answer = new int[arr1.length][arr2[0].length];
        
        //행렬곱은 앞 행렬의 열 수와 뒷 행렬의 행 수가 같아야 함
        
        for(int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2[0].length; j++) { //arr2의 열 수
                for (int k = 0; k < arr1[0].length; k++) { //arr1의 열 수
                    answer[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }
        
        
        return answer;
    }
}