import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

    static class Ingredient {
        int weight;
        int cal;
        public Ingredient(int weight, int cal) {
            this.weight = weight;
            this.cal = cal;
        }
    }

    static int[][] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken()); // 재료 수
            int L = Integer.parseInt(st.nextToken()); // 제한 칼로리

            PriorityQueue<Ingredient> ingredients = new PriorityQueue<>((i1, i2) -> Integer.compare(i1.cal, i2.cal));
            dp = new int[N+1][L+1];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int weight = Integer.parseInt(st.nextToken());
                int cal = Integer.parseInt(st.nextToken());
                ingredients.add(new Ingredient(weight, cal));
            }


            for (int i = 1; i <= N; i++) {

                Ingredient ingredient = ingredients.poll();

                for (int j = 1; j <= L; j++) {
                    
                    if (j < ingredient.cal) {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    }

                    else if (j == ingredient.cal) {
                        dp[i][j] = Math.max(dp[i-1][j], ingredient.weight);
                    }

                    else if (j > ingredient.cal) {
                        int rest = j - ingredient.cal;
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][rest] + ingredient.weight);
                    }

                }

            }

            bw.write("#" + test_case + " " + dp[N][L]);
            bw.newLine();
        }

        bw.flush();
    }

}
