package com.android.testmvp.viewpage2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.testmvp.R;
import com.android.testmvp.SettingsActivity;
import com.android.testmvp.databinding.SettingsActivityBinding;

import java.util.List;

public class Page2_Adapter extends RecyclerView.Adapter<Page2_Adapter.Page2_ViewHolder> {
    List<Fragment> list;

    public Page2_Adapter(List<Fragment> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Page2_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SettingsActivityBinding binding = SettingsActivityBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new Page2_ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Page2_ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Page2_ViewHolder extends RecyclerView.ViewHolder {
        SettingsActivityBinding binding;

        public Page2_ViewHolder(@NonNull SettingsActivityBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
