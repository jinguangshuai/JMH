package com.jgs.jmh.patterns.strategy;

/**
 * @Auther：jgs
 * @Data：2024/6/23 - 06 - 23 - 22:02
 * @Description:com.jgs.jmh.patterns.strategy
 * @version:1.0
 */
public class OperationAdd implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}
