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
input.normal, select {
    float: right;
    margin-right: 16%;
    width: 315px;
}
input.submit {
margin-left:135px
}
</style>
<title>Wiki Page Sex Identification</title>
</head>
<body>
<h1>Wiki Page Sex Identification</h1>
</br>
<p style="text-align:center;"> <strong>Instructors:</strong> Dr. Amit Sheth, Dr. Tanvi Banerjee <strong style="margin-left:30px;">Mentor:</strong> Sumant Kulkarni <strong style="margin-left:30px;">Authors:</strong> William Hatfield, Utkarshani Jaimini, Uday Sagar Panjala</p>
</br></br></br></br></br>
<div align="center">
<form action="/wikiSexIdentifier/result" method="post">
<p>
Wikipedia Page Url: <input class="normal" name="wiki_url" type="text" />
</p>
</br>
<p>
Page Sections to be POS Tagged: <select name="page_section_model">
  <option value="1">First Section</option>
  <option value="2">First Two Sections</option>
  <option value="0">Whole Wiki Page</option>
</select>
</p>
</br>
<p>
Models to be compared against: <select name="models_to_compare">
  <option value="1">First Section</option>
  <option value="2">First Two Sections</option>
  <option value="0">Whole Wiki Page</option>
</select>
</p>
</br>
<p>
Classifier to be used: <select name="classifier_name">
  <option value="naive_bayes">Naive Bayes Classifier</option>
</select>
</p>
</br></br>
<input class="submit" type="submit" value="Let me know the result!" />
</form>
</div>
</body>
</html>