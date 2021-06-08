# Please find the runnable jar and documentations in the following google drive folder 
https://drive.google.com/drive/folders/1aWu8nYHTB5FL75toWx-pHmS7uISz7td-

# Regarding the deliverables

	# Source code:
		- Kindly find the below github "online-book-store" repo 
		https://github.com/S-Sayed/online-book-store.git
		
		AND PLEASE NOTE THAT, I've pushed the code commit by commit to my own repo https://github.com/S-Sayed/java-code-examples.git but this repo contains many projects I have done, so if you want to check the commits during the development stages, it is here 
		https://github.com/S-Sayed/java-code-examples/commits/master/online-book-store

		- And please note that I've used the below in the implementation 
			- Java 8 features like stream, Datetime, Predicate, Lambda, Optional
			- Spring (core, boot, AOP, cache, data, test, ...)
			- Junit, Mockito
			- Design patterns
			- SOLID principles 
			- Swagger and springdoc for openAPI
			
	# Running instructions
		- Please find the packaged spring boot application "online-book-store.jar" in the above google drive location, or you can pull the docker image form the below docker hub line  https://hub.docker.com/r/ssayed7190/sameh-public-repo/tags 

		- To run the api
			- 1. Pre-requisites.
				- in case you will use java command, JRE should be installed 
				- in case you will use docker image, Docker should be installed 
				
			- 2. Run the application.
				- in case JAVA, please run command 
					"java -jar online-book-store.jar"
				
				- in case Docker, please run command 
					"docker pull ssayed7190/sameh-public-repo:online-book-store"
				
				- If you will checkout the code, and want to build an image, you can use the below commands 
					- to build image 
						- docker build -t ssayed7190/sameh-public-repo:online-book-store .
					
					- to run container 
						- docker run -it -p 8888:8888 ssayed7190/sameh-public-repo:online-book-store
						
				- If you will checkout the code and want to run it using maven, please run the below command
					- mvn spring-boot:run 
					
			- the spring boot will start the embedded tomcat container on port 8888
				
	# OpenAPI spec for your REST service
		- please find the "book-api-specs.yaml" file for the OpenAPI specs in the above google drive location and you can access the Swagger UI from this link http://localhost:8888/book-api-consumer-ui.html to consume the API
		
		- you can refer to the "Online Book Store API Scenarios.pdf" file to view the screen shots of all API operations
		
			
	# Consuming the API  
		- Please refer to the "Online Book Store API Scenarios.pdf" file
			
	# Test Cases
		- To get the test cases report, please 
			- 1. checkout/ download the code, and
			- 2. make sure that maven is installed in your machine, then 
			- 3. navigate to the location of downloaded project (step #1) using cd command
			- 4. run the command mvn surefire-report:report, then you will find the report in this location {downloadedPorjectLocation}/target/site/surefire-report.html (sample SS "Test Cases Report.JPG" is attached as well)
		
		- if you want to run the test cases, please download/ checkout the code, and run the below command 
			- mvn clean test 
			
	# Points to highlight 
		- I didn't use an API gateway like ZUUL, because it is not mentioned in the task but I can use it if you want.
		
		- I didn't use service discovery like Eureka for balancing the load because the scaling is not mentioned in the task but I can use it if you want.