package id.ac.uim.kamusilmiyah;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;

import id.ac.uim.kamusilmiyah.adapter.SearchAdapter;
import id.ac.uim.kamusilmiyah.data.KamusIlmiah;
import id.ac.uim.kamusilmiyah.helper.KamusIlmiahHelper;

public class KamusActivity extends AppCompatActivity implements MaterialSearchBar.OnSearchActionListener {

    MaterialSearchBar materialSearchBar;
    RecyclerView recyclerView;
    private KamusIlmiahHelper kamusHelper;
    private SearchAdapter adapter;

    private ArrayList<KamusIlmiah> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamus);

        materialSearchBar = findViewById(R.id.search_bar);
        recyclerView = findViewById(R.id.recycler_view);

        materialSearchBar.setOnSearchActionListener(this);
        kamusHelper = new KamusIlmiahHelper(this);

        setupList();
        loadData();
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
        loadData(String.valueOf(text));
    }

    @Override
    public void onButtonClicked(int buttonCode) {

    }

    private void setupList() {
        adapter = new SearchAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void loadData(String search) {
        try {
            kamusHelper.open();
            if (search.isEmpty()) {
                list = kamusHelper.getAllData();
            } else {
                list = kamusHelper.getDataByName(search);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            kamusHelper.close();
        }
        adapter.replaceAll(list);
    }

    private void loadData() {
        loadData("");
    }
}
