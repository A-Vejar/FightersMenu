package controller;

import model.Fighter;
import model.FighterData;
import java.util.*;

public class FighterController {

    /**
     * Scanner
     */
    private static Scanner input = new Scanner(System.in);

    /**
     * Fighter's list
     */
    private static List<Fighter> fightersData = new ArrayList<Fighter>();

    /**
     * Fighter's aux list
     */
    private static List<Fighter> aux;

    /**
     * Type of battle challenge
     */
    private static String battles[] = new String[]{"POW", "TGH", "SPD", "CHA"};

    /**
     * DB modified message
     */
    private static String change;

    /**
     * Ask to come back to the Main-Menu
     */
    private static void backMenu() {

        System.out.print("\nBack to menu !? [y/n]: ");
        String choose = input.next();

        if(choose.equalsIgnoreCase("y")) {
            aux = fightersData;
            Menu.MainMenu();
        }
        if(choose.equalsIgnoreCase("n")) {
            System.out.println("Program done[Battles has been save].");
        }
    }

    /*------------------------------------------------------------------------------------------------------------------------------------------
                DISPLAY FIGHTERS
    ------------------------------------------------------------------------------------------------------------------------------------------*/
    /**
     * MENU OPTION 1 - Fighters Display
     */
    public static void displayFighters() {
        String search;

        System.out.println("----------------- Display Fighters Sub-Menu -----------------");
        System.out.print("1. Display all fighters\n2. Display by brand\n3. Display by status\n4. Exit\nChoose: ");

        int choose = input.nextInt();
        switch(choose) {

            // Displays all fighters
            case 1:
                System.out.println("-------------------- All Fighters --------------------");
                searchFighters(null);
                break;

            // Displays all fighters searched by its "brand"
            case 2:
                System.out.print("Brand [RAW/Smackdown/NXT]: ");
                search = input.next();

                System.out.println("-------------------- Fighter's brand --------------------");
                searchFighters(search);
                break;

            // Displays all fighters searched by its "status"
            case 3:
                System.out.print("Status [Leyenda/Superestrella/Midcarter/Jobber]: ");
                search = input.next();

                System.out.println("-------------------- Fighter's status --------------------");
                searchFighters(search);
                break;

            // Back to the Main-Menu
            case 4:
                Menu.MainMenu();
                break;
        }

        backMenu();
    }

    /**
     * Display all the fighters or search them by its "brand" or "stat"
     * @param search - Type of fighters to looking for
     */
    private static void searchFighters(String search) {
        int cont = 0;

        // If the DB is not set (modified), the "aux" list is null
        if(aux == null) {
            FighterData.read(fightersData);
        }else {
            // DB modified
            System.out.println(change);
        }

        for(Fighter f : fightersData) {
            // SEARCH OMITTED
            if(search == null) {
                fighterData(f);
                cont++;
            }
            // BRAND
            if(f.getBrand().equalsIgnoreCase(search)) {
                fighterData(f);
                cont++;
            }
            // STATUS
            if(f.getStatus().equalsIgnoreCase(search)) {
                fighterData(f);
                cont++;
            }
        }
        System.out.println("Size: " + cont);
    }

    /**
     * Display the fighters info
     * @param fighter
     */
    private static void fighterData(Fighter fighter) {
        System.out.println("Name: " + fighter.getName() + " | Genre: " + fighter.getGender());
        System.out.println("Brand: " + fighter.getBrand() + " | Status: " + fighter.getStatus());

        System.out.println("-------- Stats --------");
        System.out.println("Speciality: " + fighter.getSpecialField());
        System.out.println("POW: " + fighter.getStatPOW() + " | TGH: " + fighter.getStatTGH());
        System.out.println("SPD: " + fighter.getStatSPD() + " | CHA: " + fighter.getStatCHA());
        System.out.println("---------------------------------------------");
    }

    /*------------------------------------------------------------------------------------------------------------------------------------------
                SORT DATABASE
    ------------------------------------------------------------------------------------------------------------------------------------------*/
    /**
     * MENU OPTION 2 - DATABASE Order
     */
    public static void sortDB() {

        System.out.println("----------------- Database Sub-Menu -----------------");
        System.out.print("1. Sort fighters by name [A-Z]\n2. Sort fighters by stats\n3. Exit\nChoose: ");

        int choose = input.nextInt();
        switch(choose) {

            // Sort the DB by its "names"
            case 1:
                aux = fightersData;
                if(aux.isEmpty()) {
                    FighterData.read(aux);
                }

                Collections.sort(fightersData, new Comparator<Fighter>() {
                    @Override
                    public int compare(Fighter o1, Fighter o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
                change = "NAMES HAS BEEN SORT[A-Z] ...";

                System.out.println("DATABASE UPDATED ...");
                break;

            // Sort the DB by its "stats"
            case 2:
                aux = fightersData;
                if(aux.isEmpty()) {
                    FighterData.read(aux);
                }

                System.out.println("--------------- STATS ---------------");
                System.out.print("1. POW\n2. TGH\n3. SPD\n4. CHA\nChoose: ");
                int select = input.nextInt();

                for(int i = 0; i < battles.length; i++) {
                    if(select == (i+1)) {

                        Collections.sort(fightersData, new Comparator<Fighter>() {
                            @Override
                            public int compare(Fighter o1, Fighter o2) {

                                int f1[] = new int[]{o1.getStatPOW(), o1.getStatTGH(), o1.getStatSPD(), o1.getStatCHA()};
                                int f2[] = new int[]{o2.getStatPOW(), o2.getStatTGH(), o2.getStatSPD(), o2.getStatCHA()};
                                return new Integer(f1[select-1]).compareTo(new Integer(f2[select-1]));
                            }
                        });
                        change = battles[i] + " HAS BEEN SORT ...";
                    }
                }

                System.out.println("DATABASE UPDATED ...");
                break;

            case 3:
                break;
        }
        Menu.MainMenu();
    }

    /*------------------------------------------------------------------------------------------------------------------------------------------
                WWE WAR-GAME[ALPHA]
    ------------------------------------------------------------------------------------------------------------------------------------------*/
    /**
     * MENU OPTION 3 - WWE Alpha Game
     */
    public static void warGame() {

        aux = fightersData;
        if(aux.isEmpty()) {
            FighterData.read(aux);
        }

        System.out.println("----------------- WWE WarGame[vAlpha] -----------------");

        // Display all fighters
        int n = 1;
        for(Fighter f : fightersData) {
            System.out.println(n + ". " + f.getName());
            n++;
        }

        // Select the fighters the user wants(3)
        Fighter myFighter[] = new Fighter[3];

        for(int i = 0; i < 3; i++) {
            System.out.print("Choose your fighters[" + (3 - i) + "]: ");
            String name = input.nextLine();

            for(int z = 0; z < fightersData.size(); z++) {
                if(name.equalsIgnoreCase(fightersData.get(z).getName())) {
                    myFighter[i] = fightersData.get(z);

                    fightersSelected(myFighter[i].getName());
                    break;
                }
                else {
                    if(z == (fightersData.size()-1)) {
                        System.out.println("Fighter not found (Try again)... ");
                        i -= 1;
                    }
                }
            }

            /*
            //OTHER FORM ...
            int y = 0;
            while(!name.equalsIgnoreCase(fightersData.get(y).getName())) {
                y++;
                if(!name.equalsIgnoreCase(fightersData.get(y).getName()) && y == (fightersData.size()-1)) {
                    y = 0;
                    System.out.print("Fighter not found (Try again)... ");
                    name = input.nextLine();
                }
            }
            myFighter[i] = fightersData.get(y);
            fightersSelected(myFighter[i].getName());
            */
        }

        System.out.println("---------------------- BATTLE PHASE----------------------");
        for(int i = 0; i < 3; i++) {
            System.out.println((i+1) + ". " + myFighter[i].getName());
        }
        System.out.print("Choose your main fighter: ");
        int select = input.nextInt();

        System.out.println("------------------------ BATTLE ------------------------");
        battle(select, myFighter);

        FighterData.write();
        backMenu();
    }

    /**
     * Display the fighters info selected by the user
     * @param name - Selected by name
     */
    private static void fightersSelected(String name) {
        for(Fighter f : fightersData) {
            if(name.equalsIgnoreCase(f.getName())) {
                System.out.println("------------------------------------------------");
                System.out.println("Name: " + f.getName() + " | Genre: " + f.getGender());

                System.out.println("---------- Stats ----------");
                System.out.println("Speciality: " + f.getSpecialField());
                System.out.print("POW:" + f.getStatPOW() + " | TGH:" + f.getStatTGH());
                System.out.println(" | SPD:" + f.getStatSPD() + " | CHA:" + f.getStatCHA());
                System.out.println("------------------------------------------------");
            }
        }
    }

    /**
     * Battle between fighters
     * @param select - Fighter selected
     * @param myFighter - The fighter
     */
    private static void battle(int select, Fighter myFighter[]) {

        // Gets type of battle to make(by random)
        int type = (int) (Math.random()*battles.length);
        System.out.println("TYPE OF BATTLE: " + battles[type]);

        // Display the fighters selected to fight each other
        for(int i = 0; i < 3; i++) {
            if(select == (i+1)) {
                System.out.println("Fighter Selected ...");
                fightersSelected(myFighter[i].getName());

                System.out.println("VS ...");

                // If both fighters has different gender, search again for a new one
                Fighter npc;
                do {
                    // Gets the challenge's fighter(by random)
                    int num = (int) (Math.random()*fightersData.size());
                    npc = fightersData.get(num);

                    if(myFighter[i].getGender().equals(npc.getGender()) || myFighter[i].getName().equals(npc.getName())) {
                        fightersSelected(npc.getName());
                    }

                }while(!myFighter[i].getGender().equals(npc.getGender()));


                // ------------------------------------------------------------------------------------------------------------
                FighterData.saveFighters(myFighter[i], npc);
                // ------------------------------------------------------------------------------------------------------------

                // Result between both fighters fight
                System.out.println("\n--------------- RESULT ---------------");
                battleResult(battles[type], myFighter[i], npc);
            }
        }
    }

    /**
     * Result battle between fighters
     * @param battle - Type of battle
     * @param myFighter - The fighter
     * @param npc - The fighter opponent
     */
    private static void battleResult(String battle, Fighter myFighter, Fighter npc) {

        int auxUser[] = new int[]{myFighter.getStatPOW(), myFighter.getStatTGH(), myFighter.getStatSPD(), myFighter.getStatCHA()};
        int auxNpc[] = new int[]{npc.getStatPOW(), npc.getStatTGH(), npc.getStatSPD(), npc.getStatCHA()};

        for(int i = 0; i < battles.length; i++) {
            if(battle.equals(battles[i])) {

                // Fighter Boost
                if(myFighter.getSpecialField().equals(battles[i])) {
                    System.out.println(myFighter.getName() + " - " + battles[i] + " boost(15)");
                    auxUser[i] += 15;
                }
                if(npc.getSpecialField().equals(battles[i])) {
                    System.out.println(npc.getName() + " - " + battles[i] + " boost(15)");
                    auxNpc[i] += 15;
                }

                // Result of the Battle [Win/Lost/Draw]
                if(auxUser[i] == auxNpc[i]) {
                    System.out.println("Type of Battle: " + battle);
                    System.out.println(myFighter.getName() + "[Draw]: " + auxUser[i]);
                    System.out.println(npc.getName() + "[Draw]: " + auxNpc[i]);

                    // ----------------------------------------------------------------------------------------------------------------------
                    FighterData.saveFightResult("DRAW", "DRAW", battle, myFighter.getName(), npc.getName(), auxUser[i], auxNpc[i]);
                    break;
                    // ----------------------------------------------------------------------------------------------------------------------
                }
                if(auxUser[i] > auxNpc[i]) {
                    System.out.println("Type of Battle: " + battle);
                    System.out.println(myFighter.getName() + "[Winner]: " + auxUser[i]);
                    System.out.println(npc.getName() + "[Defeated]: " + auxNpc[i]);

                    // ----------------------------------------------------------------------------------------------------------------------
                    FighterData.saveFightResult("Winner", "Defeated", battle, myFighter.getName(), npc.getName(), auxUser[i], auxNpc[i]);
                    break;
                    // ----------------------------------------------------------------------------------------------------------------------
                }
                else {
                    System.out.println("Type of Battle: " + battle);
                    System.out.println(myFighter.getName() + "[Defeated]: " + auxUser[i]);
                    System.out.println(npc.getName() + "[Winner]: " + auxNpc[i]);

                    // ----------------------------------------------------------------------------------------------------------------------
                    FighterData.saveFightResult("Defeated", "Winner", battle, myFighter.getName(), npc.getName(), auxUser[i], auxNpc[i]);
                    break;
                    // ----------------------------------------------------------------------------------------------------------------------
                }
            }
        }
    }
}
