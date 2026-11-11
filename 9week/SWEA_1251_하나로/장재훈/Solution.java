import java.io.*;
import java.util.*;

public class Solution {
    static int V; // V: 정점 개수
    static double taxRate; // E: 세율
    static int[] X, Y;
    static long[] minEdgeCost;
    static boolean[] visited;
    
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
		    V = Integer.parseInt(br.readLine());
		    
		    StringTokenizer stX = new StringTokenizer(br.readLine(), " ");
		    StringTokenizer stY = new StringTokenizer(br.readLine(), " ");
		    X = new int[V];
		    Y = new int[V];
		    visited = new boolean[V];
		    minEdgeCost = new long[V];
		    
		    for (int i = 0; i < V; i++) {
		        X[i] = Integer.parseInt(stX.nextToken());
		        Y[i] = Integer.parseInt(stY.nextToken());
		    }
		    taxRate = Double.parseDouble(br.readLine());
		    
		    long mstCost = prim();
		    
		    long res = Math.round(taxRate*mstCost);
		    
		    sb.append("#").append(tc).append(" ").append(res).append("\n");
		}// end of tc
		System.out.print(sb.toString());
	}// end of main
	
	// prim의 알고리즘에 정확히 fit한 기능으로 수정
	static long prim() {
	    
	    // minEdgeCost를 INF로 초기화
	    Arrays.fill(minEdgeCost, Long.MAX_VALUE);
	    minEdgeCost[0] = 0;
	    long totalCost = 0; // totalCost: 누적 거리
	    // V개의 정점을 모두 연결하도록 반복 (0으로 초기화한 0번 인덱스도 포함)
	    for (int i = 0; i < V; i++) {
	        // 1. MST에 연결할 최소 비용 정점 선택
	        int current = selectMinVertex();
	        
    	    // 2. 선택한 정점을 MST에 추가
    	    visited[current] = true;
    	    totalCost += minEdgeCost[current];
    	    
    	    // 3. 선택한 정점으로 다른 정점들의 최소 비용 갱신   
    	    for (int other = 0; other < V; other++) {
    	        if (visited[other]) continue;
    	        // 정점별 최소비용 갱신
    	        calcMinDist(X[other], Y[other], X[current], Y[current], other);
    	    }
	    }
	    return totalCost;
	}
	
	// 정점별 최소비용 갱신
	static void calcMinDist(int x1, int y1, int x2, int y2, int other) { // other : 최소 비용 갱신 대상 정점
	    long dx = x2 - x1;
        long dy = y2 - y1;
        long cost = dx * dx + dy * dy;
        
        // 최소 비용 갱신
        if (cost < minEdgeCost[other]) minEdgeCost[other] = cost;
	}
	
	// minEdgeCost 중 방문하지 않은 애들 중 최솟값 찾기
	static int selectMinVertex() {
	    int idx = -1;
	    long min = Long.MAX_VALUE;
	    for (int i = 0; i < V; i++) {
	        if (!visited[i] && min > minEdgeCost[i]) {
	            min = minEdgeCost[i];
	            idx = i;
	        }
	    }
	    return idx;
	}
	
}// end of class