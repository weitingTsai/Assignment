
public class Manager extends Employee {
	private double bonusRate;

	public Manager(String name, int wage, double bonusRate) {
		super(name, wage);
		this.bonusRate = bonusRate;
	}

	public int payment() {
		return (int)((super.getWage() * super.getWorkDay() + (int) (super.getOverWork() * (int) (super.getWage() / 8) * 1.5)) * bonusRate);
	}
}

