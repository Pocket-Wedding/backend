package pocket.backend.common.validator;

import pocket.backend.common.exceptions.InvalidParameterException;
import pocket.backend.common.exceptions.ErrorCode;
public class NameValidator {

    public static void isValidLocationName(String locationName){
        if (locationName.isEmpty()){
            throw new InvalidParameterException(ErrorCode.EMPTY_LOCATION_NAME);
        }
        if (locationName.trim().isEmpty()){
            throw new InvalidParameterException(ErrorCode.INVALID_LOCATION_NAME);
        }
    }

    public static void isValidCategoryName(String categoryName){
        if (categoryName.isEmpty()){
            throw new InvalidParameterException(ErrorCode.EMPTY_CATEGORY_NAME);
        }
        if (categoryName.trim().isEmpty()){
            throw new InvalidParameterException(ErrorCode.INVALID_CATEGORY_NAME);
        }
    }

    public static void isValidCompanyName(String companyName){
        if (companyName.isEmpty()){
            throw new InvalidParameterException(ErrorCode.EMPTY_COMPANY_NAME);
        }

        if (companyName.trim().isEmpty()){
            throw new InvalidParameterException(ErrorCode.INVALID_COMPANY_NAME);
        }
    }
}
