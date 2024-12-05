import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    /*
     * 맞물리는 가로 너비를 가장 짧게 만들기
     * 1이 들어간 곳, 2가 튀어나온 곳
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] chGear1 = br.readLine().toCharArray(); // 더 긴 기어
        char[] chGear2 = br.readLine().toCharArray();

        int[] gear1 = new int[chGear1.length];
        int[] gear2 = new int[chGear2.length];

        for (int i = 0; i < chGear1.length; i++) {
            gear1[i] = chGear1[i] - '0';
        }

        for (int i = 0; i < chGear2.length; i++) {
            gear2[i] = chGear2[i] - '0';
        }

        if (gear1.length < gear2.length) {
            int[] temp = gear1;
            gear1 = gear2;
            gear2 = temp;
        }

        // 홈(1)일 경우 1(홈)이 오든 2(이)가 오든 상관없다.
        // 이(2)일 경우엔 반드시 1(홈)이어야 한다.

        int result = gear1.length + gear2.length; // 최악의 경우

        // 딱 들어 맞는 경우
        A: for (int i = 0; i < gear1.length; i++) {
            
            if (gear2[0] == 1 || (gear2[0] == 2 && gear1[i] == 1)) {
                int gear1Start = i + 1;
                for (int j = 1; j < gear2.length; j++) {
                    if (gear1Start >= gear1.length) {
                        break A;
                    }

                    if (gear2[j] == 1 || (gear2[j] == 2 && gear1[gear1Start] == 1)) {
                        gear1Start++;
                    }
                    else {
                        break;
                    }
                }
                
                if ((gear1Start - i) == gear2.length) {
                    System.out.println(gear1.length);
                    return;
                }
            }
        }

        
        // gear2의 앞에서부터
        for (int i = 1; i < gear2.length - 1; i++) {
            
            if (gear2[i] == 1 || (gear2[i] == 2 && gear1[0] == 1)) {
                int gear1Start = 1;
                for (int j = i+1; j < gear2.length; j++) {
                    if (gear2[j] == 1 || (gear2[j] == 2 && gear1[gear1Start] == 1)) {
                        gear1Start++;
                    }
                    else {
                        break;
                    }
                }

                if (gear1Start + i == gear2.length) {
                    result = Math.min(result, gear1.length + i);
                }
            }

        }

        // gear2의 뒤에서부터
        for (int i = 2; i < gear2.length - 1; i++) {
            
            if (gear2[gear2.length - i] == 1 || (gear2[gear2.length - i] == 2 && gear1[gear1.length - 1] == 1)) {
                int gear1Start = 2;
                for (int j = i+1; j <= gear2.length; j++) {
                    if (gear2[gear2.length - j] == 1 || (gear2[gear2.length - j] == 2 && gear1[gear1.length - gear1Start] == 1)) {
                        gear1Start++;
                    }
                    else {
                        break;
                    }
                }

                if ((gear1Start + (i-2)) == gear2.length) {
                    result = Math.min(result, gear1.length + (i - 1));
                }
            }

        }

        System.out.println(result);
        
    }
}