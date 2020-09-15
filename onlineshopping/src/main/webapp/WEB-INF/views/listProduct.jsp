<div class="container">

	<div class="row">

		<!--  first would be  display sidebar-->

		<div class="col-md-3">
			<p class="lead">Shop Name</p>
			<div class="list-group">
				<c:forEach items="${categories}" var="category">
					<a href="${contextRoot}/show/category/${category.id}/products"
						class="list-group-item" id="a_${category.name}">${category.name}</a>
				</c:forEach>

			</div>
		</div>


		<!--  display the actual product-->

		<div class="col-md-9">
			<div class="row">
				<div class="col-lg-12">
					<c:if test="${userClickAllProducts == true}">
						<script>
							window.categoryId = '';
						</script>

						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">All Products</li>

						</ol>
					</c:if>

					<c:if test="${userClickCategoryProducts == true}">

						<script>
							window.categoryId = '${category.id}';
						</script>

						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">Category</li>
							<li class="active">${category.name}</li>

						</ol>
					</c:if>

				</div>

			</div>

			<div class="row">
				<div class="col-xs-12">

					<table id="productListTable"
						class="table table-striped table-borderd">

						<thead>
							<tr>
								<th></th>
								<th>Name</th>
								<th>brand</th>
								<th>unitPrice</th>
								<th>quantity</th>
								<th></th>
							</tr>


						</thead>

						<tfoot>

							<tr>
								<th></th>
								<th>Name</th>
								<th>brand</th>
								<th>unitPrice</th>
								<th>quantity</th>
								<th></th>
							</tr>

						</tfoot>


					</table>

				</div>



			</div>
		</div>


	</div>


</div>