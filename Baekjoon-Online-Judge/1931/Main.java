import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static class Time {
        int startTime;
        int endTime;
        public Time(int startTime, int endTime) { this.startTime = startTime; this.endTime = endTime; }
    }

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        List<Time> times = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());
            times.add(new Time(startTime, endTime));
        }

        // 끝나는 시간 순으로 정렬
        times.sort((o1, o2) -> {
            if (o1.endTime == o2.endTime)
                return Integer.compare(o1.startTime, o2.startTime);
            return Integer.compare(o1.endTime, o2.endTime);
        });


        int answer = 1;
        Time startTime = times.get(0);
        for (int i = 1; i < times.size(); i++) {
            Time time = times.get(i);
            if (time.startTime >= startTime.endTime) {
                answer++;
                startTime = time;
            }
        }
        
        bw.write(String.valueOf(answer));
        bw.flush();
    }
}