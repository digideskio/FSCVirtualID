<?php

//load and connect to MySQL database stuff
require("config.inc.php");

if (!empty($_POST)) {
    //gets user's info based off of a email.
    $query = " 
            SELECT *
            FROM android_users
            WHERE 
                email = :email
        ";
    
    $query_params = array(
        ':email' => $_POST['email']
    );
    
    try {
        $stmt   = $db->prepare($query);
        $result = $stmt->execute($query_params);
		//$row = $stmt->fetch(PDO::FETCH_ASSOC);
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
	$login_ok = false;
    
    //fetching all the rows from the query
    //$row = $stmt->fetch();
	
    if ($row) {
        //if we encrypted the password, we would unencrypt it here, but in our case we just
        //compare the two passwords
		
		$user_password = $_POST['password'];
		if(crypt($user_password, $row['password']) == $row['password'])
		{
			$login_ok = true;
		}
		
        //if ($password === $row['password']) {
        //    $login_ok = true;
        //}
    }
	
    // If the user logged in successfully, then we send them to the private members-only page 
    // Otherwise, we display a login failed message and show the login form again 
    if ($login_ok) {
        $response["success"] = 1;
        $response["message"] = "Login successful!";
		$response["ramid"] = $row['ramid'];
		$response["pin"] = $row['pin'];
		$response["firstname"] = $row['firstname'];
		$response["lastname"] = $row['lastname'];
		die(json_encode($response));
		
		//for a php webservice you could do a simple redirect and die.
		//header( "refresh:3; url=http://farvlu.farmingdale.edu/~schwj13/" ); 
		//echo '<br/><br/>';
		//die("Redirecting to Jonathan Schwadron's website...");
		
		// email newsletter
		// $to      = $row['email'];
		// $subject = 'SCHWJ13 - Newsletter';
		// $message = "This is the best newsletter you have ever seen in your life.";
		// $headers = 'From: schwj13@farmingdale.edu' . "\r\n" .
		// 'Reply-To: schwj13@farmingdale.edu' . "\r\n" .
		// 'X-Mailer: PHP/' . phpversion();
		// mail($to, $subject, $message, $headers);
		
		//echo 'Login success.';
		
    } else {
		
		$response["success"] = 0;
        $response["message"] = "Invalid Credentials!";
        die(json_encode($response));

		
		//echo 'Invalid credentials.';
		//echo '<br/><br/>';
		//echo 'First time? click below to register';
				
		?>
		<form action="register.php" method="post">
		<input type="submit" value="Register">
		</form>
		Forgot password? click below to reset password.
		<br/>
		<form action="forgotpassword.php" method="post">
		<input type="submit" value="Reset password">
		</form>
		<?php
		
		
    }
} else {
?>
		<h1>Login</h1> 
		<form action="login.php" method="post"> 
		    email:<br /> 
		    <input type="text" name="email" placeholder="email" /> 
		    <br /><br /> 
		    Password:<br /> 
		    <input type="password" name="password" placeholder="password" value="" /> 
		    <br /><br /> 
		    <input type="submit" value="Login" /> 
		</form> 
		<a href="register.php">Register</a>
	<?php
}

?> 

