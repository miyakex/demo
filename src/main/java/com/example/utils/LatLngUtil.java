package com.example.utils;

import com.google.maps.model.LatLng;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * LatLngを扱うUtil class<br>
 * @author miyakex
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LatLngUtil {

	/**
	 * 2地点のleft, top, right, buttomの範囲内にtargetが存在するかを判定します。<br>
	 * target(lat, lng) = 検査対象<br>
	 * position1(lat1, lng1)<br>
	 * position2(lat2, lng2)
	 * @param targetLat
	 * @param targetLng
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	public static boolean isInRange(double targetLat, double targetLng, double lat1, double lng1, double lat2, double lng2) {
		double left = NumberUtil.min(lat1, lat2);
		double right = NumberUtil.max(lat1, lat2);
		double top = NumberUtil.min(lng1, lng2);
		double buttom = NumberUtil.max(lng1, lng2);
		
		return NumberUtil.between(targetLat, left, right) && NumberUtil.between(targetLng, top, buttom);
	}
	
	/**
	 * 2地点のleft, top, right, buttomの範囲内にtargetが存在するかを判定します。<br>
	 * @param target = 検査対象
	 * @param position1 = 左上
	 * @param position2 = 右下
	 * @return
	 */
	public static boolean isInRange(LatLng target, LatLng position1, LatLng position2) {
		double left = NumberUtil.min(position1.lat, position2.lat);
		double right = NumberUtil.max(position1.lat, position2.lat);
		double top = NumberUtil.min(position1.lng, position2.lng);
		double buttom = NumberUtil.max(position1.lng, position2.lng);
		
		return NumberUtil.between(target.lat, left, right) && NumberUtil.between(target.lng, top, buttom);
	}
}
