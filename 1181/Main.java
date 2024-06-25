import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());
        List<String> words = new ArrayList<>();

        for (int i = 0; i < tc; i++) {
            words.add(br.readLine());
        }

        //중복제거
        words = words.stream().distinct().collect(Collectors.toList());

        sortByCond(words);

        for (String word : words) {
            bw.write(word);
            bw.newLine();
            bw.flush();
        }
    }
    
    static void sortByCond(List<String> words) {
        //1. 길이 짧은 순 (주요 정렬 기준)
        //2. 길이가 같으면 사전 순
        
        for (int i = 0; i < words.size() - 1; i++) {

            String minWord = words.get(i);

            for (int j = i + 1; j < words.size(); j++) {
                
                String cmpWord = words.get(j);

                if (minWord.length() > cmpWord.length()) {
                    minWord = cmpWord;
                    Collections.swap(words, i, j);
                    continue;
                }

                if (minWord.length() == cmpWord.length()) {
                    if (minWord.compareTo(cmpWord) > 0) { //minWord가 사전 상 cmpWord 보다 큰 경우
                        minWord = cmpWord;
                        Collections.swap(words, i, j);
                        continue;
                    }
                }
                
            }
        }
    }

}
