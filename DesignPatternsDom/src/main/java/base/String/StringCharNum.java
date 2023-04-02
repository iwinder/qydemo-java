package base.String;

import Utills.PrintUtill;

import java.util.HashMap;
import java.util.Map;

/**
 * 统计非空字符串中字符出现的次数
 *
 * 三种方案：
 * String.charAt(index)
 * String.split("")
 * String.toCharArray()
 */
public class StringCharNum {

	/**
	 * 通过String.charAt(index)获取字符串中的字符
	 * @param str
	 */
	public static void count(String str){
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		int total = 0;
		Character tmp = null;
		for (int i=0;i<str.length(); i++) {
			tmp = str.charAt(i);
			total = 1;
			if (map.containsKey(tmp)) {
				total = map.get(tmp)+1;
			}
			map.put(tmp, total);
		}
		PrintUtill.printlnRule();
		PrintUtill.println(map);
	}

	/**
	 * String.charAt(index)的变种，将最后存储的Character类型转为String类型
	 * @param str
	 */
	public static void count2(String str){
		Map<String, Integer> map = new HashMap<String, Integer>();
		int total = 0;
		String tmp = null;

		String maxChar = str.charAt(0) + "";
		int maxToal = 1;

		for (int i=0;i<str.length(); i++) {
			tmp = str.charAt(i) + "";
			total = 1;
			if (map.containsKey(tmp)) {
				total = map.get(tmp)+1;
			}
			map.put(tmp, total);
			if (maxToal<total) {
				maxToal = total;
				maxChar = tmp;
			}
		}
		PrintUtill.printlnRule();
		PrintUtill.println(map);
		PrintUtill.println( "出现次数最多的字母是：" + maxChar + ",出现次数是：" + maxToal);
	}

	/**
	 * 通过String.split("")将字符串直接切割成字符串数组
	 * @param str
	 */
	public static void count3(String str){
		Map<String, Integer> map = new HashMap<String, Integer>();
		int total = 0;
		String[] myStrs = str.split("");
		String tmp = null;
		for (int i=0;i<myStrs.length; i++) {
			tmp = myStrs[i];
			total = 1;
			if (map.containsKey(tmp)) {
				total = map.get(tmp)+1;
			}
			map.put(tmp, total);
		}
		PrintUtill.printlnRule();
		PrintUtill.println(map);
	}

	/**
	 * 通过String.toCharArray()将字符串转为char数组
	 * @param str
	 */
	public static void count4(String str){
		Map<String, Integer> map = new HashMap<String, Integer>();
		int total = 0;
		char[] myStrs = str.toCharArray();
		String tmp = null;
		for (int i=0;i<myStrs.length; i++) {
			tmp = myStrs[i] + "";
			total = 1;
			if (map.containsKey(tmp)) {
				total = map.get(tmp)+1;
			}
			map.put(tmp, total);
		}
		PrintUtill.printlnRule();
		PrintUtill.println(map);
	}


	public static void main(String[] args) {
//		String str1 = "ssd ffa34";
		String str1 = "ssd ffa34航啊哈哈哈，不嘛";
//		String str1 = null;
		count(str1);
		count2(str1);
		count3(str1);
		count4(str1);
	}



}
