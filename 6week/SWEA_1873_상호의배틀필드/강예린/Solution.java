import java.io.*;
import java.util.*;

class Solution
{

    public static void main(String args[]) throws Exception
    {

        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));

        int[] dx= {0, 0, -1, 1}; // 오왼위아래
        int[] dy= {1, -1, 0, 0};

        int T= Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb= new StringBuilder();

        for(int test_case = 1; test_case <= T; test_case++) {
            st= new StringTokenizer(br.readLine());
            int H= Integer.parseInt(st.nextToken());
            int W= Integer.parseInt(st.nextToken());

            int x=0, y=0, dir= 0;
            String s;
            char now = '<';

            // 맵 저장하면서 처음 위치 잡기
            char[][] map= new char[H][W];
            for (int i = 0; i < H; i++) {
                s= br.readLine();
                for (int j = 0; j < W; j++) {
                    map[i][j]= s.charAt(j);

                    if (map[i][j]== '>'){
                        dir= 0;
                        x= i;
                        y= j;
                        now= map[i][j];
                    }

                    if (map[i][j]== '<'){
                        dir= 1;
                        x= i;
                        y= j;
                        now= map[i][j];
                    }

                    if (map[i][j]== '^'){
                        dir= 2;
                        x= i;
                        y= j;
                        now= map[i][j];
                    }
                    if (map[i][j]== 'v'){
                        dir= 3;
                        x= i;
                        y= j;
                        now= map[i][j];
                    }
                }
            }
            int M= Integer.parseInt(br.readLine());
            s= br.readLine();

            for (int i = 0; i < M; i++) {
                char command= s.charAt(i);

                // 포탄 쏘는거
                if(command== 'S') {
                    int nx= x, ny= y;
                    while(true){
                        nx += dx[dir];
                        ny += dy[dir];

                        // 포탄이 게임맵을 벗어나는지
                        if (nx< 0 || ny< 0 || nx>= H || ny>= W) break;

                        // 벽돌벽이면 부수고 평지로
                        if(map[nx][ny]== '*'){
                            map[nx][ny]= '.';
                            break;
                        }

                        // 강철벽이면 스탑만
                        if(map[nx][ny]=='#') break;
                    }

                    continue;
                }

                // 이제 전차 움직이기
                // 방향도 바꾸고 움직였다면 움직이고 난 후 잔차도 바꿔줘야함 
                if(command=='R') {
                    dir= 0;
                    now= '>';
                }

                if(command=='L') {
                    dir= 1;
                    now= '<';
                }

                if(command=='U') {
                    dir= 2;
                    now= '^';
                }

                if(command=='D') {
                    dir= 3;
                    now= 'v';
                }

                // 미리 움직여봄
                int nx= x+ dx[dir];
                int ny= y+ dy[dir];

                // 전차가 한칸 움직여도 게임필드 안에 있는지
                if (nx< 0 || ny< 0 || nx>= H || ny>= W) {
                    map[x][y]= now;
                    continue;
                }

                // 다음이 평지일 때만 움직임
                if(map[nx][ny]=='.') {
                    map[x][y]= '.';
                    x= nx;
                    y= ny;
                    map[x][y]= now;
                } 
                // 움직이지 못했더라도 잔차의 방향을 바꿔줘야함
                else map[x][y]= now;

            }

            sb.append("#").append(test_case).append(" ");

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }

        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}