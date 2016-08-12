package code.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Model.Model;

public class rotateListener implements ActionListener{

	private String _direction;
	private Model _model;
	
	/**
	 * @author <jtmirfie>
	 * Constructor
	 * Runs whenever the rotate button is clicked.
	 * This takes the outside tile that is not on the board and rotates it clockwise.
	 * @param m associates the model class to figure out the piece that is off the board.
	 * @param d checks to see what direction the tile is initially orientated in.
	 */	
	public rotateListener(Model m,String d) {
		// TODO Auto-generated constructor stub
		_model = m;
		_direction = d;
	}

	
	/**
	 * @author <jtmirfie>
	 * Finds what direction the tile is orientated then rotates it clockwise.
	 */	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(_direction.equals("North"))_model.getHoldTile().Rotate("East");
		if(_direction.equals("East"))_model.getHoldTile().Rotate("South");
		if(_direction.equals("South"))_model.getHoldTile().Rotate("West");
		if(_direction.equals("West"))_model.getHoldTile().Rotate("North");
		_model.gameChanged();
		
	}

}
