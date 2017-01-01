package entity;

import entity.fields.Field;
import entity.fields.Fleet;
import entity.fields.LaborCamp;
import entity.fields.Refuge;
import entity.fields.Tax;
import entity.fields.Territory;

public class GameBoard {

	private Field[] fields;

	/**
	 * Default constructor, which initializes gameboard with 22 fields
	 */
	public GameBoard() {
		this.fields = new Field[22];
		fields[0] = new Refuge(0); // start
		fields[1] = new Territory(1000, 100);
		fields[2] = new Fleet();
		fields[3] = new Territory(1500, 300);
		fields[4] = new LaborCamp();
		fields[5] = new Territory(2000, 500);
		fields[6] = new Refuge(500);
		fields[7] = new Territory(3000, 700);
		fields[8] = new Fleet();
		fields[9] = new Territory(4000, 1000);
		fields[10] = new Tax(2000);
		fields[11] = new Territory(4300, 1300);
		fields[12] = new LaborCamp();
		fields[13] = new Territory(4750, 1600);
		fields[14] = new Fleet();
		fields[15] = new Territory(5000, 2000);
		fields[16] = new Refuge(5000);
		fields[17] = new Territory(5500, 2600);
		fields[18] = new Tax(4000, 10);
		fields[19] = new Territory(6000, 3200);
		fields[20] = new Fleet();
		fields[21] = new Territory(8000, 4000);
	}

	/**
	 * Returns array of fields
	 * @return Field[]
	 */
	public Field[] getFields() {
		return fields;
	}

	/**
	 * Returns specific field in the array at index
	 * @param atIndex
	 * @return Field
	 */
	public Field getField(int atIndex) {
		return fields[atIndex];
	}

	/**
	 * Removes ownership of every field a player owns
	 * @param player
	 */
	public void releasePlayersFields(Player player) {
		for(int i = 0; i < fields.length; i++)
			if(fields[i].isOwnable())
				if(fields[i].getOwner() != null)
					if(getField(i).getOwner().getName().equals(player.getName()))
						fields[i].setOwner(null);
	}
}
