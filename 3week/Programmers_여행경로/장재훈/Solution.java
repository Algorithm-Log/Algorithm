import java.util.*;

class Solution {
    String[][] tickets;
    // 정답 배열
    String[] answer;
    // 총 티켓 개수
    int len;
    // 방문 여부 확인
    boolean[] visited;
    // 누적 경로 저장
    List<String> path;
    
    public String[] solution(String[][] tickets) {
        this.tickets = tickets;
        this.len = tickets.length;
        answer = new String[len+1];
        
        for (int i = 0; i < len; i++) {
            // 방문 배열 초기화
            this.visited = new boolean[len];
            // 누적 경로 초기화
            this.path = new ArrayList<>();
            if (tickets[i][0].equals("ICN")) {
                visited[i] = true;
                path.add(tickets[i][0]);
                path.add(tickets[i][1]);
                findPath(1, tickets[i][1]);
            }
        }
        
        return answer;
    }
    
    // 방문한 나라 수(depth)와 다음 도시를 매개변수(nextCity)로 받음
    public void findPath(int depth, String nextCity) {
        // 모든 배열 방문 시 종료
        if (depth >= len) {
            if (answer[0] == null) {
                answer = path.toArray(new String[0]);
            }
            // 알파벳 순서로 비교
            for (int i = 0; i < depth; i++) {
                // answer가 사전순으로 더 빠르다 (음수)
                if (answer[i].compareTo(path.get(i)) < 0) {
                    break;
                }
                // answer가 사전순으로 더 느리다 (양수)
                else if (answer[i].compareTo(path.get(i)) > 0) {
                    answer = path.toArray(new String[0]);
                    break;
                }
            }
            return;
        }
        
        for (int i = 0; i < len; i++) {
            if (!visited[i] && tickets[i][0].equals(nextCity)) {
                visited[i] = true;
                path.add(tickets[i][1]);
                findPath(depth+1, tickets[i][1]);
                // 가지치기로 다른 경우를 찾기 위해 방문 배열을 false로 처리
                visited[i] = false;
                // 추가한 경로 제거
                path.remove(path.size()-1);
            }
        }
        // 방문 가능한 배열 없을 시 종료
        return;
    }
}