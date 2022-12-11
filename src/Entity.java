import java.text.DecimalFormat;

public class Entity {
    
    private String name;
    private double maxHP;
    private double hp;
    private double resist;
    private double damage;
    private double crit;
    private String msg;

    //setters and getters
    public void setMsg(String msg) {
            
        this.msg = msg;
        
    }
    
    public String getMsg() {
           
        return msg;
 
    }

    public void setName(String name) {
            
        this.name = name;
        
    }
    
    public String getName() {
           
        return name;
 
    }

    public void setMaxHP(double maxHP) {
            
        this.maxHP = maxHP;
        
    }
    
    public Double getMaxHP() {
           
        return maxHP;
 
    }

    public void setHP(double hp) {
            
        this.hp = hp;
        
    }
    
    public Double getHP() {
           
        return hp;
 
    }

    public void setResist(double resist) {
            
        this.resist = resist;
        
    }
    
    public Double getResist() {
           
        return resist;
 
    }

    public void setDamage(double damage) {
            
        this.damage = damage;
        
    }
    
    public Double getDamage() {
           
        return damage;
 
    }

    public void setCrit(double crit) {
            
        this.crit = crit;
        
    }
    
    public Double getCrit() {
           
        return crit;
 
    }

    //constructor
    public Entity(String name, double maxHP, double hp, double resist, double damage, double crit) {

        this.name = name;
        this.maxHP = maxHP;
        this.hp = hp;
        this.resist = resist;
        this.damage = damage;
        this.crit = crit;

    }

    //boolean to check if enitity is alive
    public boolean alive() {

        //returns true if hp if greater than 0, meaning alive
        return (hp > 0);

    }

    //resets hp for the restart scenario
    public void resetHP() {

        hp = maxHP;

    }

    //displays healthbar
    public String healthBar() {
        
        //starts the bra with '['
        String bar = name + ": [";
        int total = 20;
        //takes the ratio of hp to maxHP as a double and multiplies it by total count of healthbar "|"'s. Gets a value between 0-20 based on current hp
        double count = Math.round(((double)hp / maxHP) * total);
        
        //if the double count is rounded to 0 but the entity is alive, it gives the bar 1 "|"
        if (count == 0 && alive()) {
            
            count = 1;
        
        }
        
        //for loop takes the double count and adds a "|" to the health bar for as far as count goes
        for (int i = 0; i < count; i++) {
            
            bar += "|";

        }
        
        //for loop adds empty space for any missing health
        for (int i = 0; i < total - count; i++) {

            bar += " ";

        }

        //close of healthbar with "]"
        bar += "] ";

        //adds an exact # hp value
        bar += hp + " HP / " + maxHP + " HP";

        //returns the string
        return bar;
 
    }

    //heal method
    public void heal() {

        //if entitiy is full hp, print that they are full hp
        if (hp == maxHP) {

            System.out.println("\n" + name + " is already at maximum HP!\n");

        //if entity hp is in the range of the total heal, it only heals for the difference between hp and maxHP, then message is printed saying that entity was healed the difference
        } else if (hp > maxHP - 125) {

            double healthDiff = maxHP - hp;

            hp = maxHP;

            System.out.println("\n" + name + " regained " + healthDiff + " HP!\n");

        //else the entity can be healed for full effectiveness, and heals for 125
        } else {

            hp += 125;

            System.out.println("\n" + name + " regained 125.0 HP!\n");

        }

    }

    //atack method
    public void attack(Entity entity) {

        //if entity is not alive, print message. *Not necessary because fight method in ApplianceAssaultMain.java already check for death, but a falesafe
        if (entity.alive() == false) {

            System.out.println(name + " was unable to attack. " + entity.getName() + " is already dead!\n");

        //else entity can be attacked
        } else {

            double rand = Math.random();
            double dmgDealt = 50 + damage; //each entity has based damage of 50, adding their stat damage

            //prints message for attaack
            msg = "\n" + name + " attacked for " + dmgDealt + " damage!\n";

            //crit check. if double rand(decimal from 0-1) is <= crit rate, a crit was hit
            if (rand <= crit) {

                //crits multiply damage by 2
                dmgDealt = dmgDealt * 2;

                //replaces msg with crit message
                msg = "\n" + name + " landed a critical and did double damage for a total of " + dmgDealt + " damage!\n";

            }

            //prints msg
            System.out.println(msg);

            //takes the total maximum damage then sends it to the defend method which applies resist
            entity.defend(dmgDealt);

        }

    }

    //defend method
    public void defend(double dmgDealt) {

        //stores the damage that will be taken as a double that subtracts resist from the maximum damage
        double dmgTaken = dmgDealt - resist;

        //only loops if the dmgTaken is greater than 0, a falesafe even though resist doesn't grant full immunity
        if (dmgTaken > 0) {

            //subtract dmgTaken from hp
            hp -= dmgTaken;

            //if afterwards, hp is less than or equal to 0, set hp to 0 and set death message
            if (hp <= 0) {

                hp = 0;

                msg = name + " took a fatal blow and died!\n";

            //else set msg stating damage dealt
            } else {

                msg = name + "'s resist allowed it to take " + resist + " less damage. Total damage taken: " + dmgTaken + "\n";

            }
            
        }

        //prints msg
        System.out.println(msg);

    }

    @Override

    //toString prints your companion stats
    public String toString() {

        DecimalFormat df = new DecimalFormat("##.##");

        String msgHP = "Total Health: " + maxHP + "\n";
        String msgRes = "Resist: " + resist + "\n";
        String msgDmg = "Damage: 100 + " + damage + "\n";
        String msgCrit = "Critical Chance: " + df.format(crit * 100) + "%\n";

        return name + "\n" + msgHP + msgRes + msgDmg + msgCrit;

    }

}