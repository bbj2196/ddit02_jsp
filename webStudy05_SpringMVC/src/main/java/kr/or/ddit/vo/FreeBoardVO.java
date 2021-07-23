package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.groups.InsertGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="boNo")
@Valid
public class FreeBoardVO implements Serializable{
	
	@NotNull
	private Integer boNo;
	@NotBlank(groups=InsertGroup.class)
	private String boTitle;
	@NotBlank(groups=InsertGroup.class)
	private String boWriter;
	private String boDate;
	private Integer boRep;
	private Integer boHit;
	private Integer boRec;
	private Integer boParent;
	public void setBoParent(Integer boParent) {
		if(boParent == 0) this.boParent = null;
		else this.boParent = boParent;
	}
	@NotBlank(groups=InsertGroup.class)
	private String boIp;
	private String boEmail;
	@NotBlank
	private String boPass;
	private String boContent;
	
	private MultipartFile[] boFiles; // 추가할때
	public void setBoFiles(MultipartFile[] boFiles) {
		this.boFiles = boFiles;
		if(boFiles == null) {return;}
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
	private int startAttNo;
}
