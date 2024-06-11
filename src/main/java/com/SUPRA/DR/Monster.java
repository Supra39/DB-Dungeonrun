package com.SUPRA.DR;

public class Monster implements ICombat {
    private String name;
    private int hp;
    private int dmg;
    private int xpReward;
    Player player;
    Game game;
    @Override
    public int fight() {
        System.out.println("It hits you!");
        return getDmg();
    }
    //removes stats from monster object, it is used when player wins (when monster hp == 0)
    public void monsterLose(){
        setHp(0);
        setName("");
        setDmg(0);
        setXpReward(0);
    }
    //sets monster as a slime with the stats below
    public void setMonsterSlime(){
        setName("Slime");
        setHp(10);
        setDmg(4);
        setXpReward(100);
    }
    //sets monster as a skeleton with the stats below
    public void setMonsterSkeleton(){
        setName("Skeleton");
        setHp(45);
        setDmg(7);
        setXpReward(200);
    }
    //sets monster as a orc with the stats below
    public void setMonsterOrc(){
        setName("Orc");
        setHp(100);
        setDmg(10);
        setXpReward(300);
    }
    //sets monster as the final boss with the stats below
    public void setMonsterFinalBoss(){
        setName("Big Bad Evil Guy");
        setHp(1000);
        setDmg(30);
        setXpReward(1000);
    }
//constructor
    public Monster(String name, int hp, int dmg, int xpReward) {
        this.name = name;
        this.hp = hp;
        this.dmg = dmg;
        this.xpReward = xpReward;
    }
    //to string
    @Override
    public String toString() {
        return
                name +
                " HP: " + hp +
                ", DMG: " + dmg +
                ", XP Rewarded upon defeat: " + xpReward;
    }
    //getters and setters
    public int getXpReward() {
        return xpReward;
    }

    public void setXpReward(int xpReward) {
        this.xpReward = xpReward;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }
}
