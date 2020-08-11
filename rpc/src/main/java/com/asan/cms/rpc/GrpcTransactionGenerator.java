package com.asan.cms.rpc;

import com.asan.cms.grpc.*;
import com.asan.transaction.request.TransactionConditionTypeEnum;
import com.asan.transaction.request.TransactionProcessTypeEnum;
import com.asan.utils.string.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class GrpcTransactionGenerator {

    public static final Logger LOGGER = LoggerFactory.getLogger(GrpcTransactionGenerator.class);

    final TransactionReferenceGenerator referenceGenerator;

    long terminalId = 1254;

    public GrpcTransactionGenerator(TransactionReferenceGenerator referenceGenerator) {
        this.referenceGenerator = referenceGenerator;
    }

    public TransactionRequest depositTransaction(String pan, long amount, short gateway, int service, long referenceTransactionId, int host) {
        return financialTransaction(TransactionProcessTypeEnum.Deposit, pan, amount, gateway, service, referenceTransactionId, host);
    }

    public TransactionRequest cashoutTransaction(String pan, long amount, short gateway, int service, long referenceTransactionId, int host) {
        return financialTransaction(TransactionProcessTypeEnum.Cashout, pan, amount, gateway, service, referenceTransactionId, host);
    }

    public TransactionRequest paymentTransaction(String pan, long amount, short gateway, int service, long referenceTransactionId, int host) {
        return financialTransaction(TransactionProcessTypeEnum.Payment, pan, amount, gateway, service, referenceTransactionId, host);
    }

    public TransactionRequest purchaseTransaction(String pan, long amount, short gateway, int service, long referenceTransactionId, int host) {
        return financialTransaction(TransactionProcessTypeEnum.Purchase, pan, amount, gateway, service, referenceTransactionId, host);
    }

    public BalanceInquiryRequest balanceInquiryTransaction(String pan, short gateway, int service, long referenceTransactionId, int host) {
        String description = new StringBuilder().append(TransactionProcessTypeEnum.Balance.name()).append(" from ").append(pan).toString();

        return BalanceInquiryRequest.newBuilder()
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

    public FundTransferRequest fundTransferTransaction(String sourceCard, String destinationCard, long amount, short gateway, int service, long referenceTransactionId, int host) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        LocalDateTime now = LocalDateTime.now();
        Date start = Date.from(now.minusMonths(2).atZone(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());

        String description = new StringBuilder().append(TransactionProcessTypeEnum.Transfer.name()).append(" from ").append(sourceCard).toString();

        return FundTransferRequest.newBuilder()
                .setProcess(TransactionProcessTypeEnum.Transfer.getValue())
                .setCardNo(sourceCard)
                .setRrn(referenceGenerator.getRandomRRN())
                .setRefTranId(String.valueOf(referenceTransactionId))
                .setGatewayId(gateway) // 130
                .setRefTranType(service)
                .setHostId(host) // 1193
                .setClientTime(Long.parseLong(StringUtils.formatDate(new Date())))
                .setTerminalId(terminalId)
                .setAcquireIIN(581672011L)
                .setUserId(0)
                .setCondition(TransactionConditionTypeEnum.CardPresent.getValue())
                .setAmount(amount)
                .setDescription(description)
                .setPaymentId("pay123")
                .setAgentMobileNo("09121234567")
                // .setPin("")
                .setDestCardNo(destinationCard)
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

    TransactionRequest financialTransaction(TransactionProcessTypeEnum process, String pan, long amount, short gateway, int service, long referenceTransactionId, int host) {
        String description = new StringBuilder().append(process.name()).append(" from ").append(pan).append(" amount:").append(amount).toString();

        return TransactionRequest.newBuilder()
                .setCardNo(pan)
                .setRrn(referenceGenerator.getRandomRRN())
                .setAcquireIIN(11561370L)
                .setUserId(0)
                .setHostId(host)
                .setGatewayId(gateway)
                .setRefTranType(service)
                .setTerminalId(terminalId)
                .setRefTranId(String.valueOf(referenceTransactionId))
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

    public TransactionInquiryRequest inquiryTransaction(Short gateway, Integer service, Long referenceTransactionId, Integer host) {
        return TransactionInquiryRequest.newBuilder()
                .setRefTranId(referenceTransactionId)
                .setRefTranType(service)
                .setHostId(host)
                .setGatewayId(gateway)
                .build();
    }
}
