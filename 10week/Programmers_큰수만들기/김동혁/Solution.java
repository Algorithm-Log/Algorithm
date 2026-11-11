import java.util.*;
import java.io.*;

//최댓값 찾아서, 그 인덱스가 그게 최종 자리수 보다 같거나 크면, 일단 앞에걸 다 지우기.
//최댓값은 최종자리수를 일단 뒤에서 빼놓고 남은것들중에서 판단하기.
//이게 맨앞자리 찾기.

class Solution {
    public String solution(String number, int k) {
        String answer = "";

        int len = number.length();

        // 최종적으로 만들어야 하는 숫자의 자릿수
        int targetCnt = number.length() - k;

        // 현재까지 확정한 자릿수
        int currCnt = 0;

        // 현재 자릿수의 최댓값을 탐색할 시작 인덱스
        int startIdx = 0;

        // 뒤에 필요한 자릿수를 확보한 상태에서의 탐색 끝 인덱스
        int endIdx = len - (targetCnt - currCnt);

        // 최종 자릿수를 모두 확정할 때까지 반복
        while(currCnt != targetCnt) {

            // 현재 탐색 범위에서 가장 큰 숫자와 해당 인덱스
            int maxIdx = 0;
            int max = Integer.MIN_VALUE;

            // 선택 가능한 범위에서 최댓값 탐색
            for (int i = startIdx; i <= endIdx; i++) {
                int tempNumber = number.charAt(i) - '0';

                // 더 큰 숫자를 찾으면 최댓값과 인덱스 갱신
                // 같은 숫자라면 먼저 등장한 숫자를 유지
                if(max < tempNumber) {
                    maxIdx = i;
                    max = tempNumber;
                }
            }

            // 찾은 최댓값을 현재 자릿수로 확정
            answer += String.valueOf(max);
            currCnt++;

            // 확정한 숫자 앞부분은 제거된 것으로 간주
            // 다음 자릿수는 확정한 인덱스 바로 뒤부터 탐색
            startIdx = maxIdx+1;

            // 확정한 자릿수가 하나 늘었으므로 탐색 끝 범위도 한 칸 확장
            endIdx++;

        }

        return answer;
    }
}
