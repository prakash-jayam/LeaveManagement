package leaveManagement;

import java.util.InputMismatchException;
import java.util.Scanner;

class ManagerFunction extends Staff{
	Scanner scan = new Scanner(System.in);
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
				if(e.managerName.equals(args)) {
					System.out.println(String.format("|%-25s|%-11s|%-20s|%-11s|%-24s|%-13s|",e.empName,e.DOB,e.addressName,e.contactNo,e.emailId,e.pendingLeaves));
				}
				
			}
		}
		System.out.println(" -------------------------------------------------------------------------------------------------------------");
		System.out.println();
	}	
	
	@Override
	boolean verifyLogin(String userName, String password) {
		for(ManagerDetails e2:SettingValues.managerDetails) {
			if(userName.equals(e2.empName)==true) {
				if(password.equals(e2.pwd)) {
					return true;
				}
			}
		}
		return false;
	}
	
	void viewLeaves(String args) {
		boolean view=false;
		System.out.println("------------------- Your Employee leave seekings---------------------");
		if(SettingLeaveLog.leaveLog.size()==0) {
			System.out.println("No leave log details found.");
		}
		else {
			for(LeaveLog e:SettingLeaveLog.leaveLog) {
				if(e.manager.equals(args) && e.status.equals("Not updated")) {
					System.out.println("Employee Name:"+e.empName+"\nPurpose :"+e.reason+"\nNo. of days:"+e.day+"\nApplied On:"+e.datetime);
					System.out.println("update the leave status.\n1.Accept.\n2.Reject");
					int choice=scan.nextInt();
					view=true;
					if(choice==1) {
						e.status="Accept";
						for(EmployeeDetails ee:SettingValues.employeesDetails) {
							if(ee.empName.equals(e.empName)) {
								ee.pendingLeaves=ee.pendingLeaves-e.day;
							}
						}
					}
					else {
						e.status="Reject";
					}
				}
			}
		}
		if(view==false) {
			System.out.println("No updated leave pending");
		}
		System.out.println("------------------------------------------------------------------");
		System.out.println();
	}
	void viewRejectedLeaves(String args) {
		boolean view=false;
		System.out.println(" --------------------------------------------------------------------");
		System.out.println("|                            REJECTED LEAVES                        |");
		System.out.println(" --------------------------------------------------------------------");
		System.out.println(String.format("|%-25s|%-20s|%-21s|","Employee Name","Purpose","Status"));
		System.out.println(" --------------------------------------------------------------------");
		
		if(SettingLeaveLog.leaveLog.size()==0) {
			System.out.println("No leave log details found.");
		}
		else {
			for(LeaveLog e:SettingLeaveLog.leaveLog) {
				if(e.manager.equals(args)==true && e.status.equalsIgnoreCase("Reject")) {
					view=true;
					System.out.println(String.format("|%-25s|%-20s|%-21s|",e.empName,e.reason,e.status));
				}
			}
		}
		if(view==false) {
			System.out.println("No details found.");
		}
		System.out.println(" --------------------------------------------------------------------");
		System.out.println();
	}
	
	void viewAcceptedLeaves(String args) {
		boolean view=false;
		System.out.println(" --------------------------------------------------------------------");
		System.out.println("|                            ACCEPTED LEAVES                         |");
		System.out.println(" --------------------------------------------------------------------");
		System.out.println(String.format("|%-25s|%-20s|%-21s|","Employee Name","Purpose","Status"));
		System.out.println(" --------------------------------------------------------------------");
		if(SettingLeaveLog.leaveLog.size()==0) {
			System.out.println("No leave log details found.");
		}
		else {
			for(LeaveLog e:SettingLeaveLog.leaveLog) {
				if(e.manager.equals(args)==true && e.status.equalsIgnoreCase("Accept")) {
					view=true;
					System.out.println(String.format("|%-25s|%-20s|%-21s|",e.empName,e.reason,e.status));
				}
			}
		}
		if(view ==false) {
			System.out.println("No details found.");
		}
		System.out.println(" --------------------------------------------------------------------");
		System.out.println();
	}
	
	
}
	
public class ManagerPage {

	public static int main(String args) {
		Scanner scan = new Scanner(System.in);
		
		ManagerFunction manfun = new ManagerFunction();

		System.out.println("----------Welcome "+args.toUpperCase()+" to Manager Page---------------------");
		System.out.println();
		
		
		while(true) {
			System.out.println("1.view Employee Details.\n2.view Leave Request.\n3.view Granted Leave Request.\n4.view Rejected Leave Request.\n5.logout");
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
			case 1:manfun.viewDetails(args);
					break;
			case 2:manfun.viewLeaves(args);
					break;
			case 4:manfun.viewRejectedLeaves(args);
					break;
			case 3:manfun.viewAcceptedLeaves(args);
					break;
			case 5:System.out.println("--------------------------Logout Successfully--------------------------");
					System.out.println();
					return 0;
			}
		}

	}

}
