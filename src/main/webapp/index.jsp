<html>
<body style="font-family:Arial, Helvetica, sans-serif;">
	<h1>SportLoc Backend</h1>
	<p>
		Welcome to sportloc backend. Here you will find basic commands and data you need to set for reponse.
	</p>
	<hr>
	<p>
		<h2>Registration</h2>
		Path: <i>sportloc-backend.heroku.com/register</i><br>
		Requires JSON Object which contains parameters:<br>
		<ul>
			<li>name : example "Miso"</li>
			<li>surname : example "Kovac"</li>
			<li>username : example "mkovac"</li>
			<li>email : example "miso.kovac@host.hr"</li>
			<li>password : example "rukeuzrak123"</li>
			<li>dob : example "2013-06-18" (YYYY-MM-DD)</li>
			<li>gender : <i>true</i> if male, <i>false</i> if female</li>
		</ul>
		Returnes JSON Object with single boolean:
		<ul>
			<li>success : with values <i>true</i> or <i>false</i></li>
		</ul>
	</p>
	<hr>
	<p>
		<h2>Login</h2>
		Path: <i>sportloc-backend.heroku.com/login</i><br>
		Requires GET parameters which are:<br>
		<ul>
			<li>username : example <i>mkovac</i></li>
			<li>password : example <i>rukeuzrak123</i></li>

		</ul>
		Returns user ID for login is succussful or 0 if failed
		<ul>
			<li>userId : 0 or user ID (numerical value)</li>
		</ul>
	</p>
	<hr>
	<p>
		<h2>Reset Password</h2>
		Path: <i>sportloc-backend.heroku.com/resetPassword</i><br>
		Requires GET parametar which is:<br>
		<ul>
			<li>email : example <i>sample.mail@host.com</i></li>
		</ul>
		Returns JSON Object with boolean success of operation:
		<ul>
			<li>success : with values <i>true</i> or <i>false</i>></li>
		</ul>
	</p>
	<hr>
	<p>
		<h2>Check Username</h2>
		Path: <i>sportloc-backend.heroku.com/checkUser</i><br>
		Requires GET parametar which is:<br>
		<ul>
			<li>username : example <i>mkovac</i></li>
		</ul>
		Returns JSON Object with boolean on whether user exists:
		<ul>
			<li>success : with values <i>true</i> or <i>false</i></li>
		</ul>
	</p>
</body>
</html>
