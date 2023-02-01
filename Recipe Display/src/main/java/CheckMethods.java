package main.java;

public class CheckMethods {
	
	static char[] fractions = new char[] { '¼', '½', '¾', '⅓', '⅔', '⅕', '⅖', '⅗', '⅘', '⅙', '⅚', '⅛', '⅜', '⅝', '⅞' };
	static double[] fractionVals = new double[] { .25, .50, .75, .33, .67, .20, .40, .60, .80, .17, .83, .125, .375,
			.625, .875 };

	static String[] measures = new String[] { "Tbsp", "Tbl", "T", "tablespoon", "tsp", "teaspoon", "oz", "ounce", "fl",
			"fluid", "c", "cup", "qt", "quart", "pt", "pint", "gal", "gallon", "lb", "pound", "mL", "milliliter", "g",
			"gram", "kg", "kilogram", "l", "liter", "Medium", "medium", "na" };
	static String[] measureVals = new String[] { "Tbsp", "Tbsp", "Tbsp", "Tbsp", "tsp", "tsp", "oz", "oz", "fl", "fl",
			"c", "c", "qt", "qt", "pt", "pt", "gal", "gal", "lb", "lb", "mL", "mL", "g", "g", "kg", "kg", "l", "l", "medium", "medium", "na" };

	
	public static boolean isNumeric(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != '0' && s.charAt(i) != '1' && s.charAt(i) != '2' && s.charAt(i) != '3'
					&& s.charAt(i) != '4' && s.charAt(i) != '5' && s.charAt(i) != '6' && s.charAt(i) != '7'
					&& s.charAt(i) != '8' && s.charAt(i) != '9' && s.charAt(i) != '.') {
				return false;
			}
		}
		return true;
	}

	public static double isFraction(String s) {
		if (s.contains("/")) {
			String[] temp = s.split("/");
			if (temp.length > 2) {
				return 1;
			} else if (!isNumeric(temp[0]) || !isNumeric(temp[1])) {
				return 1;
			} else if (temp[0].contains(".") || temp[1].contains(".")) {
				return 1;
			} else {
				return Double.valueOf(temp[0]) / Double.valueOf(temp[1]);
			}
		}
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < fractions.length; j++) {
				if (s.charAt(i) == fractions[j]) {
					return fractionVals[j];
				}
			}
		}
		return 0.0;
	}

	public static int isMeasure(String s) {
		for (int i = 0; i < measures.length; i++) {
			if (s.equalsIgnoreCase(measures[i]) || s.equalsIgnoreCase(measures[i] + 's')) {
				return i;
			}
		}
		return -1;
	}

	public static boolean breakLoopWord(String word) {
		if (word.equalsIgnoreCase("I")) {
			return true;
		} else if (word.equalsIgnoreCase("to")) {
			return true;
		} else if (word.contains("(")) {
			return true;
		}
		return false;
	}
	
	public static String getMeasure(String meas) {
		return measureVals[isMeasure(meas)];
	}
}
