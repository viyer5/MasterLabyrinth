package code.GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import code.Model.Model;
import code.pawn.Pawn;

public class keyMovement implements KeyListener {
	
	private Pawn _pawn;
	private Model _model;
	
	/**
	 * @author <jtmirfie>
	 * Constructor
	 * Creates the key listener for the whole game that is used for pawn movement.
	 * @param m Associates the Model class with this one in order to access the board.
	 * @param p Associates the Pawn class with this one to call on the move methods.
	 */	
	public keyMovement(Model m,Pawn p) {
		// TODO Auto-generated constructor stub
		_pawn = p;
		_model = m;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @author <jtmirfie>
	 * Runs after one of the arrow keys are pressed and then released.
	 * Checks to first make sure that a tile is shifted. 
	 * After the code checks if the movement is possible and also checks if there is token to pick up.
	 */	
	@Override
	public void keyReleased(KeyEvent e) {
		
		if(!_model.firstMove){
		int keyCode = e.getKeyCode();
	    switch( keyCode ) { 
	        case KeyEvent.VK_UP:
	            _pawn.move("North");
	            System.out.println(_pawn.checkIfValidMove());
	            if(_pawn.validMove){
	            	_pawn.pickUpToken();
	            	System.out.println(_pawn.tokenCount());
	            }
	            _model.gameChanged();
	            break;
	        case KeyEvent.VK_DOWN:
	        	_pawn.move("South");
	        	System.out.println(_pawn.checkIfValidMove());
	        	if(_pawn.validMove){
	            	_pawn.pickUpToken();
	            	System.out.println(_pawn.tokenCount());
	            }
	        	_model.gameChanged();
	            break;
	        case KeyEvent.VK_LEFT:
	        	_pawn.move("West");
	        	System.out.println(_pawn.checkIfValidMove());
	        	if(_pawn.validMove){
	            	_pawn.pickUpToken();
	            	System.out.println(_pawn.tokenCount());
	            }
	        	_model.gameChanged();
	            break;
	        case KeyEvent.VK_RIGHT :
	        	_pawn.move("East");
	        	System.out.println(_pawn.checkIfValidMove());
	        	if(_pawn.validMove){
	            	_pawn.pickUpToken();
	            	System.out.println(_pawn.tokenCount());
	            }
	        	_model.gameChanged();
	            break;
	            
	        case KeyEvent.VK_KP_UP:
	            _pawn.move("North");
	            System.out.println(_pawn.checkIfValidMove());
	            if(_pawn.validMove){
	            	_pawn.pickUpToken();
	            	System.out.println(_pawn.tokenCount());
	            }
	            _model.gameChanged();
	            break;
	        case KeyEvent.VK_KP_DOWN:
	        	_pawn.move("South");
	        	System.out.println(_pawn.checkIfValidMove());
	        	if(_pawn.validMove){
	            	_pawn.pickUpToken();
	            	System.out.println(_pawn.tokenCount());
	            }
	        	_model.gameChanged();
	            break;
	        case KeyEvent.VK_KP_LEFT:
	        	_pawn.move("West");
	        	System.out.println(_pawn.checkIfValidMove());
	        	if(_pawn.validMove){
	            	_pawn.pickUpToken();
	            	System.out.println(_pawn.tokenCount());
	            }
	        	_model.gameChanged();
	            break;
	        case KeyEvent.VK_KP_RIGHT :
	        	_pawn.move("East");
	        	System.out.println(_pawn.checkIfValidMove());
	        	if(_pawn.validMove){
	            	_pawn.pickUpToken();
	            	System.out.println(_pawn.tokenCount());
	            }
	        	_model.gameChanged();
	            break;    
	    	}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
