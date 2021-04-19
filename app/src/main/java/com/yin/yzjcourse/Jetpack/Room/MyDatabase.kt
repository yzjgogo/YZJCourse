package com.yin.yzjcourse.Jetpack.Room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.yin.yzjcourse.MyApplication
import com.yin.yzjcourse.utils.DLog

const val DATABASE_NAME = "my_db"

/**
 * 之前数据库版本是1，因为业务需要要升级数据库了，比如将数据库的版本升级为2，@Database(entities = [Student::class],version = 2)
 * 则需要Migration(1,2)，然后在migrate(1,2)方法中写上本次升级的逻辑，比如新增一列数据，修改某列数据的类型等等
 */
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        DLog.eLog("数据库从 v1 -> v2")
    }
}

/**
 * 数据库由版本2，升级到版本3
 */
val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        DLog.eLog("数据库从 v2 -> v3")
    }
}

/**
 * 数据库由版本3，升级到版本4
 */
val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        DLog.eLog("数据库从 v3 -> v4")
        /**
         * 销毁与重建策略
         * Sqlite修改表结构比较麻烦，比如我们想给Student表新增TEXT类型的salary字段，则需要如下步骤：
         * 1：创建一张符合Student表结构要求的临时表temp_Student，并新增列salary;
         * 2：讲数据从旧表Student复制到临时表temp_Student;
         * 3：删除旧表Student;
         * 4：将临时表temp_Student重命名为Student;
         *
         * 注意：这里的NOT NULL DEFAULT 'xxx'非常重要，因为Student中，之前定义的都是非null类型，如果不加这个，会导致数据库升级失败
         */
        database.execSQL("CREATE TABLE temp_Student (" +
                "id INTEGER PRIMARY KEY NOT NULL," +
                "name TEXT NOT NULL DEFAULT 'xxx'," +
                "age TEXT NOT NULL DEFAULT 'xxx'," +
                "salary TEXT NOT NULL DEFAULT 'xxx')")
        database.execSQL("INSERT INTO temp_Student (id, name, age) " +
                "SELECT id, name, age FROM Student")
        database.execSQL("DROP TABLE Student")
        database.execSQL("ALTER TABLE temp_Student RENAME TO Student")
    }
}

//@Database(entities = [Student::class],exportSchema = false,version = 2)
//默认是导出scheme的，schema是一个json文件记录了，数据库历史更迭历程，我们可以把这个sch
@Database(entities = [Student::class], version = 4)
abstract class MyDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao

    companion object {
        val instance = Single.sin
    }

    private object Single {
        val sin: MyDatabase = Room.databaseBuilder(MyApplication.appContext, MyDatabase::class.java, DATABASE_NAME)
                //addMigrations()需要把每次的版本升级都列出来，方便，不同的设备根据自身的当前版本找到对应的升级方案，
                //因为可能手机A里的版本是1，手机B里的版本是2，则AB连个手机装了版本3的app都能找对对应的升级方案(1->2->3)
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
                //如果我们把数据库版本升级到5(version = 5)，但没有找对对应的Migration(没写对应的升级方案)，则会报异常IllegalStateException异常而崩溃，
                //为了避免崩溃，则需要加入fallbackToDestructiveMigration()，该方法能够在出现升级异常时，重新创建数据表，只是所有数据都会消失
                .fallbackToDestructiveMigration()
                //从Assets中加载db文件,Room 2.2版本支持
//                .createFromAsset(databaseFilePath)
                //从sd卡中加载db文件,Room 2.2版本支持
//                .createFromFile(databaseFile)
                .build()
    }

}