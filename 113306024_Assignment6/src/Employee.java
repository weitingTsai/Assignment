
public class Employee {
	private String name;
	private int wage;
	private int workDay;
	private int overWork;

	public Employee(String name, int wage) {
		this.name = name;
		this.wage = wage;
	}

	public int getWage() {
		return wage;
	}

	public String getName() {
		return name;
	}

	public int getWorkDay() {
		return workDay;
	}

	public int getOverWork() {
		return overWork;
	}

	public void addWork(int hour) {
		if (hour <= 8) {
			workDay += 1;
		} else {
			workDay += 1;
			overWork += hour - 8;
		}
	}

	public int payment() {
		return wage * workDay + (int) (overWork * (int) (wage / 8) * 1.5);
	}
}
