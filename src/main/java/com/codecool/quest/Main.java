package com.codecool.quest;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.MapLoader;
import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.actors.Player;
import com.codecool.quest.logic.inventory.FirstAid;
import com.codecool.quest.logic.inventory.Item;
import com.codecool.quest.logic.inventory.Keys;
import com.codecool.quest.logic.inventory.Weapons;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
    private int distanceFromLeftBorder;
    private int distanceFromTopBorder;
    private int xAxisMiddle;
    private int yAxisMiddle;
    private boolean isMonstersMoved = false;

    private final int screenWidth = 25;
    private final int screenHeight = 20;
    List<String> mapList = new ArrayList<>() {{
        add("/map.txt");
        add("/map2.txt");
        add("/map3.txt");
    }};
    GameMap map = MapLoader.loadMap(mapList.remove(0));
    Canvas canvas = new Canvas(
            screenWidth * Tiles.TILE_WIDTH,
            screenHeight * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label shield = new Label();
    Label attackPower = new Label();
    Button buttonBar = new Button("Pick Up");
    //
    public static ObservableList<String> items = FXCollections.observableArrayList();
    ListView<String> listView = new ListView<String>(items);


    public static int tempShield = 0;
    public static int tempHealth = 0;
    public static int tempAttackPower = 0;

    Task<Void> moveMonsters = new Task<>() {
        @Override
        protected Void call() throws Exception {
            do {
                isMonstersMoved = false;
                Thread.sleep(500);
                if (map.moveMonsters()) {
                    isMonstersMoved = true;
                    Thread.sleep(5);
                }
            } while (!isCancelled());
            return null;
        }
    };

    Task<Void> refresh = new Task<>() {
        @Override
        protected Void call() throws Exception {
            while (true) {
                Thread.sleep(4);
                if (isMonstersMoved) {
                    refresh();
                }
                if (isCancelled()) {
                    return null;
                }
            }
        }
    };


    public static void main(String[] args) {
        launch(args);
    }

    public static void checkInventory(Item item) {
        if (item instanceof Weapons) {
            if (item.getTileName().equals("Axe") && items.contains("Sword")) {
                items.remove("Sword");
                items.add(item.getTileName());
            } else if (item.getTileName().equals("Sword") && items.contains("Axe")) {
                items.remove("Axe");
                items.add(item.getTileName());
            }
            else if ((item.getTileName().equals("Axe") && items.contains("Axe")) ||
                    (item.getTileName().equals("Sword") && items.contains("Sword"))) {
            }else {
                items.add(item.getTileName());
            }
        }
        else if (item instanceof Keys) {
            items.add(item.getTileName());
        }
        else if ((item.getTileName().equals("Shield") && !items.contains("Shield"))) {
            items.add(item.getTileName());
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 0, 1);
        ui.add(new Label("Shield: "), 0, 2);
        ui.add(shield, 0, 3);
        ui.add(new Label("Attack Power: "), 0, 4);
        ui.add(attackPower, 0, 5);
        ui.add(new Label("Inventory: "), 0, 6);
        ui.add(listView, 0, 10);
        ui.add(buttonBar, 0, 15);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();

        scene.addEventFilter(KeyEvent.KEY_PRESSED, this::onKeyPressed);
        buttonBar.addEventHandler(MouseEvent.MOUSE_CLICKED, this::mouseEvent);

        primaryStage.setTitle("Codecool Quest");
        primaryStage.show();


        Thread threadMonstersMove = new Thread(moveMonsters);
        Thread threadRefreshMap = new Thread(refresh);

        threadMonstersMove.setDaemon(true);
        threadRefreshMap.setDaemon(true);

        threadMonstersMove.start();
        threadRefreshMap.start();
    }

    private void mouseEvent(MouseEvent mouseEvent) {

        map.getPlayer().pickUpItem();
        refresh();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        Cell cell = new Cell(map, map.getPlayer().getX(), map.getPlayer().getY());
        int moveX = 0;
        int moveY = 0;
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(moveX, --moveY);
                break;
            case DOWN:
                map.getPlayer().move(moveX, ++moveY);
                break;
            case LEFT:
                map.getPlayer().move(--moveX, moveY);
                break;
            case RIGHT:
                map.getPlayer().move(++moveX, moveY);
                break;
            case F:
                map.playerFinishesOff();
                break;
            case SPACE:
                map.playerFight();
                break;
        }
        if (cell.getNeighbor(moveX, moveY).getType().equals(CellType.STAIRSDOWN)) {
            map = MapLoader.loadMap(mapList.remove(0));
        }
        refresh();
        keyEvent.consume();
    }

    private void refresh() {
        setDefaultPositionVariables();

        if (mapIsBiggerThanScreen()) {
            setNewPositionVariables();
        }
        Platform.runLater(this::printNewBoard);
    }

    private void setDefaultPositionVariables() {
        distanceFromLeftBorder = 0;
        distanceFromTopBorder = 0;
        xAxisMiddle = 0;
        yAxisMiddle = 0;
    }

    private void setNewPositionVariables() {
        distanceFromLeftBorder = map.getPlayer().getX();
        distanceFromTopBorder = map.getPlayer().getY();

        xAxisMiddle = screenWidth / 2;
        yAxisMiddle = screenHeight / 2;

        if (playerIsNearLeftBorder()) {
            xAxisMiddle = distanceFromLeftBorder;
        } else if (playerIsNearRightBorder()) {
            xAxisMiddle += 1 + xAxisMiddle - (distanceFromLeftBorder - map.getWidth()) * -1;
        }
        if (playerIsNearTopBorder()) {
            yAxisMiddle = distanceFromTopBorder;
        } else if (playerIsNearBottomBorder()) {
            distanceFromTopBorder = map.getHeight() - yAxisMiddle;
        }
    }

    private boolean playerIsNearBottomBorder() {
        return map.getHeight() - map.getPlayer().getY() <= yAxisMiddle;
    }

    private boolean playerIsNearTopBorder() {
        return map.getPlayer().getY() < yAxisMiddle;
    }

    private boolean playerIsNearRightBorder() {
        return (map.getPlayer().getX() - map.getWidth()) * -1 <= xAxisMiddle;
    }

    private boolean playerIsNearLeftBorder()
    {
        return map.getPlayer().getX() < xAxisMiddle;
    }

    private void printNewBoard() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        map.countTimer();

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);

                int includedCellX = x + xAxisMiddle - distanceFromLeftBorder;
                int includedCellY = y + yAxisMiddle - distanceFromTopBorder;

                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), includedCellX, includedCellY);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), includedCellX, includedCellY);
                } else if(cell.getNote() != null){
                    Tiles.drawTile(context, cell.getNote(), includedCellX, includedCellY);
                } else {
                    Tiles.drawTile(context, cell, includedCellX, includedCellY);
                }
                if (isMonstersMoved) {
                    // animate tiles once per monster turn
                    changeTeleportEntryState(cell);
                    changeTeleportExitState(cell);
                }
            }
            healthLabel.setText("" + map.getPlayer().getHealth());
            shield.setText("" + map.getPlayer().getTempShield());
            attackPower.setText("" + map.getPlayer().getAttackPower());
        }
    }

    private void changeTeleportExitState(Cell cell) {
        if (cell.getType() == CellType.TELEPORT_EXIT_FIRST_STATE) {
            cell.setType(CellType.TELEPORT_EXIT_SECOND_STATE);
        } else if (cell.getType() == CellType.TELEPORT_EXIT_SECOND_STATE) {
            cell.setType(CellType.TELEPORT_EXIT_THIRD_STATE);
        } else if (cell.getType() == CellType.TELEPORT_EXIT_THIRD_STATE) {
            cell.setType(CellType.TELEPORT_EXIT_FIRST_STATE);
        }
    }

    private void changeTeleportEntryState(Cell cell) {
        if (cell.getType() == CellType.TELEPORT_FIRST_STATE) {
            cell.setType(CellType.TELEPORT_SECOND_STATE);
        } else if (cell.getType() == CellType.TELEPORT_SECOND_STATE) {
            cell.setType(CellType.TELEPORT_FIRST_STATE);
        }
        healthLabel.setText("" + map.getPlayer().getTempHealth());
        shield.setText("" + map.getPlayer().getTempShield());
        attackPower.setText("" + map.getPlayer().getAttackPower());
    }

    private boolean mapIsBiggerThanScreen() {
        return map.getWidth() > screenWidth || map.getHeight() > screenHeight;
    }
}
