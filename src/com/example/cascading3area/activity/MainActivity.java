package com.example.cascading3area.activity;

import java.util.ArrayList;

import com.example.cascading3area.R;

import com.example.cascading3area.cascading.CascadingMenuFragment;
import com.example.cascading3area.cascading.CascadingMenuPopWindow;
import com.example.cascading3area.db.DBHelper;
import com.example.cascading3area.interfaces.CascadingMenuViewOnSelectListener;
import com.example.cascading3area.model.Area;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnClickListener {

	ArrayList<Area> provinceList;
	//两级联动菜单数据
	private CascadingMenuFragment cascadingMenuFragment =null;
	private CascadingMenuPopWindow cascadingMenuPopWindow=null;
	private Button menuViewPopWindow;
	private Button menuViewFragment;
	private DBHelper dbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		menuViewPopWindow=(Button)findViewById(R.id.menuViewPopWindow);
		menuViewFragment=(Button)findViewById(R.id.menuViewFragment);
		//向三级menu添加地区数据
		dbHelper=new DBHelper(this);
		provinceList=dbHelper.getProvince();
		menuViewPopWindow.setOnClickListener(this);
		menuViewFragment.setOnClickListener(this);
	}

	public void showFragmentMenu(){
		FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
		fragmentTransaction.setCustomAnimations(R.anim.short_menu_pop_in, R.anim.short_menu_pop_out);
		if(cascadingMenuFragment==null){
			cascadingMenuFragment=CascadingMenuFragment.getInstance();
			cascadingMenuFragment.setMenuItems(provinceList);
			cascadingMenuFragment.setMenuViewOnSelectListener(new NMCascadingMenuViewOnSelectListener());
			fragmentTransaction.replace(R.id.liner, cascadingMenuFragment);
		}else{
			fragmentTransaction.remove(cascadingMenuFragment);
			cascadingMenuFragment=null;
		}
		fragmentTransaction.commit();
	}
	private void showPopMenu(){
		if(cascadingMenuPopWindow==null){
			cascadingMenuPopWindow=new CascadingMenuPopWindow(getApplicationContext(),provinceList);
			cascadingMenuPopWindow.setMenuViewOnSelectListener(new NMCascadingMenuViewOnSelectListener());
			cascadingMenuPopWindow.showAsDropDown(menuViewPopWindow,5,5);
		}else if(cascadingMenuPopWindow!=null&&cascadingMenuPopWindow.isShowing()){
			cascadingMenuPopWindow.dismiss();
		}else{
			cascadingMenuPopWindow.showAsDropDown(menuViewPopWindow,5,5);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	//级联菜单回调接口
	class NMCascadingMenuViewOnSelectListener implements CascadingMenuViewOnSelectListener{

		@Override
		public void getValue(Area area) {
			cascadingMenuFragment=null;
					Toast.makeText(getApplicationContext(),""+area.getName(), Toast.LENGTH_SHORT).show();
		}	
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.menuViewFragment:
			showFragmentMenu();
			break;
		case R.id.menuViewPopWindow:
			showPopMenu();
			break;
		}
		
	}
}






