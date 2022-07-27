package com.example.fmps;

import java.io.Serializable;
import java.util.Date;

public class Etudiant implements Serializable {



    int id;
    String nom;
    String prenom;
    String genre;
    String naissence;
    String dateentre;
    String lieu;
    String nomtuteur;
    String pretuteur;
    String typetutur;
    String telephone;
    String cin;
    String codemassar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getNaissence() {
        return naissence;
    }

    public void setNaissence(String naissence) {
        this.naissence = naissence;
    }

    public String getDateentre() {
        return dateentre;
    }

    public void setDateentre(String dateentre) {
        this.dateentre = dateentre;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getNomtuteur() {
        return nomtuteur;
    }

    public void setNomtuteur(String nomtuteur) {
        this.nomtuteur = nomtuteur;
    }

    public String getPretuteur() {
        return pretuteur;
    }

    public void setPretuteur(String pretuteur) {
        this.pretuteur = pretuteur;
    }

    public String getTypetutur() {
        return typetutur;
    }

    public void setTypetutur(String typetutur) {
        this.typetutur = typetutur;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getCodemassar() {
        return codemassar;
    }

    public void setCodemassar(String codemassar) {
        this.codemassar = codemassar;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public int getDouar() {
        return douar;
    }

    public void setDouar(int douar) {
        this.douar = douar;
    }

    public String getAnnerscolaire() {
        return annerscolaire;
    }

    public void setAnnerscolaire(String annerscolaire) {
        this.annerscolaire = annerscolaire;
    }

    public String getMotif_depart() {
        return motif_depart;
    }

    public void setMotif_depart(String motif_depart) {
        this.motif_depart = motif_depart;
    }

    public String getBesoin() {
        return besoin;
    }

    public void setBesoin(String besoin) {
        this.besoin = besoin;
    }

    String classe;
    String section;

    public Etudiant() {
    }

    String depart;
    int douar;

    public Etudiant(String nom, String prenom, String genre, String naissence, String dateentre, String lieu, String nomtuteur, String pretuteur, String typetutur, String telephone, String cin, String codemassar, String classe, String section, String depart, int douar, String annerscolaire, String motif_depart, String besoin) {

        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.naissence = naissence;
        this.dateentre = dateentre;
        this.lieu = lieu;
        this.nomtuteur = nomtuteur;
        this.pretuteur = pretuteur;
        this.typetutur = typetutur;
        this.telephone = telephone;
        this.cin = cin;
        this.codemassar = codemassar;
        this.classe = classe;
        this.section = section;
        this.depart = depart;
        this.douar = douar;
        this.annerscolaire = annerscolaire;
        this.motif_depart = motif_depart;
        this.besoin = besoin;
    }

    String annerscolaire;
    String motif_depart;
    String besoin;
}
