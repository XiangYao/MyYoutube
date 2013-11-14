
<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="MyYoutube.AWSResource"%>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../docs-assets/ico/favicon.png">

    <title>MyYoutube</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

    <!-- Custom styles for this template -->
    <link href="css/carousel.css" rel="stylesheet">
    
    
    <script type="text/javascript">
    
    	function validateFilename(filename){
        	var re = /[a-zA-Z0-9]+\.mp4/;
        	var re2 = /[a-zA-Z0-9]+\.flv/;
           	return re.test(filename) || re2.test(filename);
       	}
    	
    	function checkValid(){
            if ((document.getElementById("filename").value) == "") {
                alert("Filename can't be empty");
                return false;
            }
            if ((document.getElementById("filedata").value) == "") {
            	alert("File can't be empty");
            	return false;
            }
            else if(validateFilename(document.getElementById("filename").value)==false){
                alert("Invalid filename, please upload valid .mp4 or .flv file");
                return false;                    
            }
            else {
                altert("success");
            	return true;
            }
        }

 	</script>
    
    
    
  </head>
<!-- NAVBAR
================================================== -->
  <body>
    <div class="navbar-wrapper">
      <div class="container">

        <div class="navbar navbar-inverse navbar-static-top" role="navigation">
          <div class="container">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand" href="index.jsp">MyYoutube</a>
            </div>
            <div class="navbar-collapse collapse">
              <ul class="nav navbar-nav">
                <li class="active"><a href="index.jsp">Home</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#contact">Contact</a></li>
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                  <ul class="dropdown-menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li class="divider"></li>
                    <li class="dropdown-header">Nav header</li>
                    <li><a href="#">Separated link</a></li>
                    <li><a href="#">One more separated link</a></li>
                  </ul>
                </li>
              </ul>
            </div>
          </div>
        </div>

      </div>
    </div>


    <!-- Carousel
    ================================================== -->
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
      <!-- Indicators -->
      <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner">
        <div class="item active">
          <div class="container">
            <div class="carousel-caption">
              <h1>Live Streaming.</h1>
              <p>Welcome to MyYoutube and enjoy latest videos all over the world !</p>
              <p><a class="btn btn-lg btn-primary" href="#" role="button">Sign up today</a></p>
            </div>
          </div>
        </div>
        <div class="item">
          <div class="container">
            <div class="carousel-caption">
              <h1>Author.</h1>
              <p>Xiaotong Cheng, Xiang Yao.</p>
              <p><a class="btn btn-lg btn-primary" href="#" role="button">Learn more</a></p>
            </div>
          </div>
        </div>
        <div class="item">
          <div class="container">
            <div class="carousel-caption">
              <h1>Join Us.</h1>
              <p>Welcome to join our team to expand the site !</p>
              <p><a class="btn btn-lg btn-primary" href="#" role="button">Browse site</a></p>
            </div>
          </div>
        </div>
      </div>
      <a class="left carousel-control" href="#myCarousel" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
      <a class="right carousel-control" href="#myCarousel" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
    </div><!-- /.carousel -->



    <!-- Marketing messaging and featurettes
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->
    <div class="container marketing">

      <!-- START THE FEATURETTES -->
	  <center>      
      <img src="image/loading.gif" width="250" height="200" align="middle">
      </center>
      
      
	  <form action="https://xiangyaoyoutube.s3.amazonaws.com/" method="post" onsubmit="return checkValid()" enctype="multipart/form-data">
 	      Filename to upload to S3: 
 	      <br />
 	      <input name="key" value="" id="filename"/><br />
<%-- 	  <input type="hidden" name="key" value="${filename}"> --%> 	  
		  <input type="hidden" name="acl" value="public-read-write"> 
	      <input type="hidden" name="success_action_redirect" value="http://localhost:8080/MyYoutube/listing">
	      <input name="file" type="file" id="filedata"> 
	      <br> 
	      <input type="submit" value="Upload File to S3"> 
	  </form>


      <hr class="featurette-divider">	  
	  <center>
	  <p><a class="btn btn-default" href="index.jsp" role="button">Go back &raquo;</a></p>
	  </center>

      <hr class="featurette-divider">

      <!-- /END THE FEATURETTES -->


      <!-- FOOTER -->
      <footer>
        <p class="pull-right"><a href="#">Back to top</a></p>
        <p>&copy; Xiaotong Chen, Xiang Yao.</p>
      </footer>


    </div><!-- /.container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/holder.js"></script>
  </body>
</html>
