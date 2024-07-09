import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        //키가 1인 사람부터 위치를 지정
        //tallerThan[i]
        //tallerThan[i] 번째에 이미 있으면 그 뒤에 자리에 서게 함
        //

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] tallerThan = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tallerThan[i] = Integer.parseInt(st.nextToken());
        }

        int[] order = new int[N];
        for (int i = 0; i < N; i++) {
            
            int tallerNum = tallerThan[i];
            int count = -1;

            for (int j = 0; j < N; j++) {
                if (order[j] == 0)
                    count++;

                if (count == tallerNum) {
                    order[j] = i + 1;
                    break;
                }
            }
        }

        for (int i = 0; i < order.length - 1; i++) {
            bw.write(order[i] + " ");
        }
        bw.write(String.valueOf(order[order.length - 1]));
        bw.flush();
    }
}
