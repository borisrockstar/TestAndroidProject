package boris.test.testproject.ApiManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.util.Log;

//
public class JSONParser {
	private static final String TAG = "HttpClient";

    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    public JSONParser() {

    }

	/**
	 * send HttpPost
	 */
	public static String SendHttpPostRequest(String methodName, String params) {
		String endPointUrl  = Constants.BASE_URL +  methodName;
		Log.i(TAG + " Method URL", Constants.BASE_URL + "/" + methodName);

		try {

			JSONObject cred   = new JSONObject();
			cred.put("phone","1234567890");
			cred.put("internationalCode", "123");

			URL url = new URL(endPointUrl);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestProperty("User-Agent", "");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");

			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(cred.toString());
			writer.flush();

			connection.connect();

			InputStream inputStream = connection.getInputStream();
			String resultString = convertStreamToString(inputStream);
					//Log.i(TAG,resultString)
			return resultString;
		}
		catch (Exception e) {
			int x = 0;
			x = x+1;
		}
		return null;
	}

	/**
	 * convert stream to string
	 */
	private static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//Log.i("Response", sb.substring(1, sb.length() - 2));
		//return sb.substring(1, sb.length() - 2).replace("\\\"", "\"");
		Log.i("Response", sb.toString());
		return sb.toString();
	}
}