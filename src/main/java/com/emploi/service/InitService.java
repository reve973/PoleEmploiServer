package com.emploi.service;

import com.emploi.model.entity.Candidat;
import com.emploi.model.entity.Candidature;
import com.emploi.model.entity.CandidatureId;
import com.emploi.model.entity.Contrat;
import com.emploi.model.entity.Entreprise;
import com.emploi.model.entity.Offre;
import com.emploi.model.entity.Role;
import com.emploi.model.entity.User;
import com.emploi.model.entity.repository.CandidatRepository;
import com.emploi.model.entity.repository.CandidatureRepository;
import com.emploi.model.entity.repository.ContratRepository;
import com.emploi.model.entity.repository.EntrepriseRepository;
import com.emploi.model.entity.repository.OffreRepository;
import com.emploi.model.entity.repository.RoleRepository;
import com.emploi.model.entity.repository.UserRepository;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitService
{
  @Autowired
  ContratRepository contratRep;
  @Autowired
  CandidatRepository candidatRep;
  @Autowired
  CandidatureRepository candidatureRep;
  @Autowired
  UserRepository compteRep;
  @Autowired
  OffreRepository offreRep;
  @Autowired
  EntrepriseRepository entrepriseRep;
  @Autowired
  RoleRepository roleRep;
  
  public void init()
    throws ParseException
  {
    System.out.println("Suppression des candidature");
    candidatureRep.deleteAll();
    
    System.out.println("Suppression des offres");
    offreRep.deleteAll();
    
    System.out.println("Suppression des entreprises");
    entrepriseRep.deleteAll();
    
    System.out.println("Suppression des candidats");
    candidatRep.deleteAll();
    
    System.out.println("Suppression des comptes");
    compteRep.deleteAll();
    
    System.out.println("Suppression des contrats");
    contratRep.deleteAll();
    
    System.out.println("Suppression des roles");
    roleRep.deleteAll();
    
    System.out.println("-------------------------------");
    
    System.out.println("Création contrat CDD");
    Contrat contratCDD = (Contrat)contratRep.save(new Contrat(Contrat.CONTRAT_CDD));
    
    System.out.println("Création contrat CDI");
    Contrat contratCDI = (Contrat)contratRep.save(new Contrat(Contrat.CONTRAT_CDI));
    
    System.out.println("Création contrat CDDI");
    Contrat contratCDDI = (Contrat)contratRep.save(new Contrat(Contrat.CONTRAT_CDDI));
    
    System.out.println("Création Role admin");
    Role roleAdmin = (Role)roleRep.save(new Role(Role.ROLE_ADMIN));
    
    System.out.println("Création Role candidat");
    Role roleCandidat = (Role)roleRep.save(new Role(Role.ROLE_CANDIDAT));
    
    System.out.println("Création Role entreprise");
    Role roleEntreprise = (Role)roleRep.save(new Role(Role.ROLE_ENTREPRISE));
    
    System.out.println("Création Compte admin");
    User compteAdmin = (User)compteRep.save(new User("admin", "123", roleAdmin));
    
    System.out.println("Création Compte candidat1");
    User compteCand1 = (User)compteRep.save(new User("cand1", "123", roleCandidat));
    
    System.out.println("Création Compte candidat2");
    User compteCand2 = (User)compteRep.save(new User("cand2", "123", roleCandidat));
    
    System.out.println("Création Compte entreprise1");
    User compteEnt1 = (User)compteRep.save(new User("ent1", "123", roleEntreprise));
    
    System.out.println("Création Compte entreprise2");
    User compteEnt2 = (User)compteRep.save(new User("ent2", "123", roleEntreprise));
    
    DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    System.out.println("Création Candidat candidat1");
    Candidat cand1 = (Candidat)candidatRep.save(new Candidat("nom cand1", "prenom cand1", "cand1@gmail", sdf.parse("10/01/1994"), compteCand1));
    
    System.out.println("Création Candidat candidat2");
    Candidat cand2 = (Candidat)candidatRep.save(new Candidat("nom cand2", "prenom cand2", "cand2@gmail", sdf.parse("08/04/1991"), compteCand2));
    
    System.out.println("Création entreprise1");
    Entreprise ent1 = (Entreprise)entrepriseRep.save(new Entreprise("entreprise1", "0692411546", "entreprise1@gmail.com", "www.entreprise1.com", "", compteEnt1));
    
    System.out.println("Création entreprise2");
    Entreprise ent2 = (Entreprise)entrepriseRep.save(new Entreprise("entreprise2", "0692411546", "entreprise2@gmail.com", "www.entreprise2.com", "", compteEnt2));
    
    System.out.println("Création offre1 entreprise1");
    Offre offre1 = (Offre)offreRep.save(new Offre("Offre1", Double.valueOf(1900.0D), "Nous recherchons un profil de XXXXXX ...", contratCDD, Integer.valueOf(5), "Saint-Paul", ent1, sdf.parse("10/01/2018")));
    
    System.out.println("Création offre2 entreprise1");
    Offre offre2 = (Offre)offreRep.save(new Offre("Offre2", Double.valueOf(1500.0D), "Nous recherchons un profil de XXXXXX ...", contratCDI, null, "Saint-Denis", ent1, sdf.parse("14/05/2018")));
    
    System.out.println("Création offre3 entreprise2");
    Offre offre3 = (Offre)offreRep.save(new Offre("Offre3", Double.valueOf(1800.0D), "Nous recherchons un profil de XXXXXX ...", contratCDDI, null, "Saint-leu", ent2, sdf.parse("23/05/2018")));
    
    System.out.println("Création offre4 entreprise2");
    Offre offre4 = (Offre)offreRep.save(new Offre("Offre4", Double.valueOf(1100.0D), "Nous recherchons un profil de XXXXXX ...", contratCDD, Integer.valueOf(1), "Saint-Pierre", ent2, sdf.parse("30/07/2018")));
    
    String[] communes = { "Saint-Paul", 
      "Saint-Leu", 
      "Saint-Denis", 
      "Entre-deux", 
      "Petite-france", 
      "Sainte-rose", 
      "Saint-Pierre" };
    
    String[] offreTitle = { "technicien en froid et climatisation (H/F)", 
      "Vendeur / Vendeuse en revêtement de mur", 
      "Formateur ouvruer paysagiste (H/F)", 
      "vendeur sur stand H/F", 
      "Vendeur / Vendeuse en rôtisserie", 
      "Responsable de point de vente de détail (H/F)", 
      "plongeur en hotellerie (H/F)", 
      "Chef de partie", 
      "Bancheur-coffreur / Bancheuse-coffreuse\n" };
    
    String[] offreDescription = { "vous montez tous les systèmes de climatisation, faites l'entretien et les dépannages sur sites. vous intervenez sur toute l'ile.", 
      "Mode d'emploi recherche pour son client, dans le cadre de l'ouverture d'une nouvelle enseigne de revêtements de sols, des vendeurs (H/F) en CDI.\n\nEn lien avec le responsable du magasin, vous présentez les différentes gammes de produits en respectant  les préconisations de vente, les outils, ainsi que les techniques de merchandising enseignées par le groupe. Responsable de votre zone (étiquetage, mise en rayon ), vous renseignez et conseillez le client et vous contribuez aux bons résultats du magasin.\n\nDe formation supérieure (BAC+2) en vente ou commerce, vous justifiez d'une première expérience significative sur un poste similaire. \nD'excellente présentation, vous êtes patient et à l'écoute. Reconnu pour votre sens du service, vous menez les ventes avec aisance et souplesse.", 
      
      "Nous recherchons un (e) formateur (trice) expérimenté (e) dans le secteur agricole.", 
      "Vous devrez acheminer des pâtisseries et les vendre en galerie marchande \nSTAND SUR LE PORT ET STE SUZANNE \nVous êtes autonome, dynamique, avec le sens du client;", 
      
      "Vous travaillerez tous les dimanche matins de 5h30 à 13h00 sur le parking du théâtre de Saint Gilles. Vous aiderez à l'installation du stand, à son démontage et  au nettoyage. Vous assurerez le découpage de la viande,  la mise en place des produits, accueillerez les clients, les conseillerez et établirez la vente et l'encaissement des produits (porc grillé, poulets rôtis, boudins créoles, samoussas...)\nVous avez obligatoirement une expérience en alimentaire et des connaissances en hygiène alimentaire (HACCP).\nPaiement en TTS.", 
      
      "Animation commerciale\nGestion et exploitation courante\nManagement et encadrement des équipements \nGestion administrative et reporting ", 
      
      "Le Relais de l'hermitage*** recherche son plongeur.\n Vous aurez à faire la vaisselle,nettoyer les batteries de cuisine.", 
      
      "Le Relais de l'Hermitage*** recherche un Chef de partie tournant expérimenté (au minimum 3 ans d'expérience en tant que Chef de partie)\nMissions générales : préparer et réaliser des plats chaud et froid de l'entrée au dessert et en assurer la présentation pour le service dans le respect des normes HACCP\nVous devrez :\nÊtre capable de travailler sur les tous les points de vente de l'hôtel - PDJ, Case Vanille, Pizza, Papayer Buffet et carte, banqueting et sur tous les postes (chaud, froid, pâtisserie..)\nModifier les procédures de réalisation en fonction des commandes particulières,\nVérifier le résultat des préparations", 
      
      "Nous recherchons pour notre client un(e)Bancheur-coffreur / Bancheuse-coffreuse\nVous participez à la création de bâtiments neufs.\n\nVous dresserez des murs en béton armé.\nVous équiperez les banches,\nVous règlerez les banches,\nCoulerez le béton, puis retirerez le moule\nPose de pré-dalles/ferraillage et éventuellement étaiement\n\nSalaire selon grille du BTP" };
    for (int i = 0; i < offreTitle.length; i++)
    {
      String titre = offreTitle[i];
      String desc = offreDescription[i];
      Double salaire = Double.valueOf(Math.random() * 3000.0D);
      
      Contrat contrat = null;
      switch (i % 3)
      {
      case 0: 
        contrat = contratCDD; break;
      case 1: 
        contrat = contratCDI; break;
      case 2: 
        contrat = contratCDDI;
      }
      Integer duree = null;
      switch (i % 5)
      {
      case 0: 
        duree = Integer.valueOf(1); break;
      case 1: 
        duree = Integer.valueOf(4); break;
      case 2: 
        duree = Integer.valueOf(6);
      }
      String commune = communes[(i % communes.length)];
      
      Entreprise entreprise = null;
      switch (i % 2)
      {
      case 0: 
        entreprise = ent1; break;
      case 1: 
        entreprise = ent2;
      }
      String jourParution = Integer.toString((int)(Math.random() * 30.0D));
      jourParution = jourParution.length() == 0 ? "0" + jourParution : jourParution;
      
      String moisParution = Integer.toString((int)(Math.random() * 10.0D));
      moisParution = moisParution.length() == 0 ? "0" + moisParution : moisParution;
      
      String anneeParution = "2018";
      
      String dtParution = jourParution + "/" + moisParution + "/" + anneeParution;
      
      Offre localOffre1 = (Offre)offreRep.save(new Offre(titre, salaire, desc, contrat, duree, commune, entreprise, sdf.parse(dtParution)));
    }
    candidatureRep.save(new Candidature(new CandidatureId(offre1, cand1), new Date()));
    candidatureRep.save(new Candidature(new CandidatureId(offre2, cand1), new Date()));
    candidatureRep.save(new Candidature(new CandidatureId(offre4, cand1), new Date()));
    candidatureRep.save(new Candidature(new CandidatureId(offre3, cand2), new Date()));
    candidatureRep.save(new Candidature(new CandidatureId(offre1, cand2), new Date()));
  }
}