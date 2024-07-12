import java.util.*;
import java.util.stream.Stream;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        
        // 장르 별 고유번호[0]와 재생 횟수[1]
        Map<String, List<int[]>> genreMap = new HashMap<>();
        // 장르 별 재생 횟수
        Map<String, Integer> playMap = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];

            // 초기화
            if (!genreMap.containsKey(genre)) {
                genreMap.put(genre, new ArrayList<>());
                playMap.put(genre, 0);
            }

            genreMap.get(genre).add(new int[]{i, play});
            playMap.put(genre, playMap.get(genre) + play);
        }

        List<Integer> answer = new ArrayList<>();

        Stream<Map.Entry<String, Integer>> sortedGenres = 
            playMap.entrySet().stream().sorted((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));

        sortedGenres.forEach(entry -> {
            // 가장 많이 재생된 장르, 그 중 많이 재생된 노래를 먼저 수록
            // 재생 횟수가 같으면 o1, o2의 순서가 바뀌지 않음 -> 즉 고유번호가 더 낮은 노래가 먼저 수록된다. (장르 별 최대 2개의 노래만 수록)
            Stream<int[]> sortedSongs = genreMap.get(entry.getKey()).stream().sorted((o1, o2) -> Integer.compare(o2[1], o1[1])).limit(2);
            sortedSongs.forEach(song -> answer.add(song[0])); // int[] [0] 고유번호 [1] 재생 횟수 -> 0번 고유 번호를 저장
        });

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}