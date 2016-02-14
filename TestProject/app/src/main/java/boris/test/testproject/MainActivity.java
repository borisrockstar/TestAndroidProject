package boris.test.testproject;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Observable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONObject;

import boris.test.testproject.ApiManager.ApiService;
import boris.test.testproject.ApiManager.BackgroundWorkTask;
import boris.test.testproject.ApiManager.WebServiceMethods;
import boris.test.testproject.model.SafeJSONObject;
import boris.test.testproject.model.inputData;
import boris.test.testproject.model.track;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView tvPinCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        tvPinCode = (TextView)findViewById(R.id.textPinCode);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void OnShowPinCode(View v){

        new BackgroundWorkTask(this, "Getting PinCode"){
            @Override
            protected Void doInBackground(Void... params) {
                // TODO Auto-generated method stub
                //Call Webservice//

                try {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://bebetrack.com")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    ApiService service = retrofit.create(ApiService.class);
                    inputData data = new inputData("123456789", "123");
                    retrofit2.Call<track> result = service.getPinCode(data); //service.getPinCode("123456789", "123");

                    result.enqueue(new Callback<track>() {
                        @Override
                        public void onResponse(retrofit2.Call<track> call, Response<track> response) {
                                if(response != null){
                                    SaveTokenToAccountManager(response.body().getToken());
                                    DisplayPinCode(response.body().getPinCode());
                                }
                        }

                        @Override
                        public void onFailure(retrofit2.Call<track> call, Throwable t) {

                        }
                    });
                }catch (Exception e){

                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                // Put the list of todos into the list view
                super.onPostExecute(result);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void SaveTokenToAccountManager(String token){
        SharedPreferences prefs = this.getSharedPreferences(
                "boris.test.testproject", Context.MODE_PRIVATE);
        prefs.edit().putString("token", token).apply();
    }

    public void DisplayPinCode(String pincode){
        tvPinCode.setText(pincode);
    }
}
