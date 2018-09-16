package andrekabelim.br.projetostudiosolprova.presenter;

import java.util.List;

import andrekabelim.br.projetostudiosolprova.R;
import andrekabelim.br.projetostudiosolprova.helpers.AssetHelper;
import andrekabelim.br.projetostudiosolprova.helpers.MatrixHelper;
import andrekabelim.br.projetostudiosolprova.models.Caracter;

public class HomePresenterImpl implements HomePresenter {

    private HomeView homeView;

    public HomePresenterImpl(HomeView homeView) {
        this.homeView = homeView;
    }

    @Override
    public void onSearchClick(String word) {

        homeView.clearView();
        findWord(word);
    }

    private void findWord(String word) {

        MatrixHelper matrixHelper = new MatrixHelper();

        List<List<Caracter>> occurrences = matrixHelper.getOccurrencesWord(word, AssetHelper.getListLettersFromAsset(homeView.getContext(), "caracters.txt"));

        homeView.showOccurrence(word, occurrences);

        homeView.showWordInGrid(occurrences);
    }
}
