package ConnectFour;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.*;
/*
this class is designed to add ui of each element into the game
determined the layout of these elements as well
more importantly, the changes of the elements when game is ongoing
 */
public class GameUI {
    private GameRule rule;
    //the elements
    private Font font;
    private Rectangle[] recs;
    private TextField result;

    private Button[] bts;
    private Button[] indicator;
    private Button[] choice;

    private Circle[] light;
    private Circle[] chess;
    //the framework
    private GridPane board;
    private BorderPane frame;
    private GridPane control;

      //initialize all the elements
    GameUI() {
        this.rule = new GameRule();
        this.font = Font.font(null, FontWeight.NORMAL, null, 20);
        this.recs = new Rectangle[42];
        this.result = new TextField();
        this.bts = new Button[7];
        this.indicator = new Button[2];
        this.choice = new Button[2];
        this.light = new Circle[2];
        this.chess = new Circle[42];
        this.board = new GridPane();
        this.frame = new BorderPane();
        this.control = new GridPane();
        this.result = new TextField();
    }
        //set all the properties of the elements
    public void init() {
        for (int i = 0; i < 42; i++) {
            recs[i] = new Rectangle(60, 60);
            recs[i].setFill(Color.gray(0.36));
            chess[i] = new Circle(25);
            chess[i].setFill(Color.gray(0.36));
        }
        for (int i = 0; i < 7; i++) {
            bts[i] = new Button("Drop");
            bts[i].setPrefSize(60, 40);
        }
        for (int i = 0; i < 2; i++) {
            light[i] = new Circle(16);
            light[i].setFill(Color.rgb(0, 230, 0, 1));//control capacity
        }

        choice[0] = new Button("Restart");
        choice[1] = new Button("Quit");
        indicator[0] = new Button("Black");
        indicator[1] = new Button("White");

        for (int i = 0; i < 2; i++) {
            indicator[i].setFont(font);
            indicator[i].setPrefSize(100, 40);
            indicator[i].setMouseTransparent(true);
            choice[i].setPrefSize(150, 40);
            choice[i].setFont(font);
        }
        result.setFont(font);
        result.setPrefSize(180,50);
        result.setEditable(false);
    }
        //add all the elements into the main frame
        //set the layout between them
    public void load() {
        board.setHgap(1);
        control.setVgap(10);
        //fill the chess board
        for (int i = 0; i < 42; ++i){
            board.add(recs[i], i % 7, (i / 7) + 1);
            board.add(chess[i],i % 7, (i / 7) + 1);
            GridPane.setMargin(chess[i], new Insets(0, 0, 0, 5));
        }

        //for control part
        for (int i = 0; i < 2; i++) {
            GridPane.setMargin(light[i], new Insets(0, 20, 0, 20));
            GridPane.setMargin(choice[i], new Insets(20, 0, 0, 0));
            control.add(choice[i], 0, i + 10, 2, 1);
            control.add(indicator[i], 0, i+1);
            control.add(light[i], 1, i+1);
        }
        control.add(result,0,3,2,1);
        GridPane.setMargin(result,new Insets(20, 20, 0, 0));

        for (int i = 0; i < 7; i++)
            board.add(bts[i], i, 0);

        frame.setCenter(board);
        frame.setRight(control);
    }

    public void clearBoard(){
        for(int i = 0;i<42;++i)
            chess[i].setFill(Color.gray(0.36));
        this.rule = new GameRule();
        result.clear();
        for(int i = 0;i<7;++i)
            getButton(i).setOpacity(1);
    }

    public int addChess(int i) {

        int pos = rule.dropChess(i);//(i,pos) is the right point
        if (!rule.isBlack())
            chess[pos*7+i].setFill(Color.BLACK);
        else
            chess[pos*7+i].setFill(Color.WHITE);

        return pos;
    }
    public void setBtsOff(int tag){
        if(tag<0){
            for(int i = 0;i<7;++i)
                getButton(i).setOpacity(0);
        }
        else
            getButton(tag).setOpacity(0);
    }

    public void changeLight() {
        if (rule.isBlack()) {
            light[0].setFill(Color.rgb(0, 230, 0, 1));
            light[1].setFill(Color.rgb(0, 230, 0, 0));
        } else {
            light[0].setFill(Color.rgb(0, 230, 0, 0));
            light[1].setFill(Color.rgb(0, 230, 0, 1));
        }
    }

    public boolean isWin(int i,int j){
        return rule.checkWin(i,j);
    }

    public BorderPane getFrame() {
        return frame;
    }

    public Button getButton(int i){
        return bts[i];
    }
    public boolean isFull(int i){
        return rule.isFull(i);
    }
    public Button getChoice(int i){
        return choice[i];
    }
    public void printInfo(int i,int j){
            if(isWin(i,j)) {
                if(rule.isBlack())
                    result.setText("White side win!");
                else
                    result.setText("Black side win!");
            }
            if(rule.isAllFull())
                result.setText("Draw!");
    }
}

