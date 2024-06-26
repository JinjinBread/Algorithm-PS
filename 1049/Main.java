import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        
        // 6줄 패키지 or 낱개
        // 끊어진 기타줄 N개
        // 기타줄 판매 브랜드 사 M개
        // 6줄 패키지 가격 / 낱개 가격
        
        // 1. 끊어진 기타줄을 6으로 나눠 몫, 나머지로 나눔 (ex. 15 -> 2, 3)
        // 2. 가장 싼 패키지 가격과 낱개 가격을 찾는다.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int guitarNum = Integer.parseInt(st.nextToken());
        int brandNum = Integer.parseInt(st.nextToken());
        int[][] brandPrice = new int[brandNum][2]; //[0] 패키지 가, [1] 낱개 가

        for (int i = 0; i < brandNum; i++) {
            StringTokenizer s = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < brandPrice[0].length; j++) {
                brandPrice[i][j] = Integer.parseInt(s.nextToken());
            }
        }

        // 가장 싼 패키지 가격과 낱개 가격을 찾는다.
        int minPkg = brandPrice[0][0];
        int minUnit = brandPrice[0][1];

        for (int i = 1; i < brandNum; i++) {
            
            if (minPkg > brandPrice[i][0]) {
                minPkg = brandPrice[i][0];
            }

            if (minUnit > brandPrice[i][1]) {
                minUnit = brandPrice[i][1];
            }
        }

        //올림(최대 패키지 구매 수)
        int pkg = (int) Math.ceil(Double.valueOf(guitarNum) / 6);

        //경우의 수
        //1. 모두 패키지로 사는 경우
        //2. 모두 낱개로 사는 경우
        //3. 패키지 + 낱개로 사는 경우
        int minPrice = pkg * minPkg; // 모두 패키지로 사는 경우
        
        for (int i = 0; i < pkg; i++) { //i는 패키지 개수
            int unit = guitarNum - (6 * i);
            int price = (minPkg * i) + (minUnit * unit);
            if (minPrice > price) {
                minPrice = price;
            }
        }

        bw.write(String.valueOf(minPrice));
        bw.flush();
    }
}