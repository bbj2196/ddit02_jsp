package kr.or.ddit.vo;

import java.io.File;

public class FancyTreeNodeAdapter extends File implements FancyTreeNode{

	private String key;
	
	public FancyTreeNodeAdapter(File adaptee,String relativePath) {
		super(adaptee.getAbsolutePath());
		this.key = relativePath;
	}

	@Override
	public String getTitle() {
		return this.getName();
	}

	@Override
	public boolean isFolder() {
		return isDirectory();
	}

	@Override
	public String getKey() {
		return this.key;
	}
	
	@Override
	public int compareTo(File pathname) {
		boolean mine = isDirectory();
		boolean other = pathname.isDirectory();
		
		if(mine ^ other) { // ^ 는 != 와 같다
			return mine?-1:1;
			
		}else {
			return super.compareTo(pathname);
			
		}
	}

	@Override
	public boolean isLazy() {
		
		return isFolder();
	}
	
}
