package va.validates;

import javax.servlet.http.HttpServlet;

import net.bytebuddy.asm.Advice.Return;

public class Validate extends HttpServlet{
	public static boolean checkHoTen(String hoTen) {
		boolean check=true;
		String regName="[0-9._+-]{1,}";
		if(hoTen.matches(regName)) {
			check = false;
		}
		return check;
	}
	public static boolean checkEmail(String email) {
		String regEmail = "^[0-9A-Za-z-_+.]{1,}+@+[a-zA-z]{2,}+\\.+[A-Za-z]{2,6}$";
		boolean check=true;
		if(!email.matches(regEmail)) {
			check = false;
		}
		return check;
	}

	public static boolean checkSdt(String sdt) {
		boolean check=true;
		String regSdt = "[0-9]{10,}";
		if(!sdt.matches(regSdt)) {
			check = false;
		}
		return check;
	}
}
