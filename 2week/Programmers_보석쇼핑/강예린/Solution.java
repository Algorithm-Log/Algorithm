import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int left= 0;
        int[] answer= new int [2];
        int min= Integer.MAX_VALUE;

        // 현재 left ~ right까지의 key 개수 관리
        Map<String, Integer> map = new HashMap<>();

        for(String gem: gems) map.put(gem,0);

        int total= map.size();

        map.clear();
        String key;

        for(int right=0; right< gems.length; right++){

            key = gems[right];
            map.put(key, map.getOrDefault(key,0)+1);

            while(map.size()== total) {

                if (min > right - left){ // 길이가 같으면 갱신안함 작을때만 갱신
                    min= right - left;
                    answer[0]= left+1; // 1-based 맞춰주기
                    answer[1]= right+1;
                }

                // left 항목 하나 빼고 left +1
                key= gems[left];
                map.put(key, map.get(key)-1);
                if(map.get(key)==0) map.remove(key);
                left ++;

            }

        }

        return answer;
    }
}