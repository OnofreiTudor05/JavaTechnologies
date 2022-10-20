<%-- 
    Document   : result
    Created on : Oct 10, 2022, 11:12:04 PM
    Author     : Tudor Onofrei
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result page</title>
    </head>
    <body>
        <table style="border: 1px solid black; border-collapse: collapse; margin: 2px; padding: 2px;">
            <tr style="border: 1px solid black; margin: 2px; padding: 2px;">
                <th style="border: 1px solid black; margin: 2px; padding: 2px;"> No. </th>
                <th style="border: 1px solid black; margin: 2px; padding: 2px;"> Sequence </th>
            </tr>
            
            <c:forEach var="sequence" items="${sequences}" varStatus="loop" begin="0">
                <tr style="border: 1px solid black;">
                    <td style="border: 1px solid black; margin: 2px; padding: 2px;"> ${loop.index}</td>
                    <td style="border: 1px solid black; margin: 2px; padding: 2px;"> ${sequence} </td>
                </tr>
            </c:forEach>
            
        </table>
        <!--?= request.getAttribute("categoryParameter") %-->
    </body>
</html>
