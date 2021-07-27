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
		
        <form id='' method='POST' action='${pageContext.request.contextPath}/create-size'>
            <div class="form-control">
                <label for="size_number"></label>
                <input type="number" name="size_number" placeholder="size_number" id="size_number">
            </div>

            <div class="form-control">
                <label for="size_detail"></label>
                <input type="text" name="size_detail" placeholder="size_detail" id="size_detail">
            </div>
  
            <button type="submit" class="btn">Submit</button>
        </form>
    </div>

</body>
</html>