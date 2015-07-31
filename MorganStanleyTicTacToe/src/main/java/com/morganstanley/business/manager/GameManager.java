/*
* @author  Ninus Khamis (MSc)
 * @version 1.3
 * @since   2015-06-30 
 */ 
package com.morganstanley.business.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import com.morganstanley.business.RoundRobin;
import com.morganstanley.business.helper.MessageHelper;
import com.morganstanley.domain.user.IUser;
import com.morganstanley.domain.user.OUser;
import com.morganstanley.domain.user.XUser;

/**
* The following Manager specialization class is the Business Layer
* Logic or "Model" for the Tic Tack Toe Game 
*/ 
public class GameManager extends AManager {
	private char[][] tiles;	
	private boolean hasWinner;
	private boolean hasTie;
	private int count;
	private boolean gameOver;
	private List<IUser> players;
	private RoundRobin round;
	private GameManager gameManager;
	
	
	/**
	* Initializes a new board game 
	*/	
	public void initBoard() {		
		this.players = new ArrayList<IUser>();
		this.players.add(new XUser());
		this.players.add(new OUser());
		this.round = new RoundRobin(players);
		this.count = 0;
		this.gameOver = false;
		this.gameManager = new GameManager();		
		setSessionAttributes();		 
	}
	
	
	/**
	* Manages the current board game 
	*/
	public void updateBoard(){		
		boolean bForTest = Boolean.valueOf(this.httpRequest.getSession().getAttribute("bForTest").toString());
		
		if(Boolean.valueOf(this.httpRequest.getSession().getAttribute("newGame").toString())) initBoard();
		
		this.tiles = (char[][]) this.httpRequest.getSession().getAttribute("tiles");
		
		if(!Boolean.parseBoolean(this.httpRequest.getSession().getAttribute("gameOver").toString())) {
			for (int r=0; r<3; r++) {
				for (int c=0; c<3; c++) {
					if (this.httpRequest.getParameter("Position" + r + c) != null && tiles[r][c] == ' ')
						makeMove(r, c);
				}
			 }		
		}
		else {
			initBoard();
			deleteSessionAttributes();
		}
				
		
		try {
			if(!bForTest)
				forward("/WEB-INF/jsp/tictactoe.jsp");			
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
	/**
	* Manages the round robin tick 
	*/
	public void makeMove(int row, int column) {		
	    char token = getTurn();
	    
	    this.tiles[row][column] = token;
	    
	    String message = "User :" + token + " makes move to Row:" + row + " and Column: " + column;
	    setMessage(message);	    
	    
	    int count = Integer.valueOf(this.httpRequest.getSession().getAttribute("count").toString());
	    count++;	    
	    this.httpRequest.getSession().setAttribute("count",count);
	    
	    
	    if((hasWinner(token))) {	    	 
	    	 this.httpRequest.getSession().setAttribute("gameOver", true);
	    	 return;
	    }
	    
	    if((count == 9)) {
	    	message = "Tie Game";
			this.setMessage(message);
			this.httpRequest.getSession().setAttribute("gameOver", true);
			this.hasTie = true;
			return;
	    }
	}
	
	/**
	* Iterates through the multi-dimensional array looking
	* for a winner.  
	*/
	public boolean hasWinner(char token) {		
		for (int r=0; r<3; r++) {
			if (tiles[r][0]==token && tiles[r][1]==token && tiles[r][2] ==token) {
				String message = "Winner " + token;
				this.setMessage(message);
				hasWinner = true;
				return true;
			}
		}		
		
		for (int c=0; c<3; c++) {
			if (tiles[0][c]==token && tiles[1][c]==token && tiles[2][c]==token) {
				String message = "Winner " + token;
				this.setMessage(message);
				hasWinner = true;
				return true;
			}
	    }
		
	    if (tiles[0][0]==token && tiles[1][1]==token && tiles[2][2]==token) {
	    	String message = "Winner " + token;
			this.setMessage(message);
			hasWinner = true;
			return true;
	    }
	    
	    if (tiles[0][2]==token && tiles[1][1]==token && tiles[2][0]==token) {
	    	String message = "Winner " + token;
			this.setMessage(message);
			hasWinner = true;
	    	return true;
	    }
	    
	    hasWinner = false;
	    return false;
	}
	
	public char[][] getTiles() {
		return this.tiles;
	}
	
	public void setTiles(char[][] tiles) {
		this.tiles = tiles;		
	}	
	
	public char getTurn() {		
		return ((RoundRobin) this.httpRequest.getSession().getAttribute("round")).next().getUserToken().getValue();		
	}	
	
	public String getMessage() {
		return this.httpRequest.getSession().getAttribute("message").toString();
	}
	
	public void setMessage(String message) {
		this.msgHelper = (MessageHelper) this.httpRequest.getSession().getAttribute("msgHelper");
		this.msgHelper.appendMessage(message);	    	    
	}
	
	public boolean isGameOver() {
		return Boolean.valueOf(this.httpRequest.getSession().getAttribute("gameOver").toString());		
	}
	
	public boolean getHasWinner() {
		return this.hasWinner;		
	}
	
	public boolean getHasTie() {
		return this.hasTie;		
	}
	
	public void setSessionAttributes() {		
		this.httpRequest.getSession().setAttribute("gameManager", gameManager);
		this.httpRequest.getSession().setAttribute("round", round);
		this.httpRequest.getSession().setAttribute("count", count);
		this.httpRequest.getSession().setAttribute("gameOver", gameOver);
		this.httpRequest.getSession().setAttribute("msgHelper", msgHelper);
	}
	
	public void deleteSessionAttributes() {
		this.gameManager = null;
		this.msgHelper = null;
		this.setSessionAttributes();
	}	
}