package com.asan.cms.rpc;

import com.asan.cms.grpc.*;
import com.asan.transaction.request.TransactionConditionTypeEnum;
import com.asan.transaction.request.TransactionProcessTypeEnum;
import com.asan.utils.string.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class GrpcTransactionGenerator {

    public static final Logger LOGGER = LoggerFactory.getLogger(GrpcTransactionGenerator.class);

    TransactionReferenceGenerator referenceGenerator = new TransactionReferenceGenerator();

    long terminalId = 1254;

    private static final GrpcTransactionGenerator INSTANCE;

    static {
        INSTANCE = new GrpcTransactionGenerator();
    }

    public static GrpcTransactionGenerator instance() {
        return INSTANCE;
    }

    public TransactionRequest depositTransaction(String pan, long amount) {
        return financialTransaction(TransactionProcessTypeEnum.Deposit, pan, amount);
    }

    public TransactionRequest cashoutTransaction(String pan, long amount) {
        return financialTransaction(TransactionProcessTypeEnum.Cashout, pan, amount);
    }

    public TransactionRequest paymentTransaction(String pan, long amount) {
        return financialTransaction(TransactionProcessTypeEnum.Payment, pan, amount);
    }

    public TransactionRequest purchaseTransaction(String pan, long amount) {
        return financialTransaction(TransactionProcessTypeEnum.Purchase, pan, amount);
    }

    public TransactionRequest balanceInquiryTransaction(String pan) {
        String description = new StringBuilder().append(TransactionProcessTypeEnum.Balance.name()).append(" from ").append(pan).toString();

        return TransactionRequest.newBuilder()
                .setProcess(TransactionProcessTypeEnum.Balance.getValue())
                .setCardNo(pan)
                .setRrn(referenceGenerator.getRandomRRN())
                .setRefTranId(referenceGenerator.getRandomRefTranId())
                .setGatewayId(40) // 130
                .setRefTranType(16)
                .setHostId(99) // 1193
                .setClientTime(Long.parseLong(StringUtils.formatDate(new Date())))
                .setTerminalId(terminalId)
                .setAcquireIIN(581672011L)
                .setUserId(0)
                .setCondition(TransactionConditionTypeEnum.CardPresent.getValue())
                .setAmount(1)  // TODO: this is a bug
                .setDescription(description)
                .setPaymentId("pay123")
                .setAgentMobileNo("09121234567")
                // .setPin("")
                .setEndpoint("gRPC")
                .build();
    }

    public StatementRequest statementTransaction(String pan) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        LocalDateTime now = LocalDateTime.now();
        Date start = Date.from(now.minusMonths(2).atZone(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());


        String description = new StringBuilder().append(TransactionProcessTypeEnum.Statement.name()).append(" from ").append(pan).toString();

        return StatementRequest.newBuilder()
                .setProcess(TransactionProcessTypeEnum.Statement.getValue())
                .setCardNo(pan)
                .setRrn(referenceGenerator.getRandomRRN())
                .setRefTranId(referenceGenerator.getRandomRefTranId())
                .setGatewayId(40) // 130
                .setRefTranType(16)
                .setHostId(99) // 1193
                .setClientTime(Long.parseLong(StringUtils.formatDate(new Date())))
                .setTerminalId(terminalId)
                .setAcquireIIN(581672011L)
                .setUserId(0)
                .setCondition(TransactionConditionTypeEnum.CardPresent.getValue())
                .setAmount(1)  // TODO: this is a bug
                .setDescription(description)
                .setPaymentId("pay123")
                .setAgentMobileNo("09121234567")
                // .setPin("")
                .setStartTime(sdf.format(start))
                .setFinishTime(sdf.format(end))
                .setMaxCount(100)
                .setLastTranId(0)
                .setEndpoint("gRPC")
                .build();
    }

    public FundTransferRequest fundTransferTransaction(String pan) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        LocalDateTime now = LocalDateTime.now();
        Date start = Date.from(now.minusMonths(2).atZone(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());

        String description = new StringBuilder().append(TransactionProcessTypeEnum.Transfer.name()).append(" from ").append(pan).toString();

        return FundTransferRequest.newBuilder()
                .setProcess(TransactionProcessTypeEnum.Transfer.getValue())
                .setCardNo(pan)
                .setRrn(referenceGenerator.getRandomRRN())
                .setRefTranId(referenceGenerator.getRandomRefTranId())
                .setGatewayId(40) // 130
                .setRefTranType(16)
                .setHostId(99) // 1193
                .setClientTime(Long.parseLong(StringUtils.formatDate(new Date())))
                .setTerminalId(terminalId)
                .setAcquireIIN(581672011L)
                .setUserId(0)
                .setCondition(TransactionConditionTypeEnum.CardPresent.getValue())
                .setAmount(1)  // TODO: this is a bug
                .setDescription(description)
                .setPaymentId("pay123")
                .setAgentMobileNo("09121234567")
                // .setPin("")
                .setDestCardNo("9832553325401928")
                .setDestRefTranType(0)
                .setDestDescription("1")
                .setEndpoint("gRPC")
                .build();
    }

    public CardInfoRequest grpcCardInfoTransaction(String mobileNo, int group) {
        return CardInfoRequest.newBuilder()
                .setMobile(mobileNo)
                .setGroup(group)
                .build();
    }

    public CardRegisterRequest grpcCardRegisterTransaction(String mobileNo, int group) {
        return CardRegisterRequest.newBuilder()
                .setMobile(mobileNo)
                .setGroup(group)
                .build();
    }

    TransactionRequest financialTransaction(TransactionProcessTypeEnum process, String pan, long amount) {
        String description = new StringBuilder().append(process.name()).append(" from ").append(pan).append(" amount:").append(amount).toString();

        return TransactionRequest.newBuilder()
                .setCardNo(pan)
                .setRrn(referenceGenerator.getRandomRRN())
                .setAcquireIIN(11561370L)
                .setUserId(0)
                .setHostId(99)
                .setGatewayId(40)
                .setRefTranType(16)
                .setTerminalId(terminalId)
                .setRefTranId(referenceGenerator.getRandomRefTranId())
                .setPin("")
                .setAmount(amount)
                .setDescription(description)
                .setPaymentId("pay123")
                .setAgentMobileNo("09121234567")
                .setClientTime(Long.parseLong(StringUtils.formatDate(new Date())))
                .setCondition(TransactionConditionTypeEnum.CardPresent.getValue())
                .setProcess(process.getValue())
                .setEndpoint("gRPC")
                .build();
    }

    TransactionRequest reversalTransaction(TransactionRequest request) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", new Locale("fa", "IR"));
        String reverseDescription = resourceBundle.getString("reversal") + " " + request.getDescription();

        return TransactionRequest.newBuilder()
                .setProcess(request.getProcess())
                .setCardNo(request.getCardNo())
                .setAmount(request.getAmount())
                .setDescription(reverseDescription)
                .setRrn(request.getRrn())
                .setAcquireIIN(request.getAcquireIIN())
                .setClientTime(request.getClientTime())
                .setGatewayId(request.getGatewayId())
                .setHostId(request.getHostId())
                .setRefTranType(request.getRefTranType())
                .setRefTranId(request.getRefTranId())
                .setUserId(request.getUserId())
                .setTerminalId(request.getTerminalId())
                .build();
    }

    public TransactionInquiryRequest inquiryTransaction(TransactionRequest request) {
        return TransactionInquiryRequest.newBuilder()
                .setRefTranId(Long.parseLong(request.getRefTranId()))
                .setRefTranType(request.getRefTranType())
                .setHostId(request.getHostId())
                .setGatewayId(request.getGatewayId())
                .build();
    }
}
