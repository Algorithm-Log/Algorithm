import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        int len = gems.length;

        Set<String> jew = new HashSet<>(Arrays.asList(gems));
        int size = jew.size();

        HashMap<String,Integer> bag = new HashMap<>();
        int st = 0;
        int min = Integer.MAX_VALUE;

        for(int end = 0; end < len; end++){
            //보석 하나씩 담고, 이미 있으면 value +1, 없으면 걍 + 1
            bag.put(gems[end], bag.getOrDefault(gems[end], 0) + 1);

            while(size == bag.size()){ //전체 보석 개수랑 내 가방에 있는 보석 개수랑 같은가
                if((end - st) < min){ //제일 짧은 길이를 알아야하니까
                    min = (end - st);
                    answer[0] = st+1;   //일단 담아
                    answer[1] = end + 1;
                }
                bag.put(gems[st],bag.get(gems[st]) - 1); //st를 +1 해야하니까 제일 왼쪽에 있던 보석 개수 삭제
                if(bag.get(gems[st]) == 0){ //해당 보석이 0개면 삭제
                    bag.remove(gems[st]);
                }
                st++; // 오른쪽으로 이동
            }
        }

        return answer;
    }
}