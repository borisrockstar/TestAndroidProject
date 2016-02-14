package boris.test.testproject.ApiManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class BackgroundWorkTask extends AsyncTask<Void, Void, Void> {

    private Context context;
    private String message;
    ProgressDialog progressDlg;
    public BackgroundWorkTask(Context context, String message)
    {
        this.context = context;
        this.message = message;
    }
    @Override
    protected Void doInBackground(Void... params) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void onPreExecute() {
        if( context != null && message != null)
            progressDlg = ProgressDialog.show(context, "", message, true);
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void result) {
        if( progressDlg != null )
            progressDlg.dismiss();
        // Put the list of todos into the list view
    }
}
