package com.suresh.bindings;

import lombok.Data;

@Data
public class UnlockAccountRequest {

	private String email;
	private String tempPwd;
	private String newPwd;
	private String confirmPwd;
}
