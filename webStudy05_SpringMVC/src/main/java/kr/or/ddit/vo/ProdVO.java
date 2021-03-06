package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.contraints.FileMime;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 상품관리 Domain Layer
 * @author PC-13
 *
 */
@Data
@EqualsAndHashCode(of="prodId")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude="prodImage")
public class ProdVO implements Serializable{
	@NotBlank(groups= {DeleteGroup.class,UpdateGroup.class})
	private String prodId;
	
	@NotBlank(groups=InsertGroup.class)
	private String prodName;
	
	@NotBlank(groups=InsertGroup.class)
	@Size(max=4,min=4)
	@Pattern(regexp="^P.*")
	private String prodLgu;

	@NotBlank(groups=InsertGroup.class)
	@Size(min=6,max=6)
	private String prodBuyer;
	
	@NotBlank(groups=InsertGroup.class)
	private String prodOutline;
	
	@NotBlank(groups=InsertGroup.class)
	private String prodImg; // DB communication
	
	@NotNull(groups=InsertGroup.class)

	private transient MultipartFile prodImage; // Client communication
	
	
	public void setProdImage(MultipartFile prodImage) {
		if(prodImage != null && !prodImage.isEmpty()) {
			this.prodImage = prodImage;
			this.prodImg = UUID.randomUUID().toString();
		}
	}
	@NotNull(groups=InsertGroup.class)
	@Max(999999999)
	private Integer prodCost;
	
	@NotNull(groups=InsertGroup.class)
	private Integer prodPrice;
	
	@NotNull(groups=InsertGroup.class)
	private Integer prodSale;
	
	@NotNull(groups=InsertGroup.class)
	private Integer prodTotalstock;
	
	@NotNull(groups=InsertGroup.class)
	private Integer prodProperstock;
	
	private String prodInsdate;
	private String lprodNm;//////
	private String prodDetail;
	private String prodSize;
	private BuyerVO buyer;////// has a 관계 - 1:1
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private Integer prodQtyin;
	private Integer prodQtysale;
	private Integer prodMileage;

	private List<MemberVO> memberList;
}
