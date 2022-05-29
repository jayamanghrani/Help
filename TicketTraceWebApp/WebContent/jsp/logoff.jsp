<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
        <title>logoff</title>
        <script type="" language="JavaScript">
               function invalidate()
        { 
         <% session.invalidate();%>
         window.close();
          }
       
        </script>
    </head>
    <body onload="invalidate()" bgcolor="">
        <table align="center">
            <tr>
                <td align="center">Successfully Logged Out!</td>
            </tr>
        </table>
    </body>
</html>