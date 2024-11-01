package inaka.com.mangosta.xmpp.upload;


import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Url;


public interface UploadService {
    @PUT
    @Multipart
    Call<ResponseBody> uploadImage(
            @Url String url,
            @Part MultipartBody.Part image
    );


}
