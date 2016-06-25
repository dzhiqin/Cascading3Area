package com.example.cascading3area.cascading;

import java.util.ArrayList;

import com.example.cascading3area.R;
import com.example.cascading3area.db.DBHelper;
import com.example.cascading3area.interfaces.CascadingMenuViewOnSelectListener;
import com.example.cascading3area.model.Area;
import com.example.cascading3area.util.LogUtil;
import com.example.cascading3area.util.MenuItemAdapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

//三级联动ListView
public class CascadingMenuView extends LinearLayout{

	private static final String TAG=CascadingMenuView.class.getSimpleName();
	//三级菜单选择后触发的接口，即最终选择的内容
	private CascadingMenuViewOnSelectListener mOnSelectListener;
	private ListView firstMenuListView;
	private ListView secondMenuListView;
	private ListView thirdMenuListView;
	
	//每次选择的子菜单内容
	private ArrayList<Area>thirdItem=new ArrayList<Area>();
	private ArrayList<Area>secondItem=new ArrayList<Area>();
	private ArrayList<Area>menuItem;
	private MenuItemAdapter firstMenuListViewAdapter;
	private MenuItemAdapter secondMenuListViewAdapter;
	private MenuItemAdapter thirdMenuListViewAdapter;
	
	private int firstPosition=0;
	private int secondPosition=0;
	private int thirdPosition=0;
	
	private DBHelper dbHelper;
	private Context context;
	
	//上下文
	public CascadingMenuView(Context context ,ArrayList<Area> menuList){
		super(context);
		this.menuItem=menuList;
		this.context=context;
		dbHelper=new DBHelper(context);
		init(context);
	}
	public CascadingMenuView(Context context,AttributeSet attrs){
		super(context,attrs);
		this.context=context;
		dbHelper=new DBHelper(context);
		init(context);
	}
	private void init(final Context context){
		LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_region, this,true);
		firstMenuListView=(ListView)findViewById(R.id.listView);
		secondMenuListView=(ListView)findViewById(R.id.listView2);
		thirdMenuListView=(ListView)findViewById(R.id.listView3);
		
		//初始化一级主菜单
		firstMenuListViewAdapter=new MenuItemAdapter(context,menuItem,
				R.drawable.choose_item_selected,
				R.drawable.choose_area_item_selector);
		firstMenuListViewAdapter.setTextSize(17);
		firstMenuListViewAdapter.setSelectedPositionNoNotify(firstPosition, menuItem);
		firstMenuListView.setAdapter(firstMenuListViewAdapter);
		firstMenuListViewAdapter.setOnItemClickListener(new MenuItemAdapter.OnItemClickListener(){
			@Override
			public void onItemClick(View view,int position){
				//选择主菜单，清空原本子菜单内容，增加新内容
				secondItem.clear();
				secondItem=getSecondItem(menuItem.get(position).getCode());
				if(secondItem!=null){
					LogUtil.i("wer",""+secondItem.size());
				}
				//通知适配器刷新
				secondMenuListViewAdapter.notifyDataSetChanged();
				secondMenuListViewAdapter.setSelectedPositionNoNotify(0, secondItem);
				
				thirdItem.clear();
				thirdItem=getThirdItem(secondItem.get(0).getCode());
				
				//通知适配器刷新
				thirdMenuListViewAdapter.notifyDataSetChanged();
				thirdMenuListViewAdapter.setSelectedPositionNoNotify(0, thirdItem);
			}
		});
		
		//初始化二级主菜单
		secondItem=getSecondItem(menuItem.get(firstPosition).getCode());
		LogUtil.i("wer", secondItem.get(secondPosition).toString());
		thirdItem=getThirdItem(secondItem.get(secondPosition).getCode());
		secondMenuListViewAdapter=new MenuItemAdapter(context,secondItem,
				R.drawable.choose_item_selected,
				R.drawable.choose_area_item_selector);
		secondMenuListViewAdapter.setTextSize(15);
		secondMenuListViewAdapter.setSelectedPositionNoNotify(secondPosition, secondItem);
		secondMenuListView.setAdapter(secondMenuListViewAdapter);
		secondMenuListViewAdapter.setOnItemClickListener(new MenuItemAdapter.OnItemClickListener() {
			
			@Override
			public void onItemClick(View view, int position) {
				// 选择主菜单，请客原本子菜单内容 ，增加新内容
				thirdItem.clear();
				thirdItem=getThirdItem(secondItem.get(position).getCode());
				BaseAdapter thirdItemMenuListViewAdapter;
				//通知适配器刷新
				thirdMenuListViewAdapter.notifyDataSetChanged();
				thirdMenuListViewAdapter.setSelectedPositionNoNotify(0, thirdItem);
				
			}
		});
		//初始化三级主菜单
		thirdItem=getThirdItem(secondItem.get(secondPosition).getCode());
		thirdMenuListViewAdapter=new MenuItemAdapter(context,thirdItem,
				R.drawable.choose_item_right,
				R.drawable.choose_plate_item_selector);
		thirdMenuListViewAdapter.setTextSize(13);
		thirdMenuListViewAdapter.setSelectedPositionNoNotify(thirdPosition,thirdItem);
		thirdMenuListView.setAdapter(thirdMenuListViewAdapter);
		thirdMenuListViewAdapter.setOnItemClickListener(new MenuItemAdapter.OnItemClickListener() {
			
			@Override
			public void onItemClick(View view, int position) {
				Area menuItem=thirdItem.get(position);
				if(mOnSelectListener!=null){
					mOnSelectListener.getValue(menuItem);
				}
				LogUtil.e(TAG,menuItem.toString());
			}
		});
		//设置默认选择
		setDefaultSelect();
	}
	public ArrayList<Area>getSecondItem(String pcode){
		ArrayList<Area>list=dbHelper.getCity(pcode);
		return list;
	}
	
	public ArrayList<Area>getThirdItem(String pcode){
		ArrayList<Area>list=dbHelper.getDistrict(pcode);
		return list;
	}
	public void setDefaultSelect(){
		firstMenuListView.setSelection(firstPosition);
		secondMenuListView.setSelection(secondPosition);
		thirdMenuListView.setSelection(thirdPosition);
	}
	public void setCascadingMenuViewOnSelectListener(CascadingMenuViewOnSelectListener onSelectListener){
		mOnSelectListener=onSelectListener;
	}
}







