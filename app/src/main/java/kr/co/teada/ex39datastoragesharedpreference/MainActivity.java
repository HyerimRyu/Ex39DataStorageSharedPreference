package kr.co.teada.ex39datastoragesharedpreference;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName=findViewById(R.id.et_name);
        etAge=findViewById(R.id.et_age);

        tv=findViewById(R.id.tv);
    }

    public void clickSave(View view) {

        //1.
        String name=etName.getText().toString();
        int age=0;
        try{
            age=Integer.parseInt(etAge.getText().toString());
        }catch (Exception e){
            age=0;
        }

        //2. 내부 저장소에 Preference로 저장하기
        //Data.xml
        //내 앱에서 어디서든 쓰면 shared 그 액티비티에서만 쓰면 preference
        SharedPreferences pref= getSharedPreferences("Data", MODE_PRIVATE);

        //3. 저장(쓰기) 작업시작을 요청하면 자동으로 작성자객체(Editor) 리턴 해줘 : 에디터 부르는 이유는 실수 할까봐
        SharedPreferences.Editor editor =pref.edit();

        //4. 저장작업(키-밸류)
        editor.putString("Name", name); //키,값
        editor.putInt("Age", age);

        //5. 쓰기작업이 완료되었다고 명시
        editor.commit();   //이거 안하면 저장 안돼!!!!! 중요해!!
    }

    public void clickLoad(View view) {

        //6. 읽어들일때도 sharedPreference 객체 소환 : 읽고 쓰기 똑같아
        SharedPreferences pref=getSharedPreferences("Data", MODE_PRIVATE);

        //7. 아까는 쓰기모드, 지금은 읽기 모드: 읽기 모드는 곧바로 Preference 객체에게 요청
        String name=pref.getString("Name", "no name");
        int age=pref.getInt("Age", 0);  //두 번째 파라미터: 저장된게 없을 때

        //8. 화면에 보이기
        tv.setText(name+","+age);
    }
}
