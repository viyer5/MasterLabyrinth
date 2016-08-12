package code.Tiles;

import code.Tokens.Token;

/**
 * Class is the parent class for all the Tile classes in the game. File is fully defined with the exception of
 * the rotation method, which is unique to each tile and must be written by each tile. Class is
 * abstract so that it cannot be called.
 * @author Wiechec
 *
 */

	public abstract class Tiles {
	
	public String _rotation;
	/**
	 * @author Wiechec
	 * _staticVert: Field for flag indicating whether or not the specific tile can be move vertically.
	 * Field is off as false and on to prevent movement as True.
	 */
	public Boolean _staticVert = false;
	/**
	 * @author Wiechec
	 *  _staticHoriz: Field for flag indicating whether or not the specific tile can be move horizontally.
	 * Field is off as false and on to prevent movement as True.
	 */
	public Boolean _staticHoriz = false;
	/**
	 * @author Wiechec
	 * _north: Field storing whether or not the tile allows movement in the north direction.
	 */

	public Boolean _north = false;
	/**
	 * @author Wiechec
	 *  _east: Field storing whether or not the tile allows movement in the east direction.
	 */
	
	public Boolean _east = false;
	/**
	 * @author Wiechec
	 * _south: Field storing whether or not the tile allows movement in the south direction.
	 */
	public Boolean _south = false;
	/**
	 * @author Wiechec
	 * _west: Field storing whether or not the tile allows movement in the west direction.
	 */
	public Boolean _west = false;
	/**
	 * @author Wiechec
	 *_permanency: Field for checking if the tile piece is a permanent tile.
	 */
	public Boolean _permanency=false;
	
	public Token token;
	
	public Boolean hasToken = false;
	
	public Boolean player = false;
	
	public boolean isStart = false;

	/**
	 * @author Wiechec
	 * method getRotation();
	 * returns the current rotation of the object.
	 * @return: Current rotation of the object.
	 */
	public String getRotation(){
		return _rotation;
	}
	

	/**
	 * @author Wiechec
	 * method Rotate();
	 * abstract method stub. Method is used to rotate the directional fields on the tiles.
	 * code is the responsibility of the subclasses.
	 * @return: no return.
	 */
	public abstract void Rotate(String direction);

	/**
	 * @author Wiechec
	 * Method turns on or off the flag indicator for whether or not the tile can be moved 
	 * north or south. Method first checks if the tile's field has _permanency on, which would
	 * indicate the tile is a permanent structure. Method returns println string if _permanency
	 * is true. else statement sets the _staticVert to parameter value.
	 * @param freeze		Boolean value for setting _staticVert field to true or false.
	 */
	public void setVert(Boolean freeze){
		if (_permanency = true){
			System.out.println("I'm sorry, that's a permanent structure");
		}else
			_staticVert = freeze;
	}
	/**
	 * @author Wiechec
	 * Method turns on or off the flag indicator for whether or not the tile can be moved 
	 * east or west. Method first checks if the tile's field has _permanency on, which would
	 * indicate the tile is a permanent structure. Method returns println string if _permanency
	 * is true. else statement sets the _staticVert to parameter value.
	 * @param freeze		Boolean value for setting _staticVert field to true or false.
	 */
	public void setHoriz(Boolean freeze){
		if (_permanency = true){
			System.out.println("I'm sorry, that's a permanent structure");
		}else
			_staticHoriz = freeze;
	}
	/**
	 * @author Wiechec
	 * Method returns the value of the tile's _staticVert field.
	 * @return _staticVert field value
	 */
	public Boolean getVert(){
		return _staticVert;
		
	}
	/**
	 * @author Wiechec
	 * Method returns the value of the tile's _staticHoriz field.
	 * @return _staticHoriz field value
	 */
	public Boolean getHoriz(){
		return _staticHoriz;
	}
	/**
	 * @author Wiechec
	 * This method sets a tile to indicate a permanent structure. _staticVert and _staticHoriz fields
	 * also set to the same value as a matter of preventing any runtime errors.
	 * @param value: Field for setting _permanency, _staticVert, and _staticHoriz fields with one method.
	 */
	public void isPermanent(Boolean value){
		_permanency = value;
		_staticVert = value;
		_staticHoriz = value;

	}
	/**
	 * @author Wiechec
	 * Method returns the boolean values of the tile for the indicated direction of the parameter.
	 * Method returns false as a default if an incorrect value is entered into parameter.
	 * @param dir is the direction being checked
	 * @return the value of the direction being checked.
	 */
	public Boolean getDirection(String dir){
		switch(dir){
		case "North":
			return _north;
		case "South":
			return _south;
		case "East":
			return _east;
		case "West":
			return _west;
		}
		return false;
		
	}
	public void setToken(Token _token){
		token = _token;
	}
	public Token getToken(){
		return token;
	}
	public boolean checkToken(){
		return hasToken;
	}
	public Token pickUpToken(){
		Token temp=token;
		token=null;
		return temp;
	}
	public void startTile(){
		isStart=true;
	}
	public boolean isStart(){
		return isStart;
	}

}


