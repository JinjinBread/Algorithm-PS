package codingtest_java.decimalToBinary;

import java.util.ArrayDeque;

class Solution {
    
    public static String solution(int decimal) {
        //1. decimal을 2로 나눈 나머지를 스택에 넣는다.
        //2. decimal을 2로 나눈 몫으로 1번부터 다시 진행한다.
        // 위 단계를 decimal이 0보다 작거나 같아질 때까지 진행한다.

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        while(decimal > 0) {

            int remainder = decimal % 2; // 나머지

            stack.push(remainder);
            decimal /= 2;
        }

        StringBuilder sb = new StringBuilder();
        
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }

}
