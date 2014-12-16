package Items;


public class FightingItem extends Item
{
	private static final long serialVersionUID = -4379094510571054869L;
	private boolean isUsedUp;
	public FightingItem(String name, String description, boolean isVisible, boolean isPickedUp, boolean isUsedUp)
	{
		super(name, description,isVisible, isPickedUp);
		this.isUsedUp = isUsedUp;
	}
	
	public void fightWith(Item item)
	{
		isUsedUp = true;
	}

	@Override
	public void use() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnergyBoost() {
		// TODO Auto-generated method stub
		return false;
	}
}
