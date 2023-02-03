package day0203;

public class Singleton {
	private static Singleton single;
	/**
	 * 클래스 내부에서만 객체화 될 수 있도록 접근 지정자를 private으로 설정한다.
	 */
	private Singleton() {
		 
		System.out.println("Singleton 생성자");
	}//Singleton
	
	/**
	 * 객체를 하나로 유지하는 하나의 객체를 반환하는 일
	 * @return
	 */
	public static Singleton getInstance() {
		if(single == null) {
		 single = new Singleton();
		}//end if
		return single;
	}//getInstance

}//class
