import java.io.*;
import java.util.*;

public class Solution {
	static int n, m;
	static boolean[][] r;
	static boolean[] visited;
	static int cnt;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // 사용할 수 있는 재료 개수
			m = Integer.parseInt(st.nextToken()); // 궁합이 맞지 않는 조합 개수
			
			// 궁합 맞지 않는 조합이 선택된 것을 표시하기 위한 boolean 배열
			// 숫자가 1부터 시작하여 +1로 범위 늘림
			r = new boolean[n+1][n+1];
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				r[a][b] = true;
				r[b][a] = true;
			}
			
			visited = new boolean[n+1];
			cnt = 0;
			dfs(1);
		
			sb.append('#').append(tc).append(' ').append(cnt).append('\n');
		}
		System.out.print(sb.toString());
	}
	public static void dfs(int depth) {
		// 재료 개수 다 방문했다면
		if(depth == n+1) {
			cnt++;
			return;
		}
		
		// 현재 숫자를 선택 안하는 경우
		dfs(depth+1);
		
		// 진행 여부 확인 변수
		boolean check = true;
		// 현재 숫자를 선택할 경우
		for(int i = 1; i < depth; i++) { // 1부터 현재 위치까지 궁합이 맞지 않는 숫자가 있는지 확인 
			if(visited[i] && r[depth][i]) {
				check = false;
				break;
			}
		}
		
		if(check) {
			visited[depth] = true;
			dfs(depth+1);
			visited[depth] = false;
		}
	}
}
