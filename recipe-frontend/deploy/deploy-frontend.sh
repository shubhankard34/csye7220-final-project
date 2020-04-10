echo "Enter Environment Profile"
read ENV

echo "Enter Image name (dockerRepo/Image:tag)"
read IMAGE_NAME

echo "Creating Service Account"

kubectl apply -f service-account-frontend -n api

echo "Creating Frontend Deployment"

sed -i "s|placeholder|$IMAGE_NAME|" deployment.yaml

kubectl apply -f deployment.yaml -n ui


echo "Exposing Backend Service with load balancer"

kubectl expose deployment frontend -n ui --type=NodePort --port 8080 --target-port 8080
kubectl autoscale deployment frontend --cpu-percent=5 --min=1 --max=2 -n ui

