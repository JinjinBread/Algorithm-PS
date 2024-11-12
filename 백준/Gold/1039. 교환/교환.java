import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static class Money {
        int money;
        int changeNumber;
        public Money(int money, int changeNumber) {
            this.money = money;
            this.changeNumber = changeNumber;
        }
    }

    static int N;
    static int K;
    static int digit;
    static int[] arrN;
    static int max;
    static boolean[][] visited = new boolean[11][1_000_001]; // 10보다 작거나 같은 연산 수, 1,000,000보다 작거나 같은 수

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        String strN = st.nextToken();

        N = Integer.parseInt(strN);
        K = Integer.parseInt(st.nextToken());
        
        digit = strN.length();

        arrN = convertIntToArray(N);

        // 1. 자기 자신과는 바꿀 수 없으므로, 한 자릿수는 연산할 수 없음
        // 2. 두 자릿수이면서, 0으로 끝나는 수는 swap할 수 없음 ex) 90
        if (digit == 1 || (digit == 2 && arrN[1] == 0)) {
            System.out.println(-1);
            return;
        }

        getMaxNum();

        System.out.println(max);
    }

    static void getMaxNum() {

        Queue<Money> q = new ArrayDeque<>();
        visited[0][N] = true;
        q.add(new Money(N, 0));

        while (!q.isEmpty()) {

            Money now = q.poll();

            arrN = convertIntToArray(now.money);

            if (now.changeNumber == K) {
                max = Math.max(now.money, max);
                continue;
            }

            for (int i = 0; i < arrN.length; i++) {
                for (int j = i + 1; j < arrN.length; j++) {
                    swap(i, j);
                    int newMoney = convertArrayToInt(arrN);
                    swap(i, j); // 원복
                    // 방문했거나 바뀐 수(newMoney)가 0으로 시작하는 경우
                    if (visited[now.changeNumber + 1][newMoney] || getDigit(newMoney) != digit) continue;
                    visited[now.changeNumber + 1][newMoney] = true;
                    q.add(new Money(newMoney, now.changeNumber + 1));
                }
            }
            
        }

    }

    static int[] convertIntToArray(int number) {
        char[] chNum = String.valueOf(number).toCharArray();
        int[] arr = new int[chNum.length];
        int idx = 0;
        
        for (char ch : chNum) {
            arr[idx++] = ch - '0';
        }
        return arr;
    }

    static int convertArrayToInt(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num);
        }
        return Integer.parseInt(sb.toString());
    }

    static void swap(int x, int y) {
        int temp = arrN[x];
        arrN[x] = arrN[y];
        arrN[y] = temp;
    }

    static int getDigit(int number) {
        String strNumber = String.valueOf(number);
        return strNumber.length();
    }

}