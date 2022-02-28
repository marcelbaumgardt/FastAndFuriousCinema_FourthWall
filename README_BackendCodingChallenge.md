# Backend Coding Challenge

Hi 👋! Thank you for taking the time to participate in this coding challenge to develop a backend service. The following task is going to show us your skills, experience and coding style. Please remember that you can use as many tools as you feel comfortable with.

## What to expect?

We expect that it will take 3-8 hours to complete this exercise. While you're welcome to take as much extra time as you'd like to clean up or improve the codebase, it's not required. If there's something that you would have done in a "real" production environment, feel free to add details to your `README`, so we can better understand what next steps that you'd take.

## Challenge

A small cinema, which only plays movies from the [Fast & Furious](https://en.wikipedia.org/wiki/The_Fast_and_the_Furious) franchise, is looking to develop a mobile/web app for their users. Specifically, they wish to support the following functions:

* An internal endpoint in which they (i.e. the cinema owners) can update show times and prices for their movie catalog
* An endpoint in which their customers (i.e. moviegoers) can fetch movie times
* An endpoint in which their customers (i.e. moviegoers) can fetch details about one of their movies (e.g. name, description, release date, rating, IMDb rating, and runtime). Even though there's a limited offering, please use the OMDb APIs (detailed below) to demonstrate how to communicate across APIs.
* An endpoint in which their customers (i.e. moviegoers) can leave a review rating (from 1-5 stars) about a particular movie
* And adding anything else that you think will be useful for the user...

You will be responsible for the first iteration of API which will be used by another engineer, who is developing a studio cinema mobile application. To assist him/her, your design should do the following:

* Creating a persistence layer to save results for certain actions (e.g. via SQL/NoSQL/etc)
* Present API documentation leveraging OpenAPI/Swagger 2.0 standard

## Movie Catalog

This cinema plays the following movies:

| Title | IMDb ID |
| :- | :- |
| [The Fast and the Furious](https://www.imdb.com/title/tt0232500) | tt0232500 |
| [2 Fast 2 Furious](https://www.imdb.com/title/tt0322259) | tt0322259 |
| [The Fast and the Furious: Tokyo Drift](https://www.imdb.com/title/tt0463985)| tt0463985 |
| [Fast & Furious](https://www.imdb.com/title/tt1013752) | tt1013752 |
| [Fast Five](https://www.imdb.com/title/tt1596343) | tt1596343 |
| [Fast & Furious 6](https://www.imdb.com/title/tt1905041) | tt1905041 |
| [Furious 7](https://www.imdb.com/title/tt2820852) | tt2820852 |
| [The Fate of the Furious](https://www.imdb.com/title/tt4630562) | tt4630562 |
| [F9: The Fast Saga](https://www.imdb.com/title/tt5433138) | tt5433138 |


## OMDb APIs

When you fetch details about the movie, you'll want to use the [Open Movie Database API](http://www.omdbapi.com/), which is a RESTful web service to obtain movie information. A sample request to fetch the details for the first movie would look like:

* `http://www.omdbapi.com/?apikey=[yourkey]&i=tt0232500`

And have the following response:

```json
{  
   "Title":"The Fast and the Furious",
   "Year":"2001",
   "Rated":"PG-13",
   "Released":"22 Jun 2001",
   "Runtime":"106 min",
   "Genre":"Action, Crime, Thriller",
   "Director":"Rob Cohen",
   "Writer":"Ken Li (magazine article \"Racer X\"), Gary Scott Thompson (screen story), Gary Scott Thompson (screenplay), Erik Bergquist (screenplay), David Ayer (screenplay)",
   "Actors":"Paul Walker, Vin Diesel, Michelle Rodriguez, Jordana Brewster",
   "Plot":"Los Angeles police officer Brian O'Conner must decide where his loyalty really lies when he becomes enamored with the street racing world he has been sent undercover to destroy.",
   "Language":"English, Spanish",
   "Country":"USA, Germany",
   "Awards":"11 wins & 12 nominations.",
   "Poster":"https://m.media-amazon.com/images/M/MV5BNzlkNzVjMDMtOTdhZC00MGE1LTkxODctMzFmMjkwZmMxZjFhXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg",
   "Ratings":[  
      {  
         "Source":"Internet Movie Database",
         "Value":"6.8/10"
      },
      {  
         "Source":"Rotten Tomatoes",
         "Value":"53%"
      },
      {  
         "Source":"Metacritic",
         "Value":"58/100"
      }
   ],
   "Metascore":"58",
   "imdbRating":"6.8",
   "imdbVotes":"322,264",
   "imdbID":"tt0232500",
   "Type":"movie",
   "DVD":"01 Jan 2002",
   "BoxOffice":"$142,542,950",
   "Production":"Universal Pictures",
   "Website":"http://www.thefastandthefurious.com",
   "Response":"True"
}
```

You can use the following API key for this exercise (or create your own):

```
------
```

**Note:** Since this is a secure API key, please do **not** include it in your repository if you post this to GitHub, etc. Rather, your `README.md` should detail instructions for how to supply this value.

Finally, please note that this key only supports a 1,000 daily request limit. If for whatever reason you go over to available request limit, please contact us to let us know.

## What we are looking for?

* **Treat it like production code**. Develop your software in the same way that you would for any code that is intended to be deployed to production. This includes some of the following:
    * Creating a [Spring](https://spring.io/), [Ktor](https://ktor.io/), [Micronaut](https://micronaut.io/), etc app that aligns with your best practices. You should treat this codebase as if multiple different developers will work on it for the coming months
    * Writing code in Kotlin (or Scala/Java) if that's your normal approach
    * Creating detailed commit messages, which represent discrete sets of work
* **Document your choices**. Whenever you're complete with this project, please document the choices that you've made in your project `README`.

Here are some common things that we'd recommend:

- [ ] Using separate models objects for the different layers in your app
- [ ] Writing some unit, intergration, and end-to-end tests to demonstrate how you'd approach this (full coverage not needed)
- [ ] Using immutable objects
- [ ] (Preferred but not required) writing code in a functional style

## F.A.Q.

1. _Is it OK to share your solutions publicly?_
   Yes, the questions are not prescriptive, the process and discussion around the code is the valuable part. You do the work, you own the code. Given we are asking you to give up your time, it is entirely reasonable for you to keep and use your solution as you see fit.

2. _Should I do X?_
   For any value of X, it is up to you, we intentionally leave the problem a little open-ended and will leave it up to you to provide us with what you see as important. Just remember the rough time frame of the project. If it is going to take you a couple of days, it isn't essential.

3. _Something is ambiguous, and I don't know what to do?_
   The first thing is: don't get stuck. We really don't want to trip you up intentionally, we are just attempting to see how you approach problems. That said, there are intentional ambiguities in the specifications, mainly to see how you fill in those gaps, and how you make design choices.

## How to submit?

You can do this however you see fit - you can email us a tarball, a pointer to download your code from somewhere or just a link to a source control repository. Make sure your submission includes a small `README`, documenting any assumptions, simplifications and/or choices you made, as well as a short description of how to run the code and/or tests. Finally, to help us review your code, please split your commit history in sensible chunks.

## What's next?

Once you're done, send us a confirmation email. After you submit your code, we will contact you to discuss next steps.

Good luck! 💪