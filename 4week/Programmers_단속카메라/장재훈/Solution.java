import java.util.Arrays;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        int[] cameras = new int[routes.length];
        
        // 고속도로 진입한 시점으로 정렬
        Arrays.sort(routes, (a, b) -> a[1] - b[1]);
        
        // 최근에 설치한 카메라 위치
        int newCam = routes[0][1]; // 기본 값으로 첫번째 차량의 고속도로 나간 지점을 잡음
        answer++; // 카메라 개수 1 증가
        
        for (int i = 1; i < routes.length; i++) {
            // 설치한 카메라가 다음 자동차의 출발지를 못잡는다면, 새로운 카메라 설치
            if (routes[i][0] > newCam) {
                newCam = routes[i][1];
                answer++;
            }
        }
        
        return answer;
    }
}