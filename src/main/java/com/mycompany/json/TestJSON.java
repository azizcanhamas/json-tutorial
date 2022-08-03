package com.mycompany.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// SOURCE : https://howtodoinjava.com/java/library/json-simple-read-write-json-examples/

public class TestJSON {
    
    public static void main(String[] args) throws ParseException {
        TestJSON t=new TestJSON();
        t.JSON_READ();
    }

    public void JSON_WRITE(){
    
        // ============ DENEME-2 : com.googlecode.json-simple ============0       
        //  Artifact ID : json-simple
        //  Group ID: com.googlecode.json-simple
        //  Version: 1.1.1
        // "Proje Adi" -> Dependices -> Sağ Click -> Add Dependency -> Query(com.googlecode.json-simple) -> "+" -> 1.1.1 [jar]
       
        //Calisan bilgilerinin derlenmesi
        JSONObject employeeDetails = new JSONObject();
        employeeDetails.put("firstName", "Lokesh");
        employeeDetails.put("lastName", "Gupta");
        employeeDetails.put("website", "howtodoinjava.com");
//        System.out.println(employeeDetails.toJSONString());
        // OUTPUT : {"firstName":"Lokesh","lastName":"Gupta","website":"howtodoinjava.com"}
         
        //Calisan bilgilerinin calisana ait oldugunun belirtilmesi  
        JSONObject employeeObject = new JSONObject(); 
        employeeObject.put("employee", employeeDetails);        
        System.out.println(employeeObject.toJSONString());
        // OUTPUT : {"employee":{"firstName":"Lokesh","lastName":"Gupta","website":"howtodoinjava.com"}}
        
//       Baska bir calisan bilgilerinin derlenmesi
        JSONObject employeeDetails2 = new JSONObject();
        employeeDetails2.put("firstName", "Brian");
        employeeDetails2.put("lastName", "Schultz");
        employeeDetails2.put("website", "example.com");
        
//      Calisan bilgilerinin calisana ait oldugunun belirtilmesi  
        JSONObject employeeObject2 = new JSONObject(); 
        employeeObject2.put("employee", employeeDetails2);
        
//        Tum calisanlarinin bilgilerinin array'de toplanmasi
        JSONArray employeeList = new JSONArray();
        employeeList.add(employeeObject);
        employeeList.add(employeeObject2);
        System.out.println(employeeList.toJSONString());
        
         
        //Bilgilerin JSON dosyasina yazdirilmasi
        try (FileWriter file = new FileWriter("employees.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(employeeList.toJSONString()); 
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public void JSON_READ() throws ParseException{
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("employees.json"))
        {
            // JSON dosyasinin okunmasi
            Object obj = jsonParser.parse(reader);
            
            //Dosya ilk Array ile basliyor. İlk Array alinmali.
            JSONArray employeeList = (JSONArray) obj;
            System.out.println(employeeList);
            
            //Artik Array'in ici isleme alinacak.
            //Array'in altinda iki adet employee nesnesi var.
            //Employee nesneleri de kendi altinda calisanlarin ad,soyad,website
            //bilgilerini tutuyor.
            System.out.println("=======================");
            for (Object emp : employeeList) {
                JSONObject jo=(JSONObject)emp;
                JSONObject employee=(JSONObject)jo.get("employee");
                
                System.out.println(employee.get("firstName"));
                System.out.println(employee.get("lastname"));
                System.out.println(employee.get("website"));
                System.out.println("=====================");
            }
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(ParseException e){
            e.printStackTrace();
        }
        
    }
        
}
