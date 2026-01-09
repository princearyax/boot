package com.arya;

public class Calc {

    public String getGrade(int score){
        if(score < 0 || score > 100) throw new IllegalArgumentException("Score must be between 0 and 100");
        if(score >= 90) return "A";
        else if(score >= 80) return "B";
        else if(score >= 50) return "C";
        else return "F";
    }
}
