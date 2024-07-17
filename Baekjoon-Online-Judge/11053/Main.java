import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] numArr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        // 1. 수열을 오름 차순 정렬한다.
        int[] numOrder = Arrays.copyOf(numArr, N);
        List<Integer> orderList = Arrays.stream(numOrder).distinct().sorted().boxed().collect(Collectors.toList());

        int[] numArrOrder = new int[N];
        // 2. 원래 순서의 수열에 크기별 idx를 넣는다.
        for (int i = 0; i < N; i++) {
            int num = numArr[i];
            numArrOrder[i] = orderList.indexOf(num);
        }

        int maxLength = 1; // 최소 1
        for (int i = 0; i < numArr.length; i++) {
            // 모든 수열을 돌면서 첫 시작 지점으로 선택
            // 첫 시작 지점일 때, 여러 개의 증가하는 부분 수열 중 가장 긴 부분 수열의 길이를 구함
            maxLength = Math.max(calculateLength(numArr, numArrOrder, i), maxLength);
        }

        bw.write(String.valueOf(maxLength));
        bw.flush();
    }

    private static int calculateLength(int[] numArr, int[] numArrOrder, int startIdx) {

        int maxLength = 1;

        for (int i = startIdx + 1; i < numArr.length; i++) {
            
            // i 번째에 있는 수가 현재 startIdx 보다 큰 수면 타고 들어감
            if (numArrOrder[i] > numArrOrder[startIdx]) {
                maxLength = Math.max(maxLength, 1 + calculateLength(numArr, numArrOrder, i));
            }
        }

        // startIdx가 제일 큰 수 이거나 제일 마지막에 있으면 1 리턴
        return maxLength;
    }
}
