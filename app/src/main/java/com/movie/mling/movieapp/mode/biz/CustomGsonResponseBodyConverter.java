package com.movie.mling.movieapp.mode.biz;


/**
 * Created by zhougang on 2017/1/9
 */
//public class CustomGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
//
//    private final Gson gson;
//    private final TypeAdapter<T> adapter;
//
//    CustomGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
//        this.gson = gson;
//        this.adapter = adapter;
//    }
//
//    @Override
//    public T convert(ResponseBody value) throws IOException {
////      JsonReader jsonReader = gson.newJsonReader(value.charStream());
//        try {
//            //正常情况下返回的可能是对象或是数组，没有值的情况下却返回一个空字符串，解析会有问题，这里做一下处理。将 ‘ "datas":"",’ 直接去掉
//            String str1 = "\"data\":"+"\"\",";
//            String str2 = "";
//            return adapter.fromJson(value.string().replace(str1,str2));
//        } finally {
//            value.close();
//        }
//    }
//}
