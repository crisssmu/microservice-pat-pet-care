package com.microservice;


import java.text.SimpleDateFormat;
import java.util.Date;

public class PetDocumentGenerator {
    private static int numberPet = 0;

    public static long generator(Specie specie){
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String year = sdf.format(now);
        String documentNew = year + specie.ordinal() + numberPet;
        numberPet++;

        return Long.parseLong(documentNew);
}
}
