$(function() {

	
	// solving the active menu problem
	switch (menu) {

	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
    
    case 'Manage Products':
		$('#manageProducts').addClass('active');
		break;
	case 'Shopping Cart':
		$('#userCart').addClass('active');
		break;		
			
	default:
	     if(menu == "Home") break;
		$('#listProducts').addClass('active');
		$('#a_'+menu).addClass('active');
		break;
	}
	
	
	
	
	
	//code for jquary datatable
	

      var $table = $('#productListTable');

	  //excuted the below  code  only where we have this table

     if ($table.length) {
	
	 var jsonUrl = '';
		if (window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products';
		} else {
			jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryId + '/products';
		}
		
	
	 $table.DataTable({
		lengthMenu : [ [ 3, 5, 10, -1 ],[ '3 Records', '5 Records', '10 Records', 'ALL' ] ],
		
		ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					
		columns : [    
			       
                            {
								data : 'code',
								bSortable : false,
								mRender : function(data, type, row) {

									return '<img src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class="datatableImg"/>';

								}
							},
							
							{
								data : 'name'
							},
							
							{
								data : 'brand'
							},
							
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '&#8377; ' + data
								}
							},
							
							 {
								data : 'quantity',
								mRender : function(data, type, row) {

									if (data < 1) {
										return '<span style="color:red">Out of Stock!</span>';
									}

									return data;

								}
								
							},
							
							{
								 data : 'id',
							     bSortable : false,
								 mRender : function(data, type, row) {
                                  
						        var str = '';

										str += '<a href="'+ window.contextRoot +'/show/'+ data +'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';
										
										if(userRole == 'ADMIN')	{
										str += '<a href="'+ window.contextRoot +'/manage/'+ data +'/product" class="btn btn-success"><span class="glyphicon glyphicon-pencil"></span></a>';	
										}
										else{
										
										
										if(row.quantity < 1){
											str += '<a href="javascript:void(0)" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
										}
										
										else{
											str += '<a href="'+ window.contextRoot +'/cart/add/'+ data +'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
																	
										  }
										}
											 
                                       return str;
							   }
						   }
							
							


                   ]		
	    });
	
        }
               /*------*/
	/* for fading out the alert message after 3 seconds */
	$alert = $('.alert');
	if($alert.length) {
		setTimeout(function() {
	    	$alert.fadeOut('slow');
		   }, 3000
		);		
	   }


//======================= start  admin jquary datatable ===========================

/*
code for jquary datatable for admin
*/
      var $adminProductTable = $('#adminProductsTable');

	  //excuted the below  code  only where we have this table

     if ($adminProductTable.length) {
	
	 var jsonUrl = window.contextRoot + '/json/data/admin/all/products';

	 $adminProductTable.DataTable({
		lengthMenu : [ [ 3, 5, 10, -1 ],[ '3 Records', '5 Records', '10 Records', 'ALL' ] ],
		
		ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					
		columns : [    
			           {
				         data:'id'
			           },
			       
                       {
					    data : 'code',
						bSortable : false,
						mRender : function(data, type, row) {

						return '<img src="' + window.contextRoot
						+ '/resources/images/' + data
						+ '.jpg" class="admintableImg"/>';

								}
						},
							
							{
								data : 'name'
							},
							
							{
								data : 'brand'
							},
							
							
							
							 {
								data : 'quantity',
								mRender : function(data, type, row) {

									if (data < 1) {
										return '<span style="color:red">Out of Stock!</span>';
									}

									return data;

								}
								
							},
							
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '&#8377; ' + data
								}
							},
							
							
							{
								 data : 'active',
							     bSortable : false,
								 mRender : function(data, type, row) {                                 
						         var str = '';
							     str += '<label class="switch">';
				                if(data){
					            str += '<input type="checkbox" value="'+row.id+'" checked="checked"/>';
				                  }
                                  else{
	                               str += '<input type="checkbox" value="'+row.id+'" />';
                                  }
		                       
							   str += '<div class="slider round"> </div></label>';		
											 
                                return str;
							   }
						   },
			                
                            {
	                         data: 'id',
                             bSortable : false,
								 mRender : function(data, type, row) {
                                  
						        var str = '';
                                str += '<a href="' + window.contextRoot+ '/manage/'+ data + '/product" class="btn btn-primary"><span class="glyphicon glyphicon-pencil"></span></a> &#160;';

                                
                                return str;
                            }
                             
                            }
							
							


                   ],

 // active and deactive product from database

               initComplete: function () {
						var api = this.api();
						api.$('.switch input[type="checkbox"]').on('change' , function() {	
							var checkbox = $(this);	
							var checked =checkbox.prop('checked');					
							var dText = (this.checked)? 'You want to activate the Product?': 'You want to de-activate the Product?';
							//var checked = this.checked;	
							var value=checkbox.prop('value');
							
						    bootbox.confirm({
						    	size: 'medium',
						    	title: 'Product Activation/Deactivation',
						    	message: dText,
						    	callback: function (confirmed) {
							        if (confirmed) {
								     console.log(value);
			                          var activationUrl= window.contextRoot + '/manage/product/' + value+ '/activation';
                                      $.post(activationUrl, function(data){
	                                  bootbox.alert({
								      size: 'medium',
						    	      title: 'information',
						    	      message: data
							            
                                       });
                                        
                                       });
							         

							        }
							        else {							        	
							        	checkbox.prop('checked', !checked);
							        }
						    	}
						    });																											
						});
							
					}	
					
					
					
						
	    });
	
        }

//======================= end  admin jquary datatable ===========================



// validating the product form element	
	// fetch the form element
	var $categoryForm = $('#categoryForm');
	
	if($categoryForm.length) {
		
		$categoryForm.validate({			
				rules: {
					name: {
						required: true,
						minlength: 3
					},
					description: {
						required: true,
						minlength: 5					
					}				
				},
				messages: {					
					name: {
						required: 'Please enter product name!',
						minlength: 'Please enter atleast five characters'
					},
					description: {
						required: 'Please enter Decription !',
						minlength: 'Please enter atleast five characters'
					}					
				},
				errorElement : "em",
				errorPlacement : function(error, element) {
					error.addClass('help-block');
					error.insertAfter(element);
				}				
			}
		
		);
		
	}





// validating the product form element	
	// fetch the form element
	var $loginForm = $('#loginForm');
	
	if($loginForm.length) {
		
		$loginForm.validate({			
				rules: {
					username: {
						required: true,
						email: true
					},
					password: {
						required: true
											
					}				
				},
				messages: {					
					username: {
						required: 'Please enter your email!',
						
					},
					password: {
						required: 'Please enter password !',
						
					}					
				},
				errorElement : "em",
				errorPlacement : function(error, element) {
					error.addClass('help-block');
					error.insertAfter(element);
				}				
			}
		
		);
		
	}
	
	
	
				
		
	
	
	
	// to tackel the csrf token
	
	// for handling CSRF token
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');
	
	if((token!=undefined && header !=undefined) && (token.length > 0 && header.length > 0)) {		
		// set the token header for the ajax request
		$(document).ajaxSend(function(e, xhr, options) {			
			xhr.setRequestHeader(header,token);			
		});				
	}
	
	
	
	
	
	/*------*/
	/* handle refresh cart*/	
	$('button[name="refreshCart"]').click(function(){
		var cartLineId = $(this).attr('value');
		var countElement = $('#count_' + cartLineId);
		var originalCount = countElement.attr('value');
		var currectCount = countElement.val();
		
		// do the checking only the count has changed
		if(currectCount !== originalCount) {	
		
		
		if(currectCount < 1 || currectCount > 10) {
		      
		      countElement.val(originalCount);
		      
		     bootbox.alert({
					size: 'medium',
			    	title: 'Error',
			    	message: 'Product Count should be minimum 1 and maximum 10!'
				});
		      }
		      
		      else{
		      	var updateUrl = window.contextRoot + '/cart/' + cartLineId + '/update?count=' + currectCount;
				window.location.href = updateUrl;
		      }
		      
		      }
			
			});
	
	
	
	
	
		
});


