# Auth-Server
Common Authentication &amp; Authorization server for all microservices.

With this project we can integrate oauth2 authentication feature to microservices. When you call following api you can get jwt tokens. This token have a expiration time. When token expire, call with refresh token and grant type(refresh_token) for generate new jwt token

You have to call this api with basic authentication info like;
username: farmertracking
pass: test

Which application will be used in front of the auth server microservice, the definition of that application should be entered in the OAUTH_CLIENT_DETAILS table in my case my service's name is farmertracking

Grant Type: Password

Request:
```curl
curl -X POST \
  http://localhost:8080/auth-server/login \
  -H 'authorization: Basic VVNFUl9DTElFTlRfQVBQOnRlc3Q=' --> Basic auth user info
  -H 'cache-control: no-cache'
  -H 'content-type: application/x-www-form-urlencoded'
  -d 'username=test&password=test&grant_type=password'
```
Response:
```json
{
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ0ZXN0Iiwic2NvcGUiOlsib3BlbmlkIl0sInJvbGVzIjpbeyJpZCI6MSwibmFtZSI6IlJPTEVfQURNSU4iLCJwcml2aWxlZ2VzIjpbeyJpZCI6MSwiYXV0aG9yaXR5IjoiUkVBRF9QUklWSUxFR0UifSx7ImlkIjoyLCJhdXRob3JpdHkiOiJXUklURV9QUklWSUxFR0UifV19XSwiaWQiOjEsImV4cCI6MTYyMzUzMTkyMCwiYXV0aG9yaXRpZXMiOlsiUkVBRF9QUklWSUxFR0UiLCJXUklURV9QUklWSUxFR0UiXSwianRpIjoiZDUwZjEwNjQtNTIzYi00ZTc1LWJiMzItNDhlMGYzZGRkYzE4IiwiZW1haWwiOiJ0ZXN0QHRlc3QuY29tIiwiY2xpZW50X2lkIjoiZmFybWVydHJhY2tpbmcifQ.QWAsqlDFvWJlsozvgfC9c28Pr1sRwi1g5_FuwFFHJE1ELO8LbebrMIlTmkhPFNZqop8BRVyfFBn7JeV6XDPPpURpTl4kYlTNIEzdhKTT_DLxGzZ34AqEZ-dchc_FJH4rnkyVUulLURB9rJQEHzL8DmF9_i7TJdXmB-nzXKBGtVujcCLKT2cyGo0GliF9-0phqXhajQXGEZJ9LiSsbbtBl5zUOwyviKmeKyaQSH8sTPFyB96yn_7qmy8N-q9s2PCpnx4Z-olw0G9PSZOGaY3cwW5DGIqnM8qffS1paOT0NCxEJ33jE1uHfwTt6lU_FaAnE0NnAzc46pvQqOfPsvvyqw",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ0ZXN0Iiwic2NvcGUiOlsib3BlbmlkIl0sInJvbGVzIjpbeyJpZCI6MSwibmFtZSI6IlJPTEVfQURNSU4iLCJwcml2aWxlZ2VzIjpbeyJpZCI6MSwiYXV0aG9yaXR5IjoiUkVBRF9QUklWSUxFR0UifSx7ImlkIjoyLCJhdXRob3JpdHkiOiJXUklURV9QUklWSUxFR0UifV19XSwiYXRpIjoiZDUwZjEwNjQtNTIzYi00ZTc1LWJiMzItNDhlMGYzZGRkYzE4IiwiaWQiOjEsImV4cCI6MTYyMzUzNDYyMCwiYXV0aG9yaXRpZXMiOlsiUkVBRF9QUklWSUxFR0UiLCJXUklURV9QUklWSUxFR0UiXSwianRpIjoiMzA2MmEwMGUtMDc5Ny00YzE2LTk0ZTMtNWU3Yzg3Y2RhYjBkIiwiZW1haWwiOiJ0ZXN0QHRlc3QuY29tIiwiY2xpZW50X2lkIjoiZmFybWVydHJhY2tpbmcifQ.nUEJjZWQQ81bLORKqvt1qpATDSiLBVuyzjDF-bTbOmaHOrH1vV_cks1bguEd-7KKjin_P1n9PdM5gayeOMIzB5m3uva7k-udotWdQC0X-4BOk39w1wnfM8Rz3A4LITHt2BSjOCvG_G73bdPH40vBd1NNAkYuxrulJ7_mJrQGo5j4q-3sVDa6y7wRTHYK6Dk433IBplLUpZMuRPEsd2E9K12l28iUXoZY5s5x_KieLhjadzeCFIOXyfCTdImNPrEJ_c9cm91kX88DzYIUqn5cUX_3GBuCnM70reX_06LOYXuxR4IX4I1cmh5LlRtYtGX9sO20r-w8pt2p8Rz-W0doWA",
    "expires_in": 899,
    "scope": "openid",
    "id": 1,
    "email": "test@test.com",
    "roles": [
        {
            "id": 1,
            "name": "ROLE_ADMIN",
            "privileges": [
                {
                    "id": 1,
                    "authority": "READ_PRIVILEGE"
                },
                {
                    "id": 2,
                    "authority": "WRITE_PRIVILEGE"
                }
            ]
        }
    ],
    "jti": "d50f1064-523b-4e75-bb32-48e0f3dddc18"
}
```
If access token expired you have to call api with refresh token grant type

Grant Type: Refresh_token

Request:
```curl
curl -X POST \
  http://localhost:8080/auth-server/login
  -H 'authorization: Basic VVNFUl9DTElFTlRfQVBQOnRlc3Q='
  -H 'cache-control: no-cache'
  -H 'content-type: multipart/form-data 
  -F grant_type=refresh_token
  -F refresh_token=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ0ZXN0Iiwic2NvcGUiOlsib3BlbmlkIl0sImF0aSI6IjM1YTg2YTVmLTY2ODgtNGJmMS1hMzczLTZkMjEwZmIzODMzYyIsImlkIjoxLCJleHAiOjE2MjM1MzExNzIsImF1dGhvcml0aWVzIjpbIlJFQURfUFJJVklMRUdFIiwiV1JJVEVfUFJJVklMRUdFIl0sImp0aSI6IjQxMzE2NzRjLTYxYTMtNGZlYy1iYTdmLTc3YTc5Yzk1ZjA5NCIsImVtYWlsIjoidGVzdEB0ZXN0LmNvbSIsImNsaWVudF9pZCI6IlVTRVJfQ0xJRU5UX0FQUCJ9.A1eYYXetc2dqP2_2AU9RYg9dzYDGYkPmLTowO6fDqzIBsHwczci0AvNDcQC-gB4HwIyC7fzMssYKl5c45Q6ejRCxxFsfq09ol6TSOvV-AAb8LOZW9BvMwFKxr2FNDQEMpecIK5k0Il0RTfV9gBOkIYuqYjnynOACJPFpe71uszmt_Tn_kb5aPueQWGP88CO-Ae9XdtmFiWy_1Yj70bYrAXnxX4DzxmEAeT1aIpScqd9tfsCJEffSa1tXqa9JCJx0dK_SLPb0L7klkoQ1JTAM3Jl3W4BLOyn0jVSwbtf3fYPLnOiyfNfB6TUxYY_0mgcsEyhiZQLn0wDl_QSIXkB2MQ
```
Response:
```json
{
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ0ZXN0Iiwic2NvcGUiOlsib3BlbmlkIl0sInJvbGVzIjpbeyJpZCI6MSwibmFtZSI6IlJPTEVfQURNSU4iLCJwcml2aWxlZ2VzIjpbeyJpZCI6MSwiYXV0aG9yaXR5IjoiUkVBRF9QUklWSUxFR0UifSx7ImlkIjoyLCJhdXRob3JpdHkiOiJXUklURV9QUklWSUxFR0UifV19XSwiaWQiOjEsImV4cCI6MTYyMzUzMTkyMCwiYXV0aG9yaXRpZXMiOlsiUkVBRF9QUklWSUxFR0UiLCJXUklURV9QUklWSUxFR0UiXSwianRpIjoiZDUwZjEwNjQtNTIzYi00ZTc1LWJiMzItNDhlMGYzZGRkYzE4IiwiZW1haWwiOiJ0ZXN0QHRlc3QuY29tIiwiY2xpZW50X2lkIjoiZmFybWVydHJhY2tpbmcifQ.QWAsqlDFvWJlsozvgfC9c28Pr1sRwi1g5_FuwFFHJE1ELO8LbebrMIlTmkhPFNZqop8BRVyfFBn7JeV6XDPPpURpTl4kYlTNIEzdhKTT_DLxGzZ34AqEZ-dchc_FJH4rnkyVUulLURB9rJQEHzL8DmF9_i7TJdXmB-nzXKBGtVujcCLKT2cyGo0GliF9-0phqXhajQXGEZJ9LiSsbbtBl5zUOwyviKmeKyaQSH8sTPFyB96yn_7qmy8N-q9s2PCpnx4Z-olw0G9PSZOGaY3cwW5DGIqnM8qffS1paOT0NCxEJ33jE1uHfwTt6lU_FaAnE0NnAzc46pvQqOfPsvvyqw",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ0ZXN0Iiwic2NvcGUiOlsib3BlbmlkIl0sInJvbGVzIjpbeyJpZCI6MSwibmFtZSI6IlJPTEVfQURNSU4iLCJwcml2aWxlZ2VzIjpbeyJpZCI6MSwiYXV0aG9yaXR5IjoiUkVBRF9QUklWSUxFR0UifSx7ImlkIjoyLCJhdXRob3JpdHkiOiJXUklURV9QUklWSUxFR0UifV19XSwiYXRpIjoiZDUwZjEwNjQtNTIzYi00ZTc1LWJiMzItNDhlMGYzZGRkYzE4IiwiaWQiOjEsImV4cCI6MTYyMzUzNDYyMCwiYXV0aG9yaXRpZXMiOlsiUkVBRF9QUklWSUxFR0UiLCJXUklURV9QUklWSUxFR0UiXSwianRpIjoiMzA2MmEwMGUtMDc5Ny00YzE2LTk0ZTMtNWU3Yzg3Y2RhYjBkIiwiZW1haWwiOiJ0ZXN0QHRlc3QuY29tIiwiY2xpZW50X2lkIjoiZmFybWVydHJhY2tpbmcifQ.nUEJjZWQQ81bLORKqvt1qpATDSiLBVuyzjDF-bTbOmaHOrH1vV_cks1bguEd-7KKjin_P1n9PdM5gayeOMIzB5m3uva7k-udotWdQC0X-4BOk39w1wnfM8Rz3A4LITHt2BSjOCvG_G73bdPH40vBd1NNAkYuxrulJ7_mJrQGo5j4q-3sVDa6y7wRTHYK6Dk433IBplLUpZMuRPEsd2E9K12l28iUXoZY5s5x_KieLhjadzeCFIOXyfCTdImNPrEJ_c9cm91kX88DzYIUqn5cUX_3GBuCnM70reX_06LOYXuxR4IX4I1cmh5LlRtYtGX9sO20r-w8pt2p8Rz-W0doWA",
    "expires_in": 899,
    "scope": "openid",
    "id": 1,
    "email": "test@test.com",
    "roles": [
        {
            "id": 1,
            "name": "ROLE_ADMIN",
            "privileges": [
                {
                    "id": 1,
                    "authority": "READ_PRIVILEGE"
                },
                {
                    "id": 2,
                    "authority": "WRITE_PRIVILEGE"
                }
            ]
        }
    ],
    "jti": "d50f1064-523b-4e75-bb32-48e0f3dddc18"
}
```

Also you can use facebook login feature

When you open [login url](http://localhost:8080/login) in browser and click Facebook link. After you will redirect facebook authentication page. If you successfully authenticateyou receive a access bearer token from facebook 

If you want you can add additional information to jwt token, token inside like that:

```json
{
  "user_name": "test",
  "scope": [
    "openid"
  ],
  "roles": [
    {
      "id": 1,
      "name": "ROLE_ADMIN",
      "privileges": [
        {
          "id": 1,
          "authority": "READ_PRIVILEGE"
        },
        {
          "id": 2,
          "authority": "WRITE_PRIVILEGE"
        }
      ]
    }
  ],
  "id": 1,
  "exp": 1623531920,
  "authorities": [
    "READ_PRIVILEGE",
    "WRITE_PRIVILEGE"
  ],
  "jti": "d50f1064-523b-4e75-bb32-48e0f3dddc18",
  "email": "test@test.com",
  "client_id": "farmertracking"
}
```
