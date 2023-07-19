package com.example.recyclerviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //viewbinding
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewbinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //클래스로부터 객체 생성
        val profileList = arrayListOf(
            //Profiles 클래스를 호출하여 인자값을 전달한다.
            Profiles(R.drawable.man,"홍길동", 20, "대학생"),
            Profiles(R.drawable.woman,"김길동", 18, "고등학생"),
            Profiles(R.drawable.agent,"이길동", 34, "교사"),
            Profiles(R.drawable.woman,"박길동", 59, "한의사"),
            Profiles(R.drawable.man,"최길동", 26, "의사"),
            Profiles(R.drawable.man,"주길동", 48, "자영업"),
            Profiles(R.drawable.agent,"양길동", 33, "마케터"),
            Profiles(R.drawable.man,"현길동", 45, "사진사"),
            Profiles(R.drawable.woman,"남길동", 30, "직장인"),
            Profiles(R.drawable.agent,"김길동", 59, "미용사"),
            Profiles(R.drawable.man,"서길동", 26, "변호사"),
            Profiles(R.drawable.woman,"윤길동", 48, "자영업"),
            Profiles(R.drawable.agent,"문길동", 33, "은행원"),
            Profiles(R.drawable.man,"현길동", 45, "교사"),
            Profiles(R.drawable.woman,"남길동", 30, "개발자")
        )

        //context: 액티비티나 컴포넌트가 갖고 있는 모든 정보이다.
        // this를 쓰면 자기 자신의 context;정보를 갖고 올 수 있다.
        binding.rvProfile.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvProfile.setHasFixedSize(true) //RecyclerView 성능 개선

        //어댑터 연결
        val adatper = ProfileAdapter(profileList)
        binding.rvProfile.adapter = adatper

        //클릭 이벤트 추가
        adatper.itemClick = object : ProfileAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {
                //현재 위치의 profileList를 저장한다.
                val profile: Profiles =profileList[position]

                //만약 숫자가 2131165410이면 여자, 2131165345 2131165304면 남자로
                var gender:String
                if (profile.gender==2131165410){gender="여자"}
                else {gender="남자"}

                //Toast.makeText(parent.context 등 메시지를 표시할 context객체, 표시할 text, duration(지속시간))
                Toast.makeText(
                    this@MainActivity,
                    "이름 : ${profile.name}\n성별 : ${gender}\n나이 : ${profile.age}\n직업 : ${profile.job}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}