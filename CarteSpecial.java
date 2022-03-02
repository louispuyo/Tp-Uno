package uno;

/**
 * cette classe represente les cartes speciales du jeu
 * @author louispuyo
 *
 */
public class CarteSpecial extends Carte {

	/**
	 * symbole de la carte
	 * @see Symbole
	 */
	private Symbole symbole;
	
	/**
	 * constructeur
	 * @param couleur : la couleur de la carte
	 * @param symbole : le symbole de la carte
	 */
	public CarteSpecial(Couleur couleur, Symbole symbole) {
		super(couleur);
		this.symbole = symbole;
	}
	
	/**
	 * retourne une chaene contenant la representation de la carte 
	 */
	@Override
	public String toString() {
		return super.toString() + " " + symbole.getValeur();
	}
	
	/**
	 * @return true : si la carte courante est compatible avec celle passee en parametre (eed : jouable)
	 * @return false : sinon
	 * @param carte : la carte e comparer avec l'objet courant
	 */
	@Override
	public boolean compatible(Carte carte) {
		if (couleur == Couleur.NOIR) {
			return true;	// Les cartes noirs ( nottament le Joker et la carte +4 )
			// peuvent etre deposee sur n'importe qu'elle autre carte
		}
		if (carte instanceof CarteSpecial) {	//	CarteSpecial
			// meme couleur ou meme symbole ?
			return ( carte.couleur == couleur ) || ( ((CarteSpecial) carte).symbole == symbole );	// meme couleur ou meme symbole
		} else {	// CarteChiffre
			// meme couleur ?
			return carte.couleur == couleur;	// meme couleur
		}
	}
	
	/**
	 * permet de changer la couleur de la carte speciale et ceci n'est possible
	 * que si la couleur initiale de la carte est NOIR
	 * @param couleur : peut etre ROUGE, JAUNE, VERT ou BLEU
	 */
	public void setCouleur(Couleur couleur) {
		if (this.couleur == Couleur.NOIR) {
			// On ne peut changer la couleur que quand la couleur initiale de
			// la carte est noire : Joker ou +4
			this.couleur = couleur;
		}
	}

	/**
	 * permet de retourner le symbole de la carte speciale
	 * @return le symbole de la carte speciale
	 */
	public Symbole getSymbole() {
		return symbole;
	}
	
	

}
