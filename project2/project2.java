package JAVASTUDY;

import java.util.Scanner;

/*
 * project1 + "scanner �Է�, ���"
 */

public class project2 {

	static Scanner sc = new Scanner(System.in);
	
	public static void showMenu() {
		System.out.println("�����ϼ���..");
		System.out.println("1. ������ �Է�");
		System.out.println("2. ���α׷� ����");
		System.out.print("���� : ");		
	}
	public static void inputData() {
		System.out.print("�̸� : ");
		String name = sc.nextLine();
		System.out.print("��ȭ��ȣ : ");
		String phoneNumber = sc.nextLine();
		System.out.print("������� : ");
		String birthday = sc.nextLine();
		
		PhoneInfo pi = new PhoneInfo(name, phoneNumber, birthday);
		System.out.println("\n�Էµ� ���� ���...");
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
				System.out.println("���α׷��� �����մϴ�.");
				break;
			}
		}
	}
}

