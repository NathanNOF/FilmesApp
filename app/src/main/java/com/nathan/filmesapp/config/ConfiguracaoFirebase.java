package com.nathan.filmesapp.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFirebase {

    private static DatabaseReference database;
    private static FirebaseAuth auth;

    //singleton Database
    public static DatabaseReference getFirebaseDatabase(){
        if(ConfiguracaoFirebase.database==null){
          ConfiguracaoFirebase.database = FirebaseDatabase.getInstance().getReference();
        }
        return database;
    }
    //singleton auth
    public static FirebaseAuth getFirebseauth(){
      if(auth==null){
          auth = FirebaseAuth.getInstance();
      }
      return auth;
    }
}

