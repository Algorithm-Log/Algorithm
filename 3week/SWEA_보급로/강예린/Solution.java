import java.io.*;
import java.util.PriorityQueue;

public class Solution {

    private static class Road{
        int x;
        int y;
        int newCost;

        public Road(int x, int y, int newCost) {
            this.x = x;
            this.y = y;
            this.newCost = newCost;
        }
    }

    public static void main(String[] args) throws Exception
    {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));

        int T;
        T=Integer.parseInt(br.readLine());
        String[] s;
        for(int test_case = 1; test_case <= T; test_case++)
        {
            int n= Integer.parseInt(br.readLine());
            int[][] roads= new int[n][n];
            int[][] costs= new int[n][n];
            boolean[][] visited= new boolean [n][n];

            for (int i = 0; i <n ; i++) {
                s= br.readLine().split("");
                for (int j = 0; j < n; j++) {
                    roads[i][j]= Integer.parseInt(s[j]);
                    costs[i][j]= Integer.MAX_VALUE;
                }
            }

            costs[0][0]= 0;

            PriorityQueue<Road> queue= new PriorityQueue<>((r1,r2)-> r1.newCost- r2.newCost);

            queue.offer(new Road(0,0,0));

            int[] dx= {0, 0, 1, -1};
            int[] dy= {1, -1, 0, 0};

            while(!queue.isEmpty()){
                Road now= queue.poll();// 현재 최소노드
                visited[now.x][now.y]= true;


                for (int i = 0; i < dx.length; i++) {
                    int nx= now.x + dx[i];
                    int ny= now.y + dy[i];

                    if(nx < 0 || ny <0 || nx>= n || ny >= n || visited[nx][ny]) continue;

                    // 가능한 거리 업데이트
                    int newCost= costs[now.x][now.y]+ roads[nx][ny];
                    costs[nx][ny]= Math.min(newCost, costs[nx][ny]);
                    // 다음으로 제일 거리 작은 노드 보기 위해 큐에 넣음
                    queue.offer(new Road(nx, ny, newCost));


                }

            }

            bw.write("#"+test_case+" "+costs[n-1][n-1]);
            bw.newLine();
        }
        bw.flush();
    }
}
