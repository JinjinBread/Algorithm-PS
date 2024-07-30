import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {

    private static int[] ch;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        bw.write(solution(s));
        bw.flush();
    }

    // 계수 정렬
    private static String solution(String s) {
        
        ch = new int[26]; // idx 0은 'a'(97)를 의미
        return countingSort(s);
    }

    private static String countingSort(String s) {

        for (int asciiCh : s.toCharArray()) {
            ch[asciiCh - 97]++; // - 97 대신 - 'a' 해도 됨
        }

        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < ch.length; i++) {
            for (int j = 0; j < ch[i]; j++) {
                char c = (char) (i + 97);
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
