import java.util.*;

class Solution {
    public int solution(int N, int number) {
        if(N == number) return 1; // 먼저 N과 number가 같은지 확인
        
        Set<Integer>[] set = new HashSet[9]; // 중복값 제거 위해 set 사용
        for(int i = 0; i < 9; i++){
            set[i] = new HashSet<>();
        }
        
        int[] dp = new int[9];
        set[1].add(N); // 0은 무시, 1은 항상 N만 있어서 미리 넣음
        
        StringBuilder sb;
        for(int i = 2; i < 9; i++){ // 2~8까지 진행
            sb = new StringBuilder();
            for(int j = 0; j < i; j++){ // N이 각 k번 쓰여질 때 값 처리(ex. N이 5이고, k=2일때 55, k=3일때 555)
                sb.append(N);
            }
            int fNum = Integer.parseInt(sb.toString());
            set[i].add(fNum);
            if(fNum == number) return i;
            
            // 각 배열에 들어갈 수 있는 값을 넣기
            /*
            idx가 3일 땐 [1]과 [2]의 요소들을 각각 곱
            idx가 4일 땐 [1][3], [2][2], [3][1]의 요소들을 각각 곱
            */
            for(int j = 1; j < i; j++){ // 
                for(int num1 : set[j]){
                    for(int num2 : set[i-j]){ // 숫자를 set에 넣으면서 number와 비교 후 같다면 지금의 N사용 개수(i) 리턴
                        int pNum = num1 + num2;
                        int sNum = num1 - num2;
                        int mNum = num1 * num2;
                        if(pNum == number || sNum == number || mNum == number) return i;
                        set[i].add(pNum);
                        set[i].add(sNum);
                        set[i].add(mNum);
                        if(num2 == 0) continue;
                        int dNum = num1 / num2;
                        if(dNum == number) return i;
                        set[i].add(dNum);
                    }
                }
            }
        }
        
        return -1; // 8 초과시 자동 -1
    }
}