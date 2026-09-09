import java.util.HashMap;
import java.util.TreeMap;

class Solution {
    static int giTime;
    static int giFee;
    static int danTime;
    static int danFee;
    
    public int[] solution(int[] fees, String[] records) {
        
        giTime = fees[0];
        giFee = fees[1];
        danTime = fees[2];
        danFee = fees[3];
        // 입출차 관리 맵 (차 번호, 입차 시간)
        HashMap<String, Integer> map = new HashMap<>();
        // 차량별 누적 시간 관리 (차 번호, 누적 주차 시간)
        TreeMap<String, Integer> carTime = new TreeMap<>();
        
        for (String r : records) {
            String[] record = r.split(" ");
            String time = record[0]; // 시각
            String car = record[1]; // 차량 번호
            String ny = record[2]; // 내역
            
            // 절대 분 단위 환산
            String[] hhmm = time.split(":");
            int currentMin = Integer.parseInt(hhmm[0]) * 60 + Integer.parseInt(hhmm[1]); 
            // 입차
            if (ny.equals("IN")) {
                map.put(car, currentMin);
            }
            // 출차
            else {
                // 입차했던 시간 가져옴. 동시에 해당 차량 삭제
                int icTime = map.remove(car);
                
                // 누적 주차 시간
                int totalTime = currentMin - icTime;
                // 차에 시간을 누적함 (차량이 처음 누적될 때 "getOrDefault"로 에러 방지)
                carTime.put(car, carTime.getOrDefault(car, 0) + totalTime);
            }
        }
                
        // 입차 후 출차하지 않은 차량 관리
        for (String carNum : map.keySet()) {
            int icTime = map.get(carNum);
            // 누적 주차 시간
            int totalTime = 23*60+59 - icTime;
            // 차량별 누적 요금 관리
            carTime.put(carNum, carTime.getOrDefault(carNum, 0) + totalTime);
        }
        
        int[] answer = new int[carTime.size()];
        int idx = 0;
        for (int time : carTime.values()) {
            answer[idx] = payFee(time);
            idx++;
        }
        
        return answer;
    }
    
    // 요금 계산
    public static int payFee (int time) {
        int totalFee = giFee;
        time -= giTime;
        if (time <= 0) return totalFee;
        
        // double로 해야 소수점이 생겨서 올림이 가능
        totalFee += Math.ceil((double)time/danTime)*danFee;
        
        return totalFee;
    }
}