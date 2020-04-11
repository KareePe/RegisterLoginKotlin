<?php
   	if($_SERVER['REQUEST_METHOD']=='POST'){
  	// echo $_SERVER["DOCUMENT_ROOT"];  // /home1/demonuts/public_html
	//including the database connection file
       	require("config.php");
       
        $username = $_POST['username'];
 		$password = md5($_POST['password']);
 	
	 	if( $username == '' || $password == '' ){
	        echo json_encode(array( "status" => "false","message" => "Parameter missing!") );
	 	}else{
			$stmt = $DB_con->prepare("SELECT * FROM public.users WHERE username = '$username' AND password = '$password'");
			$stmt->execute();	
		 
	        if($stmt->rowCount() > 0){  
				$stmt = $DB_con->prepare("SELECT * FROM public.users WHERE username = '$username' AND password = '$password'");
				$stmt->execute();
		        $emparray = array();
	                if($stmt->rowCount() > 0){  
	                    while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
                            $emparray[] = $row;
                        }
	                }
	           	echo json_encode(array( "status" => "true","message" => "Login successfully!", "data" => $emparray) );
	        }else{ 
	        	echo json_encode(array( "status" => "false","message" => "Invalid username or password!") );
	        }
	 	}
	} else{
			echo json_encode(array( "status" => "false","message" => "Error occured, please try again!") );
	}
?>