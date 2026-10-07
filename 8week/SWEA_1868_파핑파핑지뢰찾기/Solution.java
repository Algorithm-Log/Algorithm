import java.io.*;
import java.util.*;

class Solution
{
	static int[][] lands;
	// 8방 탐색
	static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
	static boolean[][] visited;
	static int N;
	static int clickCount; // 0개 몇개 터트렸는지 카운트
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for(int tc = 1; tc <= T; tc++)
		{
			N = Integer.parseInt(br.readLine());
			lands = new int[N+2][N+2]; // 배열 초기화
			
			// step1 : 배열 초기 환경 세팅
			for (int i = 1; i <= N; i++) {
				String line = br.readLine();
				for (int j = 1; j <= N; j++) {
					if (line.charAt(j-1)=='.') lands[i][j] = 0;
				    else lands[i][j] = -1; // 지뢰
				}
			}
			
			// step2 : 배열에 각 위치별 주변 폭탄 개수 초기화
			countAdjMines();
			
			// step3 : 1,1부터 0인 애들을 기준으로 bfs
			visited = new boolean[N+2][N+2];
			clickCount = 0;

            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    //1,1부터 BFS 체크
                    if (lands[r][c] == 0 && !visited[r][c]) {
                        clickCount++; // 클릭하고 BFS
                        bfs(r, c);
                    }
                }
            }
			
			
            // System.out.println(clickCount);
			// (놓친 점) step4. 0인애들 다 처리하고, 이제 방문하지 않은 지뢰가 아닌애들 방문처리하면서 카운트 1 올림
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
                    // 0인 애를 찾는다.
					if (lands[r][c] > 0 && !visited[r][c]) {
                        clickCount++;
                    }
				} // 열 탐색
			} // 행 탐색
			
			sb.append("#").append(tc).append(" ").append(clickCount).append("\n");
		}
		System.out.println(sb.toString());
	}
	
    static void countAdjMines() {
        //1,1 ~ N,N까지
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                //이때, 해당 위치가 -1이라면 continue;
                if (lands[r][c] == -1) continue;
                //아니라면, 8방 탐색하면서 주변 -1(지뢰) 개수 카운트쳐서 해당 정점에 저장
                for (int i = 0; i < 8; i++) {
                    if (lands[r+dr[i]][c+dc[i]] == -1) lands[r][c]++;
                } // 8방 탐색
            } // 열 탐색
        } // 행 탐색
    }

    // 0인 애들 파고들면서 0인 애랑 주변 애들 visited 처리
	static void bfs(int r, int c) {
		Queue<Node> q = new ArrayDeque<>();
		// 첫 입력은 바로 큐에
        q.offer(new Node(r, c));
        // 방문 처리
        visited[r][c] = true;

        while(!q.isEmpty()) {
            Node current = q.poll();

            //아니라면, 8방 탐색하면서 -1이 아닌 애들 visited 처리하고 큐에 집어넣고 카운트 1 증가
            for (int i = 0; i < 8; i++) {
                int nr = current.row+dr[i];
                int nc = current.col+dc[i];
                // 배열 안의 요소이면서, 지뢰가 아니고, 아직 방문 안한 애들인지 확인
                if (isInRange(nr, nc) && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    // System.out.println(nr + ", " + nc);
                    if (lands[nr][nc] == 0) {
                        q.offer(new Node(nr, nc));
                    }
                }
            } // 8방 탐색
        }
	}
	
    // 범위 안인지 체크 (r, c가 1 이상이고 N이하인지 확인)
    static boolean isInRange(int r, int c) {
        return r >= 1 && r <= N && c >= 1 && c <= N;
    }

	static class Node {
		int row,col;

		public Node(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
}

//테케 개수를 입력받는다.
//N x N 배열을 생성
//배열에 라인을 입력받으면서 초기 상태를 저장
//// static 변수
//int[][] lands = new int[N+2][N+2];
//int[] dr = {-1, -1, -1, 0, 0, 1, 1 ,1} // 0,0은 제외
//int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1}
//
//// step1 : 배열 초기화
//for i = 1~N // 행 뽑기
//    line = br.readLine();
//    for j = 1 ~ N // String순회
//    if (line.charAt(j-1)) lands[i][j] = 0
//    else lands[i][j] = -1 // 지뢰
//
//// step2 : 배열에 각 위치별 주변 폭탄 개수 초기화
//1,1 ~ N,N까지
//이때, 해당 위치가 -1이라면 continue;
//아니라면, 8방 탐색하면서 주변 -1 개수 카운트쳐서 해당 정점에 저장 (for int i = 0; i < 8; i++) lands[r+dr[i]][c+dc[i]]
//
//// step3 : 1,1부터 0인 애들을 기준으로 bfs
//1,1부터 BFS
//0인 애들 파고들면서 0인 애랑 주변 애들 visited 처리하고 카운트 1 올림
//nr, nc가 1 이상이고 N이하인지 확인하면서 진행
//
//// (놓친 점) step4. 0인애들 다 처리하고, 이제 방문하지 않은 지뢰가 아닌애들 방문처리하면서 카운트 1 올림
/// 