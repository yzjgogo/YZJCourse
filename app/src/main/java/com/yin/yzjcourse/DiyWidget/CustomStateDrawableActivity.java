package com.yin.yzjcourse.DiyWidget;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.yin.yzjcourse.R;

/**
 * 参考：https://blog.csdn.net/lmj623565791/article/details/43752383?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522165301184116782248564589%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fblog.%2522%257D&request_id=165301184116782248564589&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~first_rank_ecpm_v1~rank_v31_ecpm-1-43752383-null-null.nonecase&utm_term=drawable&spm=1018.2226.3001.4450
 * 鸿洋_的博客
 */
public class CustomStateDrawableActivity extends ListActivity {

    private Message[] messages = new Message[]{
            new Message("Gas bill overdue", true),
            new Message("Congratulations, you've won!", true),
            new Message("I love you!", false),
            new Message("Please reply!", false),
            new Message("You ignoring me?", false),
            new Message("Not heard from you", false),
            new Message("Electricity bill", true),
            new Message("Gas bill", true), new Message("Holiday plans", false),
            new Message("Marketing stuff", false),};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getListView().setAdapter(new ArrayAdapter<Message>(this, -1, messages) {
            private LayoutInflater mInflater = LayoutInflater
                    .from(getContext());

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.item_msg_list,
                            parent, false);
                }
                MessageListItem messageListItem = (MessageListItem) convertView;
                TextView tv = (TextView) convertView
                        .findViewById(R.id.id_msg_item_text);
                tv.setText(getItem(position).message);
                messageListItem.setMessageReaded(getItem(position).readed);
                return convertView;
            }

        });

    }
}
