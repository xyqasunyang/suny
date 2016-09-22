package com.sun.test;

/**
 * 递归
 * 
 * @author sun
 *
 */
public class Recursion {

	// 阶乘问题
	public static int factorial(int param) {
		if (param > 0)
			return param * factorial(param - 1);
		else
			return 1;
	}

	// 汉诺塔问题
	public static void hanon(int n, String a, String b, String c) {
		if (n == 1) {
			System.out.println("把" + n + "从" + a + "移动到" + c);
		} else {
			hanon(n - 1, a, c, b);
			System.out.println("把" + n + "从" + a + "移动到" + c);
			hanon(n - 1, b, a, c);
		}

	}

	// 相加
	public static int add(int param) {
		if (param > 0)
			return param + add(param - 1);
		else
			return 0;
	}

	// 求两个数的最大公约数 :用两个数的绝对值与这两个数较小的那个一直比较，直到相等为止。
	public static int approximate(int num1,int num2){
		if(num1==num2)
			return num1;
		else{
			return approximate(num1-num2>0?num1-num2:num2-num1, num1-num2>0?num2:num1);
		}
	}
	
	//全排列
	public static void fullArray(int[] array,int cursor,int end){
		
	}
	

	public static void main(String[] args) {
		System.out.println(factorial(4));
		hanon(3, "一", "二", "三");
		System.out.println(add(3));
		System.out.println(approximate(24, 6));
		fullArray(new int[]{1,2,3,3}, 0, 4);
	}
}
