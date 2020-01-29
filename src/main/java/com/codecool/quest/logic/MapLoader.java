package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.Ghost;
import com.codecool.quest.logic.actors.Player;
import com.codecool.quest.logic.actors.PossessedSecurity;
import com.codecool.quest.logic.actors.Skeleton;
import com.codecool.quest.logic.inventory.*;


import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(String mapFile) {
        InputStream is = MapLoader.class.getResourceAsStream(mapFile);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case 'B':
                            cell.setType(CellType.CLOSEDDOOR_BLUE);
                            break;
                        case 'b':
                            cell.setType(CellType.OPENEDDOOR_BLUE);
                            break;
                        case 'Y':
                            cell.setType(CellType.CLOSEDDOOR_YELLOW);
                            break;
                        case 'y':
                            cell.setType(CellType.OPENEDDOOR_YELLOW);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            map.addMonster(new Skeleton(cell) );
                            break;
                        case 'g':
                            cell.setType(CellType.FLOOR);
                            map.addMonster(new Ghost(cell) );
                            break;
                        case 'p':
                            cell.setType(CellType.FLOOR);
                            map.addMonster(new PossessedSecurity(cell));
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case '&':
                            cell.setType(CellType.FLOOR);
                            new Shield(cell);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Keys(cell, "Blue");
                            break;
                        case 'K':
                            cell.setType(CellType.FLOOR);
                            new Keys(cell, "Yellow");
                            break;
                        case 'h':
                            cell.setType(CellType.FLOOR);
                            new FirstAid(cell);
                            break;
                        case 'w':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell);
                            break;
                        case 'a':
                            cell.setType(CellType.FLOOR);
                            new Axe(cell);
                            break;
                        case 'f':
                            cell.setType(CellType.STAIRSDOWN);
                            break;
                        case 'l':
                            cell.setType(CellType.LADDER);
                            break;
                        case 't':
                            cell.setType(CellType.TREE);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
