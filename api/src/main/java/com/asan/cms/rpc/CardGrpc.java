package com.asan.cms.rpc;

import com.asan.cms.dto.CardIssueResponse;
import com.asan.cms.dto.CardStatusInquiryResponse;

public interface CardGrpc {

    CardIssueResponse registerCard(String mobileNo, int group);

    CardStatusInquiryResponse inquiryStatus(String mobileNo, int group);
}
