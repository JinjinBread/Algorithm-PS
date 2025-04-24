import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    
    private static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] height = new int[9];
        for(int i = 0; i < 9; i++) {
            height[i] = Integer.parseInt(br.readLine());
        }
        comb(height, 0, 0, 9, 7);
        result.sort(Comparator.naturalOrder());
        for(int res: result) {
            System.out.println(res);
        }
    }

    public static boolean comb(int[] height, int start, int sum, int n, int r) {

        if (r == 0) {
            if (sum == 100)
                return true;
            return false;
        }

        for(int i = start; i < height.length; i++) {
            sum += height[i];
            result.add(height[i]);

            if (comb(height, i+1, sum, n, r-1)) {
                return true;
            }
            sum -= height[i];
            result.remove(Integer.valueOf(height[i]));
        }

        return false;
    }

}