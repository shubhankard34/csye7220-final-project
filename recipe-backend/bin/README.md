# f19-t4-webapp-backend

## Team Information

| Name | NEU ID | Email Address |
| --- | --- | --- |
| Shubhankar Dandekar| 001439467| dandekar.s@husky.neu.edu |
| Mitali Salvi|001630137  | salvi.mi@husky.neu.edu|
| Ashutosh Shukla|001449285 | shukla.as@husky.neu.edu|
| Lakshit Talreja|001475200 |talreja.l@husky.neu.edu |

## To run from docker container
Run the following docker command
First, run
```sh
sudo docker build -t test:v1 .
```
and then, run
```sh
sudo docker run --network="host" test:v1 -d 
```
