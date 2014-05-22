<?php

//load and connect to MySQL database stuff
require("config.inc.php");

if (!empty($_POST)) {
    //gets user's info based off of a email.
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
    $password_match = false;
	
    //fetching all the rows from the query
    //$row = $stmt->fetch();
	if ($row) {
	
		$user_newpassword 		= $_POST['newpassword'];
		$user_confirmpassword 	= $_POST['confirmpassword'];

		if($user_newpassword == $user_confirmpassword)
		{
			$password_match = true;
		}
		else
		{
			$response["success"] = 0;
			$response["message"] = "Password mismatch.";
			die(json_encode($response));
		}
	}
	
    // If the user logged in successfully, then we send them to the private members-only page 
    // Otherwise, we display a login failed message and show the login form again 
    if ($password_match) {

		//encrypt new password
		$unsafe_password = $_POST['newpassword'];
		$newpassword = crypt($unsafe_password);
		
		//If we have made it here without dying, then we are in the clear to 
		//create a new user.  Let's setup our new query to create a user.  
		//Again, to protect against sql injects, user tokens such as :email and :pass
		$query = "UPDATE android_users
					SET password = :password
					WHERE ramid = :ramid ";
    
		//Again, we need to update our tokens with the actual data:
		$query_params = array(
			':password' => $newpassword,
			':ramid' 	=> $_POST['ramid']
		);
		
		//time to run our query, and update the password
		try {
			$stmt   = $db->prepare($query);
			$result = $stmt->execute($query_params);
		}
		catch (PDOException $ex) {
			// For testing, you could use a die and message. 
			//die("Failed to run query: " . $ex->getMessage());
			
			//or just use this use this one:
			$response["success"] = 0;
			$response["message"] = "Database Error. Please Try Again!";
			die(json_encode($response));
			//die("Database Error2. Please Try Again!");
		}

		//header( "refresh:2; url=login.php" ); 	
		$response["success"] = 1;
        $response["message"] = "Password updated.";
		die(json_encode($response));
		//die("Password changed. Redirecting you to login.");		
    } 
	
	else {
        $response["success"] = 0;
        $response["message"] = "Invalid Ram ID.";
		die(json_encode($response));
		//echo 'I\'m sorry, the passwords does not match.';
    }
} 
else {
?>
		<h1>Change Password</h1> 
		<form action="changepassword.php" method="post"> 
		    Ram ID:<br /> 
		    <input type="text" name="ramid" placeholder="ram id" /> 
		    <br /><br />  
			New Password:<br /> 
		    <input type="password" name="newpassword" placeholder="new password" /> 
		    <br /><br />  
			Confirm Password:<br /> 
		    <input type="password" name="confirmpassword" placeholder="confirm password" /> 
		    <br /><br />
			<input type="submit" value="Submit" /> 
		</form> 
	<?php
}

?> 

