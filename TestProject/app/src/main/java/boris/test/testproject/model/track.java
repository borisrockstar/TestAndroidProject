package boris.test.testproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by elcap on 2/14/16.
 */
public class track {

    @SerializedName("phone")
    private String phone;
    @SerializedName("internationalCode")
    private String internationalCode;
    @SerializedName("pin")
    private String pin;
    @SerializedName("token")
    private String token;

    public String getPinCode(){
        return this.pin;
    }

    public String getToken(){
        return this.token;
    }
}
