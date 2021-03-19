package leaveManagement;

import java.util.*;
import java.util.regex.*;

class ManagerDetails {
	String empName;
	String DOB;
	String addressName;
	String contactNo;
	String emailId;
	String pwd;
	public ManagerDetails(String empName,String DOB,String addressName,String contactNo,String emailId,String pwd) {
		this.empName=empName;
		this.DOB=DOB;
		this.addressName=addressName;
		this.contactNo=contactNo;
		this.emailId=emailId;
		this.pwd=pwd;
	}
}


class EmployeeDetails {
	String empName;
	String DOB;
	String addressName;
	String contactNo;
	String emailId;
	String managerName;
	String pwd;
	int pendingLeaves=25;
	public EmployeeDetails(String empName,String DOB,String addressName,String contactNo,String emailId,String managerName,String pwd) {
		this.empName=empName;
		this.DOB=DOB;
		this.addressName=addressName;
		this.contactNo=contactNo;
		this.emailId=emailId;
		this.managerName=managerName;
		this.pwd=pwd;
	}
}


class SettingValues{
	public static ArrayList<EmployeeDetails> employeesDetails=new ArrayList<EmployeeDetails>();
	public static ArrayList<ManagerDetails> managerDetails=new ArrayList<ManagerDetails>();
	
	public SettingValues(String empName,String dob,String addressName,String contactNo,String emailId,String managerName,String  pwd) {
		employeesDetails.add(new EmployeeDetails(empName, dob, addressName, contactNo, emailId, managerName,pwd));
	}

	public SettingValues(String empName, String dob, String addressName, String contactNo, String emailId, String pwd) {
		managerDetails.add(new ManagerDetails(empName, dob, addressName, contactNo, emailId,pwd));
	}
	
}


class AdminFunction extends Staff{
	
	static Scanner scan=new Scanner(System.in);
//	static
//	{
//		SettingValues.employeesDetails.add(new EmployeeDetails("aravinda", "17/08/1998", "chelur", "7259959321", "aravinda@gmail.com", "prakashjcl","aa"));
//		SettingValues.employeesDetails.add(new EmployeeDetails("lokesh", "22/04/1998", "chelur", "8971974890", "lokesh@gmail.com", "prakashjcl","ll"));
//		SettingValues.employeesDetails.add(new EmployeeDetails("puru", "17/12/1998", "chelur", "7259955472", "puru@gmail.com", "ambarish","pp"));
//		SettingValues.managerDetails.add(new ManagerDetails("prakashjcl", "26/08/1999", "chelur", "9964660142", "prakash@gmail.com","jcl"));
//		SettingValues.managerDetails.add(new ManagerDetails("ambarish", "26/08/1999", "chelur", "9964660142", "ambarish@gmail.com","ak"));
//	}
	
	private String userName="prakash";
	private String password="jayam";
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	void viewDetails(String args) {
		System.out.println(" -------------------------------------------------------------------------------------------------------------");
		System.out.println("|                                              Employee Details                                               |");
		System.out.println(" -------------------------------------------------------------------------------------------------------------");
		System.out.println(String.format("|%-25s|%-11s|%-20s|%-11s|%-24s|%-13s|","Employee Name","DOB","Address","contactNo","Email Id","pendingLeaves"));
		System.out.println(" -------------------------------------------------------------------------------------------------------------");
		
		if(SettingValues.employeesDetails.size()==0) {
			System.out.println("No details found.");
		}
		else {
			for(EmployeeDetails e:SettingValues.employeesDetails) {
				System.out.println(String.format("|%-25s|%-11s|%-20s|%-11s|%-24s|%-13s|",e.empName,e.DOB,e.addressName,e.contactNo,e.emailId,e.pendingLeaves));
			}
			System.out.println(" -------------------------------------------------------------------------------------------------------------");
			System.out.println();
		}
		
		System.out.println(" -----------------------------------------------------------------------------------------------");
		System.out.println("|                                       Manager Details                                         |");
		System.out.println(" -----------------------------------------------------------------------------------------------");
		System.out.println(String.format("|%-25s|%-11s|%-20s|%-11s|%-24s|","Manager Name","DOB","Address","contactNo","Email Id"));
		System.out.println(" -----------------------------------------------------------------------------------------------");
		
		if(SettingValues.managerDetails.size()==0) {
			System.out.println("No details found.");
		}
		else {
			for(ManagerDetails e:SettingValues.managerDetails) {
				System.out.println(String.format("|%-25s|%-11s|%-20s|%-11s|%-24s|",e.empName,e.DOB,e.addressName,e.contactNo,e.emailId));
			}
		}
		
		System.out.println(" -----------------------------------------------------------------------------------------------");
		System.out.println();
		
	}
	
	@Override
	boolean verifyLogin(String userName, String password) {
		return (getUserName().equals(userName) && getPassword().equals(password));		 
	}
	
	void setEmpDetails() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the Employee Name:");
		String empName=scan.nextLine();
		System.out.println("Enter the DOB(DD/MM/YYYY):");
		String DOB=scan.nextLine();
		System.out.println("Enter the Address:");
		String addressName=scan.nextLine();
		String contactNo=returnVerifiedContact();
		String emailId=returnVerifiedEmail();
		String managerName=returnmanagerName();
		System.out.println("Enter the Password:");
		String pwd=scan.nextLine();
		System.out.println();
		
		new SettingValues(empName,DOB,addressName,contactNo,emailId,managerName, pwd);
				
		System.out.println("Deatils added Successfully.");
		System.out.println();
	}
	
	void setManDetails() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the Manager Name:");
		String empName=scan.nextLine();
		System.out.println("Enter the DOB(DD/MM/YYYY):");
		String DOB=scan.nextLine();
		System.out.println("Enter the Address:");
		String addressName=scan.nextLine();
		String contactNo=returnVerifiedContact();
		String emailId=returnVerifiedEmail();
		System.out.println("Enter the Password:");
		String pwd=scan.nextLine();
		System.out.println();
		
		new SettingValues(empName,DOB,addressName,contactNo,emailId, pwd);
				
		System.out.println("Deatils added Successfully.");
		System.out.println();
	}
	
	void delEmpDetails() {
		boolean del=false;
		System.out.println("Enter the deleted employee Name:");
		String name=scan.nextLine();
		
		for(EmployeeDetails e:SettingValues.employeesDetails) {
			if(name.equals(e.empName)) {
				SettingValues.employeesDetails.remove(e);
				System.out.println("Deleted successfully");
				del=true;
				System.out.println();
				break;
			}
		}
		
		if(del==false) {
			System.out.println();
			System.out.println("Enter proper Employee name.");
			delEmpDetails();
			System.out.println();
		}
	}
	
	void delManDetails() {
		boolean del= false;
		System.out.println("Enter the deleted Manager Name:");
		String name=scan.nextLine();
		for(ManagerDetails m:SettingValues.managerDetails) {
			if(name.equals(m.empName)) {
				SettingValues.managerDetails.remove(m);
				System.out.println("Deleted successfully");
				System.out.println();
				del=true;
				
				break;
			}
		}
		
		if(del==true) {
			for(EmployeeDetails e:SettingValues.employeesDetails) {
				if(name.equals(e.managerName)) {
					e.managerName="Not assigned";
				}
			}
		}
		if(del==false) {
			System.out.println();
			System.out.println("Enter proper Manager Name.");
			delManDetails();
			System.out.println();
		}
		
	}
	
	String returnmanagerName() {
		System.out.println("Manager Name:");
		ArrayList<String> ar=new ArrayList<String>();
		for(ManagerDetails m:SettingValues.managerDetails) {
			ar.add(m.empName);
		}
		int count=0;
		for(String s:ar) {
			System.out.println(++count+":"+s);
		}
		System.out.println("Your choice:");
		int choice=scan.nextInt();
		scan.nextLine();
		String name=ar.get(choice-1);
		return name;
	}
	
	String returnVerifiedContact() {
		Pattern p=Pattern.compile("[0-9]{10}");
		System.out.println("Enter the Contact.No:");
		String contact=scan.nextLine();
		if(p.matcher(contact).matches()==true) {
			return contact;
		}
		else {
			System.out.println("Not valide,enter 10 digits number");
			System.out.print("Please,");
			returnVerifiedContact();
		}	
		return "null";
	}
	
	String returnVerifiedEmail() {	
		Pattern p=Pattern.compile("^[a-zA-Z0-9_+&*-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,7}$");
		System.out.println("Enter the Email ID:");
		String email=scan.nextLine();
		if(p.matcher(email).matches()==true) {
			return email;
		}
		else {
			System.out.println("Not valide,\n Sample mail:abc@xyz.pqr");
			System.out.print("Please,");
			returnVerifiedEmail();
		}		
		return "null";
	}
		
}

public class AdminPage {
	public static int main(String args) {
		Scanner scan = new Scanner(System.in);
		
		AdminFunction adminfun= new AdminFunction();
		
		System.out.println("----------Welcome "+args.toUpperCase()+" to Admin Page---------------------");
		while(true) {
		System.out.println("1.Add a Employee Details.\n2.Delete a Employee Details.\n3.View Employee Details.\n4.logout");
		System.out.println("Enter the type of Activity to be performed:");
		int operationChoice=0;
		try {
			operationChoice=scan.nextInt();
		}
		catch(InputMismatchException ime) {
			scan.nextLine();
			System.out.println("------#####------Please provide numericals value.-------#####------");
			continue;
		}

		scan.nextLine();
		switch(operationChoice) {
		case 1:System.out.println("1.Manager.\n2.Employee.");
				
				int c=0;
				try {
					c=scan.nextInt();
				}
				catch(InputMismatchException ime) {
					scan.nextLine();
					System.out.println("Please provide numericals value.");
					System.out.println();
					try {
						System.out.println("Re Enter the choice:");
						c=scan.nextInt();
						
					}
					catch(InputMismatchException ime1) {
						scan.nextLine();
						System.err.println("Sorry wrong value,Go to menu.");
						continue;
					}
				}
				
				if(c==1) {
					adminfun.setManDetails();
				}
				else if(c==2) {
					adminfun.setEmpDetails();
				}
				else {
					System.out.println("Please,enter right choice.");
				}
				
			   break;
		case 2:System.out.println("1.Manager.\n2.Employee.");
				int cc=0;
				try {
					cc=scan.nextInt();	
				}
				catch(InputMismatchException ime) {
					scan.nextLine();
					System.out.println("Please provide numericals value.");
					System.out.println();
					try {
						System.out.println("Re Enter the choice:");
						cc=scan.nextInt();
					}
					catch(InputMismatchException ime1) {
						System.out.println();
						System.out.println("You entered wrong value,login come again");
						continue;
					}
				}
				if(cc==1) {
					adminfun.delManDetails();
				}
				else if(cc==2) {
					adminfun.delEmpDetails();
				}
				else {
					System.out.println("Please,enter right choice.");
				}
				break;
		case 3:adminfun.viewDetails("Admin");
				break;
		case 4:System.out.println("----------------------logout succesfully.--------------------");
				System.out.println();
				return 0;
		default:System.out.println("Enter the valid choice.");
		}
	}
	}
}
