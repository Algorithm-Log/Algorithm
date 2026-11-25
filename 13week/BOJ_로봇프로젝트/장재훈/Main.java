import java.io.*;
import java.util.*;

public class Main
{
	static final int NANO = 10000000;
    static int l1, l2;
    
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		while(true) {
		    String next = br.readLine();
		    if (next == null) break;
		    int x = Integer.parseInt(next) * NANO;
		    int n = Integer.parseInt(br.readLine());
		    l1 = 0;
		    l2 = 0;
    		List<Integer> legos = new ArrayList<>();
    		
    		for (int i = 0; i < n; i++) {
    		    legos.add(Integer.parseInt(br.readLine()));
    		}
    		
    		legos.sort((a, b) -> a - b);
    		int left = 0, right = n-1;
    		
    		while(left < right) {
    		    int min = legos.get(left);
    		    int max = legos.get(right);
    		    if (min + max == x) {
    		        l1 = min;
    		        l2 = max;
    		        break;
    		    }
    		    else if (min + max > x) right--;
    		    else left++;
    		}
    		
    		if (l2 == 0) {
    		    sb.append("danger").append("\n");
    		}
    		else {
    		    sb.append("yes ").append(l1).append(" ").append(l2).append("\n");
    		}
		} 
		System.out.print(sb.toString());
	}
}

// 딱 2개의 레고
// 구멍 크기 x, 레고 2개로 구멍을 막아야한다.
/*
첫 줄 : 구멍 너비 -> 1cm
다음 줄 : 레고 개수 -> 4개
다음 4개 줄 : 100000000
*/