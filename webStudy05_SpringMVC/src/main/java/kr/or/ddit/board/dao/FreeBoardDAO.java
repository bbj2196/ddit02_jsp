package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.PagingVO;

/**
 * FreeBoard 테이블을 대상으로 한 Persistence Layer
 * @author PC-13
 *
 */
public interface FreeBoardDAO {

	/**
	 * 게시글 생성
	 * @param board
	 * @return 생성한 갯수
	 */
	public int insertBoard(FreeBoardVO board);
	/**
	 * 모든 게시글 조회
	 * @param pagingVO
	 * @return  조회된 게시글 갯수
	 */
	public int selectTotalRecord(PagingVO<FreeBoardVO> pagingVO);
	/**
	 * 게시글 검색
	 * @param pagingVO 검색조건이 포함되어있는 paging
	 * @return 검색조건에 부합하는 게시글 리스트
	 */
	public List<FreeBoardVO> selectBoardList(PagingVO<FreeBoardVO> pagingVO);
	/**
	 * 게시글번호로 게시글  상세 검색
	 * @param boNo 검색할 게시글 번호
	 * @return 검색된 게시글 VO
	 */
	public FreeBoardVO selectBoard(int boNo);
	/**
	 * 게시글 수정
	 * @param board 수정할 게시글의 정보
	 * @return 수정된 게시글 갯수
	 */
	public int updateBoard(FreeBoardVO board);
	/**
	 * 게시글 번호로 삭제
	 * @param boNo 삭제할 게시글 번호
	 * @return 삭제된 게시글 갯수
	 */
	public int deleteBoard(int boNo);
	/**
	 * 조회수 증가
	 * @param boNo
	 * @return
	 */
	public int incrementHit(int boNo);
	/**
	 * 추천수 증가
	 * @param boNo
	 * @return
	 */
	public int incrementRec(int boNo);
	/**
	 * 신고수증가
	 * @param boNo
	 * @return
	 */
	public int incrementRep(int boNo);
}
