# Customer Reward
Simple springboot application used to calculate customer reward for three month duration(current month and last two month).

Application will run on default 8080 port and will use h2 memory database  

### You can build the jar file using command (run this command on project root directory)
mvn clean install

### To run application use (Jar file available in /target folder in project directory)
java -jar assesment-0.0.1-SNAPSHOT.jar

### Get specific customer rewards points
curl http://localhost:8080/customers/rewards/{customer-id}

### Get All customer rewards points
curl http://localhost:8080/customers/rewards

### Get all transaction
curl http://localhost:8080/customers/transactions
