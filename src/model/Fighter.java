package model;

public class Fighter {

    /**
     * Model.Fighter's name
     */
    private String name;

    /**
     * Model.Fighter's gender [Luchador / Diva]
     */
    private String gender;

    /**
     * Model.Fighter's Status [Leyenda/Superestrella/Midcarter/Jobber]
     */
    private String status;

    /**
     * Model.Fighter's brand [RAW/Smackdown/NXT]
     */
    private String brand;

    /**
     * Model.Fighter's power stat
     */
    private int statPOW;

    /**
     * Model.Fighter's though stat
     */
    private int statTGH;

    /**
     * Model.Fighter's speed stat
     */
    private int statSPD;

    /**
     * Model.Fighter's charity stat
     */
    private int statCHA;

    /**
     * Model.Fighter's domain stat
     */
    private String specialField;

    // Constructor
    public Fighter(String name, String gender, String status, String brand, int statPOW, int statTGH, int statSPD, int statCHA, String specialField) {
        this.name = name;
        this.gender = gender;
        this.status = status;
        this.brand = brand;
        this.statPOW = statPOW;
        this.statTGH = statTGH;
        this.statSPD = statSPD;
        this.statCHA = statCHA;
        this.specialField = specialField;
    }

    // Gets
    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getStatus() {
        return status;
    }

    public String getBrand() {
        return brand;
    }

    public String getSpecialField() {
        return specialField;
    }

    public int getStatPOW() {
        return statPOW;
    }

    public int getStatTGH() {
        return statTGH;
    }

    public int getStatSPD() {
        return statSPD;
    }

    public int getStatCHA() {
        return statCHA;
    }
}
