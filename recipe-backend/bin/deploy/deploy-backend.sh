echo "Enter Environment Profile"
read ENV

echo "Enter S3 Bucket ARN"
read S3_ARN

echo "Enter AWS Access Key ID"
read ACCESS_KEY

echo "Enter AWS Secret Access Key"
read SECRET_ACCESS

echo "Enter Image name (dockerRepo/Image:tag)"
read IMAGE_NAME

RDS_URL="jdbc:mysql://$(AWS_PROFILE=${ENV} aws rds describe-db-instances --db-instance-identifier mysql-database | jq -r '.DBInstances[0].Endpoint.Address'):3306/csye7374?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&createDatabaseIfNotExist=true"

echo "Deploying Single Node ElasticSearch Cluster"
kubectl apply -f ./elastic/service-account.yaml -n api
kubectl apply -f ./elastic/es-rc.yaml -n api
kubectl apply -f ./elastic/es-svc.yaml -n api
echo "Creating MySQL Secrets"
echo "Enter MySQL username"
read RDS_USER

echo "Enter MySQL Password"
read RDS_PASSWORD

kubectl create secret generic credentials \
  --from-literal=user=${RDS_USER} \
  --from-literal=password=${RDS_PASSWORD} \
  --from-literal=bucket=${S3_ARN} \
  --from-literal=host=${RDS_URL} \
  --from-literal=AWS_ACCESS_KEY_ID=${ACCESS_KEY} \
  --from-literal=AWS_SECRET_ACCESS_KEY=${SECRET_ACCESS} \
  -n api

echo "Creating Image Pull Secret"
echo "Enter Docker username"
read DOCKER_USER

echo "Enter DOCKER Password"
read DOCKER_PASSWORD

echo "Enter DOCKER Email"
read DOCKER_EMAIL

kubectl create secret docker-registry mysecret --docker-server=https://index.docker.io/v1/ \
 --docker-username=${DOCKER_USER} \
 --docker-password=${DOCKER_PASSWORD} \
 --docker-email=${DOCKER_EMAIL} \
 -n api

kubectl create secret docker-registry mysecret --docker-server=https://index.docker.io/v1/ \
 --docker-username=${DOCKER_USER} \
 --docker-password=${DOCKER_PASSWORD} \
 --docker-email=${DOCKER_EMAIL} \
 -n ui

echo "Creating Service Account"

kubectl apply -f service-account-backend.yaml -n api

echo "Creating Backend Deployment"

sed -i "s|placeholder|$IMAGE_NAME|" deployment.yaml

kubectl apply -f deployment.yaml -n api

echo "Exposing Backend Service with load balancer"

kubectl expose deployment backend -n api  --type=NodePort --port 8080 --target-port 8080

kubectl autoscale deployment backend --cpu-percent=5 --min=1 --max=2 -n api

kubectl create clusterrolebinding jenkins-default --clusterrole=cluster-admin --serviceaccount=jenkins:default

echo "RDS URL: ${RDS_URL}"


