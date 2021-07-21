<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>用户注册</h1>

<form action="${pageContext.request.contextPath}/user/register" method="post">
    <label>
        用户名: <input type="text" name="username"/>
    </label><br>
    <label>
        密 码: <input type="password" name="password"/>
    </label><br>
    <input type="submit" value="注册"/>
</form>
</body>
</html>