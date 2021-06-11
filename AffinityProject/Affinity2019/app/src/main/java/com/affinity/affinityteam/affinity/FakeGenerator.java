package com.affinity.affinityteam.affinity;



import com.affinity.affinityteam.affinity.Models.Logro;
import com.affinity.affinityteam.affinity.Models.Topic;
import com.affinity.affinityteam.affinity.Models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class FakeGenerator {


        public String randomPW(String name) {
            //lista random de names
            String[] nameList = new String[]{"Aaren","Aarika","Abagael","Abagail","Abbe","Abbey","Abbi","Abbie","Abby","Abbye","Abigael","Abigail","Abigale","Abra","Ada"};
            return nameList[new Random().nextInt(12)] + "123";
        }

        public ArrayList<Topic> topicsGenerator(){
            //20 interesos con un peso random 0 -300;
            ArrayList<Topic> topics = new ArrayList<Topic>();
            Topic actualTopic;
            ArrayList<String> topicsNames = new ArrayList(Arrays.asList("Art Lover","Bookworm","Geek","Cooker","Music Lover","Food Lover", "Family Person","Gym Lover","Traveler","Cinema Lover","Fashion","Netflixer","Festivals","Theatre","Astronomy","Gamer","Party Lover","Sport Lover","Animal Lover","Dance Lover"));
            for(int i=0;i<topicsNames.size();i++){
                actualTopic = new Topic();
                if(i>6){
                    break;
                }
                int indexRandom = new Random().nextInt(topicsNames.size());
                actualTopic.setNombre(topicsNames.get(indexRandom));
                actualTopic.setPeso(100 * (new Random().nextInt(3)));
                topicsNames.remove(indexRandom);
                topics.add(actualTopic);


            }
            return topics;
        }

        public String randomCareer(){
            String[] careerList = new String[]{"GENERAL AGRICULTURE","ANIMAL SCIENCES","FOOD SCIENCE","FORESTRY","FINE ARTS","DRAMA AND THEATER ARTS","MUSIC","GRAPHIC DESIGN","ENVIRONMENTAL SCIENCE","GENETICS","NEUROSCIENCE","COGNITIVE SCIENCE","GENERAL BUSINESS","ACCOUNTING","BUSINESS ECONOMICS","INTERNATIONAL BUSINESS","JOURNALISM","COMPUTER SCIENCE","MATHEMATICS","EARLY CHILDHOOD EDUCATION","ARCHITECTURE","ENGINEERING TECHNOLOGIES","PHILOSOPHY","ART HISTORY","GEOLOGY","PHYSICS","OCEANOGRAPHY","PSYCHOLOGY","SOCIOLOGY","GEOGRAPHY"};
            return careerList[new Random().nextInt(careerList.length)];
        }

        public String nameGenerator(int i){
            String[] nameList = new String[]{"Aaren","Aarika","Abagael","Abagail","Abbe","Abbey","Abbi","Abbie","Abby","Abbye","Abigael","Abigail","Abigale","Abra","Ada"};
            return nameList[new Random().nextInt(14)] + i;


        }

        public String descriptionGenerator(User current){
            return "Soy "+current.getNombre()+" y estudio "+current.getCarrera()+" en la Universidad "+ current.getUniversidadDestino()+", quieres ser mi amigote?";
        }

        public String randomUniversity(){
            String[] universityList = new String[]{"Roma Tre","Sapienza","Tor Vergata","LUISS","U. de Granada","U. de Valparaiso"};
            return universityList[new Random().nextInt(universityList.length)];

        }

        public ArrayList<User> listUserGenerator(int size){
            //retorna una lista de usuarios
            ArrayList<User> uList = new ArrayList<User>();
            User userActual = new User();
            ArrayList<Logro> logros = new ArrayList();
            ArrayList<Topic> commonTopics = new ArrayList<>();
            commonTopics.add(new Topic());

            logros.add(new Logro(0,"nulo",0));

            for(int i=0;i<size;i++){
                userActual = new User();
                String name= nameGenerator(i);
                userActual.setNombre(name);
                userActual.setUsername(name+"_username");
                userActual.setRadioBusqueda(new Random().nextDouble() *30);
                userActual.setUser_type(new Random().nextInt(2));
                userActual.setStatus("offline");
                userActual.setImageURL("default");
                setRandomLocation(userActual);
                userActual.setCarrera(randomCareer());
                userActual.setUniversidadDestino(randomUniversity());
                userActual.setUniversidadOrigen(randomUniversity());
                userActual.setDescripcion(descriptionGenerator(userActual));
                userActual.setTopics(topicsGenerator());
                //userActual.setLogros(logros);
                userActual.setPaisOrigen("Chambaluco");
                userActual.setPaisDestino("Chambala");
                userActual.setCiudadActual("Minnessota");
                userActual.setUserState(true);
                userActual.setEdad(i+12);
                uList.add(userActual);
            }

            return uList;
        }

    //Updated by Manu
    public void setRandomLocation(User userActual){
        //Random location inside Rome...
        double baseLat = 41.790197;
        double baseLon = 12.372998;
        double maxDeltaLat = 0.209803;
        double maxDeltaLon = 0.241594;
        Random r = new Random();
        double tempLat = baseLat + (maxDeltaLat*r.nextDouble());
        double tempLon = baseLon + (maxDeltaLon*r.nextDouble());
        //se reduce a 6 digitos...
        //String temp2Lat = String.format("%.6f", tempLat); //Double.parseDouble();
        //String temp2Lon = String.format("%.6f", tempLon);//Double.parseDouble();
        long factor = (long) Math.pow(10, 6);
        tempLat = tempLat * factor;
        tempLon = tempLon * factor;
        long tmpLat = (long) tempLat;
        long tmpLon = (long) tempLon;
        //NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
        //double randLat = nf.parse(temp2Lat).doubleValue();
        //double randLon = nf.parse(temp2Lon).doubleValue();

        double randLat = (double)tmpLat/factor;
        double randLon = (double)tmpLon/factor;
        //double randLon = Double.parseDouble(tempLon);

        System.out.println("rLat: "+randLat+" rLon: "+randLon);

        userActual.setUserLat(randLat);
        userActual.setUserLon(randLon);
    }
}


