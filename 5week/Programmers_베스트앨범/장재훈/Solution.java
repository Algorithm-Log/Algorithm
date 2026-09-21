import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        // 총 음악 개수
        int len = genres.length;
        Music[] musics = new Music[len];
        // 장르별 노래 개수 저장
        Set<String> set = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        
        // 장르 종류 저장
        for (int i = 0; i < len; i++) {
            set.add(genres[i]);
        }
        // 장르 개수 저장
        for (String genre : set) {
            map.put(genre, 0);
        }
        
        for (int i = 0; i < len; i++) {
            String genre = genres[i]; // 현재 인덱스의 장르
            musics[i] = new Music(genre, plays[i], i);
            map.put(genre, map.get(genre)+plays[i]);
            // map.put(genre, map.getOrDefault(genre, 0)+plays[i]); // Set을 쓰지 않고 해결 가능
        }
        
        // 3가지 정렬 기준에 맞춰서 정렬 (Comparable)
        Arrays.sort(musics, new Comparator<Music>() {
            @Override
            public int compare(Music a, Music b) {
                if (a.genre.equals(b.genre)) {
                    if (a.play == b.play) {
                        return a.idx - b.idx;
                    }
                    return b.play - a.play;
                }
                return map.get(b.genre).compareTo(map.get(a.genre));
            }
        });
        
        List<Integer> answer = new ArrayList<>();
        
        String genre = "";
        // 정렬된 배열을 끝까지 돔
        for (int i = 0, cnt = 0; i < musics.length; i++) {
            // 이전 곡과 같은 장르인 경우
            if (genre.equals(musics[i].genre)) {
                // 이미 2곡을 선택했다면 다음으로 이동
                if (cnt == 2) {
                    continue;
                }
                // 2곡 선택되지 않았다면 해당 앨범의 인덱스 추가
                else {
                    cnt++;
                    answer.add(musics[i].idx);
                }
            }
            // 새로운 장르인 경우
            else {
                cnt = 1;
                genre = musics[i].genre;
                answer.add(musics[i].idx);
            }
        }
        
        int[] arr = new int[answer.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = answer.get(i);
        }
        return arr;
    }
    public static class Music {
        String genre;
        int play;
        int idx;

        public Music(String genre, int play, int idx) {
            this.genre = genre;
            this.play = play;
            this.idx = idx;
        }
    }
}

// 알고리즘 
// 자료구조 : Class와 Array 사용하면 풀릴 것 같다.
// 조건 
// Class를 먼저 정렬한다. 그 후 노래별 재생횟수로 정렬한다.