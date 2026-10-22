import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        // 오름차순 정렬
        Arrays.sort(people);
        
        // 양 끝 설정
        int left = 0;
        int right = people.length-1;
        
        // left와 right가 만날 때까지 반복
        while(left <= right){
            // 양 끝 더함
            int sum = people[left] + people[right];
            
            // 더한 값을 무게 제한 변수와 비교
            if(sum > limit){ // 더한 값이 크다면 오른쪽만 탑승
                right--;
            }else{           // 작거나 같다면 양쪽 다 탑승
                left++;
                right--;
            }
            
            answer++;        // 더한 값이 크든 작든 보트는 한번 왔다갔다 하기에 1씩 더함.
        }
        
        return answer;
    }
}