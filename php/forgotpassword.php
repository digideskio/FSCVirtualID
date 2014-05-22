<?php

//load and connect to MySQL database stuff
require("config.inc.php");

if (!empty($_POST)) {
    //gets user's info based off of ramid.
    $query = " 
            SELECT *
            FROM android_users
            WHERE 
                ramid = :ramid
        ";
    
    $query_params = array(
        ':ramid' => $_POST['ramid']
	);
    
    try {
        $stmt   = $db->prepare($query);
        $result = $stmt->execute($query_params);
		$row = $stmt->fetch();
    }
    catch (PDOException $ex) {
        // For testing, you could use a die and message. 
        //die("Failed to run query: " . $ex->getMessage());
        
        //or just use this use this one to product JSON data:
        $response["success"] = 0;
        $response["message"] = "Database Error1. Please Try Again!";
        die(json_encode($response));   
    }
    
    //This will be the variable to determine whether or not the user's information is correct.
    //we initialize it as false.
    $validated_info = false;
    $user_found = false;
	
    //fetching all the rows from the query
    //$row = $stmt->fetch();
	
	$user_email = $_POST['email'];
	$user_ramid = $_POST['ramid'];
	

	if ($row) {
		if(($user_email == $row['email']) && ($user_ramid == $row['ramid']))
		{
			$user_found = true;
		}
	}
	
	
    // If the user logged in successfully, then we send them to the private members-only page 
    // Otherwise, we display a login failed message and show the login form again 
    if ($user_found) {

		
		//for a php webservice you could do a simple redirect and die.
		header( "refresh:2; url=changepassword.php" ); 
		//echo '<br/><br/>';
		
		$response["success"] = 1;
        $response["message"] = "User found.";
		die(json_encode($response));
		
		//die("User match found. You may now change your password...");
		
		//email newsletter
		// $to      = $row['email'];
		// $subject = 'SCHWJ13 - Password Reset';
		// $message = "Password is a precious thing. Try to remember it after you change it. Click the link below to reset your password.\n\m
		// http://farvlu.farmingdale.edu/~schwj13/project2/resetpassword.php";
		// $headers = 'From: schwj13@farmingdale.edu' . "\r\n" .
		// 'Reply-To: schwj13@farmingdale.edu' . "\r\n" .
		// 'X-Mailer: PHP/' . phpversion();
		// mail($to, $subject, $message, $headers);
		
		//echo 'A password reset email has been sent to you.';
		
    } else {
        $response["success"] = 0;
        $response["message"] = "User not found.";
		die(json_encode($response));
		//echo 'I\'m sorry, could not find that email/ramid in our database.';
		

    }
} else {
?>
		<h1>Forgot Password</h1> 
		<form action="forgotpassword.php" method="post"> 
		    Email:<br /> 
		    <input type="email" name="email" placeholder="email" /> 
		    <br /><br />  
			Ram ID:<br /> 
		    <input type="text" name="ramid" placeholder="ramid" /> 
		    <br /><br />  
			<input type="submit" value="Submit" /> 
		</form> 
	<?php
}

?> 

