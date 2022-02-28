# FastAndFuriousCinema_FouthWall

FourthWall - Backend Coding Challenge

BEFORE RUN

1) Add open movie database api key to system envs a) MACOS -> Execute command "export omd_secret_key={api_key}"
   b) Linux ->
   c) Windows ->

TODO

1) Change .properties to .yaml
2) Add more detailed description in Controllers (Swagger)
3) Consider extract insertDate and modificationDate to external class
4) Add logging to services
5) Upgrade methods in OpenMovieDatabaseResponseMapper a) mapToBoxOfficePairCurrencyValue b) unifyDoubleValue
6) Add hints to queries (with(index,nolock))

{
"cinemaId": 1,
"movieId": 1,
"dates": [
{
"day": 4,
"timeList": [
"13:30:00+01:00",
"14:30:00+01:00"
]  
}   
],
"clearOld": false }