package id.ac.uim.kamusilmiyah;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.SQLException;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import id.ac.uim.kamusilmiyah.data.KamusIlmiah;
import id.ac.uim.kamusilmiyah.helper.KamusIlmiahHelper;

public class GettingStartedActivity extends AppCompatActivity {

    TextView tv_load;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started);
        tv_load = findViewById(R.id.tv_load);
        progressBar = findViewById(R.id.progress_bar);

        transparentToolbar();
        ImageView imageViewLogo = findViewById(R.id.img_logo);
        new LoadData().execute();
    }

    private class LoadData extends AsyncTask<Void, Integer, Void> {
        KamusIlmiahHelper kamusHelper;
        Applications appsPreference;
        double progress;
        double maxprogress = 100;

        @Override
        protected void onPreExecute() {
            kamusHelper = new KamusIlmiahHelper(GettingStartedActivity.this);
            appsPreference = new Applications(GettingStartedActivity.this);
        }

        @SuppressWarnings("WrongThread")
        @Override
        protected Void doInBackground(Void... params) {
            Boolean firstRun = appsPreference.getFirstRun();
            if (firstRun) {
                ArrayList<KamusIlmiah> kamusMadura = preLoadRaw(R.raw.kamus_ilmiah_populer);

                publishProgress((int) progress);

                try {
                    kamusHelper.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                Double progressMaxInsert = 100.0;
                Double progressDiff = (progressMaxInsert - progress) / (kamusMadura.size());

                kamusHelper.insertTransaction(kamusMadura);
                progress += progressDiff;
                publishProgress((int) progress);

                kamusHelper.close();
                appsPreference.setFirstRun(false);

                publishProgress((int) maxprogress);
            } else {
                tv_load.setVisibility(View.INVISIBLE);
                try {
                    synchronized (this) {
                        this.wait(1000);
                        publishProgress(50);

                        this.wait(300);
                        publishProgress((int) maxprogress);
                    }
                } catch (Exception e) {
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void result) {
            Intent i = new Intent(GettingStartedActivity.this, MainActivity.class);
            startActivity(i);

            finish();
        }
    }

    public ArrayList<KamusIlmiah> preLoadRaw(int data) {
        ArrayList<KamusIlmiah> kamusModels = new ArrayList<>();
        BufferedReader reader;
        try {
            Resources res = getResources();
            InputStream raw_dict = res.openRawResource(data);

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            String line = null;
            do {
                line = reader.readLine();
                String[] splitstr = line.split(" -> ");
                KamusIlmiah kamusModel;
                kamusModel = new KamusIlmiah(splitstr[0], splitstr[1]);
                kamusModels.add(kamusModel);
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kamusModels;
    }

    private void transparentToolbar() {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
