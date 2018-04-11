<%@include file="menu.jsp" %>
<input type="hidden" id="editMode" value="${editMode}">
    <!----------------------- list des hotels ------------------------>
    <div class="ui ten wide column" id="detailHotel">
		<s:choose>
			<s:when test="${chambres.size()!=0}">
				<h1>Les chambres de l'hotel : ${chambres.get(0).getNom_hotel()}</h1>
				<a class="ui labeled icon button"  href="index?page=${page}"><i class="icon home"></i> revenir a l'accueil</a>
				<table class="ui blue selectable table">
				  <thead>
					  <tr>
						<th>Id</th>
						<th>Nom de la chambre</th>
						<th>nombre petit lit</th>
						<th>nombre grand lit</th>
						<th>prix</th>
						 <th>Reserver</th>
					  </tr>
				  </thead>
				  <tbody>
					<s:forEach var="chambre" items="${chambres}">
					  <tr>
						<td>${chambre.id}</td>
						<td>${chambre.nom_chambre}</td>
						<td>${chambre.nbr_petit_lit}</td>
						<td>${chambre.nbr_grand_lit}</td>
						<td>${chambre.prix} $</td>
						<td>
							<a class="ui circular teal icon big button" href="/reserverModal?id_chambre=${chambre.id}&id_hotel=${chambre.id_hotel}&page=${page}"><i class="icon calendar"></i></a>
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
					  Oups ! il n'y a pas encore de chambre disponible dans cette hotel !
					</div>
				</div>
				<a class="ui labeled icon button"  href="/index?page=${page}"><i class="icon home"></i> revenir a l'accueil</a>
				<br>
			</s:otherwise>
		</s:choose>
	</div>  
</div><%--fin div ui grid to menu --%>
 <!------------------------------- Reserver Chambre ---------------------------------- -->
<s:choose>
  <s:when test="${chambres.size()!=0}">
	  <div class="ui modal reserverChambre">
		  <i class="close icon"></i>
		  <div class="ui huge message">
			   <div class="ui form">
			   <h1>Reservation de la chambre ${chambre.nom_chambre}</h1>
				 <form action="/reserver" method="post" id="modal" class="ui form">
					 <input type="hidden" name="id_chambre" value="${reservation.id_chambre}" required/>
					 <input type="hidden" name="id_client" value="${reservation.id_client}" required/>
					 <input type="hidden" name="id_hotel" value="${chambre.id_hotel}" required/>
					 <input type="hidden" name="page" value="${page}" required/>
					<div class="field">
						<label>Date Debut</label>
						<div class="ui left icon input">
							<input type="date" name="date_debut" required>
						  <i class="icon calendar"></i>
						</div>

					</div>
					<div class="field">
						<label>Date Fin</label>
						<div class="ui left icon input">
							<input type="date" name="date_fin" required>
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
				 </form>
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
  if($("#editMode").val()==="true"){
	$('.reserverChambre').modal('show');
  }
});
</script>