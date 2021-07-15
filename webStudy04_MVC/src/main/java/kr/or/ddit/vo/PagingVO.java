package kr.or.ddit.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 페이징처리에 관련된 속성의 집합
 * @author PC-13
 *
 */
@NoArgsConstructor
@Getter
public class PagingVO<T> {
	
	public PagingVO(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}
	private int totalRecord;
	private int screenSize=10;
	private int blockSize=5;
	
	private int totalPage;
	private int currentPage=1;
	private int startRow;
	private int endRow;
	private int startPage;
	private int endPage;
	
	private SearchVO simpleSearch; //단순키워드검색
	private T detailSearch; // 상세검색
	private List<T>datalist;
	
	public void setDetailSearch(T detailSearch) {
		this.detailSearch = detailSearch;
	}
	public void setSimpleSearch(SearchVO simpleSearch) {
		this.simpleSearch = simpleSearch;
	}
	public void setDatalist(List<T> datalist) {
		this.datalist = datalist;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		this.totalPage = (int) Math.ceil(totalRecord/(double)screenSize);
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		endRow = currentPage * screenSize;
		startRow = endRow - (screenSize - 1);
		startPage = blockSize * ((currentPage-1)/blockSize) +1;
		endPage = startPage+(blockSize-1);
	}
	private static final String LINKPTRN = "<a class='pageLink' href='#' data-page='%d'>%s</a>\n";
	public String getPagingHTML() {
//		<a href="?page=1">1</a>
		StringBuffer html = new StringBuffer();
		
		if(startPage>1) {
			html.append(String.format(LINKPTRN,startPage-1,"이전"));
		}
		
		endPage = endPage > totalPage ? totalPage:endPage;
		
		for(int page = startPage;page<=endPage;page++) {
			if(page == currentPage) {
				html.append("["+page+"]");
			}else {
			html.append(String.format(LINKPTRN,page,page));
			}
		}
		if(endPage<totalPage) {
			html.append(String.format(LINKPTRN,endPage+1,"다음"));
		}
		return html.toString();
	}
}
