package main;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

	public static void main(String[] args) {

	}

	public static int add(String str) {
		List<String> negativeNumbers = new ArrayList<>();
		int length = str.length();
		int sum = 0;
		String delimiter = "";
		if (length == 1 && Integer.parseInt(str) < 0)
			negativeNumbers.add(str);
		else if (length == 1)
			return Integer.parseInt(str);
		else if (length == 0)
			return 0;
		if (negativeNumbers.isEmpty()) {
			String delimiterValue[] = getDelimiter(str);
			delimiter = "[" + delimiterValue[0] + "]";
			str = str.substring(Integer.parseInt(delimiterValue[1]));
			length = str.length();
//			if (str.substring(0,2).equals("//") && str.length() >= 3) {
//				if(str.contains("[") && str.contains("]"))
//				{
//				int delimiterEnd = str.indexOf(']'); 
//				delimiter = str.substring(2,delimiterEnd+1);
//				str = str.substring(delimiterEnd+1);
//				length = str.length();
//				}
//				else
//				{
//					delimiter = str.substring(2,3);
//					str = str.substring(3);
//					length = str.length();
//				}
//			}
//			else
//				delimiter = ",";

			if (length > 1) {
				String strarray[] = str.split(delimiter);

				for (int i = 0; i < strarray.length; i++) {
					if (strarray[i].contains("\n")) {
						String splitString[] = strarray[i].split("\n");

						for (int j = 0; j < splitString.length; j++) {

							if (!splitString[j].isEmpty()) {
								sum += Integer.parseInt(splitString[j]);
								if (Integer.parseInt(splitString[j]) < 0) {
									negativeNumbers.add(splitString[j]);
								}
							}
						}
					} else {

						if (!strarray[i].isEmpty()) {
							sum += Integer.parseInt(strarray[i]);
							if (Integer.parseInt(strarray[i]) < 0) {
								negativeNumbers.add(strarray[i]);
							}

						}
					}
				}

			}
		}
		if (!negativeNumbers.isEmpty()) {
			String negNumbers = "";
			for (int inc = 0; inc < negativeNumbers.size(); inc++) {
				negNumbers += negativeNumbers.get(inc) + ",";
			}
			negNumbers = negNumbers.substring(0, negNumbers.length() - 1);
			throw new IllegalArgumentException("negatives not allowed :" + negNumbers);
		}
		return sum;
	}

	public static String[] getDelimiter(String str) {
		String delim[] = { "", "" };

		int index = 0;
		if (str.contains("[") && str.contains("]")) {
			int startIndex = str.indexOf("[");

			for (int i = startIndex + 1; i < str.length(); i++) {
				if (str.charAt(i) == ']') {
					if (str.substring(i, str.length()).contains("["))
						continue;
					else {
						index = i + 1;
						break;
					}
				} else if (str.charAt(i) == '[') {

				} else
					delim[0] += str.charAt(i);
			}

		} else if (str.contains("//")) {
			delim[0] = str.substring(2, 3);
			index = 3;
		} else {
			delim[0] = ",";
			index = 0;
		}
		delim[1] = String.valueOf(index);
		return delim;
	}
}