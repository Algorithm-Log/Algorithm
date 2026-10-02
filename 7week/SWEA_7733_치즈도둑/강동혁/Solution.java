import java.util.*;
import java.io.*;

// 23:48 ~ 24:36 

// 2차원 배열 가중치인가?
// 음 날짜가 지날때마다 2차원 배열 맵이변경되고
// 변경된 값에 따라 탐색이 가능한지 아닌지가 변경이되네
// 그럼 1일차 덩어리 2일차 덩어리 이렇게
// 100일 차까지 덩어리 갯수를 구해서 최대값을 구하면될듯
// 덩어리를 구하는건 2차원 배열에서 모든 좌표를 순회 하면서
// 갈수있는곳이고 방문하지 않은곳이면 bfs 탐색을 시작 bfs 탐색을 마치면
// 덩어리수 한개 플러스 bfs 탐색을 하면서 que에서 값을 꺼낼때
// 방문처리해주기 그렇게 2차원 배열의 우측하단 까지 탐색을 마치면
// 날짜를 더하고 다시 처음부터 탐색 visited 초기화 해주어야함
// 이정도면 큰 로직은 다된거같고
// 큐에는 int[]{x,y} 넣어주고
// 갈수있는지 검사하는 함수를 하나 만들어서 nx,ny 를 매개변수로 주고
// 그 좌표가 현재 날짜보다 큰값인지 검사
// 함수까지 만들어야하나 일단 만들자 또 더있나
// 
class Solution {
    static int[][] map;
    static int n;
    static StringBuilder sb =new StringBuilder();
    static int[] dx={0,0,-1,1};
    static int[] dy={-1,1,0,0};
    public static void main(String[] args)throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T=Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int test_case=1;test_case<T+1;test_case++) {
            n=Integer.parseInt(br.readLine());
            map=new int[n][n];
            for(int row=0;row<n;row++){
                st = new StringTokenizer(br.readLine());
                for(int col=0;col<n;col++){
                    map[row][col]=Integer.parseInt(st.nextToken());
                }
            }

            boolean[][] visited=new boolean[n][n];
            int maxCount=0;
            
            Deque<int[]> que = new ArrayDeque<>();
            for(int day=0;day<=100;day++){
                int count=0;
                for(int y=0;y<n;y++){
                    for(int x=0;x<n;x++){
                        // 날짜보다 큰 치즈인지 방문한곳인지
                        if(map[y][x]>day && !visited[y][x]){
                            visited[y][x]=true;
                            que.offer(new int[]{x,y});

                            while(!que.isEmpty()){
                                int[] temp=que.poll();

                                int xx=temp[0];
                                int yy=temp[1];

                                for(int dir=0;dir<4;dir++){
                                    int nx = xx+dx[dir];
                                    int ny = yy+dy[dir];

                                    //범위 안 검사
                                    //날짜보다 큰 치즈 인지 검사
                                    if(isRangeAndBigCheeze(nx,ny,day) && !visited[ny][nx]){
                                        visited[ny][nx]=true;
                                        que.offer(new int[]{nx,ny});
                                    }
                                }
                            }
                            count++;
                        }
                    }
                }
                maxCount=Math.max(maxCount,count);
                visited=new boolean[n][n];

            }

            sb.append("#"+test_case+" "+maxCount+"\n");


        }
            
        System.out.print(sb);
            
    }

    static boolean isRangeAndBigCheeze(int nx ,int ny,int day){

        if(nx>=0 && nx<n && ny>=0 && ny<n && map[ny][nx]>day)return true;
        return false;

    }

    

    

}
