import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        
        // 1. 장르 별로 고유번호 저장
        // 1-1. 장르 별로 재생 횟수 저장
        // 2. 장르 별 총 재생 횟수 계산
        // 3. 많이 재생된 장르 먼저 
        Map<String, List<Integer>> uniqueNumberByGenreMap = new HashMap<>();
        Map<String, List<Integer>> playsByGenreMap = new HashMap<>();
        List<String> mostPlayedGenre = new ArrayList<>();
        
        for(int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            
            List<Integer> uniqueNumberByGenre = 
                uniqueNumberByGenreMap.getOrDefault(genre, new ArrayList<>());
            uniqueNumberByGenre.add(i);
            uniqueNumberByGenreMap.put(genre, uniqueNumberByGenre);
            
            List<Integer> playsByGenre = 
                playsByGenreMap.getOrDefault(genre, new ArrayList<>());
            playsByGenre.add(plays[i]);
            playsByGenreMap.put(genre, playsByGenre);
        }

        mostPlayedGenre = playsByGenreMap.entrySet().stream()
            .sorted((map1, map2) -> {
                int sum1 = map1.getValue().stream().mapToInt(i -> i).sum();
                int sum2 = map2.getValue().stream().mapToInt(i -> i).sum();
                return Integer.compare(sum2, sum1); // 내림차순
            })
            .map(map -> map.getKey())
            .collect(Collectors.toList());
        
        // 재생 횟수 내림차순 정렬
        for(Map.Entry<String, List<Integer>> entry: playsByGenreMap.entrySet()) {
            List<Integer> playsByGenre = entry.getValue();
            playsByGenre.sort(Collections.reverseOrder());
        }
        
        List<Integer> answer = new ArrayList<>();
        List<Integer> playsList = Arrays.stream(plays)
                                        .boxed()
                                        .collect(Collectors.toList());
        
        for(String genre: mostPlayedGenre) {
            
            List<Integer> uniqueNumbers = uniqueNumberByGenreMap.get(genre);
            uniqueNumbers.sort((i, j) -> {
                return Integer.compare(plays[j], plays[i]);
            });
            
            List<Integer> playsByGenre = playsByGenreMap.get(genre);
            int max = playsByGenre.size() > 2 ? 2 : playsByGenre.size();
            for(int i = 0; i < max; i++) {
                int uniqueNumber = uniqueNumbers.get(i);
                answer.add(uniqueNumber);
            }
        }
        
        return answer.stream()
            .mapToInt(Integer::intValue).toArray();
    }
}