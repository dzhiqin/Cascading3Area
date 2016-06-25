package com.example.cascading3area.cascading;

import java.util.ArrayList;

import com.example.cascading3area.interfaces.CascadingMenuViewOnSelectListener;
import com.example.cascading3area.model.Area;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class CascadingMenuFragment extends Fragment{

	private CascadingMenuView cascadingMenuView;
	private ArrayList<Area>areas=null;
	//�ṩ���ⲿ�Ľӿ�
	private CascadingMenuViewOnSelectListener menuViewOnSelectListener;
	private static CascadingMenuFragment instance=null;
	//����ģʽ
	public static CascadingMenuFragment getInstance(){
		if(instance==null){
			instance=new CascadingMenuFragment();
		}
		return instance;
	}
	
	public void setMenuItems(ArrayList<Area> areas){
		this.areas=areas;
	}
	
	public void setMenuViewOnSelectListener(
			CascadingMenuViewOnSelectListener menuViewOnSelectListener) {
		this.menuViewOnSelectListener = menuViewOnSelectListener;
	}
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//ʵ���������˵�
		cascadingMenuView=new CascadingMenuView(getActivity(),areas);
		//���ûص��ӿ�
		cascadingMenuView.setCascadingMenuViewOnSelectListener(new MCascadingMenuViewOnSelectListener());
	}
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		return cascadingMenuView;
	}
	//�����˵�ѡ��ص��ӿ�
	class MCascadingMenuViewOnSelectListener implements CascadingMenuViewOnSelectListener{
		public void getValue(Area area){
			if(menuViewOnSelectListener!=null){
				menuViewOnSelectListener.getValue(area);
			}
		}
	}






}
