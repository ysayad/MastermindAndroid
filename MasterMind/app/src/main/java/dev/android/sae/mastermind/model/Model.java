package dev.android.sae.mastermind.model;

public class Model {
/**-------------------- Attributs -----------------------------------------------------------  */

    /**
     * Flag autorisation jetons vides
     */
    private boolean m_emptyPawnFlag;


/**-------------------- Méthodes ------------------------------------------------------------  */

    /**
     * Constructeur
     */
    public Model(){
        this.m_emptyPawnFlag = false;
    }

    /**
     * Active/desactive les jetons vides
     * @param emptyPawnFlag Le flag permettant de savoir si les jetons vides sont autorisés
     */
    public void setEmptyPawnActivated(boolean emptyPawnFlag){
        this.m_emptyPawnFlag = emptyPawnFlag;
    }
}
