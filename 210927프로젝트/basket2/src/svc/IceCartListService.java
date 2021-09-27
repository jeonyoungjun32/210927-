package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.Cart;

public class IceCartListService {
	//멤버변수

	// 기본생성자

	// 메서드
	public ArrayList<Cart> getCartList(HttpServletRequest request){
		
		//request로 부터 요청한 클라리언트의 session영역을 얻어오기
		HttpSession session = request.getSession();
		
		//리스트 던져주고
		ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartList");
		
		
		return cartList;
		
	}
}
