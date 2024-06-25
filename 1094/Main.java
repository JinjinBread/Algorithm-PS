import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int input = Integer.parseInt(br.readLine());

        bw.write(String.valueOf(getStickNum(input)));
        bw.flush();
    }
    
    static int getStickNum(int input) {
        // input보단 작지만 가장 차이가 안 나는 수 부터
        int num = getNearestNum(input);
        int sum = 0;
        int n = 0;

        while (sum != input) {
            sum += num;
            n++;

            //undo
            if (sum > input) {
                sum -= num;
                n--;
            }
            num /= 2;
        }

        return n;
    }

    static int getNearestNum(int input) {

        int num = 64;

        while(input < num) {
            num /= 2;
        }

        return num;
    }
}
