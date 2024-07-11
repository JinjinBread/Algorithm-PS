
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class Solution {
    
    //1~N번 사람이 원을 둘러 앉아있다.
    //임의의 숫자 K가 주어졌을 때,
    //처음엔, 1번 사람을 기준으로 K번째 사람을 없앤다.
    //그 이후론, 없앤 사람 다음 사람을 기준으로 K번째 사람을 없앤다.
    //마지막에 살아있는 사람의 번호는?

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        bw.write(String.valueOf(solution(N, K)));
        bw.flush();
    }

    public static int solution(int N, int K) {

        ArrayDeque<Integer> q = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            q.addLast(i);
        }

        while (q.size() != 1) {
            for (int j = 0; j < K - 1; j++) {
                int first = q.pollFirst();
                q.addLast(first);
            }
            q.pollFirst();
        }

        return q.pollFirst();
    }

}
