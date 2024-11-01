package inaka.com.mangosta.http;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class RetrofitUtils {
    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://mongoose-im-message-resource-upload-test.s3.ap-southeast-1.amazonaws.com/") // 必须提供 baseUrl，但可以用动态 url 覆盖
            .client(new OkHttpClient.Builder().build())
            .build();


    public static <T> T getApi(Class<T> tClass) {
        return retrofit.create(tClass);
    }
}
