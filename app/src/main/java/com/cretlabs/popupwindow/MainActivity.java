package com.cretlabs.popupwindow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.cretlabs.popupwindow.parent.ArrowPopUp;
import com.cretlabs.popupwindow.parent.CustomRelativePopUp;
import com.cretlabs.popupwindow.parent.RelativePopupParent;

import static com.cretlabs.popupwindow.parent.RelativePopupParent.HorizontalPosition.RIGHT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void show(View view) {
        ////////////////////////arrow pop up/////////////
        final ArrowPopUp arrowPopUp = new ArrowPopUp(MainActivity.this, RIGHT);
        arrowPopUp.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        arrowPopUp.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        arrowPopUp.setFocusable(false);
        final int verticalPosArrow = RelativePopupParent.VerticalPosition.CENTER;
        final int horizontalPosArrow = RelativePopupParent.HorizontalPosition.CENTER;
        arrowPopUp.showOnAnchor(view, verticalPosArrow, horizontalPosArrow, true);
        final CustomRelativePopUp  mCancelPopUp = new CustomRelativePopUp(MainActivity.this, R.layout.popup_cancel);
        mCancelPopUp.setOutsideTouchable(false);
        mCancelPopUp.setFocusable(true);
        View viewPopup = mCancelPopUp.getContentView();

        AppCompatImageButton closeImageButton = viewPopup.findViewById(R.id.PMN_btn_close);
        closeImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCancelPopUp.dismiss();
            }
        });
        mCancelPopUp.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mCancelPopUp.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        final int verticalPos = RelativePopupParent.VerticalPosition.CENTER;
        final int horizontalPos = RelativePopupParent.HorizontalPosition.LEFT;
        mCancelPopUp.showOnAnchor(view, verticalPos, horizontalPos, true);
        mCancelPopUp.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                arrowPopUp.dismiss();
            }
        });
    }
}
