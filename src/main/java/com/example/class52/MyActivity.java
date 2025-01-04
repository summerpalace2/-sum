package com.example.class52;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class MyActivity extends AppCompatActivity
{
    private ArrayList<String>data=new ArrayList<>();
    private ViewPager2 viewPager;

    public static void StartActivity(Context content)
    {
        Intent intent= new Intent(content, MyActivity.class);
        content.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my);
        data.add("功能正在开发中😊");
        data.add("不知道怎么开发😒");
        data.add("程序员已摆烂🤣🤣");
        viewPager=findViewById(R.id.vp);
        viewPager.setAdapter(new VPAdapter(data));
        initView();
        initEvent();
    }
    public class VPAdapter extends RecyclerView.Adapter<VPAdapter.InnerHolder>
    {
        private ArrayList<String>data;
        public VPAdapter(ArrayList<String> data)
        {
            this.data=data;
        }
        @NonNull
        @Override
        public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType)
        {
            return new InnerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vp,parent,false));
        }
        @Override
        public void onBindViewHolder(@NonNull InnerHolder holder,int position)
        {
            holder.textView.setText(data.get(position).toString());
        }
        @Override
        public int getItemCount()
        {
            return data.size();
        }
        public static class InnerHolder extends  RecyclerView.ViewHolder
        {
            private TextView textView;
            @SuppressLint("WrongViewCast")
            public InnerHolder(@NonNull View itemView)
            {
                super(itemView);
                textView=itemView.findViewById(R.id.vp_text);
            }
        }

    }

    private void initEvent()
    {

    }

    private void initView()
    {

    }
}