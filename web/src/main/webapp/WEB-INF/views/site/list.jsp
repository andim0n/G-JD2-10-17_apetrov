<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h4 class="header">Site</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th>id</th>
			<th>name</th>
			<th>baseTemplate</th>
			<th>menu</th>
			<th>created</th>
			<th>updated</th>
			<th></th>
		</tr>
		<c:forEach var="site" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${site.id}" /></td>
				<td><c:out value="${site.name}" /></td>
				<td><c:out value="${site.baseTemplate}" /></td>
				<td><c:out value="${site.menu}" /></td>

				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${site.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${site.updated}" /></td>

				<td class="right"><a class="btn-floating"
					href="${pagesSite}/${site.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${pagesSite}/${site.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesSite}/${site.id}/delete"><i class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a class="waves-effect waves-light btn right" href="${pagesSite}/add"><i
	class="material-icons">add</i></a>
