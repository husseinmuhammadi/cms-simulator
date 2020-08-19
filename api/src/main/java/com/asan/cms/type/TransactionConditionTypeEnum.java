package com.asan.cms.type;

/**
 * Created by a.heydari on 9/3/2016.
 */
public enum TransactionConditionTypeEnum {
    CardPresent(1),
    CardNotPresent(2),
    ;

    private int value;
    private TransactionConditionTypeEnum(int val) {
        this.value = val;
    }

    public int getValue() {
        return value;
    }

    public static TransactionConditionTypeEnum valueOf(int val) {
        if(val == 0) return TransactionConditionTypeEnum.CardPresent; //for backward compatibility with old version of app mobile server
        for(TransactionConditionTypeEnum as : TransactionConditionTypeEnum.values())
            if(as.getValue() == val)
                return as;
        return null;
    }
}
