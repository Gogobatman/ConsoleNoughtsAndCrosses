package noughtsandcrosses;

import java.util.Scanner;

public class Main {
    private Scanner input = new Scanner(System.in);
    private char sign='X';
    private int round;
    private int boardSize;
    private char gameBoard[][]=new char[20][20];
    private char upperPart[]={'A','B','C','D','E','F','G','H','I','J','K'};
    private int xCoordinate=100;
    private char yCoordinate='W';

    public Main(int size){
        boardSize=size;
    }
    private void boardInit(){
        for(int z=1; z<boardSize+1; z++){
            gameBoard[0][z]=(char)(z+48);
        }
        for(int i=1; i<boardSize+1; i++){
            gameBoard[i][0]=upperPart[i-1];
            for(int j=1; j<boardSize+1; j++){
                gameBoard[i+1][j]=' ';
            }
        }
    }
    private void boardPrint(){
        for(int i=0; i<boardSize+1; i++){
            for(int j=0; j<boardSize+1; j++){
                System.out.print(gameBoard[i][j]);
            }
            System.out.println(" ");
        }
    }
    private char convertYCoordinate(char c){
        return ((char)(c-64));
    }
    private void resetCoordinates(){
        xCoordinate=100;
        yCoordinate='W';
    }
    private void readFirstCoordinate(){
        while(xCoordinate<0 || xCoordinate>9){
            System.out.println("Enter first coordinate 1-"+boardSize);
            char tmp=input.next().charAt(0);
            xCoordinate=(tmp-'0');
        }
    }
    private void readSecondCoordinate(){
        while(yCoordinate>64+boardSize || yCoordinate<64){
            System.out.println("Enter second coordinate A-"+(char)('A'+boardSize-1));
            yCoordinate=input.next().charAt(0);
        }
    }
    private void nextRound(){
        if(round%2==0){
            sign='X';
        }else{
            sign='O';
        }
        round++;
    }
    private void makingMove() {
        while (true) {
            boardPrint();
            resetCoordinates();
            readFirstCoordinate();
            readSecondCoordinate();
            yCoordinate = convertYCoordinate(yCoordinate);
            if(checkIfFieldEmpty(yCoordinate,xCoordinate)){
                gameBoard[yCoordinate][xCoordinate] = '-';
                boardPrint();
                System.out.println("Do you want to comfirm your move? y/n");
                int c = input.next().charAt(0);
                if (c == 'y') {
                    gameBoard[yCoordinate][xCoordinate] = sign;
                    boardPrint();
                    break;
                }
                else{
                    gameBoard[yCoordinate][xCoordinate] = ' ';
                }
            }else{
                System.out.println("Field is NOT empty");
            }

        }
    }
    private boolean checkIfWinVertically(){
        for(int i=1; i<boardSize+1; i++){
            int correct=0;
            for(int j=1; j<boardSize+1; j++){
                if(gameBoard[j][i]==sign){
                    correct++;
                }
                if(correct==boardSize){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean checkIfFieldEmpty(int a, int b){
        if(gameBoard[a][b]!='X'&&gameBoard[a][b]!='O'){
            return true;
        }else{
            return false;
        }
    }
    private boolean checkIfWinHorizontally(){
        for(int i=1; i<boardSize+1; i++){
            int correct=0;
            for(int j=1; j<boardSize+1; j++){
                if(gameBoard[i][j]==sign){
                    correct++;
                }
                if(correct==boardSize){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean checkIfWinDiagonallyOne(){
        int correct=0;
        for(int i=1; i<boardSize+1; i++){
                if(gameBoard[i][i]==sign){
                    correct++;
                }
                if(correct==boardSize){
                    return true;
                }
        }
        return false;
    }
    private boolean checkIfWinDiagonallyTwo(){
        int correct=0;
        for(int i=1; i<boardSize+1; i++){
            if(gameBoard[i][boardSize+1-i]==sign){
                correct++;
            }
            if(correct==boardSize){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
         Main m = new Main(5);
         m.boardInit();
         while((!m.checkIfWinVertically())&&(!m.checkIfWinHorizontally())&&
        (!m.checkIfWinDiagonallyOne())&&(!m.checkIfWinDiagonallyTwo())){
            m.nextRound();
            m.makingMove();
         }
        if(m.checkIfWinHorizontally()){
            System.out.println(m.sign+" Horizontal win!");
        }if(m.checkIfWinVertically()){
            System.out.println(m.sign+" Vertical win!");
        }if((m.checkIfWinDiagonallyOne()) || (m.checkIfWinDiagonallyTwo())){
            System.out.println(m.sign+" Diagonal win!");
        }
    }
}
