package action;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.IceListService;
import vo.ActionForward;
import vo.Ice;

public class IceListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 아이스크림 리스트 보는곳
		//아이스크림 이미지를 ArrayList에 담음
		ArrayList<String> todayImageList = new ArrayList<String>();
		
		//클라이언트에서 넘어온 쿠키배열로 받고  상품이미지를 쿠키에 저장
		Cookie[] cookieArray = request.getCookies();
		
		if(cookieArray != null) {
			for(int i = 0; i<cookieArray.length;i++) {
				if(cookieArray[i].getName().startsWith("today")) {
					todayImageList.add(cookieArray[i].getValue());
				}
			}
			
		}
		//아이스크림 목록보기 요청을 처리하는 서비스 객체 생성 
		//svc에 만들러가야지~
		IceListService iceListService = new IceListService(); 
		
		//등록되어 있는 아이스크림 상품 정보 ArrayList타입으로 얻어온다
		ArrayList<Ice> iceList = iceListService.getIceList();
		
		//request영역에 iceList이름으로 '아이스크림 상품 목록 정보'를 속성값으로 공유!
		request.setAttribute("iceList", iceList);
		
		//request영역에 todayImageList이름으로 '오늘 본 아이스크림 목록 정보'를 속성 값 으로 받아 공유
		request.setAttribute("todayImageList",todayImageList);
		
		//포워딩 정보를 ActionForward 객체로 생성하고 리턴
		//디스패치 방식, jsp에 보내기
		ActionForward forward = new ActionForward("iceList.jsp", false);
		
		return forward;
	}

}
