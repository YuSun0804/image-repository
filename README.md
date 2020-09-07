# image-repository

## system requirement
- mysql 8
- java 13

## set up process
- create database 
```sql
create database image_repository;
```
- create user
```sql
create user "shopify"@"localhost" indetified by "shopify";
```
- grant privilege
```sql
grant all privileges on image_repository.* to "shopify"@"localhost";
```