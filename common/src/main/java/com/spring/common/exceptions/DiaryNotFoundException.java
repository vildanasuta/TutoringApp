package com.spring.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Exception for diary not being found
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Diary not found!")
public class DiaryNotFoundException extends RuntimeException{
}
