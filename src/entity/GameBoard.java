package entity;

import java.util.ArrayList;

import entity.fields.Brewery;
import entity.fields.Chance;
import entity.fields.Field;
import entity.fields.Jail;
import entity.fields.Ownable;
import entity.fields.Plot;
import entity.fields.Refuge;
import entity.fields.ShippingLine;
import entity.fields.Tax;
import entity.language.LanguageHandler;

public class GameBoard {

	private Field[] fields;

	/**
	 * Constructor that sets up a full "Matador from the 1980's" game board in the logic with all the data for the fields.
	 * @param language
	 */
	public GameBoard(LanguageHandler language) {
		this.fields = new Field[40];
		fields[0] = new Refuge(language.fieldNames(0), 0); // start
		fields[1] = new Plot(language.fieldNames(1), 1200, new int[] {50,250,750,2250,4000,6000}, 1000, 0);
		fields[2] = new Chance(language.fieldNames(2));
		fields[3] = new Plot(language.fieldNames(3), 1200, new int[] {50,250,750,2250,4000,6000}, 1000, 0);
		fields[4] = new Tax(language.fieldNames(4), 4000, 10);
		fields[5] = new ShippingLine(language.fieldNames(5));
		fields[6] = new Plot(language.fieldNames(6), 2000, new int[] {100,600,1800,5400,8000,11000}, 1000, 1);
		fields[7] = new Chance(language.fieldNames(7));
		fields[8] = new Plot(language.fieldNames(8), 2000, new int[] {100,600,1800,5400,8000,11000}, 1000, 1);
		fields[9] = new Plot(language.fieldNames(9), 2400, new int[] {150,800,2000,6000,9000,12000}, 1000, 1);
		fields[10] = new Refuge(language.fieldNames(10), 0);
		fields[11] = new Plot(language.fieldNames(11), 2800, new int[] {200,1000,3000,9000,12500,15000}, 2000, 2);
		fields[12] = new Brewery(language.fieldNames(12));
		fields[13] = new Plot(language.fieldNames(13), 2800, new int[] {200,1000,3000,9000,12500,15000}, 2000, 2);
		fields[14] = new Plot(language.fieldNames(14), 3200, new int[] {250,1250,3750,10000,14000,18000}, 2000, 2);
		fields[15] = new ShippingLine(language.fieldNames(15));
		fields[16] = new Plot(language.fieldNames(16), 3600, new int[] {300,1400,4000,11000,15000,19000}, 2000, 3);
		fields[17] = new Chance(language.fieldNames(17));
		fields[18] = new Plot(language.fieldNames(18), 3600, new int[] {300,1400,4000,11000,15000,19000}, 2000, 3);
		fields[19] = new Plot(language.fieldNames(19), 4000, new int[] {350,1600,4400,12000,16000,20000}, 2000, 3);
		fields[20] = new Refuge(language.fieldNames(20), 5000);
		fields[21] = new Plot(language.fieldNames(21), 4400, new int[] {350,1800,5000,14000,17500,21000}, 3000, 4);
		fields[22] = new Chance(language.fieldNames(22));
		fields[23] = new Plot(language.fieldNames(23), 4400, new int[] {350,1800,5000,14000,17500,21000}, 3000, 4);
		fields[24] = new Plot(language.fieldNames(24), 4800, new int[] {400,2000,6000,15000,18500,22000}, 3000, 4);
		fields[25] = new ShippingLine(language.fieldNames(25));
		fields[26] = new Plot(language.fieldNames(26), 5200, new int[] {450,2200,6600,16000,19500,23000}, 3000, 5);
		fields[27] = new Plot(language.fieldNames(27), 5200, new int[] {450,2200,6600,16000,19500,23000}, 3000, 5);
		fields[28] = new Brewery(language.fieldNames(28));
		fields[29] = new Plot(language.fieldNames(29), 5600, new int[] {500,2400,7200,17000,20500,24000}, 3000, 5);
		fields[30] = new Jail(language.fieldNames(30));
		fields[31] = new Plot(language.fieldNames(31), 6000, new int[] {550,2600,7800,18000,22000,25000}, 4000, 6);
		fields[32] = new Plot(language.fieldNames(32), 6000, new int[] {550,2600,7800,18000,22000,25000}, 4000, 6);
		fields[33] = new Chance(language.fieldNames(33));
		fields[34] = new Plot(language.fieldNames(34), 6400, new int[] {600,3000,9000,20000,24000,28000}, 4000, 6);
		fields[35] = new ShippingLine(language.fieldNames(35));
		fields[36] = new Chance(language.fieldNames(36));
		fields[37] = new Plot(language.fieldNames(37), 7000, new int[] {700,3500,10000,22000,26000,30000}, 4000, 7);
		fields[38] = new Tax(language.fieldNames(38), 2000);
		fields[39] = new Plot(language.fieldNames(39), 8000, new int[] {1000,4000,12000,28000,34000,40000}, 4000, 7);
	}

	/**
	 * Returns an array with the fields in the property group, that is specified by the input parameter.
	 * @param propertyGroup
	 * @return fieldGroup
	 */
	public Field[] getPropertyGroup(int propertyGroup) {

		// counts how many fields are in the group
		int fieldsInGroup = 0;
		for(Field field : this.fields)
		{
			if(field.getPropertyGroup() == propertyGroup)
				fieldsInGroup++;
		}

		// creates an array containing the fields in the specified property group
		Field[] fieldGroup = new Field[fieldsInGroup];
		int arrayIndex = 0;
		for(int i = 0; i < this.fields.length; i++)
		{
			if(this.fields[i].getPropertyGroup() == propertyGroup)
			{
				fieldGroup[arrayIndex] = this.fields[i];
				arrayIndex++;
			}
		}

		return fieldGroup;
	}

	/**
	 * Evaluates all the properties in a property group has the same owner.
	 * @param propertyGroup
	 * @return
	 */
	public boolean evalPropertyGroupSameOwner(Field[] propertyGroup) {
		// if the property group has two properties
		if(propertyGroup.length == 2) {
			// if the owner is the same return true
			if(propertyGroup[0].getOwner() == propertyGroup[1].getOwner()) {
				return true;
			} else {
				return false;
			}
		} else {
			// if the owner of the first property is the same as the second and third property
			if(propertyGroup[0].getOwner() == propertyGroup[1].getOwner() && propertyGroup[0].getOwner() == propertyGroup[2].getOwner()) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * Returns a list of all the properties owned by the specified player.
	 * @param owner
	 * @return
	 */
	public ArrayList<Field> getPropertyList(Player owner) {

		ArrayList<Field> listOfProperties = new ArrayList<Field>();

		// adds every field that has the specified owner to the list
		for(int i = 0; i < this.fields.length; i++) {
			if(this.fields[i].getOwner() == owner)
				listOfProperties.add(this.fields[i]);		
		}

		return listOfProperties;
	}

	/**
	 * Returns a list of all the properties owned by the specified player, that doesn't have any buildings on them.
	 * @param owner
	 * @return
	 */
	public ArrayList<Field> getTradeableList(Player owner) {

		ArrayList<Field> listOfTradeableProperties = new ArrayList<Field>();

		// adds every field that has the specified owner and no constructions to the list
		for(int i = 0; i < this.fields.length; i++) {
			if(this.fields[i].getOwner() == owner && this.fields[i].getConstructionRate() == 0)
				listOfTradeableProperties.add(this.fields[i]);		
		}

		return listOfTradeableProperties;
	}

	/**
	 * Returns a list of all the fields that the player can currently build constructions on.
	 * @param owner
	 * @return
	 */
	public ArrayList<Field> getBuildableList(Player owner) {

		ArrayList<Field> listOfBuildableProperties = new ArrayList<Field>();

		// for every property group(0-7)
		for(int i = 0; i < 8; i++) {
			Field[] propertyGroup = getPropertyGroup(i);

			// if the owner of the first field in the group is the "input"-owner, and all properties in the group has the same owner
			if(propertyGroup[0].getOwner() == owner && evalPropertyGroupSameOwner(propertyGroup)) {

				// setting this to 4, ensures that a field with construction rate 5 won't get on the list
				int smallestConstructionRate = 4;

				// finds the smallest construction rate of property group
				for(Field field : propertyGroup) {
					if(field.getConstructionRate() < smallestConstructionRate) {
						smallestConstructionRate = field.getConstructionRate();
					}
				}

				for(int j = 0; j < propertyGroup.length; j++) {
					// if construction rate is equal to the smallest construction rate, then add to the buildable list
					// this is to ensure the player is building evenly on the properties
					if(propertyGroup[j].getConstructionRate() == smallestConstructionRate && !propertyGroup[j].isPawned()) {
						listOfBuildableProperties.add(propertyGroup[j]);
					}
				}
			}
		}

		return listOfBuildableProperties;
	}

	/**
	 * Returns a list of all the fields that the player can currently demolish constructions on.
	 * @param owner
	 * @return
	 */
	public ArrayList<Field> getDemolitionableList(Player owner) {

		ArrayList<Field> listOfSellableProperties = new ArrayList<Field>();

		// for every property group(0-7)
		for(int i = 0; i < 8; i++) {
			Field[] propertyGroup = getPropertyGroup(i);

			// if the owner of the first field in the group is the "input"-owner, and all properties in the group has the same owner
			if(propertyGroup[0].getOwner() == owner) {

				int highestConstructionRate = 0;

				// finds the highest construction rate of property group
				for(Field field : propertyGroup) {
					if(field.getConstructionRate() > highestConstructionRate) {
						highestConstructionRate = field.getConstructionRate();
					}
				}

				// ensures fields with construction rate 0 won't get on the list
				if(highestConstructionRate > 0) {
					for(int j = 0; j < propertyGroup.length; j++) {
						// if construction rate is equal to the highest construction rate, then add to the demolitionable list
						// this is to ensure the player is building evenly on the properties
						if(propertyGroup[j].getConstructionRate() == highestConstructionRate) {
							listOfSellableProperties.add(propertyGroup[j]);
						}
					}
				}
			}
		}

		return listOfSellableProperties;
	}

	/**
	 * Returns a list of all the fields that the player can pawn.
	 * @param owner
	 * @return
	 */
	public ArrayList<Field> getPawnableList(Player owner) {

		ArrayList<Field> listOfPawnableProperties = new ArrayList<Field>();

		// for each field
		for(int i = 0; i < this.fields.length; i++) {
			// if the owner of the field is the specified owner and there are no buildings on the field and the field isn't already pawned
			if(fields[i].getOwner() == owner && fields[i].getConstructionRate() == 0 && fields[i].isPawned() == false) {
				listOfPawnableProperties.add(fields[i]);
			}
		}

		return	listOfPawnableProperties;
	}

	/**
	 * Returns a list of all the fields that are already pawned.
	 * @param owner
	 * @return
	 */
	public ArrayList<Field> getAlreadyPawnedList(Player owner) {

		ArrayList<Field> listOfAlreadyPawnedProperties = new ArrayList<Field>();

		// for each field
		for(int i = 0; i < this.fields.length; i++) {
			// if the owner of the field is the specified owner and the field is pawned
			if(fields[i].getOwner() == owner && fields[i].isPawned()) {
				listOfAlreadyPawnedProperties.add(fields[i]);
			}
		}

		return	listOfAlreadyPawnedProperties;
	}

	/**
	 * Returns an array with on the fields on the game board.
	 * @return
	 */
	public Field[] getFields() {
		return fields;
	}

	/**
	 * Returns a specific field by index or field ID(they are the same numbers).
	 * @param atIndex
	 * @return Field
	 */
	public Field getField(int atIndex) {
		return fields[atIndex];
	}

	/**
	 * Returns field index by name. Returns -1 if not found.
	 * @param name
	 * @return
	 */
	public int getIndexByName(String name){
		for (Field field : this.fields){
			if(name.equals(field.getName())){
				return field.getID();
			}
		}
		return -1;
	}

	/**
	 * Returns all of a players fields to the bank and resets the fields. Used for bankruptcy.
	 * @param player
	 */
	public void releasePlayersFields(Player player) {
		// for each field
		for(int i = 0; i < fields.length; i++) {
			// if it's ownable
			if (fields[i] instanceof Ownable) {
				// if the fields owner is the specified player
				if(fields[i].getOwner() == player) {
					// reset owner, construction rate and pawn status
					fields[i].setOwner(null);
					fields[i].setConstructionRate(0);
					fields[i].releasePawnField();

				}
			}
		}
	}
}