package uno;

/**
 * enumeration presentant les couleurs possibles des cartes 
 * @author louispuyo
 *
 */
public enum Couleur {
	
	ROUGE("Rouge"),
	JAUNE("Jaune"),
	VERT("Vert"),
	BLEU("Bleu"),
	NOIR("Noir");
	

	private String valeur;
	
	/**
	 * contructeur
	 * @param valeur : la valeur de la couleur
	 */
	private Couleur(String valeur) {
		this.valeur = valeur;
	}
	
	/**
	 * @return la valeur de la couleur
	 */
	public String getValeur() {
		return valeur;
	}
}
