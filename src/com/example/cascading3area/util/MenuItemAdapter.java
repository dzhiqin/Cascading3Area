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

	//������
	private Context mContext;
	//�˵��б�
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
	//����ѡ�е�position����֪ͨˢ�������б�
	public void setSelectedPosition(int pos){
		
	}
	@Override
	public int getCount() {
		// TODO �Զ����ɵķ������
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO �Զ����ɵķ������
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO �Զ����ɵķ������
		return null;
	}

}
