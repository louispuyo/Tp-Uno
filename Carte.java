package uno;

/**
 * cette classe regroupe les aspets communs de toutes les cartes
 * @author louispuyo
 *
 */
public abstract class Carte {

	/**
	 * la couleur de la carte
	 * @see Couleur
	 * 
	 */
	protected Couleur couleur;
	
	/**
	 * constructeur
	 * @param couleur : la couleur de la carte
	 */
	public Carte(Couleur couleur) {
		this.couleur = couleur;
	}
	
	/**
	 * @param carte : la carte qu'on voit sur le sommet du talon
	 * @return true : Si la carte courante peut etre deposee sur l'objet ( carte )
	 * @return false : sinon
	 * abstraite carte le mechanisme de comparaison differe entre la carte chiffre et la carte speciale
	 */
	public abstract boolean compatible(Carte carte);
	
	/**
	 * retourne une chaene descrivant l'objet courant
	 */
	public String toString() {
		return couleur.getValeur();
	}

	/**
	 * permet de retourner la couleur de la carte courante
	 * @return la couleur de la carte courante
	 */
	public Couleur getCouleur() {
		return couleur;
	}
	
}
