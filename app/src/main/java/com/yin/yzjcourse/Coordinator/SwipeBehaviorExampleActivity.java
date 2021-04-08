package com.yin.yzjcourse.Coordinator;

import android.os.Bundle;
import androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams;
import com.google.android.material.behavior.SwipeDismissBehavior;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

public class SwipeBehaviorExampleActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_behavior_example);


        final SwipeDismissBehavior swipe = new SwipeDismissBehavior();
        swipe.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_ANY);
        swipe.setListener(new SwipeDismissBehavior.OnDismissListener() {
            @Override public void onDismiss(View view) {
                Toast.makeText(SwipeBehaviorExampleActivity.this,
                        "Card swiped !!", Toast.LENGTH_SHORT).show();
            }

            @Override public void onDragStateChanged(int state) {}
        });

        CardView cardView = (CardView) findViewById(R.id.swype_card);
        LayoutParams coordinatorParams = (LayoutParams) cardView.getLayoutParams();
        coordinatorParams.setBehavior(swipe);
    }
}
