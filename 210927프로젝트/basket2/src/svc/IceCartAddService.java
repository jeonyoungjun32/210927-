package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.Cart;
import dao.IceDAO;
import vo.Ice;

public class IceCartAddService {
	//장바구니 만들기전 CartDAO생성부터

	public Ice getCartIce(int id) {

		// 1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		// 2.싱글톤 패턴:DogDAO객체 생성
		IceDAO iceDAO = IceDAO.getInstance();
		// 3.DB작업에 사용될 Connection객체를 DogDAO의 멤버변수로 삽입하여 DB 연결
		iceDAO.setConnection(con);

		// 아이스크림 id로 호출받아옴
		Ice ice = iceDAO.selectIec(id);

		// update,delete,insert = 성공하면 commit; 실패하면 rollback
		// select 는 제외

		close(con);
		return ice;

	}

	public void addCart(HttpServletRequest request, Ice cartIce) {
		// session영역!!

		// 현재 session영역에 저장되어 있는 장바구니 목록 얻어오기
		HttpSession session = request.getSession();//// getSession을 하는 이유 없으면 만들어지고 있으면 기존Session을 가져온다

		ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartList");

		// 장바구니에 담긴지 확인
		if (cartList == null) {
			// 장바구니가 없으면 생성하고
			cartList = new ArrayList<Cart>();
			session.setAttribute("cartList", cartList);
		}
		//같은 상품이 있으면 1증가
		boolean isnewCart = true;

		for(int i=0;i<cartList.size();i++) {
			if(cartIce.getKind().equals(cartList.get(i).getQty()+1)); //똑같으면 1증가
			break; //하고 빠져나가자잉
		}
		if(isnewCart == true) {
			Cart cart = new Cart();
			cart.setImage(cartIce.getImage());//이미지 뽑고
			cart.setKind(cartIce.getKind());
			cart.setPrice(cartIce.getPrice());
			cart.setQty(1);//수량은 처음이닌깐 1로 셋팅
			
			cartList.add(cart);
		}
		
		
	
	}
}
