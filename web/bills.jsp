<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="controller" metod="POST">
            <hr>
            <label>Insert Bill Name</label>
            <input type="text" name ="name"/>
            <label>Insert Bill Amount</label>
            <input type="text" name="amount"/>
            <input type="hidden" name="action" value="insert">
            <input type="submit" value="INSERT"/>
            <hr>
        </form>
        <br>
        <form action="controller" value="POST">
            <table border="1">
                <tr><th>Bills</th><th>Amount</th><th>Delete</th></tr>
            <c:forEach var="bill" items="${bills}">
                    <tr>
                        <td>${bill.name}</td>
                        <td> \$${bill.amount}</td>
                        <td><button name="dButton" value="${bill.id}">DELETE</button></td>
                    </tr>
            </c:forEach>
                    <tr>
                        <td><b>TOTAL</b>
                        </td><td><b>\$${total}</b></td>
                        <input type="hidden" name="action" value="delete"/>
                        <td></td>
                    </tr>
            </table>
        </form>
    </body>
</html>