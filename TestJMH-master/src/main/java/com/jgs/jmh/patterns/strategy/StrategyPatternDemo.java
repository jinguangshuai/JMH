package com.jgs.jmh.patterns.strategy;

/**
 * @Auther：jgs
 * @Data：2024/6/23 - 06 - 23 - 22:03
 * @Description:com.jgs.jmh.patterns.strategy
 * @version:1.0
 */
public class StrategyPatternDemo {

    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationSubtract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationMultiply());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
    }
}
