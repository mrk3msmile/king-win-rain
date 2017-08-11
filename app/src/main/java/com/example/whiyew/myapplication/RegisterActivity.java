package com.example.whiyew.myapplication;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edtEmail, edtPass, edtPass2;
    ImageView imgParent, imgTutor, imgCheckParent, imgCheckTuor, imgEnvelope, imgshowPass, imgshowPass2, imgDelEmail, imgDelPass, imgDelPass2;
    Button btnRegister;
    TextView txtHello, txtParent, txtTutor;
    LinearLayout linearEmail, linearPass, linearPass2;
    RelativeLayout relativeParent, relativeTutor;
    Boolean choice = false, showPass = true, showPassRetype = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setContructor();
        setEvent();
        setOnFocusEditText();
        setAddChangeText();
    }

    public void setContructor() {
        edtEmail = (EditText) findViewById(R.id.register_email);
        edtPass = (EditText) findViewById(R.id.register_pass);
        edtPass2 = (EditText) findViewById(R.id.register_pass_retype);
        imgParent = (ImageView) findViewById(R.id.register_img_parent);

        imgTutor = (ImageView) findViewById(R.id.register_img_tutor);
        imgCheckParent = (ImageView) findViewById(R.id.register_check_parent);
        imgCheckTuor = (ImageView) findViewById(R.id.register_check_tutor);
        imgEnvelope = (ImageView) findViewById(R.id.register_envelope);
        imgshowPass = (ImageView) findViewById(R.id.register_lock_pass);
        imgshowPass2 = (ImageView) findViewById(R.id.register_lock_pass_retype);
        imgDelEmail = (ImageView) findViewById(R.id.register_del_email);
        imgDelPass = (ImageView) findViewById(R.id.register_del_pass);
        imgDelPass2 = (ImageView) findViewById(R.id.register_del_pass_retype);

        btnRegister = (Button) findViewById(R.id.register_button);

        txtHello = (TextView) findViewById(R.id.register_hello);
        txtParent = (TextView) findViewById(R.id.register_txt_parent);
        txtTutor = (TextView) findViewById(R.id.register_txt_tutor);

        relativeTutor = (RelativeLayout) findViewById(R.id.register_tutor_relative);
        relativeParent = (RelativeLayout) findViewById(R.id.register_std_relative);

        linearEmail = (LinearLayout) findViewById(R.id.register_email_linear);
        linearPass = (LinearLayout) findViewById(R.id.register_pass_linear);
        linearPass2 = (LinearLayout) findViewById(R.id.register_pass_retype_linear);
    }

    public void setEvent() {
        btnRegister.setOnClickListener(new ProcessMyClick());
        imgParent.setOnClickListener(new ProcessMyClick());
        imgTutor.setOnClickListener(new ProcessMyClick());
        imgEnvelope.setOnClickListener(new ProcessMyClick());
        imgshowPass.setOnClickListener(new ProcessMyClick());
        imgshowPass2.setOnClickListener(new ProcessMyClick());
        imgDelEmail.setOnClickListener(new ProcessMyClick());
        imgDelPass.setOnClickListener(new ProcessMyClick());
        imgDelPass2.setOnClickListener(new ProcessMyClick());

    }

    public void setAddChangeText() {
        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                doCheckEmpty();
                doShowDelEmail();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                doCheckEmpty();
                doShowDelEmail();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                doCheckEmpty();
                doShowDelEmail();
            }
        });
        edtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                doCheckEmpty();
                doShowDelPass();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                doCheckEmpty();
                doShowDelPass();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                doCheckEmpty();
                doShowDelPass();
            }
        });
        edtPass2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                doCheckEmpty();
                doShowDelPassretype();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                doCheckEmpty();
                doShowDelPassretype();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                doCheckEmpty();
                doShowDelPassretype();
            }
        });
    }

    public void setOnFocusEditText() {
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
                } else {
                    linearPass.setBackgroundResource(R.drawable.edittext_unchecked_bg);
                }
            }
        });
        edtPass2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b) {
                    linearPass2.setBackgroundResource(R.drawable.edittext_checked_bg);
                } else {
                    linearPass2.setBackgroundResource(R.drawable.edittext_unchecked_bg);
                }
            }
        });
    }


    public class ProcessMyClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.register_img_parent:
                    doChoiceParent();
                    break;
                case R.id.register_img_tutor:
                    doChoiceTutor();
                    break;
                case R.id.register_button:
                    doRegister();
                    break;
                case R.id.register_envelope:
                    doAddAt();
                    break;
                case R.id.register_lock_pass:
                    doShowPass();
                    break;
                case R.id.register_lock_pass_retype:
                    doshowPassRetype();
                    break;
                case R.id.register_del_email:
                    doDelEmail();
                    break;
                case R.id.register_del_pass:
                    doDelPass();
                    break;
                case R.id.register_del_pass_retype:
                    doDelPassretype();
                    break;
                default:
                    break;
            }
        }
    }

    //Ham thuc thi khi chon doi tuong Phu Huynh
    public void doChoiceParent() {
        txtHello.setText(getResources().getString(R.string.hello_parent));
        relativeParent.setBackgroundResource(R.drawable.edittext_checked_bg);
        imgCheckParent.setVisibility(View.VISIBLE);
        txtParent.setTextColor(getResources().getColor(R.color.them_login));
        relativeTutor.setBackgroundResource(R.drawable.edittext_unchecked_bg);
        imgCheckTuor.setVisibility(View.GONE);
        txtTutor.setTextColor(getResources().getColor(R.color.colorLogin));
        choice = true;
    }

    //Ham thuc thi khi tao doi tuong Gia su
    public void doChoiceTutor() {
        txtHello.setText(getResources().getString(R.string.hello_tutor));
        relativeTutor.setBackgroundResource(R.drawable.edittext_checked_bg);
        imgCheckTuor.setVisibility(View.VISIBLE);
        txtTutor.setTextColor(getResources().getColor(R.color.them_login));
        relativeParent.setBackgroundResource(R.drawable.edittext_unchecked_bg);
        imgCheckParent.setVisibility(View.GONE);
        txtParent.setTextColor(getResources().getColor(R.color.colorLogin));
        choice = true;
    }

    //Ham them duoi @gmail.com neu nhu editext khong chua dau @
    public void doAddAt() {
        String email = edtEmail.getText().toString();
        if(!email.contains("@")) {
            edtEmail.setText(email+"@gmail.com");
        }
        doChoiceEmail();
    }

    public void doShowPass() {
        if (showPass) {
            imgshowPass.setImageResource(R.drawable.ic_unlock);
            edtPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            showPass = false;
        } else {
            imgshowPass.setImageResource(R.drawable.ic_lock);
            edtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            showPass = true;
        }
        doChoicePass();
        edtPass.requestFocus();

    }

    public void doshowPassRetype() {
        if (showPassRetype) {
            imgshowPass2.setImageResource(R.drawable.ic_unlock);
            edtPass2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            showPassRetype = false;
        } else {
            imgshowPass2.setImageResource(R.drawable.ic_lock);
            edtPass2.setTransformationMethod(PasswordTransformationMethod.getInstance());
            showPassRetype = true;
        }
        doChoicePassRetype();
        edtPass2.requestFocus();

    }


    //Ham xoa noi dung edittext Email
    public void doDelEmail() {
        edtEmail.setText("");
        doChoiceEmail();
    }

    //Ham xoa noi dung edtittext mat khau
    public void doDelPass() {
        edtPass.setText("");
        doChoicePass();
    }

    //Ham xoa noi dung editText mat khau nhap lai
    public void doDelPassretype() {
        edtPass2.setText("");
        doChoicePassRetype();
    }

    //Ham dang ki
    public void doRegister() {
        if(choice) {
            if(!edtEmail.getText().toString().isEmpty() && !edtPass.getText().toString().isEmpty() && !edtPass2.getText().toString().isEmpty()) {
                if(edtEmail.getText().toString().contains("@")) {
                    if(edtPass.getText().toString().equals(edtPass2.getText().toString())) {
                        Intent intent = new Intent(Constain.REGISTER);
                        intent.putExtra(Constain.EMAIL, edtEmail.getText().toString());
                        intent.putExtra(Constain.PASSWORD, edtPass.getText().toString());
                        sendBroadcast(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.register_error_wrong_pass), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.register_error_no_email), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_login_empty), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.register_error_no_choice), Toast.LENGTH_SHORT).show();
        }
    }

    public void doShowDelEmail() {
        if(!edtEmail.getText().toString().isEmpty()) {
            imgDelEmail.setVisibility(View.VISIBLE);
        } else {
            imgDelEmail.setVisibility(View.GONE);
        }
    }

    public void doShowDelPass() {
        if(!edtPass.getText().toString().isEmpty()) {
            imgDelPass.setVisibility(View.VISIBLE);
        } else {
            imgDelPass.setVisibility(View.GONE);
        }
    }

    public void doShowDelPassretype() {
        if(!edtPass2.getText().toString().isEmpty()) {
            imgDelPass2.setVisibility(View.VISIBLE);
        } else  {
            imgDelPass2.setVisibility(View.GONE);
        }
    }

    public boolean doCheckEmpty() {

        if(!edtEmail.getText().toString().isEmpty() && !edtPass.getText().toString().isEmpty() && !edtPass2.getText().toString().isEmpty()) {
            btnRegister.setBackgroundResource(R.drawable.button_color);
            return false;
        } else {
            btnRegister.setBackgroundResource(R.drawable.button_color_unavailable);
            return true;
        }

    }

    public void createToast(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    public void doChoiceEmail() {
        linearEmail.setBackgroundResource(R.drawable.edittext_checked_bg);
        linearPass.setBackgroundResource(R.drawable.edittext_unchecked_bg);
        linearPass2.setBackgroundResource(R.drawable.edittext_unchecked_bg);
    }

    public void doChoicePass() {
        linearEmail.setBackgroundResource(R.drawable.edittext_unchecked_bg);
        linearPass.setBackgroundResource(R.drawable.edittext_checked_bg);
        linearPass2.setBackgroundResource(R.drawable.edittext_unchecked_bg);
    }

    public void doChoicePassRetype() {
        linearEmail.setBackgroundResource(R.drawable.edittext_unchecked_bg);
        linearPass.setBackgroundResource(R.drawable.edittext_unchecked_bg);
        linearPass2.setBackgroundResource(R.drawable.edittext_checked_bg);
    }
}
