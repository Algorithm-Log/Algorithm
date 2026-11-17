import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    private static int[] dr= {0, -1, 0, 1};
    private static int[] dc= {1, 0, -1, 0};

    private static int answer;
    private static int[][] map;
    private static int[][] farm;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T= Integer.parseInt(br.readLine());

        for(int test_case= 1; test_case<= T; test_case++){
            st= new StringTokenizer(br.readLine());
            int N= Integer.parseInt(st.nextToken());
            int limit= Integer.parseInt(st.nextToken());

            map= new int[N][N];
            int[][] tmp= new int[N][N];
            farm= new int[N][N];

            for(int i=0; i<N; i++){
                st= new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++){
                    map[i][j]= Integer.parseInt(st.nextToken());
                    tmp[i][j]= map[i][j];
                }
            }

            answer= Integer.MIN_VALUE;

            for(int i= 1; i< N-1; i++){
                for(int j= 1; j< N-1; j++){
                    for (int dir = 0; dir < 4; dir++) {
                        if (map[i][j] == 1) continue;
                        findFarm(i, j, dir, limit);
                        reset(tmp);
                    }
                }
            }

            sb.append("#").append(test_case).append(" ").append(answer).append("\n");

        }

        bw.write(sb.toString());
        bw.flush();
    }

    private static void reset(int[][] tmp) {
        for (int i = 0; i < map.length; i++) {
            map[i]= tmp[i].clone();
            farm[i] = new int[map.length];
        }
    }

    // 시작 위치와 방향
    private static void findFarm(int r, int c, int dir, int limit) {
        int day= 1;
        int cnt= 0;

        while(day<= limit){
            int tmp= dir;
            dir = canGo(r, c, dir, day);

            // 오전
            if (map[r][c]== 0){

                if(dir != -1) {
                    map[r][c]= day + ++farm[r][c] + 4;
                }

            } else if (map[r][c] <= day){
                map[r][c]= 0;
                cnt ++;
            }

            // 오후 이동
            if(dir != -1) {
                r+= dr[dir];
                c+= dc[dir];
            } else dir= tmp;

            day++;
        }

        answer= Math.max(answer, cnt);
    }

    private static int canGo( int r, int c, int dir, int day) {
        for (int k = 0; k < 4; k++) {
            int nr= r +  dr[(dir+3+k)%4];
            int nc= c + dc[(dir+3+k)%4];

            if (inside(nr, nc) && map[nr][nc] != 1 && (map[nr][nc] == 0 || map[nr][nc] <= day)) return (dir+3+k)%4;
        }

        return -1;
    }

    private static boolean inside(int nr, int nc) {
        return nr >=0 && nc >=0 && nr < map.length && nc < map[0].length;
    }
}