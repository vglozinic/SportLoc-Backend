<html>
<body style="font-family:Arial, Helvetica, sans-serif;">
	<h1>SportLoc Backend</h1>
	<p>
		Welcome to sportloc backend. Here you will find basic commands and data you need to set for reponse.
	</p>
	<hr>
	<p>
		<h2>Registration</h2>
		Path: <i>sportloc-backend.herokuapp.com/register</i><br>
		Requires POST JSON Object which contains parameters:<br>
		<ul>
			<li>name : example "Mišo"</li>
			<li>surname : example "Kovač"</li>
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
		Path: <i>sportloc-backend.herokuapp.com/login</i><br>
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
		Path: <i>sportloc-backend.herokuapp.com/resetPassword</i><br>
		Requires GET parametar which is:<br>
		<ul>
			<li>email : example <i>sample.mail@host.com</i></li>
		</ul>
		Returns JSON Object with boolean success of operation:
		<ul>
			<li>success : with values <i>true</i> or <i>false</i></li>
		</ul>
	</p>
	<hr>
	<p>
		<h2>Check Username</h2>
		Path: <i>sportloc-backend.herokuapp.com/checkUser</i><br>
		Requires GET parametar which is:<br>
		<ul>
			<li>username : example <i>mkovac</i></li>
		</ul>
		Returns JSON Object with boolean on whether user exists:
		<ul>
			<li>success : with values <i>true</i> or <i>false</i></li>
		</ul>
	</p>
	<hr>
	<p>
		<h2>Get Cities</h2>
		Path: <i>sportloc-backend.herokuapp.com/getCities</i><br>
		Method GET that requires nothing and returns list of JSON objects:<br>
		<ul>
			<li>id : example <i>84</i></li>
			<li>name : string <i>Varaždin</i></li>
		</ul>
	</p>
	<hr>
	<p>
		<h2>Get Sports</h2>
		Path: <i>sportloc-backend.herokuapp.com/getSports</i><br>
		Method GET that requires nothing and returns list of JSON objects:<br>
		<ul>
			<li>id : example <i>42</i></li>
			<li>name : string <i>Nogomet</i></li>
		</ul>
	</p>
	<hr>
	<p>
		<h2>Create Event</h2>
		Path: <i>sportloc-backend.herokuapp.com/createEvent</i><br>
		Requires POST JSON Event Object which must contain parameters:<br>
		<ul>
			<li>name : example "Neki događaj"</li>
			<li>start : example "18:00"</li>
			<li>end : example "20:00" <b>optional</b></li>
			<li>address : example "Pavlinska 2, Varaždin"</li>
			<li>description : "Opis ovog događaja"</li>
			<li>capacity : example <i>10</i></li>
			<li>date : example "2019-04-01" (YYYY-MM-DD)</li>
			<li>open : <i>true</i> if open, <i>false</i> if closed</li>
			<li>id_city : example <i>84</i></li>
			<li>id_sport : example <i>42</i></li>
			<li>id_user : example <i>4</i></li>
		</ul>
		Returnes JSON Object with single boolean:
		<ul>
			<li>success : with values <i>true</i> or <i>false</i></li>
		</ul>
		Optional parameter <i>end</i> is only sent if event has end time
	</p>
	<hr>
	<p>
		<h2>Update Event</h2>
		Path: <i>sportloc-backend.herokuapp.com/updateEvent</i><br>
		Requires POST JSON Event Object which must contain parameters:<br>
		<ul>
			<li>name : example "Neki događaj"</li>
			<li>start : example "18:00"</li>
			<li>end : example "20:00" <b>optional</b></li>
			<li>address : example "Pavlinska 2, Varaždin"</li>
			<li>description : "Opis ovog događaja"</li>
			<li>capacity : example <i>10</i></li>
			<li>date : example "2019-04-01" (YYYY-MM-DD)</li>
			<li>open : <i>true</i> if open, <i>false</i> if closed</li>
			<li>id_city : example <i>84</i></li>
			<li>id_sport : example <i>42</i></li>
			<li>id_event : example <i>16</i></li>
		</ul>
		Returnes JSON Object with single boolean:
		<ul>
			<li>success : with values <i>true</i> or <i>false</i></li>
		</ul>
		Optional parameter <i>end</i> is sent only if event has end time
	</p>
	<hr>
	<p>
		<h2>Delete Event</h2>
		Path <i>sportloc-backend.heroku.com/deleteEvent</i><br>
		Requires GET parametar of event ID which is:<br>
		<ul>
			<li>id : example <i>16</i></li>
		</ul>
		Returns JSON Object with boolean success of operation:
		<ul>
			<li>success : with values <i>true</i> or <i>false</i></li>
		</ul>
	</p>
</body>
</html>

