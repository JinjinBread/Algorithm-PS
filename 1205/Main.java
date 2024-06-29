import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        
        //노래 마다 정해져 있는 점수가 비오름차순으로 되어 있는 노래리스트
        //비오름차순? 각 항목이 앞의 항목 보다 작거나 같은 경우 (내림차순과 유사)

        //입력: 랭킹 리스트에 있는 점수의 개수 N, 태수의 점수, 랭킹 리스트에 올라 갈 수 있는 점수의 개수 P
        //입력: 비오름차순으로 되어 있는 N개의 랭킹 리스트 점수

        //출력: 태수가 몇 등 하는지? 만약, 올라갈 수 없다면 -1 출력
        //예) 10 1 10
        //    10 9 8 7 6 5 4 3 2 1 -> 태수의 점수는 1점으로 10등이지만, 랭킹 리스트에는 10개 밖에 못 올라가므로
        // -1을 출력한다.

        // 1. 태수 점수가 랭킹 리스트에서 크기로 몇 번째인지?
        // 2. P 보다 크면 -1

        //태수 점수 포함 현재 랭킹 리스트를 내림차순 정렬함
        //태수 점수의 idx + 1 -> 현재 랭킹 순위
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st1.nextToken());
        int tsScore = Integer.parseInt(st1.nextToken());
        int P = Integer.parseInt(st1.nextToken());

        List<Integer> curRank = new ArrayList<>();
        if (N > 0) {
            StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                curRank.add(Integer.parseInt(st2.nextToken()));
            }
        }
        curRank.add(tsScore);

        Collections.sort(curRank, Collections.reverseOrder());
        
        int tsRank = curRank.indexOf(tsScore) + 1;

        //만약 현재 N과 P가 같으면서 맨 마지막 요소와 태수 점수가 같으면 -1
        if ((N == P) && ((curRank.get(curRank.size() - 1)) == tsScore)) {
            tsRank = -1;
        }

        bw.write(String.valueOf(tsRank));
        bw.flush();
    }
}
