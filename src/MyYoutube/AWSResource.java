package MyYoutube;

import java.io.File;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.auth.policy.Policy;
import com.amazonaws.auth.policy.Principal;
import com.amazonaws.auth.policy.Statement;
import com.amazonaws.auth.policy.Statement.Effect;
import com.amazonaws.auth.policy.actions.S3Actions;
import com.amazonaws.auth.policy.resources.S3ObjectResource;
import com.amazonaws.services.cloudfront.AmazonCloudFrontClient;
import com.amazonaws.services.cloudfront.model.Aliases;
import com.amazonaws.services.cloudfront.model.CacheBehaviors;
import com.amazonaws.services.cloudfront.model.CookiePreference;
import com.amazonaws.services.cloudfront.model.CreateCloudFrontOriginAccessIdentityRequest;
import com.amazonaws.services.cloudfront.model.CreateDistributionRequest;
import com.amazonaws.services.cloudfront.model.CreateStreamingDistributionRequest;
import com.amazonaws.services.cloudfront.model.DefaultCacheBehavior;
import com.amazonaws.services.cloudfront.model.DistributionConfig;
import com.amazonaws.services.cloudfront.model.ForwardedValues;
import com.amazonaws.services.cloudfront.model.LoggingConfig;
import com.amazonaws.services.cloudfront.model.Origin;
import com.amazonaws.services.cloudfront.model.Origins;
import com.amazonaws.services.cloudfront.model.PriceClass;
import com.amazonaws.services.cloudfront.model.S3Origin;
import com.amazonaws.services.cloudfront.model.S3OriginConfig;
import com.amazonaws.services.cloudfront.model.StreamingDistributionConfig;
import com.amazonaws.services.cloudfront.model.StreamingLoggingConfig;
import com.amazonaws.services.cloudfront.model.TrustedSigners;
import com.amazonaws.services.cloudfront.model.ViewerProtocolPolicy;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;


public class AWSResource {
	
    static AmazonS3Client s3;
    
    public static List<S3ObjectSummary> getVideoList() throws Exception {

    	AmazonS3Client s3Client;
		AWSCredentials credentials = new PropertiesCredentials(
   	 			index.class.getResourceAsStream("AwsCredentials.properties"));
		s3Client = new AmazonS3Client(credentials);
		
		ObjectListing objList = s3Client.listObjects("xiangyaoyoutube");
		List<S3ObjectSummary> keyList = objList.getObjectSummaries();
		ObjectListing next = s3Client.listNextBatchOfObjects(objList);
		keyList.addAll(next.getObjectSummaries());

		while (next.isTruncated()) {
			objList=s3Client.listNextBatchOfObjects(next);
			keyList.addAll(objList.getObjectSummaries());
			next =s3Client.listNextBatchOfObjects(objList);
		}
		keyList.addAll(next.getObjectSummaries());
        
		return keyList;
	}    
    
    public static void main(String[] args) throws Exception {

   	 	AWSCredentials credentials = new PropertiesCredentials(
   	 			index.class.getResourceAsStream("AwsCredentials.properties"));

   	 	System.out.println();
	  	System.out.println("#1 S3 bucket and object");
	  	s3  = new AmazonS3Client(credentials);
	      
	     /* create bucket */
	     String bucketName = "xiangyaoyoutube";
	     s3.createBucket(bucketName);
	      	     
	     /* set key */
	     String key1 = "jj.html";
	     /* set value */
	     File new_file1 = new File("/Users/will/Desktop/jwplayer/jj.html");

	     /* set key */
	     String key2 = "jwplayer.flash.swf";
	     /* set value */
	     File new_file2 = new File("/Users/will/Desktop/jwplayer/jwplayer.flash.swf");
	     
	     /* set key */
	     String key3 = "jwplayer.html5.js";
	     /* set value */
	     File new_file3 = new File("/Users/will/Desktop/jwplayer/jwplayer.html5.js");
	     
	     /* set key */
	     String key4 = "jwplayer.js";
	     /* set value */
	     File new_file4 = new File("/Users/will/Desktop/jwplayer/jwplayer.js");	     
	     
	     /* put object - bucket, key, value(file) */
	     s3.putObject(new PutObjectRequest(bucketName, key1, new_file1));
	     s3.putObject(new PutObjectRequest(bucketName, key2, new_file2));
	     s3.putObject(new PutObjectRequest(bucketName, key3, new_file3));
	     s3.putObject(new PutObjectRequest(bucketName, key4, new_file4));
	     	     
	     System.out.println("# Successfully put files into S3 bucket...");
	     System.out.println();
	     
	     /* set access control for the bucket */
	     Statement allowPublicReadStatement = new Statement(Effect.Allow)
		         .withPrincipals(Principal.AllUsers)
		         .withActions(S3Actions.GetObject)
		         .withResources(new S3ObjectResource(bucketName, "*"));
		
	     Policy policy = new Policy()
		         .withStatements(allowPublicReadStatement);
	     s3.setBucketPolicy(bucketName, policy.toJson());
	     
	     /* create cloudfront clients */
	     System.out.println();
	     System.out.println("#2 CloudFront Web Distribution");
	     AmazonCloudFrontClient cloudfront = new AmazonCloudFrontClient(credentials);
	     CreateCloudFrontOriginAccessIdentityRequest originRequest = new CreateCloudFrontOriginAccessIdentityRequest();
	     originRequest.setRequestCredentials(credentials);
	     Origin origin = new Origin()
	        .withDomainName(bucketName+".s3.amazonaws.com")
	        .withId(bucketName)
	        .withS3OriginConfig(new S3OriginConfig().withOriginAccessIdentity(""));

	     Origins origins = new Origins().withItems(origin);
	     /* set web distribution configs */
	     DistributionConfig webDistributionConfig = new DistributionConfig()
	     	.withCallerReference(System.currentTimeMillis() + "")
	     	.withComment("web CloudFront distribution")
	     	.withDefaultRootObject("jj.html")
	     	.withEnabled(true)
	        .withOrigins(origins.withQuantity(1))
	     	.withAliases(new Aliases().withQuantity(0))
	     	.withDefaultCacheBehavior(new DefaultCacheBehavior()
    	 		.withTargetOriginId(bucketName)
    	 		.withForwardedValues(new ForwardedValues().withQueryString(false).withCookies(new CookiePreference().withForward("none")))
    	 		.withTrustedSigners(new TrustedSigners().withQuantity(0).withEnabled(false))
    	 		.withViewerProtocolPolicy(ViewerProtocolPolicy.AllowAll)
    	 		.withMinTTL((long)36000))
    	 	.withCacheBehaviors(new CacheBehaviors().withQuantity(0))
    	 	.withLogging(new LoggingConfig().withEnabled(false).withBucket("").withPrefix("").withIncludeCookies(false))
    	 	.withEnabled(true)
    	 	.withPriceClass(PriceClass.PriceClass_All);
	     /* create web distribution */
	     CreateDistributionRequest webDistribution = new CreateDistributionRequest()
	        .withDistributionConfig(webDistributionConfig);           
	     cloudfront.createDistribution(webDistribution);
	     System.out.println("# Successfully create web distribution cloudfront client...");
	     
	     /* create RTMP distribution config */
	     System.out.println();
	     System.out.println("#3 CloudFront RTMP distribtion");
	     StreamingDistributionConfig streamingDistributionConfig = new StreamingDistributionConfig()
	     	.withCallerReference(System.currentTimeMillis() + "")
	     	.withComment("web CloudFront distribution")
	     	.withEnabled(true)
	        .withS3Origin(new S3Origin(bucketName).withDomainName(bucketName+ ".s3.amazonaws.com").withOriginAccessIdentity(""))
	     	.withAliases(new Aliases().withQuantity(0))
 	 		.withTrustedSigners(new TrustedSigners().withQuantity(0).withEnabled(false))
 	 		.withEnabled(true)
 	 		.withPriceClass(PriceClass.PriceClass_All)
 	 		.withLogging(new StreamingLoggingConfig().withBucket(bucketName).withEnabled(false).withBucket("").withPrefix(""));
	     CreateStreamingDistributionRequest streamingDistribution = new CreateStreamingDistributionRequest()
	           .withStreamingDistributionConfig(streamingDistributionConfig);          
	     cloudfront.createStreamingDistribution(streamingDistribution);
	     System.out.println("# Successfully create RTMP distribution cloudfront client...");
	
    }
	
	
	
}
