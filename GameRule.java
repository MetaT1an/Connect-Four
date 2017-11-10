package ConnectFour;

/*
this class is designed to store data generated in playing games
and make the rule of the playing as well
 */
public class GameRule {

    private int[][] board;
    private int[] full;
    private boolean isBlack;

    public GameRule(){
        this.board = new int[7][];
        for(int i=0;i<7;i++)
            this.board[i] = new int[6];

        this.isBlack = true;//black side is first
        full = new int[7];
        for(int i=0;i<7;i++)
            full[i] = 5;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public void inTurn(){
        this.isBlack = !this.isBlack;
    }

    public boolean isFull(int i){
        return full[i] == -1;
    }
    public boolean isAllFull(){
        for(int i=0;i<7;++i)
            if(!isFull(i))
                return false;
        return true;
    }

    //the most important part
    //check whether one side has won the game
    public boolean checkWin(int i,int j){
        if(line(i,j)||column(i,j)||leftR(i,j)||rightL(i,j))
            return true;
        else
            return false;
    }
    public boolean line(int i,int j){
        int cx = 0;
        for(int m =i+1;m<7 && board[m][j]==board[i][j];++m)
                cx++;
        for(int m = i-1;m>=0 && board[m][j]==board[i][j];--m)
                cx++;
        return cx>=3;
    }
    public boolean column(int i,int j){
        int cx = 0;
        for(int n =j+1;n<6 && board[i][n]==board[i][j];++n)
                cx++;
        for(int n = j-1;n>=0 && board[i][n]==board[i][j];--n)
                cx++;
        return cx>=3;
    }
    public boolean leftR(int i,int j){
        int cx = 0;
        for(int m=i+1,n=j+1;m<7 && n<6 && board[m][n]==board[i][j];++m,++n)
                cx++;
        for(int m=i-1,n=j-1;m>=0 && n>=0 && board[m][n]==board[i][j];--m,--n)
                cx++;
        return cx>=3;
    }
    public boolean rightL(int i,int j){
        int cx = 0;
        for(int m=i-1,n=j+1;m>=0 && n<6 && board[m][n]==board[i][j];--m,++n)
                cx++;
        for(int m=i+1,n=j-1;m<7 && n>=0 && board[m][n]==board[i][j];++m,--n)
                cx++;
        return cx>=3;
    }

    //change the data of game when one side drop the chess
    public int dropChess(int i){
        board[i][full[i]] = (isBlack()) ? 1 : 2;
        inTurn();
        full[i]--;
        return full[i]+1;
    }
}
