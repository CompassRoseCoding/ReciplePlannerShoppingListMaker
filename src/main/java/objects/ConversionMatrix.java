package main.java.objects;

import java.util.HashMap;

import org.json.JSONObject;

import main.java.io.ReadFile;

public class ConversionMatrix {
	
	static char[] fractions = new char[] { '¼', '½', '¾', '⅓', '⅔', '⅕', '⅖', '⅗', '⅘', '⅙', '⅚', '⅛', '⅜', '⅝', '⅞' };
	static double[] fractionVals = new double[] { .25, .50, .75, .33, .67, .20, .40, .60, .80, .17, .83, .125, .375,
			.625, .875 };


	static HashMap<String, JSONObject> list;

	public ConversionMatrix() {
		list = new HashMap<String, JSONObject>();
		String[] matrix = ReadFile.getText("conversionMatrix").split("\n");
		for (int i = 0; i < matrix.length; i++) {
			JSONObject temp = new JSONObject(matrix[i]);
			temp.put("type", "measure");
			list.put(temp.getString("key"), temp);
		}
		for (int i = 0; i < fractions.length; i++) {
			JSONObject temp = new JSONObject();
			temp.put("value", fractionVals[i]);
			temp.put("type", "fraction");
			temp.put("key", fractions[i] + "");
			list.put(fractions[i] + "", temp);
		}
		//If I need to write another measure to the list
		//WriteFile.writeConversionMatrix(list);
	}

	public HashMap<String, JSONObject> getMatrix() {
		return list;
	}

	// The English measuring system is a mess, at least for computers
	// All those multiples of three and four!
	// This attempts to gracefully convert one quantity to another
	public JSONObject mergeIngredients(JSONObject i1, JSONObject i2) {
		// get conversion factor of each measure
		double c1 = list.get(i1.getString("measure")).getDouble("value");
		double c2 = list.get(i2.getString("measure")).getDouble("value");
		if (c1 > c2) {
			// basic conversion formula: quantity * fraction of smaller measure over larger
			// since I have my formulas standardized to the cup, i have to make my own ratio
			double newAmt = i1.getDouble("quantity") + i2.getDouble("quantity") * c2 / c1;
			i1.put("quantity", newAmt);
			return i1;
		} else if (c2 > c1) {
			// basic conversion formula: quantity * fraction of smaller measure over larger
			// since I have my formulas standardized to the cup, i have to make my own ratio
			double newAmt = i2.getDouble("quantity") + i1.getDouble("quantity") * c1 / c2;
			i2.put("quantity", newAmt);
			return i2;
		} else {
			try {
				double newAmt = i2.getDouble("quantity") + i1.getDouble("quantity");
				i1.put("quantity", newAmt);
				return i1;
			}
			catch (org.json.JSONException e) {
				double q1;
				try {
					q1 = Double.parseDouble((String) i2.get("quantity"));
				}
				catch (java.lang.NumberFormatException e1) {
					q1 = 0;
				}
				
				double q2;
				try {
					q2 = Double.parseDouble((String) i2.get("quantity"));
				}
				catch (java.lang.NumberFormatException e2) {
					q2 = 0;
				}
				double newAmt = q1 + q2;
				if (newAmt > 0) 
					i1.put("quantity", newAmt);
				else 
					i1.put("quantity", "");
				return i1;
			}
				
		}
	}

	// Abbreviations are far from standard for recipes
	// This attempts to put them all into one form
	public String getString(String meas, String key) {
		return list.get(meas).getString(key);
	}
	
	// Try to get the numerical value of a measure
	public double getDouble(String meas, String key) {
		try {
			return list.get(meas).getDouble(key);
		}
		catch (java.lang.NullPointerException e) {
			return 1.0;
		}
	}
	
	public boolean isNumeric(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != '0' && s.charAt(i) != '1' && s.charAt(i) != '2' && s.charAt(i) != '3'
					&& s.charAt(i) != '4' && s.charAt(i) != '5' && s.charAt(i) != '6' && s.charAt(i) != '7'
					&& s.charAt(i) != '8' && s.charAt(i) != '9' && s.charAt(i) != '.') {
				return false;
			}
		}
		return true;
	}

	public boolean isFraction(String s) {
		s = s.replace(" ", "");
		if (s.contains("/")) {
			String[] temp = s.split("/");
			if (temp.length > 2) {
				return false;
			} else if (isNumeric(temp[0]) && isNumeric(temp[1]) && !temp[0].contains(".") && !temp[1].contains(".")) {
				return true;
			} else {
				return false;
			}
		}
		else {
			return list.containsKey(s) && list.get(s).getString("type").equals("fraction");
		}
	}
	
	public boolean isMeasure(String key) {
		return list.containsKey(key) && list.get(key).getString("type").equals("measure");
	}
}
