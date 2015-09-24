function cardOwnerName()
{
	var x=/^[A-Z a-z]{3,25}$/;
        var name = payBill.cardOwner.value;
        if(name=="")
	{
        document.getElementById("1").innerHTML="Please Enter Name";
        return false;
	}
        else if(x.test(name))
        {
        document.getElementById("1").innerHTML="";
	return true;
        }
	else
	{
       	document.getElementById("1").innerHTML="Please enter only alplabets in name field";
	return false;
	}
}
function cardNo()
{
	var cardNo=payBill.cardNo.value;
	if (cardNo=="")
		{
		 document.getElementById("2").innerHTML="Please enter 16 digits Card number";
	     return false;
	}
	else if((isNaN(cardNo)) || (cardNo.length !=16))
	{
		document.getElementById("2").innerHTML="Please enter 16 digits Card number";
	    return false;
	}
	else
     {
     document.getElementById("2").innerHTML="";
	 return true;
     }
}
function dateExpiry()
{
	var month=payBill.month.value;
	var year=payBill.year.value;
	if (month=="no" && year=="no")
	{
		document.getElementById("3").innerHTML="Please select Date";
	    return false;
	}
	else if (month=="no" && year!="no")
	{
		document.getElementById("3").innerHTML="Please select Month";
	    return false;
	}
	else if (month!="no" && year=="no")
	{
		document.getElementById("3").innerHTML="Please select Year";
	    return false;
	}
	else
    {
    document.getElementById("3").innerHTML="";
	 return true;
    }
}
function cvvNo()
{
	var cvvNo=payBill.cvvNo.value;
	if (cvvNo=="")
	{
		document.getElementById("4").innerHTML="Please enter 3 digits CVV number";
	    return false;
	}
	else if( (isNaN(cvvNo)) || (cvvNo.length !=3))
	{	
		document.getElementById("4").innerHTML="Please enter 3 digits numeric values only in CVV field";
	    return false;
	}
	else
    {
    document.getElementById("4").innerHTML="";
	 return true;
    }
}
function Valid()
{
	var name = payBill.cardOwner.value;  
	var cardNo=payBill.cardNo.value;
	var month=payBill.month.value;
	var year=payBill.year.value;
	var cvvNo=payBill.cvvNo.value;
	var check = true;       
	var x=/^[A-Z a-z]{3,25}$/;
    if(name=="")
{
    document.getElementById("1").innerHTML="Please Enter Name";
    check=false;
}
    else if(x.test(name))
    {
    document.getElementById("1").innerHTML="";
check=true;
    }
else
{
   	document.getElementById("1").innerHTML="Please enter only alplabets in name field";
check= false;
}
    if (cardNo=="")
	{
	 document.getElementById("2").innerHTML="Please enter 16 digits Card number";
     check= false;
}
else if((isNaN(cardNo)) || (cardNo.length !=16))
{
	document.getElementById("2").innerHTML="Please enter 16 digits Card number";
    check= false;
}
else
 {
 document.getElementById("2").innerHTML="";
 check=true;
 }
    if (month=="no" && year=="no")
	{
		document.getElementById("3").innerHTML="Please select Date";
	    check=false;
	}
	else if (month=="no" && year!="no")
	{
		document.getElementById("3").innerHTML="Please select Month";
	    check= false;
	}
	else if (month!="no" && year=="no")
	{
		document.getElementById("3").innerHTML="Please select Year";
	    check=false;
	}
	else
    {
    document.getElementById("3").innerHTML="";
	 check= true;
    }
    
    if (cvvNo=="")
	{
		document.getElementById("4").innerHTML="Please enter 3 digits CVV number";
	    check=false;
	}
	else if( (isNaN(cvvNo)) || (cvvNo.length !=3))
	{	
		document.getElementById("4").innerHTML="Please enter 3 digits numeric values only in CVV field";
	    check=false;
	}
	else
    {
    document.getElementById("4").innerHTML="";
	 check=true;
    }
    
     return check;
}	
