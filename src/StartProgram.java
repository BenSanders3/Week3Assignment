

import java.util.List;
import java.util.Scanner;

import controller.ListPartsHelper;
import model.ListParts;

public class StartProgram {

		static Scanner in = new Scanner(System.in);
		static ListPartsHelper lih = new ListPartsHelper();

		private static void addAnPrice() {
			// TODO Auto-generated method stub
			System.out.print("Enter a part: ");
			String part = in.nextLine();
			System.out.print("Enter an price: ");
			String price = in.nextLine();
			ListParts toAdd = new ListParts(part, price);
			lih.insertPrice(toAdd);

		}

		private static void deleteAnPrice() {
			// TODO Auto-generated method stub
			System.out.print("Enter the part to delete: ");
			String part = in.nextLine();
			System.out.print("Enter the price to delete: ");
			String price = in.nextLine();
			ListParts toDelete = new ListParts(part, price);
			lih.deletePrice(toDelete);
		}

		private static void editAnPrice() {
			// TODO Auto-generated method stub
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by Part");
			System.out.println("2 : Search by Price");
			int searchBy = in.nextInt();
			in.nextLine();
			List<ListParts> foundPrices;
			if (searchBy == 1) {
				System.out.print("Enter the part name: ");
				String partName = in.nextLine();
				foundPrices = lih.serchForPriceByPart(partName);
				
			} else {
				System.out.print("Enter the price: ");
				String priceName = in.nextLine();
				foundPrices = lih.searchForPriceByPrice(priceName);
				

			}

			if (!foundPrices.isEmpty()) {
				System.out.println("Found Results.");
				for (ListParts l : foundPrices) {
					System.out.println(l.getId() + " : " + l.toString());
				}
				System.out.print("Which ID to edit: ");
				int idToEdit = in.nextInt();

				ListParts toEdit = lih.searchForPriceById(idToEdit);
				System.out.println("Retrieved " + toEdit.getPrice() + " from " + toEdit.getPart());
				System.out.println("1 : Update Part");
				System.out.println("2 : Update Price");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New Part: ");
					String newPart = in.nextLine();
					toEdit.setPart(newPart);
				} else if (update == 2) {
					System.out.print("New Price: ");
					String newPrice = in.nextLine();
					toEdit.setPrice(newPrice);
				}

				lih.updatePrice(toEdit);

			} else {
				System.out.println("---- No results found");
			}

		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			runMenu();

		}

		public static void runMenu() {
			boolean goAgain = true;
			System.out.println("--- Welcome to the Part list ---");
			while (goAgain) {
				System.out.println("*  Select a part:");
				System.out.println("*  1 -- Add a part");
				System.out.println("*  2 -- Edit a part");
				System.out.println("*  3 -- Delete a part");
				System.out.println("*  4 -- View the list");
				System.out.println("*  5 -- Exit the program");
				System.out.print("*  Your selection: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addAnPrice();
				} else if (selection == 2) {
					editAnPrice();
				} else if (selection == 3) {
					deleteAnPrice();
				} else if (selection == 4) {
					viewTheList();
				} else {
					lih.cleanUp();
					System.out.println("   Goodbye   ");
					goAgain = false;
				}

			}

		}

		private static void viewTheList() {
			// TODO Auto-generated method stub
			List<ListParts> allPrices = lih.showAllPrices();
			for(ListParts singlePrice : allPrices) {
				System.out.println(singlePrice.returnPartDetails());
			}
		}

	}