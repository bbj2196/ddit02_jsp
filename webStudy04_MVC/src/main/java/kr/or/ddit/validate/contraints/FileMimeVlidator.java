package kr.or.ddit.validate.contraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import kr.or.ddit.multipart.MultipartFile;

public class FileMimeVlidator implements  ConstraintValidator<FileMime, MultipartFile> {

	private FileMime annotation;

	@Override
	public void initialize(FileMime constraintAnnotation) {
		annotation = constraintAnnotation;
	}
	
	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		if(value == null || value.isEmpty()) {
			return false;
		}
		String contentType = value.getContentType();
		if(contentType != null&&contentType.startsWith(annotation.mime())) {
			return true;
		}
				
		return false;
	}

}
