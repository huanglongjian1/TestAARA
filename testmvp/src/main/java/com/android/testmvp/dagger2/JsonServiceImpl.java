package com.android.testmvp.dagger2;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.google.gson.Gson;

import java.lang.reflect.Type;

// 如果需要传递自定义对象，新建一个类（并非自定义对象类），
// 然后实现 SerializationService, 并使用@Route注解标注(方便用户自行选择序列化方式)，例如
@Route(path = "/self_service/json")
public class JsonServiceImpl implements SerializationService {
    Gson gson;

    @Override
    public void init(Context context) {
        gson = new Gson();
    }

    //json字符串转换为对象
    @Override
    public <T> T json2Object(String text, Class<T> clazz) {
        return gson.fromJson(text, clazz);
    }

    //自定义对象转换为json字符串
    @Override
    public String object2Json(Object instance) {
        return gson.toJson(instance);
    }

    @Override
    public <T> T parseObject(String input, Type clazz) {
        return gson.fromJson(input, clazz);
    }
}