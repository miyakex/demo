package com.example.utils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NumberUtil {
	public static boolean equals(BigDecimal a, BigDecimal b) {
		if (a == null && b == null) {
			return true;
		}
		if (a == null || b == null) {
			return false;
		}
		return a.compareTo(b) == 0;
	}
	
	public static boolean isNegative(BigDecimal num) {
		if (num == null) {
			return false;
		}
		return num.compareTo(BigDecimal.ZERO) < 0;
	}
	
	public static int max(int a, int b) {
		int[] array = {a, b};
		return Arrays.stream(array).max().orElseThrow(IllegalArgumentException::new);
	}
	
	public static int min(int a, int b) {
		int[] array = {a, b};
		return Arrays.stream(array).min().orElseThrow(IllegalArgumentException::new);
	}
	
	public static boolean between(int target, int start, int end) {
		if (start > end) {
			throw new IllegalArgumentException("start > end");
		}
		return start < target && target < end;
	}
	
	public static long max(long a, long b) {
		long[] array = {a, b};
		return Arrays.stream(array).max().orElseThrow(IllegalArgumentException::new);
	}
	
	public static long min(long a, long b) {
		long[] array = {a, b};
		return Arrays.stream(array).min().orElseThrow(IllegalArgumentException::new);
	}
	
	public static boolean between(long target, long start, long end) {
		if (start > end) {
			throw new IllegalArgumentException("start > end");
		}
		return start < target && target < end;
	}
	
	public static double max(double a, double b) {
		double[] array = {a, b};
		return Arrays.stream(array).max().orElseThrow(IllegalArgumentException::new);
	}
	
	public static double min(double a, double b) {
		double[] array = {a, b};
		return Arrays.stream(array).min().orElseThrow(IllegalArgumentException::new);
	}
	
	public static boolean between(double target, double start, double end) {
		if (start > end) {
			throw new IllegalArgumentException("start > end");
		}
		return start < target && target < end;
	}
}
