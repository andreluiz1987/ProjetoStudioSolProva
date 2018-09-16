package andrekabelim.br.projetostudiosolprova.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import andrekabelim.br.projetostudiosolprova.R;
import andrekabelim.br.projetostudiosolprova.adapters.GridViewAdapter;
import andrekabelim.br.projetostudiosolprova.helpers.AssetHelper;
import andrekabelim.br.projetostudiosolprova.helpers.MatrixHelper;
import andrekabelim.br.projetostudiosolprova.models.Caracter;
import andrekabelim.br.projetostudiosolprova.presenter.HomePresenter;
import andrekabelim.br.projetostudiosolprova.presenter.HomePresenterImpl;
import andrekabelim.br.projetostudiosolprova.presenter.HomeView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnTextChanged;

public class HomeActivity extends AppCompatActivity implements HomeView {

    private static String TAG = HomeActivity.class.getName();

    @BindView(R.id.txt_occurrences)
    TextView txtResult;

    @BindView(R.id.edt_entry)
    EditText edtWord;

    @BindView(R.id.img_clear)
    ImageView imgClear;

    @BindView(R.id.gw_palavras)
    GridView gwLetters;

    private HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GridViewAdapter customAdapter = new GridViewAdapter(getApplicationContext(), AssetHelper.getListLettersFromAsset(this, "caracters.txt"));
        gwLetters.setAdapter(customAdapter);

        homePresenter = new HomePresenterImpl(this);
    }

    @OnTextChanged(R.id.edt_entry)
    public void entryTextChanged(CharSequence text) {
        if (text.length() > 0) {
            changeVisibility(imgClear, View.VISIBLE);
        } else {
            changeVisibility(imgClear, View.INVISIBLE);
        }
    }

    @OnEditorAction(R.id.edt_entry)
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {

        if (actionId == EditorInfo.IME_ACTION_SEARCH) {

            String word = textView.getText().toString();

            Log.d(TAG, word);

            if(word.length() != 0) {
                homePresenter.onSearchClick(textView.getText().toString());
            } else {
                Toast.makeText(getContext(), "Informe uma palavra.", Toast.LENGTH_LONG).show();
            }
            hideKeyboard();
            return true;
        } else {
            return false;
        }
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @OnClick(R.id.img_clear)
    public void buttonClearClicked() {

        edtWord.setText("");

        clearGrid();

        changeVisibility(imgClear, View.INVISIBLE);
        changeVisibility(txtResult, View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showWordInGrid(List<List<Caracter>> occurrences) {
        showItemGridView(occurrences);
    }

    @Override
    public void showOccurrence(String word, List<List<Caracter>> occurrences) {

        changeVisibility(txtResult, View.VISIBLE);

        if (occurrences.size() > 0) {
            txtResult.setText((occurrences.size() + getString(R.string.message_found_occurrence).replace("[WORD]", "'" + word + "'")));
        } else {
            txtResult.setText((getString(R.string.message_not_found_occurrence).replace("[WORD]", "'" + word + "'")));
        }
    }

    @Override
    public void clearView() {
        clearGrid();
    }

    @Override
    public Context getContext() {
        return this;
    }

    private void clearGrid() {

        int size = gwLetters.getChildCount();

        for (int i = 0; i < size; i++) {
            ViewGroup gridChild = (ViewGroup) gwLetters.getChildAt(i);
            gridChild.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    private void showItemGridView(List<List<Caracter>> occurrences) {
        for (List<Caracter> characters : occurrences) {
            for (Caracter character : characters) {
                int position = (character.getPosition().getRow() * 10) + character.getPosition().getColumn();
                setHighlightGridView(position);
            }
        }
    }

    private void setHighlightGridView(int position) {

        int size = gwLetters.getChildCount();

        for (int i = 0; i < size; i++) {

            if (i == position) {

                ViewGroup gridChild = (ViewGroup) gwLetters.getChildAt(i);
                gridChild.setBackgroundResource(R.color.colorGreen);
            }
        }
    }

    private void changeVisibility(View view, int status) {
        view.setVisibility(status);
    }
}
