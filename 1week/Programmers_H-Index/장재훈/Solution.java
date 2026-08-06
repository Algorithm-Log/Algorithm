import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int n = citations.length;
        int answer = 0; // 최댓값은 n (논문 개수가 h 편이 되어야하기 때문!)
        Arrays.sort(citations); // h번 이하 인용된 것을 별도로 카운트하지 않기 위해 sort

        // (h번 이상 인용된 논문 개수)
        for (int i = 0; i < n; i++) {
            int h = citations[i];
            int papers = n - i; // h번 이상 인용된 논문 카운트
            // h번 이상 인용된 논문이 h편 이상인지 확인!
            if (h >= papers) {
                answer = papers;
                break;
            }
        }

        return answer;
    }
}
