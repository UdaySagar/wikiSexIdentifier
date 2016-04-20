<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
<style>
h1 {
text-align: center;
}
</style>
<title>Wiki Page Sex Identification</title>
</head>
<body>
<h1>Wiki Page Sex Identification Results Page</h1>
</br>
<p style="text-align:center;"> <strong>Instructors:</strong> Dr. Amit Sheth, Dr. Tanvi Banerjee <strong style="margin-left:30px;">Mentor:</strong> Sumant Kulkarni <strong style="margin-left:30px;">Authors:</strong> William Hatfield, Utkarshani Jaimini, Uday Sagar Panjala</p>
</br></br></br></br></br>
<div align="center">

<p>The subject of the given Wiki Page, ${wiki_url} is : <strong style="font-size:2em;color:green;">${winner}</strong></p>
</br></br>
<p>
${labelOf_first} : <strong style="font-size:2em;">${Score_First}</strong>
</p>
<p>
${labelOf_second} : <strong style="font-size:2em;">${Score_Second}</strong>
</p>
<p>
${labelOf_third} : <strong style="font-size:2em;">${Score_Third}</strong>
</p>
</br></br></br>
<a href="http://130.108.85.184:8080/wikiSexIdentifier/">Go back to Home Page</a>
</div>
</body>
</html>