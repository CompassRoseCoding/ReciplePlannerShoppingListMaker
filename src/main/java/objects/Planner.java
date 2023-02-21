package main.java.objects;

import main.java.io.ReadFile;
import main.java.io.WriteFile;

public class Planner {
	
	static String[] days = new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	static String[] plans;

	public Planner() {
		plans = ReadFile.getRecipePlannerData();
	}
	
	public String getEntry(int i) {
		return days[i] + ": " + plans[i].replace("_", " ");
	}
	
	public void setEntry(int i, String title) {
		plans[i] = title;
		WriteFile.writeRecipesList(plans);
	}
	
	public void clearEntry(int i) {
		plans[i] = "";
		WriteFile.writeRecipesList(plans);
	}
	
	public String getDay(int i) {
		return days[i];
	}
	
	public void deleteRecipe(String title) {
		for (int i = 0; i < plans.length; i++) {
			if (plans[i].replace("_", " ").equals(title)) {
				clearEntry(i);
			}
		}
	}
	
	public String[] getDays() {
		return days;
	}
	
	public String[] getPlans() {
		return plans;
	}
}
