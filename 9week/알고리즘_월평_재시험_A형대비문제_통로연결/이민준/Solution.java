import java.io.*;
import java.util.*;

public class Solution {
	static int R,C;
	static char map[][];
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	private static Deque<int[]> que;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C]; // 지도
		que = new ArrayDeque<>(); // 흐름을 따라갈 자료구조 que
		
		int rM = 0; int cM = 0; // M의 시작점
		
		// 입력 및 M 위치 찾기
		for (int r = 0; r < R; r++) {
			char[] cLine = br.readLine().toCharArray();
			for(int c = 0; c < C; c++) {
				map[r][c] = cLine[c];
				if(map[r][c] == 'M') {
					rM = r; cM = c;
				}
			}
		}
		
		// 시작점
		// M에서 4방 확인하여 이어갈 값 어디있는지 확인
		for (int d = 0; d < 4; d++) {
			int nr = rM + dr[d];
			int nc = cM + dc[d];
			
			if(!check(nr, nc)) continue;
			// M과 Z가 붙어있는 경우가 있을 수 있기에 Z도 확인 
			if(map[nr][nc] == '.' || map[nr][nc] == 'Z') continue;
			
			// 큐 삽입(현재 r, 현재 c, 이전 r, 이전 c)
			que.offer(new int[] {nr, nc, rM, cM});
			break;
		}
		
		// 빠져있는 곳을 넣은 변수들
		int resultR = 0; int resultC = 0; char resultValue = 'a';
		while(!que.isEmpty()) {
			int[] arr = que.poll();
			int r = arr[0];
			int c = arr[1];
			int beforeR = arr[2];
			int beforeC = arr[3];
			
			// 문제에서 무조건 유일한 길, 성공하는 길이라는 조건이 있기에 바로 빈칸이 있는지 확인.
			// 방향은 que 삽입할 때 확인해서 바로 빈칸 확인함.
			if(map[r][c] == '.') {
				resultR = r;
				resultC = c;
				
				// 아래서부터는 어떤 블록을 정해야하는지 찾기 위한 코드
				
				// 현재 이 .라는 위치에서 위쪽, 아래쪽, 왼쪽, 오른쪽에 어떤 값이 있는지 확인 후 어떤 블록을 넣을지 판단
				boolean[] wasd = new boolean[4];
				for (int d = 0; d < 4; d++) { // 위쪽, 아래쪽, 왼쪽, 오른쪽 순
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					// 배열 나가는지 확인
					if(!check(nr, nc)) continue;
					
					// 방향(위쪽, 아래쪽, 왼쪽, 오른쪽)마다 확인해야 하는 조건이 다르기에 방향별 조건문 진행
					if(d == 0 && (map[nr][nc] == '1' || map[nr][nc] == '4' || map[nr][nc] == '|' || map[nr][nc] == '+')) {
						wasd[d] = true;
					}else if(d == 1 && (map[nr][nc] == '2' || map[nr][nc] == '3' || map[nr][nc] == '|' || map[nr][nc] == '+')) {
						wasd[d] = true;
					}else if(d == 2 && (map[nr][nc] == '1' || map[nr][nc] == '2' || map[nr][nc] == '-' || map[nr][nc] == '+')) {
						wasd[d] = true;
					}else if(d == 3 && (map[nr][nc] == '3' || map[nr][nc] == '4' || map[nr][nc] == '-' || map[nr][nc] == '+')) {
						wasd[d] = true;
					}
				}
				
				// 4방면 다 무언가 존재한다면 +, 나머지는 조건별로 충족한다면 특정 블록 진행
				if(wasd[0]&&wasd[1]&&wasd[2]&&wasd[3]) resultValue = '+';
				else if(wasd[0]&&wasd[1]) resultValue = '|';
				else if(wasd[0]&&wasd[2]) resultValue = '3';
				else if(wasd[0]&&wasd[3]) resultValue = '2';
				else if(wasd[1]&&wasd[2]) resultValue = '4';
				else if(wasd[1]&&wasd[3]) resultValue = '1';
				else if(wasd[2]&&wasd[3]) resultValue = '-';
				
				break;
			}
			
			// 빈칸이 아니라면 흐름따라 다음 칸으로 이동 진행
			offerQue(r, c, beforeR, beforeC, map[r][c]);
			
		}
		// 1부터 시작해서 더하고 답 출력
		resultR++; resultC++;
		System.out.println(resultR + " " + resultC + " " + resultValue);
	}

	// 현재 위치(r,c), 이전 위치(bR, bC), 현재 위치의 블록값
	private static void offerQue(int r, int c, int beforeR, int beforeC, char value) {
		// 아래와 같이 조건을 확인하기 위해 이전 위치의 값이 필요함
		switch (value) {
		case '1': // r. 아래와 오른쪽
			if(r == beforeR) { // r모양일 때 행이 같다면 아래 방향이라는 뜻
				// 아래쪽을 큐에 삽입
				que.offer(new int[] {r+1, c, r, c});
			}else if(c == beforeC){
				// 오른쪽을 큐에 삽입
				que.offer(new int[] {r, c+1, r, c});
			}
			
			break;
		case '2': // ㄴ. 위와 오른쪽
			if(r == beforeR) { // ㄴ모양일 때 행이 같다면 위 방향이라는 뜻
				// 위쪽을 큐에 삽입
				que.offer(new int[] {r-1, c, r, c});
			}else if(c == beforeC){
				// 오른쪽을 큐에 삽입
				que.offer(new int[] {r, c+1, r, c});
			}
			
			break;
		case '3': // J. 위와 왼쪽
			if(r == beforeR) { // j모양일 때 행이 같다면 위 방향이라는 뜻
				// 위쪽을 큐에 삽입
				que.offer(new int[] {r-1, c, r, c});
			}else if(c == beforeC){
				// 왼쪽을 큐에 삽입
				que.offer(new int[] {r, c-1, r, c});
			}
			
			break;
		case '4': // ㄱ. 아래와 왼쪽
			if(r == beforeR) { // ㄱ모양일 때 행이 같다면 아래 방향이라는 뜻
				// 아래쪽을 큐에 삽입
				que.offer(new int[] {r+1, c, r, c});
			}else if(c == beforeC){
				// 왼쪽을 큐에 삽입
				que.offer(new int[] {r, c-1, r, c});
			}
			
			break;
		case '-': // 왼쪽과 오른쪽
			if(c < beforeC) { // -모양일 때 열이 작다면 왼쪽 방향이라는 뜻
				// 왼쪽을 큐에 삽입
				que.offer(new int[] {r, c-1, r, c});
			}else {
				// 오른쪽을 큐에 삽입
				que.offer(new int[] {r, c+1, r, c});
			}
			
			break;
		case '|': // 위와 아래
			if(r < beforeR) { // |모양일 때 행이 작다면 위쪽 방향이라는 뜻
				// 위쪽을 큐에 삽입
				que.offer(new int[] {r-1, c, r, c});
			}else {
				// 아래쪽을 큐에 삽입
				que.offer(new int[] {r+1, c, r, c});
			}

			break;
		case '+': // 4방
			
			// 원래 가던 방향의 직진으로 진행(무조건 성공하는 길이기때문에 직진)
			if(r == beforeR) { // 같은 행이라면 가로로 움직이고 있다는 뜻.
				if(c < beforeC) { // 현재 위치가 왼쪽이라면 왼쪽방향으로 큐 삽입
					que.offer(new int[] {r, c-1, r, c});
				}else { // 아니면 오른쪽 방향
					que.offer(new int[] {r, c+1, r, c});
				}
			}else if(c == beforeC) {
				if(r < beforeR) { // 현재 위치가 위쪽이라면 위쪽방향으로 큐 삽입
					que.offer(new int[] {r-1, c, r, c});
				}else { // 아니면 아래쪽 방향
					que.offer(new int[] {r+1, c, r, c});
				}
			}
			break;

		default:
			break;
		}
	}

	// 배열 범위 체크
	public static boolean check(int r, int c) {
		if(r < 0 || r >= R || c < 0 || c >= C) return false;
		return true;
	}
}
