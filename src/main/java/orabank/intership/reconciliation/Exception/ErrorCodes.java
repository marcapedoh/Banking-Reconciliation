package orabank.intership.reconciliation.Exception;

public enum ErrorCodes {

  FILE_EMPTY(100),
  UTILISATEUR_NOT_FOUND(1000),
  UTILISATEUR_NOT_VALID(1001),
    COLONNE_NOT_FOUND(2000),
    COLONNE_NOT_VALID(2001),
    PARTENAIRE_NOT_FOUND(3000),
    PARTENAIRE_NOT_VALID(3001),
    REPERTOIRE_NOT_VALID(4000);

    ErrorCodes(int code){
        this.errorCode=code;
    }
    private int errorCode;

    public int getErrorCode(){
        return this.errorCode;
    }
}
