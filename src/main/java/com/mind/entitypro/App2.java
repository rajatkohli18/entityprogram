package com.mind.entitypro;


	import java.util.Scanner;


import com.mindtree.EntitiesProgram.Options;

import exception.DaoInputException;
	import exception.DaoOutpuException;
	import exception.DataNotFound;
	import exception.InpuFormatEcxeption;
	import exception.InputException;
	import service.InputInterface;
	import service.impl.InputInterfaceImpl;
	import utility.InputUtility;



	/**
	 * Hello world!
	 *
	 */
	public class App2 {
		static InputInterface ip = new InputInterfaceImpl();

//		public App() {
//			super();
//			ip=new InputInterfaceImpl();
//			
//		}
		public static void main(String[] args)
				throws InputException, InpuFormatEcxeption,  DataNotFound, DaoOutpuException {
			boolean condition = false;

			do {
				Options.displayMenu();
				try {
					int choice = InputUtility.getChoice();
					switch (choice) {
					case 1:

						try {
							ip.addMethod();
						} catch (DaoInputException e) {

							System.err.println(e.getMessage());
						}
						break;
					case 2:
						ip.displayAuthorDetails();
						break;
					case 3:
						ip.displayAuthor();
						break;
					case 4:
						Options.exit();
						condition = true;
						break;
					case 5:
						ip.deleteBookByBookId();
						break;
					case 6:
						ip.displyAllRecord();
						break;
					default:
						System.out.println("You enter wrong choice please enter correct choice..");
						break;
					}

				} catch (InputException e) {
					
					condition = true;
					System.out.println(e.getMessage());
				}

			} while (!condition);

		}
	}



