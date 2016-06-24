package com.example.cascading3area.model;

public class Area {
	private String code;
	private String name;
	private String pcode;
	public String getCode(){
		return code;
	}
	public void setCode(String code){
		this.code=code;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getPcode(){
		return code;
	}
	public void setPcode(String pcode){
		this.pcode=pcode;
	}
	public Area(){
		super();
	}	
	public String toString(){
		return "Area [code="+code+",name="+name+",pcode="+pcode+"]";
	}

}
