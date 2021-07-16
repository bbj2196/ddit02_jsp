package kr.or.ddit.board.dao;

import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.FreeBoardVO;

/**
 * Attatch 테이블을 대상으로한 persistence Layer
 * @author PC-13
 *
 */
public interface AttatchDAO {

	/**
	 * 게시물하나에 있는 모든 첨부파일을 추가함
	 * @param board 첨부파일들이 포함된 VO
	 * @return 추가된 첨부파일들의 갯수
	 */
	public int insertAttatches(FreeBoardVO board);
	/**
	 * 첨부파일의 번호와 같은 VO를 가져옴
	 * @param attNo
	 * @return
	 */
	public AttatchVO selectAttach(int attNo);
	/**
	 * {@link FreeBoardVO}에 있는 deleteAttNos에 포함된 첨부파일번호를 가지고 첨부파일을 삭제한다
	 * @param board
	 * @return
	 */
	public int deleteAttaches(FreeBoardVO board);
	/**
	 * 해당 게시글번호의 모든 첨부파일을 지운다
	 * @param boNo
	 * @return
	 */
	public int deleteAll(int boNo);
	/**
	 * 다운로드수 증가
	 * @param attNo
	 * @return
	 */
	public int increamentDownCount(int attNo);
}
