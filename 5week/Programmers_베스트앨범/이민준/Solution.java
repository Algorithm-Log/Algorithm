import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        // 재생 수 더할 map
        Map<String, Integer> mapGenres = new HashMap<>();
        
        // 장르 내에서 정렬하기 위한 map
        Map<String, List<int[]>> mapPlays = new HashMap<>();
        
        for(int i = 0; i < genres.length; i++){
            mapGenres.put(genres[i], mapGenres.getOrDefault(genres[i], 0) + plays[i]);
            mapPlays.put(genres[i], mapPlays.getOrDefault(genres[i], new ArrayList<>()));
            mapPlays.get(genres[i]).add(new int[] {i, plays[i]});
        }
        
        // 재생수 정렬
        List<String> genresList = new ArrayList<>(mapGenres.keySet());
        genresList.sort((a,b) -> mapGenres.get(b) - mapGenres.get(a)); // 내림차순 정렬
        
        // 답
        List<Integer> result = new ArrayList<>();
        // 재생수 별 두곡 선별
        for(String key : genresList){
            mapPlays.get(key).sort((a,b) -> a[1] == b[1] ? a[0] - b[0] : b[1] - a[1]);
            for(int i = 0; i < mapPlays.get(key).size(); i++){
                if(i >= 2) break;
                result.add(mapPlays.get(key).get(i)[0]);
            }
        }

        // 메서드 몰라서 반복문 써서 
        answer = new int[result.size()];
        for(int i = 0; i < result.size(); i++){
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}