package kr.or.ddit.vo;

public class FileVO {

	private String type;
	private String name;
	private String path;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "FileVO [type=" + type + ", name=" + name + ", path=" + path + "]";
	}
	public FileVO(String type, String name, String path) {
		super();
		this.type = type;
		this.name = name;
		this.path = path;
	}
	public FileVO() {
	}
}
