package andrekabelim.br.projetostudiosolprova.presenter;

import android.content.Context;

import java.util.List;

import andrekabelim.br.projetostudiosolprova.models.Caracter;

public interface HomeView {
    Context getContext();
    void showWordInGrid(List<List<Caracter>> occurrences);
    void showOccurrence(String word, List<List<Caracter>> occurrences);
    void clearView();
}
