build backend image:
- cd backend
- mvn clean install -DskipTests=true
- docker build -t backend:1.0 .

verify:
- docker images


apply image to minikube:
- start minikube
- minikube image load backend:1.0

verify:
- minikube ssh
- docker images
- exit


if everything is fine - deploy app
in project root:
- k apply -f postgres-config.yaml
- k apply -f backend-config.yaml

verify:
-k get pod

endpoint call:
- minikube service backend --url

use produced address as endpoint host, for example:
- wget http://127.0.0.1:52600/


to reach db through intellij
run:
- k get pod
- k port-forward <postgres-pod-name> 5433:5432

connection specification:
- url: jdbc:postgresql://localhost:5433/postgres_db
- user: postgres
- password: 12345