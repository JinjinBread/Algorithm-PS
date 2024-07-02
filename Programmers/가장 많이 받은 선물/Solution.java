import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

class Solution {

    // 이번 달까지 선물을 주고받은 기록을 바탕으로, 다음 달에 누가 선물을 많이 받을까?
    // 두 사람에게 선물 교환 기록이 있다면 -> '두 사람 사이에' 선물을 더 많이 준 사람이 다음 달에 선물을 하나 받는다.
    
    // 기록이 없거나 주고받은 선물의 수가 같다면 -> 선물 지수가 더 큰 사람이 선물을 받는다.
    //  선물 지수란? --> 자신이 친구들에게 준 선물의 수 - 친구들에게 받은 선물의 수
    //  만약 두 사람의 선물 지수도 같다면, 다음 달에는 선물을 주고 받지 않는다.
    // 문제! 다음 달에 선물을 주고받을 때, 선물을 가장 많이 받을 친구가 받을 선물의 수는?

    private class GiftInfo {
        String name;
        Map<String, Integer> giveTakeList = new HashMap<>();
        // Set<GiftInfo> giveTakeList = new HashSet<>();
        int giveNum = 0; //준 선물
        int takeNum = 0; //받은 선물

        public GiftInfo(String name, String[] friends) {
             this.name = name;

            List<String> friendsExceptMe = Arrays.stream(friends)
                    .filter(friend -> !friend.equals(name)) // 자기 자신은 제외
                    .collect(Collectors.toList());

             for (String friend : friendsExceptMe) {
                giveTakeList.put(friend, 0);
             }
        }

        public int getGiftNum() { return this.giveNum - this.takeNum; }
    }
    
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        List<GiftInfo> giftInfos = new ArrayList<>();

        for (String friend : friends) {
            giftInfos.add(new GiftInfo(friend, friends));
        }

        // gifts의 원소는 "A B"형태의 문자열. A는 선물을 준 친구의 이름을 B는 선물을 받은 친구의 이름
        for (String giftList : gifts) {

            StringTokenizer st = new StringTokenizer(giftList);
            String A = st.nextToken();
            String B = st.nextToken();

            GiftInfo aInfo = getGiftInfo(giftInfos, A);
            GiftInfo bInfo = getGiftInfo(giftInfos, B);

            int bGiftNum = aInfo.giveTakeList.get(B);
            aInfo.giveTakeList.put(B, ++bGiftNum);

            aInfo.giveNum++;
            bInfo.takeNum++;
        }

        Map<String, Integer> takeNumList = new HashMap<>();

        for (GiftInfo aGiftInfo : giftInfos) {
            
            int willTakeNum = 0;

            Map<String, Integer> giveTakeList = aGiftInfo.giveTakeList;

            for (String friend : giveTakeList.keySet()) {

                GiftInfo bGiftInfo = getGiftInfo(giftInfos, friend);
                int aGiveToBnum = giveTakeList.get(friend); // A가 B에게 준 선물 수
                int bGiveToAnum = bGiftInfo.giveTakeList.get(aGiftInfo.name); // B가 A에게 준 선물 수

                //선물을 주고 받은 적이 없거나 주고받은 수가 같음
                if (aGiveToBnum == bGiveToAnum) {
                    if (aGiftInfo.getGiftNum() > bGiftInfo.getGiftNum())
                        willTakeNum++;
                    continue;
                }

                //선물을 주고 받은 기록이 있음
                if (aGiveToBnum > bGiveToAnum)
                    willTakeNum++;
            }

            takeNumList.put(aGiftInfo.name, willTakeNum);
        }

        takeNumList.values()
                    .stream()
                    .forEach(a -> System.out.println(a));

        answer = takeNumList.values()
                            .stream()
                            .max(Integer::compareTo)
                            .orElse(answer);

        return answer;
    }

    public GiftInfo getGiftInfo(List<GiftInfo> giftInfos, String name) {
        return giftInfos.stream()
                        .filter(giftInfo -> giftInfo.name.equals(name))
                        .findFirst()
                        .get();
    }
}