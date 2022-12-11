import java.util.*;

public class ApplianceAssaultMain{

    //default companion stats
    private static double defaultMaxHP = 1000;
    private static double defaultHP = defaultMaxHP;
    private static double defaultResist = 15;
    private static double defaultDamage = 45;
    private static double defaultCrit = 0.025;

    //constructs the entities
    private static Entity companion = new Entity(null, defaultMaxHP, defaultHP, defaultResist, defaultDamage, defaultCrit);
    private static Entity bossI = new Entity("Infernal Infernacoil", 1000, 1000, 10, 30, 0.40);
    private static Entity bossS = new Entity("Slippery Sinkadrink", 1250, 1250, 30, 50, 0.60);
    private static Entity bossF = new Entity("Freljord Frijjafreeze", 2000, 1500, 75, 75, 0.75);

    //Scanner
    private static Scanner read = new Scanner(System.in);

    //ArrayList stores inventory items
    private static ArrayList<String> companionGear = new ArrayList<String>();

    //shop method
    public static void shop(int shopID) {

        //Hashmaps to store the items in the shop, and the stat associated with the item
        HashMap<String, String> shopItems = new HashMap<String, String>();
        HashMap<String, Double> gearStats = new HashMap<String, Double>();

        //shops are switched using shop ID's. Each shopID contains different items, progressively getting better
        //first shop
        if (shopID == 1) {

            //puts item name into HashMap
            shopItems.put("HP", "Ghostly Visage");
            shopItems.put("Resist", "Ninja Steelcaps");
            shopItems.put("Damage", "Chem-Tech Chainsword");
            shopItems.put("Crit" ,"Nashor's Wisdom Tooth");

            //puts gear stats into HashMap
            gearStats.put("HP", 400.0);
            gearStats.put("Resist", 20.0);
            gearStats.put("Damage", 55.0);
            gearStats.put("Crit", 0.25);

        } else if (shopID == 2) {

            //puts item name into HashMap
            shopItems.put("HP", "Dragoon's Crippled Cap");
            shopItems.put("Resist", "Drape of the Exalted");
            shopItems.put("Damage", "DuskBreaker");
            shopItems.put("Crit" ,"Outfinity Edge");

            //puts gear stats into HashMap
            gearStats.put("HP", 750.0);
            gearStats.put("Resist", 25.0);
            gearStats.put("Damage", 80.0);
            gearStats.put("Crit", 0.3);

        } else if (shopID == 3) {

            //puts item name into HashMap
            shopItems.put("HP", "Warmog's Woodplate");
            shopItems.put("Resist", "Gargoyle's Rockplate");
            shopItems.put("Damage", "Diamond Sword");
            shopItems.put("Crit" ,"Sinbad's Soulhunter");

            //puts gear stats into HashMap
            gearStats.put("HP", 1000.0);
            gearStats.put("Resist", 30.0);
            gearStats.put("Damage", 125.0);
            gearStats.put("Crit", 0.4);

        }

        System.out.println("Shady Shopkeeper:'Hey, hey, over here. Yea, you want the goods? These are the specials!'\n");

        pause(5);

        //prints out shop items as a list
        System.out.println("----------------------------------------------------------------------------------------------------\n");
        System.out.println("1. Health item: " + shopItems.get("HP") + " | Adds " + gearStats.get("HP") + " HP to your character!");
        System.out.println("2. Resist item: " + shopItems.get("Resist") + " | Adds " + gearStats.get("Resist") + " resist to your character!");
        System.out.println("3. Damage item: " + shopItems.get("Damage") + " | Adds " + gearStats.get("Damage") + " damage to your character!");
        System.out.println("4. Critical item: " + shopItems.get("Crit") + " | Adds " + gearStats.get("Crit") * 100 + "% critical chance to your character!");
        System.out.println("\n----------------------------------------------------------------------------------------------------\n");
        System.out.println("Which item do you want to equip? (Input a # from 1-4)\n");

        //boolean to ensure correct input
        boolean correctInp = false;

        //do/while loop that runs as long as correctInp = false, if correct input is used, the correctInp will be turned into true, ending the loop
        do {

            System.out.print("> ");
            //storing integer response as a String to prevent scanner issue where read.nextInt method makes next readLine method autimatically store an empty value
            String itemChoiceStr = read.nextLine();
            //parses String as an int
            int itemChoice = Integer.parseInt(itemChoiceStr);

            //based on user response, print item message, add item stats to companion, add item to inventory ArrayList and set correctInp to true
            if (itemChoice == 1) {

                System.out.println("\n'The healthy option I see...'\nYou have successfully equipped " + shopItems.get("HP") + " and got an extra " + gearStats.get("HP") + " health!\n");
                companion.setMaxHP(companion.getMaxHP() + gearStats.get("HP"));
                companionGear.add(shopItems.get("HP"));
                correctInp = true;

            } else if (itemChoice == 2) {

                System.out.println("\n'The defensive approach, very well...'\nYou have successfully equipped " + shopItems.get("Resist") + " and got an extra " + gearStats.get("Resist") + " resist!\n");
                companion.setResist(companion.getResist() + gearStats.get("Resist"));
                companionGear.add(shopItems.get("Resist"));
                correctInp = true;

            } else if (itemChoice == 3) {

                System.out.println("\n'You will unleash pain upon your enemies with this one!'\nYou have successfully equipped " + shopItems.get("Damage") + " and got an extra " + gearStats.get("Damage") + " damage!\n");
                companion.setDamage(companion.getDamage() + gearStats.get("Damage"));
                companionGear.add(shopItems.get("Damage"));
                correctInp = true;

            } else if (itemChoice == 4) {

                System.out.println("\n'A gambler with pain, you are. You have increased your chances, but at what cost?'\nYou have successfully equipped " + shopItems.get("Crit") + " and got an extra " + gearStats.get("Crit") * 100 + "% critical chance!\n");
                companion.setCrit(companion.getCrit() + gearStats.get("Crit"));
                companionGear.add(shopItems.get("Crit"));
                correctInp = true;

            } else {

                System.out.println("\nInvalid item ID, try again.\n");
        
            }
        
        } while (correctInp == false);

        pause(5);

        //prints inventory
        System.out.println(inventory());

        pause(3);

        //prints companion stats
        System.out.println(companion);

        pause(5);

    }

    //inventory method
    public static String inventory() {

        String inventory = "Inventory:\n";

        //for loop retrieves items and adds it to the String inventory from each ArrayList index based on the array size
        for (int i = 0; i < companionGear.size(); i++) {

            inventory += companionGear.get(i) + "\n";

        }

        //return the String inventory
        return inventory;

    }

    //pause method uses Thread.sleep to delay outputs
    public static void pause(int sec) {

        int delay = sec * 1000;

        try {

            Thread.sleep(delay);

        } catch (InterruptedException ex) {}

    }

    //method for the story
    public static void loreStart(boolean skip) {

        boolean correctInp = false;

        //if user does not want to skip, starts from beginning
        if (skip == false) {

            System.out.println("\nChocolate Brownie the Pimp: \n'I've got news, and it ain't good news! Since the last time you fought and");

            pause(5);
    
            System.out.println("defeated the appliances, they formed an underground faction, swearing to seek revenge.");
    
            pause(5);
    
            System.out.println("That day of vengeance has come. Zaun, the mastermind behind all of this has mutated the");
    
            pause(5);
    
            System.out.println("appliances to be relentless. He has set up a gauntlet of sorts in the city of Withered Cupboards,");
            
            pause(5);
    
            System.out.println("and invited all the companions back. However they didn't make it so simple. Our intel acquired");
    
            pause(5);
    
            System.out.println("knowledge that the gauntlet is rigged to only allow one of you in, trapping whoever was first.");
    
            pause(5);
    
            System.out.println("Knowing this, we are going to play this trick our own way. We will equip only one of you with all");
    
            pause(5);
    
            System.out.println("the necessary tools and tricks necessary to defeat these goons. It's your choice. We either");
    
            pause(5);
    
            System.out.println("proceed with their games or we back down and surrender our reign over the kitchen...'\n");
    
            pause(5);
    
            System.out.println("(P)roceed or (S)urrender\n");

            //do loop runs as log as correctInp is false, correct input turns the boolean to true, ending the loop
            do {
            
                System.out.print("> ");
                String playGame = read.nextLine();
    
                if (playGame.equalsIgnoreCase("p")) {
    
                    System.out.println("\nChocolate Brownie the Pimp: \n'Alrighty, that's what I like to hear. So we've snuck in a black market");
                    
                    pause(5);
                    
                    System.out.println("gear salesman who is going to hook you up with all the equipment, however");
                    
                    pause(5);
                    
                    System.out.println("he can only provide one piece of gear before the fight and then one piece");
                    
                    pause(5);
    
                    System.out.println("after each fight. We asked why, but that lil Snicker won't speak.");
                    
                    pause(5);
    
                    System.out.println("We don't have time to replace him, so just deal with him. You will");
    
                    pause(5);
    
                    System.out.println("dispatch one of the three companions tomorrow. Question is,");
    
                    pause(5);
    
                    System.out.println("who you pickin' for such a mission?'\n");
                    
                    correctInp = true;
    
                } else if (playGame.equalsIgnoreCase("s")) {
    
                    System.out.println("\nChocolate Brownie the Pimp: \n'It's a real shame we couldn't relive our former glory. Farewell friend...'\n");
                    correctInp = true;
                    System.exit(0);
    
                } else {
    
                    System.out.println("\nInvalid input. Try again!\n");
    
                }
            
            } while (correctInp == false);

        //if boolean skip = true, prints message stating it has been skipped
        } else {

            System.out.println("\nSuccesfully skipped the starting lore and text. Skipping to companion select!\n");

        }

    }

    //method for story in the middle. Takes your chosen companion name and uses it in the message
    public static void loreMiddle(String companion) {

        System.out.println("\nChocolate Brownie the Pimp: \n'Your trust seems to have gone to " + companion + ". Good enough.'");

        pause(3);

        System.out.println("'You will arrive at dawn tomorrow, I hope you and your companion are ready, the kitchen relies on it.'");

        pause(5);

        System.out.println("\nDAWN THE NEXT DAY");

        pause(3);

        System.out.println("\nChocolate Brownie the Pimp: \n'You're going in chief. Remember, you will get everything you need inside. We'll stay in touch via comms. Good Luck!'\n");

        pause(5);

    }

    //fight method
    public static void fight(Entity companion, Entity boss) {

        boolean correctInp = false;
        boolean restart = true;

        pause(2);

        System.out.println("*You will attack first!\n");
        
        //do loop runs as long as boolean restart = true
        do {

            //resets companion and boss hp 
            companion.resetHP();
            boss.resetHP();

            //while loop runs as long as companion and boss are alive. If either dies, loop ends
            while (companion.alive() && boss.alive()) {

                //if companion is alive, you can pick a move
                if (companion.alive()) {

                    pause(1);

                    System.out.println("Pick your move: (1) Attack (2) Heal\n");

                    //do loop runs as log as correctInp is false, correct input turns the boolean to true, ending the loop
                    do {

                        System.out.print("> ");
                        String moveChoiceStr = read.nextLine();
                        int moveChoice = Integer.parseInt(moveChoiceStr);

                        //if you pick 'attack', companion attacks the boss and print the bosses healthbar
                        if (moveChoice == 1) {

                            pause(1);

                            System.out.println("\n====================================================================================================");

                            companion.attack(boss);
                            System.out.println(boss.healthBar() + "\n");
                            correctInp = true;

                        //else if you pick heal, companion heals and prints companions healthbar
                        } else if (moveChoice == 2) {

                            pause(1);
                        
                            System.out.println("\n====================================================================================================");

                            companion.heal();
                            System.out.println(companion.healthBar() + "\n");
                            correctInp = true;

                        } else {

                            System.out.println("\nInvalid input. Try again!\n");

                        }

                    } while (correctInp == false);

                }

                //if the boss is still alive, boss attacks the companion and prints companion's healthbar
                if (boss.alive()) {

                    pause(1);

                    System.out.println("====================================================================================================");

                    boss.attack(companion);
                    System.out.println(companion.healthBar() + "\n");

                    System.out.println("====================================================================================================\n");

                }

            }

            correctInp = false;

            //runs after one of them dies
            //if companion died, prints failure message and asks if you want to restart or surrender
            if (!companion.alive()) {

                System.out.println("'Mission failed, you'll get 'em next time. Would you like to (r)estart or (s)urrender?\n");

                //do loop runs as log as correctInp is false, correct input turns the boolean to true, ending the loop
                do {

                    System.out.print("> ");
                    String skipInput = read.nextLine();
    
                    if (skipInput.equalsIgnoreCase("r")) {
    
                        System.out.println("\nRestarting...\n");

                        pause(1);

                        System.out.println("Let the fight commence!\n");
                        correctInp = true;

                        pause(1);
    
                    //if user chooses to surrender, exits program
                    } else if (skipInput.equalsIgnoreCase("s")) {
    
                        System.out.println("\nChocolate Brownie the Pimp: \n'I heard you surrendered to those clowns. Quite shameful. I'm disappointed. Brownie the Pimp, over and out...'\n");
    
                        System.exit(0);
    
                    } else {
    
                        System.out.println("\nInvalid input. Try again!\n");
    
                    }
            
                } while (correctInp == false);

            //if boss is not alive, print victory message and set restart to false, ending the while loop
            } else if (!boss.alive()) {

                System.out.println("====================================================================================================\n");

                System.out.println(companion.getName() + " has defeated " + boss.getName() + ".\n");

                restart = false;

                pause(3);

            }

        } while (restart == true);

    }

    //method for the interactions in the gauntlet
    public static void inTheGauntlet() {

        boolean correctInp = false;

        System.out.println("*You have entered the gauntlet. First, you will enter the shop and receive your first item\n");

        pause(5);

        //first shop interaction before fight 1
        shop(1);

        pause(5);

        System.out.println("*You enter the first room of the gauntlet and see your first enemy\n");

        pause(3);

        System.out.println(bossI.healthBar() + "\n");

        System.out.println("(F)ight or (S)urrender?\n");
        
        //do loop runs as log as correctInp is false, correct input turns the boolean to true, ending the loop
        do {

            System.out.print("> ");
            String fOrSInput1 = read.nextLine();

            if (fOrSInput1.equalsIgnoreCase("f")) {

                System.out.println("\nLet the fight commence!\n");
                correctInp = true;

            
            } else if (fOrSInput1.equalsIgnoreCase("s")) {

                System.out.println("\nChocolate Brownie the Pimp: \n'I heard you surrendered to those clowns. Quite shameful. I'm disappointed. Brownie the Pimp, over and out...'\n");

                System.exit(0);

            } else {

                System.out.println("\nInvalid input. Try again!\n");

            }
        
        } while (correctInp == false);

        correctInp = false;

        //first fight commences
        fight(companion, bossI);

        System.out.println("*You now go back to the shopkeeper to get your second item\n");

        pause(5);

        //second shop interaction after first fight
        shop(2);

        pause(5);

        System.out.println("*You approach the second room and confront Slippery Sinkadrink\n");
        
        pause(3);
        
        System.out.println(bossS.healthBar() + "\n");

        System.out.println("(F)ight or (S)urrender?\n");

        //do loop runs as log as correctInp is false, correct input turns the boolean to true, ending the loop
        do {

            System.out.print("> ");
            String fOrSInput2 = read.nextLine();

            if (fOrSInput2.equalsIgnoreCase("f")) {

                System.out.println("\nLet the fight commence!\n");
                correctInp = true;

            } else if (fOrSInput2.equalsIgnoreCase("s")) {

                System.out.println("\nChocolate Brownie the Pimp: \n'I heard you surrendered to those clowns. Quite shameful. I'm disappointed. Brownie the Pimp, over and out...'\n");

                System.exit(0);

            } else {

                System.out.println("\nInvalid input. Try again!\n");

            }
        
        } while (correctInp == false);

        correctInp = false;

        //second fight commences
        fight(companion, bossS);

        System.out.println("*You go back to the shopkeeper to get your final item\n");

        pause(5);

        //third and last shop interaction
        shop(3);

        pause(5);

        System.out.println("*You approach the final room, its gets cooler as you proceed...\n");
        
        pause(3);
        
        System.out.println(bossF.healthBar() + "\n");

        System.out.println("(F)ight or (S)urrender?\n");

        //do loop runs as log as correctInp is false, correct input turns the boolean to true, ending the loop
        do {

            System.out.print("> ");
            String fOrSInput3 = read.nextLine();

            if (fOrSInput3.equalsIgnoreCase("f")) {

                System.out.println("\nLet the fight commence!\n");
                correctInp = true;

            } else if (fOrSInput3.equalsIgnoreCase("s")) {

                System.out.println("\nChocolate Brownie the Pimp: \n'I heard you surrendered to those clowns. Quite shameful. I'm disappointed. Brownie the Pimp, over and out...'\n");

                System.exit(0);

            } else {

                System.out.println("\nInvalid input. Try again!\n");

            }
        
        } while (correctInp == false);

        //third and last fight commences
        fight(companion, bossF);

        System.out.println("Chocolate Brownie the Pimp: \n'I heard what you did out there. You are unbelievable. You have saved the entire kitchen\n from their wraith You will forever be known as the kitchen guardian!'\n");

    }

    public static void main(String[] args) throws Exception {

        String companionName = null;

        boolean correctInp = false;
        boolean skip = false;

        //title text
        System.out.println("░█████╗░██████╗░██████╗░██╗░░░░░██╗░█████╗░███╗░░██╗░█████╗░███████╗");
        Thread.sleep(100);
        System.out.println("██╔══██╗██╔══██╗██╔══██╗██║░░░░░██║██╔══██╗████╗░██║██╔══██╗██╔════╝");
        Thread.sleep(100);
        System.out.println("███████║██████╔╝██████╔╝██║░░░░░██║███████║██╔██╗██║██║░░╚═╝█████╗░░");
        Thread.sleep(100);
        System.out.println("██╔══██║██╔═══╝░██╔═══╝░██║░░░░░██║██╔══██║██║╚████║██║░░██╗██╔══╝░░");
        Thread.sleep(100);
        System.out.println("██║░░██║██║░░░░░██║░░░░░███████╗██║██║░░██║██║░╚███║╚█████╔╝███████╗");
        Thread.sleep(100);
        System.out.println("╚═╝░░╚═╝╚═╝░░░░░╚═╝░░░░░╚══════╝╚═╝╚═╝░░╚═╝╚═╝░░╚══╝░╚════╝░╚══════╝");
        Thread.sleep(100);
        System.out.println("░█████╗░░██████╗░██████╗░█████╗░██╗░░░██╗██╗░░░░░████████╗  ██████╗░");
        Thread.sleep(100);
        System.out.println("██╔══██╗██╔════╝██╔════╝██╔══██╗██║░░░██║██║░░░░░╚══██╔══╝  ╚════██╗");
        Thread.sleep(100);
        System.out.println("███████║╚█████╗░╚█████╗░███████║██║░░░██║██║░░░░░░░░██║░░░  ░░███╔═╝");
        Thread.sleep(100);
        System.out.println("██╔══██║░╚═══██╗░╚═══██╗██╔══██║██║░░░██║██║░░░░░░░░██║░░░  ██╔══╝░░");
        Thread.sleep(100);
        System.out.println("██║░░██║██████╔╝██████╔╝██║░░██║╚██████╔╝███████╗░░░██║░░░  ███████╗");
        Thread.sleep(100);
        System.out.println("╚═╝░░╚═╝╚═════╝░╚═════╝░╚═╝░░╚═╝░╚═════╝░╚══════╝░░░╚═╝░░░  ╚══════╝");
        Thread.sleep(500);

        System.out.println("Would you like to skip the lore and jump right into the action(skipping is not recommened for first-time players)?\n(Y)es or (N)o\n");

        //do loop runs as log as correctInp is false, correct input turns the boolean to true, ending the loop
        do {

            System.out.print("> ");
            String skipInput = read.nextLine();

            //if yes, skip is set to true
            if (skipInput.equalsIgnoreCase("y")) {

                skip = true;
                correctInp = true;

            //else if no, skip is set to false
            } else if (skipInput.equalsIgnoreCase("n")) {

                skip = false;
                correctInp = true;

            } else {

                System.out.println("\nInvalid input. Try again!\n");

            }
        
        } while (correctInp == false);

        correctInp = false;

        //loresStart method runs based on boolean skip
        loreStart(skip);

        System.out.println("----------------\nCompanion Select\n----------------\n(V)ertiglass      | The cool guy. Frozen to -19°C instead of -18°C, left with eternal disdain...\n(H)ot Cross Burnt | The hot guy. Poor bun burnt by his master, wishes flames upon his abuser!\n(W)aterwave       | The wet guy. Spilled across the barren kitchen tiles, regrouped only to regain pride.\n");

        //do loop runs as log as correctInp is false, correct input turns the boolean to true, ending the loop
        do {

            System.out.print("> ");
            String selectInput = read.nextLine();

            if (selectInput.equalsIgnoreCase("v")) {

                companionName = "Vertiglass";
                companion.setName(companionName);
                correctInp = true;


            } else if (selectInput.equalsIgnoreCase("h")) {

                companionName = "Hot Cross Burnt";
                companion.setName(companionName);
                correctInp = true;

            } else if (selectInput.equalsIgnoreCase("w")) {

                companionName = "Waterwave";
                companion.setName(companionName);
                correctInp = true;

            } else {

                System.out.println("\nInvalid input. Try again!\n");

            }
        
        } while (correctInp == false);
        
        correctInp = false;

        //runs loreMiddle based on companion name
        loreMiddle(companionName);

        //runs inTheGauntlet method which is the fight and shop sequence
        inTheGauntlet();

        //closes Scanner read
        read.close();

    }

}