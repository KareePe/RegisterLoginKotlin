<?php
    // $host="localhost";
    // $user="root";
    // $password="";
    // $db = "tkncoth_loginapp";
    // //$port = "3306";
    
    // $con = mysqli_connect($host,$user,$password,$db);
    
    // // Check connection
    // if (mysqli_connect_errno())
    // {
    //     echo "Failed to connect to MySQL: " . mysqli_connect_error();
    //     // echo json_encode(array( "status" => "false","message" => "Connect Fail!") );
    // }else{  
    //     // echo json_encode(array( "status" => "true","message" => "Connect Success!") );
    // }
    $DB_host = "10.4.22.72";
    $DB_user = "admin";
    $DB_pass = "admin@tkn14225";
    $DB_name = "tkn";

    try{
        $DB_con = new PDO("pgsql:host={$DB_host};dbname={$DB_name}",$DB_user,$DB_pass);
        $DB_con->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);
    }catch(PDOException $e){
        echo $e->getMessage();
    }

    if($DB_con){
        // echo "Connection Success";
    }else{
        echo "Connection Failed";
    }       

    date_default_timezone_set("Asia/Bangkok");
    $timestamp = date("d-m-Y H:i:s");
?>