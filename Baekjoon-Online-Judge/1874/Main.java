import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class Main {
    
    // 스택에 맨 위(top)에 있는 수와 나와야 하는 수열의 수가 일치하면 pop

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int index = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {

            if (i != 1)
                sb.append("\n");

            stack.addFirst(i);
            sb.append("+");

            while (!stack.isEmpty() && stack.peek() == arr[index]) {
                stack.pop();
                sb.append("\n");
                sb.append("-");
                index++;
            }
        }

        if (!stack.isEmpty()) {
            bw.write("NO");
            bw.flush();
            return;
        }

        bw.write(sb.toString());
        bw.flush();
    }
    
}
