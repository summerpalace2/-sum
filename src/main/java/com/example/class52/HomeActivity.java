package com.example.class52;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.button.MaterialButton;

public class HomeActivity extends AppCompatActivity implements OnItemClickListener
{
 private TextView mTvUsename;
 private MaterialButton mBtnLogin1;
 private TranslateAdapter adapter;
 private Button mBtnLogin2;
 private MaterialButton mBtnLogin3;

    List<Translate> data=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         adapter = new TranslateAdapter(getSampleData(), this);
        recyclerView.setAdapter(adapter);
        initEvent();
        initview();
        initButton();
    }
    private List<Translate> getSampleData()
    {

        data.add(new Translate("今天你有没有学习","肯定没有",R.drawable.cqupt__1_));
        data.add(new Translate("今天你开心了吗","今天你玩了吗", R.drawable.huawei));
        return data;
    }
    @Override
    public void onItemClick ( int position)
    {
        String newdata = "别的我不知道反正你点开了这个app";
        Log.d("HomeActivity", "Item clicked at position:" + position);
        adapter.notifyItemChanged(0, newdata);
    }

    public  class TranslateAdapter extends RecyclerView.Adapter<TranslateAdapter.ViewHolder>
    {
        private final List<Translate> datalist;
        private OnItemClickListener itemClickListener;
        public TranslateAdapter(List<Translate>datalist,OnItemClickListener listener)
        {
            this.datalist=datalist;
            this.itemClickListener=listener;
        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv,parent,false);
            return new ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder,int position,@NonNull List<Object> payloads)
        {
            if(!payloads.isEmpty())
            {
                for(Object payload:payloads)
                {
                    if(payload instanceof String)
                    {
                        holder.chineseTextView1.setText((String) payload);
                    }
                }
            }
            else
            {
                super.onBindViewHolder(holder,position,payloads);
            }
        }
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position)
        {
            Translate translate=datalist.get(position);
            if(translate!=null)
            {
                holder.chineseTextView1.setText(translate.getChinese1());
                holder.chineseTextView2.setText(translate.getChinese2());
                holder.imageImageView.setImageResource(translate.getImageResId());
            }
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(itemClickListener!=null&&position==0)
                    {
                        itemClickListener.onItemClick(position);
                    }
                }
            });


        }
        public int getItemCount()
        {
            return datalist.size();
        }
         class ViewHolder extends RecyclerView.ViewHolder
        {
            TextView chineseTextView1;
            TextView chineseTextView2;
            ImageView imageImageView;
            @SuppressLint("WrongViewCast")
            public ViewHolder(@NonNull View itemView)
            {
                super(itemView);
                chineseTextView1=itemView.findViewById(R.id.tv_home_use1);
                chineseTextView2=itemView.findViewById(R.id.tv_home_use2);
                imageImageView=itemView.findViewById(R.id.im_Home_image);
//               这个可以改每一个chineseTextView的值 itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v)
//                    {
//                        notifyItemChanged(getAdapterPosition()," update for 1");
//                    }
//                });

            }
        }
    }

    class Translate {
        private String chinese1;
        private String chinese2;
        private int imageResId;
        // 获取图片资源ID的方法
        public int getImageResId() {
            return imageResId;
        }
        public Translate(String chinese1,String chinese2,int imageResId)
        {
            this.chinese1=chinese1;
            this.chinese2=chinese2;
            this.imageResId=imageResId;
        }
        public String getChinese1() {
            return chinese1;
        }

        public String getChinese2() {
            return chinese2;
        }
    }
    private void initButton()
    {
        mBtnLogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login1();
            }
        });
    }
    private void login1()
    {
        Intent intent =new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.bilibili.com"));
        startActivity(intent);
    }

    @SuppressLint("WrongViewCast")
    private void initEvent()
    {
        mTvUsename=findViewById(R.id.tv_home_use);
        mBtnLogin1=findViewById(R.id.Button2);
        mBtnLogin2=findViewById(R.id.Button6);
        mBtnLogin3=findViewById(R.id.Button7);
    }
    private void Home()
    {
     mBtnLogin2.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v)
         {Success1();}
     });
     mBtnLogin3.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {Success2();}
     });
    }
    private void Success1()
    { Toast.makeText(this,"跳转成功",Toast.LENGTH_SHORT).show();
        MyActivity.StartActivity(this);
    }
    private void Success2()
    {
        Toast.makeText(this,"跳转成功",Toast.LENGTH_SHORT).show();
        My2Activity3.StartActivity(this);

    }
    private void initview()
    {
        Intent intent =getIntent();
        mTvUsename.setText(intent.getStringExtra("username"));//传到textview上
        Home();
    }
    public static void StartActivity(Context content, String username)
    {
        Intent intent= new Intent(content, HomeActivity.class);
        intent.putExtra("username",username);
        content.startActivity(intent);
    }
}