/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.elso;

import java.util.ArrayList;
import java.util.Scanner; 

/**
 *
 * @author camfch
 */
public class Elso {

    public static void main(String[] args) {
        ArrayList<Hallgato> studentList = new ArrayList();
        for(int i = 0; i < 5; i++){
            GetStudentInput(studentList, i);
        }
        Hallgato best = HighestGrade(studentList);
        System.out.println("Best student:");
        System.out.println(best);
        
        Hallgato worst = LowestGrade(studentList);
        System.out.println("Worst student:");
        System.out.println(worst);
        
        Scholarship(studentList);

    }
    public static void GetStudentInput(ArrayList<Hallgato> studentList, int i){
        Scanner myObj = new Scanner(System.in);
        System.out.println((i+1)+". student");
        
        System.out.print("Name: ");
        String name = myObj.nextLine();

        System.out.print("Nationality: ");
        String nationality = myObj.nextLine();
        
        System.out.print("Avarage Grade: ");
        double gradeAvg = Double.parseDouble(myObj.nextLine());
        
        Hallgato newStudent = new Hallgato();
        newStudent.SetName(name);
        newStudent.SetNationality(nationality);
        newStudent.SetGradeAvg(gradeAvg);
        
        studentList.add(newStudent);
    }
    
    public static void Scholarship(ArrayList<Hallgato> studentList){
        System.out.println("Achieved scholarship:");
        for(Hallgato student : studentList){
            if(student.GetGradeAvg() >= 4.0){
                System.out.println(student);
            }
        }
    }
    
    public static Hallgato HighestGrade(ArrayList<Hallgato> studentList){
        Hallgato save = studentList.get(0);
        for(Hallgato student : studentList){
            if(student.GetGradeAvg() > save.GetGradeAvg()){
                save = student;
            }
        }
        return save;
    }
    public static Hallgato LowestGrade(ArrayList<Hallgato> studentList){
        Hallgato save = studentList.get(0);
        for(Hallgato student : studentList){
            if(student.GetGradeAvg() < save.GetGradeAvg()){
                save = student;
            }
        }
        return save;
    }
}
