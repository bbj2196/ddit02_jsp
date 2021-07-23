package kr.or.ddit.vo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.contraints.FileMime;
import kr.or.ddit.validate.contraints.TelNumber;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.coobird.thumbnailator.Thumbnails;



/**
 * 회원관리를 위한 Domain Layer
 * MyBatis (DataMapper, ORM)을 이용한 테이블 조인방법
 *  	ex) 회원 정보 상세 조회시 구매 상품 목록 동시 조회
 * 1. 메인 테이블(Member)를 기준으로 조인한 댓상이 되는 테이블과의 관계파악
 *  	MEMBER(1) : PROD(N)
 * 2. 각 테이블의 스키마 구조에 따라 VO 설계
 *  	MemberVO, ProdVO
 * 3. VO 사이에 테이블간 관계성 반영
 *  	1:N - has many 관계
 *  	ex) MemberVO has many ProdVO
 *  	1:1 - has a 관계
 *  	ex) ProdVO has a BuyerVO
 * 4. resultType 대신 resultMap으로 수동 바인딩 설정
 *  	1:N - has many -> collection
 *   	1:1 - has a -> assocation
 * @author PC-13
 *
 */
@Data
@EqualsAndHashCode(of="memId")
@ToString(exclude= {"memRegno1","memPass","memRegno2","memImg","memImage","memThumbnail"})
@NoArgsConstructor
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@Builder
public class MemberVO implements Serializable,HttpSessionBindingListener{
	
	public MemberVO(String memId, String memPass) {
		super();
		this.memId = memId;
		this.memPass = memPass;
	}
	
	@NotBlank(groups= {Default.class,DeleteGroup.class})
	private String memId;
	@NotBlank
	@Size(min=4,max=12)
	@NotBlank(groups= {Default.class,DeleteGroup.class})
	private String memPass;
	@NotBlank
	private String memName;
	@NotBlank(groups=InsertGroup.class)
	private String memRegno1;
	@NotBlank(groups=InsertGroup.class)
	private String memRegno2;
	@Pattern(regexp="\\d{4}-\\d{2}-\\d{2}")
	private String memBir;
	private String memZip;
	private String memAdd1;
	private String memAdd2;
	@TelNumber
	private String memHometel;
	@TelNumber
	private String memComtel;
	@NotBlank
	@TelNumber
	private String memHp;
	@NotBlank
	@Email
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	private String memMemorialday;
	private Integer memMileage;
	private Boolean memDelete; // null 값 처리를 위해 wraper클래스 사용
	private Set<ProdVO> prodList; // has many 관계 - 1: N
	public String getMemTest() {
		return "테스트";
	}
	private String memRole;
	private String memImg;
	@FileMime(mime="image/")
	private transient MultipartFile memImage;
	private byte[] memThumbnail;
	
	public void setMemImage(MultipartFile memImage) throws IOException {
		if(memImage ==null || memImage.isEmpty()) {
			return;
		}
		this.memImage = memImage;
//		this.memImg = memImage.getBytes();
		File memFolder = new File("d:/content");
		String imageName = UUID.randomUUID().toString();
		File savedFile = new File(memFolder, imageName);
		
		memImage.transferTo(savedFile);
		memImg = savedFile.getPath();
		
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();) {

			Thumbnails.of(savedFile).size(100, 100).toOutputStream(bos);
			memThumbnail = bos.toByteArray();
		}
	}
	public String getBase64Img() {
		if(memImg==null) {
			return null;
		}
		File origin = new File(memImg);
		if(!origin.exists()) {
			return null;
		}
		try(
				FileInputStream fis = new FileInputStream(origin);
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				){
			byte[] buffer = new byte[1024];
			int idx=-1;
			while((idx =fis.read(buffer))!=-1) {
				bos.write(buffer, 0, idx);
			}
			return Base64.encodeBase64String(bos.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public String getBase64Thumb() {
		return Base64.encodeBase64String(memThumbnail);
	}
//	public String makeThumnail() {
//		if(memImg == null || memImg.length() <=0) {return null;}
//		try(
//			ByteArrayOutputStream bos = new ByteArrayOutputStream();
//		) {
//			
//			File origin = new File(memImg);
//			Thumbnails.of(origin).size(100, 100).toOutputStream(bos);
//			byte[] thumb = bos.toByteArray();
//			return Base64.encodeBase64String(thumb);
//		} catch (IOException e) {
//			throw new RuntimeException(e); 
//		}
//	}
	
	
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		String attrName = event.getName();
		if("authMember".equals(attrName)) {
			ServletContext application = event.getSession().getServletContext();
			LinkedHashMap<String, MemberVO> currentUsers=(LinkedHashMap<String, MemberVO>) application.getAttribute("currentUserList");
			currentUsers.put(memId, this);
			
		}
		
	}
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		String attrName = event.getName();
		if("authMember".equals(attrName)) {
			ServletContext application = event.getSession().getServletContext();
			LinkedHashMap<String, MemberVO> currentUsers=(LinkedHashMap<String, MemberVO>) application.getAttribute("currentUserList");
			currentUsers.remove(memId);
			
		}		
	}
}
