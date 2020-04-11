<?php
    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        require("config.php");

        $vinno = $_POST["vinno"];

        if($vinno == ''){
            echo json_encode(array("status" => "false","message" => "Parameter missing!"));
        }else{
            $stmt = $DB_con->prepare("SELECT * FROM inventory.car AS t1 INNER JOIN inventory.car_stock_count AS t2 ON t1.car_id = t2.car_id WHERE t1.vin_no = '$vinno' ORDER BY t2.car_stock_count_id DESC LIMIT 1");
            $stmt->execute();
            $emparray = array();

            if($stmt->rowCount() > 0){
                while($row=$stmt->fetch(PDO::FETCH_ASSOC)){
                    $emparray[] = $row;
                }
                echo json_encode(array("status" => "true","message" => "Found Data!","data" => $emparray));
            }else{
                echo json_encode(array("status" => "false","message" => "Not Found Dataaaaaaa!"));
            }            
        }
    }else{
        echo json_encode(array("status" => "false","message" => "Error occured, please try again!"));
    }
?>