package com.example.whiyew.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    ImageView imgLockPass, imgEnvelope, imgDelEmail, imgDelPass;
    Button btnLogin;
    TextView txtHello, txtSignUp, txtForget, txtParent, txtTutor;
    EditText edtEmail, edtPass;
    boolean showPass = false;       //showPass:   true: mật khẩu hiện thị,  false: mật khẩu bị ẩn, mặc định là false
    LinearLayout linearEmail, linearPass;
    BroadcastReceiver broadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setContructor();
        setEvent();
        setAddChangeText();
        getBroadcastReceiver();
    }

    //Hàm ánh xạ đến activity_login layout
    public void setContructor() {
        imgLockPass = (ImageView) findViewById(R.id.login_lock_pass);
        imgEnvelope = (ImageView) findViewById(R.id.login_envelope);
        imgDelEmail = (ImageView) findViewById(R.id.login_del_email);
        imgDelPass = (ImageView) findViewById(R.id.login_del_pass);

        linearEmail = (LinearLayout) findViewById(R.id.login_email_linear);
        linearPass = (LinearLayout) findViewById(R.id.login_pass_linear);

        btnLogin = (Button) findViewById(R.id.login_button);

        txtForget = (TextView) findViewById(R.id.login_fogot);
        txtHello = (TextView) findViewById(R.id.login_hello);
        txtHello.setText(getResources().getString(R.string.hello));
        txtSignUp = (TextView) findViewById(R.id.login_sign_up);

        edtEmail = (EditText) findViewById(R.id.login_email);
        edtPass = (EditText) findViewById(R.id.login_pass);

    }

    //Hàm cài đặt sự kiện
    public void setEvent() {
        //Cài đặt sự kiện onClickListion
        imgLockPass.setOnClickListener(new ProcessMyClick());
        imgEnvelope.setOnClickListener(new ProcessMyClick());
        imgDelPass.setOnClickListener(new ProcessMyClick());
        imgDelEmail.setOnClickListener(new ProcessMyClick());
        btnLogin.setOnClickListener(new ProcessMyClick());
        txtSignUp.setOnClickListener(new ProcessMyClick());
        txtForget.setOnClickListener(new ProcessMyClick());

    }

    public void setAddChangeText() {
        edtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b) {
                    linearEmail.setBackgroundResource(R.drawable.edittext_checked_bg);
                } else {
                    linearEmail.setBackgroundResource(R.drawable.edittext_unchecked_bg);
                }
            }
        });
        edtPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b) {
                    linearPass.setBackgroundResource(R.drawable.edittext_checked_bg);
                } else  {
                    linearPass.setBackgroundResource(R.drawable.edittext_unchecked_bg);
                }
            }
        });
        //
        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                doCheckEmpty();
                showDelEmail();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                doCheckEmpty();
                showDelEmail();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                doCheckEmpty();
                showDelEmail();
            }
        });
        edtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                doCheckEmpty();
                showDelPass();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                doCheckEmpty();
                showDelPass();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                doCheckEmpty();
                showDelPass();
            }
        });
    }

    public void getBroadcastReceiver() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                edtEmail.setText(intent.getStringExtra(Constain.EMAIL));
                edtPass.setText(intent.getStringExtra(Constain.PASSWORD));
                Toast.makeText(context, getResources().getString(R.string.register_succes), Toast.LENGTH_LONG).show();
            }
        };
        registerReceiver(broadcastReceiver, new IntentFilter(Constain.REGISTER));
    }

    public class ProcessMyClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.login_del_email:
                    edtEmail.setText("");
                    break;
                case R.id.login_del_pass:
                    edtPass.setText("");
                    break;
                case R.id.login_lock_pass:
                    doShowPass();
                    break;
                case R.id.login_envelope:
                    doAddAt();
                    break;
                case R.id.login_sign_up:
                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                    break;
                case R.id.login_button:
                    doLogin();
                    break;
                case R.id.login_fogot:
                    doForgot();
                    break;
            }
        }
    }

    //Hàm kiểm tra edtMail và edtPass có trống không
    //Nếu cả 2 editText dc điền thì setBackground cho button Login là button_color (màu xanh) và trả lại false
    //Nếu trống thì trả setBackground cho button Login là button_color_unvailable (màu xanh nhạt) và trả lại true
    public boolean doCheckEmpty() {
        if(!edtEmail.getText().toString().isEmpty() && !edtPass.getText().toString().isEmpty()) {
            btnLogin.setBackgroundResource(R.drawable.button_color);
            return false;
        } else {
            btnLogin.setBackgroundResource(R.drawable.button_color_unavailable);
            return true;
        }

    }

    public void showDelEmail() {
        if(!edtEmail.getText().toString().isEmpty()) {
            imgDelEmail.setVisibility(View.VISIBLE);
        } else {
            imgDelEmail.setVisibility(View.GONE);
        }
    }


    public void showDelPass() {
        if(!edtPass.getText().toString().isEmpty()) {
            imgDelPass.setVisibility(View.VISIBLE);
        } else {
            imgDelPass.setVisibility(View.GONE);
        }
    }


    public void doLogin() {
        if(!doCheckEmpty()) {
            if(edtEmail.getText().toString().equals("a@gmail.com")) {
                if(edtPass.getText().toString().equals("1")) {
                    startActivity(new Intent(LoginActivity.this, DefaultActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_login_wrong_pass), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_login_wrong_email), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_login_empty), Toast.LENGTH_SHORT).show();
        }
    }

    public void doShowPass() {
        if (showPass) {
            imgLockPass.setImageResource(R.drawable.ic_unlock);
            edtPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            showPass = false;
        } else {
            imgLockPass.setImageResource(R.drawable.ic_lock);
            edtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            showPass = true;
        }
        linearPass.setBackgroundResource(R.drawable.edittext_checked_bg);
        linearEmail.setBackgroundResource(R.drawable.edittext_unchecked_bg);
        edtPass.requestFocus();

    }

    public void doAddAt() {
        String email = edtEmail.getText().toString();
        if(!email.contains("@")) {
            edtEmail.setText(email+"@gmail.com");
        }
        linearEmail.setBackgroundResource(R.drawable.edittext_checked_bg);
        linearPass.setBackgroundResource(R.drawable.edittext_unchecked_bg);
        edtEmail.requestFocus();
    }

    public void doForgot() {
        CustomDialog dialog = new CustomDialog(this);
        dialog.show();
    }


}
