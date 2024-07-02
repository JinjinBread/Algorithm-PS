import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        LinkedList<Integer> link = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            link.add(i);
        }

        while(link.size() > 1) {
            // 맨 위의 카드를 버리고
            // 그 다음 제일 위에 있는 카드를 맨 뒤로 보낸다.
            bw.write(String.valueOf(link.poll()) + " ");
            link.add(link.poll());
            bw.flush();
        }

        bw.write(String.valueOf(link.poll()) + " ");
        bw.flush();
    }
}
