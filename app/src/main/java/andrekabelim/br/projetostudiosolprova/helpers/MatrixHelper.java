package andrekabelim.br.projetostudiosolprova.helpers;

import java.util.ArrayList;
import java.util.List;

import andrekabelim.br.projetostudiosolprova.enums.EnumDirection;
import andrekabelim.br.projetostudiosolprova.models.Caracter;
import andrekabelim.br.projetostudiosolprova.models.Position;

public class MatrixHelper {

    private char[][] arrLetter;

    /*
    Metodo que recebe uma palavra e localiza as ocorrências na matrix de letras
     */
    public List<List<Caracter>> getOccurrencesWord(String word, List<String> lstLetter) {

        arrLetter = transformListToArray(lstLetter);

        return getWordInMatrix(word, arrLetter);
    }

    /*
    Transforma a lista de letras em uma matriz bidimensional
     */
    private char[][] transformListToArray(List<String> lstLetter) {

        int size = 10;
        char[][] arrLetter = new char[size][size];

        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arrLetter[i][j] = lstLetter.get(count).charAt(0);
                count++;
            }
        }

        return arrLetter;
    }

    private List<List<Caracter>> getWordInMatrix(String word, char[][] arrLetter) {

        List<List<Caracter>> occurrences = new ArrayList<>();
        List<Caracter> lettersFind = new ArrayList<>();

        Caracter characterAux;
        List<Caracter> wordFind;

        try {

            word = word.toUpperCase();

            char c = word.charAt(0);

            //Localiza todas as ocorrências da primeira letra da palavra na matriz
            for (int i = 0; i < arrLetter.length; i++) {

                for (int j = 0; j < arrLetter.length; j++) {

                    if (c == arrLetter[i][j]) {

                        Caracter character = new Caracter();
                        character.setCaracter(String.valueOf(c));
                        character.setPosition(new Position(i, j));
                        character.setEnumDirection(EnumDirection.NONE);

                        lettersFind.add(character);
                    }
                }
            }

            //Para cada letra em lettersFind busca as adjacentes correspondentes a palavra digitada
            for (Caracter character : lettersFind) {

                wordFind = new ArrayList<>();

                //adiciona letra na lista de palavras
                wordFind.add(character);

                characterAux = character;

                //percorre cada caracter da palavra, desconsidere o primeiro pois ele já pertence a lista
                for (int i = 1; i < word.length(); i++) {

                    //recupera as letras adjacentes
                    List<Caracter> childs = getChilds(characterAux.getPosition().getRow(), characterAux.getPosition().getColumn());

                    //existe o caracter na lista de letras adjacentes
                    Caracter letter = getLetterInChilds(childs, word.charAt(i), wordFind.get(wordFind.size() - 1).getEnumDirection());

                    if (letter != null) {
                        //adiciona na lista
                        wordFind.add(letter);
                        //atualiza variavel para proxima busca
                        characterAux = letter;
                    }
                }

                //se a palavra localiza no matrix for do mesmo tamanha da palavra, add ocorrência
                if (wordFind.size() == word.length())
                    occurrences.add(wordFind);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return occurrences;
    }

    /*
    Método que retorna
     */
    private Caracter getLetterInChilds(List<Caracter> childs, char c, EnumDirection lastDirection) {
        for (Caracter character : childs) {
            //a letra encontrada tem a direção diferente da anterior?
            if (character.getCaracter().equals(String.valueOf(c)) && (lastDirection == EnumDirection.NONE || lastDirection != character.getEnumDirection())) {
                return character;
            }
        }

        return null;
    }

    /*
    Método que retorna as palavras adjacentes de uma letra na matriz
     */
    private List<Caracter> getChilds(int line, int column) {

        List<Caracter> lstChilds = new ArrayList<>();

        if (line + 1 < arrLetter.length) {
            Caracter character = new Caracter();
            character.setCaracter(String.valueOf(arrLetter[line + 1][column]));
            character.setPosition(new Position(line + 1, column));
            character.setEnumDirection(EnumDirection.LEFT);
            lstChilds.add(character);
        }

        if (column + 1 < arrLetter.length) {
            Caracter character = new Caracter();
            character.setCaracter(String.valueOf(arrLetter[line][column + 1]));
            character.setPosition(new Position(line, column + 1));
            character.setEnumDirection(EnumDirection.RIGTH);
            lstChilds.add(character);
        }

        return lstChilds;
    }
}
