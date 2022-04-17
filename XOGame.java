import javax.swing.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class XOGame extends Canvas implements MouseListener {
    private String message = "";
    private String scoreX = "";
    private String scoreO = "";
    private int scoreOfX;
    private int scoreOfO;

    private int StartX=200,startY=50,squareSize=50,n=10;
    private int [][]board=new int[n][n];
    private int playerTurn=1;
    private boolean gameOver;
    private int numberOfPieces=0;

    public XOGame()
    {
        addMouseListener(this);
    }
    public void paint( Graphics g )
    {
        for(int i=0;i<=n;i++) {
            g.drawLine(StartX, startY+i*squareSize, StartX+n*squareSize, startY+i*squareSize);
            g.drawLine(StartX+i*squareSize, startY, StartX+i*squareSize, startY+n*squareSize);
        }
        g.setFont(new Font ("Arial",Font.ITALIC, 20));
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]==1) {
                    g.setColor(Color.red);
                    g.drawString("X",StartX+j*squareSize+squareSize/3 ,startY+i*squareSize+squareSize/2);
                }
                else if(board[i][j]==2) {
                    g.setColor(Color.blue);
                    g.drawString("O",StartX+j*squareSize+squareSize/3 ,startY+i*squareSize+squareSize/2);
                }
            }
        }
        g.setFont(new Font ("Arial",Font.BOLD, 20));
        g.setColor(Color.magenta);
        g.drawString(message,10,40);
        g.drawString(scoreX,10,80);
        g.drawString(scoreO,10,120);
    }
    public void mouseClicked( MouseEvent evt )
    {
        if(!gameOver) {
            if( evt.getX() >StartX && evt.getX()<StartX+n*squareSize
                    && evt.getY()>startY && evt.getY()<startY+n*squareSize) {
                int row=(evt.getY()-startY)/squareSize;
                int col=(evt.getX()-StartX)/squareSize;
                if(board[row][col]==0) {
                    numberOfPieces++;
                    board[row][col] = playerTurn;
                    scoreOfX = 0;
                    scoreOfO = 0;
                    for (int colum = 0; colum < n; colum++) {
                        for (int rw = 0; rw < n; rw++) {
                            if (rw < 8) {
                                if ((board[colum][rw] == 1 && board[colum][rw + 1] == 1 && board[colum][rw + 2] == 1)) {
                                    scoreOfX++;
                                } else if ((board[colum][rw] == 2 && board[colum][rw + 1] == 2 && board[colum][rw + 2] == 2)) {
                                    scoreOfO++;
                                }
                            }
                            if (colum < 8) {
                                if ((board[colum][rw] == 1 && board[colum + 1][rw] == 1 && board[colum + 2][rw] == 1)) {
                                    scoreOfX++;
                                } else if ((board[colum][rw] == 2 && board[colum + 1][rw] == 2 && board[colum + 2][rw] == 2)) {
                                    scoreOfO++;
                                }
                            }
                            if (rw < 8 && colum < 8) {
                                if ((board[colum][rw] == 1 && board[colum + 1][rw + 1] == 1 && board[colum + 2][rw + 2] == 1)) {
                                    scoreOfX++;
                                } else if ((board[colum][rw] == 2 && board[colum + 1][rw + 1] == 2 && board[colum + 2][rw + 2] == 2)) {
                                    scoreOfO++;
                                }
                            }
                            if (colum < 8 && rw >= 2) {
                                if ((board[colum][rw] == 1 && board[colum + 1][rw - 1] == 1 && board[colum + 2][rw - 2] == 1)) {
                                    scoreOfX++;
                                } else if ((board[colum][rw] == 2 && board[colum + 1][rw - 1] == 2 && board[colum + 2][rw - 2] == 2)) {
                                    scoreOfO++;
                                }
                            }
                        }
                    }
                }
                if(numberOfPieces==100) {
                    if(scoreOfX > scoreOfO){
                        message = "X win";
                    }
                    else if(scoreOfX < scoreOfO){
                        message = "O win";
                    }
                    else{
                        gameOver = true;
                    }
                }
                else {
                    if(playerTurn==1) {
                        message="Next Turn=O";
                        playerTurn=2;
                    }
                    else{
                        message="Next Turn=X";
                        playerTurn=1;
                    }
                }
                scoreX = "Score of X : " + scoreOfX;
                scoreO = "Score of O : " + scoreOfO;
            }
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }
    @Override
    public void mouseExited(MouseEvent arg0) {
    }
    @Override
    public void mousePressed(MouseEvent arg0) {
    }
    @Override
    public void mouseReleased(MouseEvent arg0) {
    }
}
class MainXO {
    public static void main(String[] args) {
        JFrame win = new JFrame("X-O Game");
        win.setSize(1024,768);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.add( new XOGame() );
        win.setVisible(true);
    }
}
