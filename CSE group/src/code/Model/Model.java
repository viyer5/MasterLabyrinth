package code.Model;

import java.awt.Point;
import java.util.Arrays;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import code.Tiles.ElbowTile;
import code.Tiles.StraightTiles;
import code.Tiles.TTile;
import code.Tiles.Tiles;
import code.Tokens.Token;
import code.pawn.Pawn;

public class Model {
		
		int rows = 7;
		int col = 7;
		private Observer _observer;
		private ArrayList<Token>tokens;
		public Boolean firstMove = true;
		code.Tiles.Tiles[][] board  = new code.Tiles.Tiles[col][rows];
		Tiles hold = null;
		public Pawn[] pawns;
		public int tokenCounter = 1;
		public int playerUp = 1;
		
		/**
		 * @author Wiechec - Constructor
		 * Calls method for board creation and setting the permanent tiles via the set static method.
		 * Previous version did not have a constructor and relied on tests calling the methods separately.
		 * This version of the constructor would automate board creation for the player. An additional 
		 * constructor can be added in the future for players to set the board layout by adding individual
		 * pieces.
		 */
		
		//error in code caused by pawn needing fourth parameter from command line input. New parameter sets the player's name into the pawn
		public Model(){
			this.setStaticTiles();
			this.setBoard();
			pawns = new Pawn[4];
		}
		/**
		 * @author Wiechec
		 * Constructor
		 * Tester board for Junit movement tests. Creates a board and sets static tiles
		 * but any additional tiles are the responsibility of the programmer.
		 * @param test
		 */
		public Model(String test){
			if (test == "test"){
				this.setStaticTiles();
				board[2][3] = new StraightTiles("East");
				board[1][2] = new StraightTiles();
				board[3][2] = new TTile();
				board[2][1] = new StraightTiles();
				board[5][5] = new ElbowTile();
				board[4][5] = new StraightTiles();
				board[5][4] = new TTile("West");
				board[3][3] = new StraightTiles("East");
				board[3][4] = new ElbowTile("West");
				board[4][3] = new StraightTiles();
				
				
			}
		}
		/**
		 * Author: Wiechec
		 * Method returns the boolean value on the tile associated with the array x and y,
		 * Board[x][y] values. Tile calls get direction method in the tile class which indicates
		 * if that direction is free for movement.
		 * @param x horizontal movement in 2d array
		 * @param y vertical movement in 2d array
		 * @param direction: Direction pawn wishes to move to.
		 * @return :True or false depending on the field set in the Tile for that direction.
		 */
		public Boolean getTileBool(int x, int y, String direction){
			return board[x][y].getDirection(direction);
			
		}
		
		/**
		 * @author <jtmirfie>
		 * Sets an observer on the board to check when the board has been updated.
		 * @param o Observer to check if board has changed
		 */	
		public void setObserver(Observer o){
			_observer = o;
		}
		
		/**
		 * @author <jtmirfie>
		 * Update called on observer to send an update from Model to GUI.
		 */	
		public void gameChanged(){
			if (_observer != null) {
				_observer.update();
			}
		}
		
		/** @author bdlipp
		 * Method sets the tiles that are static on the board.
		 * These 'static tiles' should not be movable by the methods in this class.
		 * 
		 */
		public void setStaticTiles(){
			board [0][0] = new ElbowTile("East");
			board [0][0].isPermanent(true);
			board [0][2] = new TTile("East");
			board [0][2].isPermanent(true);
			board [0][4] = new TTile("East");
			board [0][4].isPermanent(true);
			board [0][6] = new ElbowTile("North");
			board [0][6].isPermanent(true);
			
			board [2][0] = new TTile("South");
			board [2][0].isPermanent(true);
			board [2][2] = new TTile("East");
			board [2][2].isPermanent(true);
			board [2][4] = new TTile("North");
			board [2][4].isPermanent(true);
			board [2][6] = new TTile("North");
			board [2][6].isPermanent(true);
			
			board [4][0] = new TTile("South");
			board [4][0].isPermanent(true);
			board [4][2] = new TTile("South");
			board [4][2].isPermanent(true);
			board [4][4] = new TTile("West");
			board [4][4].isPermanent(true);
			board [4][6] = new TTile("North");
			board [4][6].isPermanent(true);	
			
			board [6][0] = new ElbowTile("South");
			board [6][0].isPermanent(true);
			board [6][2] = new TTile("West");
			board [6][2].isPermanent(true);
			board [6][4] = new TTile("West");
			board [6][4].isPermanent(true);
			board [6][6] = new ElbowTile("West");
			board [6][6].isPermanent(true);
		}
		/**@author bdlipp
		 * Method sets the board with tiles.
		 * These tiles are randomly chosen from Straight, Elbow and T groups and
		 * placed in the needed spots. The board should be completely full after 
		 * this method with one remaining tile which is our 'hold' tile.
		 * 
		 */
		public void setBoard(){
			setupTokens();
			int ttiles = 6;
			int elbow = 15;
			int straight = 13;
			//CHANGED*********
			for(int i=0; i<col; i++){
				for(int j =0; j<rows; j++){
					if(board[i][j]==null){
						if(elbow>0){
							board[i][j] = new ElbowTile("North");
							elbow--;
							continue;
						}
						if(straight>0){
							board[i][j] = new StraightTiles("East");
							straight--;
							continue;
						}
						if(ttiles>0){
							board[i][j] = new TTile("South");
							ttiles--;
							continue;
						}
						
						
					}
				}
				
			}
			//System.out.println(straight);System.out.println(ttiles);System.out.println(elbow);
			if(straight==1){
				Tiles tile = new StraightTiles("North");
				setholdTile(tile);
			}
			else if(elbow==1){
				Tiles tile = new ElbowTile("North");
				setholdTile(tile);
			}
			else if(ttiles==1){
				Tiles tile = new TTile("North");
				setholdTile(tile);
			}
			
			
			int counter = 0;
			for(int col=1;col<6;col=col+2){
				for(int row=1;row<6;row++ ){
					board[col][row].setToken(tokens.get(counter));
					board[col][row].hasToken= true;
				    counter++;
				    }
				}
			for(int col=2;col<6;col=col+2){
				for(int row=1;row<6;row=row+2){
					board[col][row].setToken(tokens.get(counter));
					board[col][row].hasToken= true;
				    counter++;
				    }
				}
			
			for(int col=0;col<7;col++){
				for(int row=0;row<7;row++){					
					if(board[col][row].checkToken())System.out.println("Col: "+col+ " " +"Row: "+ row + " Token: "+board[col][row].getToken().getValue());
				}
			}
			
			
			
		}
		/**@author bdlipp
		 * Method pushes the 'hold' tile across a row or down a column by passing reference from 
		 * one tile to the next. The last tile on the board will be pushed off and will become
		 * the 'hold tile.
		 * 
		 * @param col
		 * @param row
		 * @return the 'hold' tile weather that be the last one pushed out or the original
		 * 		   if the move fails
		 */
		public Tiles moveTiles(int col, int row){//takes in two ints for points to move and one Tile which is the one tile not on the board
			Tiles tempTile = hold;							//also returns the tile that fell off the board
			Tiles[][] tempBoard = new Tiles[7][];
			
			if(firstMove){
			for(int i=0;i<7;i++){
				tempBoard[i] = Arrays.copyOf(board[i], board[i].length);
			} 
			
			if(col==0 && row==1 || col==0 && row==3 || col==0 && row==5){//left
				
				hold=tempBoard[col+6][row];
				if(hold.checkToken()){
					tempTile.setToken(hold.getToken());
					tempTile.hasToken=true;
					hold.hasToken=false;
				}			
				board[col][row]=tempTile;
				board[col+1][row]=tempBoard[col][row];
				board[col+2][row]=tempBoard[col+1][row];
				board[col+3][row]=tempBoard[col+2][row];
				board[col+4][row]=tempBoard[col+3][row];
				board[col+5][row]=tempBoard[col+4][row];
				board[col+6][row]=tempBoard[col+5][row];
				firstMove = false;
				
				for(int i=0;i<7;i++){
					if(pawns[0].getPositionX()==col+i && pawns[0].getPositionY()==row){
						if(pawns[0].getPositionX()+1>6){
							pawns[0].setCurrentLocation(pawns[0].getPositionY(), pawns[0].getPositionX()-6);
							break;
						}
						pawns[0].setCurrentLocation(pawns[0].getPositionY(), pawns[0].getPositionX()+1);
						break;
					}
				}
				for(int i=0;i<7;i++){
					if(pawns[1].getPositionX()==col+i && pawns[1].getPositionY()==row){
						if(pawns[1].getPositionX()+1>6){
							pawns[1].setCurrentLocation(pawns[1].getPositionY(), pawns[1].getPositionX()-6);
							break;
						}
						pawns[1].setCurrentLocation(pawns[1].getPositionY(), pawns[1].getPositionX()+1);
						break;
					}
				}
				for(int i=0;i<7;i++){
					if(pawns[2].getPositionX()==col+i && pawns[2].getPositionY()==row){
						if(pawns[2].getPositionX()+1>6){
							pawns[2].setCurrentLocation(pawns[0].getPositionY(), pawns[2].getPositionX()-6);
							break;
						}
						pawns[2].setCurrentLocation(pawns[2].getPositionY(), pawns[2].getPositionX()+1);
						break;
					}
				}
				for(int i=0;i<7;i++){
					if(pawns[3].getPositionX()==col+i && pawns[3].getPositionY()==row){
						if(pawns[3].getPositionX()+1>6){
							pawns[3].setCurrentLocation(pawns[3].getPositionY(), pawns[3].getPositionX()-6);
							break;
						}
						pawns[3].setCurrentLocation(pawns[3].getPositionY(), pawns[3].getPositionX()+1);
						break;
					}
				}
				
				gameChanged();
			}
			else if(col==1 && row==0 || col==3 && row==0 || col==5 && row==0){//top
				hold=tempBoard[col][row+6];
				if(hold.checkToken()){
					tempTile.setToken(hold.getToken());
					tempTile.hasToken=true;
					hold.hasToken=false;
				}		
				board[col][row]=tempTile;
				board[col][row+1]=tempBoard[col][row];
				board[col][row+2]=tempBoard[col][row+1];
				board[col][row+3]=tempBoard[col][row+2];
				board[col][row+4]=tempBoard[col][row+3];
				board[col][row+5]=tempBoard[col][row+4];
				board[col][row+6]=tempBoard[col][row+5];
				firstMove = false;
				
				for(int i=0;i<7;i++){
					if(pawns[0].getPositionX()==col && pawns[0].getPositionY()==row+i){
						if(pawns[0].getPositionY()+1>6){
							pawns[0].setCurrentLocation(pawns[0].getPositionY()-6, pawns[0].getPositionX());
							break;
						}
						pawns[0].setCurrentLocation(pawns[0].getPositionY()+1, pawns[0].getPositionX());					
						break;
					}
				}
				for(int i=0;i<7;i++){
					if(pawns[1].getPositionX()==col && pawns[1].getPositionY()==row+i){
						if(pawns[1].getPositionY()+1>6){
							pawns[1].setCurrentLocation(pawns[1].getPositionY()-6, pawns[1].getPositionX());
							break;
						}
						pawns[1].setCurrentLocation(pawns[1].getPositionY()+1, pawns[1].getPositionX());
						break;
					}
				}
				for(int i=0;i<7;i++){
					if(pawns[2].getPositionX()==col && pawns[2].getPositionY()==row+i){
						if(pawns[2].getPositionY()+1>6){
							pawns[2].setCurrentLocation(pawns[2].getPositionY()-6, pawns[2].getPositionX());
							break;
						}
						pawns[2].setCurrentLocation(pawns[2].getPositionY()+1, pawns[2].getPositionX());
						break;
					}
				}
				for(int i=0;i<7;i++){
					if(pawns[3].getPositionX()==col && pawns[3].getPositionY()==row+i){
						if(pawns[3].getPositionY()+1>6){
							pawns[3].setCurrentLocation(pawns[3].getPositionY()-6, pawns[3].getPositionX());
							break;
						}
						pawns[3].setCurrentLocation(pawns[3].getPositionY()+1, pawns[3].getPositionX());
						break;
					}
				}
				
				gameChanged();
			}
			else if(col==6 && row==1 || col==6 && row==3 ||col==6 && row==5){//right
				hold=tempBoard[col-6][row];
				if(hold.checkToken()){
					tempTile.setToken(hold.getToken());
					tempTile.hasToken=true;
					hold.hasToken=false;
				}	
				board[col][row]=tempTile;
				board[col-1][row]=tempBoard[col][row];
				board[col-2][row]=tempBoard[col-1][row];
				board[col-3][row]=tempBoard[col-2][row];
				board[col-4][row]=tempBoard[col-3][row];
				board[col-5][row]=tempBoard[col-4][row];
				board[col-6][row]=tempBoard[col-5][row];
				firstMove = false;
				
				for(int i=0;i<7;i++){
					if(pawns[0].getPositionX()==col-i && pawns[0].getPositionY()==row){
						if(pawns[0].getPositionX()-1<0){
							pawns[0].setCurrentLocation(pawns[0].getPositionY(), pawns[0].getPositionX()+6);
							break;
						}
						pawns[0].setCurrentLocation(pawns[0].getPositionY(), pawns[0].getPositionX()-1);
						break;
					}
				}
				for(int i=0;i<7;i++){
					if(pawns[1].getPositionX()==col-i && pawns[1].getPositionY()==row){
						if(pawns[1].getPositionX()-1<0){
							pawns[1].setCurrentLocation(pawns[1].getPositionY(), pawns[1].getPositionX()+6);
							break;
						}
						pawns[1].setCurrentLocation(pawns[1].getPositionY(), pawns[1].getPositionX()-1);
						break;
					}
				}
				for(int i=0;i<7;i++){
					if(pawns[2].getPositionX()==col-i && pawns[2].getPositionY()==row){
						if(pawns[2].getPositionX()-1<0){
							pawns[2].setCurrentLocation(pawns[2].getPositionY(), pawns[2].getPositionX()+6);
							break;
						}
						pawns[2].setCurrentLocation(pawns[2].getPositionY(), pawns[2].getPositionX()-1);
						break;
					}
				}
				for(int i=0;i<7;i++){
					if(pawns[3].getPositionX()==col-i && pawns[3].getPositionY()==row){
						if(pawns[3].getPositionX()-1<0){
							pawns[3].setCurrentLocation(pawns[3].getPositionY(), pawns[3].getPositionX()+6);
							break;
						}
						pawns[3].setCurrentLocation(pawns[3].getPositionY(), pawns[3].getPositionX()-1);
						break;
					}
				}
				gameChanged();
			}
			else if(col==1 && row==6 || col==3 && row==6 || col==5 && row==6){//bottom
				hold=tempBoard[col][row-6];
				if(hold.checkToken()){
					tempTile.setToken(hold.getToken());
					tempTile.hasToken=true;
					hold.hasToken=false;
				}	
				board[col][row]=tempTile;
				board[col][row-1]=tempBoard[col][row];
				board[col][row-2]=tempBoard[col][row-1];
				board[col][row-3]=tempBoard[col][row-2];
				board[col][row-4]=tempBoard[col][row-3];
				board[col][row-5]=tempBoard[col][row-4];
				board[col][row-6]=tempBoard[col][row-5];
				firstMove = false;
				
				for(int i=0;i<7;i++){
					if(pawns[0].getPositionX()==col && pawns[0].getPositionY()==row-i){
						if(pawns[0].getPositionY()-1<0){
							pawns[0].setCurrentLocation(pawns[0].getPositionY()+6, pawns[0].getPositionX());
							break;
						}
						pawns[0].setCurrentLocation(pawns[0].getPositionY()-1, pawns[0].getPositionX());
						break;
					}
				}
				for(int i=0;i<7;i++){
					if(pawns[1].getPositionX()==col && pawns[1].getPositionY()==row-i){
						if(pawns[1].getPositionY()-1<0){
							pawns[1].setCurrentLocation(pawns[1].getPositionY()+6, pawns[1].getPositionX());
							break;
						}
						pawns[1].setCurrentLocation(pawns[1].getPositionY()-1, pawns[1].getPositionX());
						break;
					}
				}
				for(int i=0;i<7;i++){
					if(pawns[2].getPositionX()==col && pawns[2].getPositionY()==row-i){
						if(pawns[2].getPositionY()-1<0){
							pawns[2].setCurrentLocation(pawns[2].getPositionY()+6, pawns[2].getPositionX());
							break;
						}
						pawns[2].setCurrentLocation(pawns[2].getPositionY()-1, pawns[2].getPositionX());
						break;
					}
				}
				for(int i=0;i<7;i++){
					if(pawns[3].getPositionX()==col && pawns[3].getPositionY()==row-i){
						if(pawns[3].getPositionY()-1<0){
							pawns[3].setCurrentLocation(pawns[3].getPositionY()+6, pawns[3].getPositionX());
							break;
						}
						pawns[3].setCurrentLocation(pawns[3].getPositionY()-1, pawns[3].getPositionX());
						break;
					}
				}
				gameChanged();
			}
			else{
				System.out.println("I'm sorry, that's a permanent structure");
			}			
			}
			return hold;

		}
		/**@author bdlipp
		 * Method returns the tile at a specific spot in the graph.
		 * 
		 * @param col
		 * @param row
		 * @return Tiles object that is in a specific column and row.
		 */
		public Tiles getTile(int col, int row){
			return board[col][row];
		}
		/**@author bdlipp
		 * Method sets the 'hold' tile. Used for testing making it easier to follow a specific
		 * tile that is not randomly created.
		 * @param tile
		 */
		public void setholdTile(Tiles tile){
			hold = tile;
		}
		/**@author bdlipp
		 * Method returns the 'hold' tile. Used for testing making it easier to follow a specific
		 * tile that is not randomly created.
		 * 
		 * @return 'hold' tile. Used for testing making it easier to follow a specific
		 * tile that is not randomly created.
		 */
		public Tiles getHoldTile(){
			return hold;
		}
		
		/**
		 * @author <jtmirfie>
		 * Returns the 'board' tiles. Used to associate between other classes.
		 * @return 'board' tiles in order to access it from other classes.
		 */	
		public Tiles[][] getBoard(){
			return board;
		}
		
		/**
		 * @author <jtmirfie>
		 * Compiles an array of the tokens class and then shuffles them using a collection method.
		 */	
		public void setupTokens(){
			tokens = new ArrayList<Token>();
			for(int i=0;i<21;i++){
				tokens.add(new Token());
			}
			Collections.shuffle(tokens);
			
		}
		
		/**
		 * @author <jtmirfie>
		 * Runs after the last token is picked up. Counts up all the tokens each player has picked up then exits the game.
		 */	
		public void endGame(){
			int player1score=0;
			int player2score=0;
			int player3score=0;
			int player4score=0;
			
			System.out.println();
			System.out.println(pawns[0].getPlayer() +":");
			System.out.println(pawns[0].tokenCount());
			for(int i=0;i<pawns[0].tokenCount().size();i++){
				player1score= player1score +pawns[0].tokenCount().get(i);
			}
			System.out.println("Score: "+player1score);
			System.out.println();
			
			System.out.println(pawns[1].getPlayer() +":");
			System.out.println(pawns[1].tokenCount());
			for(int i=0;i<pawns[1].tokenCount().size();i++){
				player2score= player2score +pawns[1].tokenCount().get(i);
			}
			System.out.println("Score: "+player2score);
			System.out.println();
			
			System.out.println(pawns[2].getPlayer() +":");
			System.out.println(pawns[2].tokenCount());
			for(int i=0;i<pawns[2].tokenCount().size();i++){
				player3score= player3score +pawns[2].tokenCount().get(i);
			}
			System.out.println("Score: "+player3score);
			System.out.println();
			
			System.out.println(pawns[3].getPlayer() +":");
			System.out.println(pawns[3].tokenCount());
			for(int i=0;i<pawns[3].tokenCount().size();i++){
				player4score= player4score +pawns[3].tokenCount().get(i);
			}
			System.out.println("Score: "+player4score);
			System.out.println();
			
			System.exit(0);
		}
		
}