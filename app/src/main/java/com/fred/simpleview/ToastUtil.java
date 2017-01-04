package com.fred.simpleview;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fred.R;
import com.fred.activity.ExToast;

/**
 * 弹出任意位置的toast,直接ToastUtil.showShortToast(this, System.currentTimeMillis() + "");
 */

public class ToastUtil {

    public static void showShortToast(Context context, String message) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //获得屏幕的宽度
        int width = wm.getDefaultDisplay().getWidth();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
        View toastView = inflater.inflate(R.layout.toast_message, null);
        TextView tv = (TextView) toastView.findViewById(R.id.tv_toast);
        tv.setLayoutParams(params);
        tv.setText(message);

        ExToast exToast = new ExToast(context);
        exToast.setView(toastView);
        exToast.setDuration(3);
        exToast.setAnimations(R.style.toast_anim_view);
        exToast.setGravity(Gravity.TOP, 0, 0);
        exToast.show();

//        toast = new Toast(AppApplication.getInstance());
//        float hOffset = AppApplication.getInstance().getResources().getDimension(R.dimen.common_title_height);
//        toast.setGravity(Gravity.TOP, 0, (int) hOffset);
//        toast.setDuration(Toast.LENGTH_LONG);
//        toast.setView(toastView);
//        toast.show();


//        View toastRoot = LayoutInflater.from(context).inflate(R.layout.toast_message, null);
//        TextView tv_toast = (TextView) toastRoot.findViewById(R.id.tv_toast);
//        tv_toast.setText(message);
//        Toast toastStart = new Toast(context);
//        toastStart.set
//        toastStart.setGravity(Gravity.TOP, 0, 150);
//        toastStart.setDuration(Toast.LENGTH_SHORT);
//        toastStart.setView(toastRoot);
//        toastStart.show();


    }

}
