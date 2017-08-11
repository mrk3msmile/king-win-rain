package com.example.whiyew.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whiyew.myapplication.R;

import java.util.List;

/**
 * Created by vudou on 8/7/2017.
 */

public class CustomDialog extends Dialog implements android.view.View.OnClickListener {
    private Activity activity;
    private Button btnConfirm, btnCancel;
    private EditText edtEmail;
    private TextView txtMess;
    private ImageView imgDelete, imgEnvelope;


    public CustomDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    //Hàm khởi tạo
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //set layout
        setContentView(R.layout.custom_dialog);
        //Ánh xạ đến custom_dialog layout
        btnConfirm = (Button) findViewById(R.id.dialog_confirm);
        btnCancel = (Button) findViewById(R.id.dialog_cancel);
        edtEmail = (EditText) findViewById(R.id.dialog_editext);
        txtMess = (TextView) findViewById(R.id.dialog_mess);
        imgDelete = (ImageView) findViewById(R.id.dialog_delete);
        imgEnvelope = (ImageView) findViewById(R.id.dialog_envelope);
        //Gắn sự kiện
        btnConfirm.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        imgDelete.setOnClickListener(this);
        imgEnvelope.setOnClickListener(this);
        //Gắn sự kiện addTextChange cho editText Email
        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                showDeleteButton();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                showDeleteButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                showDeleteButton();
            }
        });
    }

    @Override
    //Hàm xử lý luồng sự kiện
    public void onClick(View view) {
        switch (view.getId()) {
            //Khi bấm nút Xác nhận
            case R.id.dialog_confirm:
                doConfirm();
                break;
            //Khi bấm nút Hủy
            case R.id.dialog_cancel:
                dismiss();
                break;
            case R.id.dialog_delete:
                edtEmail.setText("");
                break;
            case R.id.dialog_envelope:
                doAddAt();
                break;
            default:
                break;
        }
    }

    //Hàm xử lý khi bấm nút xác nhận
    public void doConfirm() {
        //Lấy string email từ edittext
        String email = edtEmail.getText().toString();
        //Kiểm tra edittext email có trống không
        if(!email.isEmpty()) {
            //Kiểm tra email có chứa kí tự @ không
            if (email.contains("@")) {
                //Kiểm tra email đăng kí đã tồn tại chưa
                if(email.equals("a@b") || email.equals("c@b")) {
                    //In ra toast thông báo kiểm tra hòm thư để nhận mật khẩu mới
                    Toast.makeText(activity, "Kiểm tra hòm thư " + email + " để nhận mật khẩu mới.", Toast.LENGTH_LONG).show();
                    dismiss();
                } else {
                    //In ra thông báo email đăng kí không tồn tại
                    Toast.makeText(activity, activity.getResources().getString(R.string.dialog_error_dont_exists), Toast.LENGTH_SHORT).show();
                }
            } else {
                //In ra thông báo email không hợp lệ (k chưa dấu @)
                Toast.makeText(activity, activity.getResources().getString(R.string.dialog_error_no_email), Toast.LENGTH_SHORT).show();
            }
        } else {
            //In ra thông báo chưa nhập email vào edittext
            Toast.makeText(activity, activity.getResources().getString(R.string.dialog_error_empty), Toast.LENGTH_SHORT).show();
         }
    }

    public void showDeleteButton() {
        if(!edtEmail.getText().toString().isEmpty()) {
            imgDelete.setVisibility(View.VISIBLE);
        } else {
            imgDelete.setVisibility(View.GONE);
        }
    }

    public void doAddAt() {
        String email = edtEmail.getText().toString();
        if(!email.contains("@")) {
            edtEmail.setText(email+"@gmail.com");
        }
    }



}
