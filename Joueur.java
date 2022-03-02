package uno;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * cette classe represente le joueur, ces attributes et ces actions 
 * @author louispuyo
 *
 */
public class Joueur {

	/**
	 * la main du joueur contenant toutes ses cartes
	 */
	private Main main;
	/**
	 * le pseudo du joueur
	 */
	private String pseudo;
	/**
	 * la pioche : la pile des cartes oe le joueur peut prendre des cartes dans le cas oe il n'a pas de cartes jouables
	 */
	private Pioche pioche;
	/**
	 * le talon : la pile des cartes oe les joueurs depose leurs cartes
	 */
	private Talon talon;

	/**
	 * contructeur
	 * @param pseudo : pseudo du joueur
	 * @param pioche : la pioche
	 * @param talon : le talon
	 */
	public Joueur(String pseudo, Pioche pioche, Talon talon) {
		this.pseudo = pseudo;
		this.pioche = pioche;
		this.talon = talon;
		// nouvelle main pour chaque joueur
		main = new Main();
	}
	
	/**
	 * permet de piocher une carte de la pioche vers la main du joueur
	 * @return la carte tiree
	 */
	public Carte prendreCarte() {
		if (pioche.nbCartes() == 0) {	
			System.out.println("La pioche est vide !");

			Carte sommetTalon = talon.depiler();
			while (talon.nbCartes() != 0) {	
				pioche.empiler(talon.depiler());
			}
			pioche.melanger();
			talon.empiler(sommetTalon);	
		}
		Carte carte = pioche.depiler();
		main.ajouter(carte);
		return carte;
	}
	
	/**
	 * cette methode permet d'afficher les cartes dans la main du joueur courant
	 */
	public void afficherMain() {
		String str = "";
		ArrayList<Carte> cartes = main.getCartes();
		if (cartes.isEmpty()) {
			str = "[VIDE]";
		}
		for(int i = 0; i < cartes.size(); ++i) {
			Carte carte = cartes.get(i);
			str = str + i + ") " + carte.toString();
			if (carte.compatible(talon.sommet())) {
				str = str + " [Jouable]";
			}
			if (i != cartes.size() - 1) {	// if this isn't the last iteration
				str = str + "\n";	// add a new line
			}
		}
		System.out.println(str);
	}
	
	/**
	 * permet au joueur de jouer son tour
	 */
	public void jouerTour() {	// TODO : ajouter le cas oe on a des doublons ! on doit se debarasser de toutes les occurences de la carte jouee !
		talon.afficherSommet();
		afficherMain();
		if (nbCartesJouables() == 0) {
			System.out.println("Vous n'avez pas de cartes jouables ! vous devez piocher !");
			pause();
			Carte c = prendreCarte();
			System.out.println("La carte piochee est : " + c);
			if (!c.compatible(talon.sommet())) {	// la carte recemment piochee n'est pas compatible avec le sommet du talon
				System.out.println("Pas de chance ! vous n'avez encore pas de cartes jouables, vous devez passer le tour");
				System.out.println("----------------------------------");
				return;	// passer le tour <=> quitter la methode
			} else {
				afficherMain();
			}
		}
		jouerCarte();
		System.out.println("----------------------------------");
	}
	
	/**
	 * permet d'attendre un peu pour que l'utilisateur arrive e lire le message affiche
	 */
	private void pause() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * permet au joueur de jouer une carte
	 */
	private void jouerCarte() {
		boolean carteJouable = false;
		int num = -1;
		ArrayList<Carte> cartes = main.getCartes();
		
		System.out.println("Vous avez " + nbCartesJouables() + " cartes jouables");
		while (!carteJouable) {	// tant que la carte n'est pas jouable
			num = Clavier.lireEntier(cartes);
			Carte carteAjouer = cartes.get(num);
			if (carteAjouer.compatible(talon.sommet())) {
				carteJouable = true;
			} else {
				System.out.println(carteAjouer + " ne peut pas etre jouee sur " + talon.sommet());
			}
		}
		
		Carte carte = main.retirer(num);
		if (carte.getCouleur() == Couleur.NOIR) {	// TODO : ce test doit etre deleguee e la classe Jeu
			// On doit demander une couleur au joueur
			System.out.println("Vous devez choisir une couleur");
			Couleur couleur = donnerCouleur();
			// Si elle est de couleur noir, on est ser qu'elle est speciale !
			((CarteSpecial) carte).setCouleur(couleur);
		}
		talon.empiler(carte);
		System.out.println(pseudo + " a joue " + carte);
	}

	/*
	public void jouerTour() {
		Scanner scanner = new Scanner(System.in);
		boolean carteJouable = false;
		boolean horsBornes;
		int num = -1;
		ArrayList<Carte> cartes = main.getCartes();
		
		System.out.println("Vous avez " + this.nbCartesJouables() + " cartes jouables");
		
		while (!carteJouable) {	// tant que la carte n'est pas jouable
			do {
				System.out.print("? >> ");
				num = scanner.nextInt();
				horsBornes = num < 0 || num >= cartes.size();
				if (horsBornes) {
					System.out.println("indice hors bornes ! [0.." + (cartes.size() - 1) + "]");
				}
			} while (horsBornes);
			Carte carteAjouer = cartes.get(num);
			if (carteAjouer.compatible(talon.sommet())) {
				carteJouable = true;
			} else {
				System.out.println(carteAjouer + " ne peut pas etre jouee sur " + talon.sommet());
			}
		}
		
		scanner.close();
		Carte carte = main.retirer(num);
		talon.empiler(carte);
		System.out.println(nom + " a joue " + carte);
	}
	*/
	
	/**
	 * permet au joueur de donner une couleur
	 * @return la couleur choisie
	 */
	private Couleur donnerCouleur() {
		HashMap<Integer, Couleur> menu = new HashMap<>();
		int i = -1;
		for(Couleur couleur : Couleur.values()) {
			if (couleur != Couleur.NOIR) {	// la couleur doit etre differente de NOIR
				i++;
				menu.put(i, couleur);
				System.out.println(i + ") " + couleur.getValeur());
			}
		}
		int choix = Clavier.lireEntier(0, i);	// le choix doit etre entre 0 et i
		return menu.get(choix);
	}

	/**
	 * cette methode doit etre prive ! seul le joueur doit connaetre combien il a de cartes jouables !
	 * @return le nombre de cartes jouables eed : compatibles avec le sommet du talon 
	 */
	private int nbCartesJouables() {
		int n = 0;
		ArrayList<Carte> cartes = main.getCartes();
		if (cartes.isEmpty()) {
			return 0;
		}
		Carte sommetTalon = talon.sommet();
		for(int i = 0; i < cartes.size(); ++i) {
			Carte carte = cartes.get(i);
			if (carte.compatible(sommetTalon)) {
				n++;
			}
		}
		return n;
	}
	
	/**
	 * contrairement e nbCartesJouables(), cette fonction doit etre publique
	 * les autres joueurs peuvent voir combien vous avez de cartes dans la main
	 * @return le nombre de cartes que possede le joueur dans sa main
	 */
	public int nbCartes() {
		return main.nbCartes();
	}

	/**
	 * @return le pseudo du joueur courant
	 */
	public String getPseudo() {
		return pseudo;
	}
	
	/**
	 * @return chaene decrivant le joueur en cours
	 * le joueur est identifie par son pseudo
	 */
	public String toString() {
		return getPseudo();
	}
	
	
}
