package Items;


public class EnergyBoostItem extends Item
{
	private static final long serialVersionUID = -4200242481087143628L;

	public EnergyBoostItem(String name, String description, boolean isVisible, boolean isPickedUp) 
	{
		super(name, description, isVisible, isPickedUp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void use() 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isEnergyBoost() {
		return true;
	}
}
