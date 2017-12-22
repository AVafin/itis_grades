<#ftl encoding='UTF-8'>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Hello page</title>
</head>
<body>
<h1>This is a simple java servlet.</h1>
<h1>Hello, ${username}</h1>
<#if colNames??>
<#list colNames as col>
    <div>${col}</div>
</#list>

</#if>
</body>
</html>