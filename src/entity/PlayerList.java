package entity;

public class PlayerList {

	private Player[] players;

	/**
	 * Creates an empty array of player objects.
	 * @param numberOfPlayers - Number of players.
	 */
	public PlayerList(int numberOfPlayers) {
		this.players = new Player[numberOfPlayers];
		for (int i = 0; i < numberOfPlayers; i++)
			players[i] = new Player();
	}

	/**
	 * Instantiates a player object in the array at the index specified.
	 * @param index - Index of the player to be instantiated.
	 * @param playerName - Name of the player to be instantiated.
	 */
	public void addPlayer(int index, String playerName) {
		this.players[index].setName(playerName);
	}

	/**
	 * Returns boolean to determine if players balance is 0 or below
	 * @param atIndex Index of the player
	 * @return Boolean
	 */
	public boolean isPlayerBroke(int atIndex) {
		return players[atIndex].getBankAccount().getBalance() <= 0;
	}

	/**
	 * Checks all players to see if every player, but one is broke. If so true is returned
	 * @return Boolean
	 */
	public boolean isThereAWinner() {
		int brokePlayers = 0;
		for (int i = 0; i < players.length; i++)
			if (players[i].getBankAccount().getBalance() <= 0)
				brokePlayers++;
		return brokePlayers == players.length - 1;
	}

	/**
	 * Returns the player, with a balance above 0, who won
	 * @return Player
	 */
	public Player whoIsTheWinner() {
		Player winner = new Player();
		for (int i = 0; i < players.length; i++)
			if (players[i].getBankAccount().getBalance() > 0)
				winner = players[i];
		return winner;
	}

	/**
	 * Returns an array of players in the game
	 * @return Player[]
	 */
	public Player[] getPlayers() {
		return players;
	}

	/**
	 * Returns specific player in the array, determined by index
	 * @param atIndex
	 * @return Player
	 */
	public Player getPlayer(int atIndex) {
		return players[atIndex];
	}
	
	/**
	 * Checks if the name is already taken
	 * @param name
	 * @return Boolean
	 */
	public boolean isNameTaken(String name) {
		boolean answer = false;
		for(Player player: this.players)
			if(player.getName().equalsIgnoreCase(name))
				answer = true;
		return answer;
	}
}
