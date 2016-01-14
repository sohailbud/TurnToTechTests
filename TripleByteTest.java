import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


/**
 * Given a string of number, generate all possible scenarios where breaking the string in various substrings and applying various operators (+, -, /, *) will evaluate to
 * the sum provided. String should stay in the same order; 
 * Example: (calculate("31426", 51)
 * Evaluates to:	3+1*42+6
 *					3/1+42+6
 *					3*1+42+6
 *					3+1*4*2*6
 *					3/1+4*2*6
 *					3*1+4*2*6
 *					31+14+6
 * @author Sohail
 *
 */
public class TripleByteTest {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//				calculate("314159265358", 27182);
		calculate("31426", 51);		
	}	

	public static void calculate(String input, double sum) {

		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		
		// create all permutations which are less than sum
		List<List<String>> perms = new ArrayList<List<String>>();
		for (int indx = 0; indx < input.length(); indx++) {
			List<String> option = getPerm(indx, input, sum);
//			System.out.println(option);
			perms.add(option);
		}

		// create all possible options of numbers
		List<List<String>> options = new ArrayList<List<String>>();

		for (int p = 0; p < perms.get(0).size(); p++) {
			List<String> perm1 = perms.get(0);

			for (int i = 1; i < perms.size(); i++) {
				for (int j = 0; j < perms.get(i).size(); j++) {

					if (i == 1) {
						List<String> newOption = new ArrayList<>();
						newOption.add(perm1.get(p));
						newOption.add(perms.get(i).get(j));
						options.add(newOption);
					} else {

						List<List<String>> optionsToRemove = new ArrayList<List<String>>();
						List<List<String>> optionsToAdd = new ArrayList<List<String>>();

						for (List<String> option : options) {
							if (stringJoiner(option).length() == i) {	

								optionsToRemove.add(option);

								for (String d : perms.get(i)) {
									List<String> tempOption = new ArrayList<>();
									tempOption.addAll(option);
									tempOption.add(d);
									optionsToAdd.add(tempOption);
								}
							}
						}

						options.removeAll(optionsToRemove);
						options.addAll(optionsToAdd);
					}
				}
			}
		}

//		System.out.println(options);
		
		// We have all possible combinations of numbers, not test all the operators to see which will add up to desired sum		
		for (List<String> option : options) {
			List<String> operations = new ArrayList<>();
			possibleStrings(option.size()-1, new char[] {'+', '-', '/', '*'}, "", operations);
			
			for (String operation : operations) {
				String arithString = "";
				
				for (int i = 0; i < option.size(); i++) {
					if (operation.length() == i) {
						arithString = arithString + option.get(i);
					} else {
						arithString = arithString + option.get(i) + operation.charAt(i);
					}
				}
				
				try {
					if (sum == (Double) engine.eval(arithString)) {
						System.out.println(arithString);
					}
				} catch (ScriptException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}

	}

	/**
	 * generates all combinations of the input string while in same order
	 * @param indx
	 * @param input
	 * @param sum
	 * @return
	 */
	public static List<String> getPerm(int indx, String input, double sum) {
		List<String> perm = new ArrayList<>();

		int len = input.length();
		for (int i = indx + 1; i <= len; i++) {
			String sub = input.substring(indx, i);
			double subInt = Double.parseDouble(sub);
			
			perm.add(sub);
		}
		return perm;
	}

	/**
	 * concatenates a list into single string
	 * @param stringList
	 * @return
	 */
	public static String stringJoiner(List<String> stringList) {
		String newString = "";
		for (String str : stringList) {
			newString = newString.concat(str);
		}
		return newString;
	}

	/**
	 * find all combinations
	 * @param maxLength
	 * @param alphabet
	 * @param curr
	 * @param operations
	 * @return
	 */
	public static List<String> possibleStrings(int maxLength, char[] alphabet, String curr, List<String> operations) {
		
        // If the current string has reached it's maximum length
        if(curr.length() == maxLength) {
        	operations.add(curr);
//            System.out.println(curr);

        // Else add each letter from the alphabet to new strings and process these new strings again
        } else {
            for(int i = 0; i < alphabet.length; i++) {
                String oldCurr = curr;
                curr += alphabet[i];
                possibleStrings(maxLength,alphabet,curr, operations);
                curr = oldCurr;
            }
        }
        return operations;
    }
	



}
