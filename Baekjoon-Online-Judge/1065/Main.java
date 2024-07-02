import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    //한수 개수 찾기
    //한수란? 각 자리가 등차수열을 이루는 수
    //예를 들어, 111, 123, 135 등을 말한다.
    //따라서 두 자릿 수까지는 모두 한수임
    //입력값은 1,000보다 작거나 같은 수.
    //1. 백의 자리 수 - 십의 자리 수 값과 십의 자리 수 - 일의 자리 수 값이 같으면
    //2. 한수 개수 ++

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());
        int sum = num;
        if (num >= 100)
            sum = 99;

        for (int i = 100; i <= num; i++) {
            int hundreds = i / 100;
            int tens = (i / 10) % 10;
            int ones = i % 10;

            if ((hundreds - tens) == (tens - ones))
                sum++;
        }

        bw.write(String.valueOf(sum));
        bw.flush();
    }
}
