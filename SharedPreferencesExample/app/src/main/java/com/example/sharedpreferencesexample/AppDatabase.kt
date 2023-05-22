package com.example.sharedpreferencesexample

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class AppDatabase : RoomDatabase(){
    //데이터베이스 객체를 가져온다.
    //싱글 톤 패턴: 하나의 앱에서 사용될 때 데이터베이스 객체 하나를 재활용한다.
    companion object{ //전역적으로 사용할 수 있는 함수나 변수를 담을 수 있다.
        //실제 데이터베이스가 담기는 객체를 생성한다.
        // null을 허용해야 한다. 나중에 초기화할 때 함수를 통해야 하기 때문에.
        // private: 변수에 직접적으로 접근하지 못하게 하기 위해 private으로 만든다.
        private var appDatabase: AppDatabase? = null

        //인스턴스를 가져오는 함수이다. 이 함수를 통해 데이터베이스 객체(AppDatabase)를 리턴받는다.
        @Synchronized //여러 스레드에서 동시에 하나의 자원에 접근하는 것을 방지한다.
        fun getInstance(context: Context): AppDatabase? {
            if (appDatabase == null){ //appDatabase가 null이면 = 초기화되었으면 실행한다.
                synchronized(AppDatabase::class.java){
                    //Room.databaseBuilder 함수를 사용한다.
                    // (context: Context, klass: Class<T!>, name: String)
                    appDatabase = Room.databaseBuilder(
                        context.applicationContext, //전역적으로 context를 사용한다.
                        AppDatabase::class.java, //RoomDatabase 객체 자체이다.
                        "app-database" //다른 Room데이터베이스와 이름이 중복되면 안된다.
                    ).allowMainThreadQueries().build() //메인 스레드에서의 쿼리를 허용하고 빌드한다.
                }
            }
            return appDatabase
        }
    }
}