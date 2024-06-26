import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static int[][] classNum;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int studentNum = Integer.parseInt(br.readLine());
        classNum = new int[studentNum][5];

        for (int i = 0; i < studentNum; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 5; j++) {
                classNum[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //1. 각 학생마다 같은 반이었던 학우의 수를 구함
        //2. 합의 최댓값을 구하고
        //3. 해당 합을 지닌 학생 중 가장 번호가 작은 학생을 뽑음

        int maxSum = 0;
        int captinNumber = 0;

        for (int i = 0; i < studentNum; i++) {
            int sum = sumSameClassNum(i);
            if (sum > maxSum) { //sum이 maxSum과 같아도(즉 임시 후보여도)
                maxSum = sum;
                captinNumber = i; //sum이 maxSum과 같아도(즉 임시 후보여도)
            }
        }

        bw.write(String.valueOf(captinNumber + 1));
        bw.flush();
    }

    public static int sumSameClassNum(int studentNumber) {

        int[] nStudentClass = classNum[studentNumber];
        //Set을 사용해 이미 같은 반이 된 적 있는 학생의 번호를 또 넣지 않음
        Set<Integer> sameClassStudent = new HashSet<>();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < classNum.length; j++) {
                if (j == studentNumber)
                    continue;

                if (nStudentClass[i] == classNum[j][i])
                    sameClassStudent.add(j);
            }
        }

        return sameClassStudent.size();
    }
}
