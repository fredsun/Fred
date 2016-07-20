package com.fred.utils;

import android.app.Activity;

import com.fred.activity.HomeActivity;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * company: 左手网络科技有限公司
 * 
 * version: 1.0
 * 
 * date: 2015年8月26日-下午4:06:17
 * 
 * author: durs
 * 
 * descript:Android完美退出
 * 
 * Revision history:
 * 
 * ======================================================
 */
public class MyActivityList {
	// 对于新增和删除操作add和remove，LinedList比较占优势，因为ArrayList实现了基于动态数组的数据结构，要移动数据。LinkedList基于链表的数据结构,便于增加删除
	private List<Activity> activityList = new LinkedList<Activity>();
	private static MyActivityList instance;
	private Activity delActivity;

	private MyActivityList() {

	}

	// 单例模式中获取唯一的MyApplication实例
	public static MyActivityList getInstance() {
		if (null == instance) {
			instance = new MyActivityList();
		}
		return instance;
	}

	// 添加Activity到容器中
	public void addActivity(Activity activity) {
		if (!activityList.contains(activity)) {
			activityList.add(activity);
		}
	}

	// 结束Activity
	public void delActivity(Activity activity) {
		activityList.remove(activity);
		activity.finish();
	}

	// 结束指定类名的Activity
	public void delActivity(Class<?> cls) {
		delActivity = null;
		for (Activity activity : activityList) {
			if (activity.getClass().equals(cls)) {
				delActivity = activity;
			}
		}
		if (delActivity != null) {
			delActivity(delActivity);
		}
	}

	// 判断制定activity是否存在
	public boolean isActivityExist(Class<?> cls) {
		for (Activity activity : activityList) {
			if (activity.getClass().equals(cls)) {
				return true;
			}
		}
		return false;
	}

	// 遍历所有Activity并finish
	public void exit() {
		Iterator<Activity> it = activityList.iterator();
		while (it.hasNext()) {
			Activity lActivity = it.next();
			lActivity.finish();
			it.remove();
		}
//		System.exit(0);
	}

	// 遍历所有Activity并finish除了LeftHandActivity
	public void delExceptLeftHand() {
		Iterator<Activity> it = activityList.iterator();
		while (it.hasNext()) {
			Activity lActivity = it.next();
			if (lActivity.getClass().equals(HomeActivity.class)) {
				continue;
			}
			lActivity.finish();
			it.remove();
		}
	}
}