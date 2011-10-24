package raes

class LoginTagLib {
		def redirectMainPage = {
		  response.sendRedirect("${request.contextPath}/login/auth/")
		}
	  

}
