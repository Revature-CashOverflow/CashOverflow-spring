package com.revature.model;

import lombok.Data;

@Data
public class FundTransfer {
	String transferFromAccount;
	String transferToAccount;
	Double transferAmount;
}
