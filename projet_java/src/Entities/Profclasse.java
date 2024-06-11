package Entities;

public class Profclasse {

    private int id;
     private Prof prof ;
        private Classe classe;
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public Prof getProf() {
            return prof;
        }
        public void setProf(Prof prof) {
            this.prof = prof;
        }
        public Classe getClasse() {
            return classe;
        }
        public void setClasse(Classe classe) {
            this.classe = classe;
        }
}
