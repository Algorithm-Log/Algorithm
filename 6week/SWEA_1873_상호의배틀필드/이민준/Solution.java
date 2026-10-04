import java.io.*;
import java.util.*;

public class Solution {
	static char[][] map;
	static int h, w;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			map = new char[h][w];
			int tX = 0, tY = 0;
			for(int i = 0; i < h; i++) { // 지도 입력받기
				map[i] = br.readLine().toCharArray();
				for(int j = 0; j < w; j++) { // 탱크의 위치 찾기 위한 for문
					if(map[i][j] == '<' || map[i][j] == 'v' || map[i][j] == '^' || map[i][j] == '>') {
						tX = i; tY = j;
					}
				}
			}
			
			// 탱크 명령어 입력
			int n = Integer.parseInt(br.readLine());
			char[] com = br.readLine().toCharArray();
			
			/*
			문자	동작
			U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
			D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
			L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
			R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
			S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
			 */
			
			// 명령어 별로 맵 변경
			for(char c : com) {
				switch(c) {
				case 'U':
					// 이동 위치가 갈 수 있는 방향인지(벽이 있는지, 맵 범위인지)
					if(PMove(tX-1, tY)) {
						map[tX--][tY] = '.';
						map[tX][tY] = '^';
					}else {
						// 방향만 수정(문자 변경 메서드)
						changeDir(tX, tY, '^');
					}
					
					break;
				case 'D':
					// 이동 위치가 갈 수 있는 방향인지(벽이 있는지, 맵 범위인지)
					if(PMove(tX+1, tY)) {
						map[tX++][tY] = '.';
						map[tX][tY] = 'v';
					}else {
						// 방향만 수정(문자 변경 메서드)
						changeDir(tX, tY, 'v');
					}
					
					break;
				case 'L':
					// 이동 위치가 갈 수 있는 방향인지(벽이 있는지, 맵 범위인지)
					if(PMove(tX, tY-1)) {
						map[tX][tY--] = '.';
						map[tX][tY] = '<';
					}else {
						// 방향만 수정(문자 변경 메서드)
						changeDir(tX, tY, '<');
					}

					break;
				case 'R':
					if(PMove(tX, tY+1)) {
						map[tX][tY++] = '.';
						map[tX][tY] = '>';
					}else {
						// 방향 수정(문자 변경 메서드)
						changeDir(tX, tY, '>');
					}
					
					break;
				case 'S':
					// 현재 방향 확인
					// 방향 라인 끝까지 확인, 가다가 벽돌 벽 있으면 평지로. 나머지는 그대로
					shoot(tX, tY);
					break;
				}
			}
			
			// 출력
			sb.append('#').append(tc).append(' ');
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < w; j++) {
					sb.append(map[i][j]);
				}
				sb.append('\n');
			}
		}
		System.out.print(sb.toString());
	}
	
	// 탱크 방향 수정
	public static void changeDir(int x, int y, char d) {
		map[x][y] = d;
	}
	
	/*
	.	평지(전차가 들어갈 수 있다.)
	*	벽돌로 만들어진 벽
	#	강철로 만들어진 벽
	-	물(전차는 들어갈 수 없다.)
	 */
	public static boolean PMove(int x, int y) {
		if(x < 0 || x >= h || y < 0 || y >= w) return false;
		
		if(map[x][y] == '*' || map[x][y] == '#' || map[x][y] == '-') return false;
		
		return true;
	}
	
	// 대포 발사
	public static void shoot(int x, int y) {
		switch(map[x][y]) {
		case '>':
			for(int i = y+1; i < w; i++) {
				if(shootAndAt(x, i)) break;
			}
			break;
		case '^':
			for(int i = x-1; i >= 0; i--) {
				if(shootAndAt(i, y)) break;
			}
			break;
		case '<':
			for(int i = y-1; i >= 0; i--) {
				if(shootAndAt(x, i)) break;
			}
			break;
		case 'v':
			for(int i = x+1; i < h; i++) {
				if(shootAndAt(i, y)) break;
			}
			break;
		}
	}
	
	// 대포가 부딪혔을 때 액션
	public static boolean shootAndAt(int x, int y) {
		if(map[x][y] == '*') {
			map[x][y] = '.';
			return true;
		}
		else if(map[x][y] == '#') return true;
		return false;
	}
}
