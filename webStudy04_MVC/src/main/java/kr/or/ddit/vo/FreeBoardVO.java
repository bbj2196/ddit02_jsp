package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.multipart.MultipartFile;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="boNo")
public class FreeBoardVO implements Serializable{
	private Integer boNo;
	private String boTitle;
	private String boWriter;
	private String boDate;
	private Integer boRep;
	private Integer boHit;
	private Integer boRec;
	private Integer boParent;
	private String boIp;
	private String boEmail;
	private String boPass;
	private String boContent;
	
	private MultipartFile[] boFiles; // 추가할때
	public void setBoFiles(MultipartFile[] boFiles) {
		this.boFiles = boFiles;
		if(boFiles == null) {
			return;
		}
		this.attatchList = new ArrayList<>();
		for (MultipartFile file : boFiles) {
			if(file.isEmpty()) {
				continue;
			}
			this.attatchList.add(new AttatchVO(file));
		}
	}
	private List<AttatchVO> attatchList;
	private int[] delAttNos; // 지우려고하는 파일의 번호들
	private List<FreeReplyVO> replyList;
}
