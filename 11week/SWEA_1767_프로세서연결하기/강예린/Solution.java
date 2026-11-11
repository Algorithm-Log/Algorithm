import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    private static int[] dx= {0,0,-1,1};
    private static int[] dy= {1,-1,0,0};
    private static List<int[]> processors;
    private static int[][] map; // 1은 프로세서 2는 전선 0은 비어있음
    private static int max;
    private static int answer;


    // 순서가 상관 없음 그냥 각 코어들의 5가지 경우에 따라 어떻게 달라지는지만 보면 됨
    private static void powerCore(int idx, int cnt, int len){
        if (idx == processors.size()){
            if(max< cnt) {
                answer= len;
                max= cnt;
            }
            else if (max== cnt) answer= Math.min(answer, len);
            return;
        }

        // 아예 설치 안하는 경우
        powerCore(idx+1, cnt, len);

        int x= processors.get(idx)[0];
        int y= processors.get(idx)[1];

        for (int i = 0; i < 4; i++) {
            int newlen= connect(x,y,i);
            // 설치 가능하다면 다음 프로세서 보기
            if (newlen!= -1) {
                powerCore(idx+1, cnt+1, len+ newlen);
                disconnect(x,y,i, newlen);
            }
        }
    }

    private static int connect(int x, int y, int dir){
        int nx= x+ dx[dir];
        int ny= y+ dy[dir];

        int len= 0;
        while (nx>=0 && ny>=0 && nx< map.length && ny< map.length) {
            if (map[nx][ny]==1 || map[nx][ny]==2) {
                disconnect(x,y,dir,len);
                return -1;
            }

            map[nx][ny]=2;
            len ++;
            nx+= dx[dir];
            ny+= dy[dir];
        }

        return len;
    }

    // 연결한 전선 없애기
    private static void disconnect(int x, int y, int dir, int len){
        x+= dx[dir];
        y+= dy[dir];

        for (int i = 0; i < len; i++) {
            map[x][y]= 0;
            x+= dx[dir];
            y+= dy[dir];
        }


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T= Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb= new StringBuilder();


        for(int test_case = 1; test_case <= T; test_case++) {
            int N= Integer.parseInt(br.readLine());
            map= new int[N][N];
            max=Integer.MIN_VALUE;
            answer=0;

            // 연결 필요한 프로세서들의 위치를 담은 리스트
            processors= new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st= new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int now= Integer.parseInt(st.nextToken());
                    map[i][j]= now;

                    // 가장자리이면 이미 연결된거임
                    if(now ==1 && ! (i==0 || i== N-1 || j==0 || j== N-1)){
                        processors.add(new int [] {i,j});
                    }
                }
            }

            powerCore(0,0,0);

            sb.append("#").append(test_case).append(" ").append(answer).append("\n");

        } // end of testcases

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    } // end of main
}// end of class
