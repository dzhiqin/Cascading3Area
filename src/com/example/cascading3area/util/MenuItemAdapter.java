package com.example.cascading3area.util;

import java.util.ArrayList;
import java.util.List;

import com.example.cascading3area.R;
import com.example.cascading3area.model.Area;




import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

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
				selectedPos=(Integer) v.getTag();
				setSelectedPosition(selectedPos);
				if(mOnItemClickListener!=null){
					mOnItemClickListener.onItemClick(v, selectedPos);
				}
				
			}
		};
	}
	//设置选中的position，并通知刷新其他列表
	public void setSelectedPosition(int pos){
		if(mListData!=null&&pos<mListData.size()){
			selectedPos=pos;
			selectedText=mListData.get(pos).getName();
			notifyDataSetChanged();
		}
	}
	//设置选中的position，但不通知刷新
	public void setSelectedPositionNoNotify(int pos,ArrayList<Area> list){
		selectedPos=pos;
		mListData=list;
		if(mListData!=null&&pos<mListData.size()){
			selectedText=mListData.get(pos).getName();
		}
	}
	//获取选中的position
	public int getSelectedPosition(){
		if(mListData!=null&&selectedPos<mListData.size()){
			return selectedPos;
		}
		return -1;
	}
	//设置列表字体大小
	public void setTextSize(float tSize){
		textSize=tSize;
	}
	public View getView(int position ,View convertView,ViewGroup parent){
		TextView view;
		if(convertView==null){
			view=(TextView)LayoutInflater.from(mContext).inflate(R.layout.choose_item,parent,false);
			
		}else{
			view=(TextView)convertView;
		}
		view.setTag(position);
		String mString="";
		if(mListData!=null){
			if(position<mListData.size()){
				mString=mListData.get(position).getName();
			}
		}
		if(mString.contains("不限"))
			view.setText("不限");
		else
			view.setText(mString);
		view.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
		
		if(selectedText!=null && selectedText.equals(mString)){
			view.setBackgroundDrawable(selectedDrawable);
		}else{
			view.setBackgroundDrawable(mContext.getResources().getDrawable(normalDrawableId));
		}
		view.setPadding(20, 0, 0, 0);
		view.setOnClickListener(onClickListener);
		return view;
	}
	public void setOnItemClickListener(OnItemClickListener listener){
		mOnItemClickListener=listener;
	}
	//重新定义菜单选项单机接口
	public interface OnItemClickListener{
		public void onItemClick(View view,int position);
	}
	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return mListData.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO 自动生成的方法存根
		return mListData.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO 自动生成的方法存根
		return 0;
	}

	


	

}
