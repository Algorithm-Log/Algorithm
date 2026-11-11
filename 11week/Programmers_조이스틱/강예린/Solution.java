class Solution {
    public int solution(String name) {
        int n= name.length();
        int sum= 0;
        for(int i=0; i< n;i++)sum += Math.min(name.charAt(i)-'A', 'Z'- name.charAt(i)+1);
        if(n==1) return sum;
        return sum+ minRoute(name, n);
    }

    public int minRoute (String name, int n){

        // 현재 기준
        int i=0;
        // 연속 A 시작
        int start=0;
        int min= n-1;

        while(i < n){

            // 현재 A면 어디까지 연속인지
            if(name.charAt(i) =='A') {
                start= i++;

                // 시작부터 연속 A 끝나는 부분 다음 i
                while(i < n && name.charAt(i) =='A') i++;

                // 시작점이 A일 경우 대비
                start= start==0 ? 1: start;
                min= Math.min(min, Math.min( ((start-1)*2+n-i), start+1+2*(n-i-1)));
            }
            else i++;

        }

        return min;
    }
}