package Entities;

import java.util.ArrayList;

public class Prof extends Utilisateurs {
    private int NCI ;
    private String grade ;
    private ArrayList<Profclasse>  profclasse=new ArrayList<>() ;
    public int getNCI() {
        return NCI;
    }
    public void setNCI(int nCI) {
        NCI = nCI;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public ArrayList<Profclasse> getProfclasse() {
        return profclasse;
    }
    public void addProfclasse(Profclasse profclasse) {
       this. profclasse.add(profclasse);
    }
   
    
}
