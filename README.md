# Ibexnews

Ibexnews is a news services that displays the latest news about the Ibex Companies from different media outlets. There is a **webscrapper algorithm** that periodically checks if there have been published any news concerning our company, and if there are, it adds them to our database. The webpage is **secured using jwt** and you can create a user so that you can have a personalized panel from where you can follow your favourite companies. 

Both in the user area and the companies landing page you can also take a look at the daily performance of the company. To retrieve the data, the app communicates with Alpha Vantage API and retrieves the value.

You can take a look at the project working here: https://ibexnews.web.app (Bear in mind that it uses free hosting services and it may take a while to secure connection with the microservices).

## Getting Started

The backend is composed by four microservices, two databases and an Angular App. A discover services, a feign service that routes the different requests, a user services secured with JWT and a news-service that uses a scheduled component that scraps websites every hour, sorts the news by company, date published and media outlet and stores it in a database.

## Built With

* [Angular](http://angular.io) - Framework
* [Spring Boot](http://spring.io) - Microservices
* [Tailwind](https://tailwindcss.com) - CSS Framework
* [Jsoup](https://jsoup.org) - Java Library to scrap websites
* Alpha Vantage API - API to retrieve stocks values.



## Authors

* Jaume Sánchez 
