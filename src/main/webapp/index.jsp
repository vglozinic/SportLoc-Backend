<html>
<body style="font-family:Arial, Helvetica, sans-serif;">
	<h1>SportLoc Backend</h1>
	<p>
		Welcome to sportloc backend. Here you will find basic commands and data you need to set for reponse.<br>
		Values in attributes are only examples which show formatting and are not representative of real data.
	</p>
	<hr>
	<p>
		<h2>Registration</h2>
		Path: <i>sportloc-backend.herokuapp.com/register</i><br>
		Requires POST JSON Object which contains parameters:<br>
		<ul>
			<li>name : "Mišo"</li>
			<li>surname : "Kovač"</li>
			<li>username : "mkovac"</li>
			<li>email : "miso.kovac@host.hr"</li>
			<li>password : "rukeuzrak123"</li>
			<li>dob : "2013-06-18" (YYYY-MM-DD)</li>
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
			<li>username : <i>mkovac</i></li>
			<li>password : <i>rukeuzrak123</i></li>

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
			<li>email : <i>sample.mail@host.com</i></li>
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
			<li>username : <i>mkovac</i></li>
		</ul>
		Returns JSON Object with boolean on whether user exists:
		<ul>
			<li>success : with values <i>true</i> or <i>false</i></li>
		</ul>
	</p>
	<hr>
	<p>
		<h2>Check E-mail</h2>
		Path: <i>sportloc-backend.herokuapp.com/checkEmail</i><br>
		Requires GET parametar which is:<br>
		<ul>
			<li>email : <i>nekiemail@host.hr</i></li>
		</ul>
		Returns JSON Object with boolean on whether email exists:
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
			<li>id :  <i>84</i></li>
			<li>name : "Varaždin"</li>
		</ul>
	</p>
	<hr>
	<p>
		<h2>Get Sports</h2>
		Path: <i>sportloc-backend.herokuapp.com/getSports</i><br>
		Method GET that requires nothing and returns list of JSON objects:<br>
		<ul>
			<li>id : <i>42</i></li>
			<li>name : "Nogomet"</li>
		</ul>
	</p>
	<hr>
	<p>
		<h2>Create Event</h2>
		Path: <i>sportloc-backend.herokuapp.com/createEvent</i><br>
		Requires POST JSON Event Object which must contain parameters:<br>
		<ul>
			<li>name : "Neki događaj"</li>
			<li>start : "18:00"</li>
			<li>end : "20:00" <b>optional</b></li>
			<li>address : "Pavlinska 2, Varaždin"</li>
			<li>description : "Opis ovog događaja"</li>
			<li>capacity : <i>10</i></li>
			<li>date : "2019-04-01" (YYYY-MM-DD)</li>
			<li>open : <i>true</i> if open, <i>false</i> if closed</li>
			<li>cityId : <i>84</i></li>
			<li>sportId : <i>42</i></li>
			<li>userId : <i>4</i></li>
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
			<li>name : "Neki događaj"</li>
			<li>start : "18:00"</li>
			<li>end : "20:00" <b>optional</b></li>
			<li>address : "Pavlinska 2, Varaždin"</li>
			<li>description : "Opis ovog događaja"</li>
			<li>capacity : <i>10</i></li>
			<li>date : "2019-04-01" (YYYY-MM-DD)</li>
			<li>open : <i>true</i> if open, <i>false</i> if closed</li>
			<li>cityId : <i>84</i></li>
			<li>sportId : <i>42</i></li>
			<li>eventId : <i>16</i></li>
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
		Path <i>sportloc-backend.herokuapp.com/deleteEvent</i><br>
		Requires GET parametar of event ID which is:<br>
		<ul>
			<li>id : <i>16</i></li>
		</ul>
		Returns JSON Object with boolean success of operation:
		<ul>
			<li>success : with values <i>true</i> or <i>false</i></li>
		</ul>
	</p>
	<hr>
	<p>
		<h2>Event List</h2>
		Path <i>sportloc-backend.herokuapp.com/getEvents</i><br>
		Method GET that requires nothing and returns list of EventBean JSON objects:<br>
		<ul>
			<li>eventId : <i>22</i></li>
			<li>sportId : <i>95</i></li>
			<li>cityId : <i>235</i></li>
			<li>userId : <i>10</i></li>
			<li>capacity : <i>4</i></li>
			<li>current : <i>2</i></li>
			<li>open : <i>true</i> if open, <i>false</i> if closed</li>
			<li>name : "Naslov događaja"</li>
			<li>start : "18:00:00"</li>
			<li>end : "20:00:00"</li>
			<li>address : "Pavilnska 2, Varaždin"</li>
			<li>description : "Kratak opis ovog događaja"</li>
			<li>date : "2019-04-01"</li>
			<li>sport : "Nogomet"</li>
			<li>city : "Varaždin"</li>
			<li>username": "testuser"</li>
		</ul>
		Attribute <i>end</i> won't be returned if event doesn't have ending
	</p>
	<hr>
	<p>
		<h2>Participant List</h2>
		Path <i>sportloc-backend.herokuapp.com/getParticipants</i><br>
		Requires GET parametar of event ID which is:<br>
		<ul>
			<li>id : <i>22</i></li>
		</ul>
		Returns list of ParticipantBean JSON objects:<br>
		<ul>
			<li>eventId : <i>22</i></li>
			<li>userId : <i>10</i></li>
			<li>statusId : <i>1</i></li>
			<li>username : "testuser"</li>
			<li>status : "Approved"</li>
		</ul>
	</p>
	<hr>
	<p>
		<h2>Get Profile</h2>
		Path <i>sportloc-backend.herokuapp.com/getProfile</i><br>
		Requires GET parametar of user ID which is:<br>
		<ul>
			<li>id : <i>8</i></li>
		</ul>
	</p>
</body>
</html>

