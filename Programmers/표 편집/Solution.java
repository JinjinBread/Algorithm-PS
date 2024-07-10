import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        
        //0행 ~ n-1행
        //U X: 현재 선택된 행에서 X칸 위에 있는 행을 선택한다.
        //D X: 현재 선택된 행에서 X칸 아래에 있는 행을 선택한다.
        //C: 현재 선택된 행을 삭제하고 바로 아래 행을 선택한다.
        //만약 맨 마지막 행을 삭제한 경우 바로 윗 행을 선택한다.
        //Z: 가장 최근에 삭제한 행을 원상 복구한다. (선택된 행은 바뀌지 않는다.)
        
        //처음 표의 행 개수 n
        //처음 선택된 행의 위치 k
        //수행한 명령어들이 담긴 문자열 배열 cmd
        
        ArrayDeque<Integer> deletedHistory = new ArrayDeque<>();
        // 0 ~ (n-1) 행의 위 행, 아래 행을 저장
        // + 2를 해주는 이유는 행이 맨 처음에 혹은 맨 끝에 삽입될 수 있으므로
        // 가상 공간을 만들어준다.
        int[] up = new int[n + 2];
        int[] down = new int[n + 2];
        
        for (int i = 0; i < n + 2; i++) {
            up[i] = i - 1;
            down[i] = i + 1;
        }
        
        // 현재 위치한 행
        // + 1을 해준다. (맨 위에 가상 공간 때문에)
        k++;
        
        for (String c : cmd) {
            
            if (c.startsWith("C")) {
                deletedHistory.push(k);
                up[down[k]] = up[k]; // k행의 아래 있던 행은 k의 위에 있는 행을 바라보게 되고
                down[up[k]] = down[k];// k의 위에 있던 행은 k의 아래에 있는 행을 바라보게 된다.
                k = n < down[k] ? up[k] : down[k]; // 마지막 행을 삭제한 거면 위의 행을 선택 아니라면 아래 행을 선택
            }
            // Z (Undo)
            else if (c.startsWith("Z")) {
                int rollbackRow = deletedHistory.pop();
                down[up[rollbackRow]] = rollbackRow; //복원하는 행의 위에 있던 행의 아래 행을 다시 복원하는 행으로 바꾼다.
                up[down[rollbackRow]] = rollbackRow; //복원하는 행의 아래에 있던 행의 윗 행을 다시 복원하는 행으로 바꾼다.
            }
            // U or D
            else {
                String[] s = c.split(" ");
                int moveNum = Integer.parseInt(s[1]);
                for (int i = 0; i < moveNum; i++) { //moveNum만큼 올라가거나 내려간다.
                    k = s[0].equals("U") ? up[k] : down[k];
                }
            }
        }
        char[] result = new char[n];
        Arrays.fill(result, 'O'); //result 배열의 모든 값을 'O'로 초기화한다.
        
        while (!deletedHistory.isEmpty()) {
            int row = deletedHistory.pop();
            // 가상 공간으로 + 1을 했기 때문에 - 1을 해준다.
            result[row - 1] = 'X';
        }
        
        return String.valueOf(result);
    }   
}