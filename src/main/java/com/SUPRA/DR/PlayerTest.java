package com.SUPRA.DR;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    Player testPlayer = new Player(1,1,1,1,1,0,1,1);

    @Test
    @DisplayName("Checking if player can lvl up by adding 100 xp")
    public void experience100(){
        testPlayer.setLvl(1); //make sure the current lvl is 1 before testing
        testPlayer.setExp(100); //sets xp to 100, which should increase the player lvl by 1
        assertEquals(2, testPlayer.getLvl()); //checks that the player lvl increases to 2
    }

    @Test
    @DisplayName("Testing that player will not lvl up with 99 xp")
    public void experience99(){
    testPlayer.setLvl(1);//make sure the current lvl is 1 before testing
    testPlayer.setExp(99);//Sets playerXP to 99 which is not enough to increase lvl
    assertEquals(1, testPlayer.getLvl()); //expects player lvl to be 1 = unchanged
        System.out.println("Current xp: " + testPlayer.getExp());//visual aid
    }

    @Test
    @DisplayName("Testing that the player will not lose the extra xp when lvl up")
    public void experience101(){
        testPlayer.setLvl(1);//make sure the current lvl is 1 before testing
        testPlayer.setExp(101);//sets cp to 101 which is 1 more than needed to lvl up
        assertEquals(2, testPlayer.getLvl()); //check that the player lvl increased by 1
        System.out.println("exp after lvl up: " + testPlayer.getExp());//visual aid
        assertEquals(1, testPlayer.getExp());//checks that the extra point of xp remains after lvl up
    }

    @Test
    @DisplayName("Check if player lose when player hp = 0")
    public void checkCanPlayerLose(){
        testPlayer.playerLose();
        testPlayer.setHp(0); assertTrue(testPlayer.playerLose());
    }

    @Test
    @DisplayName("Check if player lose when player hp = 1")
    public void checkCanPlayerLose2() {
        testPlayer.playerLose();
        testPlayer.setHp(1); assertFalse(testPlayer.playerLose());
    }

   //Test nr1 to check if player can deal as much dmg as they are supposed to
    @DisplayName("Check if player deals as much dmg as they CAN when they land a critical strike")
    @RepeatedTest(100)
    public void checkPlayerCritDmg(){
        //setting player luck to 100 means the player should deal dubble damage every time
        testPlayer.setLuck(100);
        //players damage in this class should be 1+1 = 2, and if player crits damage should be = 4
        System.out.println("Player str: " +testPlayer.getStr() + ", Player base dmg : " + testPlayer.getBaseDmg());
        assertEquals(4, testPlayer.fight());
    }
    //Test nr2 to check if player can deal as much dmg as they are supposed to
    @DisplayName("Check if player deals as much dmg as they CAN when doing regular damage")
    @RepeatedTest(100)
    public void checkPlayerRegularDmg(){
        //setting player luck to -200, means the player can never land a critical strike and should never do dubble damage
        testPlayer.setLuck(-200);
        //players damage in this class should be 1+1 = 2, and if player crits damage should be dubble (4)
        System.out.println("Player str: " +testPlayer.getStr() + ", Player base dmg : " + testPlayer.getBaseDmg());
        assertEquals(2, testPlayer.fight());
    }

}
