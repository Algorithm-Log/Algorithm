import java.util.*;
import java.io.*;
    
class Solution {
    static int[][] map,dist;
    static int[] dx={0,0,-1,1}; //상 하 좌 우
    static int[] dy={-1,1,0,0}; 
    static int n;
    static StringBuilder sb  = new StringBuilder();
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for(int test_case=1;test_case<T+1;test_case++){
            n = Integer.parseInt(br.readLine());

            map=new int[n][n];
            dist = new int[n][n];

            for (int i = 0; i < n; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);//dist 배열을 최대값으로 초기화
            }

            for(int col=0;col<n;col++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int row=0;row<n;row++){
                    map[col][row]=Integer.parseInt(st.nextToken());
                }
            }

            bfs();
            sb.append("#"+test_case+" "+dist[n-1][n-1]+"\n");
        }
        System.out.println(sb);
    }

    static void bfs(){

        Queue<int[]> que = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2])); // 가중치를 기준으로 오름차순 정렬
        
        //시작 좌표를 큐에 넣어주고 dist 배열의 시작좌표 값을 0으로 초기화
        que.offer(new int[]{0,0,0});
        dist[0][0]=0;
        
        while(!que.isEmpty()){
            int[] temp = que.poll();

            int x = temp[0];
            int y = temp[1];
            int weight = temp[2];

            if(weight > dist[y][x])continue; // 큐에서 꺼낸 경로보다 더 짧은 경로가 이미 발견되었다면 탐색 생략

            for(int dir=0;dir<4;dir++){
                int nx = x+dx[dir];
                int ny = y+dy[dir];

                if(!isRange(nx, ny)) continue; //2차원 배열 범위내부인지

                int diff = 0;
                if(map[y][x]>map[ny][nx]){
                    //다음으로 가는곳이 작을때
                    
                }else if(map[y][x]<map[ny][nx]){
                    //다음으로 가는곳이 클때
                    diff =2*(map[ny][nx]-map[y][x]);
                }else{
                    //다음으로 가는곳이 같을때
                    diff=1;
                }

                if(weight+diff < dist[ny][nx]){// 현재 좌표까지의 최단 거리 + 다음 좌표로 이동하는 비용이
                                                // 기존에 발견한 다음 좌표의 최소 비용보다 작다면 갱신

                    dist[ny][nx] = weight+diff; //dist 갱신
                    que.offer(new int[]{nx,ny,weight+diff});

                }
            }
        }
    }

    static boolean isRange(int nx,int ny){
        if(nx>=0 && nx<n && ny>=0 && ny<n) return true;
        return false;
    }
    
}
