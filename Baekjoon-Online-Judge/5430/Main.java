import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    private static Deque<Integer> dq;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {
            char[] cmds = br.readLine().toCharArray(); // 수행할 함수 p
            int arrLen = Integer.parseInt(br.readLine());

            String strArr = br.readLine();
            String[] arr = new String[arrLen];

            if (arrLen != 0) {
                arr = strArr.substring(1, strArr.length() - 1).split(",");
            }

            dq = new ArrayDeque<>();
            for (String strNum : arr) {
                dq.add(Integer.valueOf(strNum));
            }

            bw.write(manipulate(cmds));
            bw.newLine();
        }

        bw.flush();
    }

    private static String manipulate(char[] cmds) {

        boolean isFront = true; // false -> rear

        for (int i = 0; i < cmds.length; i++) {
            char cmd = cmds[i];
            

            if (cmd == 'R') {
                isFront = !isFront;
            }
            else {
                if (dq.isEmpty()) {
                    return "error";
                }

                if (isFront) {
                    dq.poll();
                }
                else {
                    dq.pollLast();
                }
            }
        }

        return formatter(isFront);
    }

    private static String formatter(boolean isFront) {
        StringBuilder sb = new StringBuilder();
        
        int remain = dq.size();

        sb.append("[");

        for (int i = 0; i < remain; i++) {

            if (i == 0) {
                if (isFront) {
                    sb.append(dq.poll());
                }
                else {
                    sb.append(dq.pollLast());
                }
                continue;
            }

            if (isFront) {
                sb.append("," + dq.poll());
            }
            else {
                sb.append("," + dq.pollLast());
            }
        }

        sb.append("]");
        return sb.toString();
    }
}