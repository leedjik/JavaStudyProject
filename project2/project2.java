package JAVASTUDY;

import java.util.Scanner;

/*
 * project1 + "scanner 입력, 출력"
 */

public class project2 {

	static Scanner sc = new Scanner(System.in);
	
	public static void showMenu() {
		System.out.println("선택하세요..");
		System.out.println("1. 데이터 입력");
		System.out.println("2. 프로그램 종료");
		System.out.print("선택 : ");		
	}
	public static void inputData() {
		System.out.print("이름 : ");
		String name = sc.nextLine();
		System.out.print("전화번호 : ");
		String phoneNumber = sc.nextLine();
		System.out.print("생년월일 : ");
		String birthday = sc.nextLine();
		
		PhoneInfo pi = new PhoneInfo(name, phoneNumber, birthday);
		System.out.println("\n입력된 정보 출력...");
		pi.printInfo();
	}
	public static void main(String[] args) {
		int choice = 0;
		
		while(true) {
			showMenu();
			choice = sc.nextInt();
			sc.nextLine();
			
			if(choice == 1) {
				inputData();
			}
			else if(choice == 2) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		}
	}
}

