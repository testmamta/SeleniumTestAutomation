package pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.*;

public class TestClass {

	static List<Integer> num = Arrays.asList(1,1,3,2,3,2,1);
	
	
	public static List<Integer> findDuplicate(List<Integer> num) 
	{
		List<Integer> duplicates = new ArrayList<Integer>();
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int n : num) {
			hm.put(n,hm.getOrDefault(n,0) +1);
		}
		for (Entry<Integer, Integer> entry : hm.entrySet())
		{
			if(entry.getValue()>1) {
				duplicates.add(entry.getKey());
			}
			
		}
		return duplicates;
	}
	
	public static void main(String[] args) {
		System.out.println(findDuplicate(num));
	}
}
