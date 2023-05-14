package com.android.testaar.Test2;

import android.Manifest;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.testaar.Loge;
import com.android.testaar.R;
import com.android.testaar.databinding.DialogLayoutBinding;
import com.android.testaar.test1.StdBean;
import com.android.testaar.test3.MyFragment;
import com.google.android.material.snackbar.Snackbar;
import com.jakewharton.rxbinding4.view.RxView;
import com.jakewharton.rxbinding4.widget.RxAdapterView;
import com.jakewharton.rxbinding4.widget.RxTextView;
import com.jakewharton.rxbinding4.widget.TextViewAfterTextChangeEvent;
import com.jakewharton.rxbinding4.widget.TextViewEditorActionEvent;
import com.tbruyelle.rxpermissions3.RxPermissions;

import net.tsz.afinal.FinalDb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Cancellable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Unit;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MyFinalActivity extends AppCompatActivity {
    DialogLayoutBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DialogLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initRxjava();
        getpermission();
        db.deleteAll(StdBean.class);
        binding.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(findViewById(R.id.snackbar_linear), "Snackbar", Snackbar.LENGTH_SHORT).show();
            }
        });
        binding.recyclerviewTestAA.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerviewTestAA.setAdapter(new RecyclerView.Adapter() {

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test1_layout, null);
                return new MyViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                MyViewHolder myViewHolder = (MyViewHolder) holder;
                myViewHolder.button.setText("今晚吃鸡大吉大利" + position);
                myViewHolder.itemView.setTag(myViewHolder.button.getText());
            }

            @Override
            public int getItemCount() {
                return 100;
            }

            class MyViewHolder extends RecyclerView.ViewHolder {
                Button button;

                public MyViewHolder(@NonNull View itemView) {
                    super(itemView);
                    button = itemView.findViewById(R.id.test1_btn);
                }
            }
        });
        binding.confirm.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                binding.confirm.startDragAndDrop(null, new View.DragShadowBuilder(v), null, 0);
                return true;
            }
        });
        RxView.drags(binding.confirm).subscribe(new Consumer<DragEvent>() {
            @Override
            public void accept(DragEvent dragEvent) throws Throwable {
                switch (dragEvent.getAction()) {
                    case DragEvent.ACTION_DROP:
                        Button localState = (Button) dragEvent.getLocalState();
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        layoutParams.topMargin = 100;
                        layoutParams.leftMargin = 100;
                        ((ViewGroup) localState.getParent()).removeView(localState);
                        localState.setTextColor(Color.RED);
                        ((ViewGroup) localState.getParent().getParent()).addView(localState, layoutParams);
                        break;

                }
            }
        });
        RxView.drags(binding.snackbarLinear).subscribe(new Consumer<DragEvent>() {
            @Override
            public void accept(DragEvent dragEvent) throws Throwable {
                switch (dragEvent.getAction()) {
                    case DragEvent.ACTION_DROP:
                        Log.i("aaa", "释放拖拽的view");

                        if (dragEvent != null && dragEvent.getLocalState() != null) {
                            View localState = (View) dragEvent.getLocalState();
                            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            ((ViewGroup) localState.getParent()).removeView(localState);
                            binding.snackbarLinear.addView(localState, layoutParams);
                        }
                        break;
                }

            }
        });
        listview();

        RxTextView.afterTextChangeEvents(binding.editInput).subscribe(new Consumer<TextViewAfterTextChangeEvent>() {
            @Override
            public void accept(TextViewAfterTextChangeEvent textViewAfterTextChangeEvent) throws Throwable {
                Loge.e(String.valueOf(textViewAfterTextChangeEvent.getEditable()));
            }
        });
        RxView.clicks(binding.confirm).subscribe(new Consumer<Unit>() {
            @Override
            public void accept(Unit unit) throws Throwable {

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.snackbar_linear, new MyFragment(), "A")
                        .addToBackStack(null).commit();
            }
        });
        RxView.clicks(binding.cancel).subscribe(new Consumer<Unit>() {
            @Override
            public void accept(Unit unit) throws Throwable {
                Loge.e(getPackageName());

            }
        });


    }

    private void initRxjava() {
        Observable.just(1, 2, 3, 4, 5).switchMap(new Function<Integer, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Integer integer) throws Throwable {
                return Observable.just(integer);
            }
        }).subscribe(i -> {
                    Loge.e(i + "----");
                }
        );

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@io.reactivex.rxjava3.annotations.NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Throwable {

                    }
                });
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {

            }
        });
        //多次使用map，想用几个用几个
        Observable.just("Hello", "World")
                .map(new Function<String, Integer>() {

                    @Override
                    public Integer apply(String s) throws Throwable {
                        return s.hashCode();
                    }
                })
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Throwable {
                        return integer.intValue() + "";
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        Loge.e(s);
                    }
                });


        getSyncRequest();
    }

    public void getSyncRequest() {
        File file = new File("A/b/c.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream=new FileOutputStream(file);
            fileOutputStream.write("AAAAA".getBytes(),0,"AAAAA".length());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listview() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list.add("sss" + i);
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1);
        adapter.addAll(list);
        binding.listView.setAdapter(adapter);
        RxAdapterView.itemClicks(binding.listView).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Loge.e(integer + "被点击了");
                setTitle(adapter.getItem(integer));
            }
        });
        RxAdapterView.itemLongClicks(binding.listView).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                ArrayAdapter adapter1 = (ArrayAdapter) binding.listView.getAdapter();
                adapter1.remove(adapter1.getItem(integer));
            }
        });
        Observable.just(1).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Loge.e(Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.io()).subscribe();

    }

    private void getpermission() {
        Loge.e("申请权限");
        RxPermissions rxPermissions = new RxPermissions(MyFinalActivity.this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Throwable {
                if (aBoolean) {
                    Loge.e("权限同意了");
                    initData();
                    initView();

                } else {
                    Loge.e("没有权限");
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                }
            }
        });

    }

    private void initView() {
        List<StdBean> list = db.findAllByWhere(StdBean.class, "age>10");
        binding.title.setText(list.size() + "-");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(((StdBean) list.get(i)).getAge()).append(((StdBean) list.get(i)).getName());
        }
        binding.message.setText(stringBuilder.toString());
    }

    FinalDb db;

    private void initData() {
        db = FinalDb.create(this);
        for (int i = 0; i < 5; i++) {
            StdBean stdBean = new StdBean();
            stdBean.setAge(i * 5);
            stdBean.setName("我在遥望" + i);
            db.save(stdBean);
        }
    }


}
