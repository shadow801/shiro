<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>用户登陆</h1>

<form action="${pageContext.request.contextPath}/user/login" method="post">
    <label>
        用户名: <input type="text" name="username"/>
    </label><br>
    <label>
        密 码: <input type="password" name="password"/>
    </label><br>
    <label>
        请输入验证码: <input type="text" name="code"><img src="${pageContext.request.contextPath}/user/getImage" alt="">
    </label><br>
    <input type="submit" value="登陆"/>
</form>
</body>
</html>