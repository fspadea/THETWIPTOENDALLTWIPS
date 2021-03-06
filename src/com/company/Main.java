package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<Person> lty = new ArrayList<>();
        BufferedReader lines = new BufferedReader(new FileReader("rawDataU6.txt"));
        ArrayList<Scanner> listOfLines = new ArrayList<>();
        for (int i = 0; i < 55; i++) {
            String s = lines.readLine();
            Scanner nower = new Scanner(s).useDelimiter("\t");
            listOfLines.add(nower);
        }
        listOfLines.remove(0);
        listOfLines.remove(6);
        listOfLines.remove(26);
        listOfLines.remove(26);
        int index = -1;
        for (int i = 0; i < 51; i++) {
            double pi;
            try {
                pi = Double.parseDouble(listOfLines.get(i).next());
                if(pi != lty.get(index).person) {
                    Person now = new Person(pi);
                    lty.add(now);
                    index++;
                }
            }
            catch (Exception e){
                Person now = new Person(1.0);
                lty.add(now);
                index++;
            }
            String current;
            double cur;
            int x = 0;
            current = listOfLines.get(i).next();
            Scanner s;
            int add1 = 0;
            while(current.contains("+") && !current.contains("Setup loop to step through wordlist")) {
                s = new Scanner(current);
                while (s.hasNext()) {
                    String p = s.next();
                    if(add1 == 1) {
                        try {
                            cur = Double.parseDouble(p);
                            lty.get(index).score1 += cur;
                        } catch (Exception e) {
                        }
                        add1 = 0;
                    }
                    if(p.contains("+")){
                        add1++;
                    }
                }
                current = listOfLines.get(i).next();
            }
            int d = 0;
            while(!current.contains("+") && d == 0) {
                try {
                    lty.get(index).score1 -= Double.parseDouble(current) / 4.0;
                    d++;
                } catch (Exception e) {
                    current = listOfLines.get(i).next();
                    s = new Scanner(current);
                }
            }
            double syn = 0;
            while(!current.contains("+") && d == 1) {
                try{
                    current = listOfLines.get(i).next();
                    s = new Scanner(current);
                    syn = s.nextDouble();
                    d++;
                } catch (Exception e){
                }
            }
            int add = 0;
            while(current.contains("+") && listOfLines.get(i).hasNext()) {
                s = new Scanner(current);
                while (s.hasNext()) {
                    String p = s.next();
                    if(add == 1) {
                        try {
                            cur = Double.parseDouble(p);
                            lty.get(index).score2 += cur;
                        } catch (Exception e) {
                        }
                        add = 0;
                    }
                    if(p.contains("+")){
                        add++;
                    }
                }
                current = listOfLines.get(i).next();
            }
            if(d != 2){
                try {
                    lty.get(index).score2 -= Double.parseDouble(current) / 4;
                }
                catch (Exception e){
                }
            }
            else {
                lty.get(index).score2 -= syn/4;
            }
        }
        lty.get(11).score1 *= 2;
        lty.get(14).score1 *= 2;
        lty.get(20).score1 *= 2;
        lty.get(24).score1 *= 2;
        lty.get(25).score1 *= 2;
        lty.get(0).score2 *= 2;
        lty.get(11).score2 *= 2;
        lty.get(14).score2 *= 2;
        lty.get(20).score2 *= 2;
        lty.get(24).score2 *= 2;
        lty.get(25).score2 *= 2;
        for(Person p: lty){
            Scanner check = new Scanner(new File("names.txt"));
            for (int i = 0; i < 28; i++) {
                String maybe = "";
                double d = 0;
                while(d == 0) {
                    try{
                        d = check.nextDouble();
                    }
                    catch (Exception e){
                        maybe += check.next() + " ";
                    }
                }
                if(p.person == d){
                    p.name = maybe;
                }
            }
        }
        Collections.sort(lty);
        System.out.println("Name\t\t\t\tSecret Number\tAvg Total Grade\t\tFR Q1 Grade\t\t\tFR Q2 Grade");
        for(Person p: lty){
            if((p.score1/2+"").toString().length() < 4) {
                if (((p.score2 + p.score1) / 2 + "/19").toString().length() < 8) {
                    if (p.name.length() > 13) {
                        System.out.println(p.name + "\t" + (int) p.person + "\t\t\t\t" + (p.score2 + p.score1) / 2 + "/19" + "\t\t\t\t" + p.score1 / 2 + "\t\t\t\t\t" + p.score2 / 2);
                    } else if (p.name.length() > 11) {
                        System.out.println(p.name + "\t\t" + (int) p.person + "\t\t\t\t" + (p.score2 + p.score1) / 2 + "/19" + "\t\t\t\t" + p.score1 / 2 + "\t\t\t\t\t" + p.score2 / 2);
                    } else if (p.name.length() > 7) {
                        System.out.println(p.name + "\t\t\t" + (int) p.person + "\t\t\t\t" + (p.score2 + p.score1) / 2 + "/19" + "\t\t\t\t" + p.score1 / 2 + "\t\t\t\t\t" + p.score2 / 2);
                    } else if (p.name.length() < 4) {
                        System.out.println(p.name + "\t\t\t\t\t" + (int) p.person + "\t\t\t\t" + (p.score2 + p.score1) / 2 + "/19" + "\t\t\t\t" + p.score1 / 2 + "\t\t\t\t\t" + p.score2 / 2);
                    } else {
                        System.out.println(p.name + "\t\t\t\t" + (int) p.person + "\t\t\t\t" + (p.score2 + p.score1) / 2 + "/19" + "\t\t\t\t" + p.score1 / 2 + "\t\t\t\t\t" + p.score2 / 2);
                    }
                } else {
                    if (p.name.length() > 13) {
                        System.out.println(p.name + "\t" + (int) p.person + "\t\t\t\t" + (p.score2 + p.score1) / 2 + "/19" + "\t\t\t" + p.score1 / 2 + "\t\t\t\t\t" + p.score2 / 2);
                    } else if (p.name.length() > 11) {
                        System.out.println(p.name + "\t\t" + (int) p.person + "\t\t\t\t" + (p.score2 + p.score1) / 2 + "/19" + "\t\t\t" + p.score1 / 2 + "\t\t\t\t\t" + p.score2 / 2);
                    } else if (p.name.length() > 7) {
                        System.out.println(p.name + "\t\t\t" + (int) p.person + "\t\t\t\t" + (p.score2 + p.score1) / 2 + "/19" + "\t\t\t" + p.score1 / 2 + "\t\t\t\t\t" + p.score2 / 2);
                    } else if (p.name.length() < 4) {
                        System.out.println(p.name + "\t\t\t\t\t" + (int) p.person + "\t\t\t\t" + (p.score2 + p.score1) / 2 + "/19" + "\t\t\t" + p.score1 / 2 + "\t\t\t\t\t" + p.score2 / 2);
                    } else {
                        System.out.println(p.name + "\t\t\t\t" + (int) p.person + "\t\t\t\t" + (p.score2 + p.score1) / 2 + "/19" + "\t\t\t" + p.score1 / 2 + "\t\t\t\t\t" + p.score2 / 2);
                    }
                }
            }
            else{
                if (((p.score2 + p.score1) / 2 + "/19").toString().length() < 8) {
                    if (p.name.length() > 13) {
                        System.out.println(p.name + "\t" + (int) p.person + "\t\t\t\t" + (p.score2 + p.score1) / 2 + "/19" + "\t\t\t\t" + p.score1 / 2 + "\t\t\t\t" + p.score2 / 2);
                    } else if (p.name.length() > 11) {
                        System.out.println(p.name + "\t\t" + (int) p.person + "\t\t\t\t" + (p.score2 + p.score1) / 2 + "/19" + "\t\t\t\t" + p.score1 / 2 + "\t\t\t\t" + p.score2 / 2);
                    } else if (p.name.length() > 7) {
                        System.out.println(p.name + "\t\t\t" + (int) p.person + "\t\t\t\t" + (p.score2 + p.score1) / 2 + "/19" + "\t\t\t\t" + p.score1 / 2 + "\t\t\t\t" + p.score2 / 2);
                    } else if (p.name.length() < 4) {
                        System.out.println(p.name + "\t\t\t\t\t" + (int) p.person + "\t\t\t\t" + (p.score2 + p.score1) / 2 + "/19" + "\t\t\t\t" + p.score1 / 2 + "\t\t\t\t" + p.score2 / 2);
                    } else {
                        System.out.println(p.name + "\t\t\t\t" + (int) p.person + "\t\t\t\t" + (p.score2 + p.score1) / 2 + "/19" + "\t\t\t\t" + p.score1 / 2 + "\t\t\t\t" + p.score2 / 2);
                    }
                } else {
                    if (p.name.length() > 13) {
                        System.out.println(p.name + "\t" + (int) p.person + "\t\t\t\t" + (p.score2 + p.score1) / 2 + "/19" + "\t\t\t" + p.score1 / 2 + "\t\t\t\t" + p.score2 / 2);
                    } else if (p.name.length() > 11) {
                        System.out.println(p.name + "\t\t" + (int) p.person + "\t\t\t\t" + (p.score2 + p.score1) / 2 + "/19" + "\t\t\t" + p.score1 / 2 + "\t\t\t\t" + p.score2 / 2);
                    } else if (p.name.length() > 7) {
                        System.out.println(p.name + "\t\t\t" + (int) p.person + "\t\t\t\t" + (p.score2 + p.score1) / 2 + "/19" + "\t\t\t" + p.score1 / 2 + "\t\t\t\t" + p.score2 / 2);
                    } else if (p.name.length() < 4) {
                        System.out.println(p.name + "\t\t\t\t\t" + (int) p.person + "\t\t\t\t" + (p.score2 + p.score1) / 2 + "/19" + "\t\t\t" + p.score1 / 2 + "\t\t\t\t" + p.score2 / 2);
                    } else {
                        System.out.println(p.name + "\t\t\t\t" + (int) p.person + "\t\t\t\t" + (p.score2 + p.score1) / 2 + "/19" + "\t\t\t" + p.score1 / 2 + "\t\t\t\t" + p.score2 / 2);
                    }
                }
            }
        }
    }
}
