package com.cretlabs.popupwindow.parent;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

import com.cretlabs.popupwindow.R;

import static com.cretlabs.popupwindow.parent.RelativePopupParent.HorizontalPosition.LEFT;
import static com.cretlabs.popupwindow.parent.RelativePopupParent.HorizontalPosition.RIGHT;
import static com.cretlabs.popupwindow.parent.RelativePopupParent.VerticalPosition.BOTTOM;
import static com.cretlabs.popupwindow.parent.RelativePopupParent.VerticalPosition.TOP;


/**
 * Created by Gokul on 2/6/2018.
 * ArrowPopUp
 */

public class ArrowPopUp extends RelativePopupParent {
    private View mParentView;
    public ArrowPopUp(Context context, int gravity) {
        switch (gravity) {
            case TOP:
                setContentView(LayoutInflater.from(context).inflate(R.layout.arrow_pop_up_top, null));
                break;
            case BOTTOM:
                setContentView(LayoutInflater.from(context).inflate(R.layout.arrow_pop_up_bottom, null));
                break;
            case LEFT:
                setContentView(LayoutInflater.from(context).inflate(R.layout.arrow_pop_up_left, null));
                break;
            case RIGHT:
                setContentView(LayoutInflater.from(context).inflate(R.layout.arrow_pop_up_right, null));
                break;
        }
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(false);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // Disable default animation for circular reveal
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setAnimationStyle(0);
        }
    }

    @Override
    public void showOnAnchor(@NonNull View anchor, int vertPos, int horizPos, int x, int y, boolean fitInScreen) {
        super.showOnAnchor(anchor, vertPos, horizPos, x, y, fitInScreen);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            circularReveal(anchor);
        }
    }

    /**
     * Animation to pop up window
     *
     * @param anchor view anchor
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void circularReveal(@NonNull final View anchor) {
        final View contentView = getContentView();
        contentView.post(new Runnable() {
            @Override
            public void run() {
                final int[] myLocation = new int[2];
                final int[] anchorLocation = new int[2];
                contentView.getLocationOnScreen(myLocation);
                anchor.getLocationOnScreen(anchorLocation);
                final int cx = anchorLocation[0] - myLocation[0] + anchor.getWidth() / 2;
                final int cy = anchorLocation[1] - myLocation[1] + anchor.getHeight() / 2;
                contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                final int dx = Math.max(cx, contentView.getMeasuredWidth() - cx);
                final int dy = Math.max(cy, contentView.getMeasuredHeight() - cy);
                final float finalRadius = (float) Math.hypot(dx, dy);
                Animator animator = ViewAnimationUtils.createCircularReveal(contentView, cx, cy, 0f, finalRadius);
                animator.setDuration(300);
                animator.start();
            }
        });
    }
}
