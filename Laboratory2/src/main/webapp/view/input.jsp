<%-- 
    Document   : input
    Created on : Oct 10, 2022, 10:36:24 PM
    Author     : Tudor Onofrei
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> JSP Form caller </title>
    </head>
    <body>
        <form action="/Laboratory2/view/wordGen" method="get">
            <label for="word"> Introduce word: </label> <input type="text" id="word" name="word"><br>
            <label for="size"> Introduce sequence length: </label> <input type="text" id="size" name="size"><br>
            <label for="angles"> Introduce the number of angles the black figure contains: </label> <input type="text" id="angles" name="captchaAngles"><br>
            <p> Select a category: </p>
            
            
            <select type="text" name="categoryParameter" id="categoryParameter">
                <c:forEach var="option" items="${options}" varStatus="loop" begin="0">
                    <c:if test="${option eq cookieCategory}">
                        <option selected="true"> ${option} </option>
                    </c:if>
                    <c:if test="${option ne cookieValue}">
                        <option> ${option} </option>
                    </c:if>
                </c:forEach>
            </select>
            <br>
            <img src="captchaGen" /> <br>
            
            <input type="submit" value="Generate Sequences!">
        </form>
    </body>
</html>
