package leaveManagement;

import java.util.Scanner;

public class LoginPage {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("LEAVE MANAGEMENT");
				
		AdminFunction adminfun=new AdminFunction();
		AdminPage adminpage=new AdminPage();
		
		ManagerFunction manfun=new ManagerFunction();
		ManagerPage manpage=new ManagerPage();
		
		EmployeePage emppage = new EmployeePage();
		EmployeeFunction empfun = new EmployeeFunction();
		
		while(true) {
			System.out.println("------------------------------LOGIN PAGE-----------------------");
			System.out.println("1.Admin\n2.Manager\n3.Employee.\n4.Exit from the page");
			
			int choice=0;
			try {
				System.out.println("Enter your login Type:");
				choice=scan.nextInt();
			}
			catch(Exception e) {
				scan.nextLine();
				System.out.println("Please,enter the Numerical  value.");
				continue;
			}
	
			scan.nextLine();
			switch(choice) {
			
			case 1:System.out.println("User name:");
					String userName1=scan.nextLine();
					System.out.println("password:");
					String password1=scan.nextLine();
					boolean res1=adminfun.verifyLogin(userName1, password1);
					if(res1==true) {
						adminpage.main(userName1);
					}
					else {
						System.out.println();
						System.out.println("Wrong password,Try Again!");
					}
					break;
			case 2:System.out.println("User name:");
					String userName=scan.nextLine();
					System.out.println("password:");
					String password=scan.nextLine();
					boolean res=manfun.verifyLogin(userName, password);
					if(res==true) {
						manpage.main(userName);
					}
					else {
						System.out.println();
						System.out.println("wrong password,Try Again!");
					}
					break;
			case 3:System.out.println("User name:");
					String userName11=scan.nextLine();
					System.out.println("password:");
					String password11=scan.nextLine();
					boolean res11=empfun.verifyLogin(userName11,password11);
					
					if(res11==true) {
						emppage.main(userName11);
					}
					else {
						System.out.println();
						System.out.println("wrong password,Try Again!");
					}
					System.out.println();
					break;
			case 4:System.out.println("Thank you for visiting.");
					System.exit(0);
			default:System.out.println("Enter right choice");
			}			
		}
	}
}
