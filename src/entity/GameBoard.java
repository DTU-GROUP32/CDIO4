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
	 * Default constructor, which initializes gameboard with 22 fields
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

	public Field[] getPropertyGroup(int propertyGroup) {
		// numbers of fields in group
		int fieldsInGroup = 0;
		for(Field field : this.fields)
		{
			if(field.getPropertyGroup() == propertyGroup)
				fieldsInGroup++;
		}

		// making property group
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

	public boolean evalPropertyGroupSameOwner(Field[] propertyGroup) {
		boolean ownedBySame;
		if(propertyGroup.length == 2)
			ownedBySame = propertyGroup[0].getOwner() == propertyGroup[1].getOwner();
		else ownedBySame = propertyGroup[0].getOwner() == propertyGroup[1].getOwner() && propertyGroup[0].getOwner() == propertyGroup[2].getOwner();

		return ownedBySame;
	}

	public ArrayList<Field> getPropertyList(Player owner) {

		ArrayList<Field> listOfProperties = new ArrayList<Field>();

		for(int i = 0; i < this.fields.length; i++) {
			if(this.fields[i].getOwner() == owner)
				listOfProperties.add(this.fields[i]);		
		}

		return listOfProperties;
	}

	public ArrayList<Field> getBuildableList(Player owner) {

		ArrayList<Field> listOfBuildableProperties = new ArrayList<Field>();

		for(int i = 0; i < 8; i++) { // For every property group
			Field[] propertyGroup = getPropertyGroup(i);

			if(propertyGroup[0].getOwner() == owner && evalPropertyGroupSameOwner(propertyGroup)) // If the owner of the fields is the "input"-owner
			{
				int smallestConstructionRate = 0;

				// Find the smallest construction rate of property group
				for(Field field : propertyGroup) {
					if(field.getConstructionRate() < smallestConstructionRate)
						smallestConstructionRate = field.getConstructionRate();
				}

				// If construction rate is equal to the smallest construction rate, then add to the buildable list.
				for(int j = 0; j < propertyGroup.length; j++)
				{
					if(propertyGroup[j].getConstructionRate() == smallestConstructionRate)
						listOfBuildableProperties.add(propertyGroup[j]);
				}
			}
		}

		return listOfBuildableProperties;
	}

	public ArrayList<Field> getDemolitionableList(Player owner) {

		ArrayList<Field> listOfSellableProperties = new ArrayList<Field>();

		for(int i = 0; i < 8; i++) { // For every property group
			Field[] propertyGroup = getPropertyGroup(i);

			if(propertyGroup[0].getOwner() == owner) // If the owner of the fields is the "input"-owner
			{
				int highestConstructionRate = 0;

				// Determine the highest construction rate
				for(Field field : propertyGroup)
					if(field.getConstructionRate() > highestConstructionRate)
						highestConstructionRate = field.getConstructionRate();

				// If construction rate is equal to the highest construction rate, then add to the sellable list.
				if(highestConstructionRate > 0)
				{
					for(int j = 0; j < propertyGroup.length; j++)
					{
						if(propertyGroup[j].getConstructionRate() == highestConstructionRate)
							listOfSellableProperties.add(propertyGroup[j]);
					}
				}
			}
		}

		return listOfSellableProperties;
	}

	public ArrayList<Field> getPawnableList(Player owner) {

		ArrayList<Field> listOfPawnableProperties = new ArrayList<Field>();

		for(int i = 0; i < this.fields.length; i++)
			if(fields[i].getOwner() == owner && fields[i].getConstructionRate() == 0)
				listOfPawnableProperties.add(fields[i]);

		return	listOfPawnableProperties;
	}

	public ArrayList<Field> getAlreadyPawnedList(Player owner) {

		ArrayList<Field> listOfAlreadyPawnedProperties = new ArrayList<Field>();

		for(int i = 0; i < this.fields.length; i++)
			if(fields[i].getOwner() == owner && fields[i].getIsPawned())
				listOfAlreadyPawnedProperties.add(fields[i]);

		return	listOfAlreadyPawnedProperties;
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
	 * Returns field index by name. Returns 0 if not found.
	 * @param name
	 * @return
	 */
	public int getIndexByName(String name){
		for (Field field : this.fields){
			if(name.equals(field.getName())){
				return field.getID();
			}
		}
		return 0;
	}

	/**
	 * Removes ownership of every field a player owns
	 * @param player
	 */
	public void releasePlayersFields(Player player) {
		for(int i = 0; i < fields.length; i++) {
			if (fields[i] instanceof Ownable) {
				if(fields[i].getOwner().getName().equals(player.getName())) {
					fields[i].setOwner(null);
					fields[i].setConstructionRate(0);
					fields[i].releasePawnField();

				}
			}
		}
	}
}