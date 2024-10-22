import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];

        for (int i = 0; i <= n; i++) {
            parent[i] = i; // 부모를 자기자신으로 초기화
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int oper = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 합집합
            if (oper == 0) {
                if (a==b) {
                    continue;
                }
                union(a, b);
            }
            // 같은 집합에 있는지 확인하는 연산
            else {
                if (a==b || isSameSet(a, b)) {
                    bw.write("YES");
                    bw.newLine();
                    continue;
                }

                bw.write("NO");
                bw.newLine();
            }
        }

        bw.flush();
    }

    static boolean isSameSet(int a, int b) {
        return getRootNode(a) == getRootNode(b);
    }

    static int getRootNode(int num) {
        if (parent[num] == num) {
            return num;
        }

        parent[num] = getRootNode(parent[num]);
        return parent[num];
    }

    static void union(int a, int b) {
        int aRoot = getRootNode(a);
        int bRoot = getRootNode(b);
        
        if (aRoot != bRoot) {
            parent[aRoot] = bRoot;
        }
    }
}
