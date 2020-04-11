<?php
   	if($_SERVER['REQUEST_METHOD']=='POST'){
  	// echo $_SERVER["DOCUMENT_ROOT"];  // /home1/demonuts/public_html
	//including the database connection file
    	require("config.php");       
        
		$username = $_POST['username'];
		$password = md5($_POST['password']);
		$fname = $_POST['fname'];
		$lname= $_POST['lname'];
  
		if($fname == '' || $username == '' || $password == '' || $lname == ''){
				echo json_encode(array( "status" => "false","message" => "Parameter missing!") );
		}else{		
			$stmt = $DB_con->prepare("SELECT * FROM public.users WHERE username = '$username'");
            $stmt->execute();	 
		 
	        if($stmt->rowCount() > 0){  
	           echo json_encode(array( "status" => "false","message" => "Username already exist!") );
	        }else{ 
				$stmt = $DB_con->prepare("INSERT INTO public.users(username,password,first_name,last_name) 
                                VALUES(:uname,:upass,:ufname,:ulname)");
                $stmt->bindparam(":uname", $username);
                $stmt->bindparam(":upass", $password);
				$stmt->bindparam(":ufname", $fname); 
				$stmt->bindparam(":ulname", $lname);   

			 	if($stmt->execute()){
			    
					$stmt = $DB_con->prepare("SELECT * FROM public.users WHERE username = '$username'");
                    $stmt->execute();
                    $emparray = array(); 
                    if($stmt->rowCount() > 0){
                        $i = 0;
                        while($row=$stmt->fetch(PDO::FETCH_ASSOC)){
                            $emparray[] = $row;
                        }
                    }
			    echo json_encode(array( "status" => "true","message" => "Successfully registered!" , "data" => $emparray) );
		 	 }else{
		 		 echo json_encode(array( "status" => "false","message" => "Error occured, please try again!") );
		 	}
	    }
	 }
     } else{
			echo json_encode(array( "status" => "false","message" => "Error occured, please try again!") );
	}
 
 ?>