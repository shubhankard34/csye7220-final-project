RDS_URL="jdbc:mysql://$(aws rds describe-db-instances --db-instance-identifier mysql-database | jq -r '.DBInstances[0].Endpoint.Address'):3306/csye7220?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&createDatabaseIfNotExist=true"
kubectl create namespace api
RDS_USER="root"
RDS_PASSWORD="1nsecure"
kubectl create secret generic credentials \
  --from-literal=user=${RDS_USER} \
  --from-literal=password=${RDS_PASSWORD} \
  --from-literal=host=${RDS_URL} \
  -n api

echo "Creating Service Account"

kubectl apply -f service-account-backend.yaml -n api

echo "Creating Backend Deployment"

kubectl apply -f deployment.yaml -n api

echo "Exposing Backend Service with load balancer"

kubectl expose deployment backend -n api  --type=NodePort --port 80 --target-port 8080

# kubectl autoscale deployment backend --cpu-percent=5 --min=1 --max=2 -n api

# kubectl create clusterrolebinding jenkins-default --clusterrole=cluster-admin --serviceaccount=jenkins:default



