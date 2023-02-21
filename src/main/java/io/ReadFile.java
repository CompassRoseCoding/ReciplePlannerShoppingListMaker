package main.java.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class ReadFile {
	static String dataPath = "src/main/data/";

	public static String[] getRecipePlannerData() {
		String[] returnArr = { "", "", "", "", "", "", "" };
		try {
			File file = new File(dataPath);
			file.mkdirs();

			file = new File(dataPath + "savedRecipes.txt");

			if (file.createNewFile()) {
				WriteFile.writeRecipesList(returnArr);
			}

			BufferedReader reader = new BufferedReader(new FileReader(dataPath + "savedRecipes.txt"));
			String inpt = reader.readLine();
			int i = 0;
			while (inpt != null && i < returnArr.length) {
				if (inpt.length() > 0 && inpt.charAt(0) != ' ') {
					returnArr[i] = inpt.replace("\n", "");
				}
				i++;
				inpt = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return returnArr;
	}

	public static String[] getArchivedRecipes() {
		File folder = new File(dataPath + "recipes/");
		folder.mkdir();
		File[] files = folder.listFiles();
		if (files != null) {
			String[] nameList = new String[files.length];
			for (int i = 0; i < files.length; i++) {
				if (!files[i].isDirectory())
					nameList[i] = files[i].getName().substring(0, files[i].getName().length() - 4);
			}
			return nameList;
		} else {
			return new String[0];
		}
	}

	public static String getText(String title) {
		String returnText = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(dataPath + title + ".txt"));
			returnText = reader.lines().collect(Collectors.joining(System.lineSeparator()));
			reader.close();
		} catch (FileNotFoundException e) {
			returnText = "{\"fulltext\": \"Error, file " + dataPath + title + ".txt" + " not found.\"}";
		} catch (IOException e) {
			returnText = "{\"fulltext\": \"Error, file corrupted\"}";
		}
		return returnText;
	}
}
