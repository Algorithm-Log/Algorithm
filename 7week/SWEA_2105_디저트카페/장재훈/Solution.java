import java.io.*;
import java.util.*;

public class Solution {
    static int N, res; // N: 배열 크기, res : 사각형의 최대크기기
    static int[][] desert;
    // {r+1, c+1 / r+1, c-1 / r-1, c-1 / r-1, c+1}
    static int[] dr = {1, 1, -1, -1};
    static int[] dc = {1, -1, -1, 1};
    static int startR, startC; // 사각형의 첫 시작점
    static boolean[] visited;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            desert = new int[N][N];
            res = -1;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    desert[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    // 4 꼭짓점들은 못돌게 막는다.
                    if ((r == 0 && c == 0) || (r == 0 && c == N-1) || (r == N-1 && c == N-1) || (r == N-1 && c == 0)) continue;
                    startR = r;
                    startC = c;
                    visited = new boolean[101];
                    visited[desert[r][c]] = true;
                    dfs(r, c, 0, 1);
                }
            }

            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }
        System.out.println(sb.toString());
    }

    static void dfs(int r, int c, int dir, int count) {
        int nr = r + dr[dir];
        int nc = c + dc[dir];

        // 기저 조건
        // 시작 row보다 높이 못간다.
        if (nr < startR) {
            return;
        }
        // 방향을 3번 바꿈 + 시작 위치로 돌아왔는가.
        if (dir == 3 && nr == startR && nc == startC) {
            // System.out.println("dir == 3 && nr == startR && nc == startC");
            //최댓값 (res와 count 비교해서 큰걸 res)
            if (res < count) {
                res = count;
            }
            return;
        }
        // 다음 이동 위치가 범위 벗어나면 종료
        if (nc >= N || nr >= N || nc < 0 || nr < 0) {
            return;
        }

        // 이미 먹은 디저트라면 패스
        if (visited[desert[nr][nc]]) {
            return;
        }
        // 순환 파트 -----------
        // visited[desert[nr][nc]] = true;
        
        // 꺾기 // dir이 3이 아닐때만 (3은 오직 직진)
        if (dir != 3) {
            visited[desert[nr][nc]] = true;
            dfs(nr, nc, dir+1, count+1); // 백트래킹
            visited[desert[nr][nc]] = false;
        }
        // 직진 시키기
        visited[desert[nr][nc]] = true;
        dfs(nr, nc, dir, count+1); // 백트래킹
        visited[desert[nr][nc]] = false;
    }
}


/*** 처음에 내가 생각했는데 어렵다..
백트래킹 쓸 수 있다. -> 각 위치별 탐색해야하는게 많이 안된다!

시작하면 끝지점은 무조건 정해진다.

// 사각형이 만들어지는 조건
시작점이 (col + 1 < N && row + 2 < N && col - 1 >= 0) 이 조건을 만족해야한다. 아니면 continue 시킨다.
// 움직이는 방향. (대각선 탐색)
{r+1, c+1 / r+1, c-1 / r-1, c-1 / r-1, c+1}
// 0,2 인덱스로 움직일때의 반복수가 같아야하고, 1,3 인덱스로 움직일때의 반복수가 같아야한다. // 미리 가로와 세로 길이를 정해서 가는 방식에서 채택!

dfs를 돌리면서 가지치기를 시킨다.
조건 1. 다음으로 이동해야하는 위치의 수가 이미 탐색된 숫자일때
조건 2. 시작점으로부터 사각형을 못만드는 위치일 때
    이걸 어떻게 찾게 만들까... 사각형을 방정식으로 표현한다면... 어려울 것 같다...
***/

// // static 변수
// int N // 4~20
// int[][] desert // 1~100

// dfs (int r, int c, int dir, boolean[] visited, int count) // r: 지금 행, c: 지금 열, dir: 방향, count : 지금까지 먹은 디저트 수
// int nr = r+dr[dir]
// int nc = r+dr[dir]

// // 기저조건
// if (nr < startR) return; // (시작 row보다 높이 못간다.)
// if (dir == 3 && nr == startR && nc == startC) // 방향을 3번 바꿈 + 시작 위치로 돌아왔는가.
//     //최댓값 (res와 count 비교해서 큰걸 res)
//     return;



// if (nc >= N || nr >= N || nc < 0 || nr < 0) return; // 다음 이동 위치가 범위 벗어나면 종료
// if (visited[desert[nr][nc]]) return; // 이미 먹은 디저트라면 패스

// // 해당 번호 디저트 먹음 처리
// visited[desert[nr][nc]]] = true;
// // 꺾기 // dir이 3이 아닐때만 (3은 오직 직진)
// if (dir != 3) {
//     dfs (nr, nr, dir+1, cnt+1)
// }
// // 직진 시키기
// dfs (nr, nc, dir, cnt+1)



// 0,1 -> 1,0
// 0,2 -> 2,0

// 20
// 20

// 1 1 1 1 1 
// 1 1 ㅇ 1 1
// 1 ㅇ 1 ㅇ 1
// 1 1 ㅇ 1 ㅇ
// 1 1 1 ㅇ 1



// 1
// 4
// 9 8 9 8
// 4 6 9 4
// 8 7 7 8
// 4 5 3 5