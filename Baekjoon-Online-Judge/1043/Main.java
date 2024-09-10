import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Integer>[] joinerInfo;
    static List<Integer>[] partyInfo;
    static boolean[] knwon;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int truthKnownNum = Integer.parseInt(st.nextToken());

        int[] originalKnown = new int[truthKnownNum];
        knwon = new boolean[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < truthKnownNum; i++) {
            int truthNum = Integer.parseInt(st.nextToken());
            originalKnown[i] = truthNum;
            knwon[truthNum] = true;
        }

        joinerInfo = new ArrayList[N+1];
        partyInfo = new ArrayList[M+1];

        for (int i = 1; i <= N; i++) {
            joinerInfo[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            partyInfo[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int partyJoinNum = Integer.parseInt(st.nextToken());
            int[] joiners = new int[partyJoinNum];
            for (int j = 0; j < partyJoinNum; j++) {
                int joiner = Integer.parseInt(st.nextToken());
                joiners[j] = joiner;
                partyInfo[i].add(joiner);
            }

            for (int j = 0; j < partyJoinNum; j++) {
                int center = joiners[j];
                for (int k = 0; k < partyJoinNum; k++) {
                    if (j==k) continue;
                    joinerInfo[center].add(joiners[k]);
                }
            }
        }

        for (int i = 0; i < truthKnownNum; i++) {
            dfs(originalKnown[i]);
        }

        int canGoPartyNum = 0;
        A: for (int i = 1; i <= M; i++) {
            for (int partyJoiner : partyInfo[i]) {
                if (knwon[partyJoiner]) {
                    continue A;
                }
            }
            canGoPartyNum++;
        }

        bw.write(String.valueOf(canGoPartyNum));
        bw.flush();
    }

    static void dfs(int truthNum) {

        if (visited[truthNum])
            return;

        visited[truthNum] = true;
        List<Integer> adjNodes = joinerInfo[truthNum];

        for (int adjNode : adjNodes) {
            knwon[adjNode] = true;
            dfs(adjNode);
        }
    }
}
