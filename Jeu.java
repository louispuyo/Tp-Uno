package uno;

import io.Clavier;

/**
 * classe jeu, elle est responsable du deroulement du jeu
 * suivie du sens du jeu, de l'activation des effets speciales
 * @author louispuyo
 *
 */
public class Jeu {
	/**
	 * la pioche
	 */
	private Pioche pioche;
	/**
	 * le talon
	 */
	private Talon talon;
	/**
	 * le nombre de joueurs ( doit etre entre 2 et 4 )
	 */
	private int nbJoueurs;
	/**
	 * la liste des joueurs
	 */
	private Joueur[] joueurs;
	

	private int sens = -1;	// par defaut e gauche
	/**
	 * l'indice du joueur courant, initialement le premier
	 */
	private int indiceJoueurCourant = 0;
	/**
	 * l'objet Joueur e l'indice indiceJoueurCourant du tableau joueurs
	 */
	private Joueur joueurCourant;
	
	/**
	 * constructeur
	 */
	public Jeu() {
		pioche = new Pioche();
		pioche.melanger();
		talon = new Talon(pioche);
		System.out.println("combien de joueurs veulent jouer ?");
		nbJoueurs = Clavier.lireEntier(2, 4);
		joueurs = new Joueur[nbJoueurs];
		for (int i = 0; i < nbJoueurs; i++) {
			String pseudoJoueur;
			boolean pseudoUnique;
			do {
				System.out.println("Nom du joueur" + " " + i + " ?");
				pseudoJoueur = Clavier.lireChaine();
				pseudoUnique = pseudoValide(pseudoJoueur, i);
				if (!pseudoUnique) {
					System.out.println("Le pseudo <" + pseudoJoueur + "> est deja utilise !");
				}
			} while (!pseudoUnique);	// le pseudo du joueur doit etre unique !
			joueurs[i] = new Joueur(pseudoJoueur, pioche, talon);
			System.out.println("Le joueur <" + joueurs[i].getPseudo() + "> est connecte");
		}
		for (int i = 0; i < joueurs.length; i++) {	// Distirbution des cartes
			for (int j = 0; j < 7; j++) {	// Chaque joueur va prendre 7 cartes
				joueurs[i].prendreCarte();
			}
		}
		//talon.ajouterPremiereCarte(pioche);	// [DONE] deplacer cette methode au constructeur du talon
	}
	
	/**
	 * permet de demarrer le jeu
	 */
	public void lancer() {
		System.out.println("=== Le jeu commence ===");
		boolean effetSpecial = false;	// cette variable permet de boucler e l'infini dans le cas des cartes speciales
		while (true) {	// boucle du jeu
			joueurCourant = joueurs[indiceJoueurCourant];
			System.out.println("Tour de " + joueurCourant.getPseudo());
			if (effetSpecial && talon.sommet() instanceof CarteSpecial) {	// le joueur precedant a joue une carte speciale
				effetSpecial = false;
				if (((CarteSpecial) talon.sommet()).getSymbole() == Symbole.PASSER) {	// le joueur courant doit passer son tour
					System.out.println(joueurCourant.getPseudo() + " doit passer son tour -> effet de la carte : " + talon.sommet().toString());
					joueurSuivant();
					continue;
				}
				if (((CarteSpecial) talon.sommet()).getSymbole() == Symbole.PLUS2) {	// le joueur precedant a joue +2
					// le joueur courant doit piocher 2 cartes
					System.out.println(joueurCourant.getPseudo() + " doit piocher 2 cartes et passer son tour -> effet de la carte " + talon.sommet());
					for (int i = 0; i < 2; i++) {
						joueurCourant.prendreCarte();
					}
					// et passer son tour
					joueurSuivant();
					continue;
				}
				if (((CarteSpecial) talon.sommet()).getSymbole() == Symbole.PLUS4) {	// le joueur precedant a joue +4
					// le joueur courant doit piocher 4 cartes
					System.out.println(joueurCourant.getPseudo() + " doit piocher 4 cartes et passer son tour -> effet de la carte " + talon.sommet());
					for (int i = 0; i < 4; i++) {
						joueurCourant.prendreCarte();
					}
					// et passer son tour
					joueurSuivant();
					continue;
				}
			}
			joueurCourant.jouerTour();
			if (joueurCourant.nbCartes() == 0) {	// on teste si le joueur courant a vide sa main
				System.out.println(joueurCourant.getPseudo() + " a gagne !");
				break;
			}
			if (joueurCourant.nbCartes() == 1) {	// on teste si le joueur courant lui reste une seule carte ( UNO ! ) dans sa main
				System.out.println(joueurCourant.getPseudo() + " <UNO!>");
			}
			// On doit tester ici si le joueur a des doublons de la carte jouee TODO
			// On doit tester la carte inverser e ce niveau
			if (talon.sommet() instanceof CarteSpecial) {	// le joueur courant a joue une carte speciale
				effetSpecial = true;	// activer l'effet special
				if (((CarteSpecial) talon.sommet()).getSymbole() == Symbole.INVERSER) {	// le joueur courant e inverse le sens
					System.out.println(joueurCourant.getPseudo() + " a inverse le sens du jeu");
					sens *= -1;	// la valeur de sens est soit 1 soit -1, on multiplie par -1 pour changer
				}
			}
			joueurSuivant();
			System.out.println();
		}
		System.out.println("=== Fin du jeu ===");
	}
	
	/**
	 * permet de passer au joueur suivant selon le sens du jeu
	 */
	private void joueurSuivant() {
		// avancer vers le joueur suivant
		indiceJoueurCourant += sens;
		// On doit verifier si l'indice a depasser les bornes du tableau
		if (indiceJoueurCourant < 0) {
			indiceJoueurCourant += nbJoueurs;
			// exemple : 3 joueurs -> [ 0 , 1 , 2 ]
			// 0 + (-1) = -1 < 0 -> -1 + 3 -> 2 ( dernier joueur )
		}
		if (indiceJoueurCourant > nbJoueurs - 1) {	// indice >= nbJoueurs
			indiceJoueurCourant -= nbJoueurs;
			// exemple : 3 joueurs -> [ 0 , 1 , 2 ]
			// 2 + (1) = 3 > 2 -> 3 - 3 -> 0 ( premier joueur )
		}
	}

	/**
	 * permet de verifier si le pseudo entre est unique ou pas
	 * @param pseudoJoueur : le pseudo e verifier
	 * @return true : si le pseudo est valide 
	 * @return false : sinon
	 * @param indice : l'indice de la case du tableau e verifer
	 */
	private boolean pseudoValide(String pseudoJoueur, int indice) {
		if (indice == 0) {	// si c'est le premier pseudo e verifier
			return true;	// il est valide, aucun autre pseudo le precede !
		}
		// on doit verifier les cases 0 jusqu'e indice - 1
		for (int j = 0; j < indice; j++) {	// il faut verifier tous les cartes qui precedent la case d'indice (indice)
			//System.out.println("pseudo e verifier : " + joueurs[j]);
			if (joueurs[j].getPseudo().equalsIgnoreCase(pseudoJoueur)) {	// si on a trouve un joueur ayant le meme pseudo
				return false;	// le pseudo n'est pas valide
			}
		}
		return true;	// tous les pseudos precedants sont != que le pseudo e verifier, il est donc valide
	}
	
}
