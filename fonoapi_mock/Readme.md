# About #

This is a mock for the fonoapi service. 
It is used for testing purposes.
The main mocking services are:
- generate token
- get devices

# Launching #

## Docker ##
- login to the docker registry:
``` 
docker login -u <username> -p <password> <registry>
```

## Build and push ##
- build an image of services:
```
./gradlew buildDockerImage
```
- push the image to the repo (not required for the local deployment in K8s):
```
./gradlew pushDockerImage
```

## Helm charts ##
- default installation:
```
helm install fonoapi-mock chart/. --create-namespace --namespace mocks --wait
helm upgrade fonoapi-mock chart/. --create-namespace --namespace mocks --wait
helm uninstall fonoapi-mock
```