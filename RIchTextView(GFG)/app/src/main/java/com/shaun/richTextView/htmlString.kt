package com.shaun.richTextView

class htmlString{

    val html =
        "<p>Today we are going to implement Firebase Authentication (Sign in with Google) using Firebase UI.&nbsp;</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<h2><em><span style=\"text-decoration: underline;\"><strong>Prerequisites</strong></span></em></h2>\n" +
                "<p>Basic knowledge of node, Javascript and firebase.</p>\n" +
                "<h2><em><span style=\"text-decoration: underline;\"><strong>Setup</strong></span></em></h2>\n" +
                "<p>First we need to create a Firebase Project,head over to <a href=\"https://console.firebase.google.com/\">firebase Console</a> and create a new project.&nbsp;</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<blockquote>\n" +
                "<p>I am gonna name it SignInWithGoogle</p>\n" +
                "<p><img src=\"https://storage.googleapis.com/my-blogger-1b264.appspot.com/1597661523610?GoogleAccessId=firebase-adminsdk-lb5mr%40my-blogger-1b264.iam.gserviceaccount.com&amp;Expires=1742169600&amp;Signature=hF2mo72PPYQcUkPByoPluNDHKicL2DDmdaIQhpfdAVGE36EJAHyZ%2FxFlZ3vI%2FRhObFw6md2uoyU6Pvd481Pi2ITpxq1nZpUBfs5DfMI1sGMjjl1y%2FSyKWmn2qEqxSFp%2FTGUeweqmwLCWpC6F0yhMCjfxqoabGAlPvV36fdc60zwppb23Hb%2FvI4SE1XcJF9yCN3XEq98PEOkLukXVFJ%2BlHW%2BS6TLNhGJueyt5fIY%2FNJjZ8GDIQrwxD2XkEDDdbctvsGitSkLmp9IaA9GO1dt72iL%2FgQgF2witlWWAgDZV%2F8Dn%2BSB8GHh7gh13Fx%2FgXd2jqwy1WwVBCJgTTDAvqyWrRg%3D%3D\" alt=\"\" width=\"432\" height=\"277\" /></p>\n" +
                "<p>&nbsp;</p>\n" +
                "</blockquote>\n" +
                "<p>Click on the web to create a web app.</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<blockquote>\n" +
                "<p><img src=\"https://storage.googleapis.com/my-blogger-1b264.appspot.com/1597661571925?GoogleAccessId=firebase-adminsdk-lb5mr%40my-blogger-1b264.iam.gserviceaccount.com&amp;Expires=1742169600&amp;Signature=EvqX22RW9j6BlSeTipcxk2utr8f3f%2BDgakSR03KujuYp8PwFywJFxq31pj5LvrXQNB6Qnah%2FiJTYhaLAvhzYbYh3aLrKTquXH2vfnXFo9S%2F708ihPiIsBypGUj3gG0CCBDt5hMhdQSQkFfzyJL%2FJXIvkq56nildiz5FXnFumUTCRsx2bmH24SO5FAaBsdmIx6FVC1MQzTMfTvJKSNFTKTXxbsiVaoWkbkaoE3Sy1DAeGW8A4bts3La71QvrDhNCIxiiU1zsQ6%2BD35qNZ%2FFKZxdZGBFGL35zmqBtArSErR1pgNaFuzfWIv6qTSYrOheMrSJ3bCHZSEIgcGRIb%2Bpaw5w%3D%3D\" alt=\"\" width=\"428\" height=\"439\" /></p>\n" +
                "</blockquote>\n" +
                "<p>&nbsp;</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p>Go to Firebase Settings&gt;Service Account &gt;Generate a new Key. This Key should be remain private,It is advised to keep this in environment variables. However for the sake of simplicity I won't.</p>\n" +
                "<p>Now go to Authentication tab and turn on<em><strong> Sign in with Google.&nbsp;</strong></em></p>\n" +
                "<blockquote>\n" +
                "<p><img src=\"https://storage.googleapis.com/my-blogger-1b264.appspot.com/1597661618295?GoogleAccessId=firebase-adminsdk-lb5mr%40my-blogger-1b264.iam.gserviceaccount.com&amp;Expires=1742169600&amp;Signature=FSrKuVaOGsrShoMZHqsw3ol2DXM0xWMOvS67MNQMnMEEqz10n0Xc978t%2FpeMPBRUVX%2FbD3sCas9fi%2FF9J40snnM%2BdfWrGXDb60GBZMmfK8xOCB%2BaaNcKxAm3GRJUK7FpfOylhOCLdzefcs6%2BYhiscn1vXo5CvOSLqB9BeJXdRWq9TbRoJ%2BgLzuiDrfZjgSS4JznUVN%2FRGj9rMovzHVaj5T66gclv9JBWw%2FVKuS0p3qhStrn6CV6b9nZ3jF9lyAR6%2FIp%2FyUbWdegjVuE9v805GK0qz%2BTwN6hltVJuBDR69a81caR8MiWLbgubVMgk3Q0qaz0juyirMFsHzuF8QK2R0g%3D%3D\" alt=\"\" width=\"445\" height=\"296\" /></p>\n" +
                "<p>&nbsp;</p>\n" +
                "</blockquote>\n" +
                "<p>Now Create a new project having success.html (<em>with a simple anchor tag ,directing to \"/ \" root</em> ) and login.html <strong>[leave a blank divison with id \"firebaseui-auth-container\" ,this the the place where Firebase UI will be Initialized ]</strong></p>\n" +
                "<p>&nbsp;</p>\n" +
                "<blockquote>\n" +
                "<p><em>change console directory to root of your project type the following commands in the console.</em></p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p><em>pm init</em></p>\n" +
                "<p><em>npm i express firebase-admin cookie-parser https fs&nbsp;</em></p>\n" +
                "<p><strong><em>(last two packages are only needed if you wanna save cookies in local-host, however if you will be running backend on https then there is no need, I will talk about this later in the article)</em></strong></p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p>const express = require(\"express\");<br />const admin = require(\"firebase-admin\");<br />const cookieParser = require(\"cookie-parser\");<br />const https = require('https');<br />const fs = require('fs');</p>\n" +
                "<p>const app = express();<br />app.use(cookieParser());</p>\n" +
                "<p>var key=\"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQE---your key here---+1d4\\n-----END PRIVATE KEY-----\\n\";</p>\n" +
                "<p>admin.initializeApp({<br />&nbsp; &nbsp; credential: admin.credential.cert({</p>\n" +
                "<p>&nbsp; &nbsp; &nbsp; &nbsp; \"private_key\": key.replace(/\\\\n/g, '\\n'),<br />&nbsp; &nbsp; &nbsp; &nbsp; \"client_email\": \"YOUR CLIENT EMAIL HERE\",<br />&nbsp; &nbsp; &nbsp; &nbsp; \"project_id\": \"YOUR PROJECT ID \"<br />&nbsp; &nbsp; })<br />});</p>\n" +
                "<p>app.get('/',(req,res)=&gt;{</p>\n" +
                "<p>&nbsp; &nbsp;res.sendFile(__dirname +'/login.html'); &nbsp;//You can use render in case of ejs&nbsp;<br />});</p>\n" +
                "<p><br />app.get('/logout',(req,res)=&gt;{<br />&nbsp; &nbsp; res.clearCookie('__session');<br />&nbsp; &nbsp; res.redirect('/');<br />});</p>\n" +
                "<p>app.get('/success',checkCookie,(req,res)=&gt;{<br />&nbsp; &nbsp; res.sendFile(__dirname + '/success.html');<br />&nbsp; &nbsp; console.log(\"UID of Signed in User is\" + req.decodedClaims.uid);<br />&nbsp; &nbsp; //You will reach here only if session is working Fine<br />});</p>\n" +
                "<p>app.get('savecookie',(req,res)=&gt;{</p>\n" +
                "<p>&nbsp; &nbsp; const Idtoken=req.query.token;<br />&nbsp; &nbsp; setCookie(Idtoken,res);<br />});</p>\n" +
                "<p>//saving cookies and verify cookies<br />// Reference : https://firebase.google.com/docs/auth/admin/manage-cookies</p>\n" +
                "<p>function savecookie(idtoken,res){</p>\n" +
                "<p>&nbsp; &nbsp; const expiresIn = 60 * 60 * 24 * 5 * 1000;<br />&nbsp; &nbsp; admin.auth().createSessionCookie(idtoken,{expiresIn})<br />&nbsp; &nbsp; .then((sessionCookie)=&gt;{<br />&nbsp; &nbsp; &nbsp; &nbsp; const options = {maxAge: expiresIn, httpOnly: true, secure: true};</p>\n" +
                "<p>&nbsp; &nbsp; &nbsp; &nbsp; admin.auth().verifyIdToken(idtoken).then(function(decodedClaims){<br />&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; res.redirect('/success');<br />&nbsp; &nbsp; &nbsp; &nbsp; });</p>\n" +
                "<p>&nbsp; &nbsp; },error=&gt;{<br />&nbsp; &nbsp; &nbsp; &nbsp; res.status(401).send(\"UnAuthorised Request\");</p>\n" +
                "<p>&nbsp; &nbsp; });<br />}</p>\n" +
                "<p><br />function checkCookie(req,res,next){</p>\n" +
                "<p><br />&nbsp;const sessionCookie = req.cookies.__session || '';<br />&nbsp;admin.auth().verifySessionCookie(<br />&nbsp; sessionCookie, true).then((decodedClaims) =&gt; {<br />&nbsp; &nbsp;req.decodedClaims = decodedClaims;<br />&nbsp; &nbsp;next();<br />&nbsp; })<br />&nbsp; .catch(error =&gt; {<br />&nbsp; &nbsp;// Session cookie is unavailable or invalid. Force user to login.<br />&nbsp; &nbsp;res.redirect('/');<br />&nbsp; });</p>\n" +
                "<p>}</p>\n" +
                "<p>&nbsp;</p>\n" +
                "</blockquote>\n" +
                "<p><em>Here is the app.js file, If you look at it closely ,you will find that there is no port listening to our request. Like I said above, this is where we need those two node modules.</em></p>\n" +
                "<p><em>Most browsers does't allow you to save cookies in local host,this is why we will setup HTTPS connection for our localhost</em></p>\n" +
                "<p>&nbsp;</p>\n"

    fun html()=html
}