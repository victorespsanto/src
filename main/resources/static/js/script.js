/**
 * 
 */
 
 function isNumberKey(evt){
	
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	
	if (charCode >= 46 && charCode <= 57 || charCode <= 31) {
		return true;
	}
	
	return false;
}