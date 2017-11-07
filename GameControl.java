package ConnectFour;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
this class is designed to organize the ui part
and add events of the game based on ui
 */
public class GameControl extends Application {
    private GameUI ui = new GameUI();
    private boolean lock = false;

    public void start(Stage stage){
        ui.init();
        ui.load();
        ui.changeLight();

        addInfoEvents();
        addQuitEvent();
        addRestartEvent();

        Scene scene = new Scene(ui.getFrame(),650,450);
        stage.setScene(scene);
        stage.show();
    }

    public void addInfoEvents(){
        for(int i = 0;i<7;++i){
            ui.getButton(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    int col = GridPane.getColumnIndex((Button)event.getSource());
                    if(!ui.isFull(col) && !lock){
                        int j =ui.addChess(col);
                        if(ui.isWin(col,j)){
                            lock = true;
                            ui.setBtsOff(-1);
                        }
                        ui.printInfo(col,j);
                        ui.changeLight();
                    }//if
                    if(ui.isFull(col))
                        ui.setBtsOff(col);
                }//class
            });
        }
    }
    public void addQuitEvent(){
        ui.getChoice(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
    }
    public void addRestartEvent(){
        ui.getChoice(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ui.clearBoard();
                lock = false;
            }
        });
    }


}

