import java.util.*;
import java.io.*;

class Solution {
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            char[][] arr = new char[h][w];

            int now_x = 0;
            int now_y = 0;
            char d = ' ';

            int now_bullet_x = 0;
            int now_bullet_y = 0;

            for (int i = 0; i < h; i++) {
                String str = br.readLine();

                for (int j = 0; j < w; j++) {
                    arr[i][j] = str.charAt(j);
                    if (arr[i][j] == '<'|| arr[i][j] == '>'|| arr[i][j] == '^' || arr[i][j] == 'v') {
                        d = arr[i][j];
                        now_x = i;
                        now_y = j;
                    }
                }
            }

            int l = Integer.parseInt(br.readLine());
            String cmd = br.readLine();

            for (int i = 0; i < l; i++) {
                char command = cmd.charAt(i);
                if (command == 'U') {
                    d = '^';
                    arr[now_x][now_y] = d;
                    if (now_x - 1 >= 0 && arr[now_x - 1][now_y] == '.') {
                        arr[now_x][now_y] = '.';
                        now_x--;
                        arr[now_x][now_y] = d;
                    }

                } else if (command == 'D') {
                    d = 'v';
                    arr[now_x][now_y] = d;
                    if (now_x + 1 < h && arr[now_x + 1][now_y] == '.') {
                        arr[now_x][now_y] = '.';
                        now_x++;
                        arr[now_x][now_y] = d;
                    }

                } else if (command == 'R') {
                    d = '>';
                    arr[now_x][now_y] = d;
                    if (now_y + 1 < w && arr[now_x][now_y + 1] == '.') {
                        arr[now_x][now_y] = '.';
                        now_y++;
                        arr[now_x][now_y] = d;
                    }

                } else if (command == 'L') {
                    d = '<';
                    arr[now_x][now_y] = d;

                    if (now_y - 1 >= 0 && arr[now_x][now_y - 1] == '.') {
                        arr[now_x][now_y] = '.';
                        now_y--;
                        arr[now_x][now_y] = d;
                    }

                } else if (command == 'S') {

// 현재 전차 방향 확인
// → 포탄을 한 칸씩 직진
// → * 만나면 .으로 변경 후 종료
// → # 만나면 그냥 종료
// → 맵 밖으로 나가면 종료
                  if(d == '<'){
                    now_bullet_x = now_x;
                    now_bullet_y = now_y - 1;
                    for(int j = now_bullet_y; j >=0; j--){
                        if(arr[now_bullet_x][j] == '#'){
                            break;
                        }
                        else if(arr[now_bullet_x][j] == '*'){
                            //arr에서 해당 벽을 . 으로 변경.
                            arr[now_bullet_x][j] = '.';
                            break; // 포탄은 소멸되니까
                        }
                    }
                  }
                  else if(d == '>'){
                    now_bullet_x = now_x;
                    now_bullet_y = now_y + 1;
                    for(int j = now_bullet_y; j < w; j++){
                        if(arr[now_bullet_x][j] == '#'){
                            break;
                        }
                        else if(arr[now_bullet_x][j] == '*'){
                            //arr에서 해당 벽을 . 으로 변경.
                            arr[now_bullet_x][j] = '.';
                            break; // 포탄은 소멸되니까
                        }
                    }
                  }
                  else if(d == '^'){
                    now_bullet_x = now_x - 1;
                    now_bullet_y = now_y;
                    for(int j = now_bullet_x; j >=0; j--){
                        if(arr[j][now_bullet_y] == '#'){
                            break;
                        }
                        else if(arr[j][now_bullet_y] == '*'){
                            //arr에서 해당 벽을 . 으로 변경.
                            arr[j][now_bullet_y] = '.';
                            break; // 포탄은 소멸되니까
                        }
                    }
                  }
                  else if(d == 'v'){
                    now_bullet_x = now_x + 1;
                    now_bullet_y = now_y;
                    for(int j = now_bullet_x; j < h; j++){
                        if(arr[j][now_bullet_y] == '#'){
                            break;
                        }
                        else if(arr[j][now_bullet_y] == '*'){
                            //arr에서 해당 벽을 . 으로 변경.
                            arr[j][now_bullet_y] = '.';
                            break; // 포탄은 소멸되니까
                        }
                    }
                  }
                }
            }

            System.out.print("#" + test_case + " ");

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    System.out.print(arr[i][j]);
                }
                System.out.println();
            }
        }
    }
}