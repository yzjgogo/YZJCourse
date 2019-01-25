package com.yin.yzjcourse.DesignModel.P16责任链模式.所有链节基类;

/**
 * 链条中的每一节都需继承该基类；
 */
public abstract class Leader {
    private Leader nextHandler;// 上一级领导处理者：当前链节处理不了，交给下一个链节

    public Leader setNextHandler(Leader nextHandler) {
        this.nextHandler = nextHandler;
        return this.nextHandler;
    }

    /**
     * 分发请求：
     * 如果当前链节可以处理就调用handle()处理；
     * 如果不可以处理就交给下一个处理者nextHandler处理；
     *
     * 这里的请求只是一个int数字，其实可以是更复杂的任意对象
     * @param money
     */
    public final void dispatchRequest(int money){
        if (money <= limit()){
            handle(money);
        }else {
            if (null!=nextHandler){
                nextHandler.dispatchRequest(money);
            }
        }
    }

    /**
     * 当前链节判断能否处理请求的条件，在dispatchRequest()方法中会使用；
     * 最终决定了是当前链节自己处理还是交给下一个链节nextHandler处理。
     * 条件的具体逻辑需要子类去实现；
     * @return
     */
    protected abstract int limit();

    /**
     * 当前链节在这里处理请求，具体的处理逻辑需要子类实现；
     * 如果在dispatchRequest()中判断当前链节可以处理，则交给该handle()方法处理；
     * @param money
     */
    protected abstract void handle(int money);

}
