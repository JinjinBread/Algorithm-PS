import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    private static Set<Integer> S;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        S = new HashSet<>();

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if (st.countTokens() == 0) { // all or empty
                switch (cmd) {
                    case "all":
                        all();
                        break;
                    case "empty":
                        empty();
                        break;
                }
            }
            else {
                int x = Integer.parseInt(st.nextToken());
                switch (cmd) {
                    case "add":
                        add(x);
                        break;
                    case "remove":
                        remove(x);
                        break;
                    case "check":
                        bw.write(String.valueOf(check(x)));
                        bw.newLine();
                        break;
                    case "toggle":
                        toggle(x);
                        break;
                }
            }
        }

        bw.flush();
    }

    private static void add(int x) {
        S.add(x);
    }

    private static void remove(int x) {
        S.remove(x);
    }

    private static int check(int x) {
        return S.contains(x) ? 1 : 0;
    }

    private static void toggle(int x) {
        if (S.contains(x)) {
            remove(x);
        }
        else {
            add(x);
        }
    }

    private static void all() {
        S.removeAll(S);
        for (int i = 1; i <= 20; i++) {
            S.add(i);
        }
    }
    
    private static void empty() {
        S.removeAll(S);
    }

}
