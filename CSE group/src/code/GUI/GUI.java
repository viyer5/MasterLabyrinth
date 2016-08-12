package code.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import code.Model.Model;
import code.Model.Observer;

public class  GUI implements Runnable, Observer {
	private JPanel _boardPanel;
	private JPanel _gamePanel;
	private JPanel _offGamePanel;
	
	private JPanel _westBPanel;
	private JPanel _northBPanel;
	private JPanel _eastBPanel;
	private JPanel _southBPanel;
	
	private JButton _rotateButton;
	private JButton _endTurn;
	private JPanel _rotatePanel;
	private JButton _player;
	
	private JPanel _holdPanel;
	private JFrame _gameFrame;
	private Model _model;
	
	private keyMovement key;
	
	/**
	 * @author <jtmirfie>
	 * Constructor
	 * Associates the Model class with this one and sets up the observer that to notify the model when to update.
	 * @param m Associated between this class and the model to get the information needed to display. 
	 */	
	public GUI(Model m){
		_model = m;
		_model.setObserver(this);
	}

	/**
	 * @author <jtmirfie>
	 * Runs at the beginning of the code. JFrame is compiled and the setupBoard() method is called onto it.
	 * Various panels are placed on the frame which hold the tiles and the buttons around the board.
	 * A key listener is also implemented which is used for pawn movement respective to each players turn.
	 */	
	@Override
	public void run() {
		
		_gameFrame = new JFrame();
		_gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_gameFrame.getContentPane().setLayout(new FlowLayout());
		_gameFrame.setFocusable(true);
		_gameFrame.setResizable(false);
		_gameFrame.setLayout(new BorderLayout());
		setupBoard();
		
		_gamePanel = new JPanel();
		_gamePanel.setFocusable(true);
		_gamePanel.setLayout(new BorderLayout());
		
		_gamePanel.add(_westBPanel,BorderLayout.WEST);
		_gamePanel.add(_northBPanel,BorderLayout.NORTH);
		_gamePanel.add(_eastBPanel,BorderLayout.EAST);
		_gamePanel.add(_southBPanel,BorderLayout.SOUTH);
		
		_gamePanel.add(_boardPanel,BorderLayout.CENTER);

		_offGamePanel = new JPanel();
		_offGamePanel.setFocusable(true);
		_offGamePanel.setLayout(new GridLayout(6,1));
		
		_holdPanel = new TilesUI(_model.getHoldTile(),_model,10,10).returnPanel();
		_holdPanel.setFocusable(true);
		_offGamePanel.add(_holdPanel);
		
		_rotatePanel= new JPanel();
		_rotatePanel.setFocusable(true);
		_rotatePanel.setLayout(new GridLayout(3,1));
		
		_rotateButton = new JButton("Rotate");
		_rotateButton.addActionListener(new rotateListener(_model,_model.getHoldTile()._rotation));
		_rotateButton.setFocusable(false);
		_endTurn = new JButton("End Turn");
		_endTurn.setFocusable(false);
		_endTurn.addActionListener(new endTurnListener(_model));
		_player = new JButton(_model.pawns[0].getPlayer());
		_player.setOpaque(true);
		_player.setBackground(Color.orange);
		_rotatePanel.add(_rotateButton);
		_rotatePanel.add(_endTurn);
		_offGamePanel.add(_rotatePanel);
		_offGamePanel.add(_player);
		
		key = new keyMovement(_model,_model.pawns[_model.playerUp-1]);
		_gameFrame.addKeyListener(key);
		_gameFrame.getContentPane().add(_gamePanel,BorderLayout.WEST);
		_gameFrame.getContentPane().add(_offGamePanel,BorderLayout.EAST);
		_gameFrame.pack();
		_gameFrame.setVisible(true);

		
	}
		
	/**
	 * @author <jtmirfie>
	 * Draws onto the JFrame of the board setting up all the tiles and other panels that are placed on the board.
	 * The buttons to shift the board are also implemented with their actionlisteners.
	 */	
		public void setupBoard(){
			_boardPanel = new JPanel();
			_boardPanel.setFocusable(true);
			_boardPanel.setLayout(new GridLayout(7,7));
			for (int a = 0; a<7;a++){			
				for (int b = 0; b<7;b++){
				
					_boardPanel.add(new TilesUI(_model.getTile(b, a),_model,b,a).returnPanel());
			}
		}
			
			
			JButton westB1 = new JButton(">");
			westB1.addActionListener(new shiftListener(_model,0,1));
			westB1.setFocusable(false);
			JButton westB2 = new JButton(">");
			westB2.addActionListener(new shiftListener(_model,0,3));
			westB2.setFocusable(false);
			JButton westB3 = new JButton(">");
			westB3.addActionListener(new shiftListener(_model,0,5));
			westB3.setFocusable(false);
			
			JButton northB1 = new JButton("V");
			northB1.addActionListener(new shiftListener(_model,1,0));
			northB1.setFocusable(false);
			JButton northB2 = new JButton("V");
			northB2.addActionListener(new shiftListener(_model,3,0));
			northB2.setFocusable(false);
			JButton northB3 = new JButton("V");
			northB3.addActionListener(new shiftListener(_model,5,0));
			northB3.setFocusable(false);
			
			JButton eastB1 = new JButton("<");
			eastB1.addActionListener(new shiftListener(_model,6,1));
			eastB1.setFocusable(false);
			JButton eastB2 = new JButton("<");
			eastB2.addActionListener(new shiftListener(_model,6,3));
			eastB2.setFocusable(false);
			JButton eastB3 = new JButton("<");
			eastB3.addActionListener(new shiftListener(_model,6,5));
			eastB3.setFocusable(false);
			
			JButton southB1 = new JButton("Λ");
			southB1.addActionListener(new shiftListener(_model,1,6));
			southB1.setFocusable(false);
			JButton southB2 = new JButton("Λ");
			southB2.addActionListener(new shiftListener(_model,3,6));
			southB2.setFocusable(false);
			JButton southB3 = new JButton("Λ");
			southB3.addActionListener(new shiftListener(_model,5,6));
			southB3.setFocusable(false);
			
			_westBPanel = new JPanel();
			_westBPanel.setFocusable(true);
			_westBPanel.setLayout(new GridLayout(7,1));
			
			_westBPanel.add(new JPanel());
			_westBPanel.add(westB1);
			_westBPanel.add(new JPanel());
			_westBPanel.add(westB2);
			_westBPanel.add(new JPanel());
			_westBPanel.add(westB3);
			_westBPanel.add(new JPanel());
			
			_northBPanel = new JPanel();
			_northBPanel.setFocusable(true);
			_northBPanel.setLayout(new GridLayout(1,7));
			
			_northBPanel.add(new JPanel());
			_northBPanel.add(northB1);
			_northBPanel.add(new JPanel());
			_northBPanel.add(northB2);
			_northBPanel.add(new JPanel());
			_northBPanel.add(northB3);
			_northBPanel.add(new JPanel());
			
			_eastBPanel = new JPanel();
			_eastBPanel.setFocusable(true);
			_eastBPanel.setLayout(new GridLayout(7,1));
			
			_eastBPanel.add(new JPanel());
			_eastBPanel.add(eastB1);
			_eastBPanel.add(new JPanel());
			_eastBPanel.add(eastB2);
			_eastBPanel.add(new JPanel());
			_eastBPanel.add(eastB3);
			_eastBPanel.add(new JPanel());
			
			_southBPanel = new JPanel();
			_southBPanel.setFocusable(true);
			_southBPanel.setLayout(new GridLayout(1,7));
			
			_southBPanel.add(new JPanel());
			_southBPanel.add(southB1);
			_southBPanel.add(new JPanel());
			_southBPanel.add(southB2);
			_southBPanel.add(new JPanel());
			_southBPanel.add(southB3);
			_southBPanel.add(new JPanel());
			

}
		/**
		 * @author <jtmirfie>
		 * Runs whenever a change is made to the model and redraws all of the modifications.
		 * All of the panels are removed and it redraws the entire board according to the new model.
		 */	
		@Override public void update() {
			_gameFrame.removeKeyListener(key);
			
			_gamePanel.remove(_boardPanel);
			
			_offGamePanel.remove(_rotatePanel);
			_offGamePanel.remove(_holdPanel);
			_offGamePanel.remove(_player);
			_player = new JButton(_model.pawns[_model.playerUp-1].getPlayer());
			_player.setOpaque(true);
			if(_model.playerUp==1)_player.setBackground(Color.orange);
			if(_model.playerUp==2)_player.setBackground(Color.red);
			if(_model.playerUp==3)_player.setBackground(Color.blue);
			if(_model.playerUp==4)_player.setBackground(Color.green);
			_rotatePanel.remove(_rotateButton);
			_rotatePanel.remove(_endTurn);
			
			_holdPanel = new TilesUI(_model.getHoldTile(),_model,10,10).returnPanel();
			_holdPanel.setFocusable(true);
			_boardPanel = new JPanel();
			_boardPanel.setFocusable(true);
			_boardPanel.setLayout(new GridLayout(7,7));
			for (int a = 0; a<7;a++){			
				for (int b = 0; b<7;b++){
				
					_boardPanel.add(new TilesUI(_model.getTile(b, a),_model,b,a).returnPanel());
			}
		}
			_gamePanel.add(_boardPanel,BorderLayout.CENTER);
			_rotateButton = new JButton("Rotate");
			_rotateButton.addActionListener(new rotateListener(_model,_model.getHoldTile()._rotation));
			_rotateButton.setFocusable(false);
			_endTurn = new JButton("End Turn");
			_endTurn.addActionListener(new endTurnListener(_model));
			_endTurn.setFocusable(false);
			_rotatePanel.add(_rotateButton);
			_rotatePanel.add(_endTurn);
			_offGamePanel.add(_holdPanel);
			_offGamePanel.add(_rotatePanel);
			_offGamePanel.add(_player);
			key = new keyMovement(_model,_model.pawns[_model.playerUp-1]);
			_gameFrame.addKeyListener(key);
			_gameFrame.pack();
			_gameFrame.repaint();	
			
		}
}