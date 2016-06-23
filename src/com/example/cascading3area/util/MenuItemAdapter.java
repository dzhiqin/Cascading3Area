package com.example.cascading3area.util;

import java.util.List;

import com.example.cascading3area.model.Area;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;

public class MenuItemAdapter extends BaseAdapter{

	//上下文
	private Context mContext;
	//菜单列表
	private List<Area> mListData;
	private int selectedPos=-1;
	private String selectedText="";
	private int normalDrawableId;
	private Drawable selectedDrawable;
	private float textSize;
	private OnClickListener onClickListener;
	private OnItemClickListener mOnItemClickListener;
	
	public MenuItemAdapter(Context context,List<Area> listData,int sId,int nId){
		mContext=context;
		mListData=listData;
		selectedDrawable=mContext.getResources().getDrawable(sId);
		normalDrawableId=nId;
		init();
	}
	
	private void init(){
		onClickListener=new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				selectedPos=(Integer) View.getTag();
				setSelectedPosition(selectedPos);
				if(mOnItemClickListener!=null){
					mOnItemClickListener.onItemClick(v, selectedPos);
				}
				
			}
		};
	}
	//设置选中的position，并通知刷新其他列表
	public void setSelectedPosition(int pos){
		
	}
	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存根
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		return null;
	}

}
