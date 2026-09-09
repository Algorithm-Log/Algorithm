import java.util.*;
class Solution {
	
	static class Music implements Comparable<Music> {
        int idx;
        int play;
        
        Music(int idx, int play){
            this.idx= idx;
            this.play= play;
        }
        
        public int compareTo(Music o){
            if(this.play== o.play) return this.idx- o.idx;
            return o.play- this.play;
        }
    }
	
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genreOrder= new HashMap<>();
        Map<String, List<Music>> musicMap= new HashMap<>();
        
        for(int i=0; i< genres.length; i++){
            genreOrder.put(genres[i], genreOrder.getOrDefault(genres[i], 0)+plays[i]);
            musicMap.put(genres[i], musicMap.getOrDefault(genres[i], new ArrayList<>()));
            musicMap.get(genres[i]).add(new Music(i, plays[i]));
        }
        
        List<String> keySet= new ArrayList<>(genreOrder.keySet());
        Collections.sort(keySet, (k1, k2)-> genreOrder.get(k2)- genreOrder.get(k1));
        
        List<Integer> answer= new ArrayList<>();
        
        for(String key: keySet) {
        	int cnt=0;
        	List<Music> tmp= musicMap.get(key);
        	Collections.sort(tmp);
        	
        	for (int i = 0; i < tmp.size(); i++) {
				if(i > 1) break;
				answer.add(tmp.get(i).idx);
			}
        	
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}