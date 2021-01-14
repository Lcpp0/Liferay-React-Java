<%@ include file="/init.jsp" %>

<portlet:resourceURL var="resultsPlanetListURL" id="resultsPlanetListURL">
</portlet:resourceURL>


<%JSONArray resultsPlanetList=(JSONArray) request.getAttribute("resultsPlanetList");
%>

<div id="<portlet:namespace />-root"></div>

<aui:script require="<%= mainRequire %>">
	main.default('<portlet:namespace />-root');
</aui:script>


<textarea id="planetList" name="hide" style="display:none;">"${resultsPlanetList}"</textarea>
<%-- <c:out value = "${resultsPlanetList}"/></br> --%>