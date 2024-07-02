import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        
        //N명의 참가자
        //인접한 번호끼리 대결
        //각 라운드의 참가자가 홀수일 경우 마지막(가장 큰) 번호를 부전승시켜 짝수로 만듦
        //다음 라운드로 넘어갈 때마다 참가자의 번호를 처음 번호의 순서를 유지하면서 다시 1번부터 부여

        //몇 라운드에서 lim과 대결하는지?

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int kimNum = Integer.parseInt(st.nextToken());
        int limNum = Integer.parseInt(st.nextToken());

        int round = 0;
        
        //lim과 kim 번호 중 홀수 번호를 기준으로 다른 한 명의 번호가 해당 번호에 +1을 한 번호이면 대결
        while(N > 1) {
            round++;
            if (isOdd(kimNum) && (limNum == (kimNum + 1))) {
                break;
            }

            if (isOdd(limNum) && (kimNum == (limNum + 1))) {
                break;
            }

            //홀수 번호인 상태로 다음 라운드에 가면 (현재 번호 + 1) / 2 번호가 됨
            //짝수 번호인 상태로 다음 라운드에 가면 현재 번호 / 2 번호가 됨
            kimNum = grantNextRoundNumber(kimNum);
            limNum = grantNextRoundNumber(limNum);

            N /= 2;
            if (isOdd(N)) {
                N++;
            }
        }

        bw.write(String.valueOf(round));
        bw.flush();
    }

    static int grantNextRoundNumber(int number) {
        if (isOdd(number))
            return (number + 1) / 2;

        return number / 2;
    }

    static boolean isOdd(int n) {
        return (n % 2) != 0;
    }
}
