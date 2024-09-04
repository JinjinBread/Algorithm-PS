import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();
        String bombStr = br.readLine();

        int inputLength = input.length();
        int bombStrLength = bombStr.length();
        char bombLastCh = bombStr.charAt(bombStrLength - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < inputLength; i++) {
            char ch = input.charAt(i);
            sb.append(ch);

            if ((sb.length() >= bombStrLength) && (ch == bombLastCh)) {
                int start = sb.length() - bombStrLength;
                if (sb.substring(start, start + bombStrLength).equals(bombStr)) {
                    sb.delete(start, start + bombStrLength);
                }
            }
        }

        // int start = 0;
        // while (start <= (inputLength - bombStrLength)) {

        //     if (sb.substring(start, start + bombStrLength).equals(bombStr)) {
        //         sb = sb.delete(start, start + bombStrLength);
        //         inputLength = sb.length();
        //         start -= (bombStrLength - 1);
        //         if (start < 0) {
        //             start = 0;
        //         }
        //         continue;
        //     }
        //     start += 1;
        // }

        if (sb.length() == 0) {
            bw.write("FRULA");
        }
        else {
            bw.write(sb.toString());
        }
        bw.flush();
    }
}