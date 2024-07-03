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

        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb1 = new StringBuilder(st.nextToken());
        StringBuilder sb2 = new StringBuilder(st.nextToken());

        int lLength = sb1.length();
        int rLength = sb2.length();


        if (rLength != lLength) {
            bw.write("0");
            bw.flush();
            return;
        }

        // 반례가 되는 테스트 케이스 찾기
        // 1. 8081 8085
        // 2. 1580 1583

        //L과 R에 8이 있는지 확인
        //L과 R의 맨 첫 자릿 수부터 비교하여
        //같은 자릿수, 같은 값이면 continue.
        //다르게 나오면 break.
        //8이 같은 자릿수에 있으면 count 1증가

        int count = 0;

        if (sb1.indexOf("8") == -1) { //L과 R에 8이 없는 경우
            bw.write("0");
            bw.flush();
            return;
        }

        for (int i = 0; i < rLength; i++) {
            char l = sb1.charAt(i);
            char r = sb2.charAt(i);

            if (l == r) {
                if (l == '8') //OR r == '8' is OK
                    count++;
                continue;
            }

            break;
        }

        bw.write(String.valueOf(count));
        bw.flush();
    }
}