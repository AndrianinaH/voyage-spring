<%@include file="menu.jsp" %>
    <!----------------------- list des hotels ------------------------>
<s:choose>

<s:when test="${hotels.size()!=0}">
	<div class="ui ten wide column">
		<s:choose>
			<s:when test="${find!='' && find!='Commodite'}">
				<br>
				<h2>Recherche des hotels ou destinations avec le mot-cle [ <i>${find }</i> ]</h2>
			</s:when>
			<s:when test="${find=='Commodite'}">
				<br>
				<h2>Recherche des hotels par le filtre Commodite</h2>
			</s:when>
		</s:choose>

		<s:forEach var="hotel" items="${hotels}">
			<div class="ui items list_hotel">
				<div class="item">
					<a class="ui medium image" href="detailHotel?id_hotel=${hotel.id}&page=${page}">
						<img src="<s:url value="/assets/images/${hotel.image}"/>" style="height:200px">
					</a>
					<div class="content">
						<a class="header" id="nom_hotel" href="detailHotel?id_hotel=${hotel.id}&page=${page}">${hotel.nom_hotel}</a>
						<div class="meta">
							<div class="extra">
								<i class="large blue icon flag"></i><span id="_bleu">${hotel.nom_destination}</span>
							</div>
							<div class="extra">
								<i class="large orange icon filter"></i><span id="_jaune">${hotel.nom_commodite}</span>
							</div>
						</div>
						<div class="description">
							<p>${hotel.description} </p>
						</div>
						<div class="extra">
							<a class="ui right large floated green button" href="detailHotel?id_hotel=${hotel.id}&page=${page}">
								Voir l'offre
								<i class="right chevron icon"></i>
							</a>
						</div>
					</div>
				</div>
			</div>
		</s:forEach>

		<br>
	</div>
	<s:choose>
		<s:when test="${find==''}">
			<div class="ui pagination menu">
				<input type="hidden" value="${page}" id="page">
				<s:forEach var="i" begin="1" end="${nbPage/maxResult}" >
					<a href="/index?page=${i}" class="item pagination">${i}</a>
				</s:forEach>
			</div>
		</s:when>
	</s:choose>
</s:when>
	<s:otherwise>
		<div class="ui ten wide column">
			<div class="ui negative message">
				<i class="close icon"></i>
				<div class="header">
					Oups ! votre recherche n'a pas abouti !
				</div>
			</div>
			<a class="ui labeled icon button"  href="index?page=${page}"><i class="icon home"></i> revenir a l'accueil</a>
		</div>
	</s:otherwise>
</s:choose>
</div> <%--fin div ui grid to menu --%>
<footer>
	<div id="titre_footer">
		<span>Voyager Facile<span>
	</div>
	<div id="copyright">
		<span>Copyright Avril 2018 -Tous droits reserves</span>
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
  //----------- pagination ------------//
  $(".pagination").each(function() { 
	if($(this).text()===$("#page").val())
	{
		$(this).attr("class","item pagination active");
	}
  });
});
</script>