package id.ac.uim.kamusilmiyah.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import id.ac.uim.kamusilmiyah.DetailActivity;
import id.ac.uim.kamusilmiyah.R;
import id.ac.uim.kamusilmiyah.data.KamusIlmiah;

public class SearchViewHolder extends RecyclerView.ViewHolder {

    TextView tv_word;
    TextView tv_translate;

    public SearchViewHolder(View itemView) {
        super(itemView);
        tv_word = itemView.findViewById(R.id.tv_word);
        tv_translate = itemView.findViewById(R.id.tv_translate);
    }

    public void bind(final KamusIlmiah item) {
        tv_word.setText(item.getWord());
        tv_translate.setText(item.getTranslate());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.ITEM_WORD, item.getWord());
                intent.putExtra(DetailActivity.ITEM_TRANSLATE, item.getTranslate());
                itemView.getContext().startActivity(intent);
            }
        });
    }
}
