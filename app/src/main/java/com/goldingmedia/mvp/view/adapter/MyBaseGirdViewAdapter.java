package com.goldingmedia.mvp.view.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.goldingmedia.GDApplication;
import com.goldingmedia.R;
import com.goldingmedia.contant.Contant;
import com.goldingmedia.goldingcloud.TruckMediaProtos;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jallen on 2017/7/28 0028 18:30.
 */

public class MyBaseGirdViewAdapter extends BaseAdapter {
    private Context mContext;
    List<TruckMediaProtos.CTruckMediaNode> mTrucks = new ArrayList<TruckMediaProtos.CTruckMediaNode>();

    public MyBaseGirdViewAdapter(Context ct){
        mContext = ct;
    }

    public void refresh(List<TruckMediaProtos.CTruckMediaNode> trucks){
        if(trucks == null){
            return;
        }
        mTrucks = trucks;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mTrucks.size();
    }

    @Override
    public Object getItem(int position) {
        if (mTrucks == null) return null;
        else if (position >= mTrucks.size()) return null;
        return mTrucks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        TruckMediaProtos.CTruckMediaNode truckMediaNode = mTrucks.get(position);
        int categoryId = truckMediaNode.getMediaInfo().getCategoryId();
        int categorySubId = truckMediaNode.getMediaInfo().getCategorySubId();
        String imgPath = Contant.getTruckMetaNodePath(categoryId,categorySubId,truckMediaNode.getMediaInfo().getTruckMeta().getTruckFilename(),true);
        String imgName = truckMediaNode.getMediaInfo().getTruckMeta().getTruckImage();
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_base_gird, parent,false);
            holder.imageView = (ImageView) convertView.findViewById(R.id.image);
            holder.txtView = (TextView) convertView.findViewById(R.id.txt);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imageView.setImageBitmap(BitmapFactory.decodeFile(imgPath+"/"+imgName));

        holder.txtView.setText(truckMediaNode.getMediaInfo().getTruckMeta().getTruckTitle());
        if(categoryId == Contant.CATEGORY_MYAPP_ID && categorySubId == Contant.PROPERTY_MYAPP_SETTING_ID){
            holder.txtView.setVisibility(View.GONE);
        }else{
            holder.txtView.setVisibility(View.VISIBLE);
        }


        return convertView;
    }

    static class ViewHolder{
        ImageView imageView;
        TextView txtView;
    }
}
