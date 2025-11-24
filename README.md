## Commands to Run the Project

### 1. **Clone the repository**
```bash
git clone https://github.com/madhav8511/SoftwareTesting.git
```

### 2. **Navigate to project directory**
```bash
cd SoftwareTesting
```

### 3. **Compile the project using maven**
```bash
mvn clean install
```

### 4. **Run all Junit testcase**
```bash
mvn test
```

### 5. **Perform Mutation testing using PIT**
```bash
mvn org.pitest:pitest-maven:mutationCoverage
```

### 6. **Run the Main Application**
```bash
mvn exec:java -Dexec.mainClass="org.example.Main"
```
