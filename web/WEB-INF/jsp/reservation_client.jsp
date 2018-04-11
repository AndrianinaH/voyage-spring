<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="menu.jsp" %>
<input type="hidden" id="editMode" value="${editMode}">
    <!----------------------- list des hotels ------------------------>
    <div class="ui ten wide column" id="detailHotel">
		<s:choose>
			<s:when test="${reservations.size()!=0}">
				<h1>Les reservations de : ${session.client_voyage.nom_client}</h1>
				<a class="ui labeled icon button"  href="index"><i class="icon home"></i> revenir a l'accueil</a>
				<table class="ui blue selectable table">
					<thead>
					<tr>
						<th>Chambre</th>
						<th>Date de debut</th>
						<th>Date de fin</th>
						<th>Modifier/Annuler</th>
					</tr>
					</thead>
					<tbody>
					<s:forEach var="reservation" items="${reservations}">
						<tr>
							<td>${reservation.nom_chambre}</td>
							<td>${reservation.formatDate_debut()}</td>
							<td>${reservation.formatDate_fin()}</td>
							<td>
								<a class="ui circular olive icon big button" href="/modifReservation?id_reservation=${reservation.id}"><i class="icon edit"></i></a>
								<a class="ui circular red icon big button" href="/annulerReservation?id_reservation=${reservation.id}"><i class="icon trash"></i></a>
							</td>
						</tr>
					</s:forEach>
					</tbody>
				</table>
				<br>
			</s:when>
			<s:otherwise>
				<div class="ui negative message">
					<i class="close icon"></i>
					<div class="header">
						Vous n'avez pas encore reserver de chambre !
					</div>
				</div>
				<a class="ui labeled icon button"  href="/"><i class="icon home"></i> revenir a l'accueil</a>
				<br>
			</s:otherwise>
		</s:choose>

	</div>  
</div> <%--fin div ui grid to menu --%>
 <!------------------------------- Reservation Client ---------------------------------- -->
<s:choose>
	<s:when test="${reservations.size()!=0}">
		<div class="ui modal modifReserver">
			<i class="close icon"></i>
			<div class="ui huge message">
				<div class="ui form">
					<h1>Reservation de la chambre ${chambre.nom_chambre}</h1>
					<form:form action="/updateReservationClient" method="post" id="modal" class="ui form" modelAttribute="reservation">
						<div class="field">
							<label>Date Debut</label>
							<form:hidden path="id" required="true"/>
							<form:hidden path="id_chambre" required="true"/>
							<form:hidden path="id_client" required="true"/>
							<div class="ui left icon input">
								<form:input path="date_debut" type="date" required="true"/>
								<i class="icon calendar"></i>
							</div>
						</div>
						<div class="field">
							<label>Date Fin</label>
							<div class="ui left icon input">
								<form:input path="date_fin" type="date" required="true"/>
								<i class="icon calendar"></i>
							</div>

						</div>
						<div class="actions">
							<div class="ui black deny button">
								annuler
							</div>
							<button type="submit" class="ui inverted green icon button">Enregistrer</button>
						</div>
						<br>
						<br>
					</form:form>
				</div>
			</div>
		</div>
	</s:when>
</s:choose>

<footer>
	<div id="titre_footer">
		<span>Voyager Facile<span>
	</div>
	<div id="copyright">
		<span>Copyright Mai 2017 -Tous droits reserves</span>
		<p>creer par : Rakotoarisoa Andrianina</p>
	</div>
</footer>
</body>
</html>
<script type="text/javascript" src="assets/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="assets/semantic.min.js"></script>
<script>
$(document).ready(function() {
  $('.message .close').on('click', function() {$(this).closest('.message').transition('fade');});
  $('select.dropdown').dropdown();
  $(".browse").popup({
    popup: '.ui.popup',
    on    : 'click'
  });
  $('.ui.checkbox').checkbox();
  //---------- modal reserver --------//
  console.log($("#editMode").val());
  if($("#editMode").val()==="true"){
	$('.modifReserver').modal('show');
  }
});
</script>