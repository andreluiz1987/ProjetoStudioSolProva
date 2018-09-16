package andrekabelim.br.projetostudiosolprova.models;

import andrekabelim.br.projetostudiosolprova.enums.EnumDirection;

public class Caracter {

    private String caracter;
    private Position position;

    private EnumDirection enumDirection;

    public String getCaracter() {
        return caracter;
    }

    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public EnumDirection getEnumDirection() {
        return enumDirection;
    }

    public void setEnumDirection(EnumDirection enumDirection) {
        this.enumDirection = enumDirection;
    }
}
