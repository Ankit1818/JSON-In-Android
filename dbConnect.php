<?php
 

define('HOST','localhost');
 

define('USER','id1694716_registration');
 

define('PASS','registration');
 

define('DB','id1694716_registration');
 
 


$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

/*if($con)

{

echo 'succcess';

}


else
{

echo 'Fail';
}



?>