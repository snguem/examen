import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

import Entities.Annee;
import Entities.Classe;
import Entities.Enregistrement;
import Entities.Etudiant;
import Entities.Prof;
import Entities.Profclasse;
import Entities.Utilisateurs;
import Services.Anneeservices;
import Services.Classeservices;
import Services.Enregistrementservices;
import Services.Etudantservice;
import Services.Profclasseservvices;
import Services.Profservices;
import Services.Utilisateursservices;

public class App {
    private static Scanner scanner=new Scanner(System.in);
    private static Anneeservices anneeservices=new Anneeservices();
    private static Classeservices classeservices=new Classeservices();
    private static Enregistrementservices enregistrementservices=new Enregistrementservices();
    private static Profclasseservvices profclasseservvices=new Profclasseservvices();
    private static Profservices profservices=new Profservices();
    private static Utilisateursservices utilisateursservices=new Utilisateursservices();
    private static Etudantservice etudantservice=new Etudantservice();

    public static void main(String[] args) throws Exception {
        Utilisateurs user=connexion();
        if (user==null) {
            effacer();
            System.out.println("APPLICATION BLOQUEE, RESSAYER ULTERIEUREMENT");
        }else{
            if (user.getRole()==1) {
                rp(user);
                main(args);
            }else if (user.getRole()==2) {
                ac(user);
                main(args);
            }else{
                effacer();
                System.out.println("UTILISATEUR NON PRIS EN CHARGE");
            }
        }
    }
    

    public static void rp(Utilisateurs rp){
        int choix;
        Classe classe;
        Prof prof;
        Profclasse affectation;
        Annee annee=anneeservices.actuel();
        effacer();
        do {
            System.out.println("Annee en cours: "+annee.getLibelle());
            System.out.println("MENU RP\n");
            System.out.println("1- creer une nouvelle classe");
            System.out.println("2- lister les classes");
            System.out.println("3- ajouter un prof");
            System.out.println("4- lister les profs");
            System.out.println("5- affecter une classe a un prof");
            System.out.println("6- lister les classes d'un prof");
            System.out.println("7- declarer une nouvelle annee scolaire");
            System.out.println("0- deconnexion");
            System.out.print("faites votre choix: ");
            choix=scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1:
                    effacer();
                    classe=new Classe();
                    System.out.println("Entrer le nom de la classe");
                    classe.setLibelle(scanner.nextLine());
                    System.out.println("Entrer la filiere: ");
                    classe.setFiliere(scanner.nextLine());
                    System.out.println("Entrer le niveau: ");
                    classe.setNiveau(scanner.nextLine());
                    if (classeservices.ajouter(classe)!=-1) {
                        effacer();
                        System.out.println("Echec de l'ajout\n");
                    }else{
                        effacer();
                        System.out.println("Ajouter avec success\n");
                    }
                    break;
                case 2:
                    effacer();
                    System.out.println("liste des classes");
                    for (Classe c : classeservices.toutes()) {
                        System.out.println(c.toString());
                    }
                    break;
                
                case 3:
                    effacer();
                    prof=new Prof();
                    System.out.println("Entrer le nom complet :");
                    prof.setNomComplet(scanner.nextLine());
                    System.out.println("Entrer le nci;");
                    prof.setNCI(scanner.nextInt());
                    prof.setRole(3);
                    scanner.nextLine();
                    System.out.println("Entrer le grade:");
                    prof.setGrade(scanner.nextLine());
                    if (profservices.ajouter(prof)!=-1) {
                        effacer();
                        System.out.println("Echec de l'ajout\n");
                    }else{
                        effacer();
                        System.out.println("Ajouter avec success\n");
                    }
                    break;
                
                case 4:
                    effacer();
                    System.out.println("liste des profas");
                    for (Prof p : profservices.tout()) {
                        System.out.println(p.toString());
                    }
                    break;
                
                case 5:
                    effacer();
                    System.out.println("Entrer l'id du prof: ");
                    prof=profservices.rechercheProf(scanner.nextInt());
                    if (prof!=null) {
                        System.out.println("Entrer l'id de la classe: ");
                        classe=classeservices.rechercherClasse(scanner.nextInt());
                        if (classe!=null) {
                            int id=classe.getId();
                            if (profclasseservvices.toutParProf(prof).stream().filter(af->af.getClasse().getId()==id).findFirst().orElse(null)==null) {
                                affectation=new Profclasse();
                                affectation.setClasse(classe);
                                affectation.setProf(prof);
                                profclasseservvices.ajouter(affectation);
                                effacer();
                                System.out.println("Classe affecter avec succes\n");
                            }else{
                                effacer();
                                System.out.println("La classe a deja ete affecte a ce prof\n");
                            }
                        }else{
                            effacer();
                            System.out.println("Classe introuvable\n");
                        }
                    }else{
                        effacer();
                        System.out.println("Prof introuvable\n");
                    }
                    break;
                
                case 6:
                    effacer();
                    System.out.println("Entrer l'id du prof: ");
                    prof=profservices.rechercheProf(scanner.nextInt());
                    if (prof!=null) {
                        effacer();
                        System.out.println("liste des classes du prof :"+prof.getNomComplet());
                        for (Profclasse prc : profclasseservvices.toutParProf(prof)) {
                            System.out.println(prc.getClasse().toString());
                        }
                    }
                    break;
                
                case 7:
                    effacer();
                    anneeservices.ajoutannee(annee);
                    annee=anneeservices.actuel();
                    System.out.println("!!! Nouvelle annee scolaire !!!");
                    break;

                default:
                    break;
            }
        } while (choix!=0);
    }

    public static void ac(Utilisateurs rp){
        int choix;
        Classe classe;
        Etudiant etudiant;
        Enregistrement enregistrement;
        Annee annee=anneeservices.actuel();
        effacer();
        do {
            System.out.println("Annee en cours: "+annee.getLibelle());
            System.out.println("MENU AC\n");
            System.out.println("1- inscription");
            System.out.println("2- reinscription");
            System.out.println("3- inscriptions par annee");
            System.out.println("0- deconnexion");
            System.out.print("faites votre choix: ");
            choix=scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1:
                    enregistrement=new Enregistrement();
                    effacer();
                    etudiant=new Etudiant();
                    System.out.println("Entrer le nom complet");
                    etudiant.setNomComplet(scanner.nextLine());
                    System.out.println("Entrer le tuteur");
                    etudiant.setTuteur(scanner.nextLine());
                    System.out.println("Entrer le matricule ");
                    etudiant.setMatricule(scanner.nextInt());
                    System.out.println("Entrer l'id de la classe: ");
                    classe = classeservices.rechercherClasse(scanner.nextInt());
                    if (classe!=null) {
                        System.out.println("Entrer le montant");
                        enregistrement.setMontant(scanner.nextDouble());
                        enregistrement.setDate(Date.valueOf(LocalDate.now()));
                        enregistrement.setType(1);
                        enregistrement.setAnnee(annee);
                        enregistrement.setClasse(classe);
                        etudiant.setRole(4);
                        etudiant.setId(etudantservice.ajouter(etudiant));
                        enregistrement.setEtudiant(etudiant);
                        if (enregistrementservices.ajouter(enregistrement)!=-1) {
                            effacer();
                            System.out.println("Inscription reussi\n");
                        }else{
                            effacer();
                            System.out.println("Echec de l'inscription\n");
                        }

                    }else{
                        effacer();
                        System.out.println("Classe introuvable\n");
                    }
                    break;
                case 2:
                    effacer();
                    enregistrement=new Enregistrement();
                    System.out.println("Entrer l'id de l'etudiant");
                    etudiant=etudantservice.rechercheEtudiant(scanner.nextInt());
                    if (etudiant!=null) {
                        classe = classeservices.rechercherClasse(scanner.nextInt());
                        if (classe!=null) {
                            if (enregistrementservices.chercherParEtudiantAnnee(etudiant, annee)==null) {
                                System.out.println("Entrer le montant");
                                enregistrement.setMontant(scanner.nextDouble());
                                enregistrement.setDate(Date.valueOf(LocalDate.now()));
                                enregistrement.setType(1);
                                enregistrement.setAnnee(annee);
                                enregistrement.setClasse(classe);
                                enregistrement.setEtudiant(etudiant);
                                if (enregistrementservices.ajouter(enregistrement)!=-1) {
                                    effacer();
                                    System.out.println("Reinscription reussi\n");
                                }else{
                                    effacer();
                                    System.out.println("Echec de la reinscription\n");
                                }
                            }else{
                                effacer();
                                System.out.println("Etudiant deja inscrit\n");
                            }

                        }else{
                            effacer();
                            System.out.println("Classe introuvable\n");
                        }
                    }else{
                        effacer();
                        System.out.println("Etudiant introuvable\n");
                    }
                    break;
                
                case 3:
                    effacer();
                    System.out.println("Entrer l'annee:");
                    Annee annee_choisie=anneeservices.rechercheannee(scanner.nextInt());
                    if (annee_choisie!=null) {
                        effacer();
                        System.out.println("Liste des inscrits en :"+annee_choisie.getLibelle());
                        for (Enregistrement inscrits : enregistrementservices.rechercherParAnnee(annee)) {
                            System.out.println(inscrits.toString());
                        }
                    }
                    break;
                
                case 4:
                    effacer();
                    System.out.println("liste des profas");
                    for (Prof p : profservices.tout()) {
                        System.out.println(p.toString());
                    };
                    break;

                default:
                    break;
            }
        } while (choix!=0);
    }


    public static Utilisateurs connexion(){
        Utilisateurs user=null;
        String login, password;
        int essai=3, nbrEssai=0;
        effacer();
        do {
            System.out.println("CONNEXION:");
            System.out.print("login: ");
            login=scanner.nextLine();
            System.out.print("mot de passe: ");
            password=scanner.nextLine();
            user=utilisateursservices.connexion(login, password);
            if (user==null) {
                nbrEssai+=1;
                System.out.println("---------- ESSAIS RESTANT: "+essai+"--------");
                System.out.println("------ LOGIN/MOT DE PASSE INCORRECT ------");
                System.out.println("------------------------------------------\n");
            }
        } while (user==null && nbrEssai<=essai);
        return user;
    }

    
    public static void effacer() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                // Pour Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Pour Linux, macOS, etc.
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Erreur lors du nettoyage du terminal: " + e.getMessage());
        }
    }


}
