<%-- webapp/WEB-INF/views/fortune.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>당신의 운세를 봐드립니다</title>
</head>
<body>
<h1>당신의 운세를 봐드립니다</h1>
<section>
    <%-- 입력 파트 --%>
    <form method="post" style="display: flex; flex-direction: column; gap: 10px; align-items: start">
        <textarea style="resize: none"
                  name="koreanName"
                  placeholder="당신의 이름을 입력하세요"></textarea>
        <input type="submit">
    </form>
</section>
<section>
    <%-- 출력 --%>
    <c:choose>
        <c:when test="${not empty fortuneResult}">
            <p>${fortuneResult.text}</p>
            <p>${fortuneResult.timestamp}</p>
        </c:when>
        <c:otherwise>
            <p>지금 오늘의 운세를 확인해보세요!</p>
        </c:otherwise>
    </c:choose>
</section>
</body>
</html>
