package org.example.crawler;

import java.util.ArrayList;
import java.util.HashMap;


public class Mappings {
    public Mappings(){
        this.releases.add(""); //Mandatory
        this.architecture.put("Default","x64"); //Mandatory

    }
    public HashMap<OSTypes,String> os = new HashMap<>();

    public HashMap<String,String> architecture = new HashMap<>();
    public HashMap<OSTypes,String> extension = new HashMap<>();


    public ArrayList<String> releases = new ArrayList<>();

}
