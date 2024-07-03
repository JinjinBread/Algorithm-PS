import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        
        int[] oneStudent = {1, 2, 3, 4, 5};
        int[] twoStudent = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] threeStudent = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        //이차원 배열로도 만들 수 있음
        // int[][] pattern = {
        //     {1, 2, 3, 4, 5},
        //     {2, 1, 2, 3, 2, 4, 2, 5},
        //     {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        // };

        int[] correctArr = new int[3];

        //이차원 배열로도 만들 수 있음
        // for (int i = 0; i < answers.length; i++) {
        //     for (int j = 0; j < pattern.length; j++) {
        //         if (answers[i] == pattern[j][i % pattern[j].length])
        //             correctArr[j]++;
        //     }
        // }

        for(int i = 0; i < answers.length; i++) {
            
            int one = oneStudent[i % oneStudent.length];
            int two = twoStudent[i % twoStudent.length];
            int three = threeStudent[i % threeStudent.length];
            
            if (answers[i] == one) {
                correctArr[0]++;
            }
            
            if (answers[i] == two) {
                correctArr[1]++;
            }
            
            if (answers[i] == three) {
                correctArr[2]++;
            }
        }
        
        //최대 점수를 찾아서
        int max = Arrays.stream(correctArr).max().getAsInt();
        
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < correctArr.length; i++) {
            //그 점수와 같은 사람 찾기
            if (correctArr[i] == max)
                res.add(i + 1);
        }
        
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
    
}