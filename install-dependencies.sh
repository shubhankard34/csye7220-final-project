sudo apt install curl -y &&
sudo apt-get update &&
sudo apt-get upgrade -y &&
sudo apt-get install python -y &&
sudo apt install software-properties-common -y &&
sudo apt-add-repository --yes --update ppa:ansible/ansible -y &&
sudo apt install ansible -y &&
sudo apt install python-pip -y &&
pip install boto3 &&
pip install boto &&
curl -LO https://storage.googleapis.com/kubernetes-release/release/`curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt`/bin/linux/amd64/kubectl &&
chmod +x ./kubectl &&
sudo mv ./kubectl /usr/local/bin/kubectl &&
wget https://get.helm.sh/helm-v2.16.6-linux-amd64.tar.gz &&
tar xvf helm-v2.16.6-linux-amd64.tar.gz &&
sudo mv linux-amd64/helm /usr/local/bin/ &&
rm helm-v2.16.6-linux-amd64.tar.gz &&
rm -rf linux-amd64 &&
curl -Lo kops https://github.com/kubernetes/kops/releases/download/$(curl -s https://api.github.com/repos/kubernetes/kops/releases/latest | grep tag_name | cut -d '"' -f 4)/kops-linux-amd64 &&
chmod +x ./kops &&
sudo mv ./kops /usr/local/bin/ &&
pip install openshift &&
sudo apt install awscli -y &&
aws configure
