import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        // 계산위한 map <차량번호, 총주차시간>
        Map<String, Integer> totalTime = new HashMap<>();
        // 입차 메모 map <차량, 입차시간>
        Map<String, String> map = new HashMap<>();
        
        StringTokenizer st;
        String[] result;
        for(int i = 0; i < records.length; i++){
            st = new StringTokenizer(records[i]);
            String time = st.nextToken();
            String carNum = st.nextToken();
            String state = st.nextToken();
            
            if(state.equals("IN")){ // 입차일 때 map에 넣기
                map.put(carNum, time);
            }else{                  // 출차일 때 바로 계산하여 totalPrice map에 계산
                String[] outTime = time.split(":");
                String[] inTime = map.get(carNum).split(":");
                map.remove(carNum);
                
                // 1. 분단위로 바꾸기
                int mTotalTime = func(outTime[0], outTime[1], inTime[0], inTime[1]);
                totalTime.put(carNum, totalTime.getOrDefault(carNum, 0) + mTotalTime);
            }
        }
        
        for(String str : map.keySet()){
            String[] inTime = map.get(str).split(":");
            
            // 1. 분단위로 바꾸기
            int mTotalTime = func("23", "59", inTime[0], inTime[1]);
            totalTime.put(str, totalTime.getOrDefault(str, 0) + mTotalTime);
        }
        
        // 정렬을 위한 List
        List<String> cars = new ArrayList<>(totalTime.keySet());
        Collections.sort(cars);
        
        // 2. fees 공식으로 정리(fees[1] + (누적 주차 시간 - fees[0]) / fees[2] * fees[3])
        int[] answer = new int[cars.size()];
        int j = 0;
        for(String str : cars){
            int resultTime = 0;
            int time = totalTime.get(str);
            if(time < fees[0]){
                resultTime = fees[1];
            }
            else {
                resultTime = fees[1] + (int)Math.ceil(((double)time - fees[0]) / fees[2]) * fees[3];
            }
            
            // 3. 계산된 내용 삽입
            answer[j] = resultTime;
            j++;
        }
        
        return answer;
    }
    
    public int func(String outTimeH, String outTimeM, String inTimeH, String inTimeM) {
        // 0. 빼기
        int h = 0, m = 0;

        int[] intOutTime = new int[2];
        int[] intInTime = new int[2];
        intOutTime[0] = Integer.parseInt(outTimeH); // 시
        intOutTime[1] = Integer.parseInt(outTimeM); // 분
        intInTime[0] = Integer.parseInt(inTimeH);
        intInTime[1] = Integer.parseInt(inTimeM);

        if(intOutTime[1] < intInTime[1]){
            intOutTime[0]--;
            intOutTime[1] += 60;
        }
        m = intOutTime[1] - intInTime[1];
        h = intOutTime[0] - intInTime[0];
        
        return h * 60 + m;
    }
}