package com.example.cascading3area.cascading;

import java.util.ArrayList;

import com.example.cascading3area.interfaces.CascadingMenuViewOnSelectListener;
import com.example.cascading3area.model.Area;

import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

//�ṩPopWindow���÷���
public class CascadingMenuPopWindow extends PopupWindow{

	private Context context;
	private CascadingMenuView cascadingMenuView;
	private ArrayList<Area>areas=null;
	//�ṩ���ⲿ�Ľӿ�
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
		//ʵ���������˵�
		cascadingMenuView=new CascadingMenuView(context,areas);
		setContentView(cascadingMenuView);
		setWidth(LayoutParams.MATCH_PARENT);
		setHeight(LayoutParams.MATCH_PARENT);
		//���ûص��ӿ�
		cascadingMenuView.setCascadingMenuViewOnSelectListener(new MCascadingMenuViewOnSelectListener());
	}
	//�����˵�ѡ��ص��ӿ�
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








