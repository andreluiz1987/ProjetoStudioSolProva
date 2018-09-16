package andrekabelim.br.projetostudiosolprova.helpers;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AssetHelper {

    private static String TAG = AssetHelper.class.getName();

    /*
    Retorna o conteúdo do arquivo em uma lista de string
     */
    public static List<String> getListLettersFromAsset(Context context, String fileName) {

        List<String> lstLetters = new ArrayList<>();

        AssetManager manager = context.getAssets();
        InputStream input = null;
        try {
            input = manager.open(fileName);
        } catch (IOException ioe) {
            Log.e(TAG, "Ocorreu um erro ao abrir o arquivo.");
            ioe.printStackTrace();
        }
        try {
            char current;
            while (input.available() > 0) {
                int data = input.read();
                if (data != 13 && data != 10 && data != 32) {
                    current = (char) data;
                    Log.d(TAG, "letter: " + current);
                    lstLetters.add(String.valueOf(current));
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return lstLetters;
    }
}
