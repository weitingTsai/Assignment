import java.util.ArrayList;

public class Company {
	private String name;
	private ArrayList<Employee> employees = new ArrayList<Employee>();

	public Company(String name) {
		this.name = name;
	}

	public void addE(Employee employee) {
		this.employees.add(employee);
	}

	public void addWork(String name, int hour) {
		for (Employee e : employees) {
			if (e.getName().equals(name)) {
				e.addWork(hour);
				return;
			}
		}
		System.out.println("The employee is not found.");
	}

	public void callA(Analyzer a) {
		a.addE(employees);
		a.getInfo();
	}

	public void getInfo() {
		System.out.printf("<Company: %s>\n%10s%10s%10s%10s%10s\n", name, "Name", "WorkDay", "OverTime", "Wage",
				"Title");
		for (Employee e : employees) {
			if (e instanceof Manager) {
				System.out.printf("%10s%10d%10d%10d%10s\n", e.getName(), e.getWorkDay(), e.getOverWork(), e.payment(),
						"manager");
			} else {
				System.out.printf("%10s%10d%10d%10d%10s\n", e.getName(), e.getWorkDay(), e.getOverWork(), e.payment(),
						"staff");
			}
		}
		System.out.println();
	}
}
