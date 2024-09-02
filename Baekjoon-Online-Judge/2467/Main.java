import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] solution = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solution[i] = Integer.parseInt(st.nextToken());
        }

        int head = 0;
        int tail = N - 1;
        int min = Integer.MAX_VALUE;

        int solutionOne = head;
        int solutionTwo = tail;

        while (head < tail) {

            int mix = solution[head] + solution[tail];
            int gapToZero = Math.abs(mix);
            
            if (min >= gapToZero) {
                solutionOne = head;
                solutionTwo = tail;
                min = gapToZero;
            }

            if (mix == 0) {
                solutionOne = head;
                solutionTwo = tail;
                break;
            }

            // 양수이면 tail 값을 줄여서 두 합을 줄임
            if (mix > 0) {
                tail--;
            }
            // 음수면 head 값을 늘려서 두 합을 늘림
            else {
                head++;
            }
        }

        
        bw.write(solution[solutionOne] + " " + solution[solutionTwo]);
        bw.flush();
    }

}
