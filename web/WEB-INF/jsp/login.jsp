<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="s" %>
<!doctype html>
<html>
<head>
  <base href="/">
  <title>Connexion Voyager Facile</title>
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<meta charset="UTF-8">
  <link rel="stylesheet" href="assets/semantic.min.css" type="text/css"/>
  <link rel="stylesheet" href="assets/icon.min.css" type="text/css"/>
  <link rel="stylesheet" href="assets/login.css" type="text/css"/>
  <meta name="viewport" content="width=device-width">
</head>
<body>
<div  id="logo"> 
   <a href="/"><span id="logo_bleu">v<i class="icon world"></i>ya</span><span id="logo_jaune">ger </span><span id="logo_rouge">faci<i class="icon marker large"></i>e</span></a>
</div>
<div id="login2">
	<h1>Connexion Client</h1>
	<form:form action="/login" method="post" class="ui form" modelAttribute="client">
	    <div class="field">
			<form:input type="email" placeholder="email" path="email" required="true"/>
	    </div>
	    <div class="field">
			<form:input type="password" placeholder="mot de passe" path="mdp" required="true"/>
	    </div>
	  	<button type="submit" class="ui fluid inverted big button">Se Connecter</button>
	  	<p>${loginError} </p>
	  	<a href="#">Vous n'avez pas encore de compte, inscrivez vous ici</a>
	  	<br>
	  	<br>
	</form:form>
</div>

</body>
</html>


