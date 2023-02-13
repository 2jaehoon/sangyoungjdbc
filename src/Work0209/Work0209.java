package Work0209;

public class Work0209 {

	public static void main(String[] args) {
		StringBuilder create = new StringBuilder();
		int a = 1;
		int b = 2;
		int c = 3;
		int d = 4;
		int e = 5;
		int f = 6;
		if(a==1) {
			create.append("Create table jogga(\n ");
			if(b==2) {
				create.append("name = sibal ");
			}
			if(c==3) {
				create.append("varchar2");
			}
			if(d==4) {
				create.append("(30) ");
			}
			if(e==5) {
				create.append("pk 준비중인 이재건 선수 ");
			}
			if(f==6) {
				create.append("primary key").append(" ");
			}
			
			
			
			create.append("\n)");
		}
		
		System.out.println(create);

	}

}
