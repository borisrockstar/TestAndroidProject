package boris.test.testproject.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by elcap on 2/14/16.
 */
public class inputData {
    @SerializedName("phone")
    private String phone;
    @SerializedName("internationalCode")
    private String internationalCode;

    public inputData(){}
    public inputData(String phone, String internationalCode){
        this.phone = phone;
        this.internationalCode = internationalCode;
    }
}
