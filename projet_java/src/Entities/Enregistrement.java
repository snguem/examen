package Entities;

import java.sql.Date;

public class Enregistrement {
        private int id; 
        private Date date;
        private Double montant;
        private int type;
         public int getType() {
            return type;
        }
        public void setType(int type) {
            this.type = type;
        }
        private Annee annee ;
        private Etudiant etudiant ;
        private Classe classe;
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public Date getDate() {
            return date;
        }
        public void setDate(Date date) {
            this.date = date;
        }
        public Double getMontant() {
            return montant;
        }
        public void setMontant(Double montant) {
            this.montant = montant;
        }
        public Annee getAnnee() {
            return annee;
        }
        public void setAnnee(Annee annee) {
            this.annee = annee;
        }
        public Etudiant getEtudiant() {
            return etudiant;
        }
        public void setEtudiant(Etudiant etudiant) {
            this.etudiant = etudiant;
        }
        public Classe getClasse() {
            return classe;
        }
        public void setClasse(Classe classe) {
            this.classe = classe;
        }

}
