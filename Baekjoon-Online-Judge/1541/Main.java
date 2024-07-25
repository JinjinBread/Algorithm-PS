import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String exp = br.readLine();

        // - 사이에 있는 수들을 괄호치면 됨
        // ex) 20 - 10 + 5 - 7 + 19 --> 20 - (10 + 5) - (7 + 19) = -21
        String[] sp1 = exp.split("-");
        List<Integer> list = new ArrayList<>();
        int sum = 0;

        // +로만 이루어져 있음
        if (sp1.length == 1) {
            for (String s : sp1[0].split("\\+")) {
                sum += Integer.parseInt(s);
            }
        }
        else {
            for (String s : sp1) {
                if (s.contains("+")) {
                    for (String s1: s.split("\\+")) {
                        sum += Integer.parseInt(s1);
                    }
                    list.add(sum);
                    sum = 0;
                }
                else {
                    list.add(Integer.parseInt(s));
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                sum = list.get(0);
                continue;
            }
            sum -= list.get(i);
        }

        bw.write(String.valueOf(sum));
        bw.flush();
    }
}
