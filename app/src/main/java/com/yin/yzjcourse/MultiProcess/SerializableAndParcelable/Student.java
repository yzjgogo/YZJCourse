package com.yin.yzjcourse.MultiProcess.SerializableAndParcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Parcelable：也是一种序列化的方式
 * <p>
 * 与Serializable与Parcelable的区别：
 * Serializable：是java的序列化方式，开销较大；
 * Parcelable：Android自己的序列化方式，效率很高，但是用起来较麻烦点，推荐使用Parcelable；
 * <p>
 * Parcelable内部真正实现序列化的是Parcel,Parcel用于序列化和反序列化，内部包装了可序列化的数据，可以
 * 在Binder中自由传输。
 * <p>
 * 我们常见的Intent,Bundle,Bitmap等都实现了Parcelable，如果List和Map的元素可以序列化，那么这个List或Map也可以序列化。
 */
public class Student implements Parcelable {

    public int userId;
    public String userName;
    public boolean isMale;

    public Book book;

    public Student() {
    }

    public Student(int userId, String userName, boolean isMale) {
        this.userId = userId;
        this.userName = userName;
        this.isMale = isMale;
    }

    /**
     * 返回当前对象的内容描述，如果含有文件描述符就返回1，否则返回0，
     * 几乎所有情况下都返回0
     *
     * @return
     */
    public int describeContents() {
        return 0;
    }

    /**
     * 真正执行序列化：将当前对象写入序列化解构
     * flags为1时表示当前对象需要作为返回值返回，不能立即释放资源。
     * 几乎所有情况都为0
     *
     * @param out
     * @param flags
     */
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(userId);
        out.writeString(userName);
        out.writeInt(isMale ? 1 : 0);
        out.writeParcelable(book, 0);//Book也实现了Parcelable
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        /**
         * 反序列化过程
         * @param in
         * @return
         */
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        /**
         * 创建指定长度的原始对象数组
         * @param size
         * @return
         */
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    //真正执行反序列化
    private Student(Parcel in) {
        userId = in.readInt();
        userName = in.readString();
        isMale = in.readInt() == 1;
        //book也实现了Parcelable，所以它的反序列化过程需要传递当前线程的上下文类加载器
        book = in
                .readParcelable(Thread.currentThread().getContextClassLoader());
    }

    @Override
    public String toString() {
        return String.format(
                "User:{userId:%s, userName:%s, isMale:%s}, with child:{%s}",
                userId, userName, isMale, book);
    }

}
