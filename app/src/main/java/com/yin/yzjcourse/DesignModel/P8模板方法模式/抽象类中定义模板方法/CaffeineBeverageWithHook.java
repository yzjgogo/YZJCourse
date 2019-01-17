package com.yin.yzjcourse.DesignModel.P8模板方法模式.抽象类中定义模板方法;

public abstract class CaffeineBeverageWithHook {

    /**
     * 模板方法，封装了一整套算法。
     */
    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {
            addCondiments();
        }
        otherHook();
    }

    //算法中的这一步由各个子类去实现
    public abstract void brew();

    //算法中的这一步由各个子类去实现
    public abstract void addCondiments();

    void boilWater() {
        System.out.println("Boiling water");
    }

    void pourInCup() {
        System.out.println("Pouring into cup");
    }

    /**
     * 钩子方法，这里有默认的实现，子类可以根据需要重写，这个钩子方法在模板方法中起到了条件判断的作用，直接决定了
     * 是否会调用addCondiments()
     *
     * @return
     */
    public boolean customerWantsCondiments() {
        return true;
    }

    /**
     * 这也是一个钩子方法，但是是一个空方法，子类如果有需要，可以重写它。
     */
    public void otherHook() {

    }
}
