import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ControlGame extends Application {
    private ChessBoard chessBoard = new ChessBoard();
    private boolean lock = false;

    public void start(Stage stage){
        chessBoard.init();
        chessBoard.load();
        chessBoard.changeLight();

        addInfoEvents();
        addQuitEvent();
        addRestartEvent();

        Scene scene = new Scene(chessBoard.getFrame(),650,450);
        stage.setScene(scene);
        stage.show();
    }

    public void addInfoEvents(){
        for(int i = 0;i<7;++i){
            chessBoard.getButton(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    int col = GridPane.getColumnIndex((Button)event.getSource());
                    if(!chessBoard.isFull(col) && !lock){
                        int j =chessBoard.addChess(col);
                        if(chessBoard.isWin(col,j)){
                            lock = true;
                            chessBoard.setBtsOff(-1);
                        }
                        chessBoard.printInfo(col,j);
                        chessBoard.changeLight();
                    }//if
                    if(chessBoard.isFull(col))
                        chessBoard.setBtsOff(col);
                }//class
            });
        }
    }
    public void addQuitEvent(){
        chessBoard.getChoice(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
    }
    public void addRestartEvent(){
        chessBoard.getChoice(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                chessBoard.clearBoard();
                lock = false;
            }
        });
    }


}

