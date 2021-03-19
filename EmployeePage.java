package leaveManagement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class LeaveLog {
	String empName;
	String reason;
	String status;
	String manager;
	String datetime;
	int day;
	public LeaveLog(String empName,String manager,String reason,int day,String datetime,String status) {
		this.empName=empName;
		this.reason=reason;
		this.status=status;
		this.manager=manager;
		this.datetime=datetime;
		this.day=day;
	}

}
class SettingLeaveLog {
	String status="Not updated";
	static ArrayList<LeaveLog> leaveLog=new ArrayList<LeaveLog>();
	public SettingLeaveLog(String empName,String manager,String reason,int day,String datetime) {
		leaveLog.add(new LeaveLog(empName,manager,reason,day,datetime,status));
	}

}
class EmployeeFunction extends Staff {
	
	Scanner scan = new Scanner(System.in);
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); 
	@Override
	void viewDetails(String args) {
		System.out.println("----------------------------------------------------");
		if(SettingValues.employeesDetails.size()==0) {
			System.out.println("No details found.");
		}
		else {
			for(EmployeeDetails e:SettingValues.employeesDetails) {
				if(e.empName.equals(args)) {
					System.out.println(e.empName.toUpperCase()+",pending leaves "+e.pendingLeaves);
				}
			}
		}
		System.out.println("----------------------------------------------------");
		System.out.println();
	}
	
	@Override	
	boolean verifyLogin(String userName, String password) {
		for(EmployeeDetails el:SettingValues.employeesDetails) {
			if(userName.equals(el.empName)==true) {
				if(password.equals(el.pwd)) {
					return true;
				}
			}
		}
		return false;
	}
	
	 boolean requestLeave(String args) {
		System.out.println(args.toUpperCase()+", Enter the purpose of leave:");
		String purpose=scan.nextLine();
		String managername=returnManagerName(args);
		
		if(managername.equals("Not assigned")) {
			System.out.println("Your manager is not Assigned.Contact Administrator");
			return false;
			
		}

		int day=askDays(args);
		scan.nextLine();
		LocalDateTime now=LocalDateTime.now();
		String datetime=dtf.format(now);
		new SettingLeaveLog(args,managername,purpose,day,datetime);
		return true;
	}
	
	String returnManagerName(String args) {
		for(EmployeeDetails e:SettingValues.employeesDetails) {
			if(e.empName.equals(args)==true) {
				return e.managerName;
			}
		}
		return "null";
	}
	int askDays(String args) {
		System.out.println("Enter the no of days:");
		int day=scan.nextInt();
		
		if(returnNoDays(args)-day>=0) {
			return day;
		}
		else {
			System.out.println("you have only "+returnNoDays(args)+" pending leaves.");
			System.out.print("Please,");
			askDays(args);
		}
		return 0;
	}
	int returnNoDays(String args) {
		for(EmployeeDetails e:SettingValues.employeesDetails) {
			if(e.empName.equals(args)==true) {
				return e.pendingLeaves;
			}
		}
		return 0;
	}
	
	void leaveEmpStatus(String args) {
		System.out.println(" -------------------------------------------------------- ");
		System.out.println("|                          Leave Status                  |");
		System.out.println(" -------------------------------------------------------- ");
		System.out.println(String.format("|%-17s|%-22s|%-15s|","Reason","Date Time","Status"));
		System.out.println(" -------------------------------------------------------- ");
		if(SettingLeaveLog.leaveLog.size()==0) {
			System.out.println("No details found.");
		}
		else {
			for(LeaveLog e:SettingLeaveLog.leaveLog) {
				if(e.empName.equals(args)) {
					System.out.println(String.format("|%-17s|%-22s|%-15s|",e.reason,e.datetime,e.status));
				}
			}
		}
		System.out.println(" -------------------------------------------------------- ");
		System.out.println();
	}	
}


public class EmployeePage {

	public static int main(String args) {
		Scanner scan = new Scanner(System.in);
		
		EmployeeFunction empfun=new EmployeeFunction();
		
		
		System.out.println("----------Welcome "+args.toUpperCase()+" to Employee Page---------------------");
		System.out.println();
		
		
		
		while(true) {
			System.out.println("1.view  Details.\n2.Requesting leave.\n3.view Leave status.\n4.logout");
			System.out.println("Enter the type of Activity to be performed:");
			int operationChoice=0;
			try {
				operationChoice=scan.nextInt();
			}
			catch(InputMismatchException ime) {
				scan.nextLine();
				System.out.println("Please provide numericals value.");
				System.out.println();
			}
			
			switch(operationChoice) {
			case 1:empfun.viewDetails(args);
					break;
			case 2:empfun.requestLeave(args);
					break;
			case 3:empfun.leaveEmpStatus(args);
					break;
			case 4:System.out.println("------------Logout Successfully--------------");
					System.out.println();
					return 0;
			default: System.out.println("Enter proper choice.");
			}
		}
	}
}
