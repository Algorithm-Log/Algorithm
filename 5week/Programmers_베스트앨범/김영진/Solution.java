import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String,Integer> map = new HashMap<>();
        Map<String, List<int[]>> songs = new HashMap<>();
        
        for(int i = 0; i < genres.length; i++){
            String genre = genres[i];
            map.put(genre, map.getOrDefault(genre, 0) + plays[i]);
            songs.putIfAbsent(genre, new ArrayList<>());
            songs.get(genre).add(new int[]{i, plays[i]});
        }
        List<String> genre = new ArrayList<>(map.keySet());
        genre.sort((a,b) -> map.get(b) - map.get(a));
        
        int size = 0;
        
        for(String g : genre){
            size += Math.min(2, songs.get(g).size());
        }
        int[] answer = new int[size];
        int idx = 0;
        
        for(String g : genre){
            List<int[]>list = songs.get(g);
            list.sort((a,b) -> {
                if(a[1] == b[1]){
                    return a[0] - b[0];
                }
                return b[1] - a[1];
            });
            for(int i = 0; i < Math.min(2, list.size()); i++){
                answer[idx++] = list.get(i)[0];
            }
        }
        return answer;
    }
}