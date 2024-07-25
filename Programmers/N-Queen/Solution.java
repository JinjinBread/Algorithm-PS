
class Solution {
    
    private static boolean[] board; // 행
    private static boolean[] antidiagonal; // 반대각선 (왼쪽 아래에서 오른쪽 위) (반대각선은 행 + 열의 값이 같다.)
    private static boolean[] diagonal; // 주대각선 (왼쪽 위에서 오른쪽 아래) (주대각선은 행 - 열(or 열 - 행)의 값이 같다.)
    private static int N;
    
    // 같은 행, 열 + 대각선에 존재하면 백트래킹
    private static int backtrack(int r) {
        
        int ans = 0;

        // 퀸이 다 채워졌으면
        if (r == N) {
            ans++;
        }
        else {
            for (int i = 0; i < N; i++) {
                // 현재 열(i)에 퀸이 있거나 대각선에 퀸에 있으면
                // 같은 (행 - 열)의 값을 가진 대각선이 있으면(주대각선) 퀸 설치 불가. (+ N을 하는 이유는 -가 나와 인덱스를 벗어날 수 있기 때문임)
                if (board[i] || antidiagonal[r+i] || diagonal[r-i+N]) {
                    continue;
                }
    
                board[i] = antidiagonal[r+i] = diagonal[r-i+N] = true;
                ans += backtrack(r + 1);
                board[i] = antidiagonal[r+i] = diagonal[r-i+N] = false;
            }
        }
        
        return ans;
    }
    
    public int solution(int n) {
        
        board = new boolean[n];
        antidiagonal = new boolean[n*2]; // 대각선은 n*2로 널널하게 잡아줌 (값: 최소 0, 최대 2(n-1))
        diagonal = new boolean[n*2]; // (값: 최소 n, 최대 2n - 1)
        N = n;
        
        return backtrack(0);
    }
}