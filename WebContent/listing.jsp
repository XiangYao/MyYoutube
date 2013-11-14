
<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="MyYoutube.AWSResource"%>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../docs-assets/ico/favicon.png">

    <title>MyYoutube</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    
    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

    <!-- Custom styles for this template -->
    <link href="css/carousel.css" rel="stylesheet">
        
    <%
   	List<String> videoList = new ArrayList<String>();
   	List<String> dateList = new ArrayList<String>();
   	if (request.getAttribute("video_list") == null) {
 		response.sendRedirect("/MyYoutube/listing");
 	} else {
   		videoList = (ArrayList<String>)request.getAttribute("video_list");
   		dateList = (ArrayList<String>)request.getAttribute("date_list");
   	}
    %>
    
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

	<% for (int i=0; i<videoList.size(); i++) { %>
	  <% if (i%2 == 0) { %>
	      <div class="row featurette">
	        <div class="col-md-7">
	          <h5 class="featurette-heading"> Top <% out.print(i+1); %> -- <span class="text-muted"><% out.print(videoList.get(i)); %>.</span></h5>
	       	  
	       	  <div class="starRate">
			  <div>Currently rated: 3 stars<b></b></div>
			  <ul>
			  <li><a href="#"><span>Give it 5 stars</span></a></li>
			  <li><a href="#"><span>Give it 4 stars</span></a></li>
			  <li><a href="#"><span>Give it 3 stars</span><b></b></a></li>
			  <li><a href="#"><span>Give it 2 stars</span></a></li>
			  <li><a href="#"><span>Give it 1 star</span></a></li>
			  </ul>
			  </div>
	       	  
	       	  <form action="delete" method="post" id="delete">
				  <input type="hidden" name="filename" id="deleteFilename" value=<%out.print(videoList.get(i));%>> 
			      <input type="submit" value="Delete From S3"> 
			  </form>
	       	  
	       	  <h6 class="featurette-heading"> Upload Time:</h6>
	       	  <h3><span class="text-muted"><% out.print(dateList.get(i)); %>.</span></h3>
	        </div>
	        <div class="col-md-5">
	          <script type='text/javascript' src='https://d2mgt2m49d2xua.cloudfront.net/jwplayer.js'></script>
			  <div id='mediaplayer<%out.print(i);%>'></div>
			  <script type="text/javascript">
			  	jwplayer('mediaplayer<%out.print(i);%>').setup({
					file: "rtmp://s1hjuh8tieb9mz.cloudfront.net/cfx/st/<% out.print(videoList.get(i)); %>",
					width: "500",
					height: "400"
				});
			  </script>
	        </div>
	      </div>
      <% } else { %>
	      <div class="row featurette">
	      	<div class="col-md-7">
	          <script type='text/javascript' src='https://d2mgt2m49d2xua.cloudfront.net/jwplayer.js'></script>
			  <div id='mediaplayer<%out.print(i);%>'></div>
			  <script type="text/javascript">
			  	jwplayer('mediaplayer<%out.print(i);%>').setup({
					file: "rtmp://s1hjuh8tieb9mz.cloudfront.net/cfx/st/<% out.print(videoList.get(i)); %>",
					width: "500",
					height: "400"
				});
			  </script>
	        </div>
	        <div class="col-md-5">
	          <h5 class="featurette-heading"> Top <% out.print(i+1); %> -- <span class="text-muted"><% out.print(videoList.get(i)); %>.</span></h5>
	       	  
	       	  <div class="starRate">
			  <div>Currently rated: 3 stars<b></b></div>
			  <ul>
			  <li><a href="#"><span>Give it 5 stars</span></a></li>
			  <li><a href="#"><span>Give it 4 stars</span></a></li>
			  <li><a href="#"><span>Give it 3 stars</span><b></b></a></li>
			  <li><a href="#"><span>Give it 2 stars</span></a></li>
			  <li><a href="#"><span>Give it 1 star</span></a></li>
			  </ul>
			  </div>
			  
	       	  <form action="delete" method="post" id="delete">
				  <input type="hidden" name="filename" id="deleteFilename" value=<%out.print(videoList.get(i));%>> 
			      <input type="submit" value="Delete From S3"> 
			  </form>
			  	       	  
	       	  <h6 class="featurette-heading"> Upload Time:</h6>
	       	  <h3><span class="text-muted"><% out.print(dateList.get(i)); %>.</span></h3>
	        </div>
	      </div>
      <% } %>
      	<hr class="featurette-divider">
	<% } %>
	  
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
