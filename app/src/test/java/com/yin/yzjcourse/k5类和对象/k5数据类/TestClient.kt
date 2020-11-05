package com.yin.yzjcourse.k5类和对象.k5数据类

import org.junit.Test

class TestClient {

    @Test
    fun testData(){
        val client = Client("Jack",10086)
        println("$client")

        val copyClient1 = client.copy()
        println(copyClient1)

        val copyClient2 = client.copy("电信",10010)
        println(copyClient2)

        println(client == copyClient2)
    }
}