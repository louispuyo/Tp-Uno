package uno;

/**
 * enumeration representant les differents symboles que les cartes du jeu peuvent avoir
 * @author louispuyo
 *
 */
public enum Symbole {
	
	PASSER("Passer"),
	INVERSER("Inverser"),
	PLUS2("+2"),
	PLUS4("+4"),
	JOKER("Joker");
	
	/**
	 * la valeur du symbole, en fait c'est une chaene de caracteres contenant le nom du symbole
	 * utile dans la methode toString()
	 */
	private String valeur;
	
	/**
	 * constructeur
	 * @param valeur : la valeur du symbole
	 */
	private Symbole(String valeur) {
		this.valeur = valeur;
	}
	
	/**
	 * @return la valeur du symbole
	 */
	public String getValeur() {
		return valeur;
	}
}
