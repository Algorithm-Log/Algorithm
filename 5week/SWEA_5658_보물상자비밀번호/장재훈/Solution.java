import java.io.*;
import java.util.*;

public class Solution
{
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st; // = new StringTokenizer()
		
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
		    st = new StringTokenizer(br.readLine());
		    int N = Integer.parseInt(st.nextToken());
		    int K = Integer.parseInt(st.nextToken());
		    // 비밀번호 숫자들 받음
		    String passwords = br.readLine();
		    // 각 번호들 저장
		    ArrayDeque<String> numbers = new ArrayDeque<>();
		    
		    for (int i = 0; i < N; i++) {
		        numbers.offer(passwords.charAt(i)+"");
		    }
		    
		    Set<Integer> set = new HashSet<>();
		    
		    // N/3번 돌린다 (다시 원래 위치로 번호들이 오게되는 총 회전 수)
		    for (int i=0; i<N/4; i++) {
		        // N번 돌리면서 q에서 빼고 집어넣으며 숫자조합 만듦
		        String num = "";
		        for (int j=0; j<N; j++) {
		            // 앞의 숫자를 빼고 뒤에 넣는다. 그러면서 모든 3자리 숫자 체크
		            String temp = numbers.pollFirst();
		            num += temp;
		            numbers.offerLast(temp);
		            // 3번 돌렸으면 이를 10진수로 바꿔서 Set에 박음
		            if (num.length() == N/4) {
		                // 숫자로 변경
		                int pwd = strToInt(num);
		                set.add(pwd);
		                num = "";
		            }
		        }
		        // 앞을 빼서 뒤로 넣는다.
		        String front = numbers.pollLast();
		        numbers.offerFirst(front);
		    }
		    
		    // Set의 값들을 배열로 변경
    	    int[] pwdList = new int[set.size()];
    	    int idx = 0;
    	    for (int pwd : set) {
    	        pwdList[idx++] = pwd;
    	    }
    	    
    	    Arrays.sort(pwdList);
    	    int kPwd = pwdList[set.size()-K];
    	    sb.append("#").append(tc).append(" ").append(kPwd).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	// 16진수 -> 10진수
	public static int c16to10(String num16) {
	    char c = num16.charAt(0);
	    int num10 = 0;
	    if (c > '9') {
	        num10 = c-'A'+10;
	    }
	    else {
	        num10 = c-'0';
	    }
	    return num10;
	}
	
	// 숫자로 변환!
	public static int strToInt(String num) {
	    int res = 0;
	    String[] nums = num.split("");
	    for (int i=0; i<nums.length; i++) {
	        // 각 자리 숫자
	        int n = c16to10(nums[i]);
	        res += Math.pow(16, nums.length-1-i)*n;
	    }
	    return res;
	}
}