package main.java.objects;

import org.json.JSONObject;

public class Ingredient {
	
	static JSONObject ingredient;

	Ingredient(String line, ConversionMatrix converter) {
		
		String[] ingredientLine = line.split(" ");
		ingredient = new JSONObject();
		ingredient.put("quantity", "");
		ingredient.put("measure", "");
		ingredient.put("modifier", "");
		
		for (int j = 0; j < ingredientLine.length; j++) {
			double totalAmt = 0;

			if (ingredientLine[j].length() == 0) {
				continue;
			} else {
				if (converter.isNumeric(ingredientLine[j])) {
					totalAmt = totalAmt + Double.parseDouble(ingredientLine[j]);
					ingredient.put("quantity", totalAmt);
				} else if (converter.isFraction(ingredientLine[j])) {
					totalAmt = totalAmt + converter.getDouble(ingredientLine[j], "value");
					ingredient.put("quantity", totalAmt);
				} else if (converter.isMeasure(ingredientLine[j].replace(".", ""))) {
					ingredient.put("measure", converter.getString(ingredientLine[j].replace(".", ""), "standard"));
				} else {
					ingredient.put("modifier", (String) ingredient.get("modifier") + " " + ingredientLine[j]);
				}
			}
		}
		String descriptor = ingredient.getString("modifier");
		String[] commasplit = descriptor.split(",");
		String[] parensplit = commasplit[0].split("\\(");
		ingredient.put("keyword", parensplit[0].replace(" ", ""));
		ingredient.put("displayword", parensplit[0]);
	}
	
	public JSONObject getIngredient() {
		return ingredient;
	}
}
