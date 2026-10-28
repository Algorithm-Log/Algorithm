
class Solution {
    public String solution(String number, int k) {
        String answer = "";
        
        // 만들어야하는 문자열 길이
        int length = number.length() - k;
        
        // 그리디
        int next = 0; // 다음 탐색 위치

        while (k > 0 && length > 0) {
            int max = 0;
            int maxIdx = next; // 이번에 선택한 최대 숫자의 인덱스
            
            // 마지막으로 선택 가능한 인덱스까지 탐색
            for (int i = next; i <= number.length() - length; i++) { // length가 0일수 없기에 인덱스에러가 안터진다.
                int current = number.charAt(i)-'0';
                if (current > max) {
                    max = current;
                    maxIdx = i;
                }
            }
            
            answer += max;
            
            // 제일 큰 숫자 앞에 있는 제거해야하는 숫자들 개수
            int skipCnt = maxIdx - next;
            k -= skipCnt; // 제거해야하는만큼 k 개수 감소
            
            length--; // 만들어야하는 문자열 길이가 줄어듦
            
            // 지금 찾은 제일 큰 숫자 다음부터 탐색하도록
            next = maxIdx + 1;
        }
        
        // 더 이상 숫자를 제거할 필요가 없다면 나머지 추가
        if (length > 0) {
            String remain = number.substring(next, number.length());
            answer += remain;
        }
        
        return answer;
    }
}