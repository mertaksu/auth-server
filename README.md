# Auth-Server
Common Authentication &amp; Authorization server for all microservices.

With this project we can integrate oauth2 authentication feature to microservices. When you call following api you can get a response json.

You have to call this api with basic authentication info like;
username: USER_CLIENT_APP
pass: test

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
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ0ZXN0Iiwic2NvcGUiOlsib3BlbmlkIl0sImlkIjoxLCJleHAiOjE2MjM1Mjg0NzIsImF1dGhvcml0aWVzIjpbIlJFQURfUFJJVklMRUdFIiwiV1JJVEVfUFJJVklMRUdFIl0sImp0aSI6IjM1YTg2YTVmLTY2ODgtNGJmMS1hMzczLTZkMjEwZmIzODMzYyIsImVtYWlsIjoidGVzdEB0ZXN0LmNvbSIsImNsaWVudF9pZCI6IlVTRVJfQ0xJRU5UX0FQUCJ9.t1ExeiQ9q53xiD7ly-mJaqXHuIO5gD67zAusdiY-sAqj54ypiT6Ffe-bbk3U6MKg8A3A7ygqSnSMxNCR6pKCAiHQ7vqvKsgcS_jUGl4ghIm3fu6UhSIvRz5--rbtrWulmXxc1jL-UWwYfcrGnF2Q9vz7vitAzM71MiK8Xw3BgdbBCf6XkMb3kgxDBiJFOpJI9jIr6A0uh6jIsoLbxedxRsEsRKArZskRr5XQTfpS6o7PrWXi72dJX-mdYs9sT6TtIJ7uywyVC6kbhQ27PeR0UBpnB5GOg0l1g5ofEtsnWaUCFX7glA3HmX0jWhpwj9R2rWsV_Js6GlPAQBuUXSMzJg",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ0ZXN0Iiwic2NvcGUiOlsib3BlbmlkIl0sImF0aSI6IjM1YTg2YTVmLTY2ODgtNGJmMS1hMzczLTZkMjEwZmIzODMzYyIsImlkIjoxLCJleHAiOjE2MjM1MzExNzIsImF1dGhvcml0aWVzIjpbIlJFQURfUFJJVklMRUdFIiwiV1JJVEVfUFJJVklMRUdFIl0sImp0aSI6IjQxMzE2NzRjLTYxYTMtNGZlYy1iYTdmLTc3YTc5Yzk1ZjA5NCIsImVtYWlsIjoidGVzdEB0ZXN0LmNvbSIsImNsaWVudF9pZCI6IlVTRVJfQ0xJRU5UX0FQUCJ9.A1eYYXetc2dqP2_2AU9RYg9dzYDGYkPmLTowO6fDqzIBsHwczci0AvNDcQC-gB4HwIyC7fzMssYKl5c45Q6ejRCxxFsfq09ol6TSOvV-AAb8LOZW9BvMwFKxr2FNDQEMpecIK5k0Il0RTfV9gBOkIYuqYjnynOACJPFpe71uszmt_Tn_kb5aPueQWGP88CO-Ae9XdtmFiWy_1Yj70bYrAXnxX4DzxmEAeT1aIpScqd9tfsCJEffSa1tXqa9JCJx0dK_SLPb0L7klkoQ1JTAM3Jl3W4BLOyn0jVSwbtf3fYPLnOiyfNfB6TUxYY_0mgcsEyhiZQLn0wDl_QSIXkB2MQ",
    "expires_in": 899,
    "scope": "openid",
    "id": 1,
    "email": "test@test.com",
    "jti": "35a86a5f-6688-4bf1-a373-6d210fb3833c"
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
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ0ZXN0Iiwic2NvcGUiOlsib3BlbmlkIl0sImlkIjoxLCJleHAiOjE2MjM1Mjg5OTMsImF1dGhvcml0aWVzIjpbIlJFQURfUFJJVklMRUdFIiwiV1JJVEVfUFJJVklMRUdFIl0sImp0aSI6IjJhOWE0NDU3LWI0ODQtNGIxMy1iYTljLTA1NmU5MTliMTcwMyIsImVtYWlsIjoidGVzdEB0ZXN0LmNvbSIsImNsaWVudF9pZCI6IlVTRVJfQ0xJRU5UX0FQUCJ9.G8_-Zhm26iefLd4rZsxTpgEgnuCo1po7bN1pZV-FYxynS1rjbL8O_YEfYH0jREChvuLwv5-5E1jD1Q13DqCJwPcwHmKQ0WB07onYNBDN7G2abBBTwYJPZRy_00ivICD5Cv5TdHUH2qZwNpd5Lp6WnF_45pGa0nyTe59CR6oRGGfRJ6hE_bRoh7mkJShq4tv9kk1oCfC94J8wn4RuwqH3AQM-Z9-vmLSlbSsfXDcq3BfQzw3q1kzEjlpqrj0UgbfjuPAdmJ05vNhAE4ZU5iAY0EpCXXrFC5Yz7UbVU7f8xQRcHn2RzctvWdlNvVHAZZg4ZAFnePavLqFLqxwYCT8WBQ",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ0ZXN0Iiwic2NvcGUiOlsib3BlbmlkIl0sImF0aSI6IjJhOWE0NDU3LWI0ODQtNGIxMy1iYTljLTA1NmU5MTliMTcwMyIsImlkIjoxLCJleHAiOjE2MjM1MzE2MzEsImF1dGhvcml0aWVzIjpbIlJFQURfUFJJVklMRUdFIiwiV1JJVEVfUFJJVklMRUdFIl0sImp0aSI6IjhiYThjZWZmLTllYmUtNGFhZC04NjBjLWVhNDVlZjVjNTVhYyIsImVtYWlsIjoidGVzdEB0ZXN0LmNvbSIsImNsaWVudF9pZCI6IlVTRVJfQ0xJRU5UX0FQUCJ9.B97_Myj8LrSH5Qxuwc31fXym8Bm_8zFD4rX8qrxyLmIQRNDmZTMqdgPgK3OoEpN8fRAQQhESZL1ayE3SM7t_qUVdmWBtOoczc40dCj5kO2d9zUy3AF4_r37zZZ8qHefVFmGnlgffCPwCkiJjqTNN9BUrgvphIJQhsCs0e2aZP9heix__bjs1aLYSL8VKrgokzdDf0MLCHpyNPaIeA78MgriVGyM1LHJkWDXSKxEb7MEQJpS3W2c6q0giJk6urkHqg4kvkJL1hBZE-GBCoI9-WxbDgqDo0qC5L6aJYCftcpcrykrLXv2VA2eTLR0EvnWx8fkWEEJ-5HSQ4796Id8f9A",
    "expires_in": 899,
    "scope": "openid",
    "id": 1,
    "email": "test@test.com",
    "jti": "2a9a4457-b484-4b13-ba9c-056e919b1703"
}
```

Also you can use facebook login feature

When you open [login url](http://localhost:8080/login) in browser and click Facebook link. After you will redirect facebook authentication page. If you successfully authenticateyou receive a access bearer token from facebook 
