package com.goldingmedia.temporary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.goldingmedia.activity.ELineActivity;
import com.goldingmedia.activity.MediaPlayActivity;
import com.goldingmedia.activity.PreviewPlayActivity;
import com.goldingmedia.activity.WebActivity;
import com.goldingmedia.contant.Contant;
import com.goldingmedia.goldingcloud.TruckMediaProtos;
import com.goldingmedia.mvp.view.activity.JmagazineActivity;
import com.goldingmedia.mvp.view.activity.ReadyBookActivity;
import com.goldingmedia.mvp.view.activity.SettingActivity;
import com.goldingmedia.temporary.Variables.StatusItem;
import com.goldingmedia.utils.Utils;

public class CardManager {
	private static CardManager mCardManager;
	public static CardManager getInstance() {
		if (mCardManager == null) {
			mCardManager = new CardManager();
		}
		return mCardManager;
	}
	
	public void action(int position, TruckMediaProtos.CTruckMediaNode truck, Context context) {
		Intent intent;
		switch (truck.getCategoryId()) {
		case Contant.CATEGORY_HOTZONE_ID:// 热门推荐类
			intent = new Intent(context, PreviewPlayActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra(StatusItem.classMainId, truck.getMediaInfo().getTruckMeta().getTruckMediaType());
			startActivity(position, truck, context, intent);
			break;

		case Contant.CATEGORY_MEDIA_ID:// 多媒体
			switch (truck.getMediaInfo().getTruckMeta().getTruckMediaType()) {
			case Contant.MEDIA_TYPE_MUSIC:// 音乐
				intent = new Intent(context, MediaPlayActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra(StatusItem.classMainId, truck.getMediaInfo().getTruckMeta().getTruckMediaType());
				startActivity(position, truck, context, intent);
				break;

			default:// 视频
				intent = new Intent(context, PreviewPlayActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra(StatusItem.classMainId, truck.getMediaInfo().getTruckMeta().getTruckMediaType());
				startActivity(position, truck, context, intent);
				break;
			}
			break;

		case Contant.CATEGORY_GOLDING_ID:// 喜粤传媒
			switch (truck.getCategorySubId()) {
				case Contant.PROPERTY_GOLDING_JTV_ID:// 喜悦电视
					intent = new Intent(context, MediaPlayActivity.class);
					//intent = new Intent(context, WebActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(position, truck, context, intent);
					break;

				case Contant.PROPERTY_GOLDING_MAGAZINE_ID:
					intent = new Intent(context, JmagazineActivity.class);
					startActivity(position+1, truck, context, intent);
					break;

				default:
					break;
			}
			break;

		case Contant.CATEGORY_ELIVE_ID:// e-在线
			switch (truck.getCategorySubId()) {
				case Contant.PROPERTY_ELIVE_MALL_ID://
					intent = new Intent(context, ELineActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(position, truck, context, intent);
					break;

				default:
					intent = new Intent(context, ELineActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(position, truck, context, intent);
					break;
			}
			break;

		case Contant.CATEGORY_MYAPP_ID:// 我的应用
			switch (truck.getCategorySubId()) {
				case Contant.PROPERTY_MYAPP_EBOOK_ID:// 电子书
					intent = new Intent(context, ReadyBookActivity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("ebookV",truck);
					intent.putExtra("ebookB",bundle);
					startActivity(position, truck, context, intent);
					break;
				case Contant.PROPERTY_MYAPP_APP_ID:// 应用
					if(truck.getMediaInfo().getTruckMeta().getTruckDesc() == null || truck.getMediaInfo().getTruckMeta().getTruckDesc().equals("")){
						return;
					}
					Utils.openApp(context,truck.getMediaInfo().getTruckMeta().getTruckDesc());
					break;
				case Contant.PROPERTY_MYAPP_SETTING_ID:// 设置
					if(truck.getMediaInfo().getTruckMeta().getTruckDesc() == null || truck.getMediaInfo().getTruckMeta().getTruckDesc().equals("")){
						return;
					}
					intent = new Intent(context, SettingActivity.class);
					startActivity(position, truck, context, intent);
					break;

				default:
					break;
			}

			break;

		default:
			break;
		}
	}
	
	private void startActivity(int position, TruckMediaProtos.CTruckMediaNode truck, Context context, Intent intent) {
//		Log.i("", "----"+mode.classId+"|"+mode.classSubId+"|"+mode.cardId);
		intent.putExtra(StatusItem.position, position);
		intent.putExtra(StatusItem.classId, truck.getCategoryId());
		intent.putExtra(StatusItem.classSubId, truck.getCategorySubId());
		intent.putExtra("truck", truck);
		context.startActivity(intent);
	}
}
