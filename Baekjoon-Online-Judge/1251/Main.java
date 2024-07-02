import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String word = br.readLine();

        List<String> words = new ArrayList<>();

        //substring의 endIndex는 - 1 까지 이므로 word.length() - 2가 아닌 - 1을 해준다.
        for (int i = 1; i < word.length() - 1; i++) {
            for (int j = i + 1; j < word.length(); j++) {
                StringBuilder sb1 = new StringBuilder(word.substring(0, i)).reverse();
                StringBuilder sb2 = new StringBuilder(word.substring(i, j)).reverse();
                StringBuilder sb3 = new StringBuilder(word.substring(j, word.length())).reverse();

                StringBuilder sb = new StringBuilder()
                                    .append(sb1)
                                    .append(sb2)
                                    .append(sb3);

                words.add(sb.toString());
            }
        }
        
        Collections.sort(words);

        bw.write(words.get(0));
        bw.flush();
    }

}