import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    //A가 B의 2-친구가 되기 위해선
    //1. 서로 친구이거나
    //2. A의 친구이면서 B의 친구인 C가 존재해야 한다.
    //즉 '나'의 2-친구 수는 '나'의 친구 수 + 친구의 친구 수('나' 제외)
    //나와 친구, 친구의 친구가 모두 친구일 경우도 생각 (--> 중복 친구 제외)

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //사람 수
        int N = Integer.parseInt(br.readLine());
        char[][] relationship = new char[N][N];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                relationship[i][j] = input.charAt(j);
            }
        }

        int[] friendNum = new int[N];
        Set<Integer> friendRelationship = new HashSet<>();

        //N명의 2-친구의 수 중 2-친구의 수 최댓값?

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (relationship[i][j] == 'Y') {
                    friendRelationship.add(j);
                    friendRelationship.addAll(getFriendOfFriendNum(relationship[j], i));
                }
            }
            friendNum[i] += friendRelationship.size();
            friendRelationship.clear();
        }

        int maxFriend = Arrays.stream(friendNum).max().getAsInt();
        bw.write(String.valueOf(maxFriend));
        bw.flush();
    }

    static List<Integer> getFriendOfFriendNum(char[] friendRelationship, int me) {
        
        List<Integer> friend = new ArrayList<>();

        for (int i = 0; i < friendRelationship.length; i++) {
            if (i == me)
                continue;

            if (friendRelationship[i] == 'Y')
                friend.add(i);
        }

        return friend;
    }
}
