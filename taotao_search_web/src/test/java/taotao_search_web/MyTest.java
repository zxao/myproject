package taotao_search_web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyTest {
	/**
	 * 按字母升序排序
	 */
	public void sort(List<String> list){
		for (String str : list) {
			char[] charArray = str.toCharArray();
			List<Integer> charList = new ArrayList<Integer>();
			for (int i = 0; i < charArray.length; i++) {
				
				charList.add(i);
			}
			
			Collections.sort(charList);
			for (Integer integer : charList) {
				
			}
			
		}
	}

}
