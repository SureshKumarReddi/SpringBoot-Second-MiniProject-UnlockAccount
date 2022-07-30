package com.suresh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suresh.bindings.UnlockAccountRequest;
import com.suresh.constants.AppConstants;
import com.suresh.models.UserEntity;
import com.suresh.props.AppsProps;
import com.suresh.repositories.UserDetailsRepository;
import com.suresh.service.UnlockAccountService;

@Service
public class UnlockAccountServiceImpl implements UnlockAccountService {

	@Autowired
	private UserDetailsRepository repository;
	@Autowired
	private AppsProps props;

	@Override
	public String unlockAccount(UnlockAccountRequest request) {
		UserEntity findByUserEmailAndUserPassword = repository.findByUserEmailAndUserPassword(request.getEmail(),
				request.getTempPwd());
		if (findByUserEmailAndUserPassword != null) {
			findByUserEmailAndUserPassword.setUserPassword(request.getConfirmPwd());
			findByUserEmailAndUserPassword.setUserAccountStatus(AppConstants.ACCOUNT_UNLOCKED);

			repository.save(findByUserEmailAndUserPassword);
			return props.getMessages().get(AppConstants.ACCOUNT_UNLOCKED); // here we are reading propeerties from yaml
																			// and reading string literals from
																			// AppConstants file.
			// return "User Account Unlocked Successfully";
		}
		return props.getMessages().get(AppConstants.INVALID_EMAIL_TEMPORARYPWD);
		// return "Given email and temporary password is incorrect";
	}

}
