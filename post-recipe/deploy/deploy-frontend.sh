kubectl create namespace ui
kubectl apply -f service-account-frontend-post.yaml -n ui
kubectl apply -f deployment.yaml -n ui
kubectl expose deployment frontend-post -n ui --type=LoadBalancer --port 80 --target-port 8080
# kubectl autoscale deployment frontend --cpu-percent=5 --min=1 --max=2 -n ui

