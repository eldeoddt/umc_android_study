package com.example.sharedpreferencesexample

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sharedpreferencesexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //sharedPreference 불러오기 getSharedPreferences(name:String!, mode:int)
        val sharedPrefs = getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
        //editor 가져오기
        val editor = sharedPrefs.edit()

        //초기화
        editor.putString("hamster","")
        editor.apply()

        //저장하기
        //key: hamster, value:cute
        editor.putString("hamster", "cute")//저장

        val spval1 = sharedPrefs.getString("hamster", "")
        Log.d("test", "before apply: ${spval1}") //apply 하기 전에 가져오기.
        editor.apply() //apply를 통해 저장 완료한다.

        //가져오기
        //putString 형식에 맞추어 get으로 가져온다.
        // getString(가져올 값의 key, key가 존재하지 않을 경우 기본값)
        val spval2 = sharedPrefs.getString("hamster", "")
        Log.d("test", "after apply: ${spval2}")
    }
}