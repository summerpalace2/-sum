package com.example.class52;
import static java.nio.file.Files.delete;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout mtextInputLayout;
    public  static SharedPreferences sharedPreferences;
    public  static SharedPreferences.Editor editor;
    private String returnedValue1;
    private String returnedValue2;
    private EditText mEtUserName;
    private EditText mEtPassword;
    private MaterialButton mBtnLogin;
    private MaterialButton mBtnLogin1;
    private Button mBtnLogin2;
    private ActivityResultLauncher<Intent> RegisterActivityResultLauncher;
    private static final int
            REQUEST_COOE_SECOND_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.sharedPreferences = getSharedPreferences("my_preference", Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        initEvent();
        setTitle("summer one");
        TextInputLyout_EditText();
    }
    public boolean check(String username)
    {  editor.putString("summer1","xia2992203079");
        editor.apply();
        return !sharedPreferences.contains(username);
    }
    private void initEvent() {
        RegisterActivityResultLauncher = registerForActivityResult(
                new
                        ActivityResultContracts.StartActivityForResult(),
                new
                        ActivityResultCallback<ActivityResult>() {
                            @Override
                            public void onActivityResult(ActivityResult result) {
                                if (result.getResultCode() == LoginActivity.RESULT_OK) {
                                    Intent data = result.getData();
                                    if (data != null) {
                                        returnedValue1 = data.getStringExtra("key1");
                                        returnedValue2 = data.getStringExtra("key2");
                                        editor.putString(returnedValue1, returnedValue2);
                                        editor.apply();
                                    }
                                }
                            }
                        }
        );
        mBtnLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete1();
            }
        });
        mBtnLogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login1();
            }
        });
        mBtnLogin.setOnClickListener
                (new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        login();
                    }
                });
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
                hasNumber = true;
            }
            if (hasEnglish && hasNumber)
            {
                return true;
            }
        }
        return false;
    }
    private void delete1() {
        String username = mEtUserName.getText().toString();
        String password = mEtPassword.getText().toString();
        boolean keyExists = sharedPreferences.contains(username);
        if (keyExists)
        {
            String password1 = sharedPreferences.getString(username, "");
            if (password1.equals(password))
            {
                editor.remove(username);
                editor.apply();
                deleteSuccess();
            } else
            {
                 deleteFail();
            }
        }
        else
        {
            deleteFail();
        }

    }

    private void login1() {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("key1", "Data1");
        intent.putExtra("key2", "Data2");
        RegisterActivityResultLauncher.launch(intent);

    }
    private void TextInputLyout_EditText() {

        mEtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mEtPassword.getText().length() > mtextInputLayout.getCounterMaxLength())
                {   int a=mEtPassword.getText().length();
                    mtextInputLayout.setError("超出限定字数了...");
                    if(a!=mEtPassword.getText().length())
                    {
                        TextInputLyout_EditText();
                    }

                }
                if (mEtPassword.getText().length()<mtextInputLayout.getCounterMaxLength())
                {
                    int b = mEtPassword.getText().length();
                    mtextInputLayout.setError(null);

                    if(b!=mEtPassword.getText().length())
                    {
                        TextInputLyout_EditText();
                    }

                }

            }
        });

    }

     private void login()
     {
        editor.putString("summer", "2024214602");
        String username = mEtUserName.getText().toString();
        String password = mEtPassword.getText().toString();
        boolean keyExists = sharedPreferences.contains(username);
        if
        (keyExists)
        {
            String password1 = sharedPreferences.getString(username, "");
            if (password1.equals(password) && isContainEnglishAndNumber(password)&&mEtPassword.getText().length()>=6)
             {
                loginSuccess();
             } else if (!password1.equals(password))
             {
                loginFail1();
             }
            else if(!isContainEnglishAndNumber(password))
            {
                loginFail2();
            }
            else if(mEtPassword.getText().length()<6)
            {
                loginFail3();
            }
        }
        else
        {
            loginFail1();
        }
    }

    private void deleteFail()
    {
        Toast.makeText(this,"删除失败",Toast.LENGTH_SHORT).show();
    }
    private void deleteSuccess()
    {
        Toast.makeText(this,"删除成功",Toast.LENGTH_SHORT).show();
    }
    private void loginFail1()
    {
        Toast.makeText( this,"登录失败",Toast.LENGTH_SHORT).show();
    }
    private void loginFail2()
    {
        Toast.makeText(this,"密码必须包含数字和字母",Toast.LENGTH_SHORT).show();
    }
    private void loginFail3(){Toast.makeText(this,"密码至少6位",Toast.LENGTH_LONG).show();}
    private void loginSuccess()
    {    Toast.makeText(this,"登陆成功",Toast.LENGTH_SHORT).show();
        HomeActivity.StartActivity(this,mEtUserName.getText().toString());

    }

    @SuppressLint("WrongViewCast")
    private void initview() {
        mEtUserName = findViewById(R.id.et_login_username);
        mEtPassword = findViewById(R.id.et_login_password);
        mBtnLogin = findViewById(R.id.button);
        mBtnLogin1=findViewById(R.id.button3);
        mBtnLogin2=findViewById(R.id.button5);
        mtextInputLayout=findViewById(R.id.summer);
    }
}

