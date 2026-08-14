import java.io.*;
import java.util.*;

class Solution
{
    static int answer = 0;
    static HashSet<String> set;
    
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T;
		T=Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++)
		{
		    // 원본 row
		    String s = br.readLine();
		    st = new StringTokenizer(s, " ");
            
		    String num = st.nextToken();
		    int len = num.length();
		    // 숫자 하나하나를 따로 저장
		    char[] arr = num.toCharArray();
		    // 총 스왑할 횟수
		    int repeat = Integer.parseInt(st.nextToken());
            
		    // 1. TC마다 static 값 초기화
		    answer = 0;
		    set = new HashSet<>();
			// 2. 완전 탐색 (DFS)
		    dfs(arr, repeat, 0);
			
            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb.toString());
	}
	
	// 바꿀 수 있는 모든 경우의 수 탐색 (cnt : 교환 횟수 카운트)
    public static void dfs(char[] arr, int repeat, int cnt) {
        // 1. 종료조건 : 목표 교환 횟수가 되었을 때, 최댓값 비교
        if (repeat == cnt) {
            int num = Integer.parseInt(String.valueOf(arr));
            answer = Math.max(answer, num);
            return;    
        }
        
        // 2. 현재의 숫자와 교환 횟수를 문자로 표현 (동일한 값을 중복 검사하지 않도록 하기 위해)
        String str = String.valueOf(arr) + "_" + cnt;
        
        // 3. 이미 동일한 횟수에 같은 숫자를 만든 적이 있다면 skip (가지치기) -> Set으로 확인
        if (set.contains(str)) {
            return;
        }
        // 4. 해당 횟수에 처음 나온 값이라면 Set에 저장 (중복처리 방지)
        set.add(str);
        
        // 5. 현재 숫자의 모든 자리를 바꾼다
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                // 자리 스왑
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                
                // 순서를 바꾼 뒤 다음 탐색을 진행
                dfs(arr, repeat, cnt+1);
                
                // 다른 분기 탐색을 위해 swap한 숫자들 원복 (백트래킹)
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
    }
}