package com.movie.mling.movieapp.mode.biz;



//public final class CustomGsonConverterFactory extends Converter.Factory {
//    /**
//     * Create an instance using a default {@link Gson} instance for conversion. Encoding to JSON and
//     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
//     */
//    public static CustomGsonConverterFactory create() {
//        return create(new Gson());
//    }
//
//    /**
//     * Create an instance using {@code gson} for conversion. Encoding to JSON and
//     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
//     */
//    public static CustomGsonConverterFactory create(Gson gson) {
//        return new CustomGsonConverterFactory(gson);
//    }
//
//    private final Gson gson;
//
//    private CustomGsonConverterFactory(Gson gson) {
//        if (gson == null) throw new NullPointerException("gson == null");
//        this.gson = gson;
//    }

//    @Override
//    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
//                                                            Retrofit retrofit) {
//        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
//        return new CustomGsonResponseBodyConverter<>(gson, adapter);
//    }
//
//    @Override
//    public Converter<?, RequestBody> requestBodyConverter(Type type,
//                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
//        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
//        return new CustomGsonRequestBodyConverter<>(gson, adapter);
//    }
//}
