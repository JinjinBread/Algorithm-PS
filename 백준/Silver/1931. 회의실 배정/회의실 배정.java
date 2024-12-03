import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    
    static class Meeting {
        int startTime;
        int endTime;
        public Meeting(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    static PriorityQueue<Meeting> pq;

    // 빨리 끝나는 회의 先
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        
        pq = new PriorityQueue<>((m1, m2) -> {
            if (m1.endTime != m2.endTime) {
                return Integer.compare(m1.endTime, m2.endTime);
            }
            return Integer.compare(m1.startTime, m2.startTime);
        }); // 끝나는 시간이 빠른(작은) 회의를 우선으로 두고, 만약 같다면 시작 시간이 빠른(작은) 회의를 우선으로 둔다.

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());
            pq.add(new Meeting(startTime, endTime));
        }

        int used = 0;
        int endTime = 0;
        while (!pq.isEmpty()) {

            Meeting now = pq.poll();

            if (now.startTime < endTime) {
                continue;
            }

            used++;
            endTime = now.endTime;
        }
        
        System.out.println(used);
    }

}
