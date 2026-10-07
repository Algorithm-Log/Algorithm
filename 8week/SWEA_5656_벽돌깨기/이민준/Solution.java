import java.io.*;
import java.util.*;

public class Solution {
	static int n, w, h, min;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // 구슬 개수
			w = Integer.parseInt(st.nextToken()); // 가로 길이
			h = Integer.parseInt(st.nextToken()); // 세로 길이
			char[][] map = new char[h][w];		  // map
			
			// 입력
			for (int i = 0; i < h; i++) {
				map[i] = br.readLine().replace(" ", "").toCharArray();
			}
			
			// 현재 개수를 구한 뒤 구슬보다 개수가 적다면 바로 0 출력
			min = countBricks(map); 
			if(min <= n) {
				sb.append('#').append(tc).append(' ').append(0).append('\n');
				continue;
			}
			
			// 바로 dfs 진입
			dfs(map, 0);
			
			sb.append('#').append(tc).append(' ').append(min).append('\n');
		}
		System.out.print(sb.toString());
	}
	
	// map의 '0'을 제외한 숫자를 세는 함수
	public static int countBricks(char[][] map) {
		int cnt = 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if(map[i][j] != '0') cnt++;
			}
		}
		return cnt;
	}
	
	// map을 복사하는 메서드
	public static char[][] copyMap(char[][] map) {
		char[][] temp = new char[h][w];
		
		// 행마다 복사 진행
		for (int i = 0; i < h; i++) {
			temp[i] = map[i].clone();
		}
		
		return temp;
	}
	
	// 각 열마다의 맨 꼭대기 숫자를 찾는 메서드
	public static int findTopBrick(char[][] map, int col) {
		for (int row = 0; row < h; row++) {
			if(map[row][col] != '0') {
				return row;
			}
		}
		return -1;
	}
	
	// map과 위치를 받아 벽돌을 깨숨
	public static void brokenBricks(char[][] map, int r, int c) {
		// 메서드 시작할 때 먼저 해당 벽돌을 0으로 바꾸고 다음 상하좌우 이동
		int num = map[r][c] -'0';
		map[r][c] = '0';
		
		// 상하좌우 이동
		for (int d = 0; d < 4; d++) {
			int nr = r; int nc = c; // += 연산을 위해 먼저 r,c 대입
			for (int i = 1; i < num; i++) { // 숫자가 1일 때는 자기 자신만 깨져야하기에 1부터 시작.
				nr += dr[d]; nc += dc[d];
				if(nr < 0 || nr >= h || nc < 0 || nc >= w) break;
				
				// 0이 아닐 때 재귀 진행
				if(map[nr][nc] != '0') brokenBricks(map, nr, nc); 
			}
		}
	}
	
	// 중력 작용 메서드
	public static void applyGravity(char[][] map) {
		for (int c = 0; c < w; c++) {
			// 들어간 것이 먼저 빠진다는 논리를 생각하여 큐로 진행
			Deque<Character> que = new ArrayDeque<>();
			
			// 각 열마다 모든 정점 0이 아니라면 que에 넣기
			for (int r = h-1; r >= 0; r--) {
				if(map[r][c] != '0') que.offer(map[r][c]);
			}
			
			// 해당 열이 모두 '0'이라면 생략. 
			if(!que.isEmpty()) {
				for (int r = h-1; r >= 0 ; r--) {
					// que가 비어있다면 0 입력, 아니면 아래서부터 que 순서대로 넣기
					if(que.isEmpty()) {
						map[r][c] = '0';
					}else {
						map[r][c] = que.poll();
					}
				}
			}
		}
	}
	
	// dfs
	public static void dfs(char[][] map, int useCnt) {
		// 구슬을 다 쓰기 전에 남은 벽돌이 없을 수 있기에 먼저 카운팅 진행
		int cnt = countBricks(map);
		
		// 기저조건(구슬을 다 사용했거나, 남은 벽돌이 없다면)
		if(useCnt == n || cnt == 0) {
			if(min > cnt) { // 갱신
				min = cnt;
			}
			return;
		}
		
		// 열만 확인
		for (int col = 0; col < w; col++) {
			// 해당 열에 대한 맨 꼭대기 숫자 위치 확인
			int row = findTopBrick(map, col);
			
			// 해당 열이 모두 0이라면 생략
			if(row == -1) continue;
			
			// map이 섞이면 안되기에 배열 복사
			char[][] temp = copyMap(map);
			
			brokenBricks(temp, row, col); // 구해진 row와 col로 벽돌 깨수기
			applyGravity(temp);			  // 중력 작용
			
			/*
			분기 경우의 수
			- dfs로 가는 경우 벽돌을 부수고 중력 작용을 한 map을 가지고 이어서 왼쪽 열부터 오른쪽 열까지 부심 반복
			- 반복문으로 올라가서 부수기 전 map으로 다음 열에 대해서 꼭대기를 찾고 부시고 dfs 진행 
			 */
			dfs(temp, useCnt+1);
		}
	}
}