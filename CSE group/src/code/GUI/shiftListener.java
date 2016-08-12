package code.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Model.Model;

public class shiftListener implements ActionListener {

	private Model _model;
	private int col;
	private int row;
	
	/**
	 * @author <jtmirfie>
	 * Constructor
	 * Runs the moveTiles() method whenever the respective button is clicked.
	 * @param m associates the model class to run the moveTiles() method.
	 * @param x value associated with the column of the button to be shifted.
	 * @param y value associated with the row of the button to be shifted.
	 */	
	public shiftListener(Model m,int x, int y){
		_model=m;
		col = x;
		row = y;
	}
	
	/**
	 * @author <jtmirfie>
	 * Runs the moveTiles() method whenever a button is clicked.
	 * @param e
	 */	
	@Override
	public void actionPerformed(ActionEvent e) {
		_model.moveTiles(col,row);
		
	}

}
