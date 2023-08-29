package com.darknights.devigation.global.exception.assertion;

import com.darknights.devigation.global.exception.InvalidParameterException;
import com.darknights.devigation.global.exception.ResourceNotFoundException;
import com.darknights.devigation.global.exception.UserNotFoundException;
import org.junit.Assert;
import org.springframework.validation.Errors;

import java.util.Optional;

public class ExceptionAssert extends Assert {

    public static void isValidParameter(Errors errors){
        if(errors.hasErrors()){
            throw new InvalidParameterException(errors.getObjectName() + " 파라미터에 잘못된 값으로 요청되었습니다.", errors);
        }
    }

    public static void isUserExist(Object object) {
        if(object == null) {
            throw new UserNotFoundException("사용자가 존재하지 않습니다.");
        }
    }

    public static void isObjectNull(Object object){
        if(object == null){
            throw new ResourceNotFoundException("해당 값이 존재하지 않습니다.");
        }
    }

    public static void isOptionalPresent(Optional<?> value){
        if(!value.isPresent()){
            throw new ResourceNotFoundException("잘못된 요청 데이터 입니다.");
        }
    }

}
