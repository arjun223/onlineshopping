<div class="container">

	<div class="row">

		<div class="col-xs-12">
			<!--   diaplay breadcrumb navbar -->

			<ol class="breadcrumb">

				<li><a href="${contextRoot}/home">Home </a></li>
				<li><a href="${contextRoot}/show/all/products">Products</a></li>
				<li><a href="active">${product.name}</a></li>
				<li></li>

			</ol>




		</div>


	</div>


	<div class="row">

		<!--  display the product image-->

		<div class="col-xs-12 col-sm-4">
			<div class="thumbnail">
				<img alt="" src="${images}/${product.code}.jpg"
					class=" img img-responsive">



			</div>


		</div>
		<!-- display description in single page -->

		<div class="col-xs-12 col-sm-4">

			<h3>${product.name}</h3>
			<hr />

			<p>${product.description }</p>
			<hr />

			<h4>
				Price <strong> &#8377; ${product.unitPrice} /- </strong>
			</h4>
			<hr />


			<c:choose>
				<c:when test="${product.quantity <1 }">
					<h6>
						Qty.Avilable: <span style="color: red;"> out of Stock</span>
					</h6>
				</c:when>

				<c:otherwise>
					<h6>Qty.Avilable: ${product.quantity}</h6>
				</c:otherwise>
			</c:choose>



			<c:choose>
				<c:when test="${product.quantity <1 }">
					
						   <a href="javascript:void(0)" class="btn btn-success disabled"><strike>
								<span class="glyphicon glyphicon-shopping-cart"> </span>add to
								cart
						   </strike></a>
					
				</c:when>

				<c:otherwise>
					<a href="${contextRoot}/cart/add/${product.id}/product"
						class="btn btn-success"><span
						class="glyphicon glyphicon-shopping-cart"></span>add to cart</a>
				</c:otherwise>
			</c:choose>






			<a href="${contextRoot}/show/all/products" class="btn btn-success">back</a>


		</div>





	</div>




</div>