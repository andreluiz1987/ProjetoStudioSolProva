package andrekabelim.br.projetostudiosolprova.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import andrekabelim.br.projetostudiosolprova.R;

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private List<String> letters;
    private  LayoutInflater inflate;

    public GridViewAdapter(Context applicationContext, List<String> letters) {
        this.context = applicationContext;
        this.letters = letters;
        inflate = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return letters.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflate.inflate(R.layout.gridview_item, null);
        TextView icon = view.findViewById(R.id.txt_letter);
        icon.setText(letters.get(i));
        return view;
    }
}
