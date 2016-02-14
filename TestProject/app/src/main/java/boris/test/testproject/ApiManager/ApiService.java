package boris.test.testproject.ApiManager;

import boris.test.testproject.model.inputData;
import boris.test.testproject.model.track;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by elcap on 2/14/16.
 */
public interface ApiService {

    @Headers(
        "Content-Type: application/json")
    @POST("/api/create")
    Call<track> getPinCode(@Body inputData body);
}
