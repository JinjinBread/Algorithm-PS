import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        
        //길이가 N인 문자열 X, Y
        //X와 Y의 차이는 같은 위치에 일치하지 않은 문자 수

        //입력 -> 문자열 A, B (이때, A.len <= B.len)
        //A의 길이가 B의 길이와 같아질 때 까지
        //A의 앞 or 뒤에 아무 알파벳이나 추가한다.

        //차이를 최소로 하도록 만들어라.

        //차이나는 문자의 개수를 n이라 하면
        //B의 앞에서 n만큼, B의 뒤에서 n만큼 떼어와서 A에 앞, 뒤에 붙인 후 차이 비교

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        String A = st.nextToken();
        String B = st.nextToken();

        int lenDiff = B.length() - A.length();
        int minDiff = 50; //A와 B의 최대 길이는 50이므로 최대 차이도 50

        for (int i = 0; i <= lenDiff; i++) {
            int j = lenDiff - i;
            
            String parts1 = B.substring(0, i);
            String parts2 = B.substring(B.length() - j); // endIndex 생략 시, 문자열 끝까지

            String temp = parts1 + A + parts2;
            minDiff = Math.min(minDiff, getDiffCount(temp, B));
        }

        bw.write(String.valueOf(minDiff));
        bw.flush();
    }

    static int getDiffCount(String a, String b) {
        int count = 0;

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i))
                count++;
        }

        return count;
    }
}
