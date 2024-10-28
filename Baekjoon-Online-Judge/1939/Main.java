import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int dest;
        int weight;
        public Node(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    static int N;
    static List<Node>[] adjLists;
    static boolean[] visited;
    static int maxCost = Integer.MIN_VALUE;
    static int to;
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        adjLists = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            adjLists[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            // 서로 같은 두 섬 사이에 여러 개의 다리가 있을 수 있다하여
            // 더 큰 중량을 옮길 수 있는 다리로 갱신하려 했으나, (이럴 경우 시간 초과가 발생)
            // 그럴 필요없이 모든 간선을 저장해도 됨
            // if (isBigger(A, B, C)) {
            adjLists[A].add(new Node(B, C));
            adjLists[B].add(new Node(A, C)); // 양방향
            // }

            maxCost = Math.max(maxCost, C);
        }

        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        to = Integer.parseInt(st.nextToken());

        binarySearch(from);
        System.out.println(answer);
    }

    static boolean dfs(int nodeNum, int weight) {

        // 도착지에 도착
        if (nodeNum == to) {
            return true;
        }

        visited[nodeNum] = true;

        List<Node> adjNodes = adjLists[nodeNum];

        for (Node node : adjNodes) {
            // 방문했거나 기준 weight 보다 작은 weight 다리라면 넘김
            if (visited[node.dest] || node.weight < weight) {
                continue;
            }

            // return dfs(node.dest, weight); // 이 경우 첫 번째 dfs의 결과가 그대로 return 되므로 모든 경로를 탐색 X
            if (dfs(node.dest, weight)) {
                return true;
            }
        }

        return false;
    }

    static void binarySearch(int start) {
        int low = 1;
        int high = maxCost;

        while (low <= high) {

            Arrays.fill(visited, false);
            int mid = (low + high) / 2;
            if (dfs(start, mid)) {
                answer = mid;
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }

        }
    }
    
    // static boolean isBigger(int from, int to, int weight) {

    //     Node node = adjLists[from].stream().filter(n -> n.dest == to).findFirst().orElse(null);

    //     // 비어있거나 입력된 weight가 더 큰 weight면
    //     if (node == null || node.weight < weight) {
    //         return true;
    //     }

    //     return false;
    // }

}
