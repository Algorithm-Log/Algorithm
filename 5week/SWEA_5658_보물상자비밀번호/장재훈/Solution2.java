import java.io.*;
import java.util.*;

public class Solution2
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
		    // 비밀번호 rotate하는 대신 2번 반복시켜서 rotate하는 효과 만듦
		    String rotatedPwd = passwords+passwords;
		    // 중복 비밀번호 처리를 위한 Set
		    Set<Integer> set = new HashSet<>();
		    
		    // 한 변에 N/4만큼 숫자들이 있을 수 있으니 N/4번 반복
		    for (int i=0; i<N/4; i++) {
		        // N/4의 길이만큼 띄어서 비밀번호로 자르도록 함
		        for (int j=i; j<N; j+=N/4) {
		            // N/4의 길이만큼 자름
		            String pwd = rotatedPwd.substring(j, j+N/4);
		            // String의 16진수를 10진수로 변환
		            int intPwd = Integer.parseInt(pwd, 16);
		            // Set에 집어넣어서 중복제거 처리
		            set.add(intPwd);
		        }
		    }
		    
		    // Set의 값들을 배열로 변경
    	    int[] pwdList = new int[set.size()];
    	    int idx = 0;
    	    for (int pwd : set) {
    	        pwdList[idx++] = pwd;
    	    }
    	    
    	    Arrays.sort(pwdList); // 오름차순 정렬
    	    // N번째로 큰걸 알기 위해 역순으로 접근 (내림차순)
    	    int kPwd = pwdList[set.size()-K];
    	    // Arrays.sort(pwdList, Collections.reverseOrder()); (내림차순)
    	    // int kPwd = pwdList[K];
    	    sb.append("#").append(tc).append(" ").append(kPwd).append("\n");
		}
		System.out.print(sb.toString());
	}
}