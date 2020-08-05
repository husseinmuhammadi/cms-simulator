package com.asan.cms.rpc;

public interface CardGrpc {

    int registerCard(String mobileNo, int group);

    int getCardInfo(String mobileNo, int group);
}
