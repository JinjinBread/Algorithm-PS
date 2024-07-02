import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

public class Main {
    
    //모든 학생의 학번 -> n개의 길이, 0~9로 이루어짐, 학번은 모두 다름
    //입력한 학번을 뒤에서부터 k자리만으로 모두 다른 학번으로 만들 수 있는 최소의 k를 구하라.

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int studentNum = Integer.parseInt(br.readLine());
        String[] studentId = new String[studentNum];

        for (int i = 0; i < studentNum; i++) {
            studentId[i] = br.readLine();
        }

        int len = studentId[0].length();
        Set<String> set = new HashSet<>();

        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < studentNum; j++) {
                set.add(studentId[j].substring(len - i));
            }

            if (set.size() == studentNum) {
                bw.write(String.valueOf(i));
                bw.flush();
                return;
            }

            set.clear();
        }
    }
}
