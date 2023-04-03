package telran.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Calculator {
	
	static ArrayList<Item> subMenu = new ArrayList<>();
	static Item mainMenu;

	public static void main(String[] args) {
		StandardInputOutput io = new StandardInputOutput();
		constructMenu();
		mainMenu.perform(io);
	}
	
	private static void constructMenu() {
		subMenu.add(new Menu("Numbers operations", constructArithmeticMenu()));
		subMenu.add(new Menu("Date operations", constructDateMenu()));
		mainMenu = new Menu ("Main menu", subMenu);
	}

	private static ArrayList<Item> constructArithmeticMenu() {
		ArrayList<Item> subArithmeticMenu = new ArrayList<>(); 
		Item add = Item.of("Addition two numbers", io -> { Double[] nums = getTwoNums(io); io.writeLine(nums[0] + nums[1]);
		});
		Item subtr = Item.of("Subtraction two numbers", io -> { Double[] nums = getTwoNums(io); io.writeLine(nums[0] - nums[1]);
		});
		Item multiply = Item.of("Multiply two numbers", io -> { Double[] nums = getTwoNums(io); io.writeLine(nums[0] * nums[1]);
		});
		Item div = Item.of("Divide two numbers", io -> { Double[] nums = getTwoNums(io); io.writeLine(nums[0] / nums[1]);
		});
	
	Item exit = Item.of("Exit", io -> {}, true);
	
	subArithmeticMenu.add(add);
	subArithmeticMenu.add(subtr);
	subArithmeticMenu.add(multiply);
	subArithmeticMenu.add(div);
	subArithmeticMenu.add(exit);
	
	return subArithmeticMenu;
	
}
			
	private static Double[] getTwoNums(InputOutput io) {
			Double nums[] = new Double[2];
			nums[0] = io.readNumber("Enter a first number", "Not a number", Double.NEGATIVE_INFINITY, Double.MAX_VALUE);
			nums[1] = io.readNumber("Enter a second number", "Not a number", Double.NEGATIVE_INFINITY, Double.MAX_VALUE);
			return nums;
		
	}
		
	
		private static ArrayList<Item> constructDateMenu() {
		ArrayList<Item> subDateMenu = new ArrayList<>();
		Item addDays = Item.of("Addition days", io -> { LocalDate date = getLocalDate(io); io.writeLine(date.plusDays(getNumOfDays(io)));
		});
		Item subtrDays = Item.of("Subtraction days", io -> { LocalDate date = getLocalDate(io); io.writeLine(date.minusDays(getNumOfDays(io)));
		});
		Item exit = Item.of("Exit", io -> {}, true);
		
		subDateMenu.add(addDays);
		subDateMenu.add(subtrDays);
		subDateMenu.add(exit);
		
		return subDateMenu;
	}

		private static long getNumOfDays(InputOutput io) {
			Integer num = io.readInt("Enter a number of days for subtraction of chosed Date", "Wrong number", 1, Integer.MAX_VALUE);
			return num;
		}

		private static LocalDate getLocalDate (InputOutput io) {
			LocalDate date = io.readDate("Enter a date using format ''yyyy-MM-dd''", "Wrong date", "yyyy-MM-dd", LocalDate.MIN, LocalDate.MAX);
			return date;
		}
}



