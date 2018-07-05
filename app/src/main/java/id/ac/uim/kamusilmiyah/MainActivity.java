package id.ac.uim.kamusilmiyah;

import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;

import id.ac.uim.kamusilmiyah.adapter.SearchAdapter;
import id.ac.uim.kamusilmiyah.data.KamusIlmiah;
import id.ac.uim.kamusilmiyah.helper.KamusIlmiahHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button kamus, tentang, keluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kamus = findViewById(R.id.kamus);
        tentang = findViewById(R.id.tentang);
        keluar = findViewById(R.id.keluar);

        kamus.setOnClickListener(this);
        tentang.setOnClickListener(this);
        keluar.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.kamus):
                startActivity(new Intent(getApplicationContext(), KamusActivity.class));
                break;
            case (R.id.tentang):
                startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                break;
            case (R.id.keluar):
                finish();
                break;
        }
    }
}

