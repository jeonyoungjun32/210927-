package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.IceCartAddAction;
import action.IceCartListAction;
import action.IceListAction;
import action.IceViewAction;
import vo.ActionForward;

/**
 * Servlet implementation class Cartcontroller
 */
//확장자가 cart이면 무조건 DogFrontController로 이동하여 doProcess()메서드 실행함
@WebServlet("*.ice")
public class Cartcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Cartcontroller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);

	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		// 요청객체로부터 '프로젝트명+파일경로'까지 가져옴(예)/project/boardWriteForm.dog
		String requestURI = request.getRequestURI();
		// 요청 URL : http://localhost:8090/project/boardWriteForm.dog
		// 요청 URI : /project/boardWriteForm.dog

		// 요청객체로부터 '프로젝트 path'만 가져옴(예)/project
		String contextPath = request.getContextPath();

		/*
		 * URI에서 contextPath길이만큼 잘라낸 나머지 문자열 /project/boardWriteForm.dog - /project =
		 * "/boardWriteForm.dog"
		 */
		String command = requestURI.substring(contextPath.length());// (index 8~끝까지) 부분문자열 반환

		/*
		 * 요청이 파악되면 해당 요청을 처리하는 각 Action클래스를 사용해서 요청 처리
		 * 
		 * 각 요청에 해당하는 Action클래스 객체들을 다형성을 이용해서 동일한 타입(Action)으로 참조하기 위해
		 * 
		 * 'Action 인터페이스' 타입의 변수 선언(혜574p)
		 */
		Action action = null;
		ActionForward forward = null;

		/*
		 * 글쓰기 페이지를 열어주는 요청인 경우는 특별한 비지니스 로직을 실행할 필요없이
		 * 
		 * 글쓰기 할 수 있는 뷰페이지로만 포워딩하면 됨
		 */
		if (command.equals("/iceList.ice")) { // 아이스크림 목록 보기
			action = new IceListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("IceViewAction 오류 " + e);
			}
		} else if (command.equals("/iceView.ice")) { // 아이스크림 1개 보기
			action = new IceViewAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("IceViewAction 오류 " + e);
			}
		} else if (command.equals("/iceCartAdd.ice")) { // 장바구니에 추가하기
			action = new IceCartAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("IcecCartAddAction 오류 " + e);
			}
		} else if (command.equals("/iceCartList.ice")) { // 장바구니에 리스트
			action = new IceCartListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				System.out.println("IcecCartListAction 오류 " + e);
			}
		}
		
	      if(forward != null) {
	          if(forward.isRedirect()) {//isRedirect=true : 주소변경(새요청), request
	             response.sendRedirect(forward.getPath());//응답-리다이렉트 방식
	          } else {//isRedirect=false : 디스패치 방식
//	          RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
	          //dispatcher.forward(request, response);//기존 요청, 기존 응답 그대로 보내므로 주소 그대로 
	          request.getRequestDispatcher(forward.getPath()).forward(request, response);
	          }
	       }
	}
	

}
