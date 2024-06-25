import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int arrNum = Integer.parseInt(br.readLine());

        int[] a = new int[arrNum];
        int[] b = new int[arrNum];
        int[] p = new int[arrNum];

        String[] arrStrs = br.readLine().split(" ");

        for (int i = 0; i < arrNum; i++) {
            a[i] = Integer.parseInt(arrStrs[i]);
            b[i] = Integer.parseInt(arrStrs[i]);
        }

        Arrays.sort(b);

        int k = 0;
        for (int i = 0; i < arrNum; i++) {
            int aFactor = a[i];

            for (int j = 0; j < arrNum; j++) {
                int bFactor = b[j];
                if (bFactor == aFactor) {
                    p[k++] = j;
                    b[j] = -1; // 중복 수가 있는 경우, 같은 idx가 나오므로
                    break;
                }
            }
        }

        for (int pFactor: p) {
            bw.write(String.valueOf(pFactor));
            bw.write(" ");
        }

        bw.flush();
    }
}
