package com.asan.cms.rpc;

public abstract class GrpcTransaction {
    protected static GrpcTransactionGenerator grpcTransactionGenerator;

    static {
        grpcTransactionGenerator = GrpcTransactionGenerator.instance();
    }
}
