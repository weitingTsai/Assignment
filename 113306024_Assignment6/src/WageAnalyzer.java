import java.util.ArrayList;
import java.lang.Math;

public class WageAnalyzer implements Analyzer {
	private ArrayList<Employee> employees = new ArrayList<Employee>();

	public void addE(ArrayList<Employee> employees) {
		this.employees = employees;
	}

	public int count() {
		return employees.size();
	}

	public int sum() {
		int total = 0;
		for (Employee e : employees) {
			total += e.payment();
		}
		return total;
	}

	public double avg() {
		return (double) sum() / (double) count();
	}

	public int max() {
		int max = 0;
		for (int i = 0; i < employees.size(); i++) {
			max = Math.max(employees.get(i).payment(), max);
		}
		return max;
	}

	public void getInfo() {
		System.out.printf("<Wage info>\n%20s%10d\n%20s%10d\n%20s%10.2f\n%20s%10d\n\n", "Employees:", count(),
				"Total payment:", sum(), "Average payment:", avg(), "Max payment:", max());
	}
}
