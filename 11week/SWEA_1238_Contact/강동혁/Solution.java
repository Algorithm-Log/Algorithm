import java.io.*;
import java.util.*;



// 입력이 from to 로 들어오닌까 이걸 인접 리스트로 만들어서 
// bfs 탐색을 보내면서 레벨단위 탐색에서 가장 큰 숫자로 결과값을 변경해주기
public class Solution {
    static List<Integer>[] graph ;
    static boolean[] visited;
    static int res;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T=10;

        for(int test_case=1;test_case<T+1;test_case++){

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n=Integer.parseInt(st.nextToken());
            int start=Integer.parseInt(st.nextToken());
            graph = new ArrayList[101];
            st = new StringTokenizer(br.readLine());

            for(int i=0;i<=100;i++){
                graph[i]=new ArrayList<>();
            }

            for(int i=0;i<n/2;i++){
                
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                
                graph[from].add(to);
                
            }
            visited=new boolean[101];
            res=0;
            bfs(start);

            sb.append("#"+test_case+" "+res+"\n");

        }
        System.out.println(sb);
    } 

    static void bfs(int start){
        Deque<Integer> que = new ArrayDeque<>();
        que.offer(start);
        visited[start]=true;
        while(!que.isEmpty()){
            int size = que.size();
            int max=0;
            for(int i=0;i<size;i++){
                int from = que.poll();

                for(int j=0;j<graph[from].size();j++){
                    if(visited[graph[from].get(j)]) continue;

                    visited[graph[from].get(j)]=true;
                    que.offer(graph[from].get(j));
                    max = Math.max(graph[from].get(j),max);
                }
            }
            res=max;
            
        }
    }

} 