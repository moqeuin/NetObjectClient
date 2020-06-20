package data;

import dto.NetData;

public class SingletonClass {
	private static SingletonClass sc = new SingletonClass();
	public NetData nd = null; 
	
	private SingletonClass(){
		nd = new NetData(1,"id",1);
		// 객체를 생성하면 동시에 NetDate의 인스턴스를 생성, 번호와 턴을 1,1로 정한 뒤 서버에서 다시 1,2로 지정.
	}
	
	public static SingletonClass getInstance() {	
		return sc;
	}
}