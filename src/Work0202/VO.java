package Work0202;

public class VO {
	private String name;
	// VO는 값만 가지게 되지 여기에서 디자인 객체를 받아서 값을 얻지 는 않습니다.
	public String getName(Design d) {
		name = d.getJtfName().getText(); //이코드의 작성위치가 올바르지 않습니다.
		return name;
	}

	

	
	
	

	
}
