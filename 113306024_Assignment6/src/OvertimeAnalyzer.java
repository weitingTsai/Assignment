import java.util.ArrayList;
import java.lang.Math;

public class OvertimeAnalyzer implements Analyzer {
	private ArrayList<Employee> employees = new ArrayList<Employee>();

	public void addE(ArrayList<Employee> employees) {
		this.employees = employees;
	}

	public int count() {
		int numEmployee = 0;
		for (Employee e : employees) {
			if (e.getOverWork() > 0) {
				numEmployee++;
			}
		}
		return numEmployee;
	}

	public int sum() {
		int total = 0;
		for (Employee e : employees) {
			if (e.getOverWork() > 0) {
				total += e.getOverWork();
			}
		}
		return total;
	}

	public double avg() {
		return (double) sum() / (double) count();
	}

	public int max() {
		int max = 0;
		for (int i = 0; i < employees.size(); i++) {
			max = Math.max(employees.get(i).getOverWork(), max);
		}
		return max;
	}

	public void getInfo() {
		System.out.printf("<Over Work info>\n%20s%10d\n%20s%10d\n%20s%10.2f\n%20s%10d\n\n", "Employees:", count(),
				"Total hours:", sum(), "Average hours:", avg(), "Max hours:", max());
	}
}
