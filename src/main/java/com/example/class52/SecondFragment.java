package com.example.class52;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment
{
    @Override
    public void onCreate(@Nullable Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
    }
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @Nullable Bundle saveInstanceState)
    {
        return inflater.inflate(R.layout.secondfragment,container,false);
    }

}

