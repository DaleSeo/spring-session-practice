# Spring Session Practice
## Run
```
./graldew tomcatRun
```
## Test
- Servlet
```
curl -v "http://localhost:8080/session?name=user&value=Dale"
curl -v "http://localhost:8080/session" -H "x-auth-token: 3ca0848d-b255-49f2-bd24-e19b77139cce"
```
- Spring Rest
```
curl -v "http://localhost:8080/rest/session?name=Kate&pass=1234"
curl -v "http://localhost:8080/rest/session" -H "x-auth-token: d55113e9-b423-4fde-be2d-7032fff71db5"
```

## To Do
- [Bug] Embedded Redis Server doesn't start up.