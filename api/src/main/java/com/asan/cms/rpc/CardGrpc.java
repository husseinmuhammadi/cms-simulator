package com.asan.cms.rpc;

import com.asan.cms.dto.CardStatusInquiryResponse;

public interface CardGrpc {

    int registerCard(String mobileNo, int group);

    CardStatusInquiryResponse inquiryStatus(String mobileNo, int group);
}
