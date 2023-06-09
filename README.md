Technical requirement:

Design and implement a REST API using Hibernate/Spring/SpringMVC (Spring-Boot preferred!) without frontend.

The task is:

Build a voting system for deciding where to have lunch.

2 types of users: admin and regular users

Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)

Menu changes each day (admins do the updates)

Users can vote for a restaurant they want to have lunch at today

Only one vote counted per user

If user votes again the same day:

If it is before 11:00 we assume that he changed his mind.

If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides a new menu each day.

As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it (better - link to Swagger).

http://localhost:8080/swagger-ui/index.html

user@yandex.com/ password

admin@yandex.com/ admin

curl samples (application deployed at application context restaurant-voiting).

get All Users
curl -s http://localhost:8080/api/admin/users

get Users 1
curl -s http://localhost:8080/api/admin/users/1

get All Dish
curl -s http://localhost:8080/api/dishes

get All User Voices
curl -s http://localhost:8080/api/votes

create Vote
curl -s -X POST -d '{"restaurantId": 1}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/api/votes

update Vote
curl -s -X PUT -d '{"restaurantId": 1}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/api/votes/1

