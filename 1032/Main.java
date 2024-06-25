import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        List<String> input = new ArrayList<>();
        
        for (int i = 0; i < tc; i++) {
            input.add(br.readLine());
        }

        bw.write(getPattern(input));
        bw.flush();
    }

    static String getPattern(List<String> input) {

        String pattern = input.get(0);
        StringBuilder sb = new StringBuilder(pattern);


        if (input.size() == 1)
            return pattern;

        if (isAllEqual(pattern, input))
            return pattern;

        for (int i = 0; i < pattern.length(); i++) {

            if (isEqualAllInputChar(pattern.charAt(i), i, input)) {
                continue;
            }

            sb.setCharAt(i, '?');
        }
            //1. 문자 하나씩 비교
            //2. 같은 자리에 
        
        return sb.toString();
    }

    static boolean isEqualAllInputChar(char ch, int idx, List<String> input) {

        for (int i = 1; i < input.size(); i++) {
            if (ch == input.get(i).charAt(idx))
                continue;
            return false;
        }
        return true;
    }

    static boolean isAllEqual(String criterion, List<String> input) {

        for (int i = 1; i < input.size(); i++) {
            if (criterion.equals(input.get(i))) {
                continue;
            }
            return false;
        }
        return true;
    }

}