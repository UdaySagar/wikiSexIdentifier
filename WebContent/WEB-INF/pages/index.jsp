<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Wiki Page Sex Identification</title>
</head>
<body>
<h1>Wiki Page Sex Identification</h1>
</br></br>
<p> Instructors: Dr. Amit Sheth, Dr. Tanvi Banerjee</p>
<p> Mentor: Sumant Kulkarni</p>
<p> Authors: William Hatfield, Utkarshani Jaimini, Uday Sagar Panjala</p>
</br></br>
<form action="/wikiSexIdentifier/result" method="post">
<p>
Wikipedia Page Url: <input name="wiki_url" type="text" />
</p>
<p>
Page Sections to be POS Tagged: <select name="page_section_model">
  <option value="first_section">First Section</option>
  <option value="two_sections">First Two Sections</option>
  <option value="whole_page">Whole Wiki Page</option>
</select>
</p>
<p>
Classifier to be used: <select name="classifier_name">
  <option value="naive_bayes">Naive Bayes Classifier</option>
</select>
</p>
<input type="submit" value="Let me know the result!" />
</form>
</body>
</html>