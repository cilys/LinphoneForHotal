package com.cilys.linphoneforhotal.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyUtils {

    public static float mul(float v1, float v2) {
        if (v1 == 0f || v2 == 0f) {
            return 0.00f;
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        BigDecimal res = b1.multiply(b2);
        res = res.setScale(2, RoundingMode.HALF_UP);

        return res.floatValue();
    }

    public static float add(float... fee){
        if (fee == null || fee.length < 1) {
            return 0.00f;
        }
        BigDecimal res = new BigDecimal(fee[0]);
        for (int i = 1; i < fee.length; i++) {
            res = res.add(new BigDecimal(fee[i]));
        }
        res = res.setScale(2, RoundingMode.HALF_UP);

        return res.floatValue();
    }

    public static String fomcatMoney(float money){
        String res = String.valueOf(money);

        if (!res.contains(".")){
            return res + ".00";
        }
        if (res.endsWith(".")) {
            return res + "00";
        }

        res = res + "00";

        int index = res.indexOf(".");

        return res.substring(0, index + 3);
    }
}
