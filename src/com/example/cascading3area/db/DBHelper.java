package com.example.cascading3area.db;

import java.util.ArrayList;

import com.example.cascading3area.model.Area;
import com.example.cascading3area.util.LogUtil;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper {
	private SQLiteDatabase db;
	private Context context;
	private DBManager dbm;
	public DBHelper(Context context){
		super();
		this.context=context;
		dbm=new DBManager(context);
	}

	public ArrayList<Area>getCity(String pcode){
		dbm.openDatabase();
		db=dbm.getDatabase();
		ArrayList<Area>list=new ArrayList<Area>();
		try{
			String sql="select * from city where pcode='"+pcode+"'";
			Cursor cursor=db.rawQuery(sql, null);
			cursor.moveToFirst();
			//可以试用do｛...｝while（cursor.moveToNext()）
			while(!cursor.isLast()){
				String code=cursor.getString(cursor.getColumnIndex("code"));
				byte bytes[]=cursor.getBlob(2);
				String name=new String (bytes,"gbk");
				Area area=new Area();
				area.setName(name);
				area.setCode(code);
				area.setPcode(pcode);
				list.add(area);
				cursor.moveToNext();
			}
			String code=cursor.getString(cursor.getColumnIndex("code"));
			byte bytes[]=cursor.getBlob(2);
			String name=new String (bytes,"gbk");
			Area area=new Area();
			area.setName(name);
			area.setCode(code);
			area.setPcode(pcode);
			list.add(area);
					
		}catch(Exception e){
			return null;
		}
		dbm.closeDatabase();
		db.close();
		return list;
	}
	
	public ArrayList<Area>getProvince(){
		dbm.openDatabase();
		db=dbm.getDatabase();
		ArrayList<Area> list=new ArrayList<Area>();
		try{
			String sql="select * from province";
			Cursor cursor=db.rawQuery(sql, null);
			cursor.moveToFirst();
			while(!cursor.isLast()){
				//得到省级代码
				String code=cursor.getString(cursor.getColumnIndex("code"));
				//从cursor中获取序号2的二进制大对象（Blob），以字节数组的形式返回，
	        	//然后把这个数据转化成gbk格式的字符串，就是省名
				byte bytes[]=cursor.getBlob(2);
				String name=new String(bytes,"gbk");
				Area area=new Area();
				area.setName(name);
				area.setCode(code);
				list.add(area);
				cursor.moveToNext();
			}
			String code=cursor.getString(cursor.getColumnIndex("code")); 
	        byte bytes[]=cursor.getBlob(2); 
	        String name=new String(bytes,"gbk");
	        Area area=new Area();
	        area.setName(name);
	        area.setCode(code);
	        list.add(area);
		}catch(Exception e){
			return null;
		}
		dbm.closeDatabase();
		db.close();
		return list;
	}
	
	public ArrayList<Area>getDistrict(String pcode){
		dbm.openDatabase();
		db=dbm.getDatabase();
		ArrayList<Area>list=new ArrayList<Area>();
		try{
			String sql="select * from district where pcode='"+pcode+"'";
			Cursor cursor=db.rawQuery(sql,null);
			if(cursor.moveToFirst()){
				while(!cursor.isLast()){
					String code=cursor.getString(cursor.getColumnIndex("code"));
					byte bytes[]=cursor.getBlob(2);
					String name=new String (bytes,"gbk");
					Area area=new Area();
					area.setName(name);
					area.setPcode(code);
					list.add(area);
					cursor.moveToNext();
				}
				String code = cursor.getString(cursor.getColumnIndex("code"));
				byte bytes[] = cursor.getBlob(2);
				String name = new String(bytes, "gbk");
				Area area = new Area();
				area.setName(name);
				area.setPcode(code);
				list.add(area);
			}
		}catch(Exception e){
			LogUtil.i("wer",e.toString());
		}
		dbm.closeDatabase();
		db.close();
		return list;
	}
}
