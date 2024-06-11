package com.SUPRA.DR;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.Statement;

public class Player implements ICombat {
Monster monster = new Monster("", 0, 0, 0);
Scanner scanner = new Scanner(System.in);

    private int id;
    private String name = "no name";
    private int hp;
    private int maxHp;
    private int str;
    private int luck;
    private int agi;
    private int exp;
    private int lvl;
    private int baseDmg;

    public void testingPlayerUpdate(){
        System.out.println("Player hp before testingPlayerUpdate" + getHp());
        setHp(hp + 10);
        System.out.println("player hp after testingPlayerUpdate" + getHp());

    }
                            //Main combat menu, accesses combat related methods
                            public void playerCombatAct(){
                            boolean combatMenu = true;
                            Scanner scanner = new Scanner(System.in);
                            chooseMonster();//method that chooses opponent based on players lvl
                            do {
                                playerLose(); //checks if player hp gets to 0
                                System.out.println("COMBAT MENU:\n1 = attack!\n2 = Attempt to flee\n3 = Check player status\n4 = Do nothing\n5 = Check monster status");
                                switch (scanner.next()){
                                    case "1" : {playerDidDodge(); fight(); if (playerWin() == true) {combatMenu = false;}
                                    if (playerLose() == true){combatMenu = false;}} break;
                                    case "2" : {if (playerFlee() == true){combatMenu = false;}else{playerDidDodge();} } break;
                                    case "3" : {System.out.println(toString());} break;
                                    case "4" : {System.out.println("As you decide to do nothing, the " + monster.getName()
                                            +" activates a secret trapdoor...");setHp(0); if (playerLose() == true); {combatMenu = false;}} break;
                                    case "5" : {System.out.println(monster.toString());} break;
                                    case  "6" : testingPlayerUpdate();
                                    default : System.out.println("Input a digit between 1-5");
                                }
                            }while (combatMenu);
                        }
                        public void chooseMonster(){
                            //checks lvl to choose appropriate monster for player based on players lvl
                            if (getLvl() <= 2){
                                monster.setMonsterSlime();
                                System.out.println("Your will fight:\n" + monster.toString());
                            }else if(getLvl() <= 6){
                                monster.setMonsterSkeleton();
                                System.out.println("Your will fight:\n" + monster.toString());
                            }else if (getLvl() <= 14){
                                monster.setMonsterOrc();
                                System.out.println("Your will fight:\n" + monster.toString());
                            }else if (getLvl() >= 15){
                                monster.setMonsterFinalBoss();
                                System.out.println("Your will fight:\n" + monster.toString());
                                if (monster.getHp() <= 0){
                                    System.out.println("Congratulation!\nYou defeated the final boss!\nGame Cleared, GGWP!");
                                    System.exit(0);
                                }
                            }
                        }
    //calculates baseDMG + str stat, player also have a chance to crit. if they do: dmg * 2
            @Override
            public int fight() {
                System.out.println("You attack the enemy");
                //converting to local variable so i can set monster hp from player class
                int mHealth = monster.getHp();
                    int dmg = baseDmg + getStr();
                    Random random = new Random();
                    int crit = random.nextInt(1,100);
                        System.out.println("Monster hp before hit: " + mHealth);
                if (crit + getLuck() >= 85){
                    System.out.println("Critical Strike! Damage is doubled!");
                    int critDmg = dmg * 2;
                    System.out.println("You deal: " + critDmg + " Damage");
                    monster.setHp(mHealth - critDmg);
                    System.out.println("Monster hp after hit: " + monster.getHp());
                    return critDmg;
                }else{
                    System.out.println("You deal: " + dmg + " Damage");
                    monster.setHp(mHealth - dmg);
                    System.out.println("Monster hp after hit: " + monster.getHp());
                    return dmg;
                }
            }
                    public void playerDidDodge(){
                        System.out.println("The " + monster.getName() + " goes for a attack!");
                        Random random = new Random();
                        int dodgeChance = random.nextInt(1,100);
                        if (dodgeChance + getAgi() >= 85){
                            System.out.println("You dodged the attack!");
                        }else {
                            playerTakeDmg();
                        }
                    }
                        public void playerTakeDmg(){
                            int mDmg = monster.fight();
                            System.out.println("You take: " + mDmg + " Damage");
                            setHp(getHp() - mDmg);
                        }
                        public boolean playerFlee(){
                            System.out.println("Attempting to flee the encounter...");
                            boolean escape;
                            int escapeScore;
                            Random random = new Random();
                            escapeScore = random.nextInt(1,100 );
                            if (escapeScore + getAgi()>= 51 ){
                                escape = true;
                                System.out.println("60 req to escape, you got: " + getAgi() + " + " + escapeScore);
                                System.out.println("You escaped successfully");
                                return true;
                            }else {escape = false;
                                System.out.println("60 req to escape, you got: " + getAgi() + " + " + escapeScore);
                                System.out.println("You failed to escape");
                                return false;
                            }
                        }
                            public boolean playerWin(){
                                if (monster.getHp() <= 0) {
                                    System.out.println("You have defeated " + monster.getName() + "!\nXP rewarded: " + monster.getXpReward());
                                    setExp(getExp() + monster.getXpReward());
                                    monster.monsterLose();
                                    return true;
                                }else{
                                    return false;
                                }
                            }
                            public boolean playerLose(){
                                if (getHp() <= 0){
                                    System.out.println("YOU DIED");
                                    tryAgain();
                                    return true;
                                }else return false;
                            }
                            //When you lose the stats get reset to their original numbers
                            public void tryAgain(){
                                System.out.println("Your stats have been reset");
                                setHp(10); setMaxHp(10); setStr(2); setLuck(2); setAgi(2); setExp(0); setLvl(1); setBaseDmg(3);
                            }

   //checks if EXP is over 100, removes 100 from EXP and increases lvl by 1, increases max hp, hp, and other stats with increaseStats function
    public void checkIfLvlUp(){
        if (getExp() >= 100){
            for (int i = 99; i <getExp() ; i++) {
                setLvl(getLvl() + 1);
                System.out.println("LEVEL UP");
                System.out.println("Current lvl: " + getLvl());
                setExp(getExp() - 100);
                increaseStats();
            }
        }
    }
    //increases following stats when called upon
        public void increaseStats(){
            setMaxHp(getMaxHp() + 10);
            setHp(getHp() + 10);
            setAgi(getAgi() + 2);
            setBaseDmg(getBaseDmg() + 2);
            setStr(getStr() + 2);
            setLuck(getLuck() + 2);
            setHp(getMaxHp());
        }
        //make sure player cant get overhealed from lvling and not taking dmg
            public void isHpAboveMax(){
                if (getHp() > getMaxHp()){
                    setHp(getMaxHp());
                }
            }

            public void createNewPlayer() {
            String newName = scanner.nextLine();
            setName(newName);
            createPlayer();

            }

    // JDBC create
    public void createPlayer() {

        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/supraOne", "root", "1337");
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Player (name, hp, maxHp, str, luck, agi, exp, lvl, baseDmg) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, hp);
            preparedStatement.setInt(3, maxHp);
            preparedStatement.setInt(4, str);
            preparedStatement.setInt(5, luck);
            preparedStatement.setInt(6, agi);
            preparedStatement.setInt(7, exp);
            preparedStatement.setInt(8, lvl);
            preparedStatement.setInt(9, baseDmg);


            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new player was created successfully.");

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        this.id = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Failed to retrieve generated ID.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


/*JDBC read*/
//this metod does SELECT * FROM "Player" table and allows user to read all entrys in "player" table
    public void readPlayer() {
        String selectQuery = "SELECT * FROM Player";

        System.out.println("Loading saved players...");

        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/supraOne", "root", "1337");
             PreparedStatement statement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println("\nSaved Players:\n");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int hp = resultSet.getInt("hp");
                int maxHp = resultSet.getInt("maxHp");
                int str = resultSet.getInt("str");
                int luck = resultSet.getInt("luck");
                int agi = resultSet.getInt("agi");
                int exp = resultSet.getInt("exp");
                int lvl = resultSet.getInt("lvl");
                int baseDmg = resultSet.getInt("baseDmg");


                System.out.println("Name: " + name);
                System.out.println("HP: " + hp);
                System.out.println("Max HP: " + maxHp);
                System.out.println("Strength: " + str);
                System.out.println("Luck: " + luck);
                System.out.println("Agility: " + agi);
                System.out.println("Experience: " + exp);
                System.out.println("Level: " + lvl);
                System.out.println("Base Damage: " + baseDmg);
                System.out.println();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//JDBC update
//When this metod is called the player with the matching "name" will have the current stats updated in the DB
    public void updatePlayerData() {

        // SQL query for update
        String sql = "UPDATE Player SET name=?, hp=?, maxHp=?, str=?, luck=?, agi=?, exp=?, lvl=?, baseDmg=? WHERE id=?";

        try (
                //JDBC connection
                Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/supraOne", "root", "1337" );

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            // Set parameter values
            preparedStatement.setString(1, this.name);
            preparedStatement.setInt(2, this.hp);
            preparedStatement.setInt(3, this.maxHp);
            preparedStatement.setInt(4, this.str);
            preparedStatement.setInt(5, this.luck);
            preparedStatement.setInt(6, this.agi);
            preparedStatement.setInt(7, this.exp);
            preparedStatement.setInt(8, this.lvl);
            preparedStatement.setInt(9, this.baseDmg);
            preparedStatement.setInt(10, this.id);

            // Execute the UPDATE statement
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("\nPlayer data updated successfully.\n");
            } else {
                System.out.println("\nNo player found with name: " + this.name + "\n");
            }
        } catch (SQLException e) {
            System.out.println("\nError updating player data: " + e.getMessage() + "\n");
        }
    }

    //JDBC delete
    public void deletePlayer(String playerName) {
        //query
        String deleteQuery = "DELETE FROM Player WHERE name = ?";
        //connection
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/supraOne", "root", "1337");
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {

            statement.setString(1, playerName);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\nPlayer '" + playerName + "' has been deleted from the database.\n");
            } else {
                System.out.println("\nPlayer '" + playerName + "' not found in the database.\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*JDBC select*/
    // Method to select a saved "player" from the database by "name"
    public void selectPlayer(String playerName) {
        String selectQuery = "SELECT * FROM Player WHERE name = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/supraOne", "root", "1337");
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, playerName);

            System.out.println("Executing SQL query: " + statement.toString()); // Logging SQL query

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                this.setId(resultSet.getInt("id"));
                this.setName(resultSet.getString("name"));
                this.setHp(resultSet.getInt("hp"));
                this.setMaxHp(resultSet.getInt("maxHp"));
                this.setStr(resultSet.getInt("str"));
                this.setLuck(resultSet.getInt("luck"));
                this.setAgi(resultSet.getInt("agi"));
                this.setExp(resultSet.getInt("exp"));
                this.setLvl(resultSet.getInt("lvl"));
                this.setBaseDmg(resultSet.getInt("baseDmg"));

                // Logging retrieved player data
                System.out.println("Player data retrieved:");
                System.out.println("ID: " + this.getId());
                System.out.println("Name: " + this.getName());
                System.out.println("HP: " + this.getHp());
                System.out.println("Max HP: " + this.getMaxHp());
                System.out.println("Strength: " + this.getStr());
                System.out.println("Luck: " + this.getLuck());
                System.out.println("Agility: " + this.getAgi());
                System.out.println("Experience: " + this.getExp());
                System.out.println("Level: " + this.getLvl());
                System.out.println("Base Damage: " + this.getBaseDmg());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//constructor

    public Player(int hp, int maxHp, int str, int luck, int agi, int exp, int lvl, int baseDmg) {
        this.hp = hp;
        this.maxHp = maxHp;
        this.str = str;
        this.luck = luck;
        this.agi = agi;
        this.exp = exp;
        this.lvl = lvl;
        this.baseDmg = baseDmg;
    }


//getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public int getAgi() {
        return agi;
    }

    public void setAgi(int agi) {
        this.agi = agi;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
        checkIfLvlUp();
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getBaseDmg() {
        return baseDmg;
    }

    public void setBaseDmg(int baseDmg) {
        this.baseDmg = baseDmg;
    }

    @Override
    public String toString() {
        return "Player status:\n" +
               "Name: " +  name +
                "\nCurrent HP: " + hp +
                "\nMax HP: " + maxHp +
                "\nSTR: " + str +
                "\nLuck: " + luck +
                "\nAGI: " + agi +
                "\nEXP: " + exp +
                "\nLVL: " + lvl +
                "\nBASE DMG: " + baseDmg;
    }
}
