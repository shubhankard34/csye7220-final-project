
# CSYE 7220 - Final Project - Recipe Management
## Team Information
| Name | NEU ID | Email Address |
| --- | --- | --- |
| Shubhankar Dandekar| 001439467| dandekar.s@husky.neu.edu |
| Vani Somashekar | 001420871 | somashekar.v@husky.neu.edu|

# To run locally for development
## Install MySql Server
Install the MySQL server by using the Ubuntu operating system package manager:
```sh
sudo apt-get update
sudo apt-get install mysql-server
```
The installer installs MySQL and all dependencies.

If the secure installation utility does not launch automatically after the installation completes, enter the following command:

```sh
sudo mysql_secure_installation utility
```
**Set the password as "root".**

This utility prompts you to define the mysql root password and other security-related options, including removing remote access to the root user and setting the root password. 

After the installation is complete, you can start the database service by running the following command. If the service is already started, a message informs you that the service is already running:
```sh
sudo systemctl start mysql
```
To ensure that the database server launches after a reboot, run the following command:
```sh
sudo systemctl enable mysql
```

Restart the mysql service.
```sh
sudo systemctl restart mysql
```

Start the MySql shell: 
```sh
sudo mysql -u root -p
```
Enter password as "root" when prompted.

The following mysql shell prompt should appear:
```sh
mysql>
```
Run the following to use MySql without sudo:
```sql
mysql > ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root';
mysql > FLUSH PRIVILEGES;
mysql > CREATE DATABASE csye7220;
mysql > exit
```

## Run the backend microservice
1. [Click here to Download Spring Tools Suite (STS IDE)](https://download.springsource.com/release/STS4/4.6.0.RELEASE/dist/e4.15/spring-tool-suite-4-4.6.0.RELEASE-e4.15.0-linux.gtk.x86_64.tar.gz)
2. Open the `recipe-backend` project in STS
3. Run it as a Springboot Application

## Run ReactJS front end
1. Make sure you have node and npm installed. [Click here and follow the instructions to install the latest versions](https://www.digitalocean.com/community/tutorials/how-to-install-node-js-on-ubuntu-18-04) 
2. Open a terminal and navigate inside the directory `recipe-frontend`
3. Run `npm run install-deps` to install all the dependencies
4. Run `npm run start-dev`
5. Now open your browser and go to http://localhost:8080
6. If you open it for the first time, the webpage would be empty since there are no recipes posted.
7. Open POSTMAN and make a post request to http://localhost:8080/v1/recipe and include [this dummy template json](./dummy-recipe-template.json) in the body
8. Now, you should be able to see a recipe at http://localhost:8080

# To run on kubernetes cluster on AWS

## Requirements

1. AWS account
2. `aws-cli`
3. `kops`
4. `ansible`
5. `helm`

## Start Cluster and app using the ansible-playbook

1. Create an s3 bucket on AWS named `<your-bucket-name>`
2. Open terminal and navigate to `infrastructure` directory
3. Run the following command to start the kubernetes server on AWS
    ```xml
    ansible-playbook main.yaml --extra-vars "command=start cluster_name=k8s.local kops_state_store=s3://<your-bucket-name>"
    ```
4. Open AWS console > EC2 > LoadBalancers
5. Note the URLs for the LoadBalancer created for both your Apps
6. Open these URLs in your browser

## Delete the created cluster and free your resources when done using

1. Open terminal and navigate to `infrastructure` directory
2. Run the following command to delete the kubernetes server on AWS
    ```xml
    ansible-playbook main.yaml --extra-vars "command=delete cluster_name=k8s.local kops_state_store=s3://<your-bucket-name>"
    ```

