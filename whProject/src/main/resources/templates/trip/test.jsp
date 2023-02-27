<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"   
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorate="~{/layout}">
<head>
<meta charset="UTF-8">
</head>
<body>
<table class="table">
<tbody>
	<tr th:each="trip, sts = ${tripList}">
	<td th:text="${tripList.title }"></td>
	<td th:text="${tripList.addr1 }"></td>
	</tr>
</tbody>
</table>
</body>
</html>