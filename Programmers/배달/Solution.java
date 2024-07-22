import java.util.*;

class Solution {
    
    private static class Node {
        int num;
        int cost;
        public Node(int num, int cost) { this.num = num; this.cost = cost; }
    }
    
    private static List<Node>[] adjLists;
    private static boolean[] visited;
    private static int[] minDeliveryTime;
    
    public int solution(int N, int[][] road, int K) {
        
        //가중치가 존재하는 양방향 그래프
        adjLists = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            adjLists[i] = new ArrayList<>();
        }
        
        for(int[] info: road) {
            adjLists[info[0]].add(new Node(info[1], info[2]));
            adjLists[info[1]].add(new Node(info[0], info[2]));
        }
        
        visited = new boolean[N + 1];
        minDeliveryTime = new int[N + 1];
        Arrays.fill(minDeliveryTime, Integer.MAX_VALUE);
        dijkstra(1); // 1번 마을에 있는 음식점에서 각 마을로 음식 배달
        return (int) Arrays.stream(minDeliveryTime).filter(time -> time <= K).count();
    }
    
    private static void dijkstra(int start) {
        
        minDeliveryTime[start] = 0;
        
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        pq.add(new Node(start, 0));
        
        while(!pq.isEmpty()) {
            
            Node now = pq.poll();
            
            if (visited[now.num])
                continue;

            visited[now.num] = true;
            
            List<Node> adjNodes = adjLists[now.num];
            
            for(Node adjNode: adjNodes) {
                if (minDeliveryTime[adjNode.num] > minDeliveryTime[now.num] + adjNode.cost) {
                    minDeliveryTime[adjNode.num] = minDeliveryTime[now.num] + adjNode.cost;
                    pq.add(new Node(adjNode.num, minDeliveryTime[adjNode.num]));
                }
                
            }
            
        }
        
    }
    
}