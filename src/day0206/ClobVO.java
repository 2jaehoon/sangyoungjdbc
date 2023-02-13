package day0206;

public class ClobVO {
	private String title, content, writer, input_date;

	public ClobVO() {
	}

	public ClobVO(String title, String content, String writer, String input_date) {
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.input_date = input_date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getInputdate() {
		return input_date;
	}

	public void setInput_date(String input_date) {
		this.input_date = input_date;
	}

	@Override
	public String toString() {
		return "ClobVO [title=" + title + ", content=" + content + ", writer=" + writer + ", input_date=" + input_date
				+ "]";
	}
	
	

}
