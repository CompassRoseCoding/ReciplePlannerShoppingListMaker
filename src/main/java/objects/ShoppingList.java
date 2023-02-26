package main.java.objects;

import java.util.LinkedList;
import java.util.Map;
import java.util.Comparator;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import main.java.io.ReadFile;

public class ShoppingList {
	static String ingredientsPath = "src/main/data/recipes/";

	HashMap<String, JSONObject> list;

	static ConversionMatrix converter;

	public ShoppingList() {
		list = new HashMap<String, JSONObject>();
		converter = new ConversionMatrix();

		Planner plan = new Planner();
		String[] recipes = plan.getPlans();

		for (int i = 0; i < recipes.length; i++) {
			if (recipes[i].length() > 0) {
				JSONObject recipe = new JSONObject(ReadFile.getText("recipes/" + recipes[i]));
				JSONArray ingredients = (JSONArray) (recipe.get("ingredients"));
				for (int j = 0; j < ingredients.length(); j++) {
					addJSONItem((JSONObject) ingredients.get(j));
				}
			}
		}
	}

	public void addJSONItem(JSONObject ingredient) {
		for (Map.Entry<String, JSONObject> set : list.entrySet()) {
			if (ingredient.getString("keyword").contains(set.getValue().getString("keyword"))
					|| set.getValue().getString("keyword").contains(ingredient.getString("keyword"))) {
				ingredient = converter.mergeIngredients(ingredient, set.getValue());
			}
		}
		list.put(ingredient.getString("keyword"), ingredient);
	}

	public LinkedList<String> addItems(String[] items) {
		for (int i = 0; i < items.length; i++) {
			Ingredient item = new Ingredient(items[i], converter);
			list.put(item.getIngredient().getString("keyword"), item.getIngredient());
		}
		return getComplexList();
	}

	public LinkedList<String> getSimpleList() {
		LinkedList<String> newlist = new LinkedList<String>();
		for (Map.Entry<String, JSONObject> set : list.entrySet()) {

			newlist.add(set.getValue().getString("displayword"));
		}

		newlist.sort(new ShoppingListComparator());
		return newlist;
	}

	public LinkedList<String> getComplexList() {
		LinkedList<String> newlist = new LinkedList<String>();
		for (Map.Entry<String, JSONObject> set : list.entrySet()) {
			newlist.add(set.getValue().get("quantity") + " " + set.getValue().getString("measure") + "\t"
					+ set.getValue().getString("displayword").toLowerCase());
		}

		newlist.sort(new ComplexListComparator());
		return newlist;
	}
}

/**
 * 
 * @author Hannah
 *	a short comparator class to sort the list
 */
class ShoppingListComparator implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        return s1.compareTo(s2);
    }
}

/**
 * 
 * @author Hannah
 *	a short comparator class to sort the list
 */
class ComplexListComparator implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        return s1.split("\t")[1].compareTo(s2.split("\t")[1]);
    }
}