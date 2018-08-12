package s2017s25.kr.hs.mirim.present_2018stac;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ScriptListAdapter extends BaseAdapter {
    Context context;
    ArrayList<script_list_item> list_itemArrayList;

    TextView timeStr;
    TextView content;

    public ScriptListAdapter(Context context, ArrayList<script_list_item> list_itemArrayList) {
        this.context = context;
        this.list_itemArrayList = list_itemArrayList;
    }

    @Override
    public int getCount() {
        return this.list_itemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list_itemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.script_item,null);

            timeStr=(TextView)convertView.findViewById(R.id.script_list_time);
            content=(TextView)convertView.findViewById(R.id.script_list_content);

            timeStr.setText(list_itemArrayList.get(position).getTimeStr());
            content.setText(list_itemArrayList.get(position).getContent());
        }

        return convertView;
    }
}
