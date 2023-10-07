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
- push the image to the repo (not required
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