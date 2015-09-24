function validate(myform)
{
var x=document.myform.un.value;
if(x==null || x=="")
{
	 var n23=document.getElementById("erruserid");
		n23.innerHTML="<font color=red>Please fill userid</font>";
	return false;
}
var anum=/^[0-9]+$/;
if (!anum.test(x)){
	var n24=document.getElementById("erruserid");
	n24.innerHTML="<font color=red>Please enter six digit numbers only</font>";

return false;
}
else
{
var n21=document.getElementById("erruserid");
	n21.innerHTML=" ";
 return true;
}
var y=document.myform.pw.value;
if(y==null || y=="")
{
	var n25=document.getElementById("errpassword");
	n25.innerHTML="<font color=red>please enter password</font>";

	return false;
}
}
function capLock(e){
	 kc = e.keyCode?e.keyCode:e.which;
	 sk = e.shiftKey?e.shiftKey:((kc == 16)?true:false);
	 if(((kc >= 65 && kc <= 90) && !sk)||((kc >= 97 && kc <= 122) && sk)){
		 var n26=document.getElementById("errpassword");
		n26.innerHTML="<font color=red>caps lock is on</font>";
		return false;
	 }
	 else{
		 var n27=document.getElementById("errpassword");
		n27.innerHTML=" ";
		return true;
	  
	}
	}
	function validreset()
	{
	 
	    var x1 = document.getElementById("erruserid");
	    x1.innerHTML =" ";
	    var x2 = document.getElementById("errpassword");
	    x2.innerHTML = " ";
	    
	}
