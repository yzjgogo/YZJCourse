package com.yin.yzjcourse.MaterialDesign;

import android.os.Bundle;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.yin.yzjcourse.R;

public class SimpleBottomDialogActivity extends AppCompatActivity {
    private CoordinatorLayout coordinatorLayout;
    private int[] location = new int[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_bottom_dialog);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.root_cl);
        findViewById(R.id.bt_show_hide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleBottomDialog dialog = SimpleBottomDialog.newInstance(location[1]);
                dialog.show(getSupportFragmentManager(),"dialog");
            }
        });
    }

    /**
     * onCreate方法中调用getLocationOnScreen获取不到，因此在该窗口回调中调用
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        coordinatorLayout.getLocationOnScreen(location);
    }
}
