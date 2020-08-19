package com.asan.cms.type;

/**
 * Created by a.heydari on 9/3/2016.
 */
public enum TransactionProcessTypeEnum {
    Payment(1),
    Purchase(2),
    Deposit(3),
    Balance(4),
    Transfer(5),
    Cashout(8),
    CashableBalance(9),
    Statement(10),

    CreditRemainedDebt(101),
    ;

    private int value;
    private TransactionProcessTypeEnum(int val) {
        this.value = val;
    }

    public int getValue() {
        return value;
    }

    public static TransactionProcessTypeEnum valueOf(int val) {
        for(TransactionProcessTypeEnum as : TransactionProcessTypeEnum.values())
            if(as.getValue() == val)
                return as;
        return null;
    }
}
