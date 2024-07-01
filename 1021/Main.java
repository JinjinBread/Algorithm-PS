import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    //양방향 순환 큐 == 덱 이용
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] num = new int[M];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        LinkedList<Integer> dq = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            dq.add(i); // addFirst는 ... 3 2 1 이런 식으로 저장됨
        }

        int count = 0;

        for (int i = 0; i < num.length; i++) {
            int find = num[i];
            count += countMinMove(dq, find);
        }

        bw.write(String.valueOf(count));
        bw.flush();
    }

    static int countMinMove(LinkedList<Integer> dq, int find) {

        int leftMoveCnt = dq.indexOf(find);
        int rightMoveCnt = dq.size() - dq.indexOf(find);

        int found = dq.pollFirst();

        if (found == find) {
            return 0;
        }

        if (leftMoveCnt > rightMoveCnt) {
            while (found != find) {
                dq.addFirst(found);
                found = dq.pollLast();
            }
            return rightMoveCnt;
        }

        while (found != find) {
            dq.addLast(found);
            found = dq.pollFirst();
        }
        return leftMoveCnt;
    }
}