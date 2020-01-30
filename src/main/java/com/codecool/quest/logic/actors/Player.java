package com.codecool.quest.logic.actors;

import com.codecool.quest.Main;
import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Directions;
import com.codecool.quest.logic.inventory.*;


public class Player extends Actor {

    private Directions direction = Directions.INPLACE;
    private int[] coordinates = new int[2];
    private int countMonsters = 0;
    /* Values to set to back on when extras work ends: */
    private int healthToReset;
    private int shieldToReset;
    private int attackPowerToReset;
    private boolean extrasIsReceived = false;

    public Player(Cell cell) {
        super(cell);
        healthToReset = health;
        shieldToReset = shield;
        attackPowerToReset = attackPower;
        stateName = getStateName();
    }

    private String getStateName() {
        if (Main.items.contains("Axe")) {
            return "player with axe";
        } else if (Main.items.contains("Sword")) {
            return "player with sword";
        } else {
            return "player";
        }
    }


    @Override
    public void move(int dx, int dy) {
        if (health < 1)
            return;
        Cell nextCell = cell.getNeighbor(dx, dy);
        Actor actor = nextCell.getActor();
        if ((nextCell.getType().equals(CellType.FLOOR) && actor == null)
                || nextCell.getType().equals(CellType.OPENEDDOOR_BLUE)
                || nextCell.getType().equals(CellType.OPENEDDOOR_YELLOW)
                || isNextCellTeleportExit(nextCell)
                || isNextCellBridge(nextCell)) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (actor != null && !actor.isPlayer()) {
            Enemy monster = (Enemy) actor;
            monster.setFightOn();
        } else if ((nextCell.getType().equals(CellType.CLOSEDDOOR_BLUE)) && Main.items.contains("key Blue")) {
            Main.items.remove("key Blue");
            nextCell.setType(CellType.OPENEDDOOR_BLUE);
        } else if ((nextCell.getType().equals(CellType.CLOSEDDOOR_YELLOW)) && Main.items.contains("key Yellow")) {
            Main.items.remove("key Yellow");
            nextCell.setType(CellType.OPENEDDOOR_YELLOW);
        } else if (isNextCellTeleportEntry(nextCell)) {
            // calculate teleport exit cords and move player character to it
            int targetCellX = cell.getTeleportExitX() - nextCell.getX() + dx;
            int targetCellY = cell.getTeleportExitY() - nextCell.getY() + dy;
            this.move(targetCellX, targetCellY);
        }
        direction.setDirection(dx, dy);
    }

    private Enemy neighbourMonster(int dx, int dy) {
        Cell cellCheck = cell.getNeighbor(dx, dy);
        Enemy monster = null;

        if (cellCheck != null) {
            monster = (Enemy) cellCheck.getActor();
            if (monster != null) {
                coordinates[0] = dx;
                coordinates[1] = dy;
                countMonsters++;
            }
        }

        return monster;
    }

    public Enemy shoot() {
        if (health < 1)
            return null;

        Enemy monster, monsterShooted = null;
        countMonsters = 0;
        coordinates[0] = coordinates[1] = 0;

        monster = neighbourMonster(0, -1);
        if (monster != null) {
            monsterShooted = monster;
        }
        monster = neighbourMonster(1, 0);
        if (monster != null) {
            monsterShooted = monster;
        }
        monster = neighbourMonster(0, 1);
        if (monster != null) {
            monsterShooted = monster;
        }
        monster = neighbourMonster(-1, 0);
        if (monster != null) {
            monsterShooted = monster;
        }

        if (countMonsters < 1) {
            return null;
        } else if (countMonsters == 1) {
            boolean isAlive = monsterShooted.getAttacked(attackPower);
            if (!monsterShooted.canBeStunned() && !isAlive)
                cell.getNeighbor(coordinates[0], coordinates[1]).setActor(null);

            direction.setDirection(coordinates[0], coordinates[1]);

            return monsterShooted;
        }

        Cell cellCheck = cellByDirection();
        if (cellCheck == null)
            return null;

        monster = (Enemy) cellCheck.getActor();
        if (monster == null)
            return null;

        boolean isAlive = monster.getAttacked(attackPower);
        if (!monster.canBeStunned() && !isAlive)
            cellCheck.setActor(null);

        return monster;
    }

    public Actor gloryKill() {
        Cell cellCheck = cellByDirection();
        if (cellCheck == null)
            return null;

        Enemy monster = (Enemy) cellCheck.getActor();
        if (monster != null && monster.canBeStunned() && monster.isStunned()) {
            cellCheck.setActor(null);
            health++;
            return monster;
        }

        return null;
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    private Cell cellByDirection() {
        Cell cellCheck;

        switch (direction.getDirection()) {
            case 1:
                cellCheck = cell.getNeighbor(0, -1);
                break;
            case 2:
                cellCheck = cell.getNeighbor(1, 0);
                break;
            case 3:
                cellCheck = cell.getNeighbor(0, 1);
                break;
            case 4:
                cellCheck = cell.getNeighbor(-1, 0);
                break;
            default:
                return null;
        }

        return cellCheck;
    }

    public void pickUpItem() {
        try {
            if (cell.getItem().getTileName() != null) {
                Main.checkInventory(cell.getItem());
                if (cell.getItem() instanceof Weapons) {
                    changeAppearance((Weapons) cell.getItem());
                    changeAttackPower((Weapons) cell.getItem());
                } else if (cell.getItem() instanceof Shield) {
                    this.shield = ((Shield) cell.getItem()).addShield();
                } else if (cell.getItem() instanceof FirstAid) {
                    this.health += ((FirstAid) cell.getItem()).getHealthIncrease();
                } else if(cell.getItem() instanceof Quad){
                    Quad quad = (Quad) cell.getItem();
                    /* keep old values: */
                    healthToReset = quad.getPreviousHealth(health);
                    shieldToReset = quad.getPreviousShield(shield);
                    attackPowerToReset = quad.getPreviousAttackPower(attackPower);
                    /* set current values based on picked extras: */
                    health = quad.getActiveHealth(health);
                    shield = quad.getActiveShield(shield);
                    attackPower = quad.getActiveAttackPower(baseAttackPower);
                    extrasIsReceived = true;
                    cell.useExtras();
                }
                cell.setItem(null);
            }
        } catch (NullPointerException e) {
            System.out.println(e + " caused by pickUpItem method when no item is on current cell");
        }
    }

    public void setBasePlayerStats(){
        if(extrasIsReceived) {
            health = healthToReset;
            shield = shieldToReset;
            attackPower = attackPowerToReset;
            extrasIsReceived = false;
        }
    }

    private boolean isNextCellTeleportExit(Cell nextCell) {
        return nextCell.getType().equals(CellType.TELEPORT_EXIT_FIRST_STATE) ||
                nextCell.getType().equals(CellType.TELEPORT_EXIT_SECOND_STATE) ||
                nextCell.getType().equals(CellType.TELEPORT_EXIT_THIRD_STATE);
    }

    private boolean isNextCellBridge(Cell nextCell) {
        return nextCell.getType().equals(CellType.BRIDGE) ||
                nextCell.getType().equals(CellType.LEFT_BRIDGEHEAD) ||
                nextCell.getType().equals(CellType.RIGHT_BRIDGEHEAD);
    }

    private boolean isNextCellTeleportEntry(Cell nextCell) {
        return nextCell.getType().equals(CellType.TELEPORT_FIRST_STATE) || nextCell.getType().equals(CellType.TELEPORT_SECOND_STATE);
    }

    private void changeAttackPower(Weapons item) {
        if (item.getTileName().equals("Axe")) {
            setAttackPower(baseAttackPower + item.addAttackPower());
        } else if (item.getTileName().equals("Sword"))
            setAttackPower(baseAttackPower + item.addAttackPower());
    }

    private void changeAppearance(Weapons item) {
        if (item instanceof Sword) {
            this.stateName = "player with sword";
        } else if (item instanceof Axe) {
            this.stateName = "player with axe";
        }
    }
}
