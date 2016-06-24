package com.example.cascading3area.cascading;

import java.util.ArrayList;

import com.example.cascading3area.interfaces.CascadingMenuViewOnSelectListener;
import com.example.cascading3area.model.Area;

import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

//提供PopWindow调用方法
public class CascadingMenuPopWindow extends PopupWindow{

	private Context context;
	private CascadingMenuView cascadingMenuView;
	private ArrayList<Area>areas=null;
	//提供给外部的接口
	private CascadingMenuViewOnSelectListener menuViewOnSelectListener;
	
	public void setMenuItems(ArrayList<Area> areas){
		this.areas=areas;
	}
	public void setMenuViewOnSelectListener(CascadingMenuViewOnSelectListener menuViewOnSelectListener){
		this.menuViewOnSelectListener=menuViewOnSelectListener;
	}
	public CascadingMenuPopWindow(Context context,ArrayList<Area> list){
		super(context);
		this.context=context;
		this.areas=list;
		init();
	}
	public void init(){
		//实例化级联菜单
		cascadingMenuView=new CascadingMenuView(context,areas);
		setContentView(cascadingMenuView);
		setWidth(LayoutParams.MATCH_PARENT);
		setHeight(LayoutParams.MATCH_PARENT);
		//设置回调接口
		cascadingMenuView.setCascadingMenuViewOnSelectListener(new MCascadingMenuViewOnSelectListener());
	}
	//级联菜单选择回调接口
	class MCascadingMenuViewOnSelectListener implements CascadingMenuViewOnSelectListener{

		@Override
		public void getValue(Area menuItem) {
			if(menuViewOnSelectListener!=null){
				menuViewOnSelectListener.getValue(menuItem);
				dismiss();
			}
			
		}
		
	}
}








