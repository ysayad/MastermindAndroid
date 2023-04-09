package dev.android.sae.mastermind.model.menu;


/**
 * Le modèle du menu
 */
public class ModelMenu {
/**-------------------- Attributs -----------------------------------------------------------  */

    /**
     * Flag autorisation jetons vides
     */
    private boolean m_emptyPawnFlag;


/**-------------------- Méthodes ------------------------------------------------------------  */

    /**
     * Constructeur
     */
    public ModelMenu(){
        this.m_emptyPawnFlag = false;
    }

    public ModelMenu(boolean emptyPawnFlag){
        this.m_emptyPawnFlag = emptyPawnFlag;
    }

    /**
     * Active/desactive les jetons vides
     * @param emptyPawnFlag Le flag permettant de savoir si les jetons vides sont autorisés
     */
    public void setEmptyPawnActivated(boolean emptyPawnFlag){
        this.m_emptyPawnFlag = emptyPawnFlag;
    }

    /**
     * Geter du flag d'activation des jetons vides
     * @return le flag d'activation des jetons vides
     */
    public boolean getEmptyPawnFlag(){
        return this.m_emptyPawnFlag;
    }
}
