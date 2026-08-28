import java.util.*;

class Solution {
    int size;
    PriorityQueue<int[]> pq;
    boolean[] visited;
    int[][] costs;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        // int[]에는 다음에 들어갈 노드와 cost가 들어감. cost 오름차순 처리
        pq = new PriorityQueue<>((a,b) -> a[1]-b[1]);
        visited = new boolean[n];
        this.costs = costs; // main 외부 메서드에서도 쓰기 위해
        size = costs.length;
        
        // start 지점 방문 처리
        visited[0] = true;
        // start 지점에서 갈 수 있는 곳 pq에 모두 삽입
        func(0);        
        
        // 모든 노드 방문한 횟수을 확인하기 위한 변수
        int cnt = 0;
        // 모든 노드 방문할 때까지 반복
        while(!pq.isEmpty()){
            int[] arr = pq.poll();
            int next = arr[0];
            int cost = arr[1];
            
            // 다음에 갈 노드가 방문되어있다면 안감
            if(visited[next]) continue;
            
            // 제일 먼저 끝나는 경로가 MST이기에 바로 더함
            answer += cost;
            visited[next] = true;
            cnt++;
            
            // 모든 노드 방문하면 끝
            if(cnt == n - 1) break;
            
            // next에서 갈 수 있는 곳 모두 큐에 삽입
            func(next);
        }
        
        return answer;
    }
    
    // 큐 삽입 로직. 양방향이기에 [0]일 때와 [1]일 때 같이 봄.
    public void func(int next) {
        for(int i = 0; i < size; i++){
            if(costs[i][0] == next){ 
                pq.offer(new int[] {costs[i][1], costs[i][2]});
            }
            if(costs[i][1] == next){
                pq.offer(new int[] {costs[i][0], costs[i][2]});
            }
        }
    }
}