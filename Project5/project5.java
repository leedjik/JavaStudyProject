/*
 * Project5
 *   1. interface 기반의 상수 표현
 *   2. PhoneBook instance는 1개만 생성하도록
 * Project6 부터는 기존 코드에 추가 및 변경
 *   1. Exception 처리하기
 * Project7
 *   1. HashSet을 이용해 동일이름에 대해 동일 인스턴스로 취급해주기(번호, 학년 등은 상관 없음)
 *   2. 자료 저장을 set으로 바꿔주기
 */

package Project5;

import java.util.HashSet;
import java.util.Scanner;

interface INIT_MENU
{
	int INPUT = 1, SEARCH = 2, DELETE = 3, EXIT = 4;
}

interface INPUT_SELECT
{
	int NORMAL = 1, UNIV = 2, COMPANY = 3;
}

// 참고로 제너릭클래스는 exception을 상속받을 수 없다.
class MenuInputException extends Exception
{
	int wrongInput;
	
	public MenuInputException(int wrongInput) {
		super("잘못된 선택이 이뤄졌습니다.");
		this.wrongInput = wrongInput;
	}
	public void showWorngMessage() {
		System.out.println(this.wrongInput + "에 해당하는 선택은 존재하지 않습니다.");
	}
	
}
class PhoneInfo{
	String name;		// 이름
	String phoneNumber;	// 전화번호
	
	PhoneInfo(String name, String phoneNumber){
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	void showPhoneInfo() {
		System.out.println("Name : "+ this.name);
		System.out.println("phoneNumber : "+ this.phoneNumber);
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public boolean equals(Object obj) {
		if(name.compareTo(((PhoneInfo)obj).name) == 0) return true;
		return false;
	}
}

class PhoneUnivInfo extends PhoneInfo{
	String major;
	int year;
	
	PhoneUnivInfo(String name, String phoneNumber, String major, int year) {
		super(name, phoneNumber);
		this.major = major;
		this.year = year;
	}
	void showPhoneInfo() {
		super.showPhoneInfo();
		System.out.println("major : "+ major);
		System.out.println("year : "+ year);
	}
}

class PhoneCompanyInfo extends PhoneInfo{
	String company;

	PhoneCompanyInfo(String name, String phoneNumber, String company) {
		super(name, phoneNumber);
		this.company = company;
	}
	void showPhoneInfo() {
		super.showPhoneInfo();
		System.out.println("major : "+ company);
	}
}

class PhoneBookManager{
	final int MAX_CNT = 100;
	HashSet<PhoneInfo> memory = new HashSet<PhoneInfo>();
	
	static PhoneBookManager inst = null;
	public static PhoneBookManager createManagerInst() {
		if(inst == null) {
			inst = new PhoneBookManager();
		}
		
		return inst;
	}
	//private PhoneBookManager() {}
	
	private PhoneInfo inputNormal() {
		System.out.print("이름 : ");
		String name = MenuViewer.sc.nextLine();
		System.out.print("전화번호 : ");
		String phone = MenuViewer.sc.nextLine();
		
		return new PhoneInfo(name, phone);
	}
	
	private PhoneUnivInfo inputUniv() {
		System.out.print("이름 : ");
		String name = MenuViewer.sc.nextLine();
		System.out.print("전화번호 : ");
		String phone = MenuViewer.sc.nextLine();
		System.out.print("전공 : ");
		String major = MenuViewer.sc.nextLine();
		System.out.print("학년 : ");
		int year = MenuViewer.sc.nextInt();
		MenuViewer.sc.nextLine();
		
		return new PhoneUnivInfo(name, phone, major, year);
	}
	
	private PhoneInfo inputCompany() {
		System.out.print("이름 : ");
		String name = MenuViewer.sc.nextLine();
		System.out.print("전화번호 : ");
		String phone = MenuViewer.sc.nextLine();
		System.out.print("회사 : ");
		String company = MenuViewer.sc.nextLine();
		
		return new PhoneCompanyInfo(name, phone, company);		
	}
	
	public void inputData() throws MenuInputException {
		System.out.println("데이터 입력을 시작합니다..");
		System.out.println("1. 일반, 2. 대학, 3. 회사");
		System.out.print("선택>> ");
		
		int inputType = MenuViewer.sc.nextInt();
		MenuViewer.sc.nextLine();
		
		if(inputType > INPUT_SELECT.COMPANY || inputType < INPUT_SELECT.NORMAL)
			throw new MenuInputException(inputType);

		PhoneInfo info = null;
		
		if(inputType == INPUT_SELECT.NORMAL) {
			info = inputNormal();
		}
		else if(inputType == INPUT_SELECT.UNIV) {
			info = inputUniv();
		}
		else if(inputType == INPUT_SELECT.COMPANY) {
			info = inputCompany();
		}
		
		boolean alreadyAdded = memory.add(info);
		
		if(alreadyAdded == true) {
			System.out.println("데이터 입력이 완료되었습니다. \n");
		}
		else {
			System.out.println("이미 저장된 데이터입니다. \n");
		}
	}
	
	public void searchData() throws MenuInputException {
		System.out.println("데이터 검색을 시작합니다..");
		
		System.out.print("이름 : ");
		String name = MenuViewer.sc.nextLine();
		PhoneInfo info = search(name);
		
		if(info == null) {
			System.out.println("해당하는 데이터가 존재하지 않습니다.\n");
		}
		else {
			info.showPhoneInfo();
			System.out.println("데이터 검색이 완료되었습니다.\n");
		}
	}
	
	public void deleteData() throws MenuInputException {
		System.out.println("데이터 삭제를 시작합니다.");
		
		System.out.print("이름 : ");
		String name = MenuViewer.sc.nextLine();
		
		PhoneInfo info = search(name);
		
		if(info == null) {
			System.out.println("해당하는 데이터가 존재하지 않습니다.\n");
		}
		else {
			memory.remove(info);
			System.out.println("데이터 삭제가 완료되었습니다. \n");
		}
	}
	private PhoneInfo search(String name) {

		for(PhoneInfo now: memory) {
			if(name.equals(now.name)) return now;
		}
		
		return null;
	}
}

class MenuViewer{
	public static Scanner sc = new Scanner(System.in);
	
	public static void showMenu() {
		System.out.println("선택하세요...");
		System.out.println("1. 데이터 입력");
		System.out.println("2. 데이터 검색");
		System.out.println("3. 데이터 삭제");
		System.out.println("4. 프로그램 종료");
		System.out.print("선택 : ");
	}
}
public class project5 {

	public static void main(String[] args) throws MenuInputException {
		PhoneBookManager manager = PhoneBookManager.createManagerInst();
		int inputType = 0;
		
		while(true) {

			try {
				MenuViewer.showMenu();
				inputType = MenuViewer.sc.nextInt();
				MenuViewer.sc.nextLine();
				
				if(inputType < INIT_MENU.INPUT || inputType > INIT_MENU.EXIT)
					throw new MenuInputException(inputType);
				
				if(inputType == INIT_MENU.INPUT) {
					manager.inputData();
				}
				else if(inputType == INIT_MENU.SEARCH) {
					manager.searchData();
				}
				else if(inputType == INIT_MENU.DELETE) {
					manager.deleteData();
				}
				else if(inputType == INIT_MENU.EXIT){
					System.out.println("프로그램을 종료합니다.");
					return;
				}				
			} catch(MenuInputException e){
				e.showWorngMessage();
				System.out.println("메뉴 선택을 처음부터 다시 진행합니다.\n");
			}
		}
	}
}
