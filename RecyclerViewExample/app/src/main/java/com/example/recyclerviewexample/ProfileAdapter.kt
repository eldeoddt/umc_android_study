package com.example.recyclerviewexample

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewexample.databinding.ActivityMainBinding
import com.example.recyclerviewexample.databinding.ListItemBinding

// ArrayList타입 profileList 생성. ArrayList는 Profiles클래스 타입이다.
// **클래스를 불러와서 리스트화**한다.
// 인자 옆 콜론은 상속을 뜻한다. RecyclerView에 있는 Adapter 속성을 갖고 올것이라는 뜻이다.
class ProfileAdapter (val profileList: ArrayList<Profiles>) : RecyclerView.Adapter<ProfileAdapter.CustomViewHolder>(){

    //스위치 상태 저장
    // SparseBooleanArray: key-value 쌍으로 데이터를 저장한다.
    // swtichStatusArray: SparseBooleanArray의 객체이다.
    private val switchStatusArray = SparseBooleanArray()


    //클릭 이벤트 추가
    interface ItemClick{
        fun onClick(view: View, position: Int)
    }
    var itemClick : ItemClick? = null //클릭 이벤트 추가
    
    // viewholder 생성, 연결된 view 생성, 초기화한다.
    // main의 setcontentview처럼 만들어둔 레이아웃과 연결해준다.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAdapter.CustomViewHolder {

        //context: 액티비티에서 담고 있는 모든 정보를 의미한다.
        // 여기서는 어댑터랑 연결될 액티비티의 모든 정보를 가져온다.
        // inflate: xml 레이아웃을 클래스에서 사용하기 위해 메모리에 로딩, 객체화한다.
        // inflate(view를 만들고 싶은 레이아웃 파일명, 생성될 view의 parent를 명시, true: root의 자식view로 자동으로 추가, false: root는 생성되는 view의 layoutParam을 생성하는 데만 사용한다.)
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        val view =ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        // 위에서 view를 생성한 후 customviewholder에 넣어준다.
        // customviewholder에 view를 넣어줌으로써 연결된다.
        return CustomViewHolder(view)
    }



    // 데이터와 viewholder를 바인딩한다.
    // 스크롤 시 지속적으로 호출되며 안정적으로 데이터를 매치시킨다.
    override fun onBindViewHolder(holder: ProfileAdapter.CustomViewHolder, position: Int) {

        // 스위치 위치값 배열에 저장한다.
        holder.switch.isChecked = switchStatusArray[position]

        //스위치 버튼 추가
        holder.switch.setOnCheckedChangeListener { buttonView, isChecked ->
            switchStatusArray.put(position, isChecked)
            if(isChecked){
                holder.switch.text="알림 켬"
            }else{
                holder.switch.text="알림 끔"
            }
        }



        holder.itemView.setOnClickListener { //클릭 이벤트 추가 
            itemClick?.onClick(it, position)
        }
        //현재 클릭한 위치나 바인딩된 위치를 가져와서 데이터와 연동된다.
        // position: 아이템 중 지금 아이템이 몇 번째 아이템인지 알려준다.
        holder.gender.setImageResource(profileList.get(position).gender)
        holder.name.text = profileList.get(position).name
        // age:Int -> age:String
        holder.age.text = profileList.get(position).age.toString()
        holder.job.text = profileList.get(position).job
    }


    // RecyclerView에서 보여진 item의 총 개수를 반환한다.
    override fun getItemCount(): Int {
        return profileList.size  // 리스트의 총 개수를 반환한다.
    }

    //내부 클래스
    // view타입 itemview 생성,  콜론으로 RecyclerView 라이브러리의 ViewHolder 속성을 상속한다.
    //viewbinding
    class CustomViewHolder(private val binding:ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        // gender변수를 선언하고 라이브러리 뷰홀더 상속을 받은 itemView를 통하여
        // findViewById<id로 받아올 객체의 타입>(R.id.뷰의 id)
        val gender = binding.ivProfile1 //성별
        val name = binding.tvName1 //이름
        val age = binding.tvAge1 //나이
        val job = binding.tvJob1 //직업
        val switch = binding.swNoti1 //스위치
    }

    // 각 ViewHolder의 스위치 상태와 해당 포지션 값을 저장한다.
    data class SwitchStatus(val position: Int, var isChecked: Boolean)

}