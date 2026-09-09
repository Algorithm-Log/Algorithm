import java.util.*;
class Solution {
    public static class Car{
        int timeSum=0;
        boolean in= false;
        int inM=0;
    }
    
    public static int recordTomin(String time){
        int h= Integer.parseInt(time.split(":")[0]);
        int m= Integer.parseInt(time.split(":")[1]);
        return h*60 + m;
    }
    
    public int[] solution(int[] fees, String[] records) {
        
        // 차번호, 차 요금 Map
        Map<String, Car> result= new TreeMap<>();

        Car car;
        for(String record: records){
            String[] s= record.split(" ");
            
            // 들어올때
            if(s[2].equals("IN")) {
                if(result.containsKey(s[1])){
                    car= result.get(s[1]);                    
                } else {
                    car= new Car();
                }
                
                car.in= true;
                car.inM= recordTomin(s[0]);
                result.put(s[1], car);
            }
            else{
                // 나갈때
                car= result.get(s[1]);  
                // 시간 계산
                car.timeSum+= recordTomin(s[0])-car.inM;
                car.in= false;
            }
            
        }
        
        int[] answer= new int [result.size()];
        int idx= 0;
        
        // 주차요금 계산
        for(String key : result.keySet()){
            
            // 무조건 기본요금은 부과
            int sum= fees[1];
            car= result.get(key);
            
            // 출차 안했으면 23:59 기준으로 출차
            if(car.in){
                car.timeSum+= 23*60+59- car.inM;
            }
                
            // 기준시간 넘은 경우만 추가요금
            if(car.timeSum > fees[0]){
                int cnt= (car.timeSum- fees[0])%fees[2] !=0? (car.timeSum- fees[0])/fees[2]+1: (car.timeSum- fees[0])/fees[2];
                sum+= cnt* fees[3];
            }
                
            answer[idx++ ]= sum;
            }
        
        
        return answer;
    }
}