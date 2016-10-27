package com.fred.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fred.R;
import com.fred.base.BaseActivity;
import com.google.zxing.WriterException;
import com.zbar.lib.CaptureActivity;
import com.zbar.lib.ZxingBuildQRCode;

import java.io.UnsupportedEncodingException;


/**
 * Created by think on 2016/10/26.
 */

public class QRActivity extends BaseActivity implements View.OnClickListener {
    private Dialog mDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        Button btn_qr = (Button) findViewById(R.id.btn_qr);
        btn_qr.setOnClickListener(this);
        Button btn_scan = (Button) findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_qr:
                showQRCode();
                break;
            case R.id.btn_scan:
            Intent intent = new Intent(this, CaptureActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 显示二维码dialog
     */
    private void showQRCode() {
        View dialogView = LayoutInflater.from(this).inflate(
                R.layout.fd_qrcode_dialog, null);
        mDialog = new AlertDialog.Builder(this).create();
        mDialog.show();
        mDialog.getWindow().setContentView(dialogView);
        int width = this.getWindowManager().getDefaultDisplay()
                .getWidth();// 得到当前显示设备的宽度，单位是像素
        android.view.WindowManager.LayoutParams params = mDialog.getWindow()
                .getAttributes();// 得到这个dialog界面的参数对象
        params.width = width - (width / 4);// 设置dialog的界面宽度
        params.height = android.view.ViewGroup.LayoutParams.WRAP_CONTENT;// 设置dialog高度为包裹内容
        params.gravity = Gravity.CENTER;// 设置dialog的重心
        mDialog.getWindow().setAttributes(params);
        dialogView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mDialog.cancel();
            }
        });
        mDialog.setCanceledOnTouchOutside(true);
        ImageView qr_avatar_url = (ImageView) dialogView.findViewById(R.id.qr_avatar_url);
        TextView qr_userName = (TextView) dialogView.findViewById(R.id.qr_userName);
        TextView qr_handsup_id = (TextView) dialogView.findViewById(R.id.qr_handsup_id);
        TextView qr_user_area = (TextView) dialogView.findViewById(R.id.qr_user_area);
        ImageView qr_card = (ImageView) dialogView.findViewById(R.id.qr_card);
            qr_avatar_url.setImageResource(R.drawable.default_img);
        qr_userName.setText("userName");
        qr_handsup_id.setText("id");
        qr_user_area.setText("area");
        qr_card.setVisibility(View.VISIBLE);

        ImageView qr_code = (ImageView) dialogView.findViewById(R.id.qr_code);
        Bitmap mIcon = BitmapFactory.decodeResource(getResources(),
                R.drawable.log);
        String content = "aaa";
        Bitmap bitmap;
        try {
            bitmap = ZxingBuildQRCode.cretaeBitmap(
                    new String(content.getBytes(), "UTF-8"), mIcon, 40, 450,
                    450);
            // 生成的二维码
            qr_code.setImageBitmap(bitmap);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        }

    }
}
