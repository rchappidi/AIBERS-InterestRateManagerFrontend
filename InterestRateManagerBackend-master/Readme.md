

as the schema creates the tables and relationships between them. better try to delete the database and recreate it everytime that we restart the server


**Database setup:**

step 1: run docker container using below command
docker run --name my-postgres -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=secret -e POSTGRES_DB=interestdb -p 5432:5432 -d postgres:15-alpine

Step 2: Access the PostgreSQL container's shell using below command

docker exec -it my-postgres psql -U admin -d interestdb 

-it means interactive terminal

psql is the PostgreSQL CLI tool

-U admin is the username you set

-d interestdb is the DB name you set

✅ Step 2: Inside psql, list all tables
Once you're in, run:=
\dt
This will show you all tables in the public schema.

If it returns "No relations found", it means no tables have been created yet.

✅ Extra Useful Commands inside psql
Command	Description
\dt	List tables
\d tablename	Describe a specific table
\l	List databases
\c dbname	Connect to a different database
\q	Quit