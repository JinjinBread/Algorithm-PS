import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static BufferedWriter bw;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        backtrack(new ArrayList<>());
        bw.flush();
    }

    private static void backtrack(List<Integer> arr) throws IOException {

        if (arr.size() == M) {
            for (Integer i : arr) {
                bw.write(i + " ");
            }
            bw.newLine();
            return;
        }

        for (int i = 1; i <= N; i++) {

            // 오름차순으로 만들기 위한 분기문
            if (arr.size() > 0 && arr.get(arr.size() - 1) > i)
                continue;

            if (arr.contains(i))
                continue;

            arr.add(i);
            backtrack(arr);
            arr.remove(Integer.valueOf(i));
        }

    }

}
