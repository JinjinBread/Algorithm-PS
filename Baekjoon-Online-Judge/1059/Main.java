import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException {
        
        //정수 집합 S, 다음 조건을 만족할 경우 [A, B]를 좋은 구간이라고 함.
        //만족해야 할 조건
        //1. A와 B는 양수이고, B는 A보다 크다 (A < B)
        //2. S에 A 보다 크거나 같고 B 보다 작거나 같은 수가 없어야 한다.

        //집합 S와 특정 수 n이 주어졌을 때, n을 포함하는 좋은 구간의 개수 구하기
        // 1. 집합 S의 원소 중 n보다 작은 값 중 가장 큰 값 구하기 (해당 값 + 1 부터)
        //  1-1. 만약 n보다 작은 값이 없으면 lowerBoundary는 n
        // 2. 집합 S의 원소 중 n보다 큰 값 중 가장 작은 값 구하기 (해당 값 - 1 까지)
        //  2-1. 만약 n보다 큰 값이 없으면 upperBoundary는 n

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //집합 크기
        int num = Integer.parseInt(br.readLine());
        
        //집합 원소
        int[] arr = new int[num];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < num; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //특정 수 n (1 ≤ n ≤ (집합 S에서 가장 큰 정수))
        int n = Integer.parseInt(br.readLine());

        int upperBoundary = 1000;
        int lowerBoundary = 0;

        for (int i : arr) {
            
            if (i < n) {
                if (lowerBoundary < i) {
                    lowerBoundary = i;
                }
            }
            
            else if (i > n) {
                if (upperBoundary > i) {
                    upperBoundary = i;
                }
            }

            else {
                bw.write(String.valueOf(0));
                bw.flush();
                return;
            }
        }


        int goodIntvlNumber = 0;
        for (int i = lowerBoundary + 1; i <= n; i++) {
            for (int j = n; j < upperBoundary; j++) {
                if (i == j)
                    continue;
                goodIntvlNumber++;
            }
        }

        bw.write(String.valueOf(goodIntvlNumber));
        bw.flush();
    }
}