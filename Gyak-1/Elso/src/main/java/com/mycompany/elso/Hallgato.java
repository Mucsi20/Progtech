/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.elso;

/**
 *
 * @author camfch
 */
public class Hallgato {
    private String name;
    private String nationality;
    private double gradeAvg;
    
    public Hallgato(){
    }
    public void SetName(String Name){
        name = Name;
    }
    public String GetName(){
        return name;
    }
     public void SetGradeAvg(double GradeAvg){
        gradeAvg = GradeAvg;
    }
    public double GetGradeAvg(){
        return gradeAvg;
    }
        public void SetNationality(String Nationality){
        name = Nationality;
    }
    public String GetNationality(){
        return nationality;
    }
    @Override
    public String toString(){
        return "Name: "+name+"/nNationality: "+nationality+"/nGrade Avarage: "+String.valueOf(gradeAvg);
    }
}
