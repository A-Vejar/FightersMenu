package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

public class FighterData {

    /**
     * Player's fighter
     */
    private static Fighter fPlayer;

    /**
     * Npc's fighter
     */
    private static Fighter fNpc;

    /**
     * Text-file path
     */
    private static String PATH = "src/RosterWWE.txt";

    /**
     * Fights results attributes
     */
    private static String resultPlayer;
    private static String resultNpc;
    private static String battleType;
    private static String playerUser;
    private static String playerNpc;
    private static int playerBoost;
    private static int npcBoost;

    // Counter
    private static int count = 1;

    /**
     * Read all the file
     * @param fightersData - Fill the list with the fighters inside the .txt file
     */
    public static void read(List<Fighter> fightersData) {

        String line = "";
        try(Scanner file = new Scanner(new BufferedReader(new FileReader(PATH))).useDelimiter(",\\s")) {

            while (file.hasNextLine()) {
                line = file.nextLine();

                if(line != null) {
                    // Stores a full line from the file
                    String[] data = line.split(",");

                    // Add the data to a fighters
                    Fighter fighter = new Fighter(
                            data[0], // Name
                            data[1], // Genre
                            data[2], // Status
                            data[3], // Brand
                            Integer.parseInt(data[4]), // POW stat
                            Integer.parseInt(data[5]), // TGH stat
                            Integer.parseInt(data[6]), // SPD stat
                            Integer.parseInt(data[7]), // CHA stat
                            data[8] // Special field (stat)
                    );
                    fightersData.add(fighter);

                }else {
                    file.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Write in a .txt file all the result of battles between fighters
     */
    public static void write() {

        StringBuffer data = new StringBuffer();
        try {

            FileWriter writer = new FileWriter("C:/Users/ariel/Desktop/WarGame_battleInfo.txt", true);

            data.append("*** BATTLE " + count).append("\n");
            data.append(saveFighters(fPlayer, fNpc))
                    .append("\n");
            data.append(saveFightResult(resultPlayer, resultNpc, battleType, playerUser, playerNpc, playerBoost, npcBoost))
                    .append("\n---------------------------------------------------------------------------\n");

            for(int i = 0; i < data.length(); i++) {
                writer.write(data.charAt(i));
            }

            count++;
            writer.close();

        }catch(Exception e) {
            e.getMessage();
        }
    }

    /**
     * Stores the fighters[Player and Npc]
     * @param p - Player fighter
     * @param n - Npc fighter
     * @return - Both fighters data inside a "String"
     */
    public static String saveFighters(Fighter p, Fighter n) {

        StringBuffer strB = new StringBuffer();

        fPlayer = p;
        fNpc = n;

        strB.append("-------------------- PLAYER --------------------\n")
                .append("Name: " + fPlayer.getName() + " | Genre: " + fPlayer.getGender())
                .append("\n---------- Stats ----------\n")
                .append("Speciality: " + fPlayer.getSpecialField()).append("\n")
                .append("POW:" + fPlayer.getStatPOW() + " | TGH:" + fPlayer.getStatTGH()
                        + " | SPD:" + fPlayer.getStatSPD() + " | CHA:" + fPlayer.getStatCHA())
                .append("\n\n--------------------- NPC ---------------------\n")
                .append("Name: " + fNpc.getName() + " | Genre: " + fNpc.getGender())
                .append("\n---------- Stats ----------\n")
                .append("Speciality: " + fNpc.getSpecialField()).append("\n")
                .append("POW:" + fNpc.getStatPOW() + " | TGH:" + fNpc.getStatTGH()
                        + " | SPD:" + fNpc.getStatSPD() + " | CHA:" + fNpc.getStatCHA());
        strB.append("\n------------------------------------------------\n");

        return strB.toString();
    }

    /**
     * Stores the result of a battle between fighters
     * @param rPlayer - Battle result[Win or Lose]
     * @param rNpc - Battle result[Win or Lose]
     * @param battle - Type of battle
     * @param player - Player's fighter name
     * @param npc - Npc's fighters name
     * @param pBoost - Player battle stat
     * @param nBoost - Npc battle stat
     * @return - The result of a battle from both fighters inside a "String"
     */
    public static String saveFightResult(String rPlayer, String rNpc, String battle, String player, String npc, int pBoost, int nBoost) {

        StringBuffer strB = new StringBuffer();

        resultPlayer = rPlayer;
        resultNpc = rNpc;
        battleType = battle;
        playerUser = player;
        playerNpc = npc;
        playerBoost = pBoost;
        npcBoost = nBoost;

        strB.append("Battle Results ...\n")
                .append("----------------------------\n")
                .append("Type of Battle: " + battleType).append("\n\n");

        if(fPlayer.getSpecialField().equals(battleType)) {
            strB.append(playerUser + " - " + battleType + " boost(15)\n");
        }
        if(fNpc.getSpecialField().equals(battleType)) {
            strB.append(playerNpc + " - " + battleType + " boost(15)\n");
        }

        strB.append(playerUser + "["+ resultPlayer + "]: " + playerBoost).append("\n")
                .append(playerNpc + "["+ resultNpc + "]: " + npcBoost).append("\n");

        return strB.toString();
    }
}