package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Cart;
import svc.IceCartListService;
import vo.ActionForward;

public class IceCartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 장바구니 리스트 보자잉!

		// 1. CartListService만들고!!
		IceCartListService iceCartListService = new IceCartListService();

		// 2. 장바구니 메서드를 얻어왔다 request가 있어야 값을 받아올수 있다
		ArrayList<Cart> cartList = iceCartListService.getCartList(request);

		// 총 금액 계산
		int totalMoney = 0;
		int money = 0;
		
		for(int i = 0; i<cartList.size();i++) {
			//돈은     장바구니의 리스트중 가격에 X  장바구니리스트에 수량   !!
			money = cartList.get(i).getPrice()*cartList.get(i).getQty();
			totalMoney += money;
		}
		// 총 금액과 1개가격을 jsp로 넘겨주자
		request.setAttribute("totalMoney", totalMoney);
		request.setAttribute("cartList", cartList);
		
		ActionForward forward = new ActionForward("iceCartList.jsp", false);
		
		
		return forward;
	}

}
