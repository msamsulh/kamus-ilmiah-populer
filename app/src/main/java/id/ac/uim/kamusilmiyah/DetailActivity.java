package id.ac.uim.kamusilmiyah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    public static final String ITEM_WORD = "ITEM_WORD";
    public static final String ITEM_TRANSLATE = "ITEM_TRANSLATE";

    TextView tv_word;
    TextView tv_translate;
    ImageView share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tv_word = findViewById(R.id.tv_word);
        tv_translate = findViewById(R.id.tv_translate);
        share = findViewById(R.id.share);

        tv_word.setText(getIntent().getStringExtra(ITEM_WORD));
        tv_translate.setText(getIntent().getStringExtra(ITEM_TRANSLATE));

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBodyText = tv_word.getText().toString() + "\n\n" + tv_translate.getText().toString() + "  \n~  " + "Powered by : Kamus Ilmiah Populer";
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, tv_word.getText().toString());
                intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                startActivity(Intent.createChooser(intent, "Choose sharing method"));
            }
        });
    }
}
