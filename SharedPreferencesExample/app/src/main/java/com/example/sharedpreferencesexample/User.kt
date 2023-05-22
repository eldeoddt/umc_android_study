package com.example.sharedpreferencesexample

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class User(
    //@ColumnInfo: 이 column의 id를 name으로 지정하겠다.
    //name 변수를 생성한다.
    @ColumnInfo(name = "name") val name: String, //이름
    @ColumnInfo(name = "age") val age: Int, //나이
    //PrimaryKey: 고유한 키값을 나타낸다.
    //autoGenerate: 고유 키마다 자동으로 숫자가 증가되면서 값이 만들어진다.
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "userId") val userId:Int = 0 //uid
)
