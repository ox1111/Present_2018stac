package s2017s25.kr.hs.mirim.present_2018stac.activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import s2017s25.kr.hs.mirim.present_2018stac.Adapter.PTListAdapter;
import s2017s25.kr.hs.mirim.present_2018stac.Adapter.ptlist_list_item;
import s2017s25.kr.hs.mirim.present_2018stac.R;


public class PTlistActivity extends AppCompatActivity {

    TextView exitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptlist);

       // ImageView plusbtn = (ImageView) findViewById(R.id.plusbtn);
        ListView listView;
        PTListAdapter myListAdapter;
        ArrayList<ptlist_list_item> list_itemArrayList;

        listView = (ListView)findViewById(R.id.my_listView);

        list_itemArrayList = new ArrayList<ptlist_list_item>();

        list_itemArrayList.add(new ptlist_list_item(R.drawable.start,"앱잼발표","5:00",R.drawable.menu));
        list_itemArrayList.add(new ptlist_list_item(R.drawable.start,"앱 디자인 기획서 발표","3:00",R.drawable.menu));
        myListAdapter = new PTListAdapter(PTlistActivity.this,list_itemArrayList);
        listView.setAdapter(myListAdapter);

        exitBtn = (TextView) findViewById(R.id.exitBtn);

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
/*
        plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PTlistActivity.this, dialogActivity.class);
                startActivity(intent);
            }
        }); 팝업창*/

    }


}