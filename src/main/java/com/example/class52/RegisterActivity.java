package com.example.class52;

import static com.example.class52.LoginActivity.sharedPreferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {
    private TextInputLayout mtextInputLayout1;
    private TextInputLayout mtextInputLayout2;
    private MaterialButton mBtnLogin;
    private EditText mEtUserName;
    private EditText mEtpassword1;
    private EditText mEtpassword2;

    public static void StartActivity(Context content)
    {
        Intent intent=new Intent(content,RegisterActivity.class);
        content.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {   sharedPreferences = getSharedPreferences("my_preference", Context.MODE_PRIVATE);
        LoginActivity.editor = sharedPreferences.edit();
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        initEvent();
        initview();
        TextInputLyout_EditText1();
        TextInputLyout_EditText2();
    }

    @SuppressLint("WrongViewCast")
    private void initEvent()
    {
   mBtnLogin=findViewById(R.id.Button4);
   mEtUserName=findViewById(R.id.et_reg_username);
   mEtpassword1=findViewById(R.id.et_reg_password1);
   mEtpassword2=findViewById(R.id.et_reg_password2);
   mtextInputLayout1=findViewById(R.id.summer4);
   mtextInputLayout2=findViewById(R.id.summer5);

    }

    private void initview()
    {
  mBtnLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v)
      {   judge();
      }
  });
    }
    private void judge()
    {
        String password1=mEtpassword1.getText().toString();
        String password2=mEtpassword2.getText().toString();
        String username=mEtUserName.getText().toString();
        LoginActivity loginActivity=new LoginActivity();
        if(password1.equals(password2)&&isContainEnglishAndNumber(password1)&&mEtpassword1.getText().length()>=6&&loginActivity.check(username))
        {
            regSuccess();
            Intent intent= new Intent();
            intent.putExtra("key1",username);
            intent.putExtra("key2",password1);
            setResult(RESULT_OK,intent);
            finish();
        }
        else if(!password1.equals(password2))
        {
            regFail2();
        }
        else if(!isContainEnglishAndNumber(password1))
        {
            regFail1();
        }
        else if(mEtpassword1.getText().length()<6)
        {
            regFail3();
        }
        else if(!loginActivity.check(username))
        {
            regFail4();
        }



    }
    private boolean isContainEnglishAndNumber(String input)
    {
        boolean hasEnglish = false;
        boolean hasNumber = false;
        for (char c : input.toCharArray())
        {
            if (Character.isLetter(c))
            {
                hasEnglish = true;
            }
            else if (Character.isDigit(c))
            {
                hasNumber=true;
            }
            if(hasNumber&&hasEnglish)
            {
                return true;
            }
        }
        return false;
    }
    private void TextInputLyout_EditText1()
        {

        mEtpassword1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s)
            {   if(mEtpassword1.getText().length()<6)
                {
                    int a=mEtpassword1.getText().length();
                    mtextInputLayout1.setError("字数小于6...");
                    if(a!=mEtpassword1.getText().length())
                    {
                        TextInputLyout_EditText1();
                    }
                }
                if (mEtpassword1.getText().length() > mtextInputLayout1.getCounterMaxLength())
                {   int a=mEtpassword1.getText().length();
                    mtextInputLayout1.setError("超出限定字数15了...");
                    if(a!=mEtpassword1.getText().length())
                    {
                        TextInputLyout_EditText1();
                    }

                }
                if (mEtpassword1.getText().length()<mtextInputLayout1.getCounterMaxLength())
                {
                    int b = mEtpassword1.getText().length();
                    mtextInputLayout1.setError(null);

                    if(b!=mEtpassword1.getText().length())
                    {
                        TextInputLyout_EditText1();
                    }

                }

            }
        });

    }
    private void TextInputLyout_EditText2() {

        mEtpassword2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(mEtpassword2.getText().length()<6)
                {
                    int a=mEtpassword2.getText().length();
                    mtextInputLayout2.setError("字数小于6...");
                    if(a!=mEtpassword2.getText().length())
                    {
                        TextInputLyout_EditText2();
                    }
                }
                if (mEtpassword2.getText().length() > mtextInputLayout2.getCounterMaxLength())
                {   int a=mEtpassword2.getText().length();
                    mtextInputLayout2.setError("超出限定字数15了...");
                    if(a!=mEtpassword2.getText().length())
                    {
                        TextInputLyout_EditText2();
                    }

                }
                if (mEtpassword2.getText().length()<mtextInputLayout2.getCounterMaxLength())
                {
                    int b = mEtpassword2.getText().length();
                    mtextInputLayout2.setError(null);

                    if(b!=mEtpassword2.getText().length())
                    {
                        TextInputLyout_EditText2();
                    }

                }

            }
        });

    }

    private void regFail1()
    {
        Toast.makeText( this,"设置的密码必须包含数字和字母",Toast.LENGTH_SHORT).show();
    }
    private void regFail2()
    {
        Toast.makeText(this, "注册失败，前后密码不相等", Toast.LENGTH_SHORT).show();


    }
    private void regFail3()
    {
        Toast.makeText(this,"设置的密码小于6位",Toast.LENGTH_LONG).show();
    }
    private void regFail4()
    {
        Toast.makeText(this,"账号已存在",Toast.LENGTH_SHORT).show();
    }

    private void regSuccess()
    {
        Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT);
    }

}