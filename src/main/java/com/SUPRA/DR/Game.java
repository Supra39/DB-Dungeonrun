package com.SUPRA.DR;

import java.util.Scanner;

public class Game {
    Player player = new Player(10,2,2,2,2,0,1,3);// starting stats for the Player
    Monster monster = new Monster("Test",0,0,0);

    Scanner scanner = new Scanner(System.in);

    public void mainMenu() {
        boolean isSelectingPlayer = true;
        String name;
        System.out.println("Welcome to the Dungeon v.4.0!\n");
        do {
            System.out.println("Select 1 to create a new player\nSelect 2 to continue adventure\nSelect 3 to view saved players\nSelect 4 to delete a saved player");
            switch (scanner.next()) {
                case "1" -> {
                    System.out.println("Enter player name...");
                    player.createNewPlayer();
                    System.out.println(player.getName() + " has been created");
                    gameMenu();
                }
                case "2" -> {
                    player.readPlayer(); //shows saved players first
                    System.out.println("Enter saved player name...");
                    scanner.nextLine(); // consumes the previous scanner input, so the same scanner can be reused
                    name = scanner.nextLine(); // read the players name
                    player.selectPlayer(name);
                    System.out.println("\n" + player.getName() + " has been selected\n");
                    gameMenu();
                }
                case "3" ->{player.readPlayer();}
                case "4" ->{
                    player.readPlayer(); //views all saved players
                    System.out.println("input the player name that should be deleted...");
                    scanner.nextLine(); // consumes the previous scanner input, so the same scanner can be reused
                    name = scanner.nextLine(); // read the players name
                    player.deletePlayer(name); //tells deletePlayer what entry to drop
                }
                default -> {
                    System.out.println("Please input one of the following:\n");
                }
            }
        } while (isSelectingPlayer);
    }

        public void gameMenu() { //from this menu the player can check status, fight monsters, view a tutorial och go back to mainMenu
            boolean isPlaying = true;
            do {
                System.out.println("+++ Welcome " + player.getName() + " +++");
                System.out.println("Fight monster = 1\nPlayer Status = 2\nTutorial = 3\nGo back to main menu = 0");
                switch (scanner.next()) {
                    case "1" -> {
                        combatMenu();}
                    case "2" -> {System.out.println(player.toString());}
                    case "4" -> System.out.println("""
                            HP: Health points, if this number hits 0, you will die.
                            Max HP: Maximum amount of health points
                            STR: Strength, the higher the number, the more damage you will deal
                            Luck: This stat increases the odds of doing a critical strike, which deals dubble damage
                            AGI: Agility, increases your chance of dodging the enemy attack
                            EXP: Experience points, when you have 100 XP you will level up
                            LVL: Level, when you level up your stats will go up, making you stronger
                            BASE DMG: Your damage before the Strength modifier is added
                            HOW TO WIN: Defeat monsters to become stronger, when you are strong enough the final boss will challenge you...""");
                    case "0" -> isPlaying = false;
                    default -> System.out.println("Input 1, 2, 3, 4 or 0");
                }
            } while (isPlaying);
        }               //in between combat and main menu
                        public void combatMenu() {
                            boolean isFighting = true;
                            do {
                                System.out.println("--- GET READY FOR THE NEXT BATTLE! ---");
                                System.out.println("Ready To Fight! = 1\nGo Back = 2");
                                switch (scanner.next()) {
                                    case "1" -> {
                                        player.playerCombatAct();
                                        player.updatePlayerData();
                                    }
                                    case "2" -> isFighting = false;
                                    default -> System.out.println("Input 1 or 2");
                                }
                            } while (isFighting);
                        }


        }


