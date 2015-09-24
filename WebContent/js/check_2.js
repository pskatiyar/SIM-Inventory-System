
function CustName()
{
	var x=/^[A-Z a-z]{3,25}$/;
        var name = Customer.customerName.value;
        if(name=="")
	{
        document.getElementById("1").innerHTML="Please enter Name";
        return false;
	}
        else if(x.test(name))
        {
        document.getElementById("1").innerHTML="";
	return true;
        }
	else
	{
       	document.getElementById("1").innerHTML="Please enter only alphabets and atleast 3 characters in Name field";
	return false;
	}
}
function CustAddress()
{
	var addr = Customer.customerAddress.value;
        if(addr=="")
	{
        document.getElementById("2").innerHTML="Please enter Address";
        return false;
	}
        else if(addr.length <15){
        	 document.getElementById("2").innerHTML="Please enter atleast 15 characters in Address field";
             return false;
        }
        else
        {
        document.getElementById("2").innerHTML="";
	return true;
        }
}
function Valid()
{
	    var x1=/^[A-Z a-z]{3,25}$/;
        var name = Customer.customerName.value;
        var addr = Customer.customerAddress.value;
        var check=false;
        var check1  = true;
        var check2  = true;
        if(name=="")
		{
	        document.getElementById("1").innerHTML="Please enter Name";
	        check1=false;
		}
	    else if(x1.test(name))
	    {
	        document.getElementById("1").innerHTML="";
	        check1=true;
	    }
	    else
	    {
	       	document.getElementById("1").innerHTML="Please enter only alphabets and atleast 3 characters in Name field";
	       	check1= false;
		}
	    if(addr=="")
		{
	        document.getElementById("2").innerHTML="Please enter Address";
	        check2= false;
		}
	    else if(addr.length <15){
       	 document.getElementById("2").innerHTML="Please enter atleast 15 characters in Address field";
            check2= false;
       }
	        else
	        {
	        document.getElementById("2").innerHTML="";
		check2=true;
	        }
        if(check1==true && check2==true)
        {
        	check=true;
        var cnfrm = confirm("Do you want to continue ?");
        return cnfrm;
        }
	return check;
}	
function customerDelete()
{
	var cnfrm = confirm("Do you want to continue ?");
	if(cnfrm==true)
		alert("Customer deleted successfully");
    return cnfrm;
}



