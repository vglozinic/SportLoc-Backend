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
		Requires POST JSON EventBean Object which must contain parameters:<br>
		<ul>
			<li>name : "Neki događaj"</li>
			<li>start : "18:00 21.12.2018."</li>
			<li>end : "20:00 01.01.2020." <b>optional</b></li>
			<li>address : "Pavlinska 2, Varaždin"</li>
			<li>description : "Opis ovog događaja"</li>
			<li>capacity : <i>10</i></li>
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
		Requires POST JSON EventBean Object which must contain parameters:<br>
		<ul>
			<li>name : "Neki događaj"</li>
			<li>start : "18:00 21.12.2018."</li>
			<li>end : "20:00 01.01.2020." <b>optional</b></li>
			<li>address : "Pavlinska 2, Varaždin"</li>
			<li>description : "Opis ovog događaja"</li>
			<li>capacity : <i>10</i></li>
			<li>open : <i>true</i> if open, <i>false</i> if closed</li>
			<li>cityId : <i>84</i></li>
			<li>sportId : <i>42</i></li>
			<li>eventId : <i>16</i></li>
		</ul>
		Returns JSON Object with boolean success of operation:
		<ul>
			<li>success : with values <i>true</i> or <i>false</i></li>
		</ul>
		Optional parameter <i>end</i> is sent only if event has end time
	</p>
	<hr>
	<p>
		<h2>Delete Event</h2>
		Path: <i>sportloc-backend.herokuapp.com/deleteEvent</i><br>
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
		Path: <i>sportloc-backend.herokuapp.com/getEvents</i><br>
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
			<li>start : "18:00 21.12.2018."</li>
			<li>end : "20:00 01.01.2020."</li>
			<li>address : "Pavilnska 2, Varaždin"</li>
			<li>description : "Kratak opis ovog događaja"</li>
			<li>sport : "Nogomet"</li>
			<li>city : "Varaždin"</li>
			<li>username": "testuser"</li>
		</ul>
		Attribute <i>end</i> won't be returned if event doesn't have ending
	</p>
	<hr>
	<p>
		<h2>Participant List</h2>
		Path: <i>sportloc-backend.herokuapp.com/getParticipants</i><br>
		Requires GET parametar of event ID which is:<br>
		<ul>
			<li>id : <i>22</i></li>
		</ul>
		Returns list of ParticipantBean JSON objects in event:<br>
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
		Path: <i>sportloc-backend.herokuapp.com/getProfile</i><br>
		Requires GET parametar of user ID which is:<br>
		<ul>
			<li>id : <i>8</i></li>
		</ul>
		Returns UserBean JSON object with following values:<br>
		<ul>
			<li>userId : <i>9</i></li>
			<li>upvote : <i>6</i></li>
			<li>downvote : <i>4</i></li>
			<li>gender : <i>true</i> if male, <i>false</i> if female</li>
			<li>name : "Mišo"</li>
			<li>surname : "Kovač"</li>
			<li>username : "mkovac"</li>
			<li>email : "miso.kovac@gmail.com"</li>
			<li>description : "Kratak opis o meni"</li> 
			<li>dob : "2013-06-18"</li>
		</ul>
		Attribute <i>description</i> won't be returned if description is empty
	</p>
	<hr>
	<p>
		<h2>Update Profile</h2>
		Path: <i>sportloc-backend.herokuapp.com/updateProfile</i><br>
		Requires POST JSON UserBean Object which must contain parameters:<br>
		<ul>
			<li>userId : <i>8</i></li>
			<li>name : "Slavica"</li>
			<li>surname : "Kovač"</li>
			<li>email : "alternativni@gmail.com"</li>
			<li>password : "novalozinka12345"</li>
			<li>description : "Neki novi opis korisnika"</li>
		</ul>
		Returns JSON Object with boolean success of operation:
		<ul>
			<li>success : with values <i>true</i> or <i>false</i></li>
		</ul>
	</p>
	<hr>
	<p>
		<h2>Comment List</h2>
		Path: <i>sportloc-backend.herokuapp.com/getComments</i>
		Requires GET parametar of user ID which is:<br>
		<ul>
			<li>id : <i>10</i></li>
		</ul>
		Returns list of CommentBean JSON objects for user:<br>
		<ul>
			<li>userId : <i>12</i></li>
			<li>commentatorId : <i>8</i></li>
			<li>vote : <i>true</i></li>
			<li>comment : "Testni komentar na profil korisnika"</li>
			<li>commentator : "mkovac"</li>
		</ul>
	</p>
	<hr>
	<p>
		<h2>Write Comment</h2>
		Path: <i>sportloc-backend.herokuapp.com/writeComment</i><br>
		Requires POST JSON CommentBean object with following parameters:<br>
		<ul>
			<li>userId : <i>10</i></li>
			<li>commentatorId : <i>20</i></li>
			<li>vote : <i>true</i> if upvote, <i>false</i> if downvote</li>
			<li>comment : "Ovo je komentar na profil korisnika"</li>
		</ul>
		Returns JSON Object with boolean success of operation:
		<ul>
			<li>success : with values <i>true</i> or <i>false</i></li>
		</ul>
	</p>
	<hr>
	<p>
		<h2>Check / Delete Comment</h2>
		Path: <i>sportloc-backend.herokuapp.com/resolveComment</i><br>
		Checks if comment exists or deletes it with GET parameters which are:<br>
		<ul>
			<li>commentator : <i>10</i></li>
			<li>user : <i>20</i></li>
			<li>action: <i>true</i> if deleting, <i>false</i> if checking</li>
		</ul>
		Returns JSON Object with boolean success of operation:
		<ul>
			<li>success : with values <i>true</i> or <i>false</i></li>
		</ul>
	</p>
</body>
</html>

