package com.loan.exception;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class ApiError {

    private int statusCode;
    private String errorMessage;
//	public int getStatusCode() {
//		return statusCode;
//	}
//	public void setStatusCode(int statusCode) {
//		this.statusCode = statusCode;
//	}
//	public String getErrorMessage() {
//		return errorMessage;
//	}
//	public void setErrorMessage(String errorMessage) {
//		this.errorMessage = errorMessage;
//	}
}
