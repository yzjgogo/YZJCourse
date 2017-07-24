package com.yin.yzjcourse.BuilderMode.ModernBuilderMode;

/**
 * Created by think on 2017/7/24.
 */

public class Person {
    //(一般来说，我们尽量将属性值定义为不可变的。总不能总不能人家都是男的。再改成女的吧)
    private final String name;
    private final String gender;
    private final String age;
    //好处1：避免了大量的set函数
    //好处2：避免了多个构造函数：
    // 这种构造函数虽然简单，但是当属性多了的时候。代码就会变得不易维护，而且构造函数里面的参数的顺序也很容易弄错。当参数有五个。你还记得第几个参数要填年龄？记得第几个参数要填姓名？
    /*
    public Person(String name){
        this(name,"男","20");
    }

    public Person(String name,String gender){
        this(name,gender,"20");
    }

    public Person(String name,String gender,String age){
        this.name = name;
        this.gender = gender;
        this.age = age;
    }
    */

    private Person(Builder builder) {
        name = builder.name;
        gender = builder.gender;
        age = builder.age;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public static final class Builder {
        private String name;
        private String gender;
        private String age;

        public Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder gender(String val) {
            gender = val;
            return this;
        }

        public Builder age(String val) {
            age = val;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
