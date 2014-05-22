<?php

function validEmail($email)
{
    if(!preg_match("/^([a-zA-Z0-9])+([a-zA-Z0-9\._-])*@([a-zA-Z0-9_-])+([a-zA-Z0-9\._-]+)+$/", $email))
    {
        return false;
    }	
    return true;
}

/*
Our "config.inc.php" file connects to database every time we include or require
it within a php script.  Since we want this script to add a new user to our db,
we will be talking with our database, and therefore,
let's require the connection to happen:
*/
require("config.inc.php");

//if posted data is not empty
if (!empty($_POST)) {
    //If the email or password is empty when the user submits
    //the form, the page will die.
    //Using die isn't a very good practice, you may want to look into
    //displaying an error message within the form instead.  
    //We could also do front-end form validation from within our Android App,
    //but it is good to have a have the back-end code do a double check.
    if (empty($_POST['ramid']) || empty($_POST['email']) || empty($_POST['password']) || empty($_POST['pin'])) {
        
        // Create some data that will be the JSON response 
        $response["success"] = 0;
        $response["message"] = "Please fill out the required information.";
        
        //die will kill the page and not execute any code below, it will also
        //display the parameter... in this case the JSON data our Android
        //app will parse
        die(json_encode($response));
    }
	
	$check_email = $_POST['email'];
	if(filter_var($check_email, FILTER_VALIDATE_EMAIL)) {
     	//if the page hasn't died, we will check with our database to see if there is
		//already a user with the email specificed in the form.  ":email" is just
		//a blank variable that we will change before we execute the query.  We
		//do it this way to increase security, and defend against sql injections
		$query = " SELECT 1 FROM android_users WHERE email = :email";
		//now lets update what :email should be
		$query_params = array(
			':email' => $_POST['email']
		);
	}
	else
	{
		$response["success"] = 0;
        $response["message"] = "Invalid email format";
        die(json_encode($response));
	}
    


	
	
    
    //Now let's make run the query:
    try {
        // These two statements run the query against your database table. 
        $stmt   = $db->prepare($query);
        $result = $stmt->execute($query_params);
    }
    catch (PDOException $ex) {
        // For testing, you could use a die and message. 
        //die("Failed to run query: " . $ex->getMessage());
        
        //or just use this use this one to product JSON data:
        $response["success"] = 0;
        $response["message"] = "Database Error1. Please Try Again!";
        die(json_encode($response));
    }
    
	
	
	
	
    //fetch is an array of returned data.  If any data is returned,
    //we know that the email is already in use, so we murder our
    //page
    $row = $stmt->fetch();
    if ($row) {
        // For testing, you could use a die and message. 
        //die("This email is already in use");
        
        //You could comment out the above die and use this one:
        $response["success"] = 0;
        $response["message"] = "I'm sorry, this email is already in use";
        die(json_encode($response));
    }
	
	

	
	
	
	$unsafe_password = $_POST['password'];
	$password = crypt($unsafe_password);
	
    //If we have made it here without dying, then we are in the clear to 
    //create a new user.  Let's setup our new query to create a user.  
    //Again, to protect against sql injects, user tokens such as :email and :pass
    $query = "INSERT INTO android_users ( ramid, email, password, pin ) VALUES ( :ramid, :email, :password, :pin ) ";
    
    //Again, we need to update our tokens with the actual data:
    $query_params = array(
		':ramid' => $_POST['ramid'],
        ':email' => $_POST['email'],
        ':password' => $password,
		':pin' => $_POST['pin']
    );
	
	

	
	
    
    //time to run our query, and create the user
    try {
        $stmt   = $db->prepare($query);
        $result = $stmt->execute($query_params);
    }
    catch (PDOException $ex) {
        // For testing, you could use a die and message. 
        //die("Failed to run query: " . $ex->getMessage());
        
        //or just use this use this one:
        $response["success"] = 0;
        $response["message"] = "Database Error2. Please Try Again!";
        die(json_encode($response));
    }
    
    //If we have made it this far without dying, we have successfully added
    //a new user to our database.  We could do a few things here, such as 
    //redirect to the login page.  Instead we are going to echo out some
    //json data that will be read by the Android application, which will login
    //the user (or redirect to a different activity, I'm not sure yet..)
    $response["success"] = 1;
    $response["message"] = "email Successfully Added!";
    echo json_encode($response);
    
    //for a php webservice you could do a simple redirect and die.
    header( "refresh:3; url=login.php" ); 
	echo '<br/><br/>';
    die("Redirecting to login.php...");
	
	
    
    
} else {
?>
<html>
	<body>
	<h1>Register</h1> 
	<form action="register.php" method="post"> 
	    Ram ID:<br /> 
	    <input type="text" name="ramid" placeholder="ram id (rXXXXXXXX)" value="" /> 
	    <br /><br /> 
		Email:<br /> 
	    <input type="email" name="email" placeholder="farmingdale email" value="" /> 
	    <br /><br /> 
	    Password:<br /> 
	    <input type="password" name="password" placeholder="password" value="" /> 
	    <br /><br /> 
		4 Digit Pin:<br /> 
	    <input type="text" name="pin" placeholder="4 digit pin number" value="" /> 
	    <br /><br />
		
	    <input type="submit" value="Register" /> 
		
	</form>
	</body>
</html>
	<?php
}

?>
