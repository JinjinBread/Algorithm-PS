import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    /*
     * N개의 통나무를
     * 난이도가 가장 낮게 세우기
     * 
     * 난이도 = 인접한 나무와의 차이들 중 가장 큰 값
     * 
     * { 2, 4, 5, 7, 9 }
     * 
     * { 5, 2, 4, 7, 9 }
     * -> 5와 9의 차이 4. 난이도: 4
     * 
     * 5 9 7 4 2
     * 
     * 
     * { 10 11 12 13 12 11 10 }
     * 
     * 덱 사용?
     * 
     */
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= tc; test_case++) {
            
            int n = Integer.parseInt(br.readLine()); // 통나무 개수
            
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] wood = new int[n];

            for (int i = 0; i < n; i++) {
                wood[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(wood);

            Deque<Integer> dq = new ArrayDeque<>();
            dq.add(wood[0]);

            int lv = Integer.MIN_VALUE;

            for (int i = 1; i < n; i++) {
                if ((i % 2) == 0) {

                    int curLast = dq.getLast();
                    lv = Math.max(lv, Math.abs(curLast - wood[i]));
                    dq.addLast(wood[i]);

                    // 마지막인 경우 가장 첫 통나무와의 차이도 계산
                    if (i == (n-1)) {
                        int curFirst = dq.getFirst();
                        lv = Math.max(lv, Math.abs(curFirst - wood[i]));
                    }
                }
                else {

                    int curFirst = dq.getFirst();
                    lv = Math.max(lv, Math.abs(curFirst - wood[i]));
                    dq.addFirst(wood[i]);

                    // 마지막인 경우 가장 마지막 통나무와의 차이도 계산
                    if (i == (n-1)) {
                        int curLast = dq.getLast();
                        lv = Math.max(lv, Math.abs(curLast - wood[i]));
                    }
                }
            }

            bw.write(String.valueOf(lv));
            bw.newLine();
        }
        bw.flush();
    }
}
