import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        
        //A를 재배열하여 S를 작게 만드는 문제
        //B는 재배열 불가

        //그렇다면, B를 크기가 큰 인덱스를 순서대로 저장하고
        //오름차순으로 정렬한 A를 크키 순으로 저장한 인덱스에 해당하는 B의 값과 계산하면 될 것 같음.
        //A를 해당 인덱스에 맞게 재배열

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());
        int[] A = new int[num];
        int[] B = new int[num];
        int[][] AB = {A, B};

        int[] indices = new int[num];

        for (int i = 0; i < 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < num; j++) {
                AB[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < indices.length; i++) {
            indices[i] = i;
        }

        //버블정렬을 통하여 작은 값을 가진 인덱스를 맨 뒤로 보냄
        for (int i = 0; i < num - 1; i++) {
            for (int j = 0; j < num - 1 - i; j++) {
                if (B[indices[j + 1]] > B[indices[j]]) {
                    int temp = indices[j];
                    indices[j] = indices[j + 1];
                    indices[j + 1] = temp;
                }
            }
        }

        Arrays.sort(A);
        int sum = 0;
        for (int i = 0; i < num; i++) {
            sum += A[i] * B[indices[i]];
        }

        bw.write(String.valueOf(sum));
        bw.flush();
    }

}