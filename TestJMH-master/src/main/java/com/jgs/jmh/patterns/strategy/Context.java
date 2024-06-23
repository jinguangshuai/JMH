package com.jgs.jmh.patterns.strategy;

/**
 * @Auther：jgs
 * @Data：2024/6/23 - 06 - 23 - 22:02
 * @Description:com.jgs.jmh.patterns.strategy
 * @version:1.0
 */
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2){
        return strategy.doOperation(num1, num2);
    }
}
