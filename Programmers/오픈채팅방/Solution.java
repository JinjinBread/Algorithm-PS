import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        
        int resultNum = 0;
        
        //userId, nickname
        Map<String, String> users = new HashMap<>();
        
        for(String s: record) {
            
            String[] str = s.split(" ");
            
            // map에 관한 작업은 change or enter 일 때만 진행한다.

            // leave는 nickname을 안 받기 때문에 길이가 2이다.
            // 따라서 change나 enter인 경우로 나눌 경우, str.lenght == 3으로 조건을 주면 된다.
            if (str.length == 3) {
                users.put(str[1], str[2]);
            }

            // if (str[0].equals("Change")) {
            //     users.put(str[1], str[2]);
            // }
            
            // else if (str[0].equals("Enter")) {
            //     users.put(str[1], str[2]);
            // }
        }
        
        ArrayList<String> result = new ArrayList<>();
        for (String s: record) {
            String[] str = s.split(" ");
            String userId = str[1];
            
            if (str[0].equals("Leave")) {
                result.add(users.get(userId) + "님이 나갔습니다.");
            }
            
            else if (str[0].equals("Enter")) {
                result.add(users.get(userId) + "님이 들어왔습니다.");
            }
            
        }
        
        return result.toArray(String[]::new);
    }
}