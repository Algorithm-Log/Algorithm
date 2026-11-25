import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		Map<String, TreeSet<String>> map = new TreeMap<>();
		Map<String, Integer> countMap = new HashMap<>();
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str;
		while((str = br.readLine()) != null && !str.equals("EOF")) {
			char[] strList = str.toCharArray();
			String temp = new String(strList);
			Arrays.sort(strList);
			String strSort = new String(strList);
			
			map.computeIfAbsent(strSort, k -> new TreeSet<>()).add(temp);
			countMap.merge(strSort, 1, Integer::sum);
		}
		
		List<String> list = new ArrayList<>(map.keySet());
		
		list.sort((a, b) -> {
		    int sizeCompare = Integer.compare(
		        countMap.get(b), countMap.get(a)
		    );

		    if (sizeCompare != 0) return sizeCompare;

		    return map.get(a).first().compareTo(map.get(b).first());
		});
		
		for (int i = 0; i < Math.min(5, list.size()); i++) {
		    String key = list.get(i);

		    sb.append("Group of size ")
		      .append(countMap.get(key))
		      .append(": ");

		    for (String s : map.get(key)) {
		        sb.append(s).append(' ');
		    }

		    sb.append('.').append('\n');
		}
		System.out.print(sb.toString());
	}

}
