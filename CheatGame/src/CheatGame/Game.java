package CheatGame;

public abstract class Game {

	private String gameName;
	private Player[] players;

	public String getGameName() {
		return this.gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public Player[] getPlayers() {
		return this.players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	/**
	 * 
	 * @param givenName
	 */
	public Game(String givenName) {
		// TODO - implement Game.Game
		throw new UnsupportedOperationException();
	}

	public abstract void play();

	public void declareWinner() {
		// TODO - implement Game.declareWinner
		throw new UnsupportedOperationException();
	}

}