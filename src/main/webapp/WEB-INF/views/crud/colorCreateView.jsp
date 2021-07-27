<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/lib/utilities.css" />
</head>
<body>

    <div class="container">
    
    	<jsp:include page="./header.jsp" />
    	
		<h3 class="msg">${message}</h3>
		
        <form id='' method='POST' action='${pageContext.request.contextPath}/create-color'>
            <div class="form-control">
                <label for="color_name"></label>
                <input type="text" name="color_name" placeholder="color_name" id="color_name">
            </div>

            <div class="form-control">
                <label for="color_code"></label>
                <input type="text" name="color_code" placeholder="color_code" id="color_code">
            </div>
  
            <button type="submit" class="btn">Submit</button>
        </form>
    </div>

</body>
</html>