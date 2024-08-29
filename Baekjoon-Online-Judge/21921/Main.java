import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] visitedNum = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            visitedNum[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        for (int i = 0; i < X; i++) {
            sum += visitedNum[i];
        }

        int max = sum;
        int n = 1;
        for (int i = X; i < N; i++) {

            sum = sum - visitedNum[i - X] + visitedNum[i];

            if (max == sum) {
                n++;
            }
            else if (max < sum) {
                max = sum;
                n = 1;
            }
        }

        if (max == 0) {
            bw.write("SAD");
        }
        else {
            bw.write(max  + "\n" + n);
        }

        bw.flush();
    }
}
