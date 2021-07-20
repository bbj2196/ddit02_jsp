package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.PagingVO;

public interface FreeBoardService {

	/**
	 * {@link FreeBoardVO}로 게시글 생성
	 * @param board
	 * @return 성공, 실패(중복) 
	 */
	public ServiceResult createBoard(FreeBoardVO board);
	/**
	 * 
	 * {@link PagingVO}의 검색조건으로 검색된 게시물의 갯수를 구하는 메서드
	 * @param paging
	 * @return 검색된 게시물의 갯수
	 */
	public int retriveBoardCount(PagingVO<FreeBoardVO> paging);
	/**
	 * {@link PagingVO}의 검색조건으로 검색된 게시물들을 반환하는 메서드
	 * @param paging 검색조건이 포함된 페이징
	 * @return {@link FreeBoardVO}로 이루어진 {@link List} 리턴, 실패시 null 반환
	 */
	public List<FreeBoardVO> retriveBoardList(PagingVO<FreeBoardVO> paging);
	/**
	 * 게시물 번호로 하나의 게시물을 찾는 메서드
	 * @param boNo 게시물 번호
	 * @return 게시물번호로 조회된 {@link FreeBoardVO}, 없을시 {@link DataNotFoundException}
	 */
	public FreeBoardVO retriveBoard(int boNo);
	/**
	 * 게시물을 수정하는 메서드
	 * @param board 수정데이터가 들어있는 {@link FreeBoardVO}
	 * @return 성공,실패(게시글없음, 비밀번호 틀림)
	 */
	public ServiceResult modifyBoard(FreeBoardVO board);
	/**
	 * 게시물을 삭제하는 메서드
	 * @param board 비밀번호와 게시물번호가 포함된 {@link FreeBoardVO}
	 * @return 성공,실패(게시글 없음, 비밀번호 틀림)
	 */
	public ServiceResult removeBoard(FreeBoardVO board);
	
	/**
	 * 파일을 다운로드 할 수 있도록 {@link AttatchVO}를 반환하는 메서드
	 * @param attNo 다운로드할 파일의 번호
	 * @return 실패시 null 반환
	 */
	public AttatchVO download(int attNo);
	public static enum CountType{RECOMMEND,REPORT,SEE}
	/**
	 * 게시물의 추천수,신고수를 증가시키기 위한 메서드
	 * @param boNo 해당 게시물의 번호
	 * @param type RECOMMEND = 추천, REPORT = 신고
	 * @return 성공,실패(게시글없음, 그런타입없음), 없을시 {@link DataNotFoundException}
	 */
	public ServiceResult incrementCount(int boNo,CountType type);
}
