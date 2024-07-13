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
        int[] switchStatus = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            switchStatus[i] = Integer.parseInt(st.nextToken());
        }

        int studentNum = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < studentNum; i++) {
            
            st = new StringTokenizer(br.readLine());
            int sex = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            if (sex == 1) {
                changeManSwitch(switchStatus, num);
                continue;
            }
            changeWomanSwitch(switchStatus, num);
        }

        for (int i = 0; i < switchStatus.length; i++) {

            if (i > 0 && i % 20 == 0) {
                bw.newLine();  // 20번째마다 개행
            } else if (i > 0) {
                bw.write(" ");
            }

            bw.write(String.valueOf(switchStatus[i]));
            
        }
        bw.flush();
    }

    private static void changeManSwitch(int[] switchStatus, int num) {

        for (int i = 1; i <= switchStatus.length; i++) {
            // 스위치 번호가 받은 수의 배수라면
            if (i % num == 0) {
                switchStatus[i - 1] = switchStatus[i - 1] == 1 ? 0 : 1;
            }
        }

    }

    private static void changeWomanSwitch(int[] switchStatus, int num) {

        int idx = num - 1;
        int gap = getSectionGap(switchStatus, idx, 1);

        for (int i = idx - gap; i <= idx + gap; i++) {
            switchStatus[i] = switchStatus[i] == 1 ? 0 : 1;
        }

    }

    private static int getSectionGap(int[] switchStatus, int idx, int gap) {

        // 맨 처음부터 구간이 시작, 맨 끝이 구간의 마지막
        if (idx - gap < 0 || idx + gap >= switchStatus.length) {
            return gap - 1;
        }

        // 대칭이면
        if (switchStatus[idx - gap] == switchStatus[idx + gap]) {
            return getSectionGap(switchStatus, idx, gap + 1);
        }

        return gap - 1;        
    }
}