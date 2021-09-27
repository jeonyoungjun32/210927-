package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.IceCartAddService;
import vo.ActionForward;
import vo.Ice;

public class IceCartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 장바구니 항목에 추가하기
		// 1. IceService만들고
		IceCartAddService iceCartAddService = new IceCartAddService();

		// 2. int타입 id로 받기
		int id = Integer.parseInt(request.getParameter("id"));
		
		//iceView에서 id를 던지닌깐id로 받는다
		Ice cartIce = iceCartAddService.getCartIce(id);
		
		//이제 다시 담아서 보내기
		iceCartAddService.addCart(request, cartIce);
		
		return new ActionForward("iceCartList.ice", true);
	}

}
