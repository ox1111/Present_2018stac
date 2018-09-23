package s2017s25.kr.hs.mirim.present_2018stac.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import s2017s25.kr.hs.mirim.present_2018stac.db.DBHelper;
import s2017s25.kr.hs.mirim.present_2018stac.R;
import s2017s25.kr.hs.mirim.present_2018stac.model.KeyPoint;
import s2017s25.kr.hs.mirim.present_2018stac.model.Presentation;
import s2017s25.kr.hs.mirim.present_2018stac.model.Script;

public class SettingActivity extends AppCompatActivity {
    TextView nextBtn, prevBtn, exitBtn;
    LinearLayout settingTime,settingScript,settingVib,settingWatch;
    CheckBox settingCheck1, settingCheck2, settingCheck3, settingCheck4;
    Presentation pt;
    String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Intent intent = getIntent();
        pt = (Presentation) intent.getSerializableExtra("presentation");
        if(intent.getStringExtra("mode")!=null) {
            mode = intent.getStringExtra("mode");
        }


        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "Presentation.db", null, 1);

        nextBtn = (TextView) findViewById(R.id.setting_next_btn);
        if(mode.equals("modify")){
            nextBtn.setText("PT 수정하기");
        }

        settingTime=(LinearLayout)findViewById(R.id.setting_time);
        settingScript=(LinearLayout)findViewById(R.id.setting_script);
        settingVib=(LinearLayout)findViewById(R.id.setting_vib);
        settingWatch=(LinearLayout)findViewById(R.id.setting_watch);

        settingCheck1=(CheckBox)findViewById(R.id.setting_check1);
        settingCheck2=(CheckBox)findViewById(R.id.setting_check2);
        settingCheck3=(CheckBox)findViewById(R.id.setting_check3);
        settingCheck4=(CheckBox)findViewById(R.id.setting_check4);

        if(pt.isDisplayTime()) settingCheck1.setChecked(true);
        else settingCheck1.setChecked(false);
        if(pt.isDisplayScript()) settingCheck2.setChecked(true);
        else settingCheck2.setChecked(false);
        if(pt.isVibPhone()) settingCheck3.setChecked(true);
        else settingCheck3.setChecked(false);
        if(pt.isVibSmartWatch()) settingCheck4.setChecked(true);
        else settingCheck4.setChecked(false);

        if(mode.equals("input")){
            settingCheck1.setChecked(true);
            settingCheck2.setChecked(true);
            settingCheck3.setChecked(true);
        }

//        Toast.makeText(getApplicationContext(),(pt.isDisplayTime()+" "+pt.isDisplayScript()+" "+pt.isVibPhone()+" "+pt.isVibSmartWatch()),Toast.LENGTH_SHORT).show();

        settingTime.setOnClickListener(setCheck);
        settingScript.setOnClickListener(setCheck);
        settingVib.setOnClickListener(setCheck);
        settingWatch.setOnClickListener(setCheck);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pt.getScripts() != null) {
                    ArrayList<Script> scripts = (ArrayList<Script>) pt.getScripts();
                    pt.setScripts(scripts);
                }
                if(pt.getKeyPoints() != null) {
                    ArrayList<KeyPoint> keyPoints = (ArrayList<KeyPoint>) pt.getKeyPoints();
                    pt.setKeyPoints(keyPoints);
                }
                Date date=new Date();
//              Presentation pt=new Presentation("test2",(long)120000,true,true,true,true,scripts,keyPoints);
                pt.setDisplayTime(settingCheck1.isChecked());
                pt.setDisplayScript(settingCheck2.isChecked());
                pt.setVibPhone(settingCheck3.isChecked());
                pt.setVibSmartWatch(settingCheck4.isChecked());
                pt.setModifiedDate(date.getTime());

                int lastId;
                if(mode.equals("input")) {
                    lastId = dbHelper.insert(pt);
                }
                else if (mode.equals("modify")){
                    dbHelper.update(pt);
                }
                if(mode.equals("modify")){
                    Toast.makeText(getApplicationContext(), "PT가 수정되었습니다.", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "새로운 PT가 생성되었습니다.", Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(SettingActivity.this, PTlistActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out);
            }
        });
        ImageView inforBtn = findViewById(R.id.inforBtn);
        inforBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, AppInfo.class);
                startActivity(intent);
            }
        });

        prevBtn = (TextView) findViewById(R.id.setting_prev_btn);

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pt.setDisplayTime(settingCheck1.isChecked());
                pt.setDisplayScript(settingCheck2.isChecked());
                pt.setVibPhone(settingCheck3.isChecked());
                pt.setVibSmartWatch(settingCheck4.isChecked());
                Intent intent = new Intent(SettingActivity.this, ScriptKeyPointListActivity.class);
                intent.putExtra("presentation", pt);
                intent.putExtra("mode", mode);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.activity_slide_enter, R.anim.activity_slide_exit);
            }
        });
        exitBtn = findViewById(R.id.exitBtn);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {finish();}
        });
    }


    View.OnClickListener setCheck = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.setting_time:
                    if(settingCheck1.isChecked())
                        settingCheck1.setChecked(false);
                    else
                        settingCheck1.setChecked(true);
                    break;
                case R.id.setting_script:
                    if(settingCheck2.isChecked())
                        settingCheck2.setChecked(false);
                    else
                        settingCheck2.setChecked(true);
                    break;
                case R.id.setting_vib:
                    if(settingCheck3.isChecked())
                        settingCheck3.setChecked(false);
                    else
                        settingCheck3.setChecked(true);
                    break;
                case R.id.setting_watch:
                    if(settingCheck4.isChecked())
                        settingCheck4.setChecked(false);
                    else
                        settingCheck4.setChecked(true);
                    break;
            }
        }
    };


    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SettingActivity.this);
        alertDialogBuilder.setTitle("PT 생성 중단");
        alertDialogBuilder
                .setMessage("PT 생성을 중단하시겠습니까?")
                .setPositiveButton("중단",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        })
                .setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // 다이얼로그를 취소한다
                                dialog.cancel();
                            }
                        });
        alertDialogBuilder.show();
    }
}