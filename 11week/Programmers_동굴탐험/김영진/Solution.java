import java.util.*;

class Solution {
    static boolean[] visited,waiting;
    static boolean answer;
    static List<Integer>[] list;
    static int[] pre,after;

    public boolean solution(int n, int[][] path, int[][] order) {
        answer = true;
        visited = new boolean[n];
        list = new ArrayList[n];
                pre = new int[n];
        after = new int[n];
        Arrays.fill(pre,-1); // 각 배열의 노드에 방문전에 방문 해야할 노드를 저장
        Arrays.fill(after,-1);// 내가 방문하고 그 뒤에 방문해야할거
        waiting = new boolean[n];
        
        for(int i = 0; i < n; i++){
            list[i] = new ArrayList<>();
        }
        for(int i = 0; i < path.length; i++){ //무방향이니까 전부 저장
            int a = path[i][0];
            int b = path[i][1];
            list[b].add(a);
            list[a].add(b);
        }
        
        for(int i = 0; i < order.length; i++){
            pre[order[i][1]] = order[i][0];
            after[order[i][0]] = order[i][1];
        }
        if(pre[0] != -1) return false;
        bfs(0);
        
        for(int i = 0; i < n; i++){
            if(!visited[i]) return false;
        }
        
        return answer;
    }
    public static void bfs(int start){
    Queue<Integer> q = new LinkedList<>();
    q.offer(start); // 기준은 더 탐색해야하는거
    visited[start] = true;
    
    while(!q.isEmpty()){
        int now = q.poll();
        
        int target = after[now]; //5,7,1
        if(target != -1 && waiting[target]){ 
    //현재 now 방을 방문해서 target 방의 조건이 풀렸고,
    // target 방이 대기 중이라면 이제 방문할 수 있음
            visited[target] = true;
            q.offer(target);
        }
        
        for(int next : list[now]){
            if(visited[next]){
                continue;
            }
            if(pre[next] != -1 && !visited[pre[next]] ){ //방문해야할 노드가 있는데 그 노드를 아직 방문 하지 않았으면 대기중으로 넘겨.
                waiting[next] = true;// 해당 배열의 방은 대기중이라는 뜻임
                continue;
            }
            visited[next] = true;
            q.offer(next);
        }
    }
}
}
