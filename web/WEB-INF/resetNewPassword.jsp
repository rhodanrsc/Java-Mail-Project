
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
    </head>
    <body>
        <h1>Enter a new password</h1>
        <form method="post" action="">
            <input type="text" name="newPass" value="">
            <br>
            <input type="submit" value="Submit">
            <input type="hidden" name="action" value="resetPass">
            <input type="hidden" name="uuid" value="${reset_password_uuid}">
        </form>
    </body>
</html>
