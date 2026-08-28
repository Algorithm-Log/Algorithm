import java.util.Arrays;

class Solution {
    int[] lands;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        // 각 노드의 부모 노드 저장
        lands = new int[n+1];
        for (int i = 0; i < lands.length; i++) {
            // 본인이 부모 노드가 되도록 초기화
            lands[i] = i;
        }
        
        // costs를 cost 기준으로 정렬
        Arrays.sort(costs, (a1, b1) -> a1[2] - b1[2]);
        
        // 2. 메인 로직에서의 Union-Find 적용
        for (int i = 0; i < costs.length; i++) {
            // 1. 각 시작노드와 끝 노드를 뽑는다.
            int start = costs[i][0];
            int end = costs[i][1];
            
            // 각 노드의 부모 노드가 동일한지 확인
            // 2-1. 부모 노드가 동일하다면 skip
            int rootStart = find(start);
            int rootEnd = find(end);
            if (rootStart == rootEnd) continue;
            // 2-2. 부모 노드가 다르다면 두 그룹을 하나로 묶는다.
            else {
                lands[rootEnd] = rootStart;
                
                answer += costs[i][2];
            }
        }
        
        return answer;
    }
    
    // 별도의 find 메서드를 만들어 '최종 대표'를 찾는 로직을 구현
    public int find(int land) {
        // 지금 섬이 최종 끝인지 확인
        if (lands[land] == land) {
            return land;
        }
        
        // 내가 최종 노드가 아니라면, 내 부모의 부모를 찾음
        return find(lands[land]);
    }
}