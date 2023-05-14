package com.android.testaar.t;

import com.android.testaar.Loge;

public class Demo {


    class Father<T> {
        T t;

        public Father(T t) {
            this.t = t;
            Loge.e(t.toString());
        }
    }

    interface TestInterface<E> {

    }

  public  class Child<T, E> extends Father<T> implements TestInterface<E> {

        public Child(T t) {
            super(t);
        }
    }
}