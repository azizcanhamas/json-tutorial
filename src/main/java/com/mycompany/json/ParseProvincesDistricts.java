package com.mycompany.json;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.parser.*;

public class ParseProvincesDistricts {
    
    public static void main(String[] args) {
        try(InputStreamReader file=new InputStreamReader(new FileInputStream("iller-ilceler.json"),"utf-8")){
            // Tum dosyanin parse edilmesi.
            JSONParser parser=new JSONParser();
            Object obj=parser.parse(file);
           
            // Hiyerarside en yukarda JSONArray var.
            // Ilk olarak JSONArray'e cevrilir.
            JSONArray jo_obj=(JSONArray)obj;
            
            // Ilk JSONArray'in altinda {} ile belirtilen objeler var.
            // Bu objelerin her biri bir sehri temsil eder.
            // Dongu ile gezilmelidir.
            for (Object object : jo_obj) {
                // Her bir sehir objesine "get()" metodunu uygulayabilmek icin
                // JSONObject ile bilincli donusum uygulanmalidir.
                // Ilk adimda value ve text degerlerinin cagrilmasi ile
                // sehrin adi ve sehir kodu alinir.
                JSONObject jo=(JSONObject)object;
                System.out.println(jo.get("value")+"   "+jo.get("text"));
                System.out.println("=================================");
                
                // districts nesnesi get edildigi zaman JSONArray donmektedir.
                // Bu yuzden bilincli donusum ile JSONArray'e cevrilir.
                JSONArray jo_obj_district=(JSONArray)jo.get("districts");
                for (Object object1 : jo_obj_district) {
                    // JSONArray icindeki her JSONObject nesnesi bir 
                    // ilce ile ilgili bilgileri tutar. get metodu ile ilcenin
                    // adi ve kodu alinir.
                    JSONObject jo_obj_2=(JSONObject)object1;
                    System.out.println(jo_obj_2.get("value")+"   "+jo_obj_2.get("text"));
                }
                System.out.println("=================================");
                
            }
        }catch(IOException e){
            System.out.println("ERROR: "+e);
        } catch (ParseException ex) {
            Logger.getLogger(ParseProvincesDistricts.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
