package day0203;

public class Singleton {
	private static Singleton single;
	/**
	 * Ŭ���� ���ο����� ��üȭ �� �� �ֵ��� ���� �����ڸ� private���� �����Ѵ�.
	 */
	private Singleton() {
		 
		System.out.println("Singleton ������");
	}//Singleton
	
	/**
	 * ��ü�� �ϳ��� �����ϴ� �ϳ��� ��ü�� ��ȯ�ϴ� ��
	 * @return
	 */
	public static Singleton getInstance() {
		if(single == null) {
		 single = new Singleton();
		}//end if
		return single;
	}//getInstance

}//class
